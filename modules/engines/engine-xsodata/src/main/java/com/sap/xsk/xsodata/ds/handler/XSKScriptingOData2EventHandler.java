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
import org.eclipse.dirigible.commons.api.context.InvalidStateException;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.engine.odata2.handler.ScriptingOData2EventHandler;
import org.eclipse.dirigible.engine.odata2.sql.api.SQLStatement;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLContext;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLInsertBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLSelectBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class XSKScriptingOData2EventHandler extends ScriptingOData2EventHandler {

  private static final Logger logger = LoggerFactory.getLogger(XSKScriptingOData2EventHandler.class);

  private static final String ODATA2_EVENT_HANDLER_NAME = "xsk-odata-event-handler";

  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  @Override
  public void beforeCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLInsertBuilder dummyBuilder = (SQLInsertBuilder) context.get("dummyBuilder");
    SQLInsertBuilder insertBuilder = (SQLInsertBuilder) context.get("insertBuilder");
    SQLContext sqlContext = (SQLContext) context.get("sqlContext");

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      createTemporaryTableLikeTable(connectionParam, afterTableName, "XSK_SAMPLES_XSODATA_SIMPLE." + targetTableName);

      insertBuilder.setTableName("\"" + afterTableName + "\"");
      SQLStatement statement = insertBuilder.build(sqlContext);
      PreparedStatement preparedStatement = connectionParam.prepareStatement(statement.sql());
      SQLUtils.setParamsOnStatement(preparedStatement, statement.getStatementParams());
      preparedStatement.executeUpdate();

      context.put("connection", connectionParam);
      context.put("afterTableName", afterTableName);
      context.remove("dummyBuilder");
      context.remove("insertBuilder");
      context.remove("sqlContext");

      super.beforeCreateEntity(uriInfo, requestContentType, contentType, entry, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get("afterTableName"));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
    }
  }

  @Override
  public void afterCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
      Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get("selectBuilder");
    SQLContext sqlContext = (SQLContext) context.get("sqlContext");

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      context.put("connection", connectionParam);
      context.put("afterTableName", afterTableName);
      context.remove("selectBuilder");
      context.remove("sqlContext");

      super.afterCreateEntity(uriInfo, requestContentType, contentType, entry, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get("afterTableName"));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
    }
  }

  @Override
  public ODataResponse onCreateEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType,
      Map<Object, Object> context) {
    SQLInsertBuilder dummyBuilder = (SQLInsertBuilder) context.get("dummyBuilder");
    SQLInsertBuilder insertBuilder = (SQLInsertBuilder) context.get("insertBuilder");
    SQLContext sqlContext = (SQLContext) context.get("sqlContext");

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      createTemporaryTableLikeTable(connectionParam, afterTableName, "XSK_SAMPLES_XSODATA_SIMPLE." + targetTableName);

      insertBuilder.setTableName("\"" + afterTableName + "\"");
      SQLStatement statement = insertBuilder.build(sqlContext);
      PreparedStatement preparedStatement = connectionParam.prepareStatement(statement.sql());
      SQLUtils.setParamsOnStatement(preparedStatement, statement.getStatementParams());
      preparedStatement.executeUpdate();

      context.put("connection", connectionParam);
      context.put("afterTableName", afterTableName);
      context.remove("dummyBuilder");
      context.remove("insertBuilder");
      context.remove("sqlContext");

      return super.onCreateEntity(uriInfo, content, requestContentType, contentType, context); //TODO: handle return properly
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get("afterTableName"));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
    }
  }

  @Override
  public void beforeUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get("selectBuilder");
    SQLInsertBuilder updateBuilder = (SQLInsertBuilder) context.get("updateBuilder");
    SQLContext sqlContext = (SQLContext) context.get("sqlContext");

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      updateBuilder.setTableName("\"" + afterTableName + "\"");
      SQLStatement statement = updateBuilder.build(sqlContext);
      PreparedStatement preparedStatement = connectionParam.prepareStatement(statement.sql());
      SQLUtils.setParamsOnStatement(preparedStatement, statement.getStatementParams());
      preparedStatement.executeUpdate();

      context.put("connection", connectionParam);
      context.put("beforeTableName", beforeTableName);
      context.put("afterTableName", afterTableName);
      context.remove("selectBuilder");
      context.remove("updateBuilder");
      context.remove("sqlContext");

      super.beforeUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get("beforeTableName"));
        dropTemporaryTable((String) context.get("afterTableName"));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
    }
  }

  @Override
  public void afterUpdateEntity(PutMergePatchUriInfo uriInfo, String requestContentType, boolean merge, String contentType,
      ODataEntry entry, Map<Object, Object> context) {
    SQLInsertBuilder dummyBuilder = (SQLInsertBuilder) context.get("dummyBuilder");
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get("selectBuilder");
    SQLContext sqlContext = (SQLContext) context.get("sqlContext");

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableLikeTable(connectionParam, beforeTableName, "XSK_SAMPLES_XSODATA_SIMPLE." + targetTableName);
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      // TODO: save entry before the update in "beforeTableName" temp table

      context.put("connection", connectionParam);
      context.put("beforeTableName", beforeTableName);
      context.put("afterTableName", afterTableName);
      context.remove("dummyBuilder");
      context.remove("selectBuilder");
      context.remove("sqlContext");

      super.afterUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get("beforeTableName"));
        dropTemporaryTable((String) context.get("afterTableName"));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
    }
  }

  @Override
  public ODataResponse onUpdateEntity(PutMergePatchUriInfo uriInfo, InputStream content, String requestContentType, boolean merge,
      String contentType, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get("selectBuilder");
    SQLInsertBuilder updateBuilder = (SQLInsertBuilder) context.get("updateBuilder");
    SQLContext sqlContext = (SQLContext) context.get("sqlContext");

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      updateBuilder.setTableName("\"" + afterTableName + "\"");
      SQLStatement statement = updateBuilder.build(sqlContext);
      PreparedStatement preparedStatement = connectionParam.prepareStatement(statement.sql());
      SQLUtils.setParamsOnStatement(preparedStatement, statement.getStatementParams());
      preparedStatement.executeUpdate();

      context.put("connection", connectionParam);
      context.put("beforeTableName", beforeTableName);
      context.put("afterTableName", afterTableName);
      context.remove("selectBuilder");
      context.remove("updateBuilder");
      context.remove("sqlContext");

      return super.onUpdateEntity(uriInfo, content, requestContentType, merge, contentType, context);//TODO: handle return correctly
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get("beforeTableName"));
        dropTemporaryTable((String) context.get("afterTableName"));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
    }
  }

  @Override
  public void beforeDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get("selectBuilder");
    SQLContext sqlContext = (SQLContext) context.get("sqlContext");

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);

      context.put("connection", connectionParam);
      context.put("beforeTableName", beforeTableName);
      context.remove("selectBuilder");
      context.remove("sqlContext");

      super.beforeDeleteEntity(uriInfo, contentType, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get("beforeTableName"));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
    }
  }

  @Override
  public void afterDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      // TODO: save entry before the delete in "beforeTableName" temp table

      context.put("connection", connectionParam);
      context.put("beforeTableName", beforeTableName);

      super.afterDeleteEntity(uriInfo, contentType, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get("beforeTableName"));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
    }
  }

  @Override
  public ODataResponse onDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
    SQLSelectBuilder selectBuilder = (SQLSelectBuilder) context.get("selectBuilder");
    SQLContext sqlContext = (SQLContext) context.get("sqlContext");

    Connection connectionParam = null;
    try {
      connectionParam = dataSource.getConnection();

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);

      context.put("connection", connectionParam);
      context.put("beforeTableName", beforeTableName);
      context.remove("selectBuilder");
      context.remove("sqlContext");

      return super.onDeleteEntity(uriInfo, contentType, context); //TODO: handle return properly
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get("beforeTableName"));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
    }
  }

  @Override
  public String getName() {
    return ODATA2_EVENT_HANDLER_NAME;
  }

  private String getSQLInsertBuilderTargetTable(SQLInsertBuilder insertBuilder, SQLContext sqlContext) throws ODataException {
    SQLStatement sqlStatement = insertBuilder.build(sqlContext);
    sqlStatement.sql();

    return insertBuilder.getTargetTableName();
  }

  private void createTemporaryTableLikeTable(Connection connection, String temporaryTableName, String likeTableName) throws SQLException {
    String sql = SqlFactory.getNative(connection).create().temporaryTable(temporaryTableName).setLikeTable(likeTableName).build();
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.execute();
  }

  private void createTemporaryTableAsSelect(Connection connection, String temporaryTableName, SQLSelectBuilder selectBuilder,
      SQLContext sqlContext) throws SQLException, ODataException {
    String sql = SqlFactory.getNative(connection).create().temporaryTable(temporaryTableName)
        .setAsSelectQuery(selectBuilder.buildSelect(sqlContext)).build();
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    SQLUtils.setParamsOnStatement(preparedStatement, selectBuilder.getStatementParams());
    preparedStatement.execute();
  }

  private void dropTemporaryTable(String temporaryTableName) throws SQLException {
    Connection connection = dataSource.getConnection();
    String sql = SqlFactory.getNative(connection).drop().table(temporaryTableName).build();
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.executeUpdate();
  }

  private void closeConnection(Connection connection) throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
    }
  }

  private String generateTemporaryTableName(String targetTypeName) {
    return "#" + targetTypeName + UUID.randomUUID().toString().replace("-", "");
  }

}
