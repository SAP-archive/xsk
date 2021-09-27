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
import com.sap.xsk.utils.XSKConstants;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class XSKHDBTableTypeParserHanaITTest {

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
        stmt.executeUpdate(String
            .format(
                "DELETE FROM \"%s\".\"XSK_DATA_STRUCTURES\" WHERE DS_LOCATION IN ('/hdbstructure-itest/str1.hdbstructure', '/hdbstructure-itest/str2.hdbstructure')",
                hanaUserName));
      }
      Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
      facade.clearCache();
    }
  }

  @Test
  public void testHDBTableTypeCreateOnSameSchema()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      String schemaName = Configuration.get("hana.username");
      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbstructure-itest/str1.hdbstructure",
          "/hdbstructure-itest/str1.hdbstructure");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();

      DatabaseMetaData metaData = connection.getMetaData();
      assertTrue(doesTableTypeExist(stmt, "hdbstructure-itest::str1", schemaName));

      ResultSet synonym = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "hdbstructure-itest::str1",
          new String[]{ISqlKeywords.KEYWORD_SYNONYM});
      assertTrue(synonym.next());

      stmt.executeUpdate(
          String.format("drop SYNONYM \"%s\".\"hdbstructure-itest::str1\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop TYPE    \"%s\".\"hdbstructure-itest::str1\"", schemaName));
    }
  }


  @Test
  public void testHDBViewCreateOnDiffSchemas()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      String schemaName = "TEST_SCHEMA";
      stmt.executeUpdate(String.format("create SCHEMA \"%s\"", schemaName));
      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbstructure-itest/str2.hdbstructure",
          "/hdbstructure-itest/str2.hdbstructure");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();

      DatabaseMetaData metaData = connection.getMetaData();
      assertTrue(doesTableTypeExist(stmt, "hdbstructure-itest::str2", schemaName));

      ResultSet synonym = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "hdbstructure-itest::str2",
          new String[]{ISqlKeywords.KEYWORD_SYNONYM});
      assertTrue(synonym.next());

      stmt.executeUpdate(
          String.format("drop SYNONYM \"%s\".\"hdbstructure-itest::str2\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop TYPE    \"%s\".\"hdbstructure-itest::str2\"", schemaName));
      stmt.executeUpdate("DROP SCHEMA TEST_SCHEMA CASCADE ");
    }
  }

  @Test
  public void testHDBViewCreateOnDiffSchemasWithExistingSynonym()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      String schemaName = "TEST_SCHEMA";
      stmt.executeUpdate(String.format("create SCHEMA \"%s\"", schemaName));
      stmt.executeUpdate(String.format(
          "CREATE TYPE \"%s\".\"hdbstructure-itest::str2\" AS TABLE ( \"ID\" INTEGER NOT NULL , \"BIZ_EVENT\" VARCHAR (60) NOT NULL );\n",
          schemaName));
      stmt.executeUpdate(
          String.format("create SYNONYM \"%s\".\"hdbstructure-itest::str2\" FOR \"%s\".\"hdbstructure-itest::str2\"",
              XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, schemaName));
      stmt.executeUpdate(String.format("drop TYPE    \"%s\".\"hdbstructure-itest::str2\"", schemaName));

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbstructure-itest/str2.hdbstructure",
          "/hdbstructure-itest/str2.hdbstructure");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();

      DatabaseMetaData metaData = connection.getMetaData();
      assertTrue(doesTableTypeExist(stmt, "hdbstructure-itest::str2", schemaName));

      ResultSet synonym = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "hdbstructure-itest::str2",
          new String[]{ISqlKeywords.KEYWORD_SYNONYM});
      assertTrue(synonym.next());

      stmt.executeUpdate(
          String.format("drop SYNONYM \"%s\".\"hdbstructure-itest::str2\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop TYPE    \"%s\".\"hdbstructure-itest::str2\"", schemaName));
      stmt.executeUpdate("DROP SCHEMA TEST_SCHEMA CASCADE ");
    }
  }

  boolean doesTableTypeExist(Statement stmt, String tableTypeName, String schemaName) throws SQLException {
    ResultSet tableType = stmt
        .executeQuery(
            String.format(
                "SELECT IS_USER_DEFINED_TYPE FROM SYS.\"TABLES\" WHERE TABLE_NAME like '%s' AND IS_USER_DEFINED_TYPE like 'TRUE' AND SCHEMA_NAME LIKE '%s'",
                tableTypeName, schemaName));
    return tableType.next();
  }

}