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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sap.cloud.sdk.cloudplatform.CloudPlatformAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
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
import java.util.Arrays;

public class CloudPlatformDestinationFacade implements IScriptingFacade {

  private static String QUERY_PATH = "queryPath";
  private static String METHOD = "method";
  private static String HEADERS = "headers";
  private static String STATUS_CODE = "statusCode";
  private static String TEXT = "text";
  private static String NAME = "name";
  private static String VALUE = "value";

  public static Destination getDestination(String name) {
    setKymaCloudPlatformFacade();

    return DestinationAccessor.getDestination(name);
  }

  public static String executeRequest(String requestObject, Destination destination) throws IOException, MethodNotSupportedException {
    setKymaCloudPlatformFacade();

    JsonParser jsonParser = new JsonParser();
    JsonObject requestJson = jsonParser.parse(requestObject).getAsJsonObject();

    HttpClient client = HttpClientAccessor.getHttpClient(destination.asHttp());
    HttpRequestBase request = getRequest(requestJson);

    request.setURI(URI.create(destination.asHttp().getUri() + requestJson.get(QUERY_PATH).getAsString()));
    setRequestHeaders(requestJson, request);

    HttpResponse response = client.execute(request);

    JsonObject responseJson = new JsonObject();
    JsonArray headers = new JsonArray();
    Arrays.stream(response.getAllHeaders()).map(CloudPlatformDestinationFacade::createHeaderJson).forEach(headers::add);

    responseJson.add(HEADERS, headers);
    responseJson.addProperty(STATUS_CODE, response.getStatusLine().getStatusCode());
    responseJson.add(TEXT, jsonParser.parse(EntityUtils.toString(response.getEntity())));

    return responseJson.toString();
  }

  private static void setKymaCloudPlatformFacade() {
    if(CloudPlatformAccessor.getCloudPlatformFacade() instanceof CloudPlatformKymaFacade) {
      return;
    }

    CloudPlatformAccessor.setCloudPlatformFacade(new CloudPlatformKymaFacade());
  }

  private static HttpRequestBase getRequest(JsonObject requestObject) throws MethodNotSupportedException {
    switch (requestObject.get(METHOD).getAsInt()) {
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
      case 7:
        throw new MethodNotSupportedException("Method CONNECT not supported");
      case 8:
        return new HttpPatch();
      default:
        throw new MethodNotSupportedException("XSJS method integer not supported");
    }
  }

  private static void setRequestHeaders(JsonObject requestJson, HttpRequestBase request) {
    requestJson.get(HEADERS).getAsJsonArray()
        .forEach(e -> request.setHeader(e.getAsJsonObject().get(NAME).getAsString(), e.getAsJsonObject().get(VALUE).getAsString()));
  }

  private static JsonObject createHeaderJson(Header header) {
    JsonObject headerJson = new JsonObject();
    headerJson.addProperty(NAME, header.getName());
    headerJson.addProperty(VALUE, header.getValue());

    return headerJson;
  }
}
