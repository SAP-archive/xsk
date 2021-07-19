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

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.itest.model.JDBCModel;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class XSKHDBTableFunctionPostgreSQLITTest {
  private static PostgreSQLContainer jdbcContainer;
  private static Connection connection;
  private static IXSKHDBCoreFacade facade;


  @BeforeClass
  public static void setUp() throws SQLException {
    jdbcContainer =
        new PostgreSQLContainer<>("postgres:alpine");
    jdbcContainer.start();
    JDBCModel model = new JDBCModel(jdbcContainer.getDriverClassName(), jdbcContainer.getJdbcUrl(), jdbcContainer.getUsername(),
        jdbcContainer.getPassword());
    Injector injector = Guice.createInjector(new XSKHDBTestModule(model));
    connection = injector.getInstance(DataSource.class).getConnection();
    facade = injector.getInstance(Key.get(IXSKHDBCoreFacade.class, Names.named("xskHDBCoreFacade")));
  }

  @AfterClass
  public static void cleanUp() {
    jdbcContainer.stop();
  }

  @Test(expected = IllegalStateException.class)
  public void testHDBTableFunctionCreateNotSupportedError() throws IOException, XSKDataStructuresException, SynchronizationException, SQLException {
    Statement stmt = connection.createStatement();

    stmt.executeUpdate("create table \"public\".\"hdbtablefunction-itest::SampleHanaTable\"(COLUMN1 integer,COLUMN2 integer)");
    LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
        "/registry/public/hdbtablefunction-itest/SampleHanaTableFunction.hdbtablefunction",
        "/hdbtablefunction-itest/SampleHanaTableFunction.hdbtablefunction");

    this.facade.handleResourceSynchronization(resource);
    this.facade.updateEntities();

  }

}
