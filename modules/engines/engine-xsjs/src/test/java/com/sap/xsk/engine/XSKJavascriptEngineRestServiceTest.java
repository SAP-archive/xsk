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

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.ibatis.scripting.ScriptingException;
import org.eclipse.dirigible.commons.api.context.ContextException;
import org.eclipse.dirigible.commons.api.context.ThreadContextFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(JUnitParamsRunner.class)
public class XSKJavascriptEngineRestServiceTest {

  private static final String TEST_RESOURCE_NAME = "/test.js";

  @Test
  public void getTypeTest() {
    XSKJavascriptEngineRestService restService = new XSKJavascriptEngineRestService();
    assertEquals(XSKJavascriptEngineRestService.class, restService.getType());
  }

  @Test
  public void getLoggerTest() {
    XSKJavascriptEngineRestService restService = new XSKJavascriptEngineRestService();
    assertNotNull(restService.getLogger());
  }

  private Response selectAndExecuteMethod(
      XSKJavascriptEngineRestService restService,
      HttpServletRequest servletRequest,
      String requestType
  ) throws ContextException {
    switch (requestType) {
      case "GET":
        return restService.get(servletRequest, TEST_RESOURCE_NAME);
      case "PUT":
        return restService.put(servletRequest, TEST_RESOURCE_NAME);
      case "HEAD":
        return restService.head(servletRequest, TEST_RESOURCE_NAME);
      case "DELETE":
        return restService.delete(servletRequest, TEST_RESOURCE_NAME);
      case "PATCH":
        return restService.patch(servletRequest, TEST_RESOURCE_NAME);
      case "POST":
        return restService.post(servletRequest, TEST_RESOURCE_NAME);
      default:
        throw new RuntimeException("Unexpected request type '" + requestType + "'");
    }
  }

  @Test
  @Parameters({"GET", "PUT", "POST", "PATCH", "DELETE", "HEAD"})
  public void executeServicePostTest(String requestType) throws ContextException {
    XSKJavascriptEngineProcessor mockProcessor = Mockito.mock(XSKJavascriptEngineProcessor.class);
    HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);

    try (MockedStatic<ThreadContextFacade> mockedThreadContextFacade = mockStatic(ThreadContextFacade.class)) {
      XSKJavascriptEngineRestService restService = new XSKJavascriptEngineRestService(mockProcessor);
      Response response = selectAndExecuteMethod(restService, mockRequest, requestType);

      mockedThreadContextFacade.verify(ThreadContextFacade::setUp, times(1));
      mockedThreadContextFacade.verify(
          () -> ThreadContextFacade.set(
              HttpServletRequest.class.getCanonicalName(),
              mockRequest
          ), times(1)
      );
      verify(mockProcessor, times(1)).executeService(TEST_RESOURCE_NAME);
      assertEquals(Status.OK, response.getStatusInfo().toEnum());
    }
  }

  @Test(expected = ContextException.class)
  @Parameters({"GET", "PUT", "POST", "PATCH", "DELETE", "HEAD"})
  public void executeServicePostWithThreadContextExceptionTest(String requestType) throws ContextException {
    XSKJavascriptEngineProcessor mockProcessor = Mockito.mock(XSKJavascriptEngineProcessor.class);
    HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);

    try (MockedStatic<ThreadContextFacade> mockedThreadContextFacade = mockStatic(ThreadContextFacade.class)) {
      mockedThreadContextFacade.when(
          () -> ThreadContextFacade.set(
              HttpServletRequest.class.getCanonicalName(),
              mockRequest
          )
      ).thenThrow(new ContextException());

      XSKJavascriptEngineRestService restService = new XSKJavascriptEngineRestService(mockProcessor);
      selectAndExecuteMethod(restService, mockRequest, requestType);
    }
  }

  @Test(expected = ScriptingException.class)
  @Parameters({"GET", "PUT", "POST", "PATCH", "DELETE", "HEAD"})
  public void executeServicePostWithOtherExceptionTest(String requestType) throws ContextException {
    XSKJavascriptEngineProcessor mockProcessor = Mockito.mock(XSKJavascriptEngineProcessor.class);
    HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
    doThrow(new ScriptingException()).when(mockProcessor).executeService(TEST_RESOURCE_NAME);

    try (MockedStatic<ThreadContextFacade> mockedThreadContextFacade = mockStatic(ThreadContextFacade.class)) {
      XSKJavascriptEngineRestService restService = new XSKJavascriptEngineRestService(mockProcessor);
      selectAndExecuteMethod(restService, mockRequest, requestType);
    }
  }

  @Test
  public void assertServiceClassHasCorrectAnnotations() {
    Class<XSKJavascriptEngineRestService> serviceClass = XSKJavascriptEngineRestService.class;
    Path pathAnnotation = serviceClass.getAnnotation(Path.class);
    assertNotNull("Class does not have a path annotation", pathAnnotation);
    assertEquals("Class does not have correct path", "/xsk", pathAnnotation.value());
  }

  @Test
  public void assertGetMethodHasCorrectAnnotations() throws NoSuchMethodException {
    assertMethodHasCorrectAnnotations("get", GET.class);
  }

  @Test
  public void assertPostMethodHasCorrectAnnotations() throws NoSuchMethodException {
    assertMethodHasCorrectAnnotations("post", POST.class);
  }

  @Test
  public void assertPutMethodHasCorrectAnnotations() throws NoSuchMethodException {
    assertMethodHasCorrectAnnotations("put", PUT.class);
  }

  @Test
  public void assertPatchMethodHasCorrectAnnotations() throws NoSuchMethodException {
    assertMethodHasCorrectAnnotations("patch", PATCH.class);
  }

  @Test
  public void assertDeleteMethodHasCorrectAnnotations() throws NoSuchMethodException {
    assertMethodHasCorrectAnnotations("delete", DELETE.class);
  }

  @Test
  public void assertHeadMethodHasCorrectAnnotations() throws NoSuchMethodException {
    assertMethodHasCorrectAnnotations("head", HEAD.class);
  }

  private static void assertMethodHasCorrectAnnotations(String methodName, Class<? extends Annotation> expectedHttpVerbAnnotation)
      throws NoSuchMethodException {
    Class<XSKJavascriptEngineRestService> serviceClass = XSKJavascriptEngineRestService.class;
    Method headMethod = serviceClass.getMethod(methodName, HttpServletRequest.class, String.class);
    assertCorrectHttpVerbAnnotation(headMethod, expectedHttpVerbAnnotation);
    assertCorrectPathAnnotation(headMethod);
  }

  private static void assertCorrectHttpVerbAnnotation(Method serviceMethod, Class<? extends Annotation> expectedHttpVerbAnnotation) {
    Annotation httpVerbAnnotation = serviceMethod.getAnnotation(expectedHttpVerbAnnotation);
    assertNotNull("Method " + serviceMethod.getName() + " does not have an HTTP verb annotation", httpVerbAnnotation);
  }

  private static void assertCorrectPathAnnotation(Method serviceMethod) {
    String methodName = serviceMethod.getName();
    Path pathAnnotation = serviceMethod.getAnnotation(Path.class);
    assertNotNull("Method " + methodName + " does not have a path annotation", pathAnnotation);
    assertEquals("Method " + methodName + " does not have correct path", "/{path:.*}", pathAnnotation.value());
  }
}
