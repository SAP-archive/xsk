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
import javax.validation.constraints.NotBlank;
import java.util.Arrays;

import static org.junit.Assert.*;

public class HanaDataTest {

  @Test
  public void testValidationAnnotationsAreSet() {
    var allMethodsAreAnnotated = Arrays
        .stream(HanaData.class.getDeclaredMethods())
        .allMatch(m -> m.getAnnotation(NotBlank.class) != null);

    assertTrue("Some method(s) are not annotated with @NotBlank", allMethodsAreAnnotated);
  }
}