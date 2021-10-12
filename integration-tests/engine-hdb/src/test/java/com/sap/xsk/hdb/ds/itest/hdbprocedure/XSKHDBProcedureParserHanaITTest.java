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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.sql.DataSource;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class XSKHDBProcedureParserHanaITTest {

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
        .clearDataFromXSKDataStructure(datasource, Arrays
            .asList("'/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure'"));
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
    facade.clearCache();
  }

  @Test
  public void testHDBTableFunctionCreate()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      try {
        HanaITestUtils.createSchema(stmt, testSchema);
        HanaITestUtils.createEmptyTable(stmt, "hdbprocedure-itest::SampleHanaTable", testSchema);

        LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
            "/registry/public/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure",
            "/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure");

        this.facade.handleResourceSynchronization(resource);
        this.facade.updateEntities();

        assertTrue(HanaITestUtils.checkExistOfProcedure(connection, "hdbprocedure-itest::SampleHanaProcedure", testSchema));
      } finally {
        HanaITestUtils.dropProcedure(connection, stmt, "hdbprocedure-itest::SampleHanaProcedure", testSchema);
        HanaITestUtils.dropTable(connection, stmt, "hdbprocedure-itest::SampleHanaTable", testSchema);
        HanaITestUtils.dropSchema(stmt, testSchema);
      }
    }
  }

  @Test
  public void testHDBTableFunctionCreateIfAlreadyExist()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      try {
        HanaITestUtils.createSchema(stmt, testSchema);
        HanaITestUtils.createEmptyTable(stmt, "hdbprocedure-itest::SampleHanaTable", testSchema);
        DatabaseMetaData metaData = connection.getMetaData();

        String hdbprocedureSample = org.apache.commons.io.IOUtils
            .toString(XSKHDBProcedureParserHanaITTest.class.getResourceAsStream("/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure"),
                StandardCharsets.UTF_8);
        stmt.executeUpdate("CREATE " + hdbprocedureSample);

        LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
            "/registry/public/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure",
            "/hdbprocedure-itest/SampleHanaProcedure.hdbprocedure");

        this.facade.handleResourceSynchronization(resource);
        this.facade.updateEntities();

        assertTrue(HanaITestUtils.checkExistOfProcedure(connection, "hdbprocedure-itest::SampleHanaProcedure", testSchema));
      } finally {
        HanaITestUtils.dropProcedure(connection, stmt, "hdbprocedure-itest::SampleHanaProcedure", testSchema);
        HanaITestUtils.dropTable(connection, stmt, "hdbprocedure-itest::SampleHanaTable", testSchema);
        HanaITestUtils.dropSchema(stmt, testSchema);
      }
    }
  }

}
