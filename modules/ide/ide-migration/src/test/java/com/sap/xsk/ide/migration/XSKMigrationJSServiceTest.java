package com.sap.xsk.ide.migration;

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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;
import org.apache.cxf.helpers.IOUtils;
import org.eclipse.dirigible.commons.api.context.ContextException;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.extensions.api.ExtensionsException;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.engine.js.graalvm.processor.GraalVMJavascriptEngineExecutor;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IRepositoryStructure;
import org.eclipse.dirigible.repository.api.RepositoryWriteException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class XSKMigrationJSServiceTest extends AbstractDirigibleTest {

  private IRepository repository;

  private GraalVMJavascriptEngineExecutor graaljsJavascriptEngineExecutor;

  @Before
  public void setUp() throws Exception {
    this.repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
    this.graaljsJavascriptEngineExecutor = new GraalVMJavascriptEngineExecutor();
  }

  @Test
  public void runMigrationTest()
      throws RepositoryWriteException, IOException, ScriptingException, ContextException, ExtensionsException, URISyntaxException {

    String neoPath = getAbsolutePath("META-INF/dirigible/ide-migration/server/migration/neo.sh");
    String targetPath = System.getProperty("user.dir") + "/target/";
    String neoClientPath = targetPath + "../../../../resources/resources-neo-sdk/src/main/resources/META-INF/dirigible/resources-neo-sdk/neo-sdk/tools/neo.sh";

    assertNotNull(Configuration.get("ACCOUNT"));
    assertNotNull(Configuration.get("HOST"));
    assertNotNull(Configuration.get("USER"));
    assertNotNull(Configuration.get("PASSWORD"));
    assertNotNull(Configuration.get("DB"));
    assertNotNull(Configuration.get("CUSER"));
    assertNotNull(Configuration.get("HANA_PASSWORD"));

    Map context = Map.ofEntries(
        Map.entry("__async_callback", new AssertHolder()),
        Map.entry("__neo_path", neoPath),
        Map.entry("__neo_client_path", neoClientPath),
        Map.entry("__account", Configuration.get("ACCOUNT")),
        Map.entry("__host", Configuration.get("HOST")),
        Map.entry("__user", Configuration.get("USER")),
        Map.entry("__password", Configuration.get("PASSWORD")),
        Map.entry("__db", Configuration.get("DB")),
        Map.entry("__cuser", Configuration.get("CUSER")),
        Map.entry("__hana_pass", Configuration.get("HANA_PASSWORD"))
    );

    runTest(this.graaljsJavascriptEngineExecutor, repository, "META-INF/dirigible/ide-migration/server/test/migrate.js", context);
  }

  private Object runTest(GraalVMJavascriptEngineExecutor executor, IRepository repository, String testModule, Map<Object, Object> context)
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

    Object result = executor.executeServiceModule(testModule, context);
    long time = System.currentTimeMillis() - start;
    System.out.printf("Migration test [%s] on engine [%s] passed for: %d ms%n", testModule, executor.getType(), time);
    return result;
  }

  private String getAbsolutePath(String resourcePath) throws URISyntaxException, IOException {

    URL res = getClass().getClassLoader().getResource(resourcePath);
    File file = Paths.get(res.toURI()).toFile();
    String absolutePath = file.getAbsolutePath();
    return absolutePath;
  }
}