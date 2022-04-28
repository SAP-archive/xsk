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

import com.xsk.integration.tests.applications.deployment.DeploymentException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

import static com.xsk.integration.tests.applications.deployment.ApplicationDeploymentRule.HOST;

public class PublisherClient {
    private static final URI PUBLISHER_SERVICE_URL = HOST.resolve("/services/v4/ide/publisher/request/");
    private final XSKHttpClient xskHttpClient;

    public PublisherClient(XSKHttpClient xskHttpClient) {
        this.xskHttpClient = xskHttpClient;
    }


    public CompletableFuture<HttpResponse> publishProjectInWorkspace(String workspace, String projectName) {
        try {
            var uri = PUBLISHER_SERVICE_URL.resolve(workspace + "/").resolve(projectName);
            HttpUriRequest request = RequestBuilder.post(uri).build();
            return xskHttpClient.executeRequestAsync(request);
        } catch (DeploymentException e) {
            String errorMessage = "Publishing project " + projectName + " to workspace: " + workspace + " failed.";
            throw new DeploymentException(errorMessage, e);
        }

    }

    public CompletableFuture<HttpResponse> unpublishProjectFromWorkspace(String workspace, String projectName) {
        try {
            var uri = PUBLISHER_SERVICE_URL.resolve(workspace + "/").resolve(projectName);
            HttpUriRequest request = RequestBuilder.delete(uri).build();
            return xskHttpClient.executeRequestAsync(request);
        } catch (DeploymentException e) {
            String errorMessage = "Unpublishing project " + projectName + " from workspace: " + workspace + " failed.";
            throw new DeploymentException(errorMessage, e);

        }

    }


}
