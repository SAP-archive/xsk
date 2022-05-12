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


import com.xsk.integration.tests.applications.DeploymentType;
import com.xsk.integration.tests.applications.deployment.DeploymentException;
import com.xsk.integration.tests.applications.kyma.TokenProvider;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicHeader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.xsk.integration.tests.applications.DeploymentType.KYMA;
import static com.xsk.integration.tests.applications.DeploymentType.LOCAL;


public class XSKHttpClient {

    private final CloseableHttpAsyncClient httpClient;

    private XSKHttpClient(CloseableHttpAsyncClient httpClient) {
        this.httpClient = httpClient;
    }


    public static XSKHttpClient create(DeploymentType deploymentType) {
        if (deploymentType == KYMA) {
            return createKymaXskHttpClient();
        } else if (deploymentType == LOCAL) {
            return createLocalXskHttpClient();
        } else {
            throw new UnsupportedOperationException("Deployment type unsupported: " + deploymentType);
        }

    }

    private static XSKHttpClient createKymaXskHttpClient() {
        CloseableHttpAsyncClient httpClient = null;
        TokenProvider tokenProvider = new TokenProvider();
        String kymaToken = tokenProvider.getToken();
        BasicHeader authHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + kymaToken);
        httpClient = HttpAsyncClients.custom().setDefaultHeaders(List.of(authHeader)).build();
        httpClient.start();
        return new XSKHttpClient(httpClient);
    }

    private static XSKHttpClient createLocalXskHttpClient() {
        CloseableHttpAsyncClient httpClient = null;
        httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
        XSKHttpClient xskHttpClient = new XSKHttpClient(httpClient);
        loginIfNecessary(xskHttpClient);
        return xskHttpClient;
    }

    private static void loginIfNecessary(XSKHttpClient xskHttpClient) {
        try {
            xskHttpClient.login().get();
        } catch (InterruptedException | ExecutionException executionException) {
            throw new DeploymentException("Login to local deployed XSK failed.", executionException);
        }
    }


    public CompletableFuture<HttpResponse> executeRequestAsync(HttpUriRequest request) {
        var future = new CompletableHttpResponseFuture(request.getURI());
        httpClient.execute(request, future);
        return future;
    }

    public CompletableFuture<HttpResponse> login() {
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

    static class CompletableHttpResponseFuture extends CompletableFuture<HttpResponse> implements FutureCallback<HttpResponse> {
        private final URI requestURI;

        public CompletableHttpResponseFuture(URI requestURI) {
            this.requestURI = requestURI;
        }

        @Override
        public void completed(HttpResponse httpResponse) {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode > 299 || statusCode < 200) {
                completeExceptionally(new DeploymentException("Unexpected status code: " + statusCode + " for URI: " + requestURI));
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
