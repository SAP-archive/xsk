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
package com.sap.xsk.integration.tests.core.client.http.local;

import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.XSKHttpClientException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class LocalXSKHttpClient extends XSKHttpClient {

  private LocalXSKHttpClient(CloseableHttpAsyncClient httpClient, URI baseHost) {
    super(httpClient, baseHost);
  }

  public static XSKHttpClient create(URI baseHost) {
    CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
    httpClient.start();
    LocalXSKHttpClient xskHttpClient = new LocalXSKHttpClient(httpClient, baseHost);

    try {
      xskHttpClient.login().get();
    } catch (InterruptedException | ExecutionException executionException) {
      throw new XSKHttpClientException("Login to local deployed XSK failed.", executionException);
    }

    return xskHttpClient;
  }

  private CompletableFuture<HttpResponse> login() {
    try {
      var uri = new URI("http://localhost:8080/services/v4/web/ide/");
      HttpUriRequest request = RequestBuilder.get(uri).build();
      return this.executeRequestAsync(request)
          .thenCompose(x -> {
            try {
              var jsecurityUri = new URI("http://localhost:8080/services/v4/web/ide/j_security_check");
              HttpUriRequest loginRequest = RequestBuilder
                  .post(jsecurityUri)
                  .addParameter("j_username", "dirigible")
                  .addParameter("j_password", "dirigible")
                  .build();
              return this.executeRequestAsync(loginRequest);
            } catch (URISyntaxException e) {
              throw new RuntimeException(e);
            }
          });
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

}
