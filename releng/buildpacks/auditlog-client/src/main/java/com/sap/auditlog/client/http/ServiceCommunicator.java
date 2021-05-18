package com.sap.auditlog.client.http;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.auditlog.client.config.ServiceConfig;
import com.sap.auditlog.client.exceptions.InvalidMessageException;
import com.sap.auditlog.client.exceptions.ServiceException;
import com.sap.auditlog.client.exceptions.UnauthorizedException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class ServiceCommunicator implements Communicator {

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

  public void send(String apiUrl, String payload, String oauthToken) throws ServiceException {
    String authHeaderContent = getAuthHeaderValueForOAuth(oauthToken);
    String url = config.getServiceURL() + apiUrl;
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("Authorization", authHeaderContent)
        .header("Content-Type", JSON_CONTENT_TYPE)
        .POST(HttpRequest.BodyPublishers.ofString(payload))
        .build();

    HttpResponse<?> response = null;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.discarding());
    } catch (Exception e) {
      throw new ServiceException(e.getMessage());
    }

    if (response.statusCode() != HTTP_CREATED) {
      processErrorResponse(response);
    }
  }

  private void processErrorResponse(HttpResponse<?> response) throws ServiceException {
    switch (response.statusCode()) {
      case HTTP_BAD_REQUEST:
        throw new InvalidMessageException("The message format is not valid. Reason: " + response.body());
      case HTTP_UNAUTHORIZED:
        throw new UnauthorizedException("Problem with the authorization. Reason :" + response.body());
      default:
        throw new ServiceException("Problem with the service. Reason: " + response.body());
    }
  }

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
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      throw new ServiceException("Problem with http client. Reason:" + e);
    }

    if (response.statusCode() != HTTP_OK) {
      processErrorResponse(response);
    }

    return response.body();
  }

  public String retrieveOAuthToken() throws ServiceException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(config.getOauthURL() + ISSUE_OAUTH_TOKEN_PATH))
        .header("Authorization", getAuthHeaderValueForBasicAuth())
        .POST(HttpRequest.BodyPublishers.noBody())
        .build();

    HttpResponse<String> response = null;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      throw new ServiceException("Problem with http client. Reason:" + e);
    }

    if (response.statusCode() != HTTP_OK) {
      processErrorResponse(response);
    }

    return extractOAuthTokenValue(response.body());
  }

  private String extractOAuthTokenValue(String responseBody) throws ServiceException {
    JsonNode tokenNode = null;
    try {
      JsonNode root = jsonMapper.readTree(responseBody);
      tokenNode = root.get(OAUTH_TOKEN_PROPERTY_NAME);
    } catch (JsonProcessingException e) {
      throw new ServiceException("Problem with the response from the server. Reason:" + e);
    }

    if (tokenNode == null) {
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
}
