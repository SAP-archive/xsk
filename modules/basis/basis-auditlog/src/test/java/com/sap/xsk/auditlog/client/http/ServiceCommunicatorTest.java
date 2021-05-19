package com.sap.xsk.auditlog.client.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgssoft.httpclient.HttpClientMock;
import com.pgssoft.httpclient.HttpClientMockBuilder;
import java.io.IOException;
import java.util.Base64;
import com.sap.xsk.auditlog.client.config.ServiceConfig;
import com.sap.xsk.auditlog.client.exceptions.InvalidMessageException;
import com.sap.xsk.auditlog.client.exceptions.ServiceException;
import com.sap.xsk.auditlog.client.exceptions.UnauthorizedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(MockitoJUnitRunner.class)
public class ServiceCommunicatorTest {

  private static final String SERVICE_URL = "http://example.com";
  private static final String OAUTH_URL = "http://example2.com";
  private static final String OAUTH_RETRIEVAL_ENDPOINT = "/oauth/token?grant_type=client_credentials";
  private static final String CLIENT_ID = "client_id";
  private static final String CLIENT_SECRET = "client_secret";
  private static final String OAUTH_TOKEN = "test_token";

  private static final String DUMMY_RESPONSE = "dummy_response";
  private static final String DUMMY_REQUEST_BODY = "dummy_request";
  private static final String PROPERTY_NAME = "access_token";

  @Mock
  JsonNode node;
  private HttpClientMock client;
  @Mock
  private ObjectMapper mapper;
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
    getBaseMockBuilderForResponseToSendRequest("/test")
        .doReturnJSON(DUMMY_RESPONSE).withStatus(201);

    communicator.send("/test", DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test(expected = ServiceException.class)
  public void send_httpClientFailsOnSendLog() throws Exception {
    getBaseMockBuilderForResponseToSendRequest("/test")
        .doThrowException(new IOException());

    communicator.send("/test", DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test(expected = UnauthorizedException.class)
  public void send_invalidOAuthToken() throws Exception {
    getBaseMockBuilderForResponseToSendRequest("/test")
        .doReturn(401, "");

    communicator.send("/test", DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test(expected = InvalidMessageException.class)
  public void send_invalidMessageFormat() throws Exception {
    getBaseMockBuilderForResponseToSendRequest("/test")
        .doReturn(400, "");

    communicator.send("/test", DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test(expected = ServiceException.class)
  public void send_problemWithServer() throws Exception {
    getBaseMockBuilderForResponseToSendRequest("/test")
        .doReturn(500, "");

    communicator.send("/test", DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test
  public void retrieveOAuthToken() throws Exception {
    mockBasicResponseFromOAuthServer(OAUTH_URL);
    Mockito.when(mapper.readTree(DUMMY_RESPONSE)).thenReturn(node);
    Mockito.when(node.get(PROPERTY_NAME)).thenReturn(node);
    Mockito.when(node.asText()).thenReturn(OAUTH_TOKEN);

    communicator.retrieveOAuthToken();
  }

  @Test
  public void retrieveOAuthToken_usingAnotherOAuthUrl() throws Exception {
    String subscriberTokenIssuerUrl = "https://subscriber-token-issuer-url";
    mockBasicResponseFromOAuthServer(subscriberTokenIssuerUrl);
    Mockito.when(mapper.readTree(DUMMY_RESPONSE)).thenReturn(node);
    Mockito.when(node.get(PROPERTY_NAME)).thenReturn(node);
    Mockito.when(node.asText()).thenReturn(OAUTH_TOKEN);
    communicator.retrieveOAuthToken(subscriberTokenIssuerUrl);
  }

  @Test(expected = UnauthorizedException.class)
  public void retrieveOAuthToken_invalidBasicAuth() throws Exception {
    client.onPost(OAUTH_URL + OAUTH_RETRIEVAL_ENDPOINT)
        .withHeader("Authorization", getBasicAuthHeader(CLIENT_ID, CLIENT_SECRET))
        .doReturn(401, "");
    communicator.retrieveOAuthToken();
  }

  @Test(expected = ServiceException.class)
  public void retrieveOAuthToken_nonJsonResponse() throws Exception {
    mockBasicResponseFromOAuthServer(OAUTH_URL);
    Mockito.when(mapper.readTree(DUMMY_RESPONSE)).thenThrow(JsonProcessingException.class);
    communicator.retrieveOAuthToken();
  }

  @Test(expected = ServiceException.class)
  public void retrieveOAuthToken_missingProperty() throws Exception {
    mockBasicResponseFromOAuthServer(OAUTH_URL);
    Mockito.when(mapper.readTree(DUMMY_RESPONSE)).thenReturn(node);
    Mockito.when(node.get(PROPERTY_NAME)).thenReturn(null);
    communicator.retrieveOAuthToken();
  }

  @Test
  public void get() throws ServiceException {
    client.onGet(SERVICE_URL + "/test")
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .doReturnJSON(DUMMY_RESPONSE)
        .withStatus(200);

    String response = communicator.get("/test", OAUTH_TOKEN);
    Assert.assertEquals(response, DUMMY_RESPONSE);
  }

  @Test(expected = UnauthorizedException.class)
  public void get_invalidToken() throws ServiceException {
    client.onGet(SERVICE_URL + "/test")
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .doReturn(401, "");

    communicator.get("/test", OAUTH_TOKEN);
  }

  @Test(expected = ServiceException.class)
  public void get_httpClientFails() throws ServiceException {
    client.onGet(SERVICE_URL + "/test")
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .doThrowException(new IOException());

    communicator.get("/test", OAUTH_TOKEN);
  }

  @Test(expected = ServiceException.class)
  public void get_problemWithServer() throws ServiceException {
    client.onGet(SERVICE_URL + "/test")
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .doReturnJSON(DUMMY_RESPONSE)
        .withStatus(500);

    communicator.get("/test", OAUTH_TOKEN);
  }


  private void setUpServiceConfig() {
    Mockito.when(config.getClientID()).thenReturn(CLIENT_ID);
    Mockito.when(config.getClientSecret()).thenReturn(CLIENT_SECRET);
    Mockito.when(config.getServiceURL()).thenReturn(SERVICE_URL);
    Mockito.when(config.getOauthURL()).thenReturn(OAUTH_URL);
  }

  private String getBasicAuthHeader(String username, String password) {
    String credentials = username + ':' + password;
    return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
  }

  private void mockBasicResponseFromOAuthServer(String OAuthURL) {
    client.onPost(OAuthURL + OAUTH_RETRIEVAL_ENDPOINT)
        .withHeader("Authorization", getBasicAuthHeader(CLIENT_ID, CLIENT_SECRET))
        .doReturnJSON(DUMMY_RESPONSE);
  }

  private HttpClientMockBuilder getBaseMockBuilderForResponseToSendRequest(String apiURL) {
    return client.onPost(SERVICE_URL + apiURL)
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .withBody(equalTo(DUMMY_REQUEST_BODY));
  }
}