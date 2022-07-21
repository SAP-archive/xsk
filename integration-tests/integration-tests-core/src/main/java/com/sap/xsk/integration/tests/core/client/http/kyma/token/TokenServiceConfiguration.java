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
package com.sap.xsk.integration.tests.core.client.http.kyma.token;

import org.eclipse.dirigible.commons.config.Configuration;

public class TokenServiceConfiguration {

  private final String clientId;
  private final String clientSecret;
  private final String username;
  private final String password;
  private final String tokenUrl;
  private final String grantType;

  public TokenServiceConfiguration() {
    clientId = Configuration.get("XSUAA_CLIENT_ID");
    clientSecret = Configuration.get("XSUAA_CLIENT_SECRET");
    username = Configuration.get("KYMA_USERNAME");
    password = Configuration.get("KYMA_PASSWORD");
    tokenUrl = Configuration.get("KYMA_TOKEN_URL");
    grantType = Configuration.get("KYMA_GRANT_TYPE");
  }

  public String getGrantType() {
    return grantType;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getTokenUrl() {
    return tokenUrl;
  }
}
