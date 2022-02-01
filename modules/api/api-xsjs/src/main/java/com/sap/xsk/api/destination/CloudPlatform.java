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
package com.sap.xsk.api.destination;

import com.google.gson.JsonObject;
import com.sap.cloud.sdk.cloudplatform.ScpCfCloudPlatform;
import com.sap.cloud.sdk.cloudplatform.exception.CloudPlatformException;
import org.eclipse.dirigible.commons.config.Configuration;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CloudPlatform extends ScpCfCloudPlatform {

  private static final String CLIENT_ID = "clientid";
  private static final String CLIENT_SECRET = "clientsecret";
  private static final String URL = "url";
  private static final String URI = "uri";

  public static final String DIRIGIBLE_DESTINATION_CLIENT_ID = "DIRIGIBLE_DESTINATION_CLIENT_ID";
  public static final String DIRIGIBLE_DESTINATION_CLIENT_SECRET = "DIRIGIBLE_DESTINATION_CLIENT_SECRET";
  public static final String DIRIGIBLE_DESTINATION_URL = "DIRIGIBLE_DESTINATION_URL";
  public static final String DIRIGIBLE_DESTINATION_URI = "DIRIGIBLE_DESTINATION_URI";

  @Nonnull
  @Override
  public JsonObject getServiceCredentials(@Nonnull String serviceName, @Nullable String servicePlan)
      throws CloudPlatformException {
    String clientId = Configuration.get(DIRIGIBLE_DESTINATION_CLIENT_ID);
    String clientSecret = Configuration.get(DIRIGIBLE_DESTINATION_CLIENT_SECRET);
    String url = Configuration.get(DIRIGIBLE_DESTINATION_URL);
    String uri = Configuration.get(DIRIGIBLE_DESTINATION_URI);

    JsonObject credentialsObject = new JsonObject();

    credentialsObject.addProperty(CLIENT_ID, clientId);
    credentialsObject.addProperty(CLIENT_SECRET, clientSecret);
    credentialsObject.addProperty(URL, url);
    credentialsObject.addProperty(URI, uri);

    return credentialsObject;
  }

}
