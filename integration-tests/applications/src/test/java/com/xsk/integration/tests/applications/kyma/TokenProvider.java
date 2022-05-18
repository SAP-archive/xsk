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
package com.xsk.integration.tests.applications.kyma;

import com.xsk.integration.tests.applications.deployment.XSKKymaTokenException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.utils.URIBuilder;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class TokenProvider {
    ApplicationDeploymentConfiguration applicationDeploymentConfiguration = new ApplicationDeploymentConfiguration();

    public String getToken() {
        try {
            byte[] baseAuthCreds = (applicationDeploymentConfiguration.getClientId() + ":" + applicationDeploymentConfiguration.getClientSecret()).getBytes();
            var token64 = Base64.getEncoder().encodeToString(baseAuthCreds);
            URIBuilder queryBuilder = new URIBuilder(applicationDeploymentConfiguration.getTokenUrl());
            queryBuilder.setParameter("grant_type", applicationDeploymentConfiguration.getGrantType());
            queryBuilder.setParameter("username", applicationDeploymentConfiguration.getUsername());
            queryBuilder.setParameter("password", applicationDeploymentConfiguration.getPassword());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(queryBuilder.build())
                    .setHeader(HttpHeaders.AUTHORIZATION, "Basic " + token64)
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            KymaTokenResponseBody responseBody = GsonHelper.GSON.fromJson(response.body(), KymaTokenResponseBody.class);
            return responseBody.getAccessToken();
        } catch (RuntimeException | IOException | InterruptedException | URISyntaxException e) {
            throw new XSKKymaTokenException("Can't access JWT Token for kyma instance", e);
        }
    }
}
