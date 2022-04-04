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
package com.sap.xsk.api.destination;

import java.util.List;

public class DestinationRequest {

  private int method;
  private String path;
  private List<Header> headers;

  public int getMethod() {
    return method;
  }

  public void setMethod(int method) {
    this.method = method;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public List<Header> getHeaders() {
    return headers;
  }

  public void setHeaders(List<Header> headers) {
    this.headers = headers;
  }

}
