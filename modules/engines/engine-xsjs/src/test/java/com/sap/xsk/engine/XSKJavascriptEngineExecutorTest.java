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
package com.sap.xsk.engine;

import com.sap.xsk.XSJSTest;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.repository.api.IRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

public class XSKJavascriptEngineExecutorTest extends XSJSTest {

  @Before
  public void setup() {
    insertJSTestCodeInRepository("throwRegularRootLevelError.js");
    insertJSTestCodeInRepository("throwRegularErrorWithMissingStack.js");
    insertJSTestCodeInRepository("throwRegularNestedLevelError.js");
    insertJSTestCodeInRepository("throwNativeJavaException.js");
    insertJSTestCodeInRepository("throwJSObject.js");
  }

  @Test
  public void testJSErrorFileNamePolyfillWithRegularRootLevelError() {
    executeJSTest("throwRegularRootLevelError.js");
  }

  @Test
  public void testJSErrorFileNamePolyfillWithErrorWithNoStack() {
    executeJSTest("throwRegularErrorWithMissingStack.js");
  }

  @Test
  public void testJSErrorFileNamePolyfillWithRegularNestedLevelError() {
    executeJSTest("throwRegularNestedLevelError.js");
  }

  @Test
  public void testJSErrorFileNamePolyfillWithNativeJavaExceptionPropagation() {
    executeJSTest("throwNativeJavaException.js");
  }

  @Test
  public void testJSErrorFileNamePolyfillWithJSObjectThrownAsException() {
    executeJSTest("throwJSObject.js");
  }

  private static void executeJSTest(String testSourceFileName) {
    new XSKJavascriptEngineExecutor().executeServiceModule(testSourceFileName, new HashMap<>());
  }

  private static void insertJSTestCodeInRepository(String testFileName) {
      String jsTestCode = getJSTestCode(testFileName);
      IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
      repository.createResource("/registry/public/" + testFileName, jsTestCode.getBytes(StandardCharsets.UTF_8));
  }

  private static String getJSTestCode(String testFileName) {
    InputStream testFileStream = XSKJavascriptEngineExecutorTest.class
        .getResourceAsStream("/META-INF/dirigible/test/xsk/error_polyfills/" + testFileName);
    try {
      return IOUtils.toString(Objects.requireNonNull(testFileStream), StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new RuntimeException("Test file '" + testFileName + "' could not be loaded!", e);
    }
  }
}