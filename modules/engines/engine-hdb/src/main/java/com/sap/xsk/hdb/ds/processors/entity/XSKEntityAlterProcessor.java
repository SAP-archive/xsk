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
package com.sap.xsk.hdb.ds.processors.entity;

import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.dirigible.database.sql.DataType;
import org.eclipse.dirigible.database.sql.DataTypeUtils;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.table.AlterTableBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Entity Alter Processor.
 */
public class XSKEntityAlterProcessor {

  private static final Logger logger = LoggerFactory.getLogger(XSKEntityAlterProcessor.class);

  private static final String INCOMPATIBLE_CHANGE_OF_ENTITY = "Incompatible change of entity [%s] by adding a column [%s] which is [%s]"; //$NON-NLS-1$

  /**
   * Execute the corresponding statement.
   *
   * @param connection  the connection
   * @param entityModel the entity model
   * @throws SQLException the SQL exception
   */
  public void execute(Connection connection, XSKDataStructureEntityModel entityModel)
      throws SQLException {
    String tableName = XSKHDBUtils.getTableName(entityModel);
    logger.info("Processing Alter Entity: {}", tableName);

    String entityName = tableName;

//		ISqlDialect nativeDialect = SqlFactory.deriveDialect(connection);

    Map<String, String> columnDefinitions = new HashMap<String, String>();
    DatabaseMetaData dmd = connection.getMetaData();
    ResultSet rsColumns = dmd.getColumns(null, null, entityName, null);
    while (rsColumns.next()) {
//			String typeName = nativeDialect.getDataTypeName(DataTypeUtils.getDatabaseType(rsColumns.getInt(5)));
      String typeName = DataTypeUtils.getDatabaseTypeName(rsColumns.getInt(5));
      columnDefinitions.put(rsColumns.getString(4).toUpperCase(), typeName);
    }

    List<String> modelColumnNames = new ArrayList<String>();

    // ADD iteration
    for (XSKDataStructureHDBTableColumnModel columnModel : entityModel.getColumns()) {
      String name = XSKHDBUtils.escapeArtifactName(columnModel.getName());
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
        if (type.equals(DataType.VARCHAR) || type.equals(DataType.CHAR)
            || columnModel.getType().equalsIgnoreCase("NVARCHAR")
            || columnModel.getType().equalsIgnoreCase("ALPHANUM")
            || columnModel.getType().equalsIgnoreCase("SHORTTEXT")) {
          args = ISqlKeywords.OPEN + length + ISqlKeywords.CLOSE;
        }
      } else if ((precision != null) && (scale != null)) {
        if (type.equals(DataType.DECIMAL)) {
          args = ISqlKeywords.OPEN + precision + "," + scale + ISqlKeywords.CLOSE;
        }
      }
      if (defaultValue != null) {
        if ("".equals(defaultValue)) {
          if ((type.equals(DataType.VARCHAR) || type.equals(DataType.CHAR))) {
            args += " DEFAULT '" + defaultValue + "' ";
          }
        } else {
          args += " DEFAULT " + defaultValue + " ";
        }

      }

      modelColumnNames.add(name.toUpperCase());

      if (!columnDefinitions.containsKey(name.toUpperCase())) {

        AlterTableBuilder alterTableBuilder = SqlFactory.getNative(connection).alter().table(tableName);

        alterTableBuilder.add().column(name, type, isPrimaryKey, isNullable, isUnique, args);

        if (!isNullable) {
          String errorMessage = String.format(INCOMPATIBLE_CHANGE_OF_ENTITY, entityName, name, "NOT NULL");
          XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR, entityModel.getLocation(),
              XSKCommonsConstants.HDB_ENTITY_PROCESSOR);
          throw new SQLException(errorMessage);
        }
        if (isPrimaryKey) {
          String errorMessage = String.format(INCOMPATIBLE_CHANGE_OF_ENTITY, entityName, name, "PRIMARY KEY");
          XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR, entityModel.getLocation(),
              XSKCommonsConstants.HDB_ENTITY_PROCESSOR);
          throw new SQLException(errorMessage);
        }

        executeAlterBuilder(connection, alterTableBuilder, entityModel);

      } else if (!columnDefinitions.get(name.toUpperCase()).equals(type.toString())) {
        String errorMessage = String.format(INCOMPATIBLE_CHANGE_OF_ENTITY, entityName, name,
            "of type " + columnDefinitions.get(name) + " to be changed to " + type);
        XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR, entityModel.getLocation(),
            XSKCommonsConstants.HDB_ENTITY_PROCESSOR);
        throw new SQLException(errorMessage);
      }
    }

    // DROP iteration
    for (String columnName : columnDefinitions.keySet()) {
      if (!modelColumnNames.contains(columnName.toUpperCase())) {
        AlterTableBuilder alterTableBuilder = SqlFactory.getNative(connection).alter().table(tableName);
        alterTableBuilder.drop().column(columnName, DataType.BOOLEAN);
        executeAlterBuilder(connection, alterTableBuilder, entityModel);
      }
    }
  }

  private void executeAlterBuilder(Connection connection, AlterTableBuilder alterTableBuilder, XSKDataStructureEntityModel entityModel)
      throws SQLException {
    final String sql = alterTableBuilder.build();
    try (PreparedStatement statement = connection.prepareStatement(sql);) {
      logger.info(sql);
      statement.executeUpdate();
    } catch (SQLException e) {
      logger.error(sql);
      logger.error(e.getMessage(), e);
      XSKCommonsUtils.logProcessorErrors(e.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, entityModel.getLocation(),
          XSKCommonsConstants.HDB_ENTITY_PROCESSOR);
      throw new SQLException(e.getMessage(), e);
    }
  }
}
