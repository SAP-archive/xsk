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

import com.sap.xsk.hdb.ds.model.hdbtable.*;
import com.sap.xsk.utils.XSKHDBUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DataType;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.table.CreateTableBuilder;
import org.eclipse.dirigible.database.sql.dialects.postgres.PostgresSqlDialect;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class XSKTableCreateEscapeService {

    private XSKDataStructureHDBTableModel tableModel;
    private CreateTableBuilder createTableBuilder;
    private Connection connection;
    private boolean shouldEscapeArtefactPropertyName;
    private boolean caseSensitive = Boolean
            .parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true"));

    public XSKTableEscapeService(Connection connection, XSKDataStructureHDBTableModel tableModel) {
        this.tableModel = tableModel;
        String escapedName = XSKHDBUtils.escapeArtifactName(connection, tableModel.getName());
        this.createTableBuilder = SqlFactory.getNative(connection).create().table(escapedName);
        this.connection = connection;
        this.shouldEscapeArtefactPropertyName =
                caseSensitive && !SqlFactory.deriveDialect(connection).getClass().equals(PostgresSqlDialect.class);
    }

    public String getDatabaseSpecificSQL() {
        this.escapeHDBTableColumnModel();
        this.escapedConstraintsModel();
        return this.createTableBuilder.build();
    }

    protected void escapeHDBTableColumnModel() {
        List<XSKDataStructureHDBTableColumnModel> columns = this.tableModel.getColumns();
        for (XSKDataStructureHDBTableColumnModel columnModel : columns) {
            String name = (this.shouldEscapeArtefactPropertyName)
                    ? XSKHDBUtils.escapeArtifactName(this.connection, columnModel.getName())
                    : columnModel.getName();
            DataType type = DataType.valueOf(columnModel.getType());

            this.createTableBuilder
                    .column(name, type, columnModel.isPrimaryKey(), columnModel.isNullable(), columnModel.isUnique(),
                            this.getColumnModelArgs(columnModel));
        }
    }

    protected void escapedConstraintsModel() {
        XSKDataStructureHDBTableConstraintsModel constraintsModel = this.tableModel.getConstraints();
        if (Objects.nonNull(constraintsModel)) {
            if (Objects.nonNull(constraintsModel.getPrimaryKey())) {
                createTableBuilder
                        .primaryKey(this.getEscapedColumns(constraintsModel.getPrimaryKey().getColumns()));
            }

            this.escapeTableBuilderForeignKeys();

            this.escapeTableBuilderUniqueIndices();

            List<XSKDataStructureHDBTableConstraintCheckModel> checks = constraintsModel.getChecks();
            if (Objects.nonNull(checks)) {
                for (XSKDataStructureHDBTableConstraintCheckModel check : checks) {
                    String checkName = check.getName();
                    if (caseSensitive) {
                        checkName = (shouldEscapeArtefactPropertyName)
                                ? XSKHDBUtils.escapeArtifactName(connection, checkName)
                                : checkName;
                    }
                    createTableBuilder.check(checkName, check.getExpression());
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
                    || columnModel.getType().equalsIgnoreCase("TIMESTAMP"))) {
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
            if (this.caseSensitive && this.shouldEscapeArtefactPropertyName) {
                primaryKeyColumns[i++] = XSKHDBUtils.escapeArtifactName(this.connection, column);
            } else {
                primaryKeyColumns[i++] = column;
            }
        }

        return primaryKeyColumns;
    }

    protected void escapeTableBuilderForeignKeys() {
        List<XSKDataStructureHDBTableConstraintForeignKeyModel> foreignKeys = this.tableModel.getConstraints().getForeignKeys();
        if (Objects.nonNull(foreignKeys)) {
            for (XSKDataStructureHDBTableConstraintForeignKeyModel foreignKey : foreignKeys) {
                String foreignKeyName = foreignKey.getName();
                String foreignKeyReferencedTable = foreignKey.getReferencedTable();
                if (caseSensitive) {
                    foreignKeyName = (shouldEscapeArtefactPropertyName)
                            ? XSKHDBUtils.escapeArtifactName(connection, foreignKeyName)
                            : foreignKeyName;

                    foreignKeyReferencedTable = (shouldEscapeArtefactPropertyName)
                            ? XSKHDBUtils.escapeArtifactName(connection, foreignKeyReferencedTable)
                            : foreignKeyReferencedTable;
                }
                String[] foreignKeyColumns = this.getEscapedColumns(foreignKey.getColumns());

                String[] foreignKeyReferencedColumns = this.getEscapedColumns(foreignKey.getReferencedColumns());

                createTableBuilder.foreignKey(foreignKeyName, foreignKeyColumns, foreignKeyReferencedTable,
                        foreignKeyReferencedColumns);
            }
        }
    }

    protected void escapeTableBuilderUniqueIndices() {
        List<XSKDataStructureHDBTableConstraintUniqueModel> uniqueIndices = this.tableModel.getConstraints().getUniqueIndices();
        if (Objects.nonNull(uniqueIndices)) {
            for (XSKDataStructureHDBTableConstraintUniqueModel uniqueIndex : uniqueIndices) {
                String uniqueIndexName = uniqueIndex.getName();
                if (caseSensitive) {
                    uniqueIndexName = (shouldEscapeArtefactPropertyName)
                            ? XSKHDBUtils.escapeArtifactName(connection, uniqueIndexName)
                            : uniqueIndexName;
                }
                String[] uniqueIndexColumns = this.getEscapedColumns(uniqueIndex.getColumns());
                createTableBuilder.unique(uniqueIndexName, uniqueIndexColumns);
            }
        }
    }
}
