/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.test.itest.hdbtable;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.test.itest.module.XSKHDBTestContainersModule;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.sap.xsk.hdb.ds.test.itest.utils.TestContainerConstants.*;
import static com.sap.xsk.hdb.ds.test.itest.utils.TestContainerConstants.HDBSEQUENCE_POSTGRESQL_RELATIVE_RESOURCES_PATH;
import static org.junit.Assert.*;

public class XSKHDBTableParserPostgreSQLITCase {

  private static PostgreSQLContainer jdbcContainer;
  private static Connection connection;
  private static IXSKHDBCoreFacade facade;


  @BeforeClass
  public static void setUp() throws SQLException {
    Network network = Network.newNetwork();
    jdbcContainer =
        new PostgreSQLContainer<>(HDBTABLE_POSTGRESQL_DOCKER_IMAGE);
    jdbcContainer.start();
    Injector injector = Guice.createInjector(new XSKHDBTestContainersModule(jdbcContainer));
    connection = injector.getInstance(DataSource.class).getConnection();
    facade = injector.getInstance(Key.get(IXSKHDBCoreFacade.class, Names.named("xskHDBCoreFacade")));
  }

  @AfterClass
  public static void cleanUp() {
    jdbcContainer.stop();
  }

  @Test
  public void testHDBTableCreate() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    LocalResource resource = XSKHDBTestContainersModule.getResources(HDBTABLE_POSTGRESQL_ROOT_FOLDER,
        HDBTABLE_POSTGRESQL_REPO_PATH,
        HDBTABLE_POSTGRESQL_RELATIVE_RESOURCES_PATH);

    this.facade.handleResourceSynchronization(resource);
    this.facade.updateEntities();

    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery(String.format("SELECT COUNT(*) as rawsCount FROM \"%s\"", HDBTABLE_POSTGRESQL_EXPECTED_TABLE_NAME));
    assertTrue(rs.next());
    assertEquals(HDBTABLE_POSTGRESQL_EXPECTED_CREATED_RAWS_COUNT, rs.getInt("rawsCount"));
  }
}
