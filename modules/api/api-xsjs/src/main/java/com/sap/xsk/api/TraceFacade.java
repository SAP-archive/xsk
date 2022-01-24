/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.api;
import org.eclipse.dirigible.commons.api.scripting.IScriptingFacade;
import org.eclipse.dirigible.commons.config.Configuration;

public class TraceFacade implements IScriptingFacade{
  private static final String XSK_LOG_DEBUG_ENABLED = "XSK_LOG_DEBUG_ENABLED";
  private static final String XSK_LOG_ERROR_ENABLED = "XSK_LOG_ERROR_ENABLED";
  private static final String XSK_LOG_FATAL_ENABLED = "XSK_LOG_FATAL_ENABLED";
  private static final String XSK_LOG_INFO_ENABLED = "XSK_LOG_INFO_ENABLED";
  private static final String XSK_LOG_WARNING_ENABLED = "XSK_LOG_WARNING_ENABLED";

  public static boolean isDebugEnabled() {
    return Boolean.parseBoolean(Configuration.get(XSK_LOG_DEBUG_ENABLED, "true"));
  }

  public static boolean isErrorEnabled() {
    return Boolean.parseBoolean(Configuration.get(XSK_LOG_ERROR_ENABLED, "true"));
  }

  public static boolean isFatalEnabled() {
    return Boolean.parseBoolean(Configuration.get(XSK_LOG_FATAL_ENABLED, "true"));
  }

  public static boolean isInfoEnabled() {
    return Boolean.parseBoolean(Configuration.get(XSK_LOG_INFO_ENABLED, "true"));
  }

  public static boolean isWarningEnabled() {
    return Boolean.parseBoolean(Configuration.get(XSK_LOG_WARNING_ENABLED, "true"));
  }
}