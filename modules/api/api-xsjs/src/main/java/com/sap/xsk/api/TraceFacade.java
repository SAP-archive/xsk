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
package com.sap.xsk.api;
import org.eclipse.dirigible.commons.api.scripting.IScriptingFacade;
import org.eclipse.dirigible.commons.config.Configuration;

public class TraceFacade implements IScriptingFacade{
  private static final String XSK_DEBUG_ENABLED = "XSK_DEBUG_ENABLED";
  private static final String XSK_ERROR_ENABLED = "XSK_ERROR_ENABLED";
  private static final String XSK_FATAL_ENABLED = "XSK_FATAL_ENABLED";
  private static final String XSK_INFO_ENABLED = "XSK_INFO_ENABLED";
  private static final String XSK_WARNING_ENABLED = "XSK_WARNING_ENABLED";

  public static String isDebugEnabled() {
    return Configuration.get(XSK_DEBUG_ENABLED, "true");
  }

  public static String isErrorEnabled() {
    return Configuration.get(XSK_ERROR_ENABLED, "true");
  }

  public static String isFatalEnabled() {
    return Configuration.get(XSK_FATAL_ENABLED, "true");
  }

  public static String isInfoEnabled() {
    return Configuration.get(XSK_INFO_ENABLED, "true");
  }

  public static String isWarningEnabled() {
    return Configuration.get(XSK_WARNING_ENABLED, "true");
  }
}