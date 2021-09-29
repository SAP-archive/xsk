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

public class XSKHDBViewParserHanaITTest {

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
        .clearDataFromXSKDataStructure(datasource, Arrays.asList("'/hdbview-itest/SampleHANAXSClassicView.hdbview'",
            "'acme.com.test.tables::MY_TABLE1'", "'acme.com.test.tables::MY_TABLE20'",
            "'/hdbview-itest/SampleHANAXSClassicViewDiffSchema.hdbview'"));
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
    facade.clearCache();
  }

  @Test
  public void testHDBViewCreateOnSameSchema()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      String artifactName = "hdbview-itest::SampleHANAXSClassicView";
      String userSchema = Configuration.get("hana.username");
      try {
        stmt.executeUpdate(String.format("create table \"%s\".\"acme.com.test.tables::MY_TABLE10\"(COLUMN1 integer,COLUMN2 integer)",
            userSchema));
        stmt.executeUpdate(String.format("create table \"%s\".\"acme.com.test.tables::MY_TABLE20\"(COLUMN1 integer,COLUMN2 integer)",
            userSchema));
        LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
            "/registry/public/hdbview-itest/SampleHANAXSClassicView.hdbview",
            "/hdbview-itest/SampleHANAXSClassicView.hdbview");

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
  public void testHDBViewUpdateOnSameSchema()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      String artifactName = "hdbview-itest::SampleHANAXSClassicView";
      String userSchema = Configuration.get("hana.username");
      try {
        stmt.executeUpdate(String.format("create table \"%s\".\"acme.com.test.tables::MY_TABLE10\"(COLUMN1 integer,COLUMN2 integer)",
            userSchema));
        stmt.executeUpdate(String.format("create table \"%s\".\"acme.com.test.tables::MY_TABLE20\"(COLUMN1 integer,COLUMN2 integer)",
            userSchema));

        stmt.executeUpdate(String
            .format("create VIEW  \"%s\".\"%s\" AS select * from \"acme.com.test.tables::MY_TABLE10\"",
                userSchema, artifactName));
        stmt.executeUpdate(String
            .format(
                "create SYNONYM \"%s\".\"%s\" FOR \"%s\".\"%s\"",
                XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, artifactName, userSchema, artifactName));
        LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
            "/registry/public/hdbview-itest/SampleHANAXSClassicView.hdbview",
            "/hdbview-itest/SampleHANAXSClassicView.hdbview");

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
  public void testHDBViewCreateOnDiffSchemas()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      String artifactName = "hdbview-itest::SampleHANAXSClassicViewDiffSchema";
      try {
        HanaITestUtils.createSchema(stmt, testSchema);
        stmt.executeUpdate(String.format("create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE10\"(COLUMN1 integer,COLUMN2 integer)",
            testSchema));
        stmt.executeUpdate(String.format("create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE20\"(COLUMN1 integer,COLUMN2 integer)",
            testSchema));
        LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
            "/registry/public/hdbview-itest/SampleHANAXSClassicViewDiffSchema.hdbview",
            "/hdbview-itest/SampleHANAXSClassicViewDiffSchema.hdbview");

        facade.handleResourceSynchronization(resource);
        facade.updateEntities();

        assertTrue(HanaITestUtils.checkExistOfView(connection, artifactName, testSchema));
        assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, artifactName));
      } finally {
        HanaITestUtils.dropView(connection, stmt, artifactName, testSchema);
        HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE10", testSchema);
        HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE20", testSchema);
        HanaITestUtils.dropSchema(stmt, testSchema);
      }
    }
  }

  @Test
  public void testHDBViewCreateOnDiffSchemasWithExistingSynonym()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      String artifactName = "hdbview-itest::SampleHANAXSClassicViewDiffSchema";
      try {
        HanaITestUtils.createSchema(stmt, testSchema);
        stmt.executeUpdate(String.format("create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE10\"(COLUMN1 integer,COLUMN2 integer)",
            testSchema));
        stmt.executeUpdate(String.format("create TABLE \"%s\".\"acme.com.test.tables::MY_TABLE20\"(COLUMN1 integer,COLUMN2 integer)",
            testSchema));

        stmt.executeUpdate(String.format(
            "CREATE VIEW \"%s\".\"%s\" AS select * from \"%s\".\"acme.com.test.tables::MY_TABLE20\"",
            testSchema, artifactName, testSchema));
        stmt.executeUpdate(String
            .format(
                "create SYNONYM \"%s\".\"%s\" FOR \"%s\".\"%s\"",
                XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, artifactName, testSchema, artifactName));
        stmt.executeUpdate(
            String.format("drop VIEW    \"%s\".\"%s\"", testSchema, artifactName));

        LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
            "/registry/public/hdbview-itest/SampleHANAXSClassicViewDiffSchema.hdbview",
            "/hdbview-itest/SampleHANAXSClassicViewDiffSchema.hdbview");

        facade.handleResourceSynchronization(resource);
        facade.updateEntities();

        assertTrue(HanaITestUtils.checkExistOfView(connection, artifactName, testSchema));
        assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, artifactName));
      } finally {
        HanaITestUtils.dropView(connection, stmt, artifactName, testSchema);
        HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE10", testSchema);
        HanaITestUtils.dropTable(connection, stmt, "acme.com.test.tables::MY_TABLE20", testSchema);
        HanaITestUtils.dropSchema(stmt, testSchema);
      }
    }
  }
}