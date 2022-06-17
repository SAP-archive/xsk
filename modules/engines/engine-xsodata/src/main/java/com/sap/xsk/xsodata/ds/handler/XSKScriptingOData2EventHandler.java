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

import com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.info.DeleteUriInfo;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.apache.olingo.odata2.api.uri.info.PutMergePatchUriInfo;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.engine.odata2.handler.ScriptingOData2EventHandler;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLContext;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLInsertBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLSelectBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLUpdateBuilder;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.AFTER_TABLE_NAME;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.BEFORE_DELETE_ENTITY_TABLE_NAME;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.BEFORE_TABLE_NAME;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.BEFORE_UPDATE_ENTITY_TABLE_NAME;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.CONNECTION;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.DUMMY_BUILDER;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.ENTRY_MAP;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.INSERT_BUILDER;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.ON_CREATE_ENTITY_TABLE_NAME;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.SELECT_BUILDER;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.SQL_CONTEXT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_AFTER_CREATE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_AFTER_DELETE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_AFTER_UPDATE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_BEFORE_CREATE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_BEFORE_DELETE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_BEFORE_UPDATE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_ON_CREATE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_ON_DELETE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UNABLE_TO_HANDLE_ON_UPDATE_ENTITY_EVENT;
import static com.sap.xsk.xsodata.utils.XSKOData2EventHandlerUtils.UPDATE_BUILDER;

public class XSKScriptingOData2EventHandler extends ScriptingOData2EventHandler {

  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  @Override
  public void beforeCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLInsertBuilder dummyBuilder = (SQLInsertBuilder) context.get(DUMMY_BUILDER);
    SQLInsertBuilder insertBuilder = (SQLInsertBuilder) context.get(INSERT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String targetTableName = XSKOData2EventHandlerUtils.getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String afterTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());

      XSKOData2EventHandlerUtils.createTemporaryTableLikeTable(connectionParam, afterTableName, targetTableName);
      XSKOData2EventHandlerUtils.insertIntoTemporaryTable(connectionParam, insertBuilder, afterTableName, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(AFTER_TABLE_NAME, afterTableName);

      super.beforeCreateEntity(uriInfo, requestContentType, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_CREATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connectionParam);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables((String) context.get(AFTER_TABLE_NAME));
    }
  }

  @Override
  public void afterCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String afterTableName;
      if (context.containsKey(ON_CREATE_ENTITY_TABLE_NAME)) {
        afterTableName = (String) context.get(ON_CREATE_ENTITY_TABLE_NAME);
      } else {
        afterTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
        XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);
      }

      context.put(CONNECTION, connectionParam);
      context.put(AFTER_TABLE_NAME, afterTableName);

      super.afterCreateEntity(uriInfo, requestContentType, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_CREATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connectionParam);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables((String) context.get(AFTER_TABLE_NAME));
    }
  }

  @Override
  public ODataResponse onCreateEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType,
      Map<Object, Object> context) {
    SQLInsertBuilder dummyBuilder = (SQLInsertBuilder) context.get(DUMMY_BUILDER);
    SQLInsertBuilder insertBuilder = (SQLInsertBuilder) context.get(INSERT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      String targetTableName = XSKOData2EventHandlerUtils.getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String afterTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());

      connectionParam = dataSource.getConnection();
      XSKOData2EventHandlerUtils.createTemporaryTableLikeTable(connectionParam, afterTableName, targetTableName);
      XSKOData2EventHandlerUtils.insertIntoTemporaryTable(connectionParam, insertBuilder, afterTableName, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(AFTER_TABLE_NAME, afterTableName);
      context.put(ON_CREATE_ENTITY_TABLE_NAME, afterTableName);

      ODataResponse response = super.onCreateEntity(uriInfo, content, requestContentType, contentType, context);

      context.put(ENTRY_MAP, XSKOData2EventHandlerUtils.readEntryMap(connectionParam, afterTableName));
      return response;
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_ON_CREATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connectionParam);
    }
  }

  @Override
  public void beforeUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLUpdateBuilder updateBuilder = (SQLUpdateBuilder) context.get(UPDATE_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      String afterTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      String beforeUpdateEntityTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());

      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);
      XSKOData2EventHandlerUtils.updateTemporaryTable(connectionParam, updateBuilder, afterTableName, sqlContext);
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connectionParam, beforeUpdateEntityTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);
      context.put(AFTER_TABLE_NAME, afterTableName);
      context.put(BEFORE_UPDATE_ENTITY_TABLE_NAME, beforeUpdateEntityTableName);

      super.beforeUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_UPDATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connectionParam);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME), (String) context.get(AFTER_TABLE_NAME));
    }
  }

  @Override
  public void afterUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String afterTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, context.get(BEFORE_UPDATE_ENTITY_TABLE_NAME));
      context.put(AFTER_TABLE_NAME, afterTableName);

      super.afterUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_UPDATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connectionParam);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME), (String) context.get(AFTER_TABLE_NAME));
    }
  }

  @Override
  public ODataResponse onUpdateEntity(PutMergePatchUriInfo uriInfo, InputStream content, String requestContentType, boolean merge,
      String contentType, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLUpdateBuilder updateBuilder = (SQLUpdateBuilder) context.get(UPDATE_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      String afterTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());

      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);
      XSKOData2EventHandlerUtils.updateTemporaryTable(connectionParam, updateBuilder, afterTableName, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);
      context.put(AFTER_TABLE_NAME, afterTableName);

      return super.onUpdateEntity(uriInfo, content, requestContentType, merge, contentType, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_ON_UPDATE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connectionParam);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME), (String) context.get(AFTER_TABLE_NAME));
    }
  }

  @Override
  public void beforeDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      String beforeDeleteEntityTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());

      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connectionParam, beforeDeleteEntityTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);
      context.put(BEFORE_DELETE_ENTITY_TABLE_NAME, beforeDeleteEntityTableName);

      super.beforeDeleteEntity(uriInfo, contentType, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_DELETE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connectionParam);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME));
    }
  }

  @Override
  public void afterDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, context.get(BEFORE_DELETE_ENTITY_TABLE_NAME));

      super.afterDeleteEntity(uriInfo, contentType, context);
    } catch (org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_DELETE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connectionParam);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME));
    }
  }

  @Override
  public ODataResponse onDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = XSKOData2EventHandlerUtils.generateTemporaryTableName(uriInfo.getTargetType().getName());
      XSKOData2EventHandlerUtils.createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);

      return super.onDeleteEntity(uriInfo, contentType, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_ON_DELETE_ENTITY_EVENT, e);
    } finally {
      XSKOData2EventHandlerUtils.closeConnection(connectionParam);
      XSKOData2EventHandlerUtils.batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME));
    }
  }
}
