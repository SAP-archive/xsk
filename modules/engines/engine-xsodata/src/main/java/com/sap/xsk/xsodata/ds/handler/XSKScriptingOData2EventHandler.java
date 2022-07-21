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
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.UriInfo;
import org.apache.olingo.odata2.api.uri.info.DeleteUriInfo;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.apache.olingo.odata2.api.uri.info.PutMergePatchUriInfo;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLContext;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLDeleteBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLInsertBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLQueryBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLSelectBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLUpdateBuilder;
import org.eclipse.dirigible.engine.odata2.sql.processor.AbstractSQLProcessor;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class XSKScriptingOData2EventHandler extends AbstractXSKOData2EventHandler {

  @Override
  public ODataResponse beforeCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    DataSource dataSource = (DataSource) context.get(DATASOURCE);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      SQLInsertBuilder dummyBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);
      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);

      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      createTemporaryTableLikeTable(connectionParam, afterTableName, targetTableName);
      insertIntoTemporaryTable(connectionParam, insertBuilder, afterTableName, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(AFTER_TABLE_NAME, afterTableName);

      callSuperBeforeCreateEntity(uriInfo, requestContentType, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_CREATE_ENTITY_EVENT, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(AFTER_TABLE_NAME));
      closeConnection(connectionParam);
    }

    return null;
  }

  @Override
  public ODataResponse afterCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    DataSource dataSource = (DataSource) context.get(DATASOURCE);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);

      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(AFTER_TABLE_NAME, afterTableName);

      callSuperAfterCreateEntity(uriInfo, requestContentType, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_CREATE_ENTITY_EVENT, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(AFTER_TABLE_NAME));
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse onCreateEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType,
      Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    DataSource dataSource = (DataSource) context.get(DATASOURCE);
    EdmEntitySet entitySet = (EdmEntitySet) context.get(ENTITY_SET);

    Connection connectionParam = null;
    try {
      ODataEntry entry = AbstractSQLProcessor.parseEntry(entitySet, content, requestContentType, false);
      SQLInsertBuilder dummyBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);
      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);

      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connectionParam = dataSource.getConnection();
      createTemporaryTableLikeTable(connectionParam, afterTableName, targetTableName);
      insertIntoTemporaryTable(connectionParam, insertBuilder, afterTableName, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(AFTER_TABLE_NAME, afterTableName);

      callSuperOnCreateEntity(uriInfo, content, requestContentType, contentType, context);

      Map<String, Object> entryMap = readEntryMap(connectionParam, afterTableName);

      return buildResponse((UriInfo) uriInfo, oDataContext, entryMap, contentType, HttpStatusCodes.CREATED);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_ON_CREATE_ENTITY_EVENT, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(AFTER_TABLE_NAME));
      closeConnection(connectionParam);
    }
  }

  @Override
  public ODataResponse beforeUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    DataSource dataSource = (DataSource) context.get(DATASOURCE);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);
      SQLUpdateBuilder updateBuilder = queryBuilder.buildUpdateEntityQuery((UriInfo) uriInfo, entry,
          AbstractSQLProcessor.mapKeys(uriInfo.getKeyPredicates()), oDataContext);

      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);
      updateTemporaryTable(connectionParam, updateBuilder, afterTableName, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);
      context.put(AFTER_TABLE_NAME, afterTableName);
      String beforeUpdateEntryJSON = GsonHelper.GSON.toJson(readEntryMap(connectionParam, beforeTableName));
      context.put(BEFORE_UPDATE_ENTRY_JSON, beforeUpdateEntryJSON);

      callSuperBeforeUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_UPDATE_ENTITY_EVENT, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME), (String) context.get(AFTER_TABLE_NAME));
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse afterUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    DataSource dataSource = (DataSource) context.get(DATASOURCE);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      SQLUpdateBuilder dummyBuilder = queryBuilder.buildUpdateEntityQuery((UriInfo) uriInfo, entry,
          AbstractSQLProcessor.mapKeys(uriInfo.getKeyPredicates()), oDataContext);
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);
      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      String targetTableName = getSQLUpdateBuilderTargetTable(dummyBuilder, sqlContext);
      createTemporaryTableLikeTable(connectionParam, beforeTableName, targetTableName);
      insertIntoTemporaryTable(connectionParam, insertBuilder, beforeTableName, sqlContext);
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);
      context.put(AFTER_TABLE_NAME, afterTableName);

      callSuperAfterUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_UPDATE_ENTITY_EVENT, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME), (String) context.get(AFTER_TABLE_NAME));
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse onUpdateEntity(PutMergePatchUriInfo uriInfo, InputStream content, String requestContentType, boolean merge,
      String contentType, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    DataSource dataSource = (DataSource) context.get(DATASOURCE);
    EdmEntitySet entitySet = (EdmEntitySet) context.get(ENTITY_SET);


    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      ODataEntry entry = AbstractSQLProcessor.parseEntry(entitySet, content, requestContentType, false);
      SQLUpdateBuilder updateBuilder = queryBuilder.buildUpdateEntityQuery((UriInfo) uriInfo, entry,
          AbstractSQLProcessor.mapKeys(uriInfo.getKeyPredicates()), oDataContext);
      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);
      updateTemporaryTable(connectionParam, updateBuilder, afterTableName, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);
      context.put(AFTER_TABLE_NAME, afterTableName);

      callSuperOnUpdateEntity(uriInfo, content, requestContentType, merge, contentType, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_ON_UPDATE_ENTITY_EVENT, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME), (String) context.get(AFTER_TABLE_NAME));
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse beforeDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    DataSource dataSource = (DataSource) context.get(DATASOURCE);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      SQLSelectBuilder selectBuilder = queryBuilder.buildSelectEntityQuery((UriInfo) uriInfo, oDataContext);

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);
      String beforeDeleteEntryJSON = GsonHelper.GSON.toJson(readEntryMap(connectionParam, beforeTableName));
      context.put(BEFORE_DELETE_ENTRY_JSON, beforeDeleteEntryJSON);

      callSuperBeforeDeleteEntity(uriInfo, contentType, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_DELETE_ENTITY_EVENT, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME));
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse afterDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLQueryBuilder queryBuilder = (SQLQueryBuilder) context.get(SQL_BUILDER);
    ODataContext oDataContext = (ODataContext) context.get(ODATA_CONTEXT);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    DataSource dataSource = (DataSource) context.get(DATASOURCE);
    EdmEntitySet entitySet = (EdmEntitySet) context.get(ENTITY_SET);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      ODataEntry entry = AbstractSQLProcessor.parseEntry(entitySet, content, requestContentType, false);

      SQLInsertBuilder insertBuilder = queryBuilder.buildInsertEntityQuery((UriInfo) uriInfo, entry, oDataContext);

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      String targetTableName = getSQLDeleteBuilderTargetTable(dummyBuilder, sqlContext);

      createTemporaryTableLikeTable(connectionParam, beforeTableName, targetTableName);
      insertIntoTemporaryTable(connectionParam, insertBuilder, beforeTableName, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);

      callSuperAfterDeleteEntity(uriInfo, contentType, context);
    } catch (org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException | ODataException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_DELETE_ENTITY_EVENT, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME));
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse onDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);
    DataSource dataSource = (DataSource) context.get(DATASOURCE);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);

      callSuperOnDeleteEntity(uriInfo, contentType, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_ON_DELETE_ENTITY_EVENT, e);
    } finally {
      batchDropTemporaryTables(connectionParam, (String) context.get(BEFORE_TABLE_NAME));
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
