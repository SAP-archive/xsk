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

import com.sap.xsk.exceptions.XSJSLibExportsGenerationSourceNotFoundException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XSJSLibExportsGenerationSourceNotFoundExceptionTest {

  @Test
  public void exportGenerationSourceNotFoundExceptionMessageTest() {
    XSJSLibExportsGenerationSourceNotFoundException exception = new XSJSLibExportsGenerationSourceNotFoundException("test");
    assertEquals("Unexpected exception message", "test", exception.getMessage());
  }

  @Test
  public void exportGenerationSourceNotFoundExceptionCauseTest() {
    XSJSLibExportsGenerationSourceNotFoundException exception = new XSJSLibExportsGenerationSourceNotFoundException(new RuntimeException("test"));
    assertEquals("Unexpected exception cause", RuntimeException.class, exception.getCause().getClass());
  }
}
