/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.auditlog.client.config;

import com.sap.xsk.auditlog.client.config.AuditLogWriteConfig;
import com.sap.xsk.auditlog.client.config.MissingEnvVariableException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import static com.sap.xsk.auditlog.client.config.AuditLogWriteConfig.*;


public class AuditLogWriteConfigTest {

  private static final String SERVICE_URL = "test_service_url";
  private static final String OAUTH_URL = "test_oauth_url";
  private static final String CLIENT_ID = "client_id";
  private static final String CLIENT_SECRET = "client_secret";

  @Rule
  public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

  @Before
  public void setUp() {
    environmentVariables.set(WRITE_API_URL_ENV, SERVICE_URL);
    environmentVariables.set(OAUTH_API_URL_ENV, OAUTH_URL);
    environmentVariables.set(WRITE_API_CLIENT_ID_ENV, CLIENT_ID);
    environmentVariables.set(WRITE_API_CLIENT_SECRET_ENV, CLIENT_SECRET);
  }

  @After
  public void tearDown() {
    environmentVariables.clear(WRITE_API_URL_ENV, OAUTH_API_URL_ENV, WRITE_API_CLIENT_ID_ENV, WRITE_API_CLIENT_SECRET_ENV);
  }

  @Test(expected = MissingEnvVariableException.class)
  public void create_missingServiceURL() throws MissingEnvVariableException {
    environmentVariables.clear(WRITE_API_URL_ENV);
    AuditLogWriteConfig.create();
  }

  @Test(expected = MissingEnvVariableException.class)
  public void create_missingOAuthURL() throws MissingEnvVariableException {
    environmentVariables.clear(OAUTH_API_URL_ENV);
    AuditLogWriteConfig.create();
  }

  @Test(expected = MissingEnvVariableException.class)
  public void create_missingClientID() throws MissingEnvVariableException {
    environmentVariables.clear(WRITE_API_CLIENT_ID_ENV);
    AuditLogWriteConfig.create();
  }

  @Test(expected = MissingEnvVariableException.class)
  public void create_missingClientSecret() throws MissingEnvVariableException {
    environmentVariables.clear(WRITE_API_CLIENT_SECRET_ENV);
    AuditLogWriteConfig.create();
  }

  @Test
  public void create() throws MissingEnvVariableException {
    AuditLogWriteConfig config = AuditLogWriteConfig.create();
    Assert.assertEquals(config.getClientID(), CLIENT_ID);
    Assert.assertEquals(config.getClientSecret(), CLIENT_SECRET);
    Assert.assertEquals(config.getOauthURL(), OAUTH_URL);
    Assert.assertEquals(config.getServiceURL(), SERVICE_URL);
  }


}