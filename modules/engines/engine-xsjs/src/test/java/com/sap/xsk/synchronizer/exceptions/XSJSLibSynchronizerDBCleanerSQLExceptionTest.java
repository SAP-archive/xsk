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
package com.sap.xsk.synchronizer.exceptions;

import com.sap.xsk.exceptions.XSJSLibSynchronizerDBCleanerSQLException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XSJSLibSynchronizerDBCleanerSQLExceptionTest {
  @Test
  public void artefactCleanerSQLExceptionTest() {
    XSJSLibSynchronizerDBCleanerSQLException exception = new XSJSLibSynchronizerDBCleanerSQLException("test", new RuntimeException("test2"));
    assertEquals("Unexpected exception message", "test", exception.getMessage());
    assertEquals("Unexpected exception cause", RuntimeException.class, exception.getCause().getClass());
  }
}
