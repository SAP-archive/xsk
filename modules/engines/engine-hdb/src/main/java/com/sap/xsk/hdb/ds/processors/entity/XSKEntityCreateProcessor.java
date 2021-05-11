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
package com.sap.xsk.hdb.ds.processors.entity;

import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.dirigible.database.sql.DataType;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.table.CreateTableBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sap.xsk.utils.XSKConstants.SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE;

/**
 * The Entity Create Processor.
 */
public class XSKEntityCreateProcessor extends AbstractXSKProcessor<XSKDataStructureEntityModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKEntityCreateProcessor.class);

  /**
   * Execute the corresponding statement.
   *
   * @param connection  the connection
   * @param entityModel the entity model
   * @throws SQLException the SQL exception
   */
  public void execute(Connection connection, XSKDataStructureEntityModel entityModel) throws SQLException {
    String tableName = XSKHDBUtils.escapeArtifactName(connection, XSKHDBUtils.getTableName(entityModel),
        SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE);
    logger.info("Processing Create Table: {}", tableName);
    CreateTableBuilder createTableBuilder = SqlFactory.getNative(connection).create().table(tableName);
    List<XSKDataStructureHDBTableColumnModel> columns = entityModel.getColumns();
    List<String> primaryKeyColumns = new ArrayList<String>();
    for (XSKDataStructureHDBTableColumnModel columnModel : columns) {
      String name = XSKHDBUtils.escapeArtifactName(connection, columnModel.getName(), SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE);
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
      if (isPrimaryKey) {
        primaryKeyColumns.add(name);
      }
      createTableBuilder.column(name, type, false, isNullable, isUnique, args);
    }
    if (entityModel.getConstraints() != null) {
      if (!primaryKeyColumns.isEmpty()) {
        createTableBuilder.primaryKey(primaryKeyColumns.toArray(new String[]{}));
      } else if (entityModel.getConstraints().getPrimaryKey() != null) {
        createTableBuilder
            .primaryKey(entityModel.getConstraints().getPrimaryKey().getName(), entityModel.getConstraints().getPrimaryKey().getColumns());
      }
      if (entityModel.getConstraints().getForeignKeys() != null) {
        for (XSKDataStructureHDBTableConstraintForeignKeyModel foreignKey : entityModel.getConstraints().getForeignKeys()) {
          String foreignKeyName = "FK_" + foreignKey.getName();
          String[] fkColumns = foreignKey.getColumns();
          String referencedTable = XSKHDBUtils
              .escapeArtifactName(connection, XSKHDBUtils.getTableName(entityModel, foreignKey.getReferencedTable()),
                  SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE);
          String[] referencedColumns = foreignKey.getReferencedColumns();
          foreignKeyName = XSKHDBUtils.escapeArtifactName(connection, foreignKeyName, SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE);
          for (int i = 0; i < fkColumns.length; i++) {
            fkColumns[i] = XSKHDBUtils.escapeArtifactName(connection, fkColumns[i], SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE);
          }
          for (int i = 0; i < referencedColumns.length; i++) {
            referencedColumns[i] = XSKHDBUtils.escapeArtifactName(connection, referencedColumns[i], SHOULD_ADD_ESCAPE_SYMBOL_DEFAULT_VALUE);
          }
          createTableBuilder.foreignKey(foreignKeyName, fkColumns, referencedTable, referencedColumns);
        }
      }
    }

    String sql = createTableBuilder.build();
    executeSql(sql, connection);
  }

}
