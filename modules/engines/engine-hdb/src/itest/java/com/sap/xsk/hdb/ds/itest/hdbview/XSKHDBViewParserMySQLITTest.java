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

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.facade.XSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.assertTrue;

public class XSKHDBViewParserMySQLITTest {

  private static MySQLContainer jdbcContainer;
  private static DataSource datasource;
  private static IXSKHDBCoreFacade facade;


  @BeforeClass
  public static void setUp() throws SQLException {
    jdbcContainer =
        new MySQLContainer<>("mysql:5.7");
    jdbcContainer.start();
    JDBCModel model = new JDBCModel(jdbcContainer.getDriverClassName(), jdbcContainer.getJdbcUrl(), jdbcContainer.getUsername(),
        jdbcContainer.getPassword());
    XSKHDBTestModule xskhdbTestModule = new XSKHDBTestModule(model);
    xskhdbTestModule.configure();
    datasource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);
    facade = new XSKHDBCoreFacade();
  }

  @Before
  public void setUpBeforeTest() throws SQLException {
    Configuration.set(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true");
    facade.clearCache();
  }

  @Test
  public void testHDBViewCreate() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
	  try (Connection connection = datasource.getConnection();
	  			Statement stmt = connection.createStatement()) {
	    stmt.executeUpdate("create table `test`.`acme.com.test.tables::MY_TABLE1`(Column1 integer,Column2 integer)");
	    stmt.executeUpdate("create table `test`.`acme.com.test.views::MY_VIEW1`(Column1 integer,Column2 integer)");
	
	    LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
	        "/registry/public/hdbview-itest/SampleMySQLXSClassicView.hdbview",
	        "/hdbview-itest/SampleMySQLXSClassicView.hdbview");
	
	    facade.handleResourceSynchronization(resource);
	    facade.updateEntities();
	
	    DatabaseMetaData metaData = connection.getMetaData();
	    ResultSet table = metaData.getTables(null, "`test`", "`hdbview-itest::SampleMySQLXSClassicView`", new String[] {"VIEW"});
	    assertTrue(table.next());
	
	    stmt.executeUpdate(String.format("DROP VIEW %s", "`hdbview-itest::SampleMySQLXSClassicView`"));
	    stmt.executeUpdate("drop table `test`.`acme.com.test.tables::MY_TABLE1`");
	    stmt.executeUpdate("drop table `test`.`acme.com.test.views::MY_VIEW1`");
	  }
  }
}
