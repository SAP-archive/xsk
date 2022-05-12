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
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.engine.odata2.handler.ScriptingOData2EventHandler;
import org.eclipse.dirigible.engine.odata2.sql.api.SQLStatement;
import org.eclipse.dirigible.engine.odata2.sql.api.SQLStatementParam;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLContext;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLInsertBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLSelectBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLUpdateBuilder;
import org.eclipse.dirigible.engine.odata2.sql.builder.SQLUtils;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class XSKScriptingOData2EventHandler extends ScriptingOData2EventHandler {

  private static final Logger logger = LoggerFactory.getLogger(XSKScriptingOData2EventHandler.class);
  private static final String ERROR_WHEN_PREPARING_TEMPORARY_TABLE_SQL = "Error when preparing temporary table SQL: ";

  private static final String ODATA2_EVENT_HANDLER_NAME = "xsk-odata-event-handler";

  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  private static final String DUMMY_BUILDER = "dummyBuilder";
  private static final String SELECT_BUILDER = "selectBuilder";
  private static final String INSERT_BUILDER = "insertBuilder";
  private static final String UPDATE_BUILDER = "updateBuilder";
  private static final String SQL_CONTEXT = "sqlContext";

  private static final String CONNECTION = "connection";
  private static final String BEFORE_TABLE_NAME = "beforeTableName";
  private static final String AFTER_TABLE_NAME = "afterTableName";
  private static final String BEFORE_UPDATE_ENTITY_TABLE_NAME = "beforeUpdateEntityTableName";
  private static final String BEFORE_DELETE_ENTITY_TABLE_NAME = "beforeDeleteEntityTableName";

  private static final String SELECT_WILDCARD_FROM = "SELECT * FROM ";

  @Override
  public void beforeCreateEntity(PostUriInfo uriInfo, String requestContentType, String contentType, ODataEntry entry,
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

      super.beforeCreateEntity(uriInfo, requestContentType, contentType, entry, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get(AFTER_TABLE_NAME));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
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

      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(AFTER_TABLE_NAME, afterTableName);

      super.afterCreateEntity(uriInfo, requestContentType, contentType, entry, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get(AFTER_TABLE_NAME));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
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
      connectionParam = dataSource.getConnection();

      String targetTableName = getSQLInsertBuilderTargetTable(dummyBuilder, sqlContext);
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      createTemporaryTableLikeTable(connectionParam, afterTableName, targetTableName);
      insertIntoTemporaryTable(connectionParam, insertBuilder, afterTableName, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(AFTER_TABLE_NAME, afterTableName);

      return super.onCreateEntity(uriInfo, content, requestContentType, contentType, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get(AFTER_TABLE_NAME));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
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

      super.beforeUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get(BEFORE_TABLE_NAME));
        dropTemporaryTable((String) context.get(AFTER_TABLE_NAME));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
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

      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, context.get(BEFORE_UPDATE_ENTITY_TABLE_NAME));
      context.put(AFTER_TABLE_NAME, afterTableName);

      super.afterUpdateEntity(uriInfo, requestContentType, merge, contentType, entry, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get(BEFORE_TABLE_NAME));
        dropTemporaryTable((String) context.get(AFTER_TABLE_NAME));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
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

      String beforeTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());
      String afterTableName = generateTemporaryTableName(uriInfo.getTargetType().getName());

      createTemporaryTableAsSelect(connectionParam, beforeTableName, selectBuilder, sqlContext);
      createTemporaryTableAsSelect(connectionParam, afterTableName, selectBuilder, sqlContext);
      updateTemporaryTable(connectionParam, updateBuilder, afterTableName, sqlContext);

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, beforeTableName);
      context.put(AFTER_TABLE_NAME, afterTableName);

      return super.onUpdateEntity(uriInfo, content, requestContentType, merge, contentType, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get(BEFORE_TABLE_NAME));
        dropTemporaryTable((String) context.get(AFTER_TABLE_NAME));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
    }
  }

  @Override
  public void beforeDeleteEntity(DeleteUriInfo uriInfo, String contentType, Map<Object, Object> context) {
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

      super.beforeDeleteEntity(uriInfo, contentType, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get(BEFORE_TABLE_NAME));
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

      context.put(CONNECTION, connectionParam);
      context.put(BEFORE_TABLE_NAME, context.get(BEFORE_DELETE_ENTITY_TABLE_NAME));

      super.afterDeleteEntity(uriInfo, contentType, context);
    } catch (SQLException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get(BEFORE_TABLE_NAME));
      } catch (SQLException e) {
        logger.error(e.getMessage(), e);
        throw new InvalidStateException(e);
      }
    }
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

      return super.onDeleteEntity(uriInfo, contentType, context);
    } catch (SQLException | ODataException e) {
      logger.error(e.getMessage(), e);
      throw new InvalidStateException(e);
    } finally {
      try {
        closeConnection(connectionParam);
        dropTemporaryTable((String) context.get(BEFORE_TABLE_NAME));
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
    DBMetadataUtil dbMetadataUtil = new DBMetadataUtil();
    String normalizedTableName = DBMetadataUtil.normalizeTableName(likeTableName);
    String odataArtifactTypeSchema = dbMetadataUtil.getOdataArtifactTypeSchema(normalizedTableName);
    String artefactType = dbMetadataUtil.getArtifactType(connection.getMetaData(), connection, normalizedTableName, odataArtifactTypeSchema);
    String qualifiedName = odataArtifactTypeSchema + "." + likeTableName;
    String sql;
    if(artefactType.equals(ISqlKeywords.METADATA_TABLE)) {
      sql = SqlFactory.getNative(connection).create().temporaryTable(temporaryTableName)
          .setLikeTable(qualifiedName).build();
    } else {
      sql = SqlFactory.getNative(connection).create().temporaryTable(temporaryTableName)
          .setAsSelectQuery(SELECT_WILDCARD_FROM + qualifiedName).setSelectWithNoData(true).build();
    }

    PreparedStatement preparedStatement = prepareStatement(connection, sql);
    preparedStatement.execute();
  }

  private void createTemporaryTableAsSelect(Connection connection, String temporaryTableName, SQLSelectBuilder selectBuilder,
      SQLContext sqlContext) throws SQLException, ODataException {
    String sql = SqlFactory.getNative(connection).create().temporaryTable(temporaryTableName)
        .setAsSelectQuery(selectBuilder.buildSelect(sqlContext)).build();
    String sqlWithoutAliases = sql.replaceAll("_T[0-9]+\"", "\"");
    String sqlWithParameters = replaceSqlParameters(sqlWithoutAliases, selectBuilder.getStatementParams());
    PreparedStatement preparedStatement = prepareStatement(connection, sqlWithParameters);
    preparedStatement.execute();
  }

  private String replaceSqlParameters(String sql, List<SQLStatementParam> parameters) {
    for (SQLStatementParam p : parameters) {
      sql = sql.replaceFirst("\\?", "'" + p.getValue() + "'");
    }

    return sql;
  }

  private void insertIntoTemporaryTable(Connection connection, SQLInsertBuilder insertBuilder, String temporaryTableName,
      SQLContext sqlContext)
      throws ODataException, SQLException {
    insertBuilder.setTableName("\"" + temporaryTableName + "\"");
    executeSQLStatement(connection, insertBuilder.build(sqlContext));
  }

  private void updateTemporaryTable(Connection connection, SQLUpdateBuilder updateBuilder, String temporaryTableName, SQLContext sqlContext)
      throws ODataException, SQLException {
    updateBuilder.setTableName("\"" + temporaryTableName + "\"");
    executeSQLStatement(connection, updateBuilder.build(sqlContext));
  }

  private void dropTemporaryTable(String temporaryTableName) throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      if (SqlFactory.getNative(connection).exists(connection, temporaryTableName)) {
        String sql = SqlFactory.getNative(connection).drop().table(temporaryTableName).build();
        PreparedStatement preparedStatement = prepareStatement(connection, sql);
        preparedStatement.executeUpdate();
      }
    }
  }

  private void executeSQLStatement(Connection connection, SQLStatement statement) throws SQLException, ODataException {
    PreparedStatement preparedStatement = prepareStatement(connection, statement.sql());
    SQLUtils.setParamsOnStatement(preparedStatement, statement.getStatementParams());
    preparedStatement.executeUpdate();
  }

  private PreparedStatement prepareStatement(Connection connection, String sql) {
    try {
      logger.debug("Preparing temporary table statement: {}", sql);
      return connection.prepareStatement(sql);
    } catch (SQLException e) {
      throw new IllegalStateException(ERROR_WHEN_PREPARING_TEMPORARY_TABLE_SQL + e.getMessage());
    }
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
