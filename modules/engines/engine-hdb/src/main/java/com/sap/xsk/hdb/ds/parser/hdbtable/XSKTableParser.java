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
package com.sap.xsk.hdb.ds.parser.hdbtable;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.artefacts.HDBTableSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintPrimaryKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintUniqueModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintsModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableIndexModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.hdb.ds.transformer.HDBTableDefinitionModelToHDBTableColumnModelTransformer;
import com.sap.xsk.parser.hdbtable.core.HdbtableLexer;
import com.sap.xsk.parser.hdbtable.core.HdbtableParser;
import com.sap.xsk.parser.hdbtable.custom.XSKHDBTABLECoreVisitor;
import com.sap.xsk.parser.hdbtable.custom.XSKHDBTABLESyntaxErrorListener;
import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableMissingPropertyException;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEColumnsModel;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEDefinitionModel;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEIndexesModel;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKTableParser implements XSKDataStructureParser<XSKDataStructureHDBTableModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableParser.class);
  private HDBTableDefinitionModelToHDBTableColumnModelTransformer columnModelTransformer = new HDBTableDefinitionModelToHDBTableColumnModelTransformer();
  private static final HDBTableSynchronizationArtefactType TABLE_ARTEFACT = new HDBTableSynchronizationArtefactType();
  private static final XSKDataStructuresSynchronizer dataStructuresSynchronizer = new XSKDataStructuresSynchronizer();

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_TABLE;
  }

  @Override
  public Class<XSKDataStructureHDBTableModel> getDataStructureClass() {
    return XSKDataStructureHDBTableModel.class;
  }

  @Override
  public XSKDataStructureHDBTableModel parse(XSKDataStructureParametersModel parametersModel)
      throws XSKDataStructuresException, IOException, XSKArtifactParserException {
    Pattern pattern = Pattern.compile("^(\\t\\n)*(\\s)*(COLUMN)(\\t\\n)*(\\s)*(TABLE)", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(parametersModel.getContent().trim().toUpperCase(Locale.ROOT));
    boolean matchFound = matcher.find();
    return (matchFound)
        ? parseHanaXSAdvancedContent(parametersModel.getLocation(), parametersModel.getContent())
        : parseHanaXSClassicContent(parametersModel.getLocation(), parametersModel.getContent());
  }

  private XSKDataStructureHDBTableModel parseHanaXSClassicContent(String location, String content)
      throws IOException, XSKArtifactParserException {
    logger.debug("Parsing hdbtable as Hana XS Classic format");
    ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
    ANTLRInputStream inputStream = new ANTLRInputStream(is);
    HdbtableLexer lexer = new HdbtableLexer(inputStream);
    XSKHDBTABLESyntaxErrorListener lexerErrorListener = new XSKHDBTABLESyntaxErrorListener();
    lexer.removeErrorListeners();
    lexer.addErrorListener(lexerErrorListener);
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);

    HdbtableParser hdbtableParser = new HdbtableParser(tokenStream);
    hdbtableParser.setBuildParseTree(true);
    hdbtableParser.removeErrorListeners();

    XSKHDBTABLESyntaxErrorListener parserErrorListener = new XSKHDBTABLESyntaxErrorListener();
    hdbtableParser.addErrorListener(parserErrorListener);
    ParseTree parseTree = hdbtableParser.hdbtableDefinition();
    XSKCommonsUtils.logParserErrors(parserErrorListener.getErrors(), XSKCommonsConstants.PARSER_ERROR, location, XSKCommonsConstants.HDB_TABLE_PARSER);
    XSKCommonsUtils.logParserErrors(lexerErrorListener.getErrors(), XSKCommonsConstants.LEXER_ERROR, location, XSKCommonsConstants.HDB_TABLE_PARSER);

    XSKHDBTABLECoreVisitor xskhdbtableCoreVisitor = new XSKHDBTABLECoreVisitor();

    JsonElement parsedResult = xskhdbtableCoreVisitor.visit(parseTree);

    Gson gson = new Gson();

    XSKHDBTABLEDefinitionModel hdbtableDefinitionModel = gson.fromJson(parsedResult, XSKHDBTABLEDefinitionModel.class);
    String artefactName = XSKCommonsUtils.getRepositoryBaseObjectName(location);
    try {
      hdbtableDefinitionModel.checkForAllMandatoryFieldsPresence();
    } catch (Exception e) {
      XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", e.getMessage(),
          XSKCommonsConstants.EXPECTED_FIELDS, XSKCommonsConstants.HDB_TABLE_PARSER, XSKCommonsConstants.MODULE_PARSERS,
          XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
      dataStructuresSynchronizer.applyArtefactState(artefactName,location,TABLE_ARTEFACT, ArtefactState.FAILED_CREATE, e.getMessage());
      throw new XSKHDBTableMissingPropertyException(
          String.format("Wrong format of table definition: [%s]. [%s]", location, e.getMessage()));
    }

    XSKDataStructureHDBTableModel dataStructureHDBTableModel = new XSKDataStructureHDBTableModel();

    XSKHDBUtils.populateXSKDataStructureModel(location, content, dataStructureHDBTableModel, IXSKDataStructureModel.TYPE_HDB_TABLE,
        XSKDBContentType.XS_CLASSIC);
    dataStructureHDBTableModel.setSchema(hdbtableDefinitionModel.getSchemaName());
    dataStructureHDBTableModel.setDescription(hdbtableDefinitionModel.getDescription());
    dataStructureHDBTableModel.setLoggingType(hdbtableDefinitionModel.getLoggingType());
    dataStructureHDBTableModel.setPublicProp(hdbtableDefinitionModel.isPublic());
    dataStructureHDBTableModel.setTemporary(hdbtableDefinitionModel.getTemporary());
    dataStructureHDBTableModel.setTableType(hdbtableDefinitionModel.getTableType());
    dataStructureHDBTableModel.setRawContent(content);
    dataStructureHDBTableModel.setColumns(columnModelTransformer.transform(hdbtableDefinitionModel, location));
    dataStructureHDBTableModel.setConstraints(new XSKDataStructureHDBTableConstraintsModel());

    XSKDataStructureHDBTableConstraintPrimaryKeyModel primaryKey = new XSKDataStructureHDBTableConstraintPrimaryKeyModel();
    primaryKey.setColumns(hdbtableDefinitionModel.getPkColumns().toArray(String[]::new));
    primaryKey.setName("PK_" + dataStructureHDBTableModel.getName());
    dataStructureHDBTableModel.getConstraints().setPrimaryKey(primaryKey);

    hdbtableDefinitionModel.getPkColumns().forEach(key -> {
      List<XSKHDBTABLEColumnsModel> foundMatchKey = hdbtableDefinitionModel.getColumns().stream().filter(x -> x.getName().equals(key))
          .collect(Collectors.toList());
      if (foundMatchKey.size() != 1) {
        String errMsg = String.format("%s: the column does not have a definition but is specified as a primary key", key);
        XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", errMsg,
            "", XSKCommonsConstants.HDB_TABLE_PARSER,XSKCommonsConstants.MODULE_PARSERS,
            XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
        dataStructuresSynchronizer.applyArtefactState(artefactName,location,TABLE_ARTEFACT, ArtefactState.FAILED_CREATE, errMsg);
        throw new IllegalStateException(errMsg);
      }
    });

    List<XSKDataStructureHDBTableConstraintUniqueModel> uniqueIndices = new ArrayList<>();
    List<XSKDataStructureHDBTableIndexModel> indexes = new ArrayList<>();

    if (hdbtableDefinitionModel.getIndexes() != null) {
      for (XSKHDBTABLEIndexesModel index : hdbtableDefinitionModel.getIndexes()) {
        validateIndex(hdbtableDefinitionModel, location, index);
        if (index.isUnique()) {
          XSKDataStructureHDBTableConstraintUniqueModel uniqueIndexModel = new XSKDataStructureHDBTableConstraintUniqueModel();
          if(index.getIndexName() != null){
            uniqueIndexModel.setIndexName(index.getIndexName());
          }
          if (index.getIndexType() != null) {
            uniqueIndexModel.setIndexType(this.convertIfHanaClassicSyntax(index));
          }
          if (index.getOrder() != null) {
            uniqueIndexModel
                .setOrder(index.getOrder().equals(XSKConstants.HDBTABLE_INDEX_ORDER_HANA_V1_DSC) ? XSKConstants.HDBTABLE_INDEX_ORDER_HANA_DESC : index.getOrder());
          }
          uniqueIndexModel.setColumns(index.getIndexColumns().toArray(String[]::new));

          uniqueIndices.add(uniqueIndexModel);
        }else{
          XSKDataStructureHDBTableIndexModel tableIndexModel = new XSKDataStructureHDBTableIndexModel();
          if(index.getIndexName() != null){
            tableIndexModel.setIndexName(index.getIndexName());
          }
          if (index.getIndexType() != null) {
            tableIndexModel.setIndexType(convertIfHanaClassicSyntax(index));
          }
          tableIndexModel.setUnique(index.isUnique());
          if (index.getOrder() != null) {
            tableIndexModel
                .setOrder(index.getOrder().equals(XSKConstants.HDBTABLE_INDEX_ORDER_HANA_V1_DSC) ? XSKConstants.HDBTABLE_INDEX_ORDER_HANA_DESC : index.getOrder());
          }
          tableIndexModel.setIndexColumns(index.getIndexColumns());
          indexes.add(tableIndexModel);
        }
      }
      dataStructureHDBTableModel.getConstraints().setUniqueIndices(uniqueIndices);
      dataStructureHDBTableModel.setIndexes(indexes);
    }

    return dataStructureHDBTableModel;
  }

  private XSKDataStructureHDBTableModel parseHanaXSAdvancedContent(String location, String content) {
    logger.debug("Parsing hdbtable as Hana XS Advanced format");
    XSKDataStructureHDBTableModel dataStructureHDBTableModel = new XSKDataStructureHDBTableModel();
    XSKHDBUtils.populateXSKDataStructureModel(location, content, dataStructureHDBTableModel, IXSKDataStructureModel.TYPE_HDB_TABLE,
        XSKDBContentType.OTHERS);
    dataStructureHDBTableModel.setRawContent(content);
    return dataStructureHDBTableModel;
  }

  private void validateIndex(XSKHDBTABLEDefinitionModel hdbtableDefinitionModel, String location, XSKHDBTABLEIndexesModel index) {
    String artefactName = XSKCommonsUtils.getRepositoryBaseObjectName(location);
    try {
      index.checkForAllIndexMandatoryFieldsPresence();
    } catch (Exception e) {
      XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", e.getMessage(),
          XSKCommonsConstants.EXPECTED_FIELDS, XSKCommonsConstants.HDB_TABLE_PARSER, XSKCommonsConstants.MODULE_PARSERS,
          XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
      dataStructuresSynchronizer.applyArtefactState(artefactName,location,TABLE_ARTEFACT, ArtefactState.FAILED_CREATE, e.getMessage());
      throw new XSKHDBTableMissingPropertyException(
          String.format("Wrong format of table definition: [%s]. [%s]", location, e.getMessage()));
    }

    index.getIndexColumns().forEach(col -> {
      List<XSKHDBTABLEColumnsModel> foundMatch = hdbtableDefinitionModel.getColumns().stream().filter(x -> x.getName().equals(col))
          .collect(Collectors.toList());
      if (foundMatch.size() != 1) {
        String errMsg = String.format("%s: the column does not have a definition but is specified as an index", col);
        XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", errMsg,
            "", XSKCommonsConstants.HDB_TABLE_PARSER, XSKCommonsConstants.MODULE_PARSERS,
            XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
        dataStructuresSynchronizer.applyArtefactState(artefactName,location,TABLE_ARTEFACT, ArtefactState.FAILED_CREATE, errMsg);
        throw new IllegalStateException(errMsg);
      }
    });
  }

  private String convertIfHanaClassicSyntax(XSKHDBTABLEIndexesModel index) {
    if (index.getIndexType().equals("CPB_TREE")) {
      return "CPBTREE";
    } else if (index.getIndexType().equals("B_TREE")) {
      return "BTREE";
    } else {
      return index.getIndexType();
    }
  }

}

