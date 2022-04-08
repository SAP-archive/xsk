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

import com.sap.cloud.sdk.cloudplatform.CloudPlatformAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import io.vavr.control.Option;
import org.apache.http.HttpResponse;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.eclipse.dirigible.api.v3.http.client.HttpClientRequestOptions;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;
import org.eclipse.dirigible.commons.api.scripting.IScriptingFacade;
import org.eclipse.dirigible.api.v3.http.HttpClientFacade;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Properties;

public class CloudPlatformDestinationFacade implements IScriptingFacade {

  public static Destination getDestination(String destinationName) {
    setKymaCloudPlatformFacade();

    Properties destinationProperties = new Properties();
    com.sap.cloud.sdk.cloudplatform.connectivity.Destination fetchedDestination = DestinationAccessor.getDestination(destinationName);
    fetchedDestination.getPropertyNames()
        .forEach(propName -> {
          Option<Object> property = fetchedDestination.get(propName);
          if (!property.isEmpty()) {
            destinationProperties.put(propName, property.get());
          }
        });

    URI uri = URI.create((String) fetchedDestination.get("URL").get());

    return new Destination(uri.getHost(), uri.getPort(), uri.getPath(), destinationProperties);
  }

  public static String executeRequest(String requestObject, String destinationName, String options)
      throws IOException, MethodNotSupportedException {
    setKymaCloudPlatformFacade();

    DestinationRequest destinationRequest = GsonHelper.GSON.fromJson(requestObject, DestinationRequest.class);
    HttpClientRequestOptions parsedOptions = HttpClientFacade.parseOptions(options);
    HttpDestination httpDestination = DestinationAccessor.getDestination(destinationName).asHttp();
    HttpClient client = HttpClientAccessor.getHttpClient(httpDestination);
    String uri = URI.create(httpDestination.getUri() + destinationRequest.getPath()).toString();
    HttpRequestBase request = getRequest(uri, destinationRequest, parsedOptions);
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
    if (CloudPlatformAccessor.getCloudPlatformFacade() instanceof CloudPlatformKymaFacade) {
      return;
    }

    CloudPlatformAccessor.setCloudPlatformFacade(new CloudPlatformKymaFacade());
  }

  private static HttpRequestBase getRequest(String uri, DestinationRequest destinationRequest, HttpClientRequestOptions options)
      throws MethodNotSupportedException, IOException {
    switch (destinationRequest.getMethod()) {
      case 0:
        return new HttpOptions();
      case 1:
        return HttpClientFacade.createGetRequest(uri, options);
      case 2:
        return HttpClientFacade.createHeadRequest(uri, options);
      case 3:
        return HttpClientFacade.createPostRequest(uri, options);
      case 4:
        return HttpClientFacade.createPutRequest(uri, options);
      case 5:
        return HttpClientFacade.createDeleteRequest(uri, options);
      case 6:
        return HttpClientFacade.createTraceRequest(uri, options);
      case 7:
        throw new MethodNotSupportedException("Method CONNECT not supported");
      case 8:
        return HttpClientFacade.createPatchRequest(uri, options);
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
