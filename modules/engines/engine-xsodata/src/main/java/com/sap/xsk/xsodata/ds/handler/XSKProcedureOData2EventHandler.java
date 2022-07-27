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
package com.sap.xsk.xsodata.ds.handler;

import com.sap.xsk.xsodata.ds.service.XSKTableMetadataProvider;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.UriInfo;
import org.apache.olingo.odata2.api.uri.info.DeleteUriInfo;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.apache.olingo.odata2.api.uri.info.PutMergePatchUriInfo;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.engine.odata2.definition.ODataHandlerDefinition;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLContext;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLDeleteBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLInsertBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLQueryBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLSelectBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLUpdateBuilder;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;

public class XSKProcedureOData2EventHandler extends AbstractXSKOData2EventHandler {

  @Override
  public ODataResponse beforeCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER_CONTEXT_KEY);

    String newTableParam = null;
    Connection connection = null;
    try {
      SQLInsertBuilder dummyBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);
      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);

      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      newTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connection = dataSource.getConnection();
      createTemporaryTableLikeTable(connection, newTableParam, targetTableName);
      insertIntoTemporaryTable(connection, insertBuilder, newTableParam, sqlContext);

      String schema = getODataArtifactTypeSchema(targetTableName);
      try(ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), newTableParam)) {
        return createODataErrorResponse(procedureCallResultSet);
      }
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_ON_CREATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connection, newTableParam);
      closeConnection(connection);
    }
  }

  @Override
  public ODataResponse afterCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER_CONTEXT_KEY);

    Connection connection = null;
    String newTableParam = null;
    try {
      SQLInsertBuilder dummyBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);

      newTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connection = dataSource.getConnection();
      createTemporaryTableAsSelect(connection, newTableParam, selectBuilder, sqlContext);

      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      try(ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), newTableParam)) {
        return createODataErrorResponse(procedureCallResultSet);
      }
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_CREATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connection, newTableParam);
      closeConnection(connection);
    }
  }

  @Override
  public ODataResponse onCreateEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType,
      Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    ODataEntry entry = (ODataEntry) context.get(ENTRY_CONTEXT_KEY);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER_CONTEXT_KEY);

    Connection connection = null;
    String newTableParam = null;
    try {
      SQLInsertBuilder dummyBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);
      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);
      connection = dataSource.getConnection();

      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      newTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());

      createTemporaryTableLikeTable(connection, newTableParam, targetTableName);
      insertIntoTemporaryTable(connection, insertBuilder, newTableParam, sqlContext);

      Map<String, Object> entryMap = readEntryMap(connection, newTableParam);

      String schema = getODataArtifactTypeSchema(targetTableName);
      try (ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), newTableParam)) {
        return createODataResponse(procedureCallResultSet, (UriInfo) uriInfo, oDataContext, entryMap, contentType,
            HttpStatusCodes.CREATED);
      }
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_ON_CREATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connection, newTableParam);
      closeConnection(connection);
    }
  }

  @Override
  public ODataResponse beforeUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    Map<String, Object> mappedKeys = (Map<String, Object>) context.get(MAPPED_KEYS_CONTEXT_KEY);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER_CONTEXT_KEY);

    Connection connection = null;
    String oldTableParam = null;
    String newTableParam = null;
    try {
      SQLUpdateBuilder dummyBuilder = queryBuilder.buildUpdateEntityQuery((UriInfo) uriInfo, entry, mappedKeys, oDataContext);
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);
      SQLUpdateBuilder updateBuilder = queryBuilder.buildUpdateEntityQuery((UriInfo) uriInfo, entry, mappedKeys, oDataContext);

      oldTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());
      newTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String beforeUpdateOldTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connection = dataSource.getConnection();
      createTemporaryTableAsSelect(connection, oldTableParam, selectBuilder, sqlContext);
      createTemporaryTableAsSelect(connection, newTableParam, selectBuilder, sqlContext);
      updateTemporaryTable(connection, updateBuilder, newTableParam, sqlContext);
      createTemporaryTableAsSelect(connection, beforeUpdateOldTableParam, selectBuilder, sqlContext);

      String beforeUpdateEntryJSON = GsonHelper.GSON.toJson(readEntryMap(connection, oldTableParam));
      context.put(ENTRY_JSON_CONTEXT_KEY, beforeUpdateEntryJSON);

      String targetTableName = getSQLUpdateBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      try (ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam, newTableParam)) {
        return createODataErrorResponse(procedureCallResultSet);
      }
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_UPDATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connection, oldTableParam, newTableParam);
      closeConnection(connection);
    }
  }

  @Override
  public ODataResponse afterUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    Map<String, Object> mappedKeys = (Map<String, Object>) context.get(MAPPED_KEYS_CONTEXT_KEY);
    ODataEntry entryBeforeUpdate = (ODataEntry) context.get(ENTRY_CONTEXT_KEY);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER_CONTEXT_KEY);

    Connection connection = null;
    String oldTableParam = null;
    String newTableParam = null;
    try {
      SQLUpdateBuilder dummyBuilder = queryBuilder.buildUpdateEntityQuery((UriInfo) uriInfo, entry, mappedKeys, oDataContext);
      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entryBeforeUpdate, oDataContext);
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);

      oldTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());
      newTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connection = dataSource.getConnection();
      String targetTableName = getSQLUpdateBuilderTargetTable(dummyBuilder, sqlContext);
      createTemporaryTableLikeTable(connection, oldTableParam, targetTableName);
      insertIntoTemporaryTable(connection, insertBuilder, oldTableParam, sqlContext);
      createTemporaryTableAsSelect(connection, newTableParam, selectBuilder, sqlContext);

      String beforeUpdateEntryJSON = GsonHelper.GSON.toJson(readEntryMap(connection, oldTableParam));
      context.put(ENTRY_JSON_CONTEXT_KEY, beforeUpdateEntryJSON);

      String schema = getODataArtifactTypeSchema(targetTableName);
      try (ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam, newTableParam)) {
        return createODataErrorResponse(procedureCallResultSet);
      }
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_UPDATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connection, oldTableParam, newTableParam);
      closeConnection(connection);
    }
  }

  @Override
  public ODataResponse onUpdateEntity(PutMergePatchUriInfo uriInfo, InputStream content, String requestContentType, boolean merge,
      String contentType, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    Map<String, Object> mappedKeys = (Map<String, Object>) context.get(MAPPED_KEYS_CONTEXT_KEY);
    ODataEntry entry = (ODataEntry) context.get(ENTRY_CONTEXT_KEY);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER_CONTEXT_KEY);

    Connection connection = null;
    String oldTableParam = null;
    String newTableParam = null;
    try {
      SQLUpdateBuilder dummyBuilder = queryBuilder.buildUpdateEntityQuery((UriInfo) uriInfo, entry, mappedKeys, oDataContext);
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);
      SQLUpdateBuilder updateBuilder = queryBuilder.buildUpdateEntityQuery((UriInfo) uriInfo, entry, mappedKeys, oDataContext);

      oldTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());
      newTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connection = dataSource.getConnection();
      createTemporaryTableAsSelect(connection, oldTableParam, selectBuilder, sqlContext);
      createTemporaryTableAsSelect(connection, newTableParam, selectBuilder, sqlContext);
      updateTemporaryTable(connection, updateBuilder, newTableParam, sqlContext);

      String targetTableName = getSQLUpdateBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      try (ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam, newTableParam)) {
        return createODataErrorResponse(procedureCallResultSet);
      }
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_ON_UPDATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connection, oldTableParam, newTableParam);
      closeConnection(connection);
    }
  }

  @Override
  public ODataResponse beforeDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    Map<String, Object> mappedKeys = (Map<String, Object>) context.get(MAPPED_KEYS_CONTEXT_KEY);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER_CONTEXT_KEY);

    Connection connection = null;
    String oldTableParam = null;
    try {
      SQLDeleteBuilder dummyBuilder = queryBuilder.buildDeleteEntityQuery((UriInfo) uriInfo, mappedKeys, oDataContext);
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);

      oldTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connection = dataSource.getConnection();
      createTemporaryTableAsSelect(connection, oldTableParam, selectBuilder, sqlContext);

      String beforeDeleteEntryJSON = GsonHelper.GSON.toJson(readEntryMap(connection, oldTableParam));
      context.put(ENTRY_JSON_CONTEXT_KEY, beforeDeleteEntryJSON);

      String targetTableName = getSQLDeleteBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      try (ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam)) {
        return createODataErrorResponse(procedureCallResultSet);
      }
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_DELETE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connection, oldTableParam);
      closeConnection(connection);
    }
  }

  @Override
  public ODataResponse afterDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    Map<String, Object> mappedKeys = (Map<String, Object>) context.get(MAPPED_KEYS_CONTEXT_KEY);
    ODataEntry entryBeforeDelete = (ODataEntry) context.get(ENTRY_CONTEXT_KEY);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER_CONTEXT_KEY);

    Connection connection = null;
    String oldTableParam = null;
    try {
      SQLDeleteBuilder dummyBuilder = queryBuilder.buildDeleteEntityQuery((UriInfo) uriInfo, mappedKeys, oDataContext);
      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entryBeforeDelete, oDataContext);

      oldTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String targetTableName = getSQLDeleteBuilderTargetTable(dummyBuilder, sqlContext);

      connection = dataSource.getConnection();
      createTemporaryTableLikeTable(connection, oldTableParam, targetTableName);
      insertIntoTemporaryTable(connection, insertBuilder, oldTableParam, sqlContext);

      String schema = getODataArtifactTypeSchema(targetTableName);
      try (ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam)) {
        return createODataErrorResponse(procedureCallResultSet);
      }
    } catch (SQLException | ODataException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_DELETE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connection, oldTableParam);
      closeConnection(connection);
    }
  }

  @Override
  public ODataResponse onDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    Map<String, Object> mappedKeys = (Map<String, Object>) context.get(MAPPED_KEYS_CONTEXT_KEY);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER_CONTEXT_KEY);

    Connection connection = null;
    String oldTableParam = null;
    try {
      SQLDeleteBuilder dummyBuilder = queryBuilder.buildDeleteEntityQuery((UriInfo) uriInfo, mappedKeys, oDataContext);
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);

      oldTableParam = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connection = dataSource.getConnection();
      createTemporaryTableAsSelect(connection, oldTableParam, selectBuilder, sqlContext);

      String targetTableName = getSQLDeleteBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      try(ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam)) {
        return createODataErrorResponse(procedureCallResultSet);
      }
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_ON_DELETE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connection, oldTableParam);
      closeConnection(connection);
    }
  }

  protected ResultSet callProcedure(Connection connection, String schema, String procedureName, String newTableParam) throws SQLException {
    String callProcedureSQL = "CALL \"" + schema + "\".\"" + procedureName + "\" (\"" + newTableParam + "\", ?)";
    try(PreparedStatement statement = createPreparedStatement(connection, callProcedureSQL)) {
      return statement.executeQuery();
    }
  }

  protected ResultSet callProcedure(Connection connection, String schema, String procedureName, String oldTableParam, String newTableParam)
      throws SQLException {
    String callProcedureSQL = "CALL \"" + schema + "\".\"" + procedureName + "\" (\"" + newTableParam + "\", \"" + oldTableParam + "\", ?)";
    try(PreparedStatement statement = createPreparedStatement(connection, callProcedureSQL)) {
      return statement.executeQuery();
    }
  }

  protected String getODataArtifactTypeSchema(String tableName) throws SQLException {
    String normalizedTableName = DBMetadataUtil.normalizeTableName(tableName);
    XSKTableMetadataProvider tableMetadataProvider = new XSKTableMetadataProvider();
    PersistenceTableModel persistenceTableModel = tableMetadataProvider.getPersistenceTableModel(normalizedTableName);
    return persistenceTableModel.getSchemaName();
  }
}
