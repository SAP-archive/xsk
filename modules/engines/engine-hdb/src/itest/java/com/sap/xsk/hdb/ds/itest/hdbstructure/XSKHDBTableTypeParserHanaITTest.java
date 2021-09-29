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
package com.sap.xsk.hdb.ds.itest.hdbstructure;

import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_DRIVER;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_PASSWORD;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_URL;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_USERNAME;
import static org.junit.Assert.assertTrue;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.facade.XSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.HanaITestUtils;
import com.sap.xsk.utils.XSKConstants;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.sql.DataSource;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class XSKHDBTableTypeParserHanaITTest {

  private static DataSource datasource;
  private static IXSKHDBCoreFacade facade;
  private static final String testSchema = "TEST_SCHEMA";

  @BeforeClass
  public static void setUpBeforeClass() throws SQLException {
    JDBCModel model = new JDBCModel(HANA_DRIVER, HANA_URL, HANA_USERNAME,
        HANA_PASSWORD);
    XSKHDBTestModule xskhdbTestModule = new XSKHDBTestModule(model);
    xskhdbTestModule.configure();
    datasource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);
    facade = new XSKHDBCoreFacade();
  }

  @Before
  public void setUpBeforeTest() throws SQLException {
    HanaITestUtils
        .clearDataFromXSKDataStructure(datasource, Arrays.asList("'/hdbstructure-itest/str1.hdbstructure'",
            "'/hdbstructure-itest/str2.hdbstructure'"));
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
    facade.clearCache();
  }

  @Test
  public void testHDBTableTypeCreateOnSameSchema()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      String userSchema = Configuration.get("hana.username");
      String fileContent = String.format("table.schemaName = \"%s\";\n"
          + "table.description = \"Business event table type\";\n"
          + "table.temporary   = true;\n"
          + "table.columns = [\n"
          + "    {name = \"ID\";        sqlType = INTEGER;              comment = \"Object identifier\";},\n"
          + "    {name = \"BIZ_EVENT\"; sqlType = VARCHAR; length = 60; comment = \"Object type code\";}\n"
          + "];", userSchema);
      LocalResource resource = XSKHDBTestModule.getResourceFromString("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbstructure-itest/str1.hdbstructure",
          fileContent);

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        assertTrue(HanaITestUtils.checkExistOfTableType(stmt, "hdbstructure-itest::str1", userSchema));
        assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "hdbstructure-itest::str1"));
      } finally {
        HanaITestUtils.dropTableType(connection, stmt, "hdbstructure-itest::str1", userSchema);
      }
    }
  }

  @Test
  public void testHDBViewCreateOnDiffSchemas()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      HanaITestUtils.createSchema(stmt, testSchema);
      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbstructure-itest/str2.hdbstructure",
          "/hdbstructure-itest/str2.hdbstructure");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        assertTrue(HanaITestUtils.checkExistOfTableType(stmt, "hdbstructure-itest::str2", testSchema));
        assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "hdbstructure-itest::str2"));
      } finally {
        HanaITestUtils.dropTableType(connection, stmt, "hdbstructure-itest::str2", testSchema);
        HanaITestUtils.dropSchema(stmt, testSchema);
      }
    }
  }

  @Test
  public void testHDBViewCreateOnDiffSchemasWithExistingSynonym()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      HanaITestUtils.createSchema(stmt, testSchema);

      stmt.executeUpdate(String.format(
          "CREATE TYPE \"%s\".\"hdbstructure-itest::str2\" AS TABLE ( \"ID\" INTEGER NOT NULL , \"BIZ_EVENT\" VARCHAR (60) NOT NULL );\n",
          testSchema));
      stmt.executeUpdate(
          String.format("create SYNONYM \"%s\".\"hdbstructure-itest::str2\" FOR \"%s\".\"hdbstructure-itest::str2\"",
              XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, testSchema));
      stmt.executeUpdate(String.format("drop TYPE    \"%s\".\"hdbstructure-itest::str2\"", testSchema));

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbstructure-itest/str2.hdbstructure",
          "/hdbstructure-itest/str2.hdbstructure");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        assertTrue(HanaITestUtils.checkExistOfTableType(stmt, "hdbstructure-itest::str2", testSchema));
        assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "hdbstructure-itest::str2"));
      } finally {
        HanaITestUtils.dropTableType(connection, stmt, "hdbstructure-itest::str2", testSchema);
        HanaITestUtils.dropSchema(stmt, testSchema);
      }
    }
  }

}