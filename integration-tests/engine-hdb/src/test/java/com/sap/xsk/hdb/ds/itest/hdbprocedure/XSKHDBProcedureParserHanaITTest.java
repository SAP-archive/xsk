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
package com.sap.xsk.hdb.ds.itest.hdbprocedure;

import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.Test;

import com.sap.xsk.hdb.ds.itest.AbstractXSKHDBITTest;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.HanaITestUtils;

public class XSKHDBProcedureParserHanaITTest extends AbstractXSKHDBITTest {

	@Before
	public void setUpBeforeTest() throws SQLException {
		HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList( //
				"'/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure'" //
		));
		Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
		facade.clearCache();
	}

	@Test
	public void testHDBTableFunctionCreate() throws Exception {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			try {
				HanaITestUtils.createSchema(stmt, TEST_SCHEMA);
				HanaITestUtils.createEmptyTable(stmt, "hdbprocedure-itest::SampleHanaTable", TEST_SCHEMA);

				LocalResource resource = XSKHDBTestModule.getResources( //
						"/usr/local/target/dirigible/repository/root", //
						"/registry/public/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure", //
						"/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure" //
				);

				facade.handleResourceSynchronization(resource);
				facade.updateEntities();

				assertTrue(HanaITestUtils.checkExistOfProcedure(connection, "hdbprocedure-itest::SampleHanaProcedure",
						TEST_SCHEMA));
			} finally {
				HanaITestUtils.dropProcedure(connection, stmt, "hdbprocedure-itest::SampleHanaProcedure", TEST_SCHEMA);
				HanaITestUtils.dropTable(connection, stmt, "hdbprocedure-itest::SampleHanaTable", TEST_SCHEMA);
				HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
			}
		}
	}

	@Test
	public void testHDBTableFunctionCreateIfAlreadyExist() throws Exception {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			try {
				HanaITestUtils.createSchema(stmt, TEST_SCHEMA);
				HanaITestUtils.createEmptyTable(stmt, "hdbprocedure-itest::SampleHanaTable", TEST_SCHEMA);

				String hdbprocedureSample = org.apache.commons.io.IOUtils
						.toString(
								XSKHDBProcedureParserHanaITTest.class
										.getResourceAsStream("/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure"),
								StandardCharsets.UTF_8);
				stmt.executeUpdate("CREATE " + hdbprocedureSample);

				LocalResource resource = XSKHDBTestModule.getResources( //
						"/usr/local/target/dirigible/repository/root", //
						"/registry/public/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure", //
						"/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure" //
				);

				facade.handleResourceSynchronization(resource);
				facade.updateEntities();

				assertTrue(HanaITestUtils.checkExistOfProcedure(connection, "hdbprocedure-itest::SampleHanaProcedure",
						TEST_SCHEMA));
			} finally {
				HanaITestUtils.dropProcedure(connection, stmt, "hdbprocedure-itest::SampleHanaProcedure", TEST_SCHEMA);
				HanaITestUtils.dropTable(connection, stmt, "hdbprocedure-itest::SampleHanaTable", TEST_SCHEMA);
				HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
			}
		}
	}

}
