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

import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.info.DeleteUriInfo;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.apache.olingo.odata2.api.uri.info.PutMergePatchUriInfo;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLContext;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLInsertBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLSelectBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLUpdateBuilder;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class XSKScriptingOData2EventHandler extends AbstractXSKOData2EventHandler {

  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  @Override
  public ODataResponse beforeCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLInsertBuilder dummyBuilder = (SQLInsertBuilder) context.get(DUMMY_BUILDER);
    SQLInsertBuilder insertBuilder = (SQLInsertBuilder) context.get(INSERT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

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
      closeConnection(connectionParam);
      batchDropTemporaryTables((String) context.get(AFTER_TABLE_NAME));
    }
    return null;
  }

  @Override
  public ODataResponse afterCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
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
        afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
        createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);
      }

      context.put(CONNECTION, connectionParam);
      context.put(AFTER_TABLE_NAME, afterTableName);

      callSuperAfterCreateEntity(uriInfo, requestContentType, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_CREATE_ENTITY_EVENT, e);
    } finally {
      closeConnection(connectionParam);
      batchDropTemporaryTables((String) context.get(AFTER_TABLE_NAME));
    }
    return null;
  }

  @Override
  public ODataResponse onCreateEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType,
      Map<Object, Object> context) {
    SQLInsertBuilder dummyBuilder = (SQLInsertBuilder) context.get(DUMMY_BUILDER);
    SQLInsertBuilder insertBuilder = (SQLInsertBuilder) context.get(INSERT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      connectionParam = dataSource.getConnection();
      createTemporaryTableLikeTable(connectionParam, afterTableName, targetTableName);
      insertIntoTemporaryTable(connectionParam, insertBuilder, afterTableName, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(AFTER_TABLE_NAME, afterTableName);
      context.put(ON_CREATE_ENTITY_TABLE_NAME, afterTableName);

      callSuperOnCreateEntity(uriInfo, content, requestContentType, contentType, context);

      context.put(ENTRY_MAP, readEntryMap(connectionParam, afterTableName));
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_ON_CREATE_ENTITY_EVENT, e);
    } finally {
      closeConnection(connectionParam);
    }
    return null;
  }

  @Override
  public ODataResponse beforeUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLUpdateBuilder updateBuilder = (SQLUpdateBuilder) context.get(UPDATE_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String beforeUpdateEntityTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);
      updateTemporaryTable(connectionParam, updateBuilder, afterTableName, sqlContext);
      createTemporaryTableAsSelect(connectionParam, beforeUpdateEntityTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);
      context.put(AFTER_TABLE_NAME, afterTableName);
      context.put(BEFORE_UPDATE_ENTITY_TABLE_NAME, beforeUpdateEntityTableName);

      callSuperBeforeUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_UPDATE_ENTITY_EVENT, e);
    } finally {
      closeConnection(connectionParam);
      batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME), (String) context.get(AFTER_TABLE_NAME));
    }
    return null;
  }

  @Override
  public ODataResponse afterUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, context.get(BEFORE_UPDATE_ENTITY_TABLE_NAME));
      context.put(AFTER_TABLE_NAME, afterTableName);

      callSuperAfterUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_UPDATE_ENTITY_EVENT, e);
    } finally {
      closeConnection(connectionParam);
      batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME), (String) context.get(AFTER_TABLE_NAME));
    }
    return null;
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
      closeConnection(connectionParam);
      batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME), (String) context.get(AFTER_TABLE_NAME));
    }
    return null;
  }

  @Override
  public ODataResponse beforeDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String beforeDeleteEntityTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      createTemporaryTableAsSelect(connectionParam, beforeDeleteEntityTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);
      context.put(BEFORE_DELETE_ENTITY_TABLE_NAME, beforeDeleteEntityTableName);

      callSuperBeforeDeleteEntity(uriInfo, contentType, context);
    } catch (ODataException | org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_BEFORE_DELETE_ENTITY_EVENT, e);
    } finally {
      closeConnection(connectionParam);
      batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME));
    }
    return null;
  }

  @Override
  public ODataResponse afterDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, context.get(BEFORE_DELETE_ENTITY_TABLE_NAME));

      callSuperAfterDeleteEntity(uriInfo, contentType, context);
    } catch (org.eclipse.dirigible.engine.odata2.api.ODataException | SQLException e) {
      throw new XSKScriptingOData2EventHandlerException(UNABLE_TO_HANDLE_AFTER_DELETE_ENTITY_EVENT, e);
    } finally {
      closeConnection(connectionParam);
      batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME));
    }
    return null;
  }

  @Override
  public ODataResponse onDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get(SELECT_BUILDER);
    SQLContext sqlContext = (SQLContext) context.get(SQL_CONTEXT);

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
      closeConnection(connectionParam);
      batchDropTemporaryTables((String) context.get(BEFORE_TABLE_NAME));
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
