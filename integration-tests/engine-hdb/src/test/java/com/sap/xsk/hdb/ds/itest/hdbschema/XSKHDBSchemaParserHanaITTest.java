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
package com.sap.xsk.hdb.ds.itest.hdbschema;

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

public class XSKHDBSchemaParserHanaITTest extends AbstractXSKHDBITTest {

	@Before
	public void setUpBeforeTest() throws SQLException {
		HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList( //
				"'/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema'" //
		));
		Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
		facade.clearCache();
	}

	@Test
	public void testHDBSchemaCreateWithNoCaseSensitivity() throws Exception {
		Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false");
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			String schemaName = "MYSCHEMA";// in hana the name will be created in UpperCase

			try {
				LocalResource resource = XSKHDBTestModule.getResources( //
						"/usr/local/target/dirigible/repository/root", //
						"/registry/public/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema", //
						"/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema" //
				);
				facade.handleResourceSynchronization(resource);
				facade.updateEntities();
				assertTrue(HanaITestUtils.checkExistOfSchema(connection, schemaName));
			} finally {
				HanaITestUtils.dropSchema(stmt, schemaName);
			}
		}
	}

	@Test
	public void testHDBSchemaCreate() throws Exception {
		Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			String schemaName = "MySchema";

			try {
				LocalResource resource = XSKHDBTestModule.getResources( //
						"/usr/local/target/dirigible/repository/root", //
						"/registry/public/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema", //
						"/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema" //
				);
				facade.handleResourceSynchronization(resource);
				facade.updateEntities();
				assertTrue(HanaITestUtils.checkExistOfSchema(connection, schemaName));
			} finally {
				HanaITestUtils.dropSchema(stmt, schemaName);
			}
		}
	}

	@Test
	public void testHDBSchemaCreateIfSchemaAlreadyExist() throws Exception {
		Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

			String schemaName = "MySchema";

			try {
				HanaITestUtils.createSchema(stmt, schemaName);
				LocalResource resource = XSKHDBTestModule.getResources( //
						"/usr/local/target/dirigible/repository/root", //
						"/registry/public/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema", //
						"/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema" //
				);
				facade.handleResourceSynchronization(resource);
				facade.updateEntities();
				assertTrue(HanaITestUtils.checkExistOfSchema(connection, schemaName));
			} finally {
				HanaITestUtils.dropSchema(stmt, schemaName);
			}
		}
	}
}