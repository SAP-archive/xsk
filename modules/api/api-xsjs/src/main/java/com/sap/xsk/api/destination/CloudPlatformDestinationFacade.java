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
import com.google.gson.JsonParser;
import com.sap.cloud.sdk.cloudplatform.CloudPlatformAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.util.EntityUtils;
import org.eclipse.dirigible.commons.api.scripting.IScriptingFacade;
import java.io.IOException;
import java.net.URI;

public class CloudPlatformDestinationFacade implements IScriptingFacade {


  public static Destination getDestination(String name) {
    CloudPlatformAccessor.setCloudPlatformFacade(new CloudPlatformKymaFacade());
    return DestinationAccessor.getDestination(name);
  }

  public static String executeRequest(String requestObject, Destination destination) throws IOException, MethodNotSupportedException {
    JsonParser jsonParser = new JsonParser();
    JsonObject requestJson = jsonParser.parse(requestObject).getAsJsonObject();

    HttpClient client = HttpClientAccessor.getHttpClient(destination.asHttp());
    HttpRequestBase request = getRequest(requestJson, destination);
    request.setURI(URI.create(destination.asHttp().getUri() + requestJson.get("queryPath").getAsString()));

    return EntityUtils.toString(client.execute(request).get);
  }

  private static HttpRequestBase getRequest(JsonObject requestObject, Destination destination) throws MethodNotSupportedException {
    switch (requestObject.get("method").getAsInt()) {
      case 0:
        return new HttpOptions();
      case 1:
        return new HttpGet();
      case 2:
        return new HttpHead();
      case 3:
        return new HttpPost();
      case 4:
        return new HttpPut();
      case 5:
        return new HttpDelete();
      case 6:
        return new HttpTrace();
      // TODO: CONNECT missing
      case 8:
        return new HttpPatch();
      default:
        throw new MethodNotSupportedException("XSJS method integer not supported");
    }
  }

}
