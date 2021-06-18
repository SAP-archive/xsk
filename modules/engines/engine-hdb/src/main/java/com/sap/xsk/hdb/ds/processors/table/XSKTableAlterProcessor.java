/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.processors.table;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sap.xsk.hdb.ds.api.IXSKHdbProcessor;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintUniqueModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKHDBUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.ds.model.processors.TableAlterProcessor;
import org.eclipse.dirigible.database.sql.DataType;
import org.eclipse.dirigible.database.sql.DataTypeUtils;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.table.AlterTableBuilder;
import org.eclipse.dirigible.databases.helpers.DatabaseMetadataHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XSKTableAlterProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableModel> {

  private static final Logger logger = LoggerFactory.getLogger(TableAlterProcessor.class);

  private static final String INCOMPATIBLE_CHANGE_OF_TABLE = "Incompatible change of table [%s] by adding a column [%s] which is [%s]"; //$NON-NLS-1$

  @Inject
  @Named("xskTableCreateProcessor")
  private IXSKHdbProcessor xskTableCreateProcessor;

  /**
   * Execute the corresponding statement.
   *
   * @param connection the connection
   * @param tableModel the table model
   * @throws SQLException the SQL exception
   */
  @Override
  public void execute(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
    boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
    String tableName = tableModel.getName();
    if (caseSensitive) {
      tableName = "\"" + tableName + "\"";
    }
    logger.info("Processing Alter Table: " + tableName);

    Map<String, String> columnDefinitions = new HashMap<String, String>();
    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rsColumns = dmd.getColumns(null, null, DatabaseMetadataHelper.normalizeTableName(tableName), null);
    while (rsColumns.next()) {
//			String typeName = nativeDialect.getDataTypeName(DataTypeUtils.getDatabaseType(rsColumns.getInt(5)));
      String typeName = DataTypeUtils.getDatabaseTypeName(rsColumns.getInt(5));
      columnDefinitions.put(rsColumns.getString(4).toUpperCase(), typeName);
    }

    List<String> modelColumnNames = new ArrayList<String>();

    // ADD iteration
    for (XSKDataStructureHDBTableColumnModel columnModel : tableModel.getColumns()) {
      String name = columnModel.getName();
     /* if (caseSensitive) {
        name = "\"" + name + "\"";
      }*/
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

      modelColumnNames.add(name.toUpperCase());

      if (!columnDefinitions.containsKey(name.toUpperCase())) {

        AlterTableBuilder alterTableBuilder = SqlFactory.getNative(connection).alter().table(tableName);

        alterTableBuilder.add().column(XSKHDBUtils.escapeArtifactName(connection, name), type, isPrimaryKey, isNullable, isUnique, args);

        if (!isNullable) {
          throw new SQLException(String.format(INCOMPATIBLE_CHANGE_OF_TABLE, tableName, name, "NOT NULL"));
        }
        if (isPrimaryKey) {
          throw new SQLException(String.format(INCOMPATIBLE_CHANGE_OF_TABLE, tableName, name, "PRIMARY KEY"));
        }

        executeAlterBuilder(connection, alterTableBuilder);

      } else if (!columnDefinitions.get(name.toUpperCase()).equals(type.toString())) {
        throw new SQLException(String
            .format(INCOMPATIBLE_CHANGE_OF_TABLE, tableName, name, "of type " + columnDefinitions.get(name) + " to be changed to" + type));
      }
    }

    // DROP iteration

    for (String columnName : columnDefinitions.keySet()) {
      if (!modelColumnNames.contains(columnName.toUpperCase())) {
        AlterTableBuilder alterTableBuilder = SqlFactory.getNative(connection).alter().table(tableName);
        if (caseSensitive) {
          columnName = "\"" + columnName + "\"";
        }
        alterTableBuilder.drop().column(columnName, DataType.BOOLEAN);
        executeAlterBuilder(connection, alterTableBuilder);
      }
    }
    //TO DO: alterTableBuilder.renameTable(String from, String to);
    //executeAlterBuilder
    Statement stmt = connection.createStatement();
    String oldTableName = XSKHDBUtils.escapeArtifactName(connection, "old::" + tableModel.getName());
    stmt.executeUpdate(String
        .format("RENAME TABLE %s TO %s", tableName, oldTableName));

    //TO DO: alterTableBuilder.dropExistingIndeces
    List<XSKDataStructureHDBTableConstraintUniqueModel> indices = tableModel.getConstraints().getUniqueIndices();
    indices.forEach(index -> {
      try {
        stmt.executeUpdate(String.format("DROP INDEX %s", XSKHDBUtils.escapeArtifactName(connection, index.getName())));
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    });

    //create brand new table TO DO: alterTableBuilder.createTable(String from, String to);
    xskTableCreateProcessor.execute(connection, tableModel);

    //TO DO: alterTableBuilder.insertContent(String from, String to);
    //all newly added non nullable columns MUST have default values, otherwise the old data could not be inserted. As of now, all new columns are nullable. Look over line 112
    stmt.executeUpdate(String
        .format("INSERT INTO %s SELECT * FROM %s", tableName, oldTableName));

    //drop foreign keys from old table
   /* ResultSet rs = stmt.executeQuery(String.format(
        " SELECT 'ALTER TABLE \"' || \"SCHEMA_NAME\" || '\".\"' || \"TABLE_NAME\" || '\" DROP CONSTRAINT \"' || \"CONSTRAINT_NAME\" || '\"' as \"command\"\n"
            + "FROM \"SYS\".\"REFERENTIAL_CONSTRAINTS\"\n"
            + "WHERE \"SCHEMA_NAME\" = '%s'\n"
            + "AND \"TABLE_NAME\" LIKE '%s'\n"
            + "UNION\n"
            + " SELECT 'ALTER TABLE \"' || \"SCHEMA_NAME\" || '\".\"' || \"TABLE_NAME\" || '\" DROP CONSTRAINT \"' || \"CONSTRAINT_NAME\" || '\"' as \"command\"\n"
            + "FROM \"SYS\".\"CONSTRAINTS\"\n"
            + "WHERE \"SCHEMA_NAME\" = %s\n"
            + "AND \"TABLE_NAME\" LIKE %s\n"
            + "AND \"IS_UNIQUE_KEY\" = 'FALSE';", tableModel.getSchema(), oldTableName, tableModel.getSchema(), oldTableName));
    while (rs.next()) {
      stmt.executeUpdate(rs.getString("command"));
    }*/
    stmt.executeUpdate(String.format("DROP TABLE %s", oldTableName));
  }

  private void executeAlterBuilder(Connection connection, AlterTableBuilder alterTableBuilder)
      throws SQLException {
    final String sql = alterTableBuilder.build();
    logger.info(sql);
    PreparedStatement statement = connection.prepareStatement(sql);
    try {
      statement.executeUpdate();
    } catch (SQLException e) {
      logger.error(sql);
      logger.error(e.getMessage(), e);
      throw new SQLException(e.getMessage(), e);
    } finally {
      if (statement != null) {
        statement.close();
      }
    }
  }


}
