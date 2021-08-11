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

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.facade.XSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.assertTrue;

public class XSKHDBTableParserMySQLITTest {

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

  @Test
  public void testHDBTableCreate() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
	  try (Connection connection = datasource.getConnection();
	  			Statement stmt = connection.createStatement()) {
	    LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
	        "/registry/public/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable",
	        "/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable");
	
	    facade.handleResourceSynchronization(resource);
	    facade.updateEntities();
	
	    DatabaseMetaData metaData = connection.getMetaData();
	    ResultSet table = metaData.getTables(null, "test", "hdbtable-itest::SamplePostgreXSClassicTable", null);
	    assertTrue(table.next());
	
	    stmt.executeUpdate("DROP TABLE `test`.`hdbtable-itest::SamplePostgreXSClassicTable`");
	  }
  }
}
