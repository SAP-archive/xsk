/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.processors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;

/**
 * The View Drop Processor.
 */
public class XSKViewDropProcessor {

	private static final Logger logger = LoggerFactory.getLogger(XSKViewDropProcessor.class);

	/**
	 * Execute the corresponding statement.
	 *
	 * @param connection the connection
	 * @param viewModel the view model
	 * @throws SQLException the SQL exception
	 */
	public static void execute(Connection connection, XSKDataStructureHDBViewModel viewModel) throws SQLException {
		boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
		String viewName = viewModel.getName();
		if (caseSensitive) {
			viewName = "\"" + viewName + "\"";
		}
		logger.info("Processing Drop View: " + viewName);
		if (SqlFactory.getNative(connection).exists(connection, viewName)) {
			String sql = SqlFactory.getNative(connection).drop().view(viewName).build();
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

}
