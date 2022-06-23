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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(JUnitParamsRunner.class)
public class XSKJavascriptEngineRestServiceTest {
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

  @Test
  @Parameters({"GET", "PUT", "DELETE", "HEAD"})
  public void executeServiceTest(String requestType) throws Exception {
    XSKJavascriptEngineProcessor mockProcessor = Mockito.mock(XSKJavascriptEngineProcessor.class);
    XSKJavascriptEngineRestService restService = new XSKJavascriptEngineRestService(mockProcessor);
    Response response = selectAndExecuteMethod(restService, requestType);
    verify(mockProcessor, times(1)).executeService("/test.js");
    assertEquals(Status.OK, response.getStatusInfo().toEnum());
  }

  private Response selectAndExecuteMethod(
      XSKJavascriptEngineRestService restService,
      String requestType
  ) throws Exception {
    Response response;
    switch(requestType) {
      case "GET": response = restService.executeServiceGet( "/test.js"); break;
      case "PUT": response = restService.executeServicePut( "/test.js"); break;
      case "HEAD": response = restService.executeServiceHead( "/test.js"); break;
      case "DELETE": response = restService.executeServiceDelete( "/test.js"); break;
      default: throw new Exception("Unexpected request type '" + requestType + "'");
    }
    return response;
  }

  @Test(expected = ScriptingException.class)
  @Parameters({"GET", "PUT", "DELETE", "HEAD"})
  public void executeServiceWithExceptionTest(String requestType) throws Exception {
    XSKJavascriptEngineProcessor mockProcessor = Mockito.mock(XSKJavascriptEngineProcessor.class);
    XSKJavascriptEngineRestService restService = new XSKJavascriptEngineRestService(mockProcessor);
    doThrow(new ScriptingException()).when(mockProcessor).executeService("/test.js");
    selectAndExecuteMethod(restService, requestType);
  }

  @Test
  public void executeServicePostTest() throws ContextException {
    XSKJavascriptEngineProcessor mockProcessor = Mockito.mock(XSKJavascriptEngineProcessor.class);
    HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);

    try(MockedStatic<ThreadContextFacade> mockedThreadContextFacade = mockStatic(ThreadContextFacade.class)) {
      XSKJavascriptEngineRestService restService = new XSKJavascriptEngineRestService(mockProcessor);
      Response response = restService.executeServicePost(mockRequest, "/test.js");

      mockedThreadContextFacade.verify(ThreadContextFacade::setUp, times(1));
      mockedThreadContextFacade.verify(
          () -> ThreadContextFacade.set(
              HttpServletRequest.class.getCanonicalName(),
              mockRequest
          ), times(1)
      );
      verify(mockProcessor, times(1)).executeService("/test.js");
      assertEquals(Status.OK, response.getStatusInfo().toEnum());
    }
  }

  @Test(expected = ContextException.class)
  public void executeServicePostWithThreadContextExceptionTest() throws ContextException {
    XSKJavascriptEngineProcessor mockProcessor = Mockito.mock(XSKJavascriptEngineProcessor.class);
    HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);

    try(MockedStatic<ThreadContextFacade> mockedThreadContextFacade = mockStatic(ThreadContextFacade.class)) {
      mockedThreadContextFacade.when(
          () -> ThreadContextFacade.set(
              HttpServletRequest.class.getCanonicalName(),
              mockRequest
          )
      ).thenThrow(new ContextException());

      XSKJavascriptEngineRestService restService = new XSKJavascriptEngineRestService(mockProcessor);
      restService.executeServicePost(mockRequest, "/test.js");
    }
  }

  @Test(expected = ScriptingException.class)
  public void executeServicePostWithOtherExceptionTest() throws ContextException {
    XSKJavascriptEngineProcessor mockProcessor = Mockito.mock(XSKJavascriptEngineProcessor.class);
    HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
    doThrow(new ScriptingException()).when(mockProcessor).executeService("/test.js");

    try(MockedStatic<ThreadContextFacade> mockedThreadContextFacade = mockStatic(ThreadContextFacade.class)) {
      XSKJavascriptEngineRestService restService = new XSKJavascriptEngineRestService(mockProcessor);
      restService.executeServicePost(mockRequest, "/test.js");
    }
  }
}
