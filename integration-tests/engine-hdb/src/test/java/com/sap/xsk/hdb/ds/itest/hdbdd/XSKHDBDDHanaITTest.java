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
package com.sap.xsk.hdb.ds.itest.hdbdd;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.Test;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.itest.AbstractXSKHDBITTest;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.HanaITestUtils;

public class XSKHDBDDHanaITTest extends AbstractXSKHDBITTest {

	@Before
	public void setUpBeforeTest() throws SQLException {
		HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList( //
				"'/itest/ProductsWithManagedAssItest.hdbdd'", //
				"'/itest/Status.hdbdd'", //
				"'/itest/ProductsWithManagedAssWithUsingItest.hdbdd'" //
		));
		Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
		facade.clearCache();
	}

	@Test
	public void testHDBDDWithManagedAssOnDiffSchema() throws Exception {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {
			try {
				HanaITestUtils.createSchema(stmt, TEST_SCHEMA);

				LocalResource resource = XSKHDBTestModule.getResources( //
						"/usr/local/target/dirigible/repository/root", //
						"/registry/public/itest/ProductsWithManagedAssItest.hdbdd", //
						"/registry/public/itest/ProductsWithManagedAssItest.hdbdd" //
				);

				facade.handleResourceSynchronization(resource);
				facade.updateEntities();

				assertTrue(HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssItest.Orders", TEST_SCHEMA));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::ProductsWithManagedAssItest.Orders"));

				assertTrue(HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssItest.Country", TEST_SCHEMA));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::ProductsWithManagedAssItest.Country"));

			} finally {
				HanaITestUtils.dropTable(connection, stmt, "itest::ProductsWithManagedAssItest.Orders", TEST_SCHEMA);
				HanaITestUtils.dropTable(connection, stmt, "itest::ProductsWithManagedAssItest.Country", TEST_SCHEMA);
				HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
			}
		}
	}

	@Test
	public void testHDBDDWithManagedAssWithUsingOnDiffSchema()
			throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
		try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {
			String schemaName2 = "TEST_SCHEMA 2";
			try {
				HanaITestUtils.createSchema(stmt, TEST_SCHEMA);
				HanaITestUtils.createSchema(stmt, schemaName2);

				LocalResource resource = XSKHDBTestModule.getResources( //
						"/usr/local/target/dirigible/repository/root", //
						"/registry/public/itest/ProductsWithManagedAssWithUsingItest.hdbdd", //
						"/registry/public/itest/ProductsWithManagedAssWithUsingItest.hdbdd" //
				);

				LocalResource resource2 = XSKHDBTestModule.getResources( //
						"/usr/local/target/dirigible/repository/root", //
						"/registry/public/itest/Status.hdbdd", //
						"/registry/public/itest/Status.hdbdd" //
				);

				facade.handleResourceSynchronization(resource);
				facade.handleResourceSynchronization(resource2);
				facade.updateEntities();

				assertTrue(HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssWithUsingItest.Orders", TEST_SCHEMA));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::ProductsWithManagedAssWithUsingItest.Orders"));

				assertTrue(HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssWithUsingItest.Country", TEST_SCHEMA));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::ProductsWithManagedAssWithUsingItest.Country"));

				assertTrue(HanaITestUtils.checkExistOfTable(connection, "itest::Status.StatusEntity", schemaName2));
				assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::Status.StatusEntity"));

			} finally {
				HanaITestUtils.dropTable(connection, stmt, "itest::ProductsWithManagedAssWithUsingItest.Orders", TEST_SCHEMA);
				HanaITestUtils.dropTable(connection, stmt, "itest::ProductsWithManagedAssWithUsingItest.Country", TEST_SCHEMA);
				HanaITestUtils.dropTable(connection, stmt, "itest::Status.StatusEntity", schemaName2);
				HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
				HanaITestUtils.dropSchema(stmt, schemaName2);
			}
		}
	}

  @Test
  public void testHDBDDWithDateTimeFunctionDefaultValue()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

      try {
        HanaITestUtils.createSchema(stmt, TEST_SCHEMA);

        LocalResource resource = XSKHDBTestModule.getResources( //
            "/usr/local/target/dirigible/repository/root", //
            "/registry/public/itest/DefaultValueWithDateTimeFunction.hdbdd", //
            "/registry/public/itest/DefaultValueWithDateTimeFunction.hdbdd" //
        );

        facade.handleResourceSynchronization(resource);
        facade.updateEntities();

        assertTrue(HanaITestUtils.checkExistOfTable(connection, "itest::DefaultValueWithDateTimeFunction.Orders", TEST_SCHEMA));
        assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::DefaultValueWithDateTimeFunction.Orders"));


      } finally {
        HanaITestUtils.dropTable(connection, stmt, "itest::DefaultValueWithDateTimeFunction.Orders", TEST_SCHEMA);
        HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
      }
    }
  }

}