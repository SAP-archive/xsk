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

import com.sap.cloud.sdk.cloudplatform.CloudPlatformAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
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
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;
import org.eclipse.dirigible.commons.api.scripting.IScriptingFacade;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

public class CloudPlatformDestinationFacade implements IScriptingFacade {
  public static Destination getDestination(String name) {
    setKymaCloudPlatformFacade();

    return DestinationAccessor.getDestination(name);
  }

  public static String executeRequest(String requestObject, Destination destination) throws IOException, MethodNotSupportedException {
    setKymaCloudPlatformFacade();

    DestinationRequest destinationRequest = GsonHelper.GSON.fromJson(requestObject, DestinationRequest.class);

    HttpClient client = HttpClientAccessor.getHttpClient(destination.asHttp());
    HttpRequestBase request = getRequest(destinationRequest);

    request.setURI(URI.create(destination.asHttp().getUri() + destinationRequest.getQueryPath()));
    setRequestHeaders(destinationRequest, request);

    HttpResponse response = client.execute(request);

    Header[] headers = Arrays.stream(response.getAllHeaders()).map(CloudPlatformDestinationFacade::createHeader).toArray(Header[]::new);

    DestinationResponse destinationResponse = new DestinationResponse();
    destinationResponse.setHeaders(headers);
    destinationResponse.setStatusCode(response.getStatusLine().getStatusCode());
    destinationResponse.setText(EntityUtils.toString(response.getEntity()));

    return GsonHelper.GSON.toJson(destinationResponse);
  }

  private static void setKymaCloudPlatformFacade() {
    if(CloudPlatformAccessor.getCloudPlatformFacade() instanceof CloudPlatformKymaFacade) {
      return;
    }

    CloudPlatformAccessor.setCloudPlatformFacade(new CloudPlatformKymaFacade());
  }

  private static HttpRequestBase getRequest(DestinationRequest destinationRequest) throws MethodNotSupportedException {
    switch (destinationRequest.getMethod()) {
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

  private static void setRequestHeaders(DestinationRequest destinationRequest, HttpRequestBase request) {
    destinationRequest.getHeaders()
        .forEach(e -> request.setHeader(e.getName(), e.getValue()));
  }

  private static Header createHeader(org.apache.http.Header header) {
    return new Header(header.getName(), header.getValue());
  }
}
