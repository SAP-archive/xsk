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
package com.sap.xsk.integration.tests.applications;

import com.sap.xsk.integration.tests.applications.deployment.XSKProjectApplicationType;
import com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentRule;
import com.sap.xsk.integration.tests.applications.deployment.XSKProjectDeploymentType;
import org.junit.ClassRule;

public class AppTest {

    @ClassRule
    public static XSKProjectDeploymentRule xskProjectDeploymentRule = new XSKProjectDeploymentRule("simple", XSKProjectApplicationType.CUSTOM, XSKProjectDeploymentType.LOCAL);

}
