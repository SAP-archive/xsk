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
package com.sap.xsk.hdb.ds.itest;

import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_DRIVER;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_PASSWORD;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_URL;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_USERNAME;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.eclipse.dirigible.commons.config.StaticObjects;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreSynchronizationFacade;
import com.sap.xsk.hdb.ds.facade.XSKHDBCoreSynchronizationFacade;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.HanaITestUtils;

public abstract class AbstractXSKHDBITTest {

	protected static final String TEST_SCHEMA= "TEST_SCHEMA";

	protected static DataSource datasource;
	protected static DataSource systemDatasource;

	protected static IXSKHDBCoreSynchronizationFacade facade;

	@BeforeClass
	public static void setUp() {
		JDBCModel model = new JDBCModel(HANA_DRIVER, HANA_URL, HANA_USERNAME, HANA_PASSWORD);
		XSKHDBTestModule xskhdbTestModule = new XSKHDBTestModule(model);
		xskhdbTestModule.configure();
		datasource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);
		systemDatasource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);
		facade = new XSKHDBCoreSynchronizationFacade();
		facade.clearCache();
	}

	
	@AfterClass
	public static void tearDown() {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {
			HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
		} catch (SQLException e) {
			// Do nothing, the schema is already dropped
		}
	}

	public DataSource getDatasource() {
		return datasource;
	}

	public IXSKHDBCoreSynchronizationFacade getFacade() {
		return facade;
	}
}
