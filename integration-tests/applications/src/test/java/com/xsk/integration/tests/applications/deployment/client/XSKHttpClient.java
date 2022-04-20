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
package com.xsk.integration.tests.applications.deployment.client;


import com.xsk.integration.tests.applications.Deployment;
import com.xsk.integration.tests.applications.deployment.DeploymentException;
import com.xsk.integration.tests.applications.kyma.TokenProvider;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicHeader;

import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

public class XSKHttpClient {

    private CloseableHttpAsyncClient httpClient;

    public XSKHttpClient(Deployment deployment) {
        switch (deployment) {
            case KYMA:
                TokenProvider tokenProvider = new TokenProvider();
                String kymaToken = tokenProvider.getToken();
                BasicHeader authHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + kymaToken);
                httpClient = HttpAsyncClients.custom().setDefaultHeaders(List.of(authHeader)).build();
            case LOCAL:
                httpClient = HttpAsyncClients.createDefault();
        }
        httpClient.start();
    }


    CompletableFuture<HttpResponse> executeRequestAsync(HttpUriRequest request) {
        var future = new CompletableHttpResponseFuture();
        httpClient.execute(request, future);
        return future;
    }


    static class CompletableHttpResponseFuture extends CompletableFuture<HttpResponse> implements FutureCallback<HttpResponse> {

        @Override
        public void completed(HttpResponse httpResponse) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode > 299 || statusCode < 200) {
                completeExceptionally(new DeploymentException("Unexpected status code: " + statusCode));
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
}
