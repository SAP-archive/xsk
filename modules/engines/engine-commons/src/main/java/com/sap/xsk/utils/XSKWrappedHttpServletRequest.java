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
package com.sap.xsk.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

public class XSKWrappedHttpServletRequest extends HttpServletRequestWrapper {

  private final Map<String, String> customHeaders;

  public XSKWrappedHttpServletRequest(HttpServletRequest request) {
    super(request);
    this.customHeaders = new HashMap<String, String>();
  }

  public void putHeader(String name, String value) {
    this.customHeaders.put(name, value);
  }

  public String getHeader(String name) {
    String headerValue = customHeaders.get(name);

    if (headerValue != null) {
      return headerValue;
    }

    return ((HttpServletRequest) getRequest()).getHeader(name);
  }

  public Enumeration<String> getHeaderNames() {
    List<String> names = Collections.list(((HttpServletRequest) getRequest()).getHeaderNames());
    for (String name : customHeaders.keySet()) {
      names.add(name);
    }
    return Collections.enumeration(names);
  }
}