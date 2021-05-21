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

import static java.net.HttpURLConnection.HTTP_BAD_GATEWAY;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_GATEWAY_TIMEOUT;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static java.net.HttpURLConnection.HTTP_UNAVAILABLE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.xsk.auditlog.client.config.ServiceConfig;
import com.sap.xsk.auditlog.client.exceptions.InvalidMessageException;
import com.sap.xsk.auditlog.client.exceptions.ServiceException;
import com.sap.xsk.auditlog.client.exceptions.UnauthorizedException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Provider.Service;
import java.util.Base64;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

public class ServiceCommunicator implements Communicator {

  private static final Logger logger = Logger.getLogger(ServiceCommunicator.class.getName());
  private static final Set<Integer> retryResponseCodes = Set
      .of(HTTP_NOT_FOUND, HTTP_UNAVAILABLE, HTTP_INTERNAL_ERROR, HTTP_BAD_GATEWAY, HTTP_GATEWAY_TIMEOUT);
  private static final int MAX_REQUEST_RETRY_COUNT = 3;
  private static final String ISSUE_OAUTH_TOKEN_PATH = "/oauth/token?grant_type=client_credentials";
  private static final String OAUTH_TOKEN_PROPERTY_NAME = "access_token";
  private static final String JSON_CONTENT_TYPE = "application/json";
  private final ServiceConfig config;
  private final HttpClient client;
  private final ObjectMapper jsonMapper;

  public ServiceCommunicator(HttpClient client, ServiceConfig config, ObjectMapper jsonMapper) {
    this.config = config;
    this.jsonMapper = jsonMapper;
    this.client = client;
  }

  @Override
  public void send(String apiUrl, String payload, String oauthToken) throws ServiceException {
    String authHeaderContent = getAuthHeaderValueForOAuth(oauthToken);
    String url = config.getServiceURL() + apiUrl;
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("Authorization", authHeaderContent)
        .header("Content-Type", JSON_CONTENT_TYPE)
        .POST(HttpRequest.BodyPublishers.ofString(payload))
        .build();

    HttpResponse<String> response = null;
    try {
      logger.info("Sending post request to service with url [" + url + "]");
      response = sendRequestWithRetry(request);
    } catch (Exception e) {
      logger.warning("Problem with the http client during the post request. Reason :" + e);
      throw new ServiceException(e.getMessage());
    }

    if (response.statusCode() != HTTP_CREATED) {
      processErrorResponse(response);
    }
  }

  @Override
  public String get(String apiUrl, String oauthToken) throws ServiceException {
    String url = config.getServiceURL() + apiUrl;
    String authHeaderContent = getAuthHeaderValueForOAuth(oauthToken);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("Authorization", authHeaderContent)
        .header("Content-Type", JSON_CONTENT_TYPE)
        .GET()
        .build();

    HttpResponse<String> response = null;
    try {
      logger.info("Sending get request to service with url [" + url + "]");
      response = sendRequestWithRetry(request);//client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      logger.warning("Problem with the http client during the post request. Reason :" + e);
      throw new ServiceException("Problem with http client. Reason:" + e);
    }

    if (response.statusCode() != HTTP_OK) {
      processErrorResponse(response);
    }

    return response.body();
  }

  @Override
  public String retrieveOAuthToken() throws ServiceException {
    return retrieveOAuthToken(config.getOauthURL());
  }

  @Override
  public String retrieveOAuthToken(String oauthUrl) throws ServiceException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(oauthUrl + ISSUE_OAUTH_TOKEN_PATH))
        .header("Authorization", getAuthHeaderValueForBasicAuth())
        .POST(HttpRequest.BodyPublishers.noBody())
        .build();

    HttpResponse<String> response = null;
    try {
      logger.info("Sending retrieval request to OAuth server with url [" + oauthUrl + "]");
      response = sendRequestWithRetry(request);//client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      logger.warning("Problem with the http client during the OAuth token retrieval request. Reason :" + e);
      throw new ServiceException("Problem with http client. Reason:" + e);
    }

    if (response.statusCode() != HTTP_OK) {
      processErrorResponse(response);
    }

    return extractOAuthTokenValue(response.body());
  }

  private HttpResponse<String> sendRequestWithRetry(HttpRequest request) throws Exception {
    int retries = MAX_REQUEST_RETRY_COUNT;
    HttpResponse<String> response = null;
    while (retries > 0) {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
      if (!shouldRetryRequest(response.statusCode())) {
        break;
      }
      retries--;
    }
    return response;
  }

  private boolean shouldRetryRequest(int statusCode) {
    return retryResponseCodes.contains(statusCode);
  }

  private String extractOAuthTokenValue(String responseBody) throws ServiceException {
    JsonNode tokenNode = null;
    try {
      JsonNode root = jsonMapper.readTree(responseBody);
      tokenNode = root.get(OAUTH_TOKEN_PROPERTY_NAME);
    } catch (JsonProcessingException e) {
      logger.info("Problem with the format of the response from the server. Reason :" + e);
      throw new ServiceException("Problem with the response from the server. Reason:" + e);
    }

    if (tokenNode == null) {
      logger.info("Problem with the response from the server. The property, containing the OAuth token,is missing.");
      throw new ServiceException("Problem with the format of the response from the server.");
    }
    return tokenNode.asText();
  }

  private String getAuthHeaderValueForBasicAuth() {
    String username = config.getClientID();
    String password = config.getClientSecret();
    String plainCredentials = username + ":" + password;
    String encodedCredentials = Base64.getEncoder().encodeToString(plainCredentials.getBytes());
    return "Basic " + encodedCredentials;
  }

  private String getAuthHeaderValueForOAuth(String oauthToken) {
    return "Bearer" + " " + oauthToken;
  }

  private void processErrorResponse(HttpResponse<?> response) throws ServiceException {
    logger.info("Unexpected response from the server [" + response.statusCode() + "]");
    switch (response.statusCode()) {
      case HTTP_BAD_REQUEST:
        throw new InvalidMessageException("The message format is not valid. Reason: " + response.body());
      case HTTP_UNAUTHORIZED:
        throw new UnauthorizedException("Problem with the authorization. Reason :" + response.body());
      default:
        throw new ServiceException("Problem with the service. Reason: " + response.body());
    }
  }
}
