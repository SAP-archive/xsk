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
package com.sap.xsk.hdb.ds.test.itest.hdbsequence;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.test.itest.module.XSKHDBTestContainersModule;
import com.sap.xsk.hdb.ds.test.itest.utils.TestContainerConstants;
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

import static com.sap.xsk.hdb.ds.test.itest.utils.TestContainerConstants.POSTGRESQL_ROOT_FOLDER;
import static com.sap.xsk.hdb.ds.test.itest.utils.TestContainerConstants.POSTGRESQL_REPO_PATH;
import static com.sap.xsk.hdb.ds.test.itest.utils.TestContainerConstants.POSTGRESQL_RELATIVE_RESOURCES_PATH;
import static com.sap.xsk.hdb.ds.test.itest.utils.TestContainerConstants.POSTGRESQL_EXPECTED_SEQUENCE_COUNT;
import static com.sap.xsk.hdb.ds.test.itest.utils.TestContainerConstants.POSTGRESQL_EXPECTED_SEQUENCE_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * XSKHDBSequenceParserITCase will test only sequence
 * creation due existing sequence drop logic that is never invoked.
 * Existing sequence alter logic is not checked due db limitations on retrieving sequence info.
 */
public class XSKHDBSequenceParserPostgreSQLITCase {

  private static PostgreSQLContainer jdbcContainer;
  private static Connection connection;
  private static IXSKHDBCoreFacade facade;


  @BeforeClass
  public static void setUp() throws SQLException {
    Network network = Network.newNetwork();
    jdbcContainer =
        new PostgreSQLContainer<>(TestContainerConstants.POSTGRESQL_DOCKER_IMAGE)
            .withNetwork(network)
            .withNetworkAliases(TestContainerConstants.POSTGRESQL_DOCKER_NETWORK_ALIAS);
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
  public void testHDBSequenceCreate() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    LocalResource resource = XSKHDBTestContainersModule.getResources( POSTGRESQL_ROOT_FOLDER,
                                                                      POSTGRESQL_REPO_PATH,
                                                                      POSTGRESQL_RELATIVE_RESOURCES_PATH);

    this.facade.handleResourceSynchronization(resource);
    this.facade.updateEntities();

    Statement stmt = connection.createStatement();
    List<String> dbSequences = new ArrayList<>();
    ResultSet rs = stmt.executeQuery("SELECT  relname sequence_name FROM  pg_class WHERE  relkind = 'S'");
    while (rs.next()) {
      dbSequences.add(rs.getString("sequence_name"));
    }
    assertEquals(POSTGRESQL_EXPECTED_SEQUENCE_COUNT, dbSequences.size());
    assertEquals(POSTGRESQL_EXPECTED_SEQUENCE_NAME, dbSequences.get(0));

    stmt.executeUpdate(String.format("DROP SEQUENCE \"%s\"", dbSequences.get(0)));
    rs = stmt.executeQuery("SELECT  relname sequence_name FROM  pg_class WHERE  relkind = 'S'");
    assertFalse(rs.next());
  }


}
