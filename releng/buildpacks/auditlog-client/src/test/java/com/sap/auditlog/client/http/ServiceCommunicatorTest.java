package com.sap.auditlog.client.http;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgssoft.httpclient.HttpClientMock;
import com.sap.auditlog.client.config.ServiceConfig;
import com.sap.auditlog.client.exceptions.InvalidMessageException;
import com.sap.auditlog.client.exceptions.ServiceException;
import com.sap.auditlog.client.exceptions.UnauthorizedException;
import java.io.IOException;
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
    client.onPost(SERVICE_URL + "/test")
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .withBody(equalTo(DUMMY_REQUEST_BODY))
        .doReturnJSON(DUMMY_RESPONSE).withStatus(201);

    communicator.send("/test", DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test(expected = ServiceException.class)
  public void send_httpClientFailsOnSendLog() throws Exception {
    client.onPost(SERVICE_URL + "/test")
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .withBody(equalTo(DUMMY_REQUEST_BODY))
        .doThrowException(new IOException());

    communicator.send("/test", DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test(expected = UnauthorizedException.class)
  public void send_invalidOAuthToken() throws Exception {
    client.onPost(SERVICE_URL + "/test")
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .withBody(equalTo(DUMMY_REQUEST_BODY))
        .doReturn(401, "");

    communicator.send("/test", DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test(expected = InvalidMessageException.class)
  public void send_invalidMessageFormat() throws Exception {
    client.onPost(SERVICE_URL + "/test")
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .withBody(equalTo(DUMMY_REQUEST_BODY))
        .doReturn(400, "");

    communicator.send("/test", DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test(expected = ServiceException.class)
  public void send_problemWithServer() throws Exception {
    client.onPost(SERVICE_URL + "/test")
        .withHeader("Authorization", containsString(OAUTH_TOKEN))
        .withBody(equalTo(DUMMY_REQUEST_BODY))
        .doReturn(500, "");

    communicator.send("/test", DUMMY_REQUEST_BODY, OAUTH_TOKEN);
  }

  @Test
  public void retrieveOAuthToken() throws Exception {
    client.onPost(OAUTH_URL + OAUTH_RETRIEVAL_ENDPOINT).doReturnJSON(DUMMY_RESPONSE);
    Mockito.when(mapper.readTree(DUMMY_RESPONSE)).thenReturn(node);
    Mockito.when(node.get(PROPERTY_NAME)).thenReturn(node);
    Mockito.when(node.asText()).thenReturn(OAUTH_TOKEN);

    communicator.retrieveOAuthToken();
  }

  @Test(expected = UnauthorizedException.class)
  public void retrieveOAuthToken_invalidBasicAuth() throws Exception {
    client.onPost(OAUTH_URL + OAUTH_RETRIEVAL_ENDPOINT).doReturn(401, "");
    communicator.retrieveOAuthToken();
  }

  @Test(expected = ServiceException.class)
  public void retrieveOAuthToken_nonJsonResponse() throws Exception {
    client.onPost(OAUTH_URL + OAUTH_RETRIEVAL_ENDPOINT).doReturnJSON(DUMMY_RESPONSE);
    Mockito.when(mapper.readTree(DUMMY_RESPONSE)).thenThrow(JsonProcessingException.class);
    communicator.retrieveOAuthToken();
  }

  @Test(expected = ServiceException.class)
  public void retrieveOAuthToken_missingProperty() throws Exception {
    client.onPost(OAUTH_URL + OAUTH_RETRIEVAL_ENDPOINT).doReturnJSON(DUMMY_RESPONSE);
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
}