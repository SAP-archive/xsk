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
package com.sap.xsk.api.test;

import com.sap.cloud.sdk.cloudplatform.CloudPlatformAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.AuthenticationType;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import com.sap.xsk.api.destination.CloudPlatformDestinationFacade;
import com.sap.xsk.api.destination.CloudPlatformKymaFacade;
import io.vavr.control.Option;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import static org.mockito.ArgumentMatchers.any;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import com.sap.xsk.api.destination.Destination;
import java.net.URI;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class CloudPlatformDestinationFacadeTest {

  HttpClient httpClient;
  String destinationName = "test-destination";
  String DESTINATION_URI = "http://test-destination.com:8080/destination";
  MockedStatic<DestinationAccessor> destinationAccessor;
  MockedStatic<HttpClientAccessor> httpClientAccessor;
  MockedStatic<CloudPlatformAccessor> cloudPlatformAccessor;
  boolean isKymaFacadeSet = false;

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
        { true }, { false }
    });
  }

  public CloudPlatformDestinationFacadeTest(boolean isKymaFacadeSet) {
    this.isKymaFacadeSet = isKymaFacadeSet;
  }

  @Before
  public void setup() throws IOException {
    mockRequest();
    mockAccessors(isKymaFacadeSet);
  }

  @After
  public void close() {
    destinationAccessor.close();
    httpClientAccessor.close();
    cloudPlatformAccessor.close();
  }


  public void mockAccessors(Boolean isKymaFacadeSet) {
    destinationAccessor = Mockito.mockStatic(DestinationAccessor.class);
    httpClientAccessor = Mockito.mockStatic(HttpClientAccessor.class);
    cloudPlatformAccessor = Mockito.mockStatic(CloudPlatformAccessor.class);

    com.sap.cloud.sdk.cloudplatform.connectivity.Destination mockedDestination = Mockito.mock(com.sap.cloud.sdk.cloudplatform.connectivity.Destination.class);
    when(mockedDestination.get("URL")).thenReturn(Option.of(DESTINATION_URI));

    HttpDestination mockedHttpDestination = Mockito.mock(HttpDestination.class);
    when(mockedHttpDestination.getAuthenticationType()).thenReturn(AuthenticationType.NO_AUTHENTICATION);
    when(mockedHttpDestination.getUri()).thenReturn(URI.create(DESTINATION_URI));

    when(mockedDestination.asHttp()).thenReturn(mockedHttpDestination);

    destinationAccessor.when(() -> DestinationAccessor.getDestination(destinationName))
        .thenReturn(mockedDestination);

    httpClientAccessor.when(() -> HttpClientAccessor.getHttpClient(any()))
        .thenReturn(httpClient);

    cloudPlatformAccessor.when(() -> CloudPlatformAccessor.getCloudPlatformFacade())
        .thenReturn(isKymaFacadeSet ? new CloudPlatformKymaFacade() : null);
  }


  public void mockRequest() throws IOException {
    httpClient = Mockito.mock(HttpClient.class);
    HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
    StatusLine statusLine = Mockito.mock(StatusLine.class);

    when(statusLine.getStatusCode()).thenReturn(200);
    when(httpResponse.getStatusLine()).thenReturn(statusLine);
    when(httpResponse.getAllHeaders()).thenReturn(new Header[0]);
    when(httpClient.execute(any())).thenReturn(httpResponse);

    HttpEntity mockedEntity = Mockito.mock(HttpEntity.class);
    when(mockedEntity.getContent()).thenReturn(new ByteArrayInputStream("success".getBytes()));

    when(httpResponse.getEntity()).thenReturn(mockedEntity);
  }

  @Test
  public void getDestinationTest() throws Exception {
    Destination dest = CloudPlatformDestinationFacade.getDestination(destinationName);
    assertEquals("test-destination.com", dest.getHost());
    assertEquals(8080, dest.getPort());
    assertEquals("/destination", dest.getPathPrefix());

    if (!isKymaFacadeSet) {
      cloudPlatformAccessor.verify(() -> CloudPlatformAccessor.setCloudPlatformFacade(any()));
    }

  }

  @Test
  public void executeDestinationRequestTest() throws Exception {
    String request = "{\"method\": 1, \"queryPath\": \"test-destination.com\", \"headers\": []}";
    String options = "{}";

    String response = CloudPlatformDestinationFacade.executeRequest(request, destinationName, options);
    assertEquals("{\"headers\":[],\"statusCode\":200,\"text\":\"success\"}", response);

    if (!isKymaFacadeSet) {
      cloudPlatformAccessor.verify(() -> CloudPlatformAccessor.setCloudPlatformFacade(any()));
    }
  }

}
