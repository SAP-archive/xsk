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
import com.sap.xsk.hdb.ds.test.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.test.itest.hdbsequence.utils.TestContainerConstants;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.RepositoryPath;
import org.eclipse.dirigible.repository.fs.FileSystemRepository;
import org.eclipse.dirigible.repository.local.LocalRepository;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.After;
import org.junit.Before;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * XSKHDBSequenceParserITCase will test only sequence
 * creation due existing sequence drop logic that is never invoked.
 * Existing sequence alter logic is not checked due db limitations on retrieving sequence info.
 */
public class XSKHDBSequenceParserPostgreSQLITCase {

  private PostgreSQLContainer jdbcContainer;
  private Connection connection;
  private IXSKHDBCoreFacade facade;


  @Before
  public void setUp() throws SQLException {
    Network network = Network.newNetwork();
    jdbcContainer =
        new PostgreSQLContainer<>(TestContainerConstants.POSTGRESQL_DOCKER_IMAGE)
            .withNetwork(network)
            .withNetworkAliases(TestContainerConstants.POSTGRESQL_DOCKER_NETWORK_ALIAS);
    jdbcContainer.start();
    Injector injector = Guice.createInjector(new XSKHDBTestModule(jdbcContainer));
    this.connection = injector.getInstance(DataSource.class).getConnection();
    this.facade = injector.getInstance(Key.get(IXSKHDBCoreFacade.class, Names.named("xskHDBCoreFacade")));
  }

  @After
  public void cleanUp() {
    jdbcContainer.stop();
  }

  @Test
  public void testHDBSequenceCreate() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    final int EXPECTED_SEQUENCE_COUNT = 1;
    final String EXPECTED_SEQUENCE_NAME = "sequence-itest::SampleSequence_HanaXSClassic";
    FileSystemRepository fileRepo = new LocalRepository("/usr/local/target/dirigible/repository/root");
    RepositoryPath path = new RepositoryPath("/registry/public/sequence-itest/SampleSequence_HanaXSClassic.hdbsequence");
    byte[] content = XSKHDBSequenceParserPostgreSQLITCase.class
        .getResourceAsStream("/registry.public.sequence-itest/SampleSequence_HanaXSClassic.hdbsequence").readAllBytes();

    LocalResource resource = new LocalResource(fileRepo, path);
    resource.setContent(content);
    this.facade.handleResourceSynchronization(resource);
    this.facade.updateEntities();

    Statement stmt = connection.createStatement();
    List<String> dbSequences = new ArrayList<>();
    ResultSet rs = stmt.executeQuery("SELECT  relname sequence_name FROM  pg_class WHERE  relkind = 'S'");
    while (rs.next()) {
      dbSequences.add(rs.getString("sequence_name"));
    }
    assertEquals(EXPECTED_SEQUENCE_COUNT, dbSequences.size());
    assertEquals(EXPECTED_SEQUENCE_NAME, dbSequences.get(0));

    stmt.executeUpdate(String.format("DROP SEQUENCE \"%s\"", dbSequences.get(0)));
    rs = stmt.executeQuery("SELECT  relname sequence_name FROM  pg_class WHERE  relkind = 'S'");
    assertFalse(rs.next());
  }


}
