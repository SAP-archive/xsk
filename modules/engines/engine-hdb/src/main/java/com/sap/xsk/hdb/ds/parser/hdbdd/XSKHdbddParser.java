/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.parser.hdbdd;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.artefacts.HDBDDEntitySynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureCdsModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.hdb.ds.transformer.hdbdd.HdbddTransformer;
import com.sap.xsk.parser.hdbdd.core.CdsLexer;
import com.sap.xsk.parser.hdbdd.core.CdsParser;
import com.sap.xsk.parser.hdbdd.custom.ArtifactDefinitionListener;
import com.sap.xsk.parser.hdbdd.custom.ReferenceResolvingListener;
import com.sap.xsk.parser.hdbdd.custom.XSKHdbddErrorListener;
import com.sap.xsk.parser.hdbdd.exception.CDSRuntimeException;
import com.sap.xsk.parser.hdbdd.symbols.SymbolTable;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.StructuredDataTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.view.ViewSymbol;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKHdbddParser implements XSKDataStructureParser {

  private static final Logger logger = LoggerFactory.getLogger(XSKHdbddParser.class);

  private HdbddTransformer hdbddTransformer = new HdbddTransformer();
  private IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
  private SymbolTable symbolTable = new SymbolTable();
  private Map<String, Set<String>> dependencyStructure = new HashMap<>();
  private Set<String> parsedNodes = new HashSet<>();
  private HDBDDEntitySynchronizationArtefactType ENTITY_ARTEFACT = new HDBDDEntitySynchronizationArtefactType();
  private XSKDataStructuresSynchronizer dataStructuresSynchronizer = new XSKDataStructuresSynchronizer();

  @Override
  public XSKDataStructureModel parse(XSKDataStructureParametersModel parametersModel) throws XSKDataStructuresException, IOException {
    for (String fileLocation : this.getFilesToProcess(parametersModel.getLocation())) {
      IResource loadedResource = this.repository.getResource(parametersModel.getWorkspacePath() + fileLocation);
      String fileContent = new String(loadedResource.getContent());
      try {
        parseHdbdd(fileLocation, fileContent);
      } catch (CDSRuntimeException | XSKArtifactParserException e) {
        this.symbolTable.clearSymbolsByFullName();
        this.symbolTable.clearEntityGraph();
        throw new XSKDataStructuresException(e.getMessage());
      }
    }

    XSKDataStructureCdsModel cdsModel = populateXSKDataStructureCdsModel(parametersModel.getLocation(), parametersModel.getContent());
    this.symbolTable.clearSymbolsByFullName();
    this.symbolTable.clearEntityGraph();
    this.symbolTable.clearViewGraph();
    parsedNodes.clear();

    return cdsModel;
  }


  private void parseHdbdd(String location, String content) throws IOException, XSKArtifactParserException {
    ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
    ANTLRInputStream inputStream = new ANTLRInputStream(is);
    CdsLexer hdbtiLexer = new CdsLexer(inputStream);
    CommonTokenStream tokenStream = new CommonTokenStream(hdbtiLexer);

    XSKHdbddErrorListener lexerErrorListener = new XSKHdbddErrorListener();
    hdbtiLexer.removeErrorListeners();//remove the ConsoleErrorListener
    hdbtiLexer.addErrorListener(lexerErrorListener);
    XSKHdbddErrorListener parserErrorListener = new XSKHdbddErrorListener();

    CdsParser hdbtiParser = new CdsParser(tokenStream);
    hdbtiParser.setBuildParseTree(true);
    hdbtiParser.removeErrorListeners();
    hdbtiParser.addErrorListener(parserErrorListener);

    ParseTree parseTree = hdbtiParser.cdsFile();
    XSKCommonsUtils.logParserErrors(parserErrorListener.getErrors(), XSKCommonsConstants.PARSER_ERROR, location,
        XSKCommonsConstants.HDBDD_PARSER);
    XSKCommonsUtils.logParserErrors(lexerErrorListener.getErrors(), XSKCommonsConstants.LEXER_ERROR, location,
        XSKCommonsConstants.HDBDD_PARSER);

    ArtifactDefinitionListener artifactDefinitionListener = new ArtifactDefinitionListener();
    artifactDefinitionListener.setSymbolTable(symbolTable);
    artifactDefinitionListener.setFileLocation(location);

    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    try {
      parseTreeWalker.walk(artifactDefinitionListener, parseTree);
    } catch (CDSRuntimeException e) {
      XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", e.getMessage(),
          "", XSKCommonsConstants.HDBDD_PARSER, XSKCommonsConstants.MODULE_PARSERS,
          XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
      dataStructuresSynchronizer.applyArtefactState(XSKCommonsUtils.getRepositoryBaseObjectName(location), location, ENTITY_ARTEFACT,
          ArtefactState.FAILED_CREATE, e.getMessage());
      throw new CDSRuntimeException(String.format("Failed to parse file: %s. %s", location, e.getMessage()));
    }

    artifactDefinitionListener.getPackagesUsed().forEach(p -> {
      String fileLocation = getFileLocation(p);
      addFileToDependencyTree(fileLocation, location);
      if (!parsedNodes.isEmpty() && parsedNodes.contains(fileLocation)) {
        return;
      }

      try {
        IResource loadedResource = this.repository.getResource("/registry/public/" + fileLocation);
        String loadedResourceContent = new String(loadedResource.getContent());
        parseHdbdd(fileLocation, loadedResourceContent);
        parsedNodes.add(fileLocation);
        synchronizeNodeMetadataFromRoot(fileLocation, loadedResourceContent);
      } catch (IOException | XSKArtifactParserException | XSKDataStructuresException e) {
        XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", e.getMessage(),
            "", XSKCommonsConstants.HDBDD_PARSER, XSKCommonsConstants.MODULE_PARSERS,
            XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
        dataStructuresSynchronizer.applyArtefactState(XSKCommonsUtils.getRepositoryBaseObjectName(location), location, ENTITY_ARTEFACT,
            ArtefactState.FAILED_CREATE, e.getMessage());
      }
    });

    ReferenceResolvingListener referenceResolvingListener = new ReferenceResolvingListener();
    referenceResolvingListener.setCdsFileScope(artifactDefinitionListener.getCdsFileScope());
    referenceResolvingListener.setSymbolTable(symbolTable);
    referenceResolvingListener.setEntityElements(artifactDefinitionListener.getEntityElements());
    referenceResolvingListener.setTypeables(artifactDefinitionListener.getTypeables());
    referenceResolvingListener.setAssociations(artifactDefinitionListener.getAssociations());

    try {
      parseTreeWalker.walk(referenceResolvingListener, parseTree);
    } catch (CDSRuntimeException e) {
      XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", e.getMessage(),
          "", XSKCommonsConstants.HDBDD_PARSER, XSKCommonsConstants.MODULE_PARSERS,
          XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
      dataStructuresSynchronizer.applyArtefactState(XSKCommonsUtils.getRepositoryBaseObjectName(location), location, ENTITY_ARTEFACT,
          ArtefactState.FAILED_CREATE, e.getMessage());
      throw new CDSRuntimeException(String.format("Failed to parse file: %s. %s", location, e.getMessage()));
    }
  }

  private void addFileToDependencyTree(String nodeFile, String rootFile) {
    Set<String> rootFiles = dependencyStructure.get(nodeFile);
    if (rootFiles == null) {
      rootFiles = new HashSet<>();
    }

    rootFiles.add(rootFile);
    this.dependencyStructure.put(nodeFile, rootFiles);
  }

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDBDD;
  }

  @Override
  public Class getDataStructureClass() {
    return XSKDataStructureCdsModel.class;
  }

  private List<String> getFilesToProcess(String fileLocation) {
    List<String> rootFiles = new ArrayList<>();
    getRootFiles(fileLocation, rootFiles);

    return rootFiles;
  }

  private void getRootFiles(String usedFileName, List<String> rootFiles) {
    Set<String> userFiles = dependencyStructure.get(usedFileName);
    if (userFiles == null) {
      rootFiles.add(usedFileName);
      return;
    }

    dependencyStructure.get(usedFileName).forEach(f -> {
      getRootFiles(f, rootFiles);
    });
  }

  private XSKDataStructureCdsModel populateXSKDataStructureCdsModel(String location, String content) {
    XSKDataStructureCdsModel cdsModel = getCdsModelBaseData(location, content);
    getCdsModelWithParsedData(cdsModel);

    return cdsModel;
  }

  private XSKDataStructureCdsModel getCdsModelBaseData(String location, String content) {
    XSKDataStructureCdsModel cdsModel = new XSKDataStructureCdsModel();
    cdsModel.setName(location);
    cdsModel.setLocation(location);
    cdsModel.setType(getType());
    cdsModel.setCreatedBy(UserFacade.getName());
    cdsModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    cdsModel.setHash(DigestUtils.md5Hex(content));
    cdsModel.setDbContentType(XSKDBContentType.XS_CLASSIC);

    return cdsModel;
  }

  private void getCdsModelWithParsedData(XSKDataStructureCdsModel cdsModel) {
    List<EntitySymbol> parsedEntities = this.symbolTable.getSortedEntities();
    List<ViewSymbol> parsedViews = this.symbolTable.getSortedViews();

    List<XSKDataStructureHDBTableModel> tableModels = new ArrayList<>();
    parsedEntities.forEach(e -> {
      tableModels.add(this.hdbddTransformer.transformEntitySymbolToTableModel(e, cdsModel.getLocation()));

      //One HDBDD file have only one schema, with apply to all entities inside the hdbdd file
      cdsModel.setSchema(e.getSchema());
    });

    List<StructuredDataTypeSymbol> structuredDataTypes = this.symbolTable.getTableTypes();
    List<XSKDataStructureHDBTableTypeModel> hdbTableTypeModels = new ArrayList<>();
    structuredDataTypes.forEach(sdt -> {
      if (!(sdt.getAnnotations().containsKey("GenerateTableType")) || (sdt.getAnnotation("GenerateTableType").getKeyValuePairs()
          .get("booleanValue").getValue()).equals("true")) {
        hdbTableTypeModels.add(this.hdbddTransformer.transformStructuredDataTypeToHdbTableType(sdt));
      }
    });

    List<XSKDataStructureHDBViewModel> viewModels = new ArrayList<>();
    parsedViews.forEach(v -> {
      viewModels.add(this.hdbddTransformer.transformViewSymbolToHdbViewModel(v, cdsModel.getLocation()));
    });

    cdsModel.setTableModels(tableModels);
    cdsModel.setTableTypeModels(hdbTableTypeModels);
    cdsModel.setViewModels(viewModels);
  }

  private String getFileLocation(String fullPackagePath) {
    String[] splitPackagePath = fullPackagePath.split("::");
    String directory = splitPackagePath[0];
    String topLevelCdsObject = splitPackagePath[1].split("\\.")[0];
    String fileLocation = directory + "." + topLevelCdsObject;
    fileLocation = fileLocation.replace('.', XSKConstants.UNIX_SEPARATOR);
    fileLocation = fileLocation + ".hdbdd";

    return XSKConstants.UNIX_SEPARATOR + fileLocation;
  }

  private void synchronizeNodeMetadataFromRoot(String location, String content) throws XSKDataStructuresException {
    XSKDataStructureCdsModel nodeCdsModel = getCdsModelBaseData(location, content);
    XSKHDBModule.getManagerServices().get(getType()).synchronizeParsedByRootMetadata(nodeCdsModel);
  }
}