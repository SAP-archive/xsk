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
import org.eclipse.dirigible.kyma.KymaModule;
import org.eclipse.dirigible.commons.config.Configuration;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CloudPlatform extends ScpCfCloudPlatform {

  private static final String CLIENT_ID = "clientid";
  private static final String CLIENT_SECRET = "clientsecret";
  private static final String URL = "url";
  private static final String URI = "uri";

  @Nonnull
  @Override
  public JsonObject getServiceCredentials(@Nonnull String serviceName, @Nullable String servicePlan)
      throws CloudPlatformException {
    String destinationPrefix = Configuration.get(KymaModule.DIRIGIBLE_DESTINATION_PREFIX, "");

    String clientId = getWithPrefix(destinationPrefix, KymaModule.DIRIGIBLE_DESTINATION_CLIENT_ID);
    String clientSecret = getWithPrefix(destinationPrefix, KymaModule.DIRIGIBLE_DESTINATION_CLIENT_SECRET);
    String url = getWithPrefix(destinationPrefix, KymaModule.DIRIGIBLE_DESTINATION_URL);
    String uri = getWithPrefix(destinationPrefix, KymaModule.DIRIGIBLE_DESTINATION_URI);

    JsonObject credentialsObject = new JsonObject();

    credentialsObject.addProperty(CLIENT_ID, clientId);
    credentialsObject.addProperty(CLIENT_SECRET, clientSecret);
    credentialsObject.addProperty(URL, url);
    credentialsObject.addProperty(URI, uri);

    return credentialsObject;
  }

  private String getWithPrefix(String prefix, String variableName) {
    return prefix.isEmpty() ? Configuration.get(variableName) : Configuration.get(String.format("%s_%s", prefix, variableName));
  }

}
