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
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintPrimaryKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintUniqueModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintsModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableIndexModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.transformer.HDBTableDefinitionModelToHDBTableColumnModelTransformer;
import com.sap.xsk.hdb.ds.transformer.HDBTableDefinitionModelToHDBTableIndexModelTransformer;
import com.sap.xsk.parser.hdbtable.core.HdbtableLexer;
import com.sap.xsk.parser.hdbtable.core.HdbtableParser;
import com.sap.xsk.parser.hdbtable.custom.XSKHDBTABLECoreVisitor;
import com.sap.xsk.parser.hdbtable.custom.XSKHDBTABLESyntaxErrorListener;
import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableMissingPropertyException;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEColumnsModel;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEDefinitionModel;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKTableParser implements XSKDataStructureParser<XSKDataStructureHDBTableModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableParser.class);
  private HDBTableDefinitionModelToHDBTableColumnModelTransformer columnModelTransformer = new HDBTableDefinitionModelToHDBTableColumnModelTransformer();
  private HDBTableDefinitionModelToHDBTableIndexModelTransformer indexModelTransformer = new HDBTableDefinitionModelToHDBTableIndexModelTransformer();

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_TABLE;
  }

  @Override
  public Class<XSKDataStructureHDBTableModel> getDataStructureClass() {
    return XSKDataStructureHDBTableModel.class;
  }

  @Override
  public XSKDataStructureHDBTableModel parse(String location, String content)
      throws XSKDataStructuresException, IOException, XSKArtifactParserException {
    Pattern pattern = Pattern.compile("^(\\t\\n)*(\\s)*(COLUMN)(\\t\\n)*(\\s)*(TABLE)", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(content.trim().toUpperCase(Locale.ROOT));
    boolean matchFound = matcher.find();
    return (matchFound)
        ? parseHanaXSAdvancedContent(location, content)
        : parseHanaXSClassicContent(location, content);
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
    try {
      hdbtableDefinitionModel.checkForAllMandatoryFieldsPresence();
    } catch (Exception e) {
      XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", e.getMessage(),
          XSKCommonsConstants.EXPECTED_FIELDS, XSKCommonsConstants.HDB_TABLE_PARSER, XSKCommonsConstants.MODULE_PARSERS,
          XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
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
        throw new IllegalStateException(errMsg);
      }
    });

    if (hdbtableDefinitionModel.getIndexes() != null) {
      XSKDataStructureHDBTableConstraintUniqueModel uniqueIndex = new XSKDataStructureHDBTableConstraintUniqueModel();
      XSKDataStructureHDBTableIndexModel tableIndex = new XSKDataStructureHDBTableIndexModel();

      dataStructureHDBTableModel.setIndexes(indexModelTransformer.transform(hdbtableDefinitionModel, location, tableIndex));
      dataStructureHDBTableModel.getConstraints().setUniqueIndices(indexModelTransformer.transform(hdbtableDefinitionModel, location, uniqueIndex));
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

}

