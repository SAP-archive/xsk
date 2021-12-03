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
package com.sap.xsk.xsodata.ds.service;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.parser.xsodata.core.HdbxsodataLexer;
import com.sap.xsk.parser.xsodata.core.HdbxsodataParser;
import com.sap.xsk.parser.xsodata.custom.XSKHDBXSODATACoreListener;
import com.sap.xsk.parser.xsodata.custom.XSKHDBXSODATASyntaxErrorListener;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATABindingType;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAEntity;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAParameter;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAService;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.xsodata.ds.api.IXSKODataParser;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The factory for creation of the data structure models from source content.
 */
public class XSKODataParser implements IXSKODataParser {

  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  private static final List<String> METADATA_VIEW_TYPES = List.of(ISqlKeywords.METADATA_CALC_VIEW, ISqlKeywords.METADATA_VIEW);
  private static final List<String> METADATA_CALC_ANALYTIC_TYPES = List.of(ISqlKeywords.METADATA_CALC_VIEW);
  private static final List<String> METADATA_SYNONYM_TYPES = List.of(ISqlKeywords.METADATA_SYNONYM);

  private static final Logger logger = LoggerFactory.getLogger(XSKODataParser.class);

  private DBMetadataUtil dbMetadataUtil = new DBMetadataUtil();

  /**
   * Creates a odata model from the raw content.
   *
   * @param content the odata definition
   * @return the odata model instance
   * @throws IOException exception during parsing
   */
  public XSKODataModel parseXSODataArtifact(String location, String content)
      throws IOException, SQLException, XSKArtifactParserException {
    logger.debug("Parsing xsodata.");
    XSKODataModel odataModel = new XSKODataModel();
    odataModel.setName(new File(location).getName());
    odataModel.setLocation(location);
    odataModel.setHash(DigestUtils.md5Hex(content));
    odataModel.setCreatedBy(UserFacade.getName());
    odataModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

    ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
    ANTLRInputStream inputStream = new ANTLRInputStream(is);
    HdbxsodataLexer lexer = new HdbxsodataLexer(inputStream);
    XSKHDBXSODATASyntaxErrorListener lexerErrorListener = new XSKHDBXSODATASyntaxErrorListener();
    lexer.addErrorListener(lexerErrorListener);
    lexer.removeErrorListeners();

    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    HdbxsodataParser parser = new HdbxsodataParser(tokenStream);
    parser.setBuildParseTree(true);
    parser.removeErrorListeners();
    XSKHDBXSODATASyntaxErrorListener parserErrorListener = new XSKHDBXSODATASyntaxErrorListener();
    parser.addErrorListener(parserErrorListener);

    ParseTree parseTree = parser.xsodataDefinition();
    XSKCommonsUtils.logParserErrors(parserErrorListener.getErrors(), XSKCommonsConstants.PARSER_ERROR, location, XSKCommonsConstants.XSK_ODATA_PARSER);
    XSKCommonsUtils.logParserErrors(lexerErrorListener.getErrors(), XSKCommonsConstants.LEXER_ERROR, location, XSKCommonsConstants.XSK_ODATA_PARSER);

    XSKHDBXSODATACoreListener coreListener = new XSKHDBXSODATACoreListener();
    ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
    parseTreeWalker.walk(coreListener, parseTree);

    XSKHDBXSODATAService antlr4Model = coreListener.getServiceModel();
    odataModel.setService(antlr4Model);

    applyConditions(location, odataModel);

    return odataModel;
  }

  private void applyConditions(String location, XSKODataModel odataModel) throws SQLException {
    try {
      //the order of invocation matter, so do not change it
      applyEmptyExistCondition(location, odataModel);
      applyEntitySetCondition(odataModel);

      applyEmptyNamespaceCondition(location, odataModel);
      applyKeysCondition(odataModel);
      applyNavEntryFromEndCondition(odataModel);
      applyNumberOfJoinPropertiesCondition(odataModel);
      applyOrderOfJoinPropertiesCondition(odataModel);
      applyAggregatesWithKeyGeneratedCondition(odataModel);
      applyParametersToViewsCondition(odataModel);
      applyOmittedParamResultCondition(odataModel);
    } catch (Exception ex) {
      XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PARSER_ERROR, location, XSKCommonsConstants.XSK_ODATA_PARSER);
      throw ex;
    }
  }

  private void applyEmptyExistCondition(String location, XSKODataModel odataModel) throws SQLException {
    for (XSKHDBXSODATAEntity entity : odataModel.getService().getEntities()) {
      if (entity.getKeyList().size() > 0) {
        if (!checkIfEntityExist(entity.getRepositoryObject().getCatalogObjectName())) {
          throw new XSKOData2TransformerException(String
              .format("Entity: %s from %s don't exist. make sure the artifacts exist before processing the xsodata file",
                  entity.getRepositoryObject().getCatalogObjectName(), location));
        }
      }
    }
  }

  /**
   * If the namespace is not specified, the schema namespace in the EDMX metadata document will be the repository package of
   * the service definition file concatenated with the repository object name.
   * E.g. if the location name of the .xsodata file is /hdb-xsodata/test.xsodata the namespace will implicitly be hdb-xsodata.test
   */
  private void applyEmptyNamespaceCondition(String location, XSKODataModel odataModel) {
    if (odataModel.getService().getNamespace() == null) {
      String namespace = XSKCommonsUtils.getRepositoryNamespace(location) + "." + FilenameUtils.getBaseName(location);
      odataModel.getService().setNamespace(namespace);
    }
  }

  /**
   * keyslist must not be specified for objects of type 'table'.
   * They must only be applied to objects referring a view type.
   * keygenerated in turn, can be applied to table objects
   */
  private void applyKeysCondition(XSKODataModel odataModel) throws SQLException {
    for (XSKHDBXSODATAEntity entity : odataModel.getService().getEntities()) {
      if (entity.getKeyList().size() > 0) {
        String catalogObjectName = getCorrectCatalogObjectName(entity);

        if (!checkIfEntityIsOfViewType(catalogObjectName)) {
          throw new XSKOData2TransformerException(String
              .format("Keys cannot be specified for source %s. They must only be applied to objects referring a view type",
                  entity.getRepositoryObject().getCatalogObjectName()));
        }
      }
    }
  }

  /**
   * If the Alias is not specified in an entity, the Alias for this object is named after the repository object name.
   * For example, if object is "sap.hana.xs.doc/odata_docu", then the Alias is implicitly set to odata_docu,
   * which then can also be referenced in associations.
   */
  private void applyEntitySetCondition(XSKODataModel odataModel) {
    odataModel.getService().getEntities().forEach(entity -> {
      if (StringUtils.isEmpty(entity.getAlias()) || entity.getAlias() == null) {
        String baseObjName = XSKCommonsUtils.extractBaseObjectNameFromCatalogName(entity.getRepositoryObject().getCatalogObjectName());
        if (!StringUtils.isEmpty(baseObjName) || baseObjName == null) {
          entity.setAlias(baseObjName);
        } else {
          entity.setAlias(entity.getRepositoryObject().getCatalogObjectName());
        }
      }
    });
  }

  /**
   * The fromend in a navigation entry must be specified if the endtype is the same for both the principalend and the dependentend of an association.
   * Here is a working example:
   * <pre>
   *  "tables::A" navigates ("A_Self" as "ARef" from principal);
   *  association "A_Self"
   * 	principal "A"("SelfID") multiplicity "1"
   * 	dependent "A"("ID") multiplicity "0..1";
   * 	</pre>
   */
  private void applyNavEntryFromEndCondition(XSKODataModel odataModel) {
    odataModel.getService().getAssociations().forEach(ass -> {
      if (ass.getDependent().getEntitySetName().equals(ass.getPrincipal().getEntitySetName())) {
        List<XSKHDBXSODATAEntity> entity = odataModel.getService().getEntities().stream()
            .filter(el -> el.getAlias().equals(ass.getDependent().getEntitySetName())).collect(Collectors.toList());
        if (entity.size() == 1) {
          entity.get(0).getNavigates().forEach(nav -> {
            if (nav.getAssociation().equals(ass.getName())) {
              if (nav.getFromBindingType() == null) {
                throw new XSKOData2TransformerException(String
                    .format("Both ends of association %s are of type %s. Specify whether to navigate from principal or dependent.",
                        ass.getName(), ass.getDependent().getEntitySetName()));
              }
            }
          });
        } else {
          throw new XSKOData2TransformerException(String.format("Missing entity with alias: %s", ass.getDependent().getEntitySetName()));
        }
      }
    });
  }

  /**
   * The number of joinproperties in the principalend must be the same as in the dependentend.
   * Here is a working example:
   * <pre>
   *  association "someName"
   * 	principal "A"("ID1","ID2") multiplicity "1"
   * 	dependent "B"("ID3","ID4") multiplicity "0..1";
   * 	</pre>
   */
  private void applyNumberOfJoinPropertiesCondition(XSKODataModel odataModel) {
    odataModel.getService().getAssociations().forEach(ass -> {
      if (ass.getDependent().getBindingRole().getKeys().size() != ass.getPrincipal().getBindingRole().getKeys().size()) {
        throw new XSKOData2TransformerException(String.format("Different number of properties in ends of association %s.", ass.getName()));
      }
    });
  }

  /**
   * The overprincipalend corresponds to the principalend.
   * The number of properties in the joinproperties and the overproperties must be the same and their ordering is relevant.
   * The same statement is true for the dependent end.
   * Here is a working example:
   * <pre>
   *
   * "tables::A" navigates ("B_A_linktab" as "linkedBs");
   * "tables::B" navigates ("B_A_linktab" as "linkedAs");
   *
   * association "B_A_linktab"
   *  principal "A"("ID") multiplicity "*"
   *  dependent "B"("ID1") multiplicity "*"
   *  over "tables::linktab"
   *  principal ("AID")
   *  dependent ("BID1","BID2");
   * 	</pre>
   */
  private void applyOrderOfJoinPropertiesCondition(XSKODataModel odataModel) {
    odataModel.getService().getAssociations().forEach(ass -> {
      if (ass.getAssociationTable() != null) {
        if (ass.getAssociationTable().getDependent().getKeys().size() != ass.getDependent().getBindingRole().getKeys().size()) {
          throw new XSKOData2TransformerException(String
              .format("Different number of %s properties in association %s", XSKHDBXSODATABindingType.DEPENDENT.getText(), ass.getName()));
        }
        if (ass.getAssociationTable().getPrincipal().getKeys().size() != ass.getPrincipal().getBindingRole().getKeys().size()) {
          throw new XSKOData2TransformerException(String
              .format("Different number of %s properties in association %s", XSKHDBXSODATABindingType.PRINCIPAL.getText(), ass.getName()));
        }
      }
    });
  }

  /**
   * Aggregates can only be applied in combination with keygenerated.
   * Here is a working example:
   * <pre>
   * "sap.test.odata.db.views::COUNT_VIEW"
   * 		keys generate local "ID"
   * 		aggregates always (SUM of "COUNTER");
   * 	</pre>
   */
  private void applyAggregatesWithKeyGeneratedCondition(XSKODataModel odataModel) {
    odataModel.getService().getEntities().forEach(entity -> {
      if (entity.getAggregationType() != null) {
        if (entity.getKeyGenerated() == null) {
          throw new XSKOData2TransformerException(String.format("Missing specification of keys for view  %s", entity.getAlias()));
        }
      }
    });
  }

  /**
   * Specifying parameters is only possible for calculation views and analytic views
   */
  private void applyParametersToViewsCondition(XSKODataModel odataModel) throws SQLException {
    for (XSKHDBXSODATAEntity entity : odataModel.getService().getEntities()) {
      if (entity.getParameterEntitySet() != null) {
        String catalogObjectName = getCorrectCatalogObjectName(entity);

        if (!checkIfEntityIsOfCalcAndAnalyticViewType(catalogObjectName)) {
          throw new XSKOData2TransformerException(String
              .format("Parameters are not allowed for entity %s as it is not a calculation or analytical view.",
                  entity.getRepositoryObject().getCatalogObjectName()));
        }
      }
    }
  }

  /**
   * If the parametersresultsprop is omitted, the navigation property from the parameter entity set to the entity is called "Results".
   * The default parameterentitysetname is the entitysetname of the entity concatenated with the suffix "Parameters".
   * Here is a working example:
   * <pre>
   * "sap.test.odata.db.views::COUNT_VIEW" as "Count"
   * 		keys generate local "ID"
   * 		parameters via entity "CountParameters" results property "Results";
   * 	</pre>
   */
  private void applyOmittedParamResultCondition(XSKODataModel odataModel) {
    for (XSKHDBXSODATAEntity entity : odataModel.getService().getEntities()) {
      if (entity.getParameterType() != null) {
        XSKHDBXSODATAParameter defaultParam = new XSKHDBXSODATAParameter();
        if (entity.getParameterEntitySet() != null && entity.getParameterEntitySet().getParameterEntitySetName() == null) {
          entity.setParameterEntitySet(defaultParam.setParameterEntitySetName(entity.getAlias() + "Parameters"));
        }
        if (entity.getParameterEntitySet() != null && entity.getParameterEntitySet().getParameterResultsProperty() == null) {
          entity.setParameterEntitySet(defaultParam.setParameterResultsProperty("Results"));
        }
      }
    }
  }

  private boolean checkIfEntityIsOfSynonymType(String artifactName) throws SQLException {
    return checkIfEntityIsFromAGivenDBType(artifactName, METADATA_SYNONYM_TYPES);
  }

  private boolean checkIfEntityIsOfViewType(String artifactName) throws SQLException {
    return checkIfEntityIsFromAGivenDBType(artifactName, METADATA_VIEW_TYPES);
  }

  private boolean checkIfEntityIsOfCalcAndAnalyticViewType(String artifactName) throws SQLException {
    return checkIfEntityIsFromAGivenDBType(artifactName, METADATA_CALC_ANALYTIC_TYPES);
  }

  private boolean checkIfEntityIsFromAGivenDBType(String artifactName, List<String> dbTypes) throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet rs = databaseMetaData.getTables(connection.getCatalog(), null, artifactName, dbTypes.toArray(String[]::new));
      return rs.next();
    }
  }

  private boolean checkIfEntityExist(String artifactName) throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      DatabaseMetaData databaseMetaData = connection.getMetaData();
      ResultSet rs = databaseMetaData.getTables(null, null, artifactName, null);
      return rs.next();
    }
  }

  private String getCorrectCatalogObjectName(XSKHDBXSODATAEntity entity) throws SQLException {
    HashMap<String, String> targetObjectMetadata = new HashMap<String, String>();
    String catalogObjectName;

    if (checkIfEntityIsOfSynonymType(entity.getRepositoryObject().getCatalogObjectName())) {
      targetObjectMetadata = dbMetadataUtil.getSynonymTargetObjectMetadata(entity.getRepositoryObject().getCatalogObjectName());
    }

    if (targetObjectMetadata.isEmpty()) {
      catalogObjectName = entity.getRepositoryObject().getCatalogObjectName();
    }
    else {
      catalogObjectName = targetObjectMetadata.get(ISqlKeywords.KEYWORD_TABLE);
    }

    return catalogObjectName;
  }
}