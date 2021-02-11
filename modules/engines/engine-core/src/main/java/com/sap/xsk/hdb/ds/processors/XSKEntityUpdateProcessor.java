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
import java.sql.SQLException;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.utils.XSKUtils;

public class XSKEntityUpdateProcessor {

	private static final Logger logger = LoggerFactory.getLogger(XSKEntityUpdateProcessor.class);

	private XSKEntityDropProcessor xskEntityDropProcessor;
	private XSKEntityCreateProcessor xskEntityCreateProcessor;
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
        logger.info("Processing Update Entity: {}", tableName);
        if (SqlFactory.getNative(connection).exists(connection, tableName)) {
            if (SqlFactory.getNative(connection).count(connection, tableName) == 0) {
				xskEntityDropProcessor.execute(connection, entityModel);
				xskEntityCreateProcessor.execute(connection, entityModel);
            } else {
            	// XSKEntityAlterProcessor.execute(connection, entityModel);
            }
        } else {
			xskEntityCreateProcessor.execute(connection, entityModel);
        }
	}
}
