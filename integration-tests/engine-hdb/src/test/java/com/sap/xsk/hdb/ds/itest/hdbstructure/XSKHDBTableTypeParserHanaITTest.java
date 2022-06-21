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
package com.sap.xsk.hdb.ds.itest.hdbstructure;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.Test;

import com.sap.xsk.hdb.ds.AbstractXSKHDBITTest;
import com.sap.xsk.integration.tests.core.hdb.module.XSKHDBTestModule;
import com.sap.xsk.integration.tests.core.hdb.utils.HanaITestUtils;
import com.sap.xsk.utils.XSKConstants;

public class XSKHDBTableTypeParserHanaITTest extends AbstractXSKHDBITTest {

	@Before
	public void setUpBeforeTest() throws SQLException {
		HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList( //
				"'/hdbstructure-itest/str1.hdbstructure'", //
				"'/hdbstructure-itest/str2.hdbstructure'" //
		));
		Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
		facade.clearCache();
	}

	@Test
	public void testHDBTableTypeCreateOnSameSchema() throws Exception {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			String userSchema = Configuration.get("hana.username");
			String fileContent = String.format("table.schemaName = \"%s\";\n"
					+ "table.description = \"Business event table type\";\n" + "table.temporary   = true;\n"
					+ "table.columns = [\n"
					+ "    {name = \"ID\";        sqlType = INTEGER;              comment = \"Object identifier\";},\n"
					+ "    {name = \"BIZ_EVENT\"; sqlType = VARCHAR; length = 60; comment = \"Object type code\";}\n"
					+ "];", userSchema);
			LocalResource resource = XSKHDBTestModule.getResourceFromString( //
					"/usr/local/target/dirigible/repository/root", //
					"/registry/public/hdbstructure-itest/str1.hdbstructure", //
					fileContent);

			try {
				facade.handleResourceSynchronization(resource);
				facade.updateEntities();
				assertTrue(HanaITestUtils.checkExistOfTableType(stmt, "hdbstructure-itest::str1", userSchema));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "hdbstructure-itest::str1"));
			} finally {
				HanaITestUtils.dropTableType(connection, stmt, "hdbstructure-itest::str1", userSchema);
			}
		}
	}

	@Test
	public void testHDBViewCreateOnDiffSchemas() throws Exception {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			HanaITestUtils.createSchema(stmt, TEST_SCHEMA);
			LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root", //
					"/registry/public/hdbstructure-itest/str2.hdbstructure", //
					"/hdbstructure-itest/str2.hdbstructure" //
			);

			try {
				facade.handleResourceSynchronization(resource);
				facade.updateEntities();
				assertTrue(HanaITestUtils.checkExistOfTableType(stmt, "hdbstructure-itest::str2", TEST_SCHEMA));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "hdbstructure-itest::str2"));
			} finally {
				HanaITestUtils.dropTableType(connection, stmt, "hdbstructure-itest::str2", TEST_SCHEMA);
				HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
			}
		}
	}

	@Test
	public void testHDBViewCreateOnDiffSchemasWithExistingSynonym() throws Exception {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			HanaITestUtils.createSchema(stmt, TEST_SCHEMA);

			stmt.executeUpdate(String.format(
					"CREATE TYPE \"%s\".\"hdbstructure-itest::str2\" AS TABLE ( \"ID\" INTEGER NOT NULL , \"BIZ_EVENT\" VARCHAR (60) NOT NULL );\n",
					TEST_SCHEMA));
			stmt.executeUpdate(String.format(
					"create SYNONYM \"%s\".\"hdbstructure-itest::str2\" FOR \"%s\".\"hdbstructure-itest::str2\"",
					XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, TEST_SCHEMA));
			stmt.executeUpdate(String.format("drop TYPE    \"%s\".\"hdbstructure-itest::str2\"", TEST_SCHEMA));

			LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root", //
					"/registry/public/hdbstructure-itest/str2.hdbstructure", //
					"/hdbstructure-itest/str2.hdbstructure" //
			);

			try {
				facade.handleResourceSynchronization(resource);
				facade.updateEntities();
				assertTrue(HanaITestUtils.checkExistOfTableType(stmt, "hdbstructure-itest::str2", TEST_SCHEMA));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "hdbstructure-itest::str2"));
			} finally {
				HanaITestUtils.dropTableType(connection, stmt, "hdbstructure-itest::str2", TEST_SCHEMA);
				HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
			}
		}
	}

}