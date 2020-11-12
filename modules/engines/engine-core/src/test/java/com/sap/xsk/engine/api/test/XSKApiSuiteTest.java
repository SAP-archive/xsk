/**
 * Copyright (c) 2010-2019 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   SAP - initial API and implementation
 */
package com.sap.xsk.engine.api.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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
import javax.servlet.http.HttpSession;

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

import com.sap.xsk.engine.XSKJavascriptEngineExecutor;

public class XSKApiSuiteTest extends AbstractGuiceTest {

	private static List<String> TEST_MODULES = new ArrayList<String>();

	private IExtensionsCoreService extensionsCoreService;

	private IRepository repository;
	
	/** The rhino javascript engine executor. */
	private XSKJavascriptEngineExecutor graaljsJavascriptEngineExecutor;

	@Before
	public void setUp() throws Exception {
		this.extensionsCoreService = getInjector().getInstance(ExtensionsCoreService.class);
		this.repository = getInjector().getInstance(IRepository.class);
		this.graaljsJavascriptEngineExecutor = getInjector().getInstance(XSKJavascriptEngineExecutor.class);
	}

	@Before
	public void registerModules() {

		TEST_MODULES.add("test/xsk/response.xsjs");
		
	}

	public void runSuite(IJavascriptEngineExecutor executor, IRepository repository)
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
					
					Object result = null;
					
					result = runTest(executor, repository, testModule);
					
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
//		when(mockedRequest.getPathInfo()).thenReturn("/path");
//		when(mockedRequest.getPathTranslated()).thenReturn("/translated");
		when(mockedRequest.getHeader("header1")).thenReturn("header1");
		when(mockedRequest.getHeaderNames()).thenReturn(Collections.enumeration(Arrays.asList("header1", "header2")));
//		when(mockedRequest.getServerName()).thenReturn("server1");
		when(mockedRequest.getHeader("header1")).thenReturn("header1");
//		when(mockedRequest.isUserInRole("role1")).thenReturn(true);
//		when(mockedRequest.getAttribute("attr1")).thenReturn("val1");
//		when(mockedRequest.getAuthType()).thenReturn("Basic");
		when(mockedRequest.getRequestURI()).thenReturn("/services/v3/js/test/test.xsjs");

		HttpSession mockedSession = Mockito.mock(HttpSession.class);
//		when(mockedRequest.getSession()).thenReturn(mockedSession);
//		when(mockedRequest.getSession(true)).thenReturn(mockedSession);
//		mockSession(mockedSession);
	}
	
	private void mockSession(HttpSession mockedSession) {
		when(mockedSession.getAttributeNames()).thenReturn(Collections.enumeration(Arrays.asList("attr1")));
	}

	private void mockResponse(HttpServletResponse mockedResponse) throws IOException {
		when(mockedResponse.getHeaderNames()).thenReturn(Arrays.asList("header1", "header2"));
		when(mockedResponse.getOutputStream()).thenReturn(new StubServletOutputStream());
	}

	public class StubServletOutputStream extends ServletOutputStream {

		public void write(int i) throws IOException {
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

	private Object runTest(IJavascriptEngineExecutor executor, IRepository repository, String testModule) throws IOException, ScriptingException {

		try {
			InputStream in = XSKApiSuiteTest.class.getResourceAsStream(IRepositoryStructure.SEPARATOR + testModule);
			try {
				if (in == null) {
					throw new IOException(IRepositoryStructure.SEPARATOR + testModule + " does not exist");
				}
				repository.createResource(
						IRepositoryStructure.PATH_REGISTRY_PUBLIC + IRepositoryStructure.SEPARATOR + testModule,
						IOUtils.readBytesFromStream(in));
			} finally {
				if (in != null) {
					in.close();
				}
			}
		} catch (RepositoryWriteException e) {
			throw new IOException(IRepositoryStructure.SEPARATOR + testModule, e);
		}
		Object result = null;
		long start = System.currentTimeMillis();
		result = executor.executeServiceModule(testModule, null);
		long time = System.currentTimeMillis() - start;
		System.out.println(String.format("API test [%s] on engine [%s] passed for: %d ms", testModule,
				executor.getType(), time));
		return result;
	}
	
	

	/**
	 * Run suite.
	 *
	 * @throws RepositoryWriteException the repository write exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ScriptingException the scripting exception
	 * @throws ContextException the context exception
	 * @throws ExtensionsException the extensions exception
	 */
	@Test
	public void runSuite() throws RepositoryWriteException, IOException, ScriptingException, ContextException, ExtensionsException {
		runSuite(this.graaljsJavascriptEngineExecutor, repository);
	}
}
