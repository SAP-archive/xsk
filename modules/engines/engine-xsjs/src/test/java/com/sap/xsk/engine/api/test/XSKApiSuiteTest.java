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
package com.sap.xsk.engine.api.test;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sap.cloud.sdk.testutil.MockDestination;
import com.sap.cloud.sdk.testutil.MockUtil;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.cxf.helpers.IOUtils;
import org.eclipse.dirigible.commons.api.context.ContextException;
import org.eclipse.dirigible.commons.api.context.ThreadContextFacade;
import org.eclipse.dirigible.commons.api.module.DirigibleModulesInstallerModule;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.extensions.api.ExtensionsException;
import org.eclipse.dirigible.core.extensions.api.IExtensionsCoreService;
import org.eclipse.dirigible.core.extensions.service.ExtensionsCoreService;
import org.eclipse.dirigible.core.test.AbstractDirigibleTest;
import org.eclipse.dirigible.engine.js.api.IJavascriptEngineExecutor;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IRepositoryStructure;
import org.eclipse.dirigible.repository.api.RepositoryWriteException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.sap.xsk.engine.XSKJavascriptEngineExecutor;

@RunWith(JUnitParamsRunner.class)
public class XSKApiSuiteTest extends AbstractDirigibleTest {

  private IExtensionsCoreService extensionsCoreService;

  private IRepository repository;

  private XSKJavascriptEngineExecutor graaljsJavascriptEngineExecutor;

  public XSKApiSuiteTest() {
    // should be executed before parent @Before method as parent would otherwise initialize the DB in a persistent way
    Configuration.set("DIRIGIBLE_DATABASE_H2_URL", "jdbc:h2:mem:xsk-tests");
  }

  @Before
  public void setUp() {
    this.extensionsCoreService = new ExtensionsCoreService();
    this.repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
    this.graaljsJavascriptEngineExecutor = new XSKJavascriptEngineExecutor();
//    if(StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE) == null) {
//      DirigibleModulesInstallerModule.configure();
//    }
  }

  @Test
  @Parameters({
      "test/xsk/db/api/db.xsjs",
      "test/xsk/db/api/connection.xsjs",
      "test/xsk/db/api/parameter-metadata.xsjs",
      "test/xsk/db/api/callable-statement.xsjs",
      "test/xsk/db/api/prepared-statement.xsjs",
      "test/xsk/db/api/result-set.xsjs",
      "test/xsk/db/api/resultset-metadata.xsjs",
      "test/xsk/response/response.xsjs",
      "test/xsk/session/session.xsjs",
      "test/xsk/trace/trace.xsjs",
      "test/xsk/util/util.xsjs",
      "test/xsk/util/codec/codec.xsjs",
      "test/xsk/http/http.xsjs",
      "test/xsk/net/net.xsjs",
      "test/xsk/hdb/column-metadata.xsjs",
      "test/xsk/hdb/connection-execute-query.xsjs",
      "test/xsk/hdb/connection-execute-update.xsjs",
      "test/xsk/hdb/result-set.xsjs",
      "test/xsk/hdb/resultset-metadata.xsjs",
  })
  public void runXSKApiTest(String testModule) throws IOException, ContextException, ExtensionsException {
    runXSKApiTest(graaljsJavascriptEngineExecutor, repository, testModule);
  }

  private void runXSKApiTest(IJavascriptEngineExecutor executor, IRepository repository, String testModule)
      throws RepositoryWriteException, IOException, ScriptingException, ContextException, ExtensionsException {
    mockDestination();

    try {
      HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
      HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
      HttpSession mockedSession = Mockito.mock(HttpSession.class);
      mockRequest(mockedRequest, mockedSession);
      mockResponse(mockedResponse);

      ThreadContextFacade.setUp();
      try {
        ThreadContextFacade.set(HttpServletRequest.class.getCanonicalName(), mockedRequest);
        ThreadContextFacade.set(HttpServletResponse.class.getCanonicalName(), mockedResponse);
        extensionsCoreService.createExtensionPoint("/test_extpoint1", "test_extpoint1", "Test");
        extensionsCoreService.createExtension("/test_ext1", "/test_ext_module1", "test_extpoint1", "Test");

        Object error = runTest(executor, repository, testModule);

        assertNull("API test failed: " + testModule, error);

      } finally {
        extensionsCoreService.removeExtension("/test_ext1");
        extensionsCoreService.removeExtensionPoint("/test_extpoint1");
      }
    } finally {
      ThreadContextFacade.tearDown();
    }

  }

  private void mockRequest(HttpServletRequest mockedRequest, HttpSession httpSession) {
    when(mockedRequest.getMethod()).thenReturn("GET");
    when(mockedRequest.getRemoteUser()).thenReturn("TestUser");
    when(mockedRequest.getHeaderNames())
        .thenReturn(Collections.enumeration(Arrays.asList("TestHeader1", "TestHeader2", "Accept-Language")));
    when(mockedRequest.getHeaders("TestHeader1")).thenReturn(Collections.enumeration(Collections.singletonList("TestValue")));
    when(mockedRequest.getHeaders("TestHeader2")).thenReturn(Collections.enumeration(Collections.singletonList("TestValue")));
    when(mockedRequest.getHeaders("Accept-Language"))
        .thenReturn(Collections.enumeration(Collections.singletonList("en-US,en;q=0.9,bg;q=0.8,nb;q=0.7")));
    when(mockedRequest.getCookies()).thenReturn(new Cookie[]{new Cookie("SESSIONID", "D7B319C3D55AC4CD126181F01E4C1DC7")});
    when(mockedRequest.getRequestURI()).thenReturn("/services/v4/xsk/test/test.xsjs");
    when(mockedRequest.getSession(true)).thenReturn(httpSession);
    when(mockedRequest.getServerName()).thenReturn("Test");
    when(mockedRequest.getServerPort()).thenReturn(443);
    when(mockedRequest.getProtocol()).thenReturn("HTTP/1.1");
    when(mockedRequest.getContentType()).thenReturn("application/json");
  }

  private void mockResponse(HttpServletResponse mockedResponse) throws IOException {
    when(mockedResponse.getOutputStream()).thenReturn(new StubServletOutputStream());
  }

  private Object runTest(IJavascriptEngineExecutor executor, IRepository repository, String testModule)
      throws IOException, ScriptingException {

    try (InputStream in = XSKApiSuiteTest.class.getResourceAsStream("/META-INF/dirigible/" + testModule)) {
      if (in == null) {
        throw new IOException(IRepositoryStructure.SEPARATOR + testModule + " does not exist");
      }
      repository.createResource(IRepositoryStructure.PATH_REGISTRY_PUBLIC + IRepositoryStructure.SEPARATOR + testModule,
          IOUtils.readBytesFromStream(in));

    } catch (RepositoryWriteException e) {
      throw new IOException(IRepositoryStructure.SEPARATOR + testModule, e);
    }

    long start = System.currentTimeMillis();
    Object error = executor.executeServiceModule(testModule, null);
    long time = System.currentTimeMillis() - start;
    System.out.printf("API test [%s] on engine [%s] passed for: %d ms%n", testModule, executor.getType(), time);
    return error;
  }

  private void mockDestination() {
    final MockUtil mockUtil = new MockUtil();

    MockDestination destination = MockDestination
        .builder("test-destination", URI.create("https://services.odata.org"))
        .build();

    mockUtil.mockDestination(destination);
  }

  private static class StubServletOutputStream extends ServletOutputStream {

    public void write(int i) {
      System.out.write(i);
    }

    @Override
    public boolean isReady() {
      // TODO Auto-generated method stub
      return true;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
  }

}
