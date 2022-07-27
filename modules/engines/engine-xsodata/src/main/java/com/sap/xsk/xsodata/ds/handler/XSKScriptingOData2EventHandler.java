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
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLContext;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLDeleteBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLInsertBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLQueryBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLSelectBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLUpdateBuilder;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class XSKScriptingOData2EventHandler extends AbstractXSKOData2EventHandler {

  @Override
  public ODataResponse beforeCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);

    Connection connectionParam = null;
    try {
      SQLInsertBuilder dummyBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);
      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);

      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connectionParam = dataSource.getConnection();
      createTemporaryTableLikeTable(connectionParam, afterTableName, targetTableName);
      insertIntoTemporaryTable(connectionParam, insertBuilder, afterTableName, sqlContext);

      context.put(CONNECTION_CONTEXT_KEY, connectionParam);
      context.put(AFTER_TABLE_NAME_CONTEXT_KEY, afterTableName);

      callSuperBeforeCreateEntity(uriInfo, requestContentType, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_CREATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(AFTER_TABLE_NAME_CONTEXT_KEY));
      closeConnection(connectionParam);
    }

    return null;
  }

  @Override
  public ODataResponse afterCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);

    Connection connectionParam = null;
    try {
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);

      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connectionParam = dataSource.getConnection();
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      context.put(CONNECTION_CONTEXT_KEY, connectionParam);
      context.put(AFTER_TABLE_NAME_CONTEXT_KEY, afterTableName);

      callSuperAfterCreateEntity(uriInfo, requestContentType, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_CREATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(AFTER_TABLE_NAME_CONTEXT_KEY));
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse onCreateEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType,
      Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    ODataEntry entry = (ODataEntry) context.get(ENTRY_CONTEXT_KEY);

    Connection connectionParam = null;
    try {
      SQLInsertBuilder dummyBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);
      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);

      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connectionParam = dataSource.getConnection();
      createTemporaryTableLikeTable(connectionParam, afterTableName, targetTableName);
      insertIntoTemporaryTable(connectionParam, insertBuilder, afterTableName, sqlContext);

      context.put(CONNECTION_CONTEXT_KEY, connectionParam);
      context.put(AFTER_TABLE_NAME_CONTEXT_KEY, afterTableName);

      callSuperOnCreateEntity(uriInfo, content, requestContentType, contentType, context);

      Map<String, Object> entryMap = readEntryMap(connectionParam, afterTableName);
      return buildResponse((UriInfo) uriInfo, oDataContext, entryMap, contentType, HttpStatusCodes.CREATED);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_ON_CREATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(AFTER_TABLE_NAME_CONTEXT_KEY));
      closeConnection(connectionParam);
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

    Connection connectionParam = null;
    try {
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);
      SQLUpdateBuilder updateBuilder = queryBuilder.buildUpdateEntityQuery((UriInfo) uriInfo, entry, mappedKeys, oDataContext);

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connectionParam = dataSource.getConnection();
      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);
      updateTemporaryTable(connectionParam, updateBuilder, afterTableName, sqlContext);

      context.put(CONNECTION_CONTEXT_KEY, connectionParam);
      context.put(BEFORE_TABLE_NAME_CONTEXT_KEY, beforeTableName);
      context.put(AFTER_TABLE_NAME_CONTEXT_KEY, afterTableName);
      String beforeUpdateEntryJSON = GsonHelper.GSON.toJson(readEntryMap(connectionParam, beforeTableName));
      context.put(ENTRY_JSON_CONTEXT_KEY, beforeUpdateEntryJSON);

      callSuperBeforeUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_UPDATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME_CONTEXT_KEY), (String) context.get(
          AFTER_TABLE_NAME_CONTEXT_KEY));
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse afterUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    ODataEntry entryBeforeUpdate = (ODataEntry) context.get(ENTRY_CONTEXT_KEY);
    Map<String, Object> mappedKeys = (Map<String, Object>) context.get(MAPPED_KEYS_CONTEXT_KEY);

    Connection connectionParam = null;
    try {
      SQLUpdateBuilder dummyBuilder = queryBuilder.buildUpdateEntityQuery((UriInfo) uriInfo, entry,
          mappedKeys, oDataContext);
      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entryBeforeUpdate, oDataContext);
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connectionParam = dataSource.getConnection();
      String targetTableName = getSQLUpdateBuilderTargetTable(dummyBuilder, sqlContext);
      createTemporaryTableLikeTable(connectionParam, beforeTableName, targetTableName);
      insertIntoTemporaryTable(connectionParam, insertBuilder, beforeTableName, sqlContext);
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      context.put(CONNECTION_CONTEXT_KEY, connectionParam);
      context.put(BEFORE_TABLE_NAME_CONTEXT_KEY, beforeTableName);
      context.put(AFTER_TABLE_NAME_CONTEXT_KEY, afterTableName);

      callSuperAfterUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_UPDATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME_CONTEXT_KEY), (String) context.get(
          AFTER_TABLE_NAME_CONTEXT_KEY));
      closeConnection(connectionParam);
    }
    return null;
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

    Connection connectionParam = null;
    try {
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);
      SQLUpdateBuilder updateBuilder = queryBuilder.buildUpdateEntityQuery((UriInfo) uriInfo, entry, mappedKeys, oDataContext);

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connectionParam = dataSource.getConnection();
      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);
      updateTemporaryTable(connectionParam, updateBuilder, afterTableName, sqlContext);

      context.put(CONNECTION_CONTEXT_KEY, connectionParam);
      context.put(BEFORE_TABLE_NAME_CONTEXT_KEY, beforeTableName);
      context.put(AFTER_TABLE_NAME_CONTEXT_KEY, afterTableName);

      callSuperOnUpdateEntity(uriInfo, content, requestContentType, merge, contentType, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_ON_UPDATE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME_CONTEXT_KEY), (String) context.get(
          AFTER_TABLE_NAME_CONTEXT_KEY));
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse beforeDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);

    Connection connectionParam = null;
    try {
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connectionParam = dataSource.getConnection();
      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);

      context.put(CONNECTION_CONTEXT_KEY, connectionParam);
      context.put(BEFORE_TABLE_NAME_CONTEXT_KEY, beforeTableName);
      String beforeDeleteEntryJSON = GsonHelper.GSON.toJson(readEntryMap(connectionParam, beforeTableName));
      context.put(ENTRY_JSON_CONTEXT_KEY, beforeDeleteEntryJSON);

      callSuperBeforeDeleteEntity(uriInfo, contentType, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_DELETE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME_CONTEXT_KEY));
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse afterDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);
    Map<String, Object> mappedKeys = (Map<String, Object>) context.get(MAPPED_KEYS_CONTEXT_KEY);
    ODataEntry entryBeforeDelete = (ODataEntry) context.get(ENTRY_CONTEXT_KEY);

    Connection connectionParam = null;
    try {
      SQLDeleteBuilder dummyBuilder = queryBuilder.buildDeleteEntityQuery((UriInfo) uriInfo, mappedKeys, oDataContext);
      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entryBeforeDelete, oDataContext);

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String targetTableName = getSQLDeleteBuilderTargetTable(dummyBuilder, sqlContext);

      connectionParam = dataSource.getConnection();
      createTemporaryTableLikeTable(connectionParam, beforeTableName, targetTableName);
      insertIntoTemporaryTable(connectionParam, insertBuilder, beforeTableName, sqlContext);

      context.put(CONNECTION_CONTEXT_KEY, connectionParam);
      context.put(BEFORE_TABLE_NAME_CONTEXT_KEY, beforeTableName);

      callSuperAfterDeleteEntity(uriInfo, contentType, context);
    } catch (org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException | ODataException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_DELETE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME_CONTEXT_KEY));
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse onDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER_CONTEXT_KEY);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT_CONTEXT_KEY);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT_CONTEXT_KEY);
    DataSource dataSource = (DataSource) context.get(DATASOURCE_CONTEXT_KEY);

    Connection connectionParam = null;
    try {
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connectionParam = dataSource.getConnection();
      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);

      context.put(CONNECTION_CONTEXT_KEY, connectionParam);
      context.put(BEFORE_TABLE_NAME_CONTEXT_KEY, beforeTableName);

      callSuperOnDeleteEntity(uriInfo, contentType, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_ON_DELETE_ENTITY_EVENT_ERROR, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME_CONTEXT_KEY));
      closeConnection(connectionParam);
    }
    return null;
  }

  void callSuperBeforeCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) throws org.eclipse.dirigible.engine.odata2.api.ODataException {
    super.beforeCreateEntity(uriInfo, requestContentType, contentType, entry, context);
  }

  void callSuperAfterCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) throws org.eclipse.dirigible.engine.odata2.api.ODataException {
    super.afterCreateEntity(uriInfo, requestContentType, contentType, entry, context);
  }

  void callSuperOnCreateEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType,
      Map<Object, Object> context) throws org.eclipse.dirigible.engine.odata2.api.ODataException {
    super.onCreateEntity(uriInfo, content, requestContentType, contentType, context);
  }

  void callSuperBeforeUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) throws org.eclipse.dirigible.engine.odata2.api.ODataException {
    super.beforeUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
  }

  void callSuperAfterUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) throws org.eclipse.dirigible.engine.odata2.api.ODataException {
    super.afterUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
  }

  void callSuperOnUpdateEntity(PutMergePatchUriInfo uriInfo, InputStream content, String requestContentType, boolean merge,
      String contentType, Map<Object, Object> context) throws org.eclipse.dirigible.engine.odata2.api.ODataException {
    super.onUpdateEntity(uriInfo, content, requestContentType, merge, contentType, context);
  }

  void callSuperBeforeDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context)
      throws org.eclipse.dirigible.engine.odata2.api.ODataException {
    super.beforeDeleteEntity(uriInfo, contentType, context);
  }

  void callSuperAfterDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context)
      throws org.eclipse.dirigible.engine.odata2.api.ODataException {
    super.afterDeleteEntity(uriInfo, contentType, context);
  }

  void callSuperOnDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context)
      throws org.eclipse.dirigible.engine.odata2.api.ODataException {
    super.onDeleteEntity(uriInfo, contentType, context);
  }
}
