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

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.facade.XSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.HanaITestUtils;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.*;
import static org.junit.Assert.assertTrue;

public class XSKHDBDDHanaITTest {

  private static DataSource datasource;
  private static IXSKHDBCoreFacade facade;

  @BeforeClass
  public static void setUpBeforeClass() throws SQLException {
    JDBCModel model = new JDBCModel(HANA_DRIVER, HANA_URL, HANA_USERNAME,
        HANA_PASSWORD);
    XSKHDBTestModule xskhdbTestModule = new XSKHDBTestModule(model);
    xskhdbTestModule.configure();
    datasource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);
    facade = new XSKHDBCoreFacade();
    facade.clearCache();
  }

  @Before
  public void setUpBeforeTest() throws SQLException {
    HanaITestUtils
        .clearDataFromXSKDataStructure(datasource, Arrays.asList("'/itest/ProductsWithManagedAssItest.hdbdd'", "'/itest/Status.hdbdd'",
            "'/itest/ProductsWithManagedAssWithUsingItest.hdbdd'"));
    facade.clearCache();
  }

  @Test
  public void testHDBDDWithManagedAssOnDiffSchema() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      String schemaName = "TEST_SCHEMA";
      try {
        HanaITestUtils.createSchema(stmt, schemaName);

        LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
            "/registry/public/itest/ProductsWithManagedAssItest.hdbdd",
            "/registry/public/itest/ProductsWithManagedAssItest.hdbdd");

        facade.handleResourceSynchronization(resource);
        facade.updateEntities();

        assertTrue(HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssItest.Orders", schemaName));
        assertTrue(HanaITestUtils.checkExistOfSynonym(connection, "itest::ProductsWithManagedAssItest.Orders"));

        assertTrue(HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssItest.Country", schemaName));
        assertTrue(HanaITestUtils.checkExistOfSynonym(connection, "itest::ProductsWithManagedAssItest.Country"));

      } finally {
        HanaITestUtils.dropTable(connection, stmt, "itest::ProductsWithManagedAssItest.Orders", schemaName);
        HanaITestUtils.dropTable(connection, stmt, "itest::ProductsWithManagedAssItest.Country", schemaName);
        HanaITestUtils.dropSchema(stmt, schemaName);
      }
    }
  }

  @Test
  public void testHDBDDWithManagedAssWithUsingOnDiffSchema()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      String schemaName = "TEST_SCHEMA";
      String schemaName2 = "TEST_SCHEMA 2";
      try {
        HanaITestUtils.createSchema(stmt, schemaName);
        HanaITestUtils.createSchema(stmt, schemaName2);

        LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
            "/registry/public/itest/ProductsWithManagedAssWithUsingItest.hdbdd",
            "/registry/public/itest/ProductsWithManagedAssWithUsingItest.hdbdd");

        LocalResource resource2 = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
            "/registry/public/itest/Status.hdbdd",
            "/registry/public/itest/Status.hdbdd");

        facade.handleResourceSynchronization(resource);
        facade.handleResourceSynchronization(resource2);
        facade.updateEntities();

        assertTrue(HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssWithUsingItest.Orders", schemaName));
        assertTrue(HanaITestUtils.checkExistOfSynonym(connection, "itest::ProductsWithManagedAssWithUsingItest.Orders"));

        assertTrue(HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssWithUsingItest.Country", schemaName));
        assertTrue(HanaITestUtils.checkExistOfSynonym(connection, "itest::ProductsWithManagedAssWithUsingItest.Country"));

        assertTrue(HanaITestUtils.checkExistOfTable(connection, "itest::Status.StatusEntity", schemaName2));
        assertTrue(HanaITestUtils.checkExistOfSynonym(connection, "itest::Status.StatusEntity"));

      } finally {
        HanaITestUtils.dropTable(connection, stmt, "itest::ProductsWithManagedAssWithUsingItest.Orders", schemaName);
        HanaITestUtils.dropTable(connection, stmt, "itest::ProductsWithManagedAssWithUsingItest.Country", schemaName);
        HanaITestUtils.dropTable(connection, stmt, "itest::Status.StatusEntity", schemaName2);
        HanaITestUtils.dropSchema(stmt, schemaName);
        HanaITestUtils.dropSchema(stmt, schemaName2);
      }
    }
  }

}