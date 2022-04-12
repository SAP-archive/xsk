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
package com.xsk.integration.tests.applications.deployment;

import com.xsk.integration.tests.applications.deployment.client.PublisherClient;
import com.xsk.integration.tests.applications.deployment.client.WorkspaceClient;
import com.xsk.integration.tests.applications.deployment.client.XSKHttpClient;
import org.apache.http.HttpResponse;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class XSKProjectDeployer {

    private final WorkspaceClient workspaceClient;
    private final PublisherClient publisherClient;

    public XSKProjectDeployer() {
        XSKHttpClient xskHttpClient = new XSKHttpClient();
        this.workspaceClient = new WorkspaceClient(xskHttpClient);
        this.publisherClient = new PublisherClient(xskHttpClient);
    }

    public void deploy(String applicationName, Path applicationFolderPath) throws DeploymentException {
        try {
            deployAsync(applicationName, applicationFolderPath).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new DeploymentException("Publish " + applicationName + " to " + applicationFolderPath + " failed", e);
        }
    }

    public CompletableFuture<HttpResponse> deployAsync(String projectName, Path projectFolderPath) throws DeploymentException {
        return workspaceClient.createWorkspace(projectName)
                .thenCompose(x -> workspaceClient.importProjectInWorkspace(projectName, projectName, projectFolderPath))
                .thenCompose(x -> publisherClient.publishProjectInWorkspace(projectName, projectName));
    }

    public void undeploy(String applicationName, String applicationFolderPath) {
        try {
            undeployAsync(applicationName).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new DeploymentException("Unpublish " + applicationName + " from " + applicationFolderPath + " failed", e);
        }
    }

    public CompletableFuture<HttpResponse> undeployAsync(String projectName) throws DeploymentException {
        return publisherClient.unpublishProjectFromWorkspace(projectName, projectName);
    }


}
