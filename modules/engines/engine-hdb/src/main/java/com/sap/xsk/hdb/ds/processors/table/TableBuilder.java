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
package com.sap.xsk.hdb.ds.processors.table;

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintCheckModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintUniqueModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintsModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableIndexModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.utils.XSKHDBUtils;
import java.util.List;
import java.util.Objects;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DataType;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.Table;
import org.eclipse.dirigible.database.sql.builders.table.AbstractTableBuilder;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaCreateTableBuilder;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;

public class TableBuilder {

  private boolean caseSensitive = Boolean
      .parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true"));

  public Table build(XSKDataStructureHDBTableModel model) {
    String tableName = XSKHDBUtils.escapeArtifactName(model.getName(), model.getSchema());

    HanaCreateTableBuilder sqlTableBuilder = createTableBuilder(tableName, model.getTableType());

    addTableColumnToBuilder(sqlTableBuilder, model);
    addTableConstraintsToBuilder(sqlTableBuilder, model);
    addTableIndicesToBuilder(sqlTableBuilder, model);

    return sqlTableBuilder.buildTable();
  }

  private HanaCreateTableBuilder createTableBuilder(String tableName, String tableType) {
    HanaSqlDialect dialect = new HanaSqlDialect();
    if (null != tableType) {
      return SqlFactory.getNative(dialect).create().table(tableName, tableType);
    }

    return SqlFactory.getNative(dialect).create().table(tableName);
  }

  private void addTableIndicesToBuilder(HanaCreateTableBuilder sqlTableBuilder, XSKDataStructureHDBTableModel tableModel) {
    List<XSKDataStructureHDBTableIndexModel> indexes = tableModel.getIndexes();
    for (XSKDataStructureHDBTableIndexModel indexModel : indexes) {
      String name = caseSensitive
          ? XSKHDBUtils.escapeArtifactName(indexModel.getIndexName())
          : indexModel.getIndexName();

      sqlTableBuilder
          .index(name, indexModel.isUnique(), indexModel.getOrder(), indexModel.getIndexType(), indexModel.getIndexColumns());
    }
  }

  private void addTableColumnToBuilder(HanaCreateTableBuilder sqlTableBuilder, XSKDataStructureHDBTableModel tableModel) {
    List<XSKDataStructureHDBTableColumnModel> columns = tableModel.getColumns();
    for (XSKDataStructureHDBTableColumnModel columnModel : columns) {
      String name = caseSensitive
          ? XSKHDBUtils.escapeArtifactName(columnModel.getName())
          : columnModel.getName();
      DataType type = DataType.valueOf(columnModel.getType());

      sqlTableBuilder
          .column(name, type, columnModel.isPrimaryKey(), columnModel.isNullable(), columnModel.isUnique(),
              getColumnModelArgs(columnModel));
    }
  }

  private void addTableConstraintsToBuilder(HanaCreateTableBuilder sqlTableBuilder, XSKDataStructureHDBTableModel tableModel) {
    XSKDataStructureHDBTableConstraintsModel constraintsModel = tableModel.getConstraints();
    if (Objects.nonNull(constraintsModel)) {
      if (Objects.nonNull(constraintsModel.getPrimaryKey())) {
        sqlTableBuilder
            .primaryKey(getEscapedColumns(constraintsModel.getPrimaryKey().getColumns()));
      }

      addTableForeignKeysToBuilder(sqlTableBuilder, tableModel);
      addUniqueIndicesToBuilder(sqlTableBuilder, tableModel);

      List<XSKDataStructureHDBTableConstraintCheckModel> checks = constraintsModel.getChecks();
      if (Objects.nonNull(checks)) {
        for (XSKDataStructureHDBTableConstraintCheckModel check : checks) {
          String checkName = check.getName();
          if (caseSensitive) {
            checkName = caseSensitive
                ? XSKHDBUtils.escapeArtifactName(checkName)
                : checkName;
          }
          sqlTableBuilder.check(checkName, check.getExpression());
        }
      }
    }
  }

  private String getColumnModelArgs(XSKDataStructureHDBTableColumnModel columnModel) {
    DataType type = DataType.valueOf(columnModel.getType());
    String args = "";
    if (columnModel.getLength() != null) {
      if (type.equals(DataType.VARCHAR) || type.equals(DataType.CHAR)
          || columnModel.getType().equalsIgnoreCase("NVARCHAR")
          || columnModel.getType().equalsIgnoreCase("ALPHANUM")
          || columnModel.getType().equalsIgnoreCase("SHORTTEXT")) {
        args = ISqlKeywords.OPEN + columnModel.getLength() + ISqlKeywords.CLOSE;
      }
    } else if ((columnModel.getPrecision() != null) && (columnModel.getScale() != null)) {
      if (type.equals(DataType.DECIMAL)) {
        args = ISqlKeywords.OPEN + columnModel.getPrecision() + "," + columnModel.getScale() + ISqlKeywords.CLOSE;
      }
    }
    if (columnModel.getDefaultValue() != null) {

      if ((type.equals(DataType.VARCHAR) || type.equals(DataType.CHAR) || type.equals(DataType.NVARCHAR)
          || columnModel.getType().equalsIgnoreCase("ALPHANUM")
          || columnModel.getType().equalsIgnoreCase("SHORTTEXT")
          || (columnModel.getType().equalsIgnoreCase("TIMESTAMP") && !columnModel.isDefaultValueDateTimeFunction()))) {
        args += " DEFAULT '" + columnModel.getDefaultValue() + "' ";
      } else {
        args += " DEFAULT " + columnModel.getDefaultValue() + " ";
      }

    }
    return args;
  }

  private String[] getEscapedColumns(String[] columns) {
    String[] primaryKeyColumns = new String[columns.length];
    int i = 0;
    for (String column : columns) {
      if (caseSensitive) {
        primaryKeyColumns[i++] = XSKHDBUtils.escapeArtifactName(column);
      } else {
        primaryKeyColumns[i++] = column;
      }
    }

    return primaryKeyColumns;
  }

  private void addTableForeignKeysToBuilder(HanaCreateTableBuilder sqlTableBuilder, XSKDataStructureHDBTableModel tableModel) {
    List<XSKDataStructureHDBTableConstraintForeignKeyModel> foreignKeys = tableModel.getConstraints().getForeignKeys();
    if (Objects.nonNull(foreignKeys)) {
      for (XSKDataStructureHDBTableConstraintForeignKeyModel foreignKey : foreignKeys) {
        String foreignKeyName = foreignKey.getName();
        String foreignKeyReferencedTable = foreignKey.getReferencedTable();
        if (caseSensitive) {
          foreignKeyName = XSKHDBUtils.escapeArtifactName(foreignKeyName);
          foreignKeyReferencedTable = XSKHDBUtils.escapeArtifactName(foreignKeyReferencedTable);
        }
        String[] foreignKeyColumns = this.getEscapedColumns(foreignKey.getColumns());

        String[] foreignKeyReferencedColumns = this.getEscapedColumns(foreignKey.getReferencedColumns());

        sqlTableBuilder.foreignKey(foreignKeyName, foreignKeyColumns, foreignKeyReferencedTable,
            XSKHDBUtils.escapeArtifactName(foreignKey.getReferencedTableSchema()),
            foreignKeyReferencedColumns);
      }
    }
  }

  protected void addUniqueIndicesToBuilder(AbstractTableBuilder builder, XSKDataStructureHDBTableModel tableModel) {
    List<XSKDataStructureHDBTableConstraintUniqueModel> uniqueIndices = tableModel.getConstraints().getUniqueIndices();
    if (Objects.nonNull(uniqueIndices)) {
      for (XSKDataStructureHDBTableConstraintUniqueModel uniqueIndex : uniqueIndices) {
        String uniqueIndexName = uniqueIndex.getIndexName();
        if (this.caseSensitive) {
          uniqueIndexName = XSKHDBUtils.escapeArtifactName(uniqueIndexName);
        }
        String[] uniqueIndexColumns = this.getEscapedColumns(uniqueIndex.getColumns());
        String indexOrder = uniqueIndex.getOrder();
        String indexType = uniqueIndex.getIndexType();
        builder.unique(uniqueIndexName, uniqueIndexColumns, indexType, indexOrder);
      }
    }
  }
}


