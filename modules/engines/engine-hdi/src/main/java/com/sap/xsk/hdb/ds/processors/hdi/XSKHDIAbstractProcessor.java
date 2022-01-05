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
package com.sap.xsk.hdb.ds.processors.hdi;

import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class XSKHDIAbstractProcessor {

	private static final String ERROR_LOCATION = "-";
	private static final Logger LOGGER = LoggerFactory.getLogger(XSKHDIAbstractProcessor.class);

	/**
	 * Execute non-select SQL statement with String parameters
	 * 
	 * @param connection - DB connection
	 * @param sql        - SQL to be executed
	 * @param parameters - SQL parameters
	 * @throws SQLException - in case of failure
	 */
  public void executeUpdate(Connection connection, String sql, String... parameters) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			setStatementParams(statement, parameters);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to execute SQL statement - " + sql, e);
			XSKCommonsUtils.logProcessorErrors(e.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, ERROR_LOCATION,
					XSKCommonsConstants.HDI_PROCESSOR);
		}
	}

	/**
	 * Execute SQL statement with String parameters that might return a result object
	 * 
	 * @param connection - DB connection
	 * @param sql        - SQL to be executed
	 * @param parameters - SQL parameters
	 * @throws SQLException - in case of failure
	 */
	protected void executeQuery(Connection connection, String sql, String... parameters) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			setStatementParams(statement, parameters);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				parseResultSet(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error("Failed to execute SQL statement - " + sql, e);
			XSKCommonsUtils.logProcessorErrors(e.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, ERROR_LOCATION,
					XSKCommonsConstants.HDI_PROCESSOR);
		}
	}

	private void parseResultSet(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			StringBuffer buff = new StringBuffer();
			boolean error = false;
			ResultSetMetaData metaData = resultSet.getMetaData();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				String text = resultSet.getString(i);
				String column = metaData.getColumnName(i);
				
				error = "ERROR".equals(text);
				
				buff.append(column).append(": ").append(text).append(" ");
			}
			if (error) {
				LOGGER.error(buff.toString());
			} else {
				LOGGER.info(buff.toString());
			}
		}
	}

	private void setStatementParams(PreparedStatement statement, String... parameters) throws SQLException {
		int paramIndex = 0;
		for (String param : parameters) {
			statement.setString(++paramIndex, param);
		}
	}

}
