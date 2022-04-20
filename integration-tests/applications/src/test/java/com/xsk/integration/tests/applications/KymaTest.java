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

import com.xsk.integration.tests.applications.deployment.ApplicationDeploymentRule;
import org.junit.ClassRule;
import org.junit.Test;

public class KymaTest {

  @ClassRule
  public static ApplicationDeploymentRule applicationDeploymentRule = new ApplicationDeploymentRule("simple", Deployment.KYMA);

  @Test
  public void test1() {
    int a = 5;
  }

  @Test
  public void test2() {
    int a = 5;
  }
}
