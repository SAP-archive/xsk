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
package com.sap.xsk.integration.tests.core.client.http.kyma;

import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.kyma.token.TokenService;
import org.apache.http.HttpHeaders;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicHeader;
import java.net.URI;
import java.util.List;

public class KymaXSKHttpClient extends XSKHttpClient {

  private KymaXSKHttpClient(CloseableHttpAsyncClient httpClient, URI baseHost) {
    super(httpClient, baseHost);
  }

  public static KymaXSKHttpClient create(URI baseHost) {
    CloseableHttpAsyncClient httpClient = null;
    TokenService tokenService = new TokenService();
    String kymaToken = tokenService.getToken();
    BasicHeader authHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + kymaToken);
    httpClient = HttpAsyncClients.custom().setDefaultHeaders(List.of(authHeader)).build();
    httpClient.start();
    return new KymaXSKHttpClient(httpClient, baseHost);
  }

}
