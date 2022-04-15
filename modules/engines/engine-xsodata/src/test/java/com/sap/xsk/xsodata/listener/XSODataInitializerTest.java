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
package com.sap.xsk.xsodata.listener;

import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.core.edm.provider.EdmImplProv;
import org.apache.olingo.odata2.core.edm.provider.EdmNamedImplProv;
import org.junit.Test;

import static org.junit.Assert.*;

public class XSODataInitializerTest {

  @Test
  public void testWhenValidationPatternIsPatchedNamesCanHaveDot() throws EdmException {
    new XSODataInitializer().contextInitialized(null);

    String expectedName = "test.name";
    DummyEdmNamedImplProv dummyEdmNamedImplProv = new DummyEdmNamedImplProv(expectedName);
    assertEquals("Unexpected name", "test.name", dummyEdmNamedImplProv.getName());
  }

  @Test
  public void testWhenValidationPatternIsNotPatchedNamesCanNotHaveDot() throws EdmException {
    assertThrows(EdmException.class, () -> new DummyEdmNamedImplProv("test.name"));
  }

  class DummyEdmNamedImplProv extends EdmNamedImplProv {

    public DummyEdmNamedImplProv(String name) throws EdmException {
      super(null, name);
    }
  }
}