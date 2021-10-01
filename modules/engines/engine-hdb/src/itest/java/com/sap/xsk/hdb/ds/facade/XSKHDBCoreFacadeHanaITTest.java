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
package com.sap.xsk.hdb.ds.facade;

import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_DRIVER;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_PASSWORD;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_URL;
import static com.sap.xsk.hdb.ds.itest.utils.TestConstants.HANA_USERNAME;
import static org.junit.Assert.assertTrue;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.HanaITestUtils;
import com.sap.xsk.utils.XSKConstants;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
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

public class XSKHDBCoreFacadeHanaITTest {

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
        .clearDataFromXSKDataStructure(datasource, Arrays.asList("'/acme/com/test/views/MY_VIEW1.hdbview'",
            "'/acme/com/test/views/MY_VIEW2.hdbview'", "'/acme/com/test/tables/W_TABLE1.hdbtable'",
            "'/acme/com/test/tables/W_VIEW_3.hdbview'", "'/acme/com/test/tables/MY_TABLE2.hdbtable'"));
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
    facade.clearCache();
  }

  @Test
  public void testUpdateEntities()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      HanaITestUtils.createSchema(stmt, testSchema);
      LocalResource view1Resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/acme/com/test/views/MY_VIEW1.hdbview",
          "/hdbview-itest/MY_VIEW1.hdbview");
      LocalResource view2Resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/acme/com/test/views/MY_VIEW2.hdbview",
          "/hdbview-itest/MY_VIEW2.hdbview");

      LocalResource table1Resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/acme/com/test/tables/W_TABLE1.hdbtable",
          "/hdbview-itest/W_TABLE1.hdbtable");
      LocalResource table2Resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/acme/com/test/tables/MY_TABLE2.hdbtable",
          "/hdbview-itest/MY_TABLE2.hdbtable");
      LocalResource view3Resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/acme/com/test/tables/W_VIEW_3.hdbview",
          "/hdbview-itest/W_VIEW_3.hdbview");

      facade.handleResourceSynchronization(view3Resource);
      facade.handleResourceSynchronization(view2Resource);
      facade.handleResourceSynchronization(view1Resource);
      facade.handleResourceSynchronization(table1Resource);
      facade.handleResourceSynchronization(table2Resource);

      facade.updateEntities();
      try {
        assertTrue(HanaITestUtils.checkExistOfView(connection, "acme.com.test.views::MY_VIEW1", testSchema));
        assertTrue(HanaITestUtils.checkExistOfPublicSynonym(connection, "acme.com.test.views::MY_VIEW1"));
      } finally {
        HanaITestUtils.dropView(connection, stmt, "acme.com.test.views::MY_VIEW1", testSchema);
        HanaITestUtils.dropView(connection, stmt, "acme.com.test.views::MY_VIEW2", testSchema);
        HanaITestUtils.dropView(connection, stmt, "acme.com.test.tables::W_TABLE1", testSchema);
        HanaITestUtils.dropView(connection, stmt, "acme.com.test.tables::MY_TABLE2", testSchema);
        HanaITestUtils.dropView(connection, stmt, "acme.com.test.views::MY_VIEW3", testSchema);
        HanaITestUtils.dropSchema(stmt, testSchema);
      }
    }
  }
}