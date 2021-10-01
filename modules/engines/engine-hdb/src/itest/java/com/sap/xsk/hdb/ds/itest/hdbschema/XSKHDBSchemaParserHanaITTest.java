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

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.facade.XSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.HanaITestUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.*;
import static org.junit.Assert.assertTrue;

public class XSKHDBSchemaParserHanaITTest {

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
    HanaITestUtils
        .clearDataFromXSKDataStructure(datasource, Arrays.asList("'/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema'"));
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
    facade.clearCache();
  }

  @Test
  public void testHDBSchemaCreateWithNoCaseSensitivity()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false");
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      String schemaName = "MYSCHEMA";//in hana the name will be created in UpperCase
      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema",
          "/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        assertTrue(HanaITestUtils.checkExistOfSchema(connection, schemaName));
      } finally {
        HanaITestUtils.dropSchema(stmt, schemaName);
      }
    }
  }

  @Test
  public void testHDBSchemaCreate()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      String schemaName = "MySchema";
      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema",
          "/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        assertTrue(HanaITestUtils.checkExistOfSchema(connection, schemaName));
      } finally {
        HanaITestUtils.dropSchema(stmt, schemaName);
      }
    }
  }

  @Test
  public void testHDBSchemaCreateIfSchemaAlreadyExist()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      String schemaName = "MySchema";
      HanaITestUtils.createSchema(stmt, schemaName);
      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema",
          "/hdbschema-itest/SampleHANAXSClassicSchema.hdbschema");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        assertTrue(HanaITestUtils.checkExistOfSchema(connection, schemaName));
      } finally {
        HanaITestUtils.dropSchema(stmt, schemaName);
      }
    }
  }
}