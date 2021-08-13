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
package com.sap.xsk.hdb.ds.itest.hdbtablefunction;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.facade.XSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.utils.XSKHDBUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_DRIVER;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_PASSWORD;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_URL;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_USERNAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XSKHDBTableFunctionHanaITTest {

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
  }

  @Before
  public void setUpBeforeTest() throws SQLException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      DatabaseMetaData metaData = connection.getMetaData();
      String hanaUserName = Configuration.get("hana.username");
      ResultSet table = metaData.getTables(null, hanaUserName, "XSK_DATA_STRUCTURES", null);
      if (table.next()) {
        stmt.executeUpdate(String.format(
            "DELETE FROM \"%s\".\"XSK_DATA_STRUCTURES\" WHERE DS_LOCATION ='/hdbtablefunction-itest/SampleHanaTableFunction.hdbtablefunction'",
            hanaUserName));
      }
    }
  }

  @Test
  public void testHDBTableFunctionCreate() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      DatabaseMetaData metaData = connection.getMetaData();
      ResultSet tables = metaData.getTables(null, null, "hdbtablefunction-itest::SampleHanaTable", null);
      if (!tables.next()) {
        stmt.executeUpdate(String.format("create table \"%s\".\"hdbtablefunction-itest::SampleHanaTable\"(Id INTEGER,Name NVARCHAR)",
            Configuration.get("hana.username")));
      }

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbtablefunction-itest/SampleHanaTableFunction.hdbtablefunction",
          "/hdbtablefunction-itest/SampleHanaTableFunction.hdbtablefunction");

      this.facade.handleResourceSynchronization(resource);
      this.facade.updateEntities();

      ResultSet rs = stmt.executeQuery(
          "SELECT COUNT(*) as rawsCount FROM SYS.OBJECTS WHERE OBJECT_NAME IN ('hdbtablefunction-itest::SampleHanaTableFunction')");
      assertTrue(rs.next());
      assertEquals(1, rs.getInt("rawsCount"));
      stmt.executeUpdate(
          String.format("DROP FUNCTION %s", XSKHDBUtils.escapeArtifactName(connection, "hdbtablefunction-itest::SampleHanaTableFunction")));
      stmt.executeUpdate(
          String.format("DROP TABLE \"%s\".\"hdbtablefunction-itest::SampleHanaTable\"", Configuration.get("hana.username")));
    }
  }
}
