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
package com.sap.xsk.utils;

import static com.sap.xsk.utils.XSKCommonsConstants.HDB_PROCEDURE_PARSER;
import static com.sap.xsk.utils.XSKCommonsConstants.MODULE_PARSERS;
import static com.sap.xsk.utils.XSKCommonsConstants.PARSER_ERROR;
import static com.sap.xsk.utils.XSKCommonsConstants.PROGRAM_XSK;
import static com.sap.xsk.utils.XSKCommonsConstants.SOURCE_PUBLISH_REQUEST;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKHDBSYNONYMDefinitionModel;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;

public class XSKHDBUtils {

  private static final String commentRegex = "(/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/)|(--.*)";
  private static final String ESCAPE_SYMBOL = "\"";

  private XSKHDBUtils() {
  }

  public static String getTableName(XSKDataStructureEntityModel model) {
    return getTableName(model, model.getName());
  }

  public static String getTableName(XSKDataStructureEntityModel model, String tableName) {
    return new StringBuilder()
        .append(model.getNamespace()).append("::").append(model.getContext()).append(".").append(tableName)
        .toString();
  }

  /**
   * Escape artifact name if DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE is activated
   *
   * @param artifactName name of the artifact
   * @param schemaName   name of the schema that will be assembled to the artifact name
   * @return escaped in quotes artifact name
   */
  public static String escapeArtifactName(String artifactName, String schemaName) {
    boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true"));
    if (!artifactName.startsWith(ESCAPE_SYMBOL)) {
      if (caseSensitive) {
        artifactName = ESCAPE_SYMBOL + artifactName + ESCAPE_SYMBOL;
      }
    }

    if (schemaName != null && !schemaName.trim().isEmpty()) {
      if (!schemaName.startsWith(ESCAPE_SYMBOL)) {
        if (caseSensitive) {
          schemaName = ESCAPE_SYMBOL + schemaName + ESCAPE_SYMBOL + ".";
        } else {
          schemaName = schemaName + ".";
        }
      }
    } else {
      schemaName = "";
    }

    return schemaName + artifactName;
  }

  /**
   * See also {@link #escapeArtifactName(String, String)}.
   */
  public static String escapeArtifactName(String artifactName) {
    return escapeArtifactName(artifactName, null);
  }

  public static void populateXSKDataStructureModel(String location, String content, XSKDataStructureModel model, String artifactType,
      XSKDBContentType DbContentType) {
    model.setName(XSKCommonsUtils.getRepositoryBaseObjectName(location));
    model.setLocation(location);
    model.setType(artifactType);
    model.setHash(DigestUtils.md5Hex(content));
    model.setCreatedBy(UserFacade.getName());
    model.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    model.setDbContentType(DbContentType);
  }

  public static void createPublicSynonymForArtifact(IXSKDataStructureManager<XSKDataStructureModel> xskSynonymManagerService,
      String artifactName, String artifactSchema, Connection connection)
      throws SQLException {
    xskSynonymManagerService.createDataStructure(connection, assemblePublicSynonym(artifactName, artifactSchema));
  }

  public static void dropPublicSynonymForArtifact(IXSKDataStructureManager<XSKDataStructureModel> xskSynonymManagerService,
      String artifactName, String artifactSchema, Connection connection)
      throws SQLException {
    xskSynonymManagerService.dropDataStructure(connection, assemblePublicSynonym(artifactName, artifactSchema));
  }

  public static XSKDataStructureHDBSynonymModel assemblePublicSynonym(String artifactName, String artifactSchema) {
    XSKDataStructureHDBSynonymModel model = new XSKDataStructureHDBSynonymModel();

    XSKHDBSYNONYMDefinitionModel defModel = new XSKHDBSYNONYMDefinitionModel();
    defModel.setSynonymSchema(XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA);
    XSKHDBSYNONYMDefinitionModel.Target target = new XSKHDBSYNONYMDefinitionModel.Target();
    target.setObject(artifactName);
    target.setSchema(artifactSchema);
    defModel.setTarget(target);

    model.getSynonymDefinitions().put(artifactName, defModel);
    model.setType(IXSKDataStructureModel.TYPE_HDB_SYNONYM);
    model.setCreatedBy(UserFacade.getName());
    model.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    return model;
  }

  public static String extractProcedureNameFromContent(String content, String location) throws XSKDataStructuresException {
    content = removeSqlCommentsFromContent(content);
    int indexOfEndOfProcKeyword = content.toLowerCase().indexOf("procedure") + "procedure".length();
    int indexOfBracket = content.indexOf('(', indexOfEndOfProcKeyword);
    if (indexOfEndOfProcKeyword < 0 || indexOfBracket < 0) {
      String errorMessage = "HDB Procedure file not correct";
      XSKCommonsUtils.logCustomErrors(location, PARSER_ERROR, "", "", errorMessage, "", HDB_PROCEDURE_PARSER, MODULE_PARSERS,
          SOURCE_PUBLISH_REQUEST, PROGRAM_XSK);
      throw new XSKDataStructuresException(errorMessage);
    }
    String procedureName = content.substring(indexOfEndOfProcKeyword, indexOfBracket);
    return procedureName.replace("\\s", "").trim();
  }

  public static String extractTableFunctionNameFromContent(String content, String location, String parser)
      throws XSKDataStructuresException {
    content = removeSqlCommentsFromContent(content);
    int indexOfBracket = content.indexOf('(');
    int indexOfEndOfProcKeyword = content.toLowerCase().indexOf("function") + "function".length();
    if (indexOfBracket > -1 && indexOfEndOfProcKeyword > -1) {
      String procNameWithWhiteSymbols = content.substring(indexOfEndOfProcKeyword, indexOfBracket);
      return procNameWithWhiteSymbols.replace("\\s", "").trim();
    }
    String errMsg = "HDB Table Function file not correct";
    XSKCommonsUtils.logCustomErrors(location, XSKCommonsConstants.PARSER_ERROR, "", "", errMsg,
        "", parser, XSKCommonsConstants.MODULE_PARSERS,
        XSKCommonsConstants.SOURCE_PUBLISH_REQUEST, XSKCommonsConstants.PROGRAM_XSK);
    throw new XSKDataStructuresException(errMsg);
  }

  public static String removeSqlCommentsFromContent(String content) {
    return content.replaceAll(commentRegex, "").trim();
  }
}
