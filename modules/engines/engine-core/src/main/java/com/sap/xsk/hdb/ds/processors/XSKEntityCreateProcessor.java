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
package com.sap.xsk.hdb.ds.processors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DataType;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.table.CreateTableBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.utils.XSKUtils;

/**
 * The Entity Create Processor.
 */
public class XSKEntityCreateProcessor {

	private static final Logger logger = LoggerFactory.getLogger(XSKEntityCreateProcessor.class);

	/**
	 * Execute the corresponding statement.
	 *
	 * @param connection the connection
	 * @param entityModel the entity model
	 * @throws SQLException the SQL exception
	 */
	public void execute(Connection connection, XSKDataStructureEntityModel entityModel) throws SQLException {
		boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
		String tableName = XSKUtils.getTableName(entityModel);
		if (caseSensitive) {
			tableName = "\"" + tableName + "\"";
		}
		logger.info("Processing Create Table: {}", tableName);
		CreateTableBuilder createTableBuilder = SqlFactory.getNative(connection).create().table(tableName);
		List<XSKDataStructureHDBTableColumnModel> columns = entityModel.getColumns();
		List<String> primaryKeyColumns = new ArrayList<String>();
		for (XSKDataStructureHDBTableColumnModel columnModel : columns) {
			String name = columnModel.getName();
			if (caseSensitive) {
				name = "\"" + name + "\"";
			}
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
				createTableBuilder.primaryKey(primaryKeyColumns.toArray(new String[] {}));
			} else if (entityModel.getConstraints().getPrimaryKey() != null) {
				createTableBuilder.primaryKey(entityModel.getConstraints().getPrimaryKey().getName(), entityModel.getConstraints().getPrimaryKey().getColumns());
			}
			if (entityModel.getConstraints().getForeignKeys() != null) {
				for (XSKDataStructureHDBTableConstraintForeignKeyModel foreignKey : entityModel.getConstraints().getForeignKeys()) {
					String foreignKeyName = "FK_" + foreignKey.getName();
					String[] fkColumns = foreignKey.getColumns();
					String referencedTable = XSKUtils.getTableName(entityModel, foreignKey.getReferencedTable());
					String[] referencedColumns = foreignKey.getReferencedColumns();
					if (caseSensitive) {
						foreignKeyName = "\"" + foreignKeyName + "\"";
						for (int i=0;i<fkColumns.length;i++) {
							fkColumns[i] = "\"" + fkColumns[i] + "\"";
						}
						referencedTable = "\"" + referencedTable + "\"";
						for (int i=0;i<referencedColumns.length;i++) {
							referencedColumns[i] = "\"" + referencedColumns[i] + "\"";
						}
					}
					createTableBuilder.foreignKey(foreignKeyName, fkColumns, referencedTable, referencedColumns);
				}
			}
//			if (entityModel.getConstraints().getUniqueIndices() != null) {
//				for (DataStructureTableConstraintUniqueModel uniqueIndex : entityModel.getConstraints().getUniqueIndices()) {
//					createTableBuilder.unique(uniqueIndex.getName(), uniqueIndex.getColumns());
//				}
//			}
//			if (entityModel.getConstraints().getChecks() != null) {
//				for (DataStructureTableConstraintCheckModel check : entityModel.getConstraints().getChecks()) {
//					createTableBuilder.check(check.getName(), check.getExpression());
//				}
//			}
		}

		String sql = createTableBuilder.build();
		try(PreparedStatement statement = connection.prepareStatement(sql);) {
			logger.info(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(sql);
			logger.error(e.getMessage(), e);
			throw new SQLException(e.getMessage(), e);
		}
		
	}

}
