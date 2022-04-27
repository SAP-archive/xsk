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

import com.sap.xsk.engine.XSKJavascriptEngineExecutor;
import com.sap.xsk.exceptions.XSJSLibArtefactCleanerSQLException;
import com.sap.xsk.exceptions.XSJSLibExportsGenerationSourceNotFoundException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.ISchedulerCoreService;
import org.eclipse.dirigible.core.scheduler.service.definition.JobDefinition;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.engine.js.graalvm.processor.GraalVMJavascriptEngineExecutor;
import org.eclipse.dirigible.repository.api.IEntity;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.local.LocalRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class ExportGenerationTest extends AbstractDirigibleTest {

  private static final Logger logger = LoggerFactory.getLogger(ExportGenerationTest.class);

  public ExportGenerationTest() {
    // should be executed before parent @Before method as parent would otherwise initialize the DB in a persistent way
    Configuration.set("DIRIGIBLE_DATABASE_H2_URL", "jdbc:h2:mem:xsk-tests");
  }

  @Before
  public void beforeTest() {
    setUpRepository();
    cleanup();
  }

  @After
  public void afterTest() {
    cleanup();
  }

  @Test
  public void synchronizerTest() {
    logger.info("XSJSLibSynchronizer test starting... ");

    XSJSLibSynchronizer synchronizer = new XSJSLibSynchronizer();
    assertEquals("Unexpected XSJSLibSynchronizer Priority",
        666, synchronizer.getPriority());

    logger.info("XSJSLibSynchronizer test passed successfully.");
  }

  @Test
  public void synchronizerJobTest() {
    logger.info("XSJSLibSynchronizerJob test starting... ");

    XSJSLibSynchronizerJob job = new XSJSLibSynchronizerJob();

    assertEquals("Unexpected XSJSLib Job Name",
        "XSK XSJSLib Synchronizer Job", job.getName());

    assertEquals("Unexpected XSJSLib Job Synchronizer",
        XSJSLibSynchronizer.class, job.getSynchronizer().getClass());

    logger.info("XSJSLibSynchronizerJob test passed successfully.");
  }

  @Test
  public void synchronizerJobDefinitionTest() {
    logger.info("XSJSLibSynchronizerJobDefinition test starting... ");

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

    logger.info("XSJSLibSynchronizerJobDefinition test passed successfully.");
  }

  @Test
  public void artefactCleanerTest() {
    logger.info("XSJSLibArtefactStateCleaner test starting... ");

    runJs("/test/xsk/exports/utils/createTableHelper.mjs"); // create a state table with an entry

    XSJSLibSynchronizerArtefactsCleaner cleaner = new XSJSLibSynchronizerArtefactsCleaner();
    cleaner.cleanup("bbb/");

    DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);
    try (PreparedStatement selectStatement = dataSource.getConnection()
        .prepareStatement(
            "SELECT FROM \""
                + XSJSLibSynchronizer.XSJSLIB_SYNCHRONIZER_STATE_TABLE_NAME
                + "\" WHERE \"LOCATION\" LIKE 'bbb/'")
    ) {
      ResultSet result = selectStatement.executeQuery();
      assertNotNull("Unexpected null result set after state table cleanup", result);

      result.last();
      int entries = result.getRow();
      assertEquals("Unexpected count of entries after state table cleanup", 0, entries);
    } catch (SQLException e) {
      throw new XSJSLibArtefactCleanerSQLException("Could not cleanup xsjslib synchronizer entries. ", e);
    }

    logger.info("XSJSLibArtefactStateCleaner test passed successfully.");
  }

  @Test
  public void exportGenerationSourceNotFoundExceptionTest() {
    try {
      throw new XSJSLibExportsGenerationSourceNotFoundException("test");
    } catch (XSJSLibExportsGenerationSourceNotFoundException exception) {
      assertEquals("Unexpected exception message", "test", exception.getMessage());
    }

    try {
      throw new XSJSLibExportsGenerationSourceNotFoundException(new RuntimeException("test"));
    } catch (XSJSLibExportsGenerationSourceNotFoundException exception) {
      assertEquals("Unexpected exception cause", RuntimeException.class, exception.getCause().getClass());
    }
  }

  @Test
  public void artefactStateExceptionTest() {
    try {
      throw new XSJSLibArtefactCleanerSQLException("test", new RuntimeException("test2"));
    } catch (XSJSLibArtefactCleanerSQLException exception) {
      assertEquals("Unexpected exception message", "test", exception.getMessage());
      assertEquals("Unexpected exception cause", RuntimeException.class, exception.getCause().getClass());
    }
  }

  @Test
  public void contentModifierTest() throws ScriptingException {
    runJsTest("/test/xsk/exports/contentModifierTest.mjs");
  }

  @Test
  @Parameters({
      "/test/xsk/exports/stateTableWriteTest.mjs",
      "/test/xsk/exports/stateTableUpdateTest.mjs",
      "/test/xsk/exports/stateTableFindTest.mjs",
      "/test/xsk/exports/stateTableCheckContentChangeTest.mjs"
  })
  public void stateTableTest(String testModule) throws ScriptingException {
    runJsTest(testModule);
  }

  @Test
  @Parameters({
      "/test/xsk/exports/singleFileExportGenerationTest.mjs",
      "/test/xsk/exports/singleFileExportUpdateTest.mjs",
      "/test/xsk/exports/multiFileExportGeneration.mjs",
  })
  public void exportsGeneratorTest(String testModule) throws ScriptingException {
    runJsTest(testModule);
  }

  @Test
  public void importTest() throws ScriptingException {
    logger.info("XSJSLib Import test starting... ");

    XSJSLibSynchronizer.forceSynchronization("../../test/xsk/import/");

    Map<Object, Object> context = new HashMap<>();
    XSKJavascriptEngineExecutor xskJavascriptEngineExecutor = new XSKJavascriptEngineExecutor();
    Object result = xskJavascriptEngineExecutor.executeServiceModule(
        "/test/xsk/import/import.xsjs",
        context
    );

    assertNull("Unexpected xsjs execution result for import.xsjs", result);

    logger.info("XSJSLib Import test passed successfully. ");
  }

  private void runJsTest(String testModule) {
    logger.info("XSJSLib Export js test starting(" + testModule + ")");

    Object executionResult = runJs(testModule);
    assertNull(
        "XSJSLib Export js test unexpected js execution result for " + testModule,
        executionResult
    );

    logger.info("XSJSLib Export js test passed successfully: " + testModule);
  }

  private Object runJs(String testModule) throws ScriptingException {
    Map<Object, Object> context = new HashMap<>();
    GraalVMJavascriptEngineExecutor graalVMJavascriptEngineExecutor = new GraalVMJavascriptEngineExecutor();
    return graalVMJavascriptEngineExecutor.executeService(
        testModule,
        context,
        true,
        false
    );
  }

  private void cleanup() {
    logger.info("Cleaning up tables and repository after test execution.");

    cleanupRepository();
    dropTableIfExists(XSJSLibSynchronizer.XSJSLIB_SYNCHRONIZER_STATE_TABLE_NAME);
    dropTableIfExists("XSJSLIB_EXPORT_TEST_TABLE");
  }

  private void setUpRepository() {
    String rootFolder = "target/test-classes/META-INF/";
    IRepository repository = new LocalRepository(rootFolder, false);
    StaticObjects.set(StaticObjects.REPOSITORY, repository);
  }

  private void cleanupRepository() {
    IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
    repository.getRoot().getResources().forEach(IEntity::delete);
    repository.getRoot().getCollections().forEach(IEntity::delete);
  }

  private void dropTableIfExists(String tableName) {
    DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);
    try (Connection connection = dataSource.getConnection();
        PreparedStatement dropStatement = connection.prepareStatement(
            "DROP TABLE \"" + tableName + "\""
        )
    ) {
      if (tableExists(tableName, connection)) {
        dropStatement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new XSJSLibArtefactCleanerSQLException("Could not drop table after test", e);
    }
  }

  private boolean tableExists(String tableName, Connection connection) throws SQLException {
    ResultSet resultSet = connection.getMetaData().getTables(
        null,
        null,
        tableName,
        null
    );
    return resultSet.next();
  }
}
