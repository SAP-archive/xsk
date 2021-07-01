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

public class ServiceConfig {

  private final String serviceURL;
  private final String oauthURL;
  private final String clientID;
  private final String clientSecret;

  public ServiceConfig(String serviceURL, String oauthURL, String clientID, String clientSecret) {
    this.serviceURL = serviceURL;
    this.oauthURL = oauthURL;
    this.clientID = clientID;
    this.clientSecret = clientSecret;
  }

  public String getServiceURL() {
    return serviceURL;
  }

  public String getOauthURL() {
    return oauthURL;
  }

  public String getClientID() {
    return clientID;
  }

  public String getClientSecret() {
    return clientSecret;
  }
}
