/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.engine.api.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.sap.xsk.engine.XSKJavascriptEngineExecutor;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.cxf.helpers.IOUtils;
import org.eclipse.dirigible.commons.api.context.ContextException;
import org.eclipse.dirigible.commons.api.context.ThreadContextFacade;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.core.extensions.api.ExtensionsException;
import org.eclipse.dirigible.core.extensions.api.IExtensionsCoreService;
import org.eclipse.dirigible.core.extensions.service.ExtensionsCoreService;
import org.eclipse.dirigible.core.test.AbstractGuiceTest;
import org.eclipse.dirigible.engine.js.api.IJavascriptEngineExecutor;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IRepositoryStructure;
import org.eclipse.dirigible.repository.api.RepositoryWriteException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class XSKApiSuiteTest extends AbstractGuiceTest {

  private static final List<String> TEST_MODULES = new ArrayList<>();

  private IExtensionsCoreService extensionsCoreService;

  private IRepository repository;

  /**
   * The rhino javascript engine executor.
   */
  private XSKJavascriptEngineExecutor graaljsJavascriptEngineExecutor;

  @Before
  public void setUp() throws Exception {
    this.extensionsCoreService = getInjector().getInstance(ExtensionsCoreService.class);
    this.repository = getInjector().getInstance(IRepository.class);
    this.graaljsJavascriptEngineExecutor = getInjector().getInstance(XSKJavascriptEngineExecutor.class);
  }

  @Before
  public void registerModules() {
    TEST_MODULES.add("test/xsk/response/response.xsjs");
    TEST_MODULES.add("test/xsk/session/session.xsjs");
    TEST_MODULES.add("test/xsk/trace/trace.xsjs");
    TEST_MODULES.add("test/xsk/import/import.xsjs");
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
    for (String testModule : TEST_MODULES) {
      try {
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        mockRequest(mockedRequest);
        mockResponse(mockedResponse);

        ThreadContextFacade.setUp();
        try {
          ThreadContextFacade.set(HttpServletRequest.class.getCanonicalName(), mockedRequest);
          ThreadContextFacade.set(HttpServletResponse.class.getCanonicalName(), mockedResponse);
          extensionsCoreService.createExtensionPoint("/test_extpoint1", "test_extpoint1", "Test");
          extensionsCoreService.createExtension("/test_ext1", "/test_ext_module1", "test_extpoint1", "Test");

          Object result = runTest(executor, repository, testModule);

          assertNotNull(result);
          assertTrue("API test failed: " + testModule, Boolean.parseBoolean(result.toString()));

        } finally {
          extensionsCoreService.removeExtension("/test_ext1");
          extensionsCoreService.removeExtensionPoint("/test_extpoint1");
        }
      } finally {
        ThreadContextFacade.tearDown();
      }
    }
  }

  private void mockRequest(HttpServletRequest mockedRequest) {
    when(mockedRequest.getMethod()).thenReturn("GET");
    when(mockedRequest.getRemoteUser()).thenReturn("tester");
    when(mockedRequest.getHeader("header1")).thenReturn("header1");
    when(mockedRequest.getHeaderNames()).thenReturn(Collections.enumeration(Arrays.asList("header1", "header2")));
    when(mockedRequest.getHeader("header1")).thenReturn("header1");
    when(mockedRequest.getRequestURI()).thenReturn("/services/v3/js/test/test.xsjs");
  }

  private void mockResponse(HttpServletResponse mockedResponse) throws IOException {
    when(mockedResponse.getHeaderNames()).thenReturn(Arrays.asList("header1", "header2"));
    when(mockedResponse.getOutputStream()).thenReturn(new StubServletOutputStream());
  }

  private Object runTest(IJavascriptEngineExecutor executor, IRepository repository, String testModule)
      throws IOException, ScriptingException {

    try (InputStream in = XSKApiSuiteTest.class.getResourceAsStream(IRepositoryStructure.SEPARATOR + testModule)) {
      if (in == null) {
        throw new IOException(IRepositoryStructure.SEPARATOR + testModule + " does not exist");
      }
      repository.createResource(IRepositoryStructure.PATH_REGISTRY_PUBLIC + IRepositoryStructure.SEPARATOR + testModule,
          IOUtils.readBytesFromStream(in));

    } catch (RepositoryWriteException e) {
      throw new IOException(IRepositoryStructure.SEPARATOR + testModule, e);
    }

    long start = System.currentTimeMillis();
    Object result = executor.executeServiceModule(testModule, null);
    long time = System.currentTimeMillis() - start;
    System.out.printf("API test [%s] on engine [%s] passed for: %d ms%n", testModule, executor.getType(), time);
    return result;
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
