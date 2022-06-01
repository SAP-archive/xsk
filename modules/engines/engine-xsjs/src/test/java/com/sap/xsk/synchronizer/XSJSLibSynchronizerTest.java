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

import com.sap.xsk.XSJSTest;
import com.sap.xsk.engine.XSKJavascriptEngineExecutor;
import com.sap.xsk.exceptions.XSJSLibSynchronizerDBCleanerSQLException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.engine.js.graalvm.processor.GraalVMJavascriptEngineExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnitParamsRunner.class)
public class XSJSLibSynchronizerTest extends XSJSTest {

  @Before
  public void beforeTest() {
    cleanup();
  }

  @After
  public void afterTest() {
    cleanup();
  }

  private void cleanup() {
    dropTableIfExists(XSJSLibSynchronizer.XSJSLIB_SYNCHRONIZER_STATE_TABLE_NAME);
    dropTableIfExists("XSJSLIB_EXPORT_TEST_TABLE");
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
      throw new XSJSLibSynchronizerDBCleanerSQLException("Could not drop table after test", e);
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

  @Test
  public void synchronizerGetPriorityTest() {
    XSJSLibSynchronizer synchronizer = new XSJSLibSynchronizer();
    assertEquals("Unexpected XSJSLibSynchronizer Priority",
        666, synchronizer.getPriority());
  }

  @Test
  public void synchronizeTest() throws ScriptingException {
    XSJSLibSynchronizer.forceSynchronization("../../test/xsk/import/"); // look two directories back as the test resources are outside the repository root

    Map<Object, Object> context = new HashMap<>();
    XSKJavascriptEngineExecutor xskJavascriptEngineExecutor = new XSKJavascriptEngineExecutor();
    Object result = xskJavascriptEngineExecutor.executeServiceModule(
        "/test/xsk/import/import.xsjs",
        context
    );

    assertNull("Unexpected xsjs execution result for import.xsjs", result);
  }

  @Test
  @Parameters({
      "/test/xsk/exports/tests/XSJSLibStateTableWriteTest.mjs",
      "/test/xsk/exports/tests/XSJSLibStateTableUpdateTest.mjs",
      "/test/xsk/exports/tests/XSJSLibStateTableFindTest.mjs",
      "/test/xsk/exports/tests/XSJSLibExportsGeneratorIsContentChangedTest.mjs",
      "/test/xsk/exports/tests/XSJSLibParserTest.mjs",
      "/test/xsk/exports/tests/XSJSLibExportGeneratorSingleFileTest.mjs",
      "/test/xsk/exports/tests/XSJSLibExportGeneratorSingleFileModifyTest.mjs",
      "/test/xsk/exports/tests/XSJSLibExportGeneratorMultiFileTest.mjs",
  })
  public void exportsGenerationJsTest(String testModule) throws ScriptingException {
    runJsTest(testModule);
  }

  private void runJsTest(String testModule) {
    Object executionResult = runJs(testModule);
    assertNull(
        "XSJSLib Export js test unexpected js execution result for " + testModule,
        executionResult
    );
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
}
