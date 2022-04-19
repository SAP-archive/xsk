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

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.core.edm.provider.EdmNamedImplProv;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;

public class XSODataInitializerTest {

  private XSODataInitializer xsoDataInitializer;

  @Before
  public void setUp() {
    xsoDataInitializer = new XSODataInitializer();
  }

  @Test
  public void testWhenValidationPatternIsPatchedNamesCanHaveDot() throws EdmException {
    xsoDataInitializer.contextInitialized(null);

    var expectedName = "test.name";
    var dummyEdmNamedImplProv = new DummyEdmNamedImplProv(expectedName);

    assertEquals("Unexpected name", "test.name", dummyEdmNamedImplProv.getName());
  }

  @Test
  public void testWhenValidationPatternIsNotPatchedNamesCanNotHaveDot() throws EdmException {
    assertThrows(EdmException.class, () -> new DummyEdmNamedImplProv("test.name"));
  }

  @Test
  public void testWhenValidationPatcherThrowsExceptionNewIllegalStateExceptionIsThrown() throws ReflectiveOperationException {
    var odataInternalValidationPatternPatcherMock = Mockito.mock(ODataNamesValidationPatternPatcher.class);
    doThrow(ReflectiveOperationException.class).when(odataInternalValidationPatternPatcherMock).patch();
    FieldUtils.writeField(xsoDataInitializer, "odataNamesValidationPatternPatcher",odataInternalValidationPatternPatcherMock,true);

    var illegalStateException = assertThrows(
        IllegalStateException.class,
        () -> xsoDataInitializer.contextInitialized(null)
    );

    assertEquals(
        "Unexpected exception message",
        "Failed to replace default Olingo OData parameter name pattern.",
        illegalStateException.getMessage()
    );
    MatcherAssert.assertThat(
        "Unexpected exception cause",
        illegalStateException.getCause(),
        instanceOf(ReflectiveOperationException.class)
    );
  }

  class DummyEdmNamedImplProv extends EdmNamedImplProv {

    public DummyEdmNamedImplProv(String name) throws EdmException {
      super(null, name);
    }
  }
}