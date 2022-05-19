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
import org.apache.http.concurrent.FutureCallback;
import java.net.URI;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

public class XSKHttpClientFuture extends CompletableFuture<HttpResponse> implements FutureCallback<HttpResponse> {

  private final URI requestURI;

  public XSKHttpClientFuture(URI requestURI) {
    this.requestURI = requestURI;
  }

  @Override
  public void completed(HttpResponse httpResponse) {
    int statusCode = httpResponse.getStatusLine().getStatusCode();
    if (statusCode > 299 || statusCode < 200) {
      completeExceptionally(new XSKHttpClientException("Unexpected status code: " + statusCode + " for URI: " + requestURI));
    }
    complete(httpResponse);
  }

  @Override
  public void failed(Exception e) {
    completeExceptionally(e);
  }

  @Override
  public void cancelled() {
    completeExceptionally(new CancellationException());
  }
}
