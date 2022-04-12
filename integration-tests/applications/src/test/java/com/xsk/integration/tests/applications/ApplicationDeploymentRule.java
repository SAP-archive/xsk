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
package com.xsk.integration.tests.applications;

import org.junit.rules.ExternalResource;
import java.nio.file.Path;

public class ApplicationDeploymentRule extends ExternalResource {

  private final XSKProjectPublisher projectPublisher;
  private final String applicationName;

  public ApplicationDeploymentRule(String applicationName) {
    this.applicationName = applicationName;
    projectPublisher = new XSKProjectPublisher();
  }

  @Override
  protected void before() throws Throwable {
    super.before();
    String resourcePathString = getClass().getResource("/test-applications/" + applicationName).getPath();
    Path resourcePath = Path.of(resourcePathString);
    projectPublisher.publish(applicationName, resourcePath);
  }

  @Override
  protected void after() {
    super.after();
  }
}
