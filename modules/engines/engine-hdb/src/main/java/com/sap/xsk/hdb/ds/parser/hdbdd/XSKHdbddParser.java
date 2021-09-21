/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.parser.hdbdd;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureCdsModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.transformer.hdbdd.HdbddTransformer;
import com.sap.xsk.parser.hdbdd.core.CdsLexer;
import com.sap.xsk.parser.hdbdd.core.CdsParser;
import com.sap.xsk.parser.hdbdd.custom.EntityDefinitionListener;
import com.sap.xsk.parser.hdbdd.custom.ReferenceResolvingListener;
import com.sap.xsk.parser.hdbdd.custom.XSKHdbddErrorListener;
import com.sap.xsk.parser.hdbdd.exception.CDSRuntimeException;
import com.sap.xsk.parser.hdbdd.symbols.SymbolTable;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.StructuredDataTypeSymbol;
import com.sap.xsk.parser.utils.ParserConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;

public class XSKHdbddParser implements XSKDataStructureParser {

  private static final Logger logger = LoggerFactory.getLogger(XSKHdbddParser.class);

  private HdbddTransformer hdbddTransformer = new HdbddTransformer();
  private IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
  private SymbolTable symbolTable = new SymbolTable();
  private Map<String, Set<String>> usedFiles = new HashMap<>();

  @Override
  public XSKDataStructureModel parse(String location, String content) throws XSKDataStructuresException, IOException {
    for (String fileLocation : this.getFilesToProcess(location)) {
      IResource loadedResource = this.repository.getResource("/registry/public/" + fileLocation);
      String fileContent = new String(loadedResource.getContent());
      try {
        parseHdbdd(fileLocation, fileContent);
      } catch (CDSRuntimeException | XSKArtifactParserException | ProblemsException e) {
        this.symbolTable.clearSymbolsByFullName();
        this.symbolTable.clearEntityGraph();
        throw new XSKDataStructuresException(e.getMessage());
      }
    }

    XSKDataStructureCdsModel cdsModel = getCdsModel(location, content);
    this.symbolTable.clearSymbolsByFullName();
    this.symbolTable.clearEntityGraph();

    return cdsModel;
  }


  private void parseHdbdd(String location, String content) throws IOException, XSKArtifactParserException, ProblemsException {
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
    XSKCommonsUtils.logParserErrors(parserErrorListener.getErrors(), ParserConstants.PARSER_ERROR, location, "HDBDD");
    XSKCommonsUtils.logParserErrors(lexerErrorListener.getErrors(), ParserConstants.LEXER_ERROR, location, "HDBDD");

    EntityDefinitionListener entityDefinitionListener = new EntityDefinitionListener();
    entityDefinitionListener.setSymbolTable(symbolTable);
    entityDefinitionListener.setFileLocation(location);

    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    try {
      parseTreeWalker.walk(entityDefinitionListener, parseTree);
    } catch (CDSRuntimeException e) {
      throw new CDSRuntimeException(String.format("Failed to parse file: %s. %s", location, e.getMessage()));
    }

    entityDefinitionListener.getPackagesUsed().forEach(p -> {
      String fileLocation = getFileLocation(p);
      addUsedFile(fileLocation, location);

      try {
        IResource loadedResource = this.repository.getResource("/registry/public/" + fileLocation);
        parseHdbdd(fileLocation, new String(loadedResource.getContent()));
      } catch (IOException | ProblemsException | XSKArtifactParserException e) {
        e.printStackTrace();
      }
    });

    ReferenceResolvingListener referenceResolvingListener = new ReferenceResolvingListener();
    referenceResolvingListener.setCdsFileScope(entityDefinitionListener.getCdsFileScope());
    referenceResolvingListener.setSymbolTable(symbolTable);
    referenceResolvingListener.setEntityElements(entityDefinitionListener.getEntityElements());
    referenceResolvingListener.setTypeables(entityDefinitionListener.getTypeables());
    referenceResolvingListener.setAssociations(entityDefinitionListener.getAssociations());

    try {
      parseTreeWalker.walk(referenceResolvingListener, parseTree);
    } catch (CDSRuntimeException e) {
      throw new CDSRuntimeException(String.format("Failed to parse file: %s. %s", location, e.getMessage()));
    }
  }

  private void addUsedFile(String usedFile, String userFile) {
    Set<String> userFiles = usedFiles.get(usedFile);
    if (userFiles == null) {
      userFiles = new HashSet<>();
    }

    userFiles.add(userFile);
    this.usedFiles.put(usedFile, userFiles);
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
    Set<String> userFiles = usedFiles.get(usedFileName);
    if (userFiles == null) {
      rootFiles.add(usedFileName);
      return;
    }

    usedFiles.get(usedFileName).forEach(f -> {
      getRootFiles(f, rootFiles);
    });
  }

  private XSKDataStructureCdsModel getCdsModel(String location, String content) {
    List<EntitySymbol> parsedEntities = this.symbolTable.getSortedEntities();

    List<XSKDataStructureHDBTableModel> tableModels = new ArrayList<>();
    parsedEntities.forEach(e -> {
      tableModels.add(this.hdbddTransformer.transformEntitySymbolToTableModel(e));
    });

    List<StructuredDataTypeSymbol> structuredDataTypes = this.symbolTable.getTableTypes();
    List<XSKDataStructureHDBTableTypeModel> hdbTableTypeModels = new ArrayList<>();
    structuredDataTypes.forEach(sdt -> {
      hdbTableTypeModels.add(this.hdbddTransformer.transformStructuredDataTypeToHdbTableType(sdt));
    });

    XSKDataStructureCdsModel cdsModel = new XSKDataStructureCdsModel();
    cdsModel.setTableModels(tableModels);
    cdsModel.setTableTypeModels(hdbTableTypeModels);
    cdsModel.setName(location);
    cdsModel.setLocation(location);
    cdsModel.setType(getType());
    cdsModel.setCreatedBy(UserFacade.getName());
    cdsModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    cdsModel.setHash(DigestUtils.md5Hex(content));
    cdsModel.setDbContentType(XSKDBContentType.XS_CLASSIC);
    return cdsModel;
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
}
