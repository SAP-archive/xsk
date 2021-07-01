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
package com.sap.xsk.auditlog.client.http;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.pgssoft.httpclient.HttpClientMock;
import com.pgssoft.httpclient.HttpClientMockBuilder;
import com.sap.xsk.auditlog.client.config.ServiceConfig;
import com.sap.xsk.auditlog.client.exceptions.InvalidMessageException;
import com.sap.xsk.auditlog.client.exceptions.ServiceException;
import com.sap.xsk.auditlog.client.exceptions.UnauthorizedException;
import java.io.IOException;
import java.util.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServiceCommunicatorTest {

  private static final String SERVICE_URL = "http://example.com";
  private static final String OAUTH_URL = "http://example2.com";
  private static final String API_URL = "/test";
  private static final String OAUTH_RETRIEVAL_ENDPOINT = "/oauth/token?grant_type=client_credentials";
  private static final String CLIENT_ID = "client_id";
  private static final String CLIENT_SECRET = "client_secret";
  private static final String OAUTH_TOKEN = "test_token";
  private static final int MAX_NUM_RETRIES = 3;
  private static final String DUMMY_RESPONSE = "dummy_response";
  private static final String DUMMY_REQUEST_BODY = "dummy_request";
  private static final String PROPERTY_NAME = "access_token";

  @Mock
  JsonObject element;
  private HttpClientMock client;
  @Mock
  private Gson mapper;
  @Mock
  private ServiceConfig config;
  private Communicator communicator;

  @Before
  public void setUp() {
    setUpServiceConfig();
    client = new HttpClientMock();
    communicator = new ServiceCommunicator(client, config, mapper);
  }

  @Test
  public void send() throws Exception {
    getBaseMockBuilderForResponseToSendRequest(API_URL)
        .doReturnJSON(DUMMY_RESPONSE).withStatus(201);

    communicator.send(API_URL, DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test(expected = ServiceException.class)
  public void send_httpClientFailsOnSendLog() throws Exception {
    getBaseMockBuilderForResponseToSendRequest(API_URL)
        .doThrowException(new IOException());

    communicator.send(API_URL, DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test(expected = UnauthorizedException.class)
  public void send_invalidOAuthToken() throws Exception {
    getBaseMockBuilderForResponseToSendRequest(API_URL)
        .doReturn(401, "");

    try {
      communicator.send(API_URL, DUMMY_REQUEST_BODY, OAUTH_TOKEN);
    } catch (UnauthorizedException ex) {
      client.verify().post(SERVICE_URL + API_URL).called(1);
      throw ex;
    }
  }

  @Test(expected = InvalidMessageException.class)
  public void send_invalidMessageFormat() throws Exception {
    getBaseMockBuilderForResponseToSendRequest(API_URL)
        .doReturn(400, "");

    try {
      communicator.send(API_URL, DUMMY_REQUEST_BODY, OAUTH_TOKEN);
    } catch (InvalidMessageException ex) {
      client.verify().post(SERVICE_URL + API_URL).called(1);
      throw ex;
    }
  }

  @Test(expected = ServiceException.class)
  public void send_problemWithServer() throws Exception {
    getBaseMockBuilderForResponseToSendRequest(API_URL)
        .doReturn(500, "");

    try {
      communicator.send(API_URL, DUMMY_REQUEST_BODY, OAUTH_TOKEN);
    } catch (ServiceException ex) {
      client.verify().post(SERVICE_URL + API_URL).called(MAX_NUM_RETRIES);
      throw ex;
    }
  }

  @Test
  public void send_problemWithServerWithSuccessResponseOnRetry() throws Exception {
    getBaseMockBuilderForResponseToSendRequest(API_URL)
        .doReturn(500, "").doReturn(201, "");

    communicator.send(API_URL, DUMMY_REQUEST_BODY, OAUTH_TOKEN);
    client.verify().post(SERVICE_URL + API_URL).called(2);
  }

  @Test(expected = InvalidMessageException.class)
  public void send_problemWithServerWithErrorResponseOnRetry() throws Exception {
    getBaseMockBuilderForResponseToSendRequest(API_URL)
        .doReturn(500, "").doReturn(400, "");

    try {
      communicator.send(API_URL, DUMMY_REQUEST_BODY, OAUTH_TOKEN);
    } catch (UnauthorizedException ex) {
      client.verify().post(SERVICE_URL + API_URL).called(2);
      throw ex;
    }
  }

  @Test
  public void retrieveOAuthToken() throws Exception {
    mockBasicResponseFromOAuthServer(OAUTH_URL);
    Mockito.when(mapper.fromJson(DUMMY_RESPONSE, JsonObject.class)).thenReturn(element);
    Mockito.when(element.get(PROPERTY_NAME)).thenReturn(element);
    Mockito.when(element.getAsString()).thenReturn(OAUTH_TOKEN);

    communicator.retrieveOAuthToken();
  }

  @Test
  public void retrieveOAuthToken_usingAnotherOAuthUrl() throws Exception {
    String subscriberTokenIssuerUrl = "https://subscriber-token-issuer-url";
    mockBasicResponseFromOAuthServer(subscriberTokenIssuerUrl);
    Mockito.when(mapper.fromJson(DUMMY_RESPONSE, JsonObject.class)).thenReturn(element);
    Mockito.when(element.get(PROPERTY_NAME)).thenReturn(element);
    Mockito.when(element.getAsString()).thenReturn(OAUTH_TOKEN);

    communicator.retrieveOAuthToken(subscriberTokenIssuerUrl);
  }

  @Test(expected = UnauthorizedException.class)
  public void retrieveOAuthToken_invalidBasicAuth() throws Exception {
    client.onPost(OAUTH_URL + OAUTH_RETRIEVAL_ENDPOINT)
        .withHeader("Authorization", getBasicAuthHeader())
        .doReturn(401, "");

    try {
      communicator.retrieveOAuthToken();
    } catch (UnauthorizedException ex) {
      client.verify().post(OAUTH_URL + OAUTH_RETRIEVAL_ENDPOINT).called(1);
      throw ex;
    }
  }

  @Test
  public void retrieveOAuthToken_problemWithServerWithSuccessResponseOnRetry() throws Exception {
    client.onPost(OAUTH_URL + OAUTH_RETRIEVAL_ENDPOINT)
        .withHeader("Authorization", getBasicAuthHeader())
        .doReturn(500, "")
        .doReturnJSON(DUMMY_RESPONSE);

    Mockito.when(mapper.fromJson(DUMMY_RESPONSE, JsonObject.class)).thenReturn(element);
    Mockito.when(element.get(PROPERTY_NAME)).thenReturn(element);
    Mockito.when(element.getAsString()).thenReturn(OAUTH_TOKEN);

    communicator.retrieveOAuthToken();
    client.verify().post(OAUTH_URL + OAUTH_RETRIEVAL_ENDPOINT).called(2);
  }

  @Test(expected = InvalidMessageException.class)
  public void retrieveOAuthToken_problemWithServerWithErrorResponseOnRetry() throws Exception {
    client.onPost(OAUTH_URL + OAUTH_RETRIEVAL_ENDPOINT)
        .withHeader("Authorization", getBasicAuthHeader())
        .doReturn(500, "")
        .doReturn(400, "");

    try {
      communicator.retrieveOAuthToken();
    } catch (InvalidMessageException ex) {
      client.verify().post(OAUTH_URL + OAUTH_RETRIEVAL_ENDPOINT).called(2);
      throw ex;
    }
  }

  @Test(expected = ServiceException.class)
  public void retrieveOAuthToken_nonJsonResponse() throws Exception {
    mockBasicResponseFromOAuthServer(OAUTH_URL);
    Mockito.when(mapper.fromJson(DUMMY_RESPONSE, JsonObject.class)).thenThrow(JsonSyntaxException.class);

    communicator.retrieveOAuthToken();
  }

  @Test(expected = ServiceException.class)
  public void retrieveOAuthToken_missingProperty() throws Exception {
    mockBasicResponseFromOAuthServer(OAUTH_URL);
    Mockito.when(mapper.fromJson(DUMMY_RESPONSE, JsonObject.class)).thenReturn(element);
    Mockito.when(element.get(PROPERTY_NAME)).thenReturn(null);

    communicator.retrieveOAuthToken();
  }


  @Test
  public void get() throws ServiceException {
    client.onGet(SERVICE_URL + API_URL)
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .doReturnJSON(DUMMY_RESPONSE)
        .withStatus(200);

    String response = communicator.get(API_URL, OAUTH_TOKEN);
    client.verify().get(SERVICE_URL + API_URL).called(1);
    Assert.assertEquals(response, DUMMY_RESPONSE);
  }

  @Test(expected = UnauthorizedException.class)
  public void get_invalidToken() throws ServiceException {
    client.onGet(SERVICE_URL + API_URL)
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .doReturn(401, "");

    try {
      communicator.get(API_URL, OAUTH_TOKEN);
    } catch (UnauthorizedException ex) {
      client.verify().get(SERVICE_URL + API_URL).called(1);
      throw ex;
    }

  }

  @Test(expected = ServiceException.class)
  public void get_httpClientFails() throws ServiceException {
    client.onGet(SERVICE_URL + API_URL)
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .doThrowException(new IOException());

    try {
      communicator.get(API_URL, OAUTH_TOKEN);
    } catch (UnauthorizedException ex) {
      client.verify().get(SERVICE_URL + API_URL).called(1);
    }
  }

  @Test(expected = ServiceException.class)
  public void get_problemWithServer() throws ServiceException {
    client.onGet(SERVICE_URL + API_URL)
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .doReturnJSON(DUMMY_RESPONSE)
        .withStatus(500);

    try {
      communicator.get(API_URL, OAUTH_TOKEN);
    } catch (UnauthorizedException ex) {
      client.verify().get(SERVICE_URL + API_URL).called(MAX_NUM_RETRIES);
    }
  }

  @Test
  public void get_problemWithServerWithSuccessResponseOnRetry() throws ServiceException {
    client.onGet(SERVICE_URL + API_URL)
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .doReturn(500, "")
        .doReturnJSON(DUMMY_RESPONSE)
        .withStatus(200);

    communicator.get(API_URL, OAUTH_TOKEN);
    client.verify().get(SERVICE_URL + API_URL).called(2);
  }

  @Test(expected = InvalidMessageException.class)
  public void get_problemWithServerWithErrorResponseOnRetry() throws ServiceException {
    client.onGet(SERVICE_URL + API_URL)
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .doReturn(500, "")
        .doReturnJSON(DUMMY_RESPONSE).withStatus(400);

    try {
      communicator.get(API_URL, OAUTH_TOKEN);
    } catch (InvalidMessageException ex) {
      client.verify().get(SERVICE_URL + API_URL).called(2);
      throw ex;
    }


  }


  private void setUpServiceConfig() {
    Mockito.when(config.getClientID()).thenReturn(CLIENT_ID);
    Mockito.when(config.getClientSecret()).thenReturn(CLIENT_SECRET);
    Mockito.when(config.getServiceURL()).thenReturn(SERVICE_URL);
    Mockito.when(config.getOauthURL()).thenReturn(OAUTH_URL);
  }

  private String getBasicAuthHeader() {
    String credentials = CLIENT_ID + ':' + CLIENT_SECRET;
    return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
  }

  private void mockBasicResponseFromOAuthServer(String OAuthURL) {
    client.onPost(OAuthURL + OAUTH_RETRIEVAL_ENDPOINT)
        .withHeader("Authorization", getBasicAuthHeader())
        .doReturnJSON(DUMMY_RESPONSE);
  }

  private HttpClientMockBuilder getBaseMockBuilderForResponseToSendRequest(String apiURL) {
    return client.onPost(SERVICE_URL + apiURL)
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .withBody(equalTo(DUMMY_REQUEST_BODY));
  }
}