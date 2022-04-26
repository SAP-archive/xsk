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

import com.xsk.integration.tests.applications.Deployment;
import com.xsk.integration.tests.applications.kyma.KymaCredentials;
import org.junit.rules.ExternalResource;

import java.nio.file.Path;
import java.util.Objects;

import static com.xsk.integration.tests.applications.Deployment.KYMA;
import static com.xsk.integration.tests.applications.Deployment.LOCAL;

public class ApplicationDeploymentRule extends ExternalResource {
    public static String HOST;
    private final XSKProjectDeployer projectPublisher;
    private final String applicationName;
    KymaCredentials kymaCredentials = new KymaCredentials();

    public ApplicationDeploymentRule(String applicationName, Deployment deployment) {
        if (deployment == KYMA) {
            HOST = kymaCredentials.getHost();
        } else if (deployment == LOCAL) {
            HOST = "http://localhost:8080";
        }
        this.applicationName = applicationName;
        projectPublisher = new XSKProjectDeployer(deployment);
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        String resourcePathString = Objects.requireNonNull(getClass().getResource("/test-applications/" + applicationName)).getPath();
        Path resourcePath = Path.of(resourcePathString);
        projectPublisher.deploy(applicationName, resourcePath);
    }

    @Override
    protected void after() {
        super.after();
        projectPublisher.undeploy(applicationName, applicationName);
    }
}
