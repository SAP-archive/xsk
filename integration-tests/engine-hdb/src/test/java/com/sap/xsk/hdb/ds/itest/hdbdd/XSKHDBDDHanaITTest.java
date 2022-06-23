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
import com.sap.xsk.hdb.ds.AbstractXSKHDBITTest;
import com.sap.xsk.integration.tests.core.hdb.module.XSKHDBTestModule;
import com.sap.xsk.integration.tests.core.hdb.utils.HanaITestUtils;

public class XSKHDBDDHanaITTest extends AbstractXSKHDBITTest {

  @Before
  public void setUpBeforeTest() throws SQLException {
    HanaITestUtils.clearDataFromXSKDataStructure(systemDatasource, Arrays.asList(
        "'/itest/ProductsWithManagedAssItest.hdbdd'",
        "'/itest/Status.hdbdd'",
        "'/itest/ProductsWithManagedAssWithUsingItest.hdbdd'",
        "'/itest/DefaultValueWithDateTimeFunction.hdbdd'",
        "'/itest/CatalogTableTypes.hdbdd'",
        "'/itest/EmployeesWithViewDefinitions.hdbdd'"
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

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssItest.Orders", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::ProductsWithManagedAssItest.Orders"));

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssItest.Country", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::ProductsWithManagedAssItest.Country"));

      } finally {
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

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssWithUsingItest.Orders", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::ProductsWithManagedAssWithUsingItest.Orders"));

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::ProductsWithManagedAssWithUsingItest.Country", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::ProductsWithManagedAssWithUsingItest.Country"));

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::Status.StatusEntity", schemaName2));
        assertTrue("Expected synonym was not found!", HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::Status.StatusEntity"));

      } finally {
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

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::DefaultValueWithDateTimeFunction.Orders", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::DefaultValueWithDateTimeFunction.Orders"));

      } finally {
        HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
      }
    }
  }

  @Test
  public void testHDBDDWithCatalogTableTypeAnnotation()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

      try {
        HanaITestUtils.createSchema(stmt, TEST_SCHEMA);

        LocalResource resource = XSKHDBTestModule.getResources( //
            "/usr/local/target/dirigible/repository/root", //
            "/registry/public/itest/CatalogTableTypes.hdbdd", //
            "/registry/public/itest/CatalogTableTypes.hdbdd" //
        );

        facade.handleResourceSynchronization(resource);
        facade.updateEntities();

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::CatalogTableTypes.CONFIGURATION1", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::CatalogTableTypes.CONFIGURATION1"));

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::CatalogTableTypes.CONFIGURATION2", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::CatalogTableTypes.CONFIGURATION2"));

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::CatalogTableTypes.CONFIGURATION3", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::CatalogTableTypes.CONFIGURATION3"));

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::CatalogTableTypes.CONFIGURATION4", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::CatalogTableTypes.CONFIGURATION4"));

      } finally {
        HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
      }
    }
  }

  @Test
  public void testHDBDDWithViewDefinitions()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection(); Statement stmt = connection.createStatement()) {

      try {
        HanaITestUtils.createSchema(stmt, TEST_SCHEMA);

        LocalResource resource = XSKHDBTestModule.getResources( //
            "/usr/local/target/dirigible/repository/root", //
            "/registry/public/itest/EmployeesWithViewDefinitions.hdbdd", //
            "/registry/public/itest/EmployeesWithViewDefinitions.hdbdd" //
        );

        facade.handleResourceSynchronization(resource);
        facade.updateEntities();

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::EmployeesWithViewDefinitions.employees", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::EmployeesWithViewDefinitions.employees"));

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::EmployeesWithViewDefinitions.employee_roles", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::EmployeesWithViewDefinitions.employee_roles"));

        assertTrue("Expected table was not found!",
            HanaITestUtils.checkExistOfTable(connection, "itest::EmployeesWithViewDefinitions.employee_salaries", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::EmployeesWithViewDefinitions.employee_salaries"));

        assertTrue("Expected view was not found!",
            HanaITestUtils.checkExistOfView(connection, "itest::EmployeesWithViewDefinitions.employees_view_basic", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::EmployeesWithViewDefinitions.employees_view_basic"));

        assertTrue("Expected view was not found!",
            HanaITestUtils.checkExistOfView(connection, "itest::EmployeesWithViewDefinitions.employees_view_with_join", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::EmployeesWithViewDefinitions.employees_view_with_join"));

        assertTrue("Expected view was not found!",
            HanaITestUtils.checkExistOfView(connection, "itest::EmployeesWithViewDefinitions.employees_view_with_where", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::EmployeesWithViewDefinitions.employees_view_with_where"));

        assertTrue("Expected view was not found!",
            HanaITestUtils.checkExistOfView(connection, "itest::EmployeesWithViewDefinitions.employees_view_with_union", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::EmployeesWithViewDefinitions.employees_view_with_union"));

        assertTrue("Expected view was not found!",
            HanaITestUtils.checkExistOfView(connection, "itest::EmployeesWithViewDefinitions.embedded_context.embedded_view", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::EmployeesWithViewDefinitions.embedded_context.embedded_view"));

        assertTrue("Expected view was not found!",
            HanaITestUtils.checkExistOfView(connection, "itest::EmployeesWithViewDefinitions.outer_view", TEST_SCHEMA));
        assertTrue("Expected synonym was not found!",
            HanaITestUtils.checkExistOfPublicSynonym(connection, "itest::EmployeesWithViewDefinitions.outer_view"));

      } finally {
        HanaITestUtils.dropSchema(stmt, TEST_SCHEMA);
      }
    }
  }

}