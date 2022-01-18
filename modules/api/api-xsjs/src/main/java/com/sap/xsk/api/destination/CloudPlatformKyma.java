/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.api.destination;

import com.google.gson.JsonObject;
import com.sap.cloud.sdk.cloudplatform.ScpCfCloudPlatform;
import com.sap.cloud.sdk.cloudplatform.exception.CloudPlatformException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CloudPlatformKyma extends ScpCfCloudPlatform {

  private static final String DESTINATION_CLIENTID = "destination_clientid";
  private static final String DESTINATION_CLIENTSECRET = "destination_clientsecret";
  private static final String DESTINATION_URL = "destination_url";
  private static final String DESTINATION_URI = "destination_uri";

  private static final String CLIENTID = "clientid";
  private static final String CLIENTSECRET = "clientsecret";
  private static final String URL = "url";
  private static final String URI = "uri";

  @Nonnull
  @Override
  public JsonObject getServiceCredentials(@Nonnull String serviceName, @Nullable String servicePlan)
      throws CloudPlatformException {
    String clientId = this.getEnvironmentVariable(DESTINATION_CLIENTID).get();
    String clientSecret = this.getEnvironmentVariable(DESTINATION_CLIENTSECRET).get();
    String url = this.getEnvironmentVariable(DESTINATION_URL).get();
    String uri = this.getEnvironmentVariable(DESTINATION_URI).get();

    JsonObject credentialsObject = new JsonObject();

    credentialsObject.addProperty(CLIENTID, clientId);
    credentialsObject.addProperty(CLIENTSECRET, clientSecret);
    credentialsObject.addProperty(URL, url);
    credentialsObject.addProperty(URI, uri);

    return credentialsObject;
  }

}
