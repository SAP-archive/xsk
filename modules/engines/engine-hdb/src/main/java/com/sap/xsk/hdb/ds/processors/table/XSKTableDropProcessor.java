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

import static java.text.MessageFormat.format;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKHDBUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;

/**
 * The Table Drop Processor.
 */
public class XSKTableDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableModel> {

	private static final Logger logger = LoggerFactory.getLogger(XSKTableDropProcessor.class);

	/**
	 * Execute the corresponding statement.
	 *
	 * @param connection
	 *            the connection
	 * @param tableModel
	 *            the table model
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void execute(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
		String tableName = XSKHDBUtils.escapeArtifactName(tableModel.getName());
		logger.info("Processing Drop Table: " + tableName);
		if (SqlFactory.getNative(connection).exists(connection, tableName)) {
			String sql = SqlFactory.getNative(connection).select().column("COUNT(*)").from(tableName)
					.build();
			PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				logger.info(sql);
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					if (count > 0) {
						logger.error(
								format("Drop operation for the non empty Table [{0}] will not be executed. Delete all the records in the table first.",
										tableName));
						return;
					}
				}
			} catch (SQLException e) {
				logger.error(sql);
				logger.error(e.getMessage(), e);
			} finally {
				if (statement != null) {
					statement.close();
				}
			}
			
			if (tableModel.getConstraints().getForeignKeys() != null) {
				for (XSKDataStructureHDBTableConstraintForeignKeyModel foreignKeyModel : tableModel.getConstraints().getForeignKeys()) {
					sql = SqlFactory.getNative(connection).drop().constraint(foreignKeyModel.getName()).fromTable(tableName).build();
					executeUpdate(connection, sql);
				}
			}

			sql = SqlFactory.getNative(connection).drop().table(tableName).build();
			executeUpdate(connection, sql);
		}
	}

	private void executeUpdate(Connection connection, String sql) throws SQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			logger.info(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(sql);
			logger.error(e.getMessage(), e);
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

}
