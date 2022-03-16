package com.sap.xsk.engine;

import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.*;

public class XSKJavascriptEngineExecutorTest extends AbstractDirigibleTest {

  @Test
  public void testJSErrorFileNamePolyfillWithRegularRootLevelError() {
    String source = getJSTestCode("throwRegularRootLevelError.js");
    executeJSTest(source);
  }

  @Test
  public void testJSErrorFileNamePolyfillWithErrorWithNoStack() {
    String source = getJSTestCode("throwRegularErrorWithMissingStack.js");
    executeJSTest(source);
  }

  @Test
  public void testJSErrorFileNamePolyfillWithRegularNestedLevelError() {
    String source = getJSTestCode("throwRegularNestedLevelError.js");
    executeJSTest(source);
  }

  @Test
  public void testJSErrorFileNamePolyfillWithNativeJavaExceptionPropagation() {
    String source = getJSTestCode("throwNativeJavaException.js");
    executeJSTest(source);
  }

  @Test
  public void testJSErrorFileNamePolyfillWithJSObjectThrownAsException() {
    String source = getJSTestCode("throwJSObject.js");
    executeJSTest(source);
  }

  private static void executeJSTest(String testSource) {
    new XSKJavascriptEngineExecutor().executeServiceCode(testSource, new HashMap<>());
  }

  private static String getJSTestCode(String testFileName) {
    InputStream testFileStream = XSKJavascriptEngineExecutorTest.class.getResourceAsStream("/META-INF/dirigible/test/xsk/error_polyfills/" + testFileName);
    try {
      return IOUtils.toString(Objects.requireNonNull(testFileStream), StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new RuntimeException("Test file '" + testFileName + "' could not be loaded!", e);
    }
  }
}