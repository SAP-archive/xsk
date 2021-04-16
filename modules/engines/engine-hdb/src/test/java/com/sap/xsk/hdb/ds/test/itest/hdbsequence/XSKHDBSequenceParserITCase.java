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
import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import com.sap.xsk.hdb.ds.test.itest.hdbsequence.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.test.parser.XSKViewParserTest;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.eclipse.dirigible.repository.api.RepositoryPath;
import org.eclipse.dirigible.repository.fs.FileSystemRepository;
import org.eclipse.dirigible.repository.local.LocalRepository;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.Test;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class XSKHDBSequenceParserITCase {

  private Connection connection;
  private IXSKHDBCoreFacade facade;
  private PersistenceManager<XSKDataStructureHDBSequenceModel> sequenceManager = new PersistenceManager<>();

  @Before
  public void setUp() throws SQLException {
    Injector injector = Guice.createInjector(new XSKHDBTestModule());
    this.connection = injector.getInstance(DataSource.class).getConnection();
    this.facade = injector.getInstance(Key.get(IXSKHDBCoreFacade.class, Names.named("xskHDBCoreFacade")));
  }

  @Test
  public void testHDBSequenceCreate() throws XSKDataStructuresException, SynchronizationException, IOException {
    FileSystemRepository fileRepo = new LocalRepository("/usr/local/target/dirigible/repository/root");
    RepositoryPath path = new RepositoryPath("/registry/public/sequence-itest/SampleSequence_HanaXSClassic.hdbsequence");
    byte[] content = XSKHDBSequenceParserITCase.class
        .getResourceAsStream("/registry.public.sequence-itest/SampleSequence_HanaXSClassic.hdbsequence").readAllBytes();

    LocalResource resource = new LocalResource(fileRepo, path);
    resource.setContent(content);
    this.facade.handleResourceSynchronization(resource);
    this.facade.updateEntities();
    List<XSKDataStructureHDBSequenceModel> students = sequenceManager.findAll(connection, XSKDataStructureHDBSequenceModel.class);
    students.size();
  }
}
