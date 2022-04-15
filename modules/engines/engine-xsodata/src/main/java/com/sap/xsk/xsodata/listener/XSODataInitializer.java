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

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class XSODataInitializer implements ServletContextListener {

  private final ODataNamesValidationPatternPatcher odataNamesValidationPatternPatcher = new ODataNamesValidationPatternPatcher();

  public void contextInitialized(ServletContextEvent sce) {
    try {
      odataNamesValidationPatternPatcher.patch();
    } catch (Exception e) {
      throw new IllegalStateException("Failed to replace default Olingo OData parameter name pattern.", e);
    }
  }
}