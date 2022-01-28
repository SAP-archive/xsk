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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.testutil.MockDestination;
import com.sap.cloud.sdk.testutil.MockUtil;
import org.apache.cxf.helpers.IOUtils;
import org.eclipse.dirigible.commons.api.context.ContextException;
import org.eclipse.dirigible.commons.api.context.ThreadContextFacade;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
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
import org.junit.Test;
import org.mockito.Mockito;

import com.sap.xsk.engine.XSKJavascriptEngineExecutor;

public class XSKApiSuiteTest extends AbstractDirigibleTest {

  private static final List<String> TEST_MODULES = new ArrayList<>();

  private IExtensionsCoreService extensionsCoreService;

  private IRepository repository;

  /**
   * The rhino javascript engine executor.
   */
  private XSKJavascriptEngineExecutor graaljsJavascriptEngineExecutor;

  @Before
  public void setUp() throws Exception {
    this.extensionsCoreService = new ExtensionsCoreService();
    this.repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
    this.graaljsJavascriptEngineExecutor = new XSKJavascriptEngineExecutor();
  }

  @Before
  public void registerModules()
  {
    // DB tests
    TEST_MODULES.add("test/xsk/db/api/db.xsjs");
    TEST_MODULES.add("test/xsk/db/api/connection.xsjs");
    TEST_MODULES.add("test/xsk/db/api/parameter-metadata.xsjs");
    TEST_MODULES.add("test/xsk/db/api/callable-statement.xsjs");
    TEST_MODULES.add("test/xsk/db/api/prepared-statement.xsjs");
    TEST_MODULES.add("test/xsk/db/api/result-set.xsjs");
    TEST_MODULES.add("test/xsk/db/api/resultset-metadata.xsjs");

    TEST_MODULES.add("test/xsk/response/response.xsjs");
    TEST_MODULES.add("test/xsk/session/session.xsjs");
    TEST_MODULES.add("test/xsk/trace/trace.xsjs");
    TEST_MODULES.add("test/xsk/import/import.xsjs");
    TEST_MODULES.add("test/xsk/util/util.xsjs");
    TEST_MODULES.add("test/xsk/util/codec/codec.xsjs");
    TEST_MODULES.add("test/xsk/http/http.xsjs");
    TEST_MODULES.add("test/xsk/net/net.xsjs");

    // HDB tests
    TEST_MODULES.add("test/xsk/hdb/column-metadata.xsjs");
    TEST_MODULES.add("test/xsk/hdb/connection-execute-query.xsjs");
    TEST_MODULES.add("test/xsk/hdb/connection-execute-update.xsjs");
    TEST_MODULES.add("test/xsk/hdb/result-set.xsjs");
    TEST_MODULES.add("test/xsk/hdb/resultset-metadata.xsjs");
  }

  /**
   * Runs suite.
   *
   * @throws RepositoryWriteException the repository write exception
   * @throws IOException              Signals that an I/O exception has occurred.
   * @throws ScriptingException       the scripting exception
   * @throws ContextException         the context exception
   * @throws ExtensionsException      the extensions exception
   */
  @Test
  public void runSuite()
      throws RepositoryWriteException, IOException, ScriptingException, ContextException, ExtensionsException {
    runSuite(this.graaljsJavascriptEngineExecutor, repository);
  }

  private void runSuite(IJavascriptEngineExecutor executor, IRepository repository)
      throws RepositoryWriteException, IOException, ScriptingException, ContextException, ExtensionsException {
    mockDestination();

    for (String testModule : TEST_MODULES) {
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
  }

  private void mockRequest(HttpServletRequest mockedRequest, HttpSession httpSession) {
    when(mockedRequest.getMethod()).thenReturn("GET");
    when(mockedRequest.getRemoteUser()).thenReturn("tester");
    when(mockedRequest.getHeader("header1")).thenReturn("header1");
    when(mockedRequest.getHeader("header1")).thenReturn("header1");
    when(mockedRequest.getHeader("accept-language")).thenReturn("en-US,en;q=0.9,bg;q=0.8,nb;q=0.7");
    when(mockedRequest.getHeaderNames()).thenReturn(Collections.enumeration(Arrays.asList("header1", "header2", "accept-language")));
    when(mockedRequest.getRequestURI()).thenReturn("/services/v3/js/test/test.xsjs");
    when(mockedRequest.getSession(true)).thenReturn(httpSession);
  }

  private void mockResponse(HttpServletResponse mockedResponse) throws IOException {
    when(mockedResponse.getHeaderNames()).thenReturn(Arrays.asList("header1", "header2"));
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

  private class StubServletOutputStream extends ServletOutputStream {

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
