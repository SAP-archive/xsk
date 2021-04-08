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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKHDBUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DataType;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.table.CreateTableBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintCheckModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintUniqueModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;

/**
 * The Table Create Processor.
 */
public class XSKTableCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableModel> {

	private static final Logger logger = LoggerFactory.getLogger(XSKTableCreateProcessor.class);

	/**
	 * Execute the corresponding statement.
	 *
	 * @param connection the connection
	 * @param tableModel the table model
	 * @throws SQLException the SQL exception
	 */
	public void execute(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {

		boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
		String tableName = XSKHDBUtils.escapeArtifactName(tableModel.getName());
		logger.info("Processing Create Table: " + tableName);
		CreateTableBuilder createTableBuilder = SqlFactory.getNative(connection).create().table(tableName);
		List<XSKDataStructureHDBTableColumnModel> columns = tableModel.getColumns();
		for (XSKDataStructureHDBTableColumnModel columnModel : columns) {
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

					if ((type.equals(DataType.VARCHAR) || type.equals(DataType.CHAR) || type.equals(DataType.NVARCHAR)
							|| columnModel.getType().equalsIgnoreCase("ALPHANUM")
							|| columnModel.getType().equalsIgnoreCase("SHORTTEXT"))) {
						args += " DEFAULT '" + defaultValue + "' ";
					}
				 else {
					args += " DEFAULT " + defaultValue + " ";
				}

			}
			createTableBuilder.column(name, type, isPrimaryKey, isNullable, isUnique, args);
		}
		if (tableModel.getConstraints() != null) {
			if (tableModel.getConstraints().getPrimaryKey() != null) {
				String[] primaryKeyColumns = new String[tableModel.getConstraints().getPrimaryKey().getColumns().length];
				int i = 0;
				for (String column : tableModel.getConstraints().getPrimaryKey().getColumns()) {
					if (caseSensitive) {
						primaryKeyColumns[i++] = "\"" + column + "\"";
					} else {
						primaryKeyColumns[i++] = column;
					}
				}
				
				createTableBuilder.primaryKey(primaryKeyColumns);
			}
			if (tableModel.getConstraints().getForeignKeys() != null) {
				for (XSKDataStructureHDBTableConstraintForeignKeyModel foreignKey : tableModel.getConstraints().getForeignKeys()) {
					String foreignKeyName = foreignKey.getName();
					if (caseSensitive) {
						foreignKeyName = "\"" + foreignKeyName + "\"";
					}
					String[] foreignKeyColumns = new String[foreignKey.getColumns().length];
					int i = 0;
					for (String column : foreignKey.getColumns()) {
						if (caseSensitive) {
							foreignKeyColumns[i++] = "\"" + column + "\"";
						} else {
							foreignKeyColumns[i++] = column;
						}
					}
					String foreignKeyReferencedTable = foreignKey.getReferencedTable();
					if (caseSensitive) {
						foreignKeyReferencedTable = "\"" + foreignKeyReferencedTable + "\"";
					}
					String[] foreignKeyReferencedColumns = new String[foreignKey.getReferencedColumns().length];
					i = 0;
					for (String column : foreignKey.getReferencedColumns()) {
						if (caseSensitive) {
							foreignKeyReferencedColumns[i++] = "\"" + column + "\"";
						} else {
							foreignKeyReferencedColumns[i++] = column;
						}
					}
					
					createTableBuilder.foreignKey(foreignKeyName, foreignKeyColumns, foreignKeyReferencedTable,
							foreignKeyReferencedColumns);
				}
			}
			if (tableModel.getConstraints().getUniqueIndices() != null) {
				for (XSKDataStructureHDBTableConstraintUniqueModel uniqueIndex : tableModel.getConstraints().getUniqueIndices()) {
					String uniqueIndexName = uniqueIndex.getName();
					if (caseSensitive) {
						uniqueIndexName = "\"" + uniqueIndexName + "\"";
					}
					String[] uniqueIndexColumns = new String[uniqueIndex.getColumns().length];
					int i = 0;
					for (String column : uniqueIndex.getColumns()) {
						if (caseSensitive) {
							uniqueIndexColumns[i++] = "\"" + column + "\"";
						} else {
							uniqueIndexColumns[i++] = column;
						}
					}
					createTableBuilder.unique(uniqueIndexName, uniqueIndexColumns);
				}
			}
			if (tableModel.getConstraints().getChecks() != null) {
				for (XSKDataStructureHDBTableConstraintCheckModel check : tableModel.getConstraints().getChecks()) {
					String checkName = check.getName();
					if (caseSensitive) {
						checkName = "\"" + checkName + "\"";
					}
					createTableBuilder.check(checkName, check.getExpression());
				}
			}
		}

		final String sql = createTableBuilder.build();
		executeSql(sql, connection);
	}

}
