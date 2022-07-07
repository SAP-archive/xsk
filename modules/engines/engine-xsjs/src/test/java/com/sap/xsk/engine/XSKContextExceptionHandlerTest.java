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

import org.eclipse.dirigible.commons.api.context.ContextException;
import org.junit.Test;

import javax.ws.rs.core.Response.Status;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class XSKContextExceptionHandlerTest {
  @Test
  public void XSKContextExceptionHandlerTest() {
    XSKContextExceptionHandler handler = new XSKContextExceptionHandler();
    ContextException testException = new ContextException("test");
    assertEquals(XSKContextExceptionHandler.class, handler.getType());
    assertNotNull(handler.getLogger());
    assertEquals(Status.INTERNAL_SERVER_ERROR, handler.getResponseStatus(testException));
    assertEquals("test", handler.getResponseMessage(testException));
  }
}
