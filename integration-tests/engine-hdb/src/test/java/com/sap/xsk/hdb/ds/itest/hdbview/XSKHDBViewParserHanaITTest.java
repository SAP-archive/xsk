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
package com.sap.xsk.hdb.ds.itest.hdbview;

import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_USERNAME;
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

import com.sap.xsk.hdb.ds.itest.AbstractXSKHDBITTest;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.HanaITestUtils;
import com.sap.xsk.utils.XSKConstants;

public class XSKHDBViewParserHanaITTest extends AbstractXSKHDBITTest {

	@Before
	public void setUpBeforeTest() throws SQLException {
		HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList( //
				"'/hdbview-itest/SampleHANAXSClassicView.hdbview'", //
				"'acme.com.test.tables::MY_TABLE1'", "'acme.com.test.tables::MY_TABLE20'", //
				"'/hdbview-itest/SampleHANAXSClassicViewDiffSchema.hdbview'" //
		));
		Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
		facade.clearCache();
	}

	@Test
	public void testHDBViewCreateOnSameSchema() throws Exception {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			String artifactName = "hdbview-itest::SampleHANAXSClassicView";
			String userSchema = HANA_USERNAME;
			try {
				stmt.executeUpdate(String.format(
						"create table \"%s\".\"acme.com.test.tables::MY_TABLE10\"(COLUMN1 integer,COLUMN2 integer)",
						userSchema));
				stmt.executeUpdate(String.format(
						"create table \"%s\".\"acme.com.test.tables::MY_TABLE20\"(COLUMN1 integer,COLUMN2 integer)",
						userSchema));

				String fileContent = String.format("schema=\"%s\";\n"
						+ "query=\"SELECT T1.\\\"COLUMN2\\\" FROM \\\"acme.com.test.tables::MY_TABLE10\\\" AS T1 LEFT JOIN \\\"acme.com.test.tables::MY_TABLE20\\\" AS T2 ON T1.\\\"COLUMN1\\\" = T2.\\\"COLUMN1\\\"\";\n"
						+ "depends_on= [\"acme.com.test.tables::MY_TABLE10\",\"acme.com.test.tables::MY_TABLE20\"];",
						userSchema);
				LocalResource resource = XSKHDBTestModule.getResourceFromString(
						"/usr/local/target/dirigible/repository/root",
						"/registry/public/hdbview-itest/SampleHANAXSClassicView.hdbview", fileContent);

				facade.handleResourceSynchronization(resource);
				facade.updateEntities();

				assertTrue(HanaITestUtils.checkExistOfView(connection, artifactName, userSchema));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, artifactName));
			} finally {
				HanaITestUtils.dropView(connection, stmt, artifactName, userSchema);
				HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE10", userSchema);
				HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE20", userSchema);
			}
		}
	}

	@Test
	public void testHDBViewUpdateOnSameSchema() throws Exception {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			String artifactName = "hdbview-itest::SampleHANAXSClassicView";
			String userSchema = Configuration.get("hana.username");
			try {
				stmt.executeUpdate(String.format(
						"create table \"%s\".\"acme.com.test.tables::MY_TABLE10\"(COLUMN1 integer,COLUMN2 integer)",
						userSchema));
				stmt.executeUpdate(String.format(
						"create table \"%s\".\"acme.com.test.tables::MY_TABLE20\"(COLUMN1 integer,COLUMN2 integer)",
						userSchema));

				stmt.executeUpdate(String.format(
						"create VIEW  \"%s\".\"%s\" AS select * from \"acme.com.test.tables::MY_TABLE10\"", userSchema,
						artifactName));
				stmt.executeUpdate(String.format("create SYNONYM \"%s\".\"%s\" FOR \"%s\".\"%s\"",
						XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, artifactName, userSchema, artifactName));

				String fileContent = String.format("schema=\"%s\";\n"
						+ "query=\"SELECT T1.\\\"COLUMN2\\\" FROM \\\"acme.com.test.tables::MY_TABLE10\\\" AS T1 LEFT JOIN \\\"acme.com.test.tables::MY_TABLE20\\\" AS T2 ON T1.\\\"COLUMN1\\\" = T2.\\\"COLUMN1\\\"\";\n"
						+ "depends_on= [\"acme.com.test.tables::MY_TABLE10\",\"acme.com.test.tables::MY_TABLE20\"];",
						userSchema);
				LocalResource resource = XSKHDBTestModule.getResourceFromString(
						"/usr/local/target/dirigible/repository/root",
						"/registry/public/hdbview-itest/SampleHANAXSClassicView.hdbview", fileContent);

				facade.handleResourceSynchronization(resource);
				facade.updateEntities();

				assertTrue(HanaITestUtils.checkExistOfView(connection, artifactName, userSchema));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, artifactName));
			} finally {
				HanaITestUtils.dropView(connection, stmt, artifactName, userSchema);
				HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE10", userSchema);
				HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE20", userSchema);
			}
		}
	}

	@Test
	public void testHDBViewCreateOnDiffSchemas() throws Exception {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			String artifactName = "hdbview-itest::SampleHANAXSClassicViewDiffSchema";
			try {
				HanaITestUtils.createSchema(stmt, TEST_SCHEMA);
				stmt.executeUpdate(String.format(
						"create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE10\"(COLUMN1 integer,COLUMN2 integer)",
						TEST_SCHEMA));
				stmt.executeUpdate(String.format(
						"create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE20\"(COLUMN1 integer,COLUMN2 integer)",
						TEST_SCHEMA));
				LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
						"/registry/public/hdbview-itest/SampleHANAXSClassicViewDiffSchema.hdbview",
						"/hdbview-itest/SampleHANAXSClassicViewDiffSchema.hdbview");

				facade.handleResourceSynchronization(resource);
				facade.updateEntities();

				assertTrue(HanaITestUtils.checkExistOfView(connection, artifactName, TEST_SCHEMA));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, artifactName));
			} finally {
				HanaITestUtils.dropView(connection, stmt, artifactName, TEST_SCHEMA);
				HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE10", TEST_SCHEMA);
				HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE20", TEST_SCHEMA);
				HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
			}
		}
	}

	@Test
	public void testHDBViewCreateOnDiffSchemasWithExistingSynonym() throws Exception {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			String artifactName = "hdbview-itest::SampleHANAXSClassicViewDiffSchema";
			try {
				HanaITestUtils.createSchema(stmt, TEST_SCHEMA);
				stmt.executeUpdate(String.format(
						"create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE10\"(COLUMN1 integer,COLUMN2 integer)",
						TEST_SCHEMA));
				stmt.executeUpdate(String.format(
						"create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE20\"(COLUMN1 integer,COLUMN2 integer)",
						TEST_SCHEMA));

				stmt.executeUpdate(String.format(
						"CREATE VIEW \"%s\".\"%s\" AS select * from \"%s\".\"acme.com.test.tables::MY_TABLE20\"",
						TEST_SCHEMA, artifactName, TEST_SCHEMA));
				stmt.executeUpdate(String.format("create SYNONYM \"%s\".\"%s\" FOR \"%s\".\"%s\"",
						XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, artifactName, TEST_SCHEMA, artifactName));
				stmt.executeUpdate(String.format("drop VIEW    \"%s\".\"%s\"", TEST_SCHEMA, artifactName));

				LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
						"/registry/public/hdbview-itest/SampleHANAXSClassicViewDiffSchema.hdbview",
						"/hdbview-itest/SampleHANAXSClassicViewDiffSchema.hdbview");

				facade.handleResourceSynchronization(resource);
				facade.updateEntities();

				assertTrue(HanaITestUtils.checkExistOfView(connection, artifactName, TEST_SCHEMA));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, artifactName));
			} finally {
				HanaITestUtils.dropView(connection, stmt, artifactName, TEST_SCHEMA);
				HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE10", TEST_SCHEMA);
				HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE20", TEST_SCHEMA);
				HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
			}
		}
	}
}