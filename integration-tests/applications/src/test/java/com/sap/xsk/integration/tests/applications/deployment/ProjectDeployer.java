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
package com.sap.xsk.integration.tests.applications.deployment;

import com.sap.xsk.integration.tests.applications.utils.HttpClientFactory;
import com.sap.xsk.integration.tests.core.client.PublisherClient;
import com.sap.xsk.integration.tests.core.client.WorkspaceClient;
import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;

import org.apache.http.HttpResponse;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ProjectDeployer {

  private final WorkspaceClient workspaceClient;
  private final PublisherClient publisherClient;

  public ProjectDeployer(ProjectDeploymentType projectDeploymentType) {
    XSKHttpClient xskHttpClient = HttpClientFactory.createXSKHttpClient(projectDeploymentType);
    this.workspaceClient = new WorkspaceClient(xskHttpClient);
    this.publisherClient = new PublisherClient(xskHttpClient);
  }

  public void deploy(String applicationName, Path applicationFolderPath) throws ProjectDeploymentException {
    try {
      deployAsync(applicationName, applicationFolderPath).get();
    } catch (InterruptedException | ExecutionException e) {
      String errorMessage = "Publish " + applicationName + " to " + applicationFolderPath + " failed";
      throw new ProjectDeploymentException(errorMessage, e);
    }
  }

  public CompletableFuture<HttpResponse> deployAsync(String projectName, Path projectFolderPath) throws ProjectDeploymentException {
    return workspaceClient.createWorkspace(projectName)
        .thenCompose(x -> workspaceClient.importProjectInWorkspace(projectName, projectName, projectFolderPath))
        .thenCompose(x -> publisherClient.publishProjectInWorkspace(projectName, projectName));
  }

  public void undeploy(String applicationName, String applicationFolderPath) {
    try {
      undeployAsync(applicationName).get();
    } catch (InterruptedException | ExecutionException e) {
      throw new ProjectDeploymentException("Unpublish " + applicationName + " from " + applicationFolderPath + " failed", e);
    }
  }

  public CompletableFuture<HttpResponse> undeployAsync(String projectName) throws ProjectDeploymentException {
    return publisherClient.unpublishProjectFromWorkspace(projectName, projectName)
        .thenCompose(x -> workspaceClient.deleteWorkspace(projectName));

  }
}
