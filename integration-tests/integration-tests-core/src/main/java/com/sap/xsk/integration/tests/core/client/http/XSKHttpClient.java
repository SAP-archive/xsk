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
package com.sap.xsk.integration.tests.core.client.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import java.net.URI;
import java.util.concurrent.Future;

public abstract class XSKHttpClient {

  protected final CloseableHttpAsyncClient httpClient;
  private final URI baseHost;

  protected XSKHttpClient(CloseableHttpAsyncClient httpClient, URI baseHost) {
    this.httpClient = httpClient;
    this.baseHost = baseHost;
  }

  public URI getBaseHost() {
    return baseHost;
  }

  public Future<HttpResponse> executeRequestAsync(HttpUriRequest request) {
    return httpClient.execute(request, null);
  }

  public XSKHttpClientFuture executeRequestAsyncWithCallbackFuture(HttpUriRequest request) {
    var future = new XSKHttpClientFuture(request.getURI());
    httpClient.execute(request, future);
    return future;
  }
}
