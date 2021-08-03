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
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class XSKHDBViewParserHanaITTest {

  private static DataSource datasource;
  private static IXSKHDBCoreFacade facade;

  @BeforeClass
  public static void setUpBeforeClass() throws SQLException {
    JDBCModel model = new JDBCModel(HANA_DRIVER, HANA_URL, HANA_USERNAME,
        HANA_PASSWORD);
    XSKHDBTestModule xskhdbTestModule = new XSKHDBTestModule(model);
    datasource = xskhdbTestModule.getDataSource();
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
        stmt.executeUpdate(String.format("DELETE FROM \"%s\".\"XSK_DATA_STRUCTURES\" WHERE DS_LOCATION ='/hdbview-itest/SampleHANAXSClassicView.hdbview'", hanaUserName));
      }
    }
  }

  @Test
  public void testHDBViewCreateOnSameSchema() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      String schemaName = Configuration.get("hana.username");
      stmt.executeUpdate(String.format("create table \"%s\".\"acme.com.test.tables::MY_TABLE1\"(COLUMN1 integer,COLUMN2 integer)",
          schemaName));
      stmt.executeUpdate(String.format("create table \"%s\".\"acme.com.test.tables::MY_TABLE2\"(COLUMN1 integer,COLUMN2 integer)",
          schemaName));
      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbview-itest/SampleHANAXSClassicView.hdbview",
          "/hdbview-itest/SampleHANAXSClassicView.hdbview");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();

      DatabaseMetaData metaData = connection.getMetaData();
      ResultSet view = metaData.getTables(null, schemaName, "hdbview-itest::SampleHANAXSClassicView", new String[]{ISqlKeywords.KEYWORD_VIEW});
      assertTrue(view.next());

      ResultSet synonym = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "hdbview-itest::SampleHANAXSClassicView", new String[]{ISqlKeywords.KEYWORD_SYNONYM});
      assertTrue(synonym.next());

      stmt.executeUpdate(String.format("drop SYNONYM \"%s\".\"hdbview-itest::SampleHANAXSClassicView\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop VIEW    \"%s\".\"hdbview-itest::SampleHANAXSClassicView\"", schemaName));
      stmt.executeUpdate(String.format("drop table \"%s\".\"acme.com.test.tables::MY_TABLE1\"", schemaName));
      stmt.executeUpdate(String.format("drop table \"%s\".\"acme.com.test.tables::MY_TABLE2\"", schemaName));
    }
  }

  @Test
  public void testHDBViewUpdateOnSameSchema() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      String schemaName = Configuration.get("hana.username");
      stmt.executeUpdate(String.format("create table \"%s\".\"acme.com.test.tables::MY_TABLE1\"(COLUMN1 integer,COLUMN2 integer)",
          schemaName));
      stmt.executeUpdate(String.format("create table \"%s\".\"acme.com.test.tables::MY_TABLE2\"(COLUMN1 integer,COLUMN2 integer)",
          schemaName));

      stmt.executeUpdate(String.format("create VIEW  \"%s\".\"hdbview-itest::SampleHANAXSClassicView\" AS select * from \"acme.com.test.tables::MY_TABLE1\"",
          schemaName));
      stmt.executeUpdate(String.format("create SYNONYM \"%s\".\"hdbview-itest::SampleHANAXSClassicView\" FOR \"%s\".\"hdbview-itest::SampleHANAXSClassicView\"",
          XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, schemaName));
      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbview-itest/SampleHANAXSClassicView.hdbview",
          "/hdbview-itest/SampleHANAXSClassicView.hdbview");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();

      DatabaseMetaData metaData = connection.getMetaData();
      ResultSet view = metaData.getTables(null, schemaName, "hdbview-itest::SampleHANAXSClassicView", new String[]{ISqlKeywords.KEYWORD_VIEW});
      assertTrue(view.next());

      ResultSet synonym = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "hdbview-itest::SampleHANAXSClassicView", new String[]{ISqlKeywords.KEYWORD_SYNONYM});
      assertTrue(synonym.next());

      stmt.executeUpdate(String.format("drop SYNONYM \"%s\".\"hdbview-itest::SampleHANAXSClassicView\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop VIEW    \"%s\".\"hdbview-itest::SampleHANAXSClassicView\"", schemaName));
      stmt.executeUpdate(String.format("drop table \"%s\".\"acme.com.test.tables::MY_TABLE1\"", schemaName));
      stmt.executeUpdate(String.format("drop table \"%s\".\"acme.com.test.tables::MY_TABLE2\"", schemaName));
    }
  }

  @Test
  public void testHDBViewCreateOnDiffSchemas() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      String schemaName = "TEST_SCHEMA";
      stmt.executeUpdate(String.format("create SCHEMA \"%s\"", schemaName));
      stmt.executeUpdate(String.format("create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE1\"(COLUMN1 integer,COLUMN2 integer)",
          schemaName));
      stmt.executeUpdate(String.format("create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE2\"(COLUMN1 integer,COLUMN2 integer)",
          schemaName));
      stmt.executeUpdate(String.format("create SYNONYM \"%s\".\"acme.com.test.tables::MY_TABLE1\" FOR \"%s\".\"acme.com.test.tables::MY_TABLE1\"",
          XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, schemaName));
      stmt.executeUpdate(String.format("create SYNONYM \"%s\".\"acme.com.test.tables::MY_TABLE2\" FOR \"%s\".\"acme.com.test.tables::MY_TABLE2\"",
          XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, schemaName));
      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbview-itest/SampleHANAXSClassicView.hdbview",
          "/hdbview-itest/SampleHANAXSClassicView.hdbview");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();

      DatabaseMetaData metaData = connection.getMetaData();
      ResultSet view = metaData.getTables(null, Configuration.get("hana.username"), "hdbview-itest::SampleHANAXSClassicView", new String[]{ISqlKeywords.KEYWORD_VIEW});
      assertTrue(view.next());

      ResultSet synonym = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "hdbview-itest::SampleHANAXSClassicView", new String[]{ISqlKeywords.KEYWORD_SYNONYM});
      assertTrue(synonym.next());

      stmt.executeUpdate(String.format("drop SYNONYM \"%s\".\"hdbview-itest::SampleHANAXSClassicView\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop VIEW    \"%s\".\"hdbview-itest::SampleHANAXSClassicView\"", Configuration.get("hana.username")));
      stmt.executeUpdate(String.format("drop SYNONYM \"%s\".\"acme.com.test.tables::MY_TABLE1\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop SYNONYM \"%s\".\"acme.com.test.tables::MY_TABLE2\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop TABLE   \"%s\".\"acme.com.test.tables::MY_TABLE1\"", schemaName));
      stmt.executeUpdate(String.format("drop TABLE   \"%s\".\"acme.com.test.tables::MY_TABLE2\"", schemaName));
      stmt.executeUpdate("DROP SCHEMA TEST_SCHEMA");
    }
  }

  @Test
  public void testHDBViewCreateOnDiffSchemasWithExistingSynonym() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      String schemaName = "TEST_SCHEMA";
      stmt.executeUpdate(String.format("create SCHEMA \"%s\"", schemaName));
      stmt.executeUpdate(String.format("create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE1\"(COLUMN1 integer,COLUMN2 integer)",
          schemaName));
      stmt.executeUpdate(String.format("create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE2\"(COLUMN1 integer,COLUMN2 integer)",
          schemaName));
      stmt.executeUpdate(String.format("create SYNONYM \"%s\".\"acme.com.test.tables::MY_TABLE1\" FOR \"%s\".\"acme.com.test.tables::MY_TABLE1\"",
          XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, schemaName));
      stmt.executeUpdate(String.format("create SYNONYM \"%s\".\"acme.com.test.tables::MY_TABLE2\" FOR \"%s\".\"acme.com.test.tables::MY_TABLE2\"",
          XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, schemaName));

      stmt.executeUpdate(String.format("CREATE VIEW \"%s\".\"hdbview-itest::SampleHANAXSClassicView\" AS select * from \"%s\".\"acme.com.test.tables::MY_TABLE2\"",
          Configuration.get("hana.username"), schemaName));
      stmt.executeUpdate(String.format("create SYNONYM \"%s\".\"hdbview-itest::SampleHANAXSClassicView\" FOR \"%s\".\"hdbview-itest::SampleHANAXSClassicView\"",
          XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, Configuration.get("hana.username")));
      stmt.executeUpdate(String.format("drop VIEW    \"%s\".\"hdbview-itest::SampleHANAXSClassicView\"", Configuration.get("hana.username")));

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbview-itest/SampleHANAXSClassicView.hdbview",
          "/hdbview-itest/SampleHANAXSClassicView.hdbview");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();

      DatabaseMetaData metaData = connection.getMetaData();
      ResultSet view = metaData.getTables(null, Configuration.get("hana.username"), "hdbview-itest::SampleHANAXSClassicView", new String[]{ISqlKeywords.KEYWORD_VIEW});
      assertTrue(view.next());

      ResultSet synonym = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "hdbview-itest::SampleHANAXSClassicView", new String[]{ISqlKeywords.KEYWORD_SYNONYM});
      assertTrue(synonym.next());

      stmt.executeUpdate(String.format("drop SYNONYM \"%s\".\"hdbview-itest::SampleHANAXSClassicView\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop VIEW    \"%s\".\"hdbview-itest::SampleHANAXSClassicView\"", Configuration.get("hana.username")));
      stmt.executeUpdate(String.format("drop SYNONYM \"%s\".\"acme.com.test.tables::MY_TABLE1\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop SYNONYM \"%s\".\"acme.com.test.tables::MY_TABLE2\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop TABLE   \"%s\".\"acme.com.test.tables::MY_TABLE1\"", schemaName));
      stmt.executeUpdate(String.format("drop TABLE   \"%s\".\"acme.com.test.tables::MY_TABLE2\"", schemaName));
      stmt.executeUpdate("DROP SCHEMA TEST_SCHEMA");
    }
  }
}