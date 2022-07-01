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

import org.junit.rules.ExternalResource;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentConstants.CUSTOM_APPS_DIR_NAME;
import static com.sap.xsk.integration.tests.applications.deployment.ProjectDeploymentConstants.SAMPLES_DIR_NAME;

public class ProjectDeploymentRule extends ExternalResource {

  private final ProjectDeployer projectDeployer;
  private final String applicationName;
  private final ProjectType projectType;

  public ProjectDeploymentRule(String applicationName, ProjectType projectType) {
    this.applicationName = applicationName;
    this.projectType = projectType;
    this.projectDeployer = new ProjectDeployer();
  }

  @Override
  protected void before() throws Throwable {
    super.before();

    Path resourcePath = null;

    switch (projectType) {
      case SAMPLE:
        resourcePath = Path.of(Paths.get("").toAbsolutePath().getParent().getParent() + SAMPLES_DIR_NAME + applicationName);
        break;
      case CUSTOM:
        URL resource = getClass().getResource(CUSTOM_APPS_DIR_NAME + applicationName);
        String resourcePathString = Objects.requireNonNull(resource).getPath();
        resourcePath = Path.of(resourcePathString);
        break;
    }

    projectDeployer.deploy(applicationName, resourcePath);
  }

  @Override
  protected void after() {
    super.after();
    projectDeployer.undeploy(applicationName, applicationName);
  }
}
