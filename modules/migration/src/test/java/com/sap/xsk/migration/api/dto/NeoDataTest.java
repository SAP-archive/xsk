/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.migration.api.dto;

import org.junit.Test;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static org.junit.Assert.*;

public class NeoDataTest {

  @Test
  public void testValidationAnnotationsAreSet() throws NoSuchMethodException {
    var declaredMethods = NeoData.class.getDeclaredMethods();

    var allMethodsAreAnnotated = Arrays.stream(declaredMethods).allMatch(m -> m.getAnnotation(NotNull.class) != null);

    assertTrue("Some method(s) are not annotated with @NotBlank", allMethodsAreAnnotated);
  }
}