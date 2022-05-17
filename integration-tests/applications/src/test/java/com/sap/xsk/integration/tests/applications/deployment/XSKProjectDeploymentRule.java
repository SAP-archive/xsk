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
import java.util.Objects;

public class XSKProjectDeploymentRule extends ExternalResource {
    private final XSKProjectDeployer projectPublisher;
    private final String applicationName;

    public XSKProjectDeploymentRule(String applicationName, XSKProjectDeploymentType XSKProjectDeploymentType) {
        this.applicationName = applicationName;
        projectPublisher = new XSKProjectDeployer(XSKProjectDeploymentType);
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        URL resource = getClass().getResource("/test-applications/" + applicationName);
        String resourcePathString = Objects.requireNonNull(resource).getPath();
        Path resourcePath = Path.of(resourcePathString);
        projectPublisher.deploy(applicationName, resourcePath);
    }

    @Override
    protected void after() {
        super.after();
        projectPublisher.undeploy(applicationName, applicationName);
    }
}
