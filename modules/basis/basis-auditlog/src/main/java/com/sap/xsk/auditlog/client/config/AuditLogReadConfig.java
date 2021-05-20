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

import java.util.Objects;

public class AuditLogReadConfig extends ServiceConfig {



  static final String READ_API_URL_ENV = "AUDIT_LOG_MANAGEMENT_SERVICE_URL";
  static final String READ_API_CLIENT_ID_ENV = "AUDIT_LOG_MANAGEMENT_SERVICE_CLIENT_ID";
  static final String READ_API_CLIENT_SECRET_ENV = "AUDIT_LOG_MANAGEMENT_SERVICE_CLIENT_SECRET";
  static final String OAUTH_API_URL_ENV = "AUDIT_LOG_MANAGEMENT_SERVICE_OAUTH_URL";

  private AuditLogReadConfig(String serviceURL, String oauthURL, String clientID, String clientSecret) {
    super(serviceURL, oauthURL, clientID, clientSecret);
  }

  public static AuditLogReadConfig create() throws MissingEnvVariableException {

    String serviceURL = System.getenv(READ_API_URL_ENV);
    if (Objects.isNull(serviceURL)) {
      throw new MissingEnvVariableException("Audit Log write api cannot be found");
    }

    String clientID = System.getenv(READ_API_CLIENT_ID_ENV);
    if (Objects.isNull(clientID)) {
      throw new MissingEnvVariableException("The clientID for the write api of the Audit Log cannot be found");
    }

    String clientSecret = System.getenv(READ_API_CLIENT_SECRET_ENV);
    if (Objects.isNull(clientSecret)) {
      throw new MissingEnvVariableException("The clientSecret for the write api of the Audit Log cannot be found");
    }

    String oauthURL = System.getenv(OAUTH_API_URL_ENV);
    if (Objects.isNull(oauthURL)) {
      throw new MissingEnvVariableException("The oauth url for the write api of the Audit Log cannot be found");
    }
    return new AuditLogReadConfig(serviceURL, oauthURL, clientID, clientSecret);
  }
}
