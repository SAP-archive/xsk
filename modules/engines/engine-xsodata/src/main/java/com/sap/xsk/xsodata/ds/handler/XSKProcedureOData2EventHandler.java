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
import com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.info.DeleteUriInfo;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.apache.olingo.odata2.api.uri.info.PutMergePatchUriInfo;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.engine.odata2.definition.ODataHandlerDefinition;
import org.eclipse.dirigible.engine.odata2.handler.ScriptingOData2EventHandler;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLContext;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLDeleteBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLInsertBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLSelectBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLUpdateBuilder;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;

import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.BEFORE_DELETE_ENTITY_TABLE_NAME;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.BEFORE_UPDATE_ENTITY_TABLE_NAME;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.DUMMY_BUILDER;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.ENTRY_MAP;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.ERROR_DOCUMENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.HANDLER;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.INSERT_BUILDER;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.ON_CREATE_ENTITY_TABLE_NAME;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.SELECT_BUILDER;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.SQL_CONTEXT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_AFTER_CREATE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_AFTER_DELETE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_AFTER_UPDATE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_BEFORE_DELETE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_BEFORE_UPDATE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_ON_CREATE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_ON_DELETE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_ON_UPDATE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UPDATE_BUILDER;

public class XSKProcedureOData2EventHandler extends ScriptingOData2EventHandler {

  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  @Override
  public void beforeCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLInsertBuilder dummyBuilder = (SQLInsertBuilder) context.get(DUMMY_BUILDER);
    SQLInsertBuilder insertBuilder = (SQLInsertBuilder) context.get(INSERT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER);

    Connection connection = null;
    String newTableParam = null;
    try {
      connection = dataSource.getConnection();

      String targetTableName = XSKOData2EventHandlerUtils.getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      newTableParam = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());

      XSKOData2EventHandlerUtils.createTemporaryTableLikeTable(connection, newTableParam, targetTableName);
      XSKOData2EventHandlerUtils.insertIntoTemporaryTable(connection, insertBuilder, newTableParam, sqlContext);

      String schema = getODataArtifactTypeSchema(targetTableName);
      ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), newTableParam);
      context.put(ERROR_DOCUMENT, XSKOData2EventHandlerUtils.createErrorDocument(procedureCallResultSet));
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_ON_CREATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connection);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables(newTableParam);
    }
  }

  @Override
  public void afterCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLInsertBuilder dummyBuilder = (SQLInsertBuilder) context.get(DUMMY_BUILDER);
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER);

    Connection connection = null;
    String newTableParam = null;
    try {
      connection = dataSource.getConnection();
      if (context.containsKey(ON_CREATE_ENTITY_TABLE_NAME)) {
        newTableParam = (String) context.get(ON_CREATE_ENTITY_TABLE_NAME);
      } else {
        newTableParam = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
        XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connection, newTableParam, selectBuilder, sqlContext);
      }

      String targetTableName = XSKOData2EventHandlerUtils.getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), newTableParam);
      context.put(ERROR_DOCUMENT, XSKOData2EventHandlerUtils.createErrorDocument(procedureCallResultSet));
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_CREATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connection);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables(newTableParam);
    }
  }

  @Override
  public ODataResponse onCreateEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType,
      Map<Object, Object> context) {
    SQLInsertBuilder dummyBuilder = (SQLInsertBuilder) context.get(DUMMY_BUILDER);
    SQLInsertBuilder insertBuilder = (SQLInsertBuilder) context.get(INSERT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER);

    Connection connection = null;
    try {
      connection = dataSource.getConnection();

      String targetTableName = XSKOData2EventHandlerUtils.getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String newTableParam = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());

      XSKOData2EventHandlerUtils.createTemporaryTableLikeTable(connection, newTableParam, targetTableName);
      XSKOData2EventHandlerUtils.insertIntoTemporaryTable(connection, insertBuilder, newTableParam, sqlContext);

      context.put(ON_CREATE_ENTITY_TABLE_NAME, newTableParam);

      String schema = getODataArtifactTypeSchema(targetTableName);
      ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), newTableParam);
      context.put(ERROR_DOCUMENT, XSKOData2EventHandlerUtils.createErrorDocument(procedureCallResultSet));

      context.put(ENTRY_MAP, XSKOData2EventHandlerUtils.readEntryMap(connection, newTableParam));
      return null;
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_ON_CREATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connection);
    }
  }

  @Override
  public void beforeUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLUpdateBuilder dummyBuilder = (SQLUpdateBuilder) context.get(DUMMY_BUILDER);
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLUpdateBuilder updateBuilder = (SQLUpdateBuilder) context.get(UPDATE_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER);

    Connection connection = null;
    String oldTableParam = null;
    String newTableParam = null;
    try {
      connection = dataSource.getConnection();

      oldTableParam = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      newTableParam = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      String beforeUpdateOldTableParam = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());

      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connection, oldTableParam, selectBuilder, sqlContext);
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connection, newTableParam, selectBuilder, sqlContext);
      XSKOData2EventHandlerUtils.updateTemporaryTable(connection, updateBuilder, newTableParam, sqlContext);
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connection, beforeUpdateOldTableParam, selectBuilder, sqlContext);

      context.put(BEFORE_UPDATE_ENTITY_TABLE_NAME, beforeUpdateOldTableParam);

      String targetTableName = XSKOData2EventHandlerUtils.getSQLUpdateBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam, newTableParam);
      context.put(ERROR_DOCUMENT, XSKOData2EventHandlerUtils.createErrorDocument(procedureCallResultSet));
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_UPDATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connection);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables(oldTableParam, newTableParam);
    }
  }

  @Override
  public void afterUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLUpdateBuilder dummyBuilder = (SQLUpdateBuilder) context.get(DUMMY_BUILDER);
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER);

    Connection connection = null;
    String oldTableParam = null;
    String newTableParam = null;
    try {
      connection = dataSource.getConnection();

      oldTableParam = (String) context.get(BEFORE_UPDATE_ENTITY_TABLE_NAME);
      newTableParam = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connection, newTableParam, selectBuilder, sqlContext);

      String targetTableName = XSKOData2EventHandlerUtils.getSQLUpdateBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam, newTableParam);
      context.put(ERROR_DOCUMENT, XSKOData2EventHandlerUtils.createErrorDocument(procedureCallResultSet));
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_UPDATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connection);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables(oldTableParam, newTableParam);
    }
  }

  @Override
  public ODataResponse onUpdateEntity(PutMergePatchUriInfo uriInfo, InputStream content, String requestContentType, boolean merge,
      String contentType, Map<Object, Object> context) {
    SQLUpdateBuilder dummyBuilder = (SQLUpdateBuilder) context.get(DUMMY_BUILDER);
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLUpdateBuilder updateBuilder = (SQLUpdateBuilder) context.get(UPDATE_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER);

    Connection connection = null;
    String oldTableParam = null;
    String newTableParam = null;
    try {
      connection = dataSource.getConnection();

      oldTableParam = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      newTableParam = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());

      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connection, oldTableParam, selectBuilder, sqlContext);
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connection, newTableParam, selectBuilder, sqlContext);
      XSKOData2EventHandlerUtils.updateTemporaryTable(connection, updateBuilder, newTableParam, sqlContext);

      String targetTableName = XSKOData2EventHandlerUtils.getSQLUpdateBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam, newTableParam);
      context.put(ERROR_DOCUMENT, XSKOData2EventHandlerUtils.createErrorDocument(procedureCallResultSet));

      return null;
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_ON_UPDATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connection);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables(oldTableParam, newTableParam);
    }
  }

  @Override
  public void beforeDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLDeleteBuilder dummyBuilder = (SQLDeleteBuilder) context.get(DUMMY_BUILDER);
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER);

    Connection connection = null;
    String oldTableParam = null;
    try {
      connection = dataSource.getConnection();

      oldTableParam = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      String beforeDeleteEntityTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());

      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connection, oldTableParam, selectBuilder, sqlContext);
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connection, beforeDeleteEntityTableName, selectBuilder, sqlContext);

      context.put(BEFORE_DELETE_ENTITY_TABLE_NAME, beforeDeleteEntityTableName);

      String targetTableName = XSKOData2EventHandlerUtils.getSQLDeleteBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam);
      context.put(ERROR_DOCUMENT, XSKOData2EventHandlerUtils.createErrorDocument(procedureCallResultSet));
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_DELETE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connection);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables(oldTableParam);
    }
  }

  @Override
  public void afterDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLDeleteBuilder dummyBuilder = (SQLDeleteBuilder) context.get(DUMMY_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER);

    Connection connection = null;
    String oldTableParam = null;
    try {
      connection = dataSource.getConnection();
      oldTableParam = (String) context.get(BEFORE_DELETE_ENTITY_TABLE_NAME);

      String targetTableName = XSKOData2EventHandlerUtils.getSQLDeleteBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam);
      context.put(ERROR_DOCUMENT, XSKOData2EventHandlerUtils.createErrorDocument(procedureCallResultSet));
    } catch (SQLException | ODataException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_DELETE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connection);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables(oldTableParam);
    }
  }

  @Override
  public ODataResponse onDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLDeleteBuilder dummyBuilder = (SQLDeleteBuilder) context.get(DUMMY_BUILDER);
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    ODataHandlerDefinition handler = (ODataHandlerDefinition) context.get(HANDLER);

    Connection connection = null;
    String oldTableParam = null;
    try {
      connection = dataSource.getConnection();

      oldTableParam = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connection, oldTableParam, selectBuilder, sqlContext);

      String targetTableName = XSKOData2EventHandlerUtils.getSQLDeleteBuilderTargetTable(dummyBuilder, sqlContext);
      String schema = getODataArtifactTypeSchema(targetTableName);
      ResultSet procedureCallResultSet = callProcedure(connection, schema, handler.getHandler(), oldTableParam);
      context.put(ERROR_DOCUMENT, XSKOData2EventHandlerUtils.createErrorDocument(procedureCallResultSet));

      return null;
    } catch (ODataException | SQLException e) {
      throw new XSKProcedureOData2EventHandlerException(UNABLE_TO_HANDLE_ON_DELETE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connection);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables(oldTableParam);
    }
  }

  private ResultSet callProcedure(Connection connection, String schema, String procedureName, String newTableParam) throws SQLException {
    PreparedStatement statement = XSKOData2EventHandlerUtils.prepareStatement(connection,
        String.format("CALL \"%s\".\"%s\" (\"%s\", ?)", schema, procedureName, newTableParam));
    return statement.executeQuery();
  }

  private ResultSet callProcedure(Connection connection, String schema, String procedureName, String oldTableParam, String newTableParam)
      throws SQLException {
    PreparedStatement statement = XSKOData2EventHandlerUtils.prepareStatement(connection,
        String.format("CALL \"%s\".\"%s\" (\"%s\", \"%s\", ?)", schema, procedureName, newTableParam, oldTableParam));
    return statement.executeQuery();
  }

  private String getODataArtifactTypeSchema(String tableName) throws SQLException {
    String normalizedTableName = DBMetadataUtil.normalizeTableName(tableName);
    XSKTableMetadataProvider tableMetadataProvider = new XSKTableMetadataProvider();
    PersistenceTableModel persistenceTableModel = tableMetadataProvider.getPersistenceTableModel(normalizedTableName);
    return persistenceTableModel.getSchemaName();
  }
}
