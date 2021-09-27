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
package com.sap.xsk.hdb.ds.itest.hdbtable;

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
import com.sap.xsk.hdb.ds.processors.table.utils.XSKTableAlterHandler;
import com.sap.xsk.utils.XSKConstants;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import nl.altindag.log.LogCaptor;
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
public class XSKHDBTableParserHanaITTest {

  private static DataSource datasource;
  private static IXSKHDBCoreFacade facade;
  private static int indexCounter;

  @BeforeClass
  public static void setUpBeforeClass() throws SQLException {
    JDBCModel model = new JDBCModel(HANA_DRIVER, HANA_URL, HANA_USERNAME,
        HANA_PASSWORD);
    XSKHDBTestModule xskhdbTestModule = new XSKHDBTestModule(model);
    xskhdbTestModule.configure();
    datasource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);
    facade = new XSKHDBCoreFacade();
    indexCounter = 0;
    facade.clearCache();
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
            .format("DELETE FROM \"%s\".\"XSK_DATA_STRUCTURES\" WHERE DS_LOCATION IN "
                    + "('/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable','/hdbtable-itest/compatible-length-change-hana.hdbtable',"
                    + "'/hdbtable-itest/incompatible-nullable-change-hana.hdbtable','/hdbtable-itest/incompatible-unique-change-hana.hdbtable',"
                    + "'/hdbtable-itest/incompatible-primary-key-change-hana.hdbtable','/hdbtable-itest/adding-new-column-to-pk-list-hana.hdbtable',"
                    + "'/hdbtable-itest/adding-new-not-nullable-column-hana.hdbtable', '/hdbtable-itest/incompatible-column-type-change-hana.hdbtable')",
                hanaUserName));
      }
    }
    facade.clearCache();
  }

  @Test
  public void testHDBTableCreateOnSameSchema()
      throws XSKDataStructuresException, SynchronizationException, IOException, SQLException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      String schemaName = "test";
      stmt.executeUpdate(String.format("CREATE SCHEMA \"%s\"", schemaName));

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable",
          "/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();

      DatabaseMetaData metaData = connection.getMetaData();
      ResultSet table = metaData
          .getTables(null, schemaName, "hdbtable-itest::SamplePostgreXSClassicTable", new String[]{ISqlKeywords.KEYWORD_TABLE});
      assertTrue(table.next());

      ResultSet synonym = metaData.getTables(null, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, "hdbtable-itest::SamplePostgreXSClassicTable",
          new String[]{ISqlKeywords.KEYWORD_SYNONYM});
      assertTrue(synonym.next());

      stmt.executeUpdate(
          String.format("drop SYNONYM \"%s\".\"hdbtable-itest::SamplePostgreXSClassicTable\"", XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA));
      stmt.executeUpdate(String.format("drop table \"%s\".\"hdbtable-itest::SamplePostgreXSClassicTable\"", schemaName));
      stmt.executeUpdate(String.format("DROP SCHEMA \"%s\" CASCADE", schemaName));
    }
  }

  @Test
  public void testHDBTableAlterWhenCompatibleChange()
      throws SQLException, IOException, XSKDataStructuresException, SynchronizationException, ProblemsException {

    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
      DatabaseMetaData metaData = connection.getMetaData();
      createNonEmptyTable(stmt, metaData, "hdbtable-itest::compatible-length-change-hana");

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbtable-itest/compatible-length-change-hana.hdbtable",
          "/hdbtable-itest/compatible-length-change-hana.hdbtable");

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        ResultSet allColumns = metaData
            .getColumns(null, null, "hdbtable-itest::compatible-length-change-hana",
                null);
        boolean isCol78Added = false, isCol77Removed = true, isCol66Updated = false;
        while (allColumns.next()) {
          if (allColumns.getString("COLUMN_NAME").equals("Col78")) {
            isCol78Added = true;
          }
          if (allColumns.getString("COLUMN_NAME").equals("Col77")) {
            isCol77Removed = false;
          }

          if (allColumns.getString("COLUMN_NAME").equals("Col66")) {
            isCol66Updated = allColumns.getString("TYPE_NAME").equals("NVARCHAR") && allColumns.getString("COLUMN_SIZE").equals("50");
          }
        }
        assertTrue(isCol78Added);
        assertTrue(isCol77Removed);
        assertTrue(isCol66Updated);

        ResultSet rsIndeces = metaData.getIndexInfo(null, null, "hdbtable-itest::compatible-length-change-hana", false, false);
        boolean isCol78Indexed = false;
        while (rsIndeces.next()) {
          if (rsIndeces.getString("INDEX_NAME").equals("BB112") && rsIndeces.getString("COLUMN_NAME").equals("Col78")) {
            isCol78Indexed = true;
          }
        }
        assertTrue(isCol78Indexed);
      } finally {
        stmt.executeUpdate("drop table \"hdbtable-itest::compatible-length-change-hana\"");
        Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false");
      }


    }
  }

  @Test
  public void testHDBTableAlterWhenNullableToNotNullableChange()
      throws SQLException, IOException, XSKDataStructuresException, SynchronizationException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
      DatabaseMetaData metaData = connection.getMetaData();
      createNonEmptyTable(stmt, metaData, "hdbtable-itest::incompatible-nullable-change-hana");

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbtable-itest/incompatible-nullable-change-hana.hdbtable",
          "/hdbtable-itest/incompatible-nullable-change-hana.hdbtable");
      LogCaptor logCaptor = LogCaptor.forClass(XSKTableAlterHandler.class);

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        String errMsg = "SAP DBTech JDBC: [7]: feature not supported: NULL value exists: Col66: line 1 col 72 (at pos 71)";
        assertTrue(logCaptor.getErrorLogs().stream().anyMatch(logMsg -> logMsg.equals(errMsg)));
      } finally {
        stmt.executeUpdate("drop table \"hdbtable-itest::incompatible-nullable-change-hana\"");
        Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false");
      }
    }

  }

  @Test
  public void testHDBTableAlterWhenNotUniqueToUniqueChange()
      throws SQLException, IOException, XSKDataStructuresException, SynchronizationException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
      DatabaseMetaData metaData = connection.getMetaData();
      createNonEmptyTable(stmt, metaData, "hdbtable-itest::incompatible-unique-change-hana");

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbtable-itest/incompatible-unique-change-hana.hdbtable",
          "/hdbtable-itest/incompatible-unique-change-hana.hdbtable");
      LogCaptor logCaptor = LogCaptor.forClass(XSKTableAlterHandler.class);

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        String errMsg = "SAP DBTech JDBC: [349]: cannot CREATE UNIQUE INDEX; duplicate key found:  [5] "
            + "Several documents with the same ID exist in the index;DBADMIN:hdbtable-itest::incompatible-unique-change-hana.Col66 content not unique, "
            + "cannot define unique constraint. rowCount != distinctCount ";
        assertTrue(logCaptor.getErrorLogs().stream().anyMatch(logMsg -> logMsg.equals(errMsg)));

      } finally {
        stmt.executeUpdate("drop table \"hdbtable-itest::incompatible-unique-change-hana\"");
        Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false");
      }


    }
  }

  @Test
  public void testHDBTableAlterWhenAddingExistingColumnToPKList()
      throws SQLException, IOException, XSKDataStructuresException, SynchronizationException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
      DatabaseMetaData metaData = connection.getMetaData();
      createNonEmptyTable(stmt, metaData, "hdbtable-itest::incompatible-primary-key-change-hana");

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbtable-itest/incompatible-primary-key-change-hana.hdbtable",
          "/hdbtable-itest/incompatible-primary-key-change-hana.hdbtable");
      LogCaptor logCaptor = LogCaptor.forClass(XSKHDBCoreFacade.class);

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        String errMsg = String.format("Incompatible change of table [%s] by trying to change its primary key list",
            "hdbtable-itest::incompatible-primary-key-change-hana");
        assertTrue(logCaptor.getErrorLogs().stream().anyMatch(logMsg -> logMsg.equals(errMsg)));

      } finally {
        stmt.executeUpdate("drop table \"hdbtable-itest::incompatible-primary-key-change-hana\"");
        Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false");
      }


    }
  }

  @Test
  public void testHDBTableAlterWhenAddingNewColumnToPKList()
      throws SQLException, IOException, XSKDataStructuresException, SynchronizationException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {

      Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
      DatabaseMetaData metaData = connection.getMetaData();
      createNonEmptyTable(stmt, metaData, "hdbtable-itest::adding-new-column-to-pk-list-hana");

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbtable-itest/adding-new-column-to-pk-list-hana.hdbtable",
          "/hdbtable-itest/adding-new-column-to-pk-list-hana.hdbtable");
      LogCaptor logCaptor = LogCaptor.forClass(XSKHDBCoreFacade.class);

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        String errMsg = "Incompatible change of table [\"hdbtable-itest::adding-new-column-to-pk-list-hana\"] by adding a column [Col67] which is [PRIMARY KEY]";
        assertTrue(logCaptor.getErrorLogs().stream().anyMatch(logMsg -> logMsg.equals(errMsg)));

      } finally {
        stmt.executeUpdate("drop table \"hdbtable-itest::adding-new-column-to-pk-list-hana\"");
        Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false");
      }


    }
  }

  @Test
  public void testHDBTableAlterWhenAddingNewNotNullableColumn()
      throws SQLException, IOException, XSKDataStructuresException, SynchronizationException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
      DatabaseMetaData metaData = connection.getMetaData();
      createNonEmptyTable(stmt, metaData, "hdbtable-itest::adding-new-not-nullable-column-hana");

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbtable-itest/adding-new-not-nullable-column-hana.hdbtable",
          "/hdbtable-itest/adding-new-not-nullable-column-hana.hdbtable");
      LogCaptor logCaptor = LogCaptor.forClass(XSKHDBCoreFacade.class);

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        String errMsg = "Incompatible change of table [\"hdbtable-itest::adding-new-not-nullable-column-hana\"] by adding a column [Col67] which is [NOT NULL]";
        assertTrue(logCaptor.getErrorLogs().stream().anyMatch(logMsg -> logMsg.equals(errMsg)));

      } finally {
        stmt.executeUpdate("drop table \"hdbtable-itest::adding-new-not-nullable-column-hana\"");
        Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false");
      }

    }
  }

  @Test
  public void testHDBTableAlterWhenExistingColumnTypeChange()
      throws SQLException, IOException, XSKDataStructuresException, SynchronizationException, ProblemsException {
    try (Connection connection = datasource.getConnection();
        Statement stmt = connection.createStatement()) {
      Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
      DatabaseMetaData metaData = connection.getMetaData();
      createNonEmptyTable(stmt, metaData, "hdbtable-itest::incompatible-column-type-change-hana");

      LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
          "/registry/public/hdbtable-itest/incompatible-column-type-change-hana.hdbtable",
          "/hdbtable-itest/incompatible-column-type-change-hana.hdbtable");
      LogCaptor logCaptor = LogCaptor.forClass(XSKHDBCoreFacade.class);

      facade.handleResourceSynchronization(resource);
      facade.updateEntities();
      try {
        String errMsg = "Incompatible change of table [\"hdbtable-itest::incompatible-column-type-change-hana\"] by adding a column [Col66] which is [of type NVARCHAR to be changed to BIGINT]";
        assertTrue(logCaptor.getErrorLogs().stream().anyMatch(logMsg -> logMsg.equals(errMsg)));

      } finally {
        stmt.executeUpdate("drop table \"hdbtable-itest::incompatible-column-type-change-hana\"");
        Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false");
      }


    }
  }

  private void createNonEmptyTable(Statement stmt, DatabaseMetaData metaData, String tableName) throws SQLException {
    String createTableSQL = String.format(
        "CREATE COLUMN TABLE \"DBADMIN\".\"%s\" ( \"Col1\" NVARCHAR (20) NOT NULL , \"Col2\" INTEGER  NOT\n"
            + " NULL , \"Col5\" NVARCHAR (20) DEFAULT 'Defaultvalue'  NOT NULL , \"Col66\" NVARCHAR (20) DEFAULT 'Defaultvalue'  , \"Col77\" NVARCHAR (20) DEFAULT 'Defaultvalue'  , PRIMARY KEY(\"Col1\" , "
            + "\"Col2\" , \"Col5\") , CONSTRAINT \"BB112\" UNIQUE ( \"Col2\" ), CONSTRAINT \"BB212\" UNIQUE ( \"Col1\" ))", tableName);
    stmt.executeUpdate(createTableSQL);
    ResultSet table = metaData
        .getTables(null, null, tableName, new String[]{ISqlKeywords.KEYWORD_TABLE});
    assertTrue(table.next());

    String insertOne = String.format("INSERT INTO \"DBADMIN\".\"%s\" \n"
        + "(\"Col1\", \"Col2\", \"Col5\", \"Col66\", \"Col77\")\n"
        + "VALUES('', 0, 'Defaultvalue', null, 'Defaultvalue');", tableName);
    String insertSecond = String.format("INSERT INTO \"DBADMIN\".\"%s\" \n"
        + "(\"Col1\", \"Col2\", \"Col5\", \"Col66\", \"Col77\")\n"
        + "VALUES('a', 1, 'Defaultvalue12', 'Defaultvalue', 'Defaultvalue');", tableName);
    String insertThird = String.format("INSERT INTO \"DBADMIN\".\"%s\" \n"
        + "(\"Col1\", \"Col2\", \"Col5\", \"Col66\", \"Col77\")\n"
        + "VALUES('b', 2, 'Defaultvalue12', 'Defaultvalue', 'Defaultvalue');", tableName);
    stmt.addBatch(insertOne);
    stmt.addBatch(insertSecond);
    stmt.addBatch(insertThird);
    stmt.executeBatch();
  }

}