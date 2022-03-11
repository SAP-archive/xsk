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
package com.sap.xsk.hdb.ds.parser.hdbtabletype;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypePrimaryKeyModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.transformer.HDBTableDefinitionModelToHDBTableColumnModelTransformer;
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
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKTableTypeParser implements XSKDataStructureParser<XSKDataStructureHDBTableTypeModel> {

  // TYPE (?:["'](.*)["'].)?["'](.*)["']
  // uses non-capturing group in order to handle the
  // possible case of a table type with only a name and no schema
  private static final Pattern TABLE_TYPE_SCHEMA_AND_NAME_PATTERN = Pattern.compile("TYPE\\s+(?:[\"'](.*)[\"'].)?[\"'](.*)[\"']");
  private static final Pattern XS_ADVANCED_TABLE_TYPE_PATTERN = Pattern.compile("^(\\t\\n)*(\\s)*TYPE", Pattern.CASE_INSENSITIVE);
  private static final Logger logger = LoggerFactory.getLogger(XSKTableTypeParser.class);
  private final HDBTableDefinitionModelToHDBTableColumnModelTransformer columnModelTransformer = new HDBTableDefinitionModelToHDBTableColumnModelTransformer();

  @Override
  public XSKDataStructureHDBTableTypeModel parse(XSKDataStructureParametersModel parametersModel)
      throws XSKDataStructuresException, IOException, XSKArtifactParserException {
    String contentWithoutPossibleComments = XSKHDBUtils.removeSqlCommentsFromContent(parametersModel.getContent());
    Matcher matcher = XS_ADVANCED_TABLE_TYPE_PATTERN.matcher(contentWithoutPossibleComments.trim());
    boolean matchFound = matcher.find();
    return (matchFound)
        ? parseHanaXSAdvancedContent(parametersModel.getLocation(), parametersModel.getContent())
        : parseHanaXSClassicContent(parametersModel.getLocation(), parametersModel.getContent());
  }

  private XSKDataStructureHDBTableTypeModel parseHanaXSClassicContent(String location, String content)
      throws IOException, XSKArtifactParserException {
    logger.debug("Parsing hdbstructure in Hana XS Classic format");
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
    XSKCommonsUtils.logParserErrors(parserErrorListener.getErrors(), XSKCommonsConstants.PARSER_ERROR, location,
        XSKCommonsConstants.HDB_TABLE_TYPE_PARSER);
    XSKCommonsUtils.logParserErrors(lexerErrorListener.getErrors(), XSKCommonsConstants.LEXER_ERROR, location,
        XSKCommonsConstants.HDB_TABLE_TYPE_PARSER);

    XSKHDBTABLECoreVisitor xskhdbtableCoreVisitor = new XSKHDBTABLECoreVisitor();

    JsonElement parsedResult = xskhdbtableCoreVisitor.visit(parseTree);

    Gson gson = new Gson();

    XSKHDBTABLEDefinitionModel hdbtableDefinitionModel = gson.fromJson(parsedResult, XSKHDBTABLEDefinitionModel.class);
    try {
      hdbtableDefinitionModel.checkForAllMandatoryFieldsPresence();
    } catch (Exception e) {
      XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", e.getMessage(),
          XSKCommonsConstants.EXPECTED_FIELDS, XSKCommonsConstants.HDB_TABLE_TYPE_PARSER, XSKCommonsConstants.MODULE_PARSERS,
          XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
      throw new XSKHDBTableMissingPropertyException(
          String.format("Wrong format of table definition: [%s]. [%s]", location, e.getMessage()));
    }

    XSKDataStructureHDBTableTypeModel dataStructureHDBTableTypeModel = new XSKDataStructureHDBTableTypeModel();

    XSKHDBUtils.populateXSKDataStructureModel(location, content, dataStructureHDBTableTypeModel, IXSKDataStructureModel.TYPE_HDB_TABLE_TYPE,
        XSKDBContentType.XS_CLASSIC);
    dataStructureHDBTableTypeModel.setSchema(hdbtableDefinitionModel.getSchemaName());
    dataStructureHDBTableTypeModel.setPublicProp(hdbtableDefinitionModel.isPublic());
    dataStructureHDBTableTypeModel.setRawContent(content);
    dataStructureHDBTableTypeModel.setColumns(columnModelTransformer.transform(hdbtableDefinitionModel, location));

    XSKDataStructureHDBTableTypePrimaryKeyModel primaryKey = new XSKDataStructureHDBTableTypePrimaryKeyModel();
    primaryKey.setPrimaryKeyColumns(hdbtableDefinitionModel.getPkColumns().toArray(String[]::new));
    primaryKey.setName("PK_" + dataStructureHDBTableTypeModel.getName());
    dataStructureHDBTableTypeModel.setPrimaryKey(primaryKey);

    hdbtableDefinitionModel.getPkColumns().forEach(key -> {
      List<XSKHDBTABLEColumnsModel> foundMatchKey = hdbtableDefinitionModel.getColumns().stream().filter(x -> x.getName().equals(key))
          .collect(Collectors.toList());
      if (foundMatchKey.size() != 1) {
        String errMsg = String.format("%s: the column does not have a definition but is specified as a primary key", key);
        XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", errMsg,
            "", XSKCommonsConstants.HDB_TABLE_TYPE_PARSER, XSKCommonsConstants.MODULE_PARSERS,
            XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
        throw new IllegalStateException(errMsg);
      }
    });

    return dataStructureHDBTableTypeModel;
  }

  private XSKDataStructureHDBTableTypeModel parseHanaXSAdvancedContent(String location, String content) {
    logger.debug("Parsing hdbstructure as Hana XS Advanced format");
    XSKDataStructureHDBTableTypeModel dataStructureHDBTableTypeModel = new XSKDataStructureHDBTableTypeModel();

    XSKHDBUtils.populateXSKDataStructureModel(location, content, dataStructureHDBTableTypeModel, IXSKDataStructureModel.TYPE_HDB_TABLE_TYPE,
        XSKDBContentType.OTHERS);
    Pair<String, String> schemaAndNamePair = extractTableTypeSchemaAndName(content);

    dataStructureHDBTableTypeModel.setSchema(schemaAndNamePair.getLeft());
    dataStructureHDBTableTypeModel.setName(schemaAndNamePair.getRight());
    dataStructureHDBTableTypeModel.setRawContent(content);

    return dataStructureHDBTableTypeModel;
  }

  private Pair<String, String> extractTableTypeSchemaAndName(String content) {
    String contentWithoutPossibleComments = XSKHDBUtils.removeSqlCommentsFromContent(content);
    Matcher matcher = TABLE_TYPE_SCHEMA_AND_NAME_PATTERN.matcher(contentWithoutPossibleComments);

    if (!matcher.find()) {
      throw new IllegalStateException("Couldn't extract table type schema and name from content: " + content);
    }

    return Pair.of(matcher.group(1), matcher.group(2));
  }

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_TABLE_TYPE;
  }

  @Override
  public Class<XSKDataStructureHDBTableTypeModel> getDataStructureClass() {
    return XSKDataStructureHDBTableTypeModel.class;
  }
}
