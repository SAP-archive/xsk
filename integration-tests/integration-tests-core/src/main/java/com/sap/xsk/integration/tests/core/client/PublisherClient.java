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
package com.sap.xsk.integration.tests.core.client;

import com.sap.xsk.integration.tests.core.client.http.XSKHttpClient;
import com.sap.xsk.integration.tests.core.client.http.XSKHttpClientException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

public class PublisherClient {

  private final URI publisherServiceUri;
  private final XSKHttpClient xskHttpClient;

  public PublisherClient(XSKHttpClient xskHttpClient) {
    this.xskHttpClient = xskHttpClient;
    this.publisherServiceUri = xskHttpClient.getBaseHost().resolve("/services/v4/ide/publisher/request/");
  }


  public CompletableFuture<HttpResponse> publishProjectInWorkspace(String workspace, String projectName) {
    try {
      var uri = publisherServiceUri.resolve(workspace + "/").resolve(projectName);
      HttpUriRequest request = RequestBuilder.post(uri).build();
      return xskHttpClient.executeRequestAsync(request);
    } catch (XSKHttpClientException e) {
      String errorMessage = "Publishing project " + projectName + " to workspace: " + workspace + " failed.";
      throw new XSKClientException(errorMessage, e);
    }

  }

  public CompletableFuture<HttpResponse> unpublishProjectFromWorkspace(String workspace, String projectName) {
    try {
      var uri = publisherServiceUri.resolve(workspace + "/").resolve(projectName);
      HttpUriRequest request = RequestBuilder.delete(uri).build();
      return xskHttpClient.executeRequestAsync(request);
    } catch (XSKHttpClientException e) {
      String errorMessage = "Unpublishing project " + projectName + " from workspace: " + workspace + " failed.";
      throw new XSKClientException(errorMessage, e);

    }

  }


}
