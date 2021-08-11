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
package com.sap.xsk.migration.api;

import com.sap.xsk.engine.XSKJavascriptEngineExecutor;
import org.apache.cxf.helpers.IOUtils;
import org.eclipse.dirigible.commons.api.context.ContextException;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.extensions.api.ExtensionsException;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IRepositoryStructure;
import org.eclipse.dirigible.repository.api.RepositoryWriteException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.io.InputStream;

public class XSKMigrationJSServiceTest extends AbstractDirigibleTest {

  private IRepository repository;

  private XSKJavascriptEngineExecutor graaljsJavascriptEngineExecutor;

  @Before
  public void setUp() throws Exception {
    this.repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
    this.graaljsJavascriptEngineExecutor = new XSKJavascriptEngineExecutor();
  }

  @Test
  public void runMigrationTest()
      throws RepositoryWriteException, IOException, ScriptingException, ContextException, ExtensionsException {
    Object result = runTest(this.graaljsJavascriptEngineExecutor, repository, "test/migration.js");
    assertTrue((Boolean)result);
  }

  private Object runTest(XSKJavascriptEngineExecutor executor, IRepository repository, String testModule)
      throws IOException, ScriptingException {

    try (InputStream in = XSKMigrationJSServiceTest.class.getResourceAsStream(IRepositoryStructure.SEPARATOR + testModule)) {
      if (in == null) {
        throw new IOException(IRepositoryStructure.SEPARATOR + testModule + " does not exist");
      }
      repository.createResource(IRepositoryStructure.PATH_REGISTRY_PUBLIC + IRepositoryStructure.SEPARATOR + testModule,
          IOUtils.readBytesFromStream(in));

    } catch (RepositoryWriteException e) {
      throw new IOException(IRepositoryStructure.SEPARATOR + testModule, e);
    }

    long start = System.currentTimeMillis();
    Object result = executor.executeServiceModule(testModule, null);
    long time = System.currentTimeMillis() - start;
    System.out.printf("Migration test [%s] on engine [%s] passed for: %d ms%n", testModule, executor.getType(), time);
    return result;
  }
}