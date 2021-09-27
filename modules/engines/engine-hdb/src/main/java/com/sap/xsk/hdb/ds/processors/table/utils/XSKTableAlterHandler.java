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
package com.sap.xsk.hdb.ds.processors.table.utils;

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DataType;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.table.AlterTableBuilder;
import org.eclipse.dirigible.databases.helpers.DatabaseMetadataHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKTableAlterHandler {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableAlterHandler.class);
  private static final String INCOMPATIBLE_CHANGE_OF_TABLE = "Incompatible change of table [%s] by adding a column [%s] which is [%s]"; //$NON-NLS-1$
  XSKDataStructureHDBTableModel tableModel;
  private Map<String, String> dbColumnTypes;
  private List<String> modelColumnNames;

  public XSKTableAlterHandler(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
    this.dbColumnTypes = new HashMap<>();

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rsColumns = dmd
        .getColumns(null, null, DatabaseMetadataHelper.normalizeTableName(XSKHDBUtils.escapeArtifactName(connection, tableModel.getName())),
            null);
    while (rsColumns.next()) {
      this.dbColumnTypes.put(rsColumns.getString("COLUMN_NAME"), rsColumns.getString("TYPE_NAME"));
    }

    this.modelColumnNames = tableModel.getColumns().stream().map(column -> column.getName()).collect(Collectors.toList());
    this.tableModel = tableModel;
  }

  public void addColumns(Connection connection) throws SQLException, ProblemsException {
    String tableName = XSKHDBUtils.escapeArtifactName(connection, this.tableModel.getName(), this.tableModel.getSchema());

    for (XSKDataStructureHDBTableColumnModel columnModel : tableModel.getColumns()) {
      String name = columnModel.getName();
      DataType type = DataType.valueOf(columnModel.getType());
      String length = columnModel.getLength();
      boolean isNullable = columnModel.isNullable();
      boolean isPrimaryKey = columnModel.isPrimaryKey();
      boolean isUnique = columnModel.isUnique();
      String defaultValue = columnModel.getDefaultValue();
      String precision = columnModel.getPrecision();
      String scale = columnModel.getScale();
      String args = "";
      if (length != null) {
        if (type.equals(DataType.VARCHAR) || type.equals(DataType.CHAR) || type.equals(DataType.NVARCHAR)) {
          args = ISqlKeywords.OPEN + length + ISqlKeywords.CLOSE;
        }
      } else if ((precision != null) && (scale != null)) {
        if (type.equals(DataType.DECIMAL)) {
          args = ISqlKeywords.OPEN + precision + "," + scale + ISqlKeywords.CLOSE;
        }
      }
      if (defaultValue != null) {
        if (type.equals(DataType.VARCHAR) || type.equals(DataType.CHAR) || type.equals(DataType.NVARCHAR)) {
          args += " DEFAULT '" + defaultValue + "' ";
        } else {
          args += " DEFAULT " + defaultValue + " ";
        }
      }

      if (!dbColumnTypes.containsKey(name)) {

        AlterTableBuilder alterTableBuilder = SqlFactory.getNative(connection).alter().table(tableName);

        alterTableBuilder.add().column(XSKHDBUtils.escapeArtifactName(connection, name), type, isPrimaryKey, isNullable, isUnique, args);

        if (!isNullable) {
          String errorMessage = String.format(INCOMPATIBLE_CHANGE_OF_TABLE, tableName, name, "NOT NULL");
          XSKCommonsUtils.logProcessorErrors(errorMessage, "PROCESSOR", tableModel.getLocation(), "HDB Table");
          throw new SQLException(errorMessage);
        }
        if (isPrimaryKey) {
          String errorMessage = String.format(INCOMPATIBLE_CHANGE_OF_TABLE, tableName, name, "PRIMARY KEY");
          XSKCommonsUtils.logProcessorErrors(errorMessage, "PROCESSOR", tableModel.getLocation(), "HDB Table");
          throw new SQLException(errorMessage);
        }

        executeAlterBuilder(connection, alterTableBuilder);

      } else if (!dbColumnTypes.get(name).equals(type.toString())) {
        String errorMessage = String.format(INCOMPATIBLE_CHANGE_OF_TABLE, tableName, name, "of type " + dbColumnTypes.get(name) + " to be changed to " + type);
        XSKCommonsUtils.logProcessorErrors(errorMessage, "PROCESSOR", tableModel.getLocation(), "HDB Table");
        throw new SQLException(errorMessage);
      }
    }
  }

  public void removeColumns(Connection connection) throws SQLException, ProblemsException {
    boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
    for (String columnName : this.dbColumnTypes.keySet()) {
      if (!modelColumnNames.contains(columnName)) {
        AlterTableBuilder alterTableBuilder = SqlFactory.getNative(connection).alter()
            .table(XSKHDBUtils.escapeArtifactName(connection, tableModel.getName(), tableModel.getSchema()));
        if (caseSensitive) {
          columnName = "\"" + columnName + "\"";
        }
        alterTableBuilder.drop().column(columnName, DataType.BOOLEAN);
        executeAlterBuilder(connection, alterTableBuilder);
      }
    }
  }

  public void updateColumns(Connection connection) throws SQLException, ProblemsException {
    String tableName = XSKHDBUtils.escapeArtifactName(connection, this.tableModel.getName(), this.tableModel.getSchema());
    List<XSKDataStructureHDBTableColumnModel> columns = this.getColumnsToUpdate();
    for (XSKDataStructureHDBTableColumnModel columnModel : columns) {
      String name = columnModel.getName();
      DataType type = DataType.valueOf(columnModel.getType());
      String length = columnModel.getLength();
      boolean isNullable = columnModel.isNullable();
      boolean isPrimaryKey = columnModel.isPrimaryKey();
      boolean isUnique = columnModel.isUnique();
      String defaultValue = columnModel.getDefaultValue();
      String precision = columnModel.getPrecision();
      String scale = columnModel.getScale();
      String args = "";
      if (length != null) {
        if (type.equals(DataType.VARCHAR) || type.equals(DataType.CHAR) || type.equals(DataType.NVARCHAR)) {
          args = ISqlKeywords.OPEN + length + ISqlKeywords.CLOSE;
        }
      } else if ((precision != null) && (scale != null)) {
        if (type.equals(DataType.DECIMAL)) {
          args = ISqlKeywords.OPEN + precision + "," + scale + ISqlKeywords.CLOSE;
        }
      }
      if (defaultValue != null) {
        if (type.equals(DataType.VARCHAR) || type.equals(DataType.CHAR) || type.equals(DataType.NVARCHAR)) {
          args += " DEFAULT '" + defaultValue + "' ";
        } else {
          args += " DEFAULT " + defaultValue + " ";
        }
      }

      if (!dbColumnTypes.get(name).equals(type.toString())) {
        String errorMessage = String.format(INCOMPATIBLE_CHANGE_OF_TABLE, tableName, name, "of type " + dbColumnTypes.get(name) + " to be changed to" + type);
        XSKCommonsUtils.logProcessorErrors(errorMessage, "PROCESSOR", tableModel.getLocation(), "HDB Table");
        throw new SQLException(errorMessage);
      }
      AlterTableBuilder alterTableBuilder = SqlFactory.getNative(connection).alter().table(tableName);
      alterTableBuilder.alter().column(XSKHDBUtils.escapeArtifactName(connection, name), type, isPrimaryKey, isNullable, isUnique, args);
      executeAlterBuilder(connection, alterTableBuilder);
    }
  }

  public void rebuildIndeces(Connection connection) throws SQLException, ProblemsException {
    String tableName = XSKHDBUtils.escapeArtifactName(connection, this.tableModel.getName(), this.tableModel.getSchema());
    AlterTableBuilder alterTableBuilder = SqlFactory.getNative(connection).alter().table(tableName);

    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rsIndeces = dmd.getIndexInfo(null, null, this.tableModel.getName(), false, false);

    Statement stmt = connection.createStatement();
    Set<String> droppedIndices = new HashSet<>();
    while (rsIndeces.next()) {
      dropExistingIndex(connection, stmt, droppedIndices, rsIndeces);
    }

    XSKTableEscapeService escapeService = new XSKTableEscapeService(connection, this.tableModel);

    escapeService.escapeTableBuilderUniqueIndices(alterTableBuilder);
    executeAlterBuilder(connection, alterTableBuilder);
  }

  public void ensurePrimaryKeyIsUnchanged(Connection connection) throws SQLException, ProblemsException {
    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rsPrimaryKeys = dmd.getPrimaryKeys(null, null, this.tableModel.getName());
    Set<String> dbPrimaryKeys = new HashSet<>();
    Set<String> modelPrimaryKeys = new HashSet<>();
    if (this.tableModel.getConstraints().getPrimaryKey() != null) {
      modelPrimaryKeys = new HashSet<>(Arrays.asList(this.tableModel.getConstraints().getPrimaryKey().getColumns()));
    }
    while (rsPrimaryKeys.next()) {
      dbPrimaryKeys.add(rsPrimaryKeys.getString("COLUMN_NAME"));
    }
    boolean isPKListUnchanged =
        dbPrimaryKeys.size() == modelPrimaryKeys.size() && dbPrimaryKeys.removeAll(modelPrimaryKeys) && dbPrimaryKeys.isEmpty();
    if (!isPKListUnchanged) {
      String errorMessage = String.format("Incompatible change of table [%s] by trying to change its primary key list", this.tableModel.getName());
      XSKCommonsUtils.logProcessorErrors(errorMessage, "PROCESSOR", tableModel.getLocation(), "HDB Table");
      throw new SQLException(errorMessage);
    }
  }

  private List<XSKDataStructureHDBTableColumnModel> getColumnsToUpdate() {
    Set<String> dbColumnNames = this.dbColumnTypes.keySet();
    Set<String> columnsToUpdate = new HashSet<String>(dbColumnNames);
    columnsToUpdate.retainAll(modelColumnNames);
    return this.tableModel.getColumns().stream()
        .filter(column -> columnsToUpdate.contains(column.getName()))
        .collect(Collectors.toList());
  }

  private void dropExistingIndex(Connection connection, Statement stmt, Set<String> droppedIndices, ResultSet rsIndeces)
      throws SQLException {
    if (!droppedIndices.contains(rsIndeces.getString("INDEX_NAME"))) {

      String sql = String.format("DROP INDEX %s.%s",
          XSKHDBUtils.escapeArtifactName(connection, this.tableModel.getSchema()),
          XSKHDBUtils.escapeArtifactName(connection, rsIndeces.getString("INDEX_NAME")));
      logger.info(sql);
      stmt.executeUpdate(sql);
      droppedIndices.add(rsIndeces.getString("INDEX_NAME"));
    }
  }

  private void executeAlterBuilder(Connection connection, AlterTableBuilder alterTableBuilder)
      throws SQLException, ProblemsException {
    final String multiSQL = alterTableBuilder.build();
    String[] sqlStatements = multiSQL.split(ISqlKeywords.SEMICOLON);

    for (String sql : sqlStatements) {
      logger.info(sql);
      PreparedStatement statement = connection.prepareStatement(sql);
      try {
        statement.executeUpdate();
      } catch (SQLException e) {
        logger.error(sql);
        logger.error(e.getMessage(), e);
        XSKCommonsUtils.logProcessorErrors(e.getMessage(), "PROCESSOR", this.tableModel.getLocation(), "HDB Table");
        throw new SQLException(e.getMessage(), e);
      } finally {
        if (statement != null) {
          statement.close();
        }
      }
    }

  }
}
