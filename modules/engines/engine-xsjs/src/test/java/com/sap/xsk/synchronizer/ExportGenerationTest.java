/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.synchronizer;

import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.ISchedulerCoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.engine.js.graalvm.processor.GraalVMJavascriptEngineExecutor;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.local.LocalRepository;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExportGenerationTest extends AbstractDirigibleTest {

  private static final Logger logger = LoggerFactory.getLogger(ExportGenerationTest.class);

  @Before
  public void setUp() {
    String rootFolder = "target/test-classes/";
    IRepository repository = new LocalRepository(rootFolder, false);
    StaticObjects.set(StaticObjects.REPOSITORY, repository);
  }

  @Test
  public void synchronizerJobTest() {
    XSJSLibSynchronizerJob job = new XSJSLibSynchronizerJob();

    assertEquals("Unexpected XSJSLib Job Name",
        "XSK XSJSLib Synchronizer Job", job.getName());

    assertEquals("Unexpected XSJSLib Job Synchronizer",
        XSJSLibSynchronizer.class, job.getSynchronizer().getClass());
  }

  @Test
  public void synchronizerJobDefinitionTest() {
    XSJSLibSynchronizerJob job = new XSJSLibSynchronizerJob();
    JobDefinition jobDefinition = job.getJobDefinition();

    assertEquals("Unexpected XSJSLib Job Definition Name",
        "dirigible-internal-xsk-xsjslib-synchronizer-job", jobDefinition.getName());

    assertEquals("Unexpected XSJSLib Job Definition Group",
        ISchedulerCoreService.JOB_GROUP_INTERNAL, jobDefinition.getGroup());

    assertEquals("Unexpected XSJSLib Job Definition Clazz",
        XSJSLibSynchronizerJob.class.getCanonicalName(), jobDefinition.getClazz());

    assertEquals("Unexpected XSJSLib Job Definition Description",
        "XSK XSJSLib Synchronizer Job", jobDefinition.getDescription());

    assertEquals("Unexpected XSJSLib Job Definition Expression",
        "0/55 * * * * ?", jobDefinition.getExpression());

    assertTrue("Unexpected XSJSLib Job Definition Singleton Flag",
        jobDefinition.isSingleton());
  }

  @Test
  public void contentModifierTest() throws ScriptingException {
    runJsTest("/test/xsk/exports/contentModifierTest.mjs");
  }

  @Test
  public void stateTableTest() throws ScriptingException {
    runJsTest("/test/xsk/exports/stateTableTest.mjs");
  }

  @Test
  public void exportsGeneratorTest() throws ScriptingException {
    runJsTest("/test/xsk/exports/exportsGeneratorTest.mjs");
  }

  private void runJsTest(String testModule) throws ScriptingException {
    logger.info("XSJSLib Export js test starting... " + testModule);
    Map<Object, Object> context = new HashMap<>();
    GraalVMJavascriptEngineExecutor graalVMJavascriptEngineExecutor = new GraalVMJavascriptEngineExecutor();
    graalVMJavascriptEngineExecutor.executeService(
        testModule,
        context,
        true,
        false
    );
    logger.info("XSJSLib Export js test passed successfully: " + testModule);
  }
}
