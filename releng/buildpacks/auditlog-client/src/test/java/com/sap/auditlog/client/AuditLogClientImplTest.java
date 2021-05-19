package com.sap.auditlog.client;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.auditlog.client.auditlogs.Log;
import com.sap.auditlog.client.exceptions.ServiceException;
import com.sap.auditlog.client.http.Communicator;
import com.sap.auditlog.client.messages.AuditLogCategory;
import com.sap.auditlog.client.messages.AuditLogMessage;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuditLogClientImplTest {

  private static final String BASE_SEND_AUDITLOG_API_PATH = "/audit-log/oauth2/v2";
  private static final String DUMMY_PAYLOAD = "dummy_payload";
  private static final String DUMMY_OAUTH_TOKEN = "dummy_token";
  private static final String DATA_ACCESS_ENDPOINT = "/data-accesses";
  private static final String DATA_MODIFICATION_ENDPOINT = "/data-modifications";
  private static final String SECURITY_EVENT_ENDPOINT = "/security-events";
  private static final String CONFIGURATION_CHANGE_ENDPOINT = "/configuration-changes";
  private static final String RETRIEVE_AUDITLOG_PATH = "/auditlog/v2/auditlogrecords";
  private static final String DUMMY_RESPONSE = "dummy_response";

  @Mock
  private Communicator auditLogComm;
  @Mock
  private Communicator auditLogManagerComm;
  @Mock
  private ObjectMapper jsonMapper;
  @Mock
  private AuditLogMessage message;

  private AuditLogClient auditLogClient;

  @Before
  public void setUp() {
    auditLogClient = new AuditLogClientImpl(auditLogComm, auditLogManagerComm, jsonMapper);
  }

  @Test
  public void log_dataAccessMessage() throws Exception {
    Mockito.when(message.getCategory()).thenReturn(AuditLogCategory.DATA_ACCESS);
    Mockito.when(jsonMapper.writeValueAsString(message)).thenReturn(DUMMY_PAYLOAD);
    Mockito.when(auditLogComm.retrieveOAuthToken()).thenReturn(DUMMY_OAUTH_TOKEN);
    auditLogClient.log(message);
    Mockito.verify(auditLogComm).send(BASE_SEND_AUDITLOG_API_PATH + DATA_ACCESS_ENDPOINT, DUMMY_PAYLOAD, DUMMY_OAUTH_TOKEN);
  }

  @Test
  public void log_dataModificationMessage() throws Exception {
    Mockito.when(message.getCategory()).thenReturn(AuditLogCategory.DATA_MODIFICATION);
    Mockito.when(jsonMapper.writeValueAsString(message)).thenReturn(DUMMY_PAYLOAD);
    Mockito.when(auditLogComm.retrieveOAuthToken()).thenReturn(DUMMY_OAUTH_TOKEN);
    auditLogClient.log(message);
    Mockito.verify(auditLogComm).send(BASE_SEND_AUDITLOG_API_PATH + DATA_MODIFICATION_ENDPOINT, DUMMY_PAYLOAD, DUMMY_OAUTH_TOKEN);
  }

  @Test
  public void log_securityEventMessage() throws Exception {
    Mockito.when(message.getCategory()).thenReturn(AuditLogCategory.SECURITY_EVENT);
    Mockito.when(jsonMapper.writeValueAsString(message)).thenReturn(DUMMY_PAYLOAD);
    Mockito.when(auditLogComm.retrieveOAuthToken()).thenReturn(DUMMY_OAUTH_TOKEN);
    auditLogClient.log(message);
    Mockito.verify(auditLogComm).send(BASE_SEND_AUDITLOG_API_PATH + SECURITY_EVENT_ENDPOINT, DUMMY_PAYLOAD, DUMMY_OAUTH_TOKEN);
  }

  @Test
  public void log_configurationChangeMessage() throws Exception {
    Mockito.when(message.getCategory()).thenReturn(AuditLogCategory.CONFIGURATION_CHANGE);
    Mockito.when(jsonMapper.writeValueAsString(message)).thenReturn(DUMMY_PAYLOAD);
    Mockito.when(auditLogComm.retrieveOAuthToken()).thenReturn(DUMMY_OAUTH_TOKEN);
    auditLogClient.log(message);
    Mockito.verify(auditLogComm).send(BASE_SEND_AUDITLOG_API_PATH + CONFIGURATION_CHANGE_ENDPOINT, DUMMY_PAYLOAD, DUMMY_OAUTH_TOKEN);
  }

  @Test(expected = JsonProcessingException.class)
  public void log_problemWithSerializationOfMessage() throws Exception {
    Mockito.when(jsonMapper.writeValueAsString(message)).thenThrow(JsonProcessingException.class);
    auditLogClient.log(message);
    Mockito.verify(auditLogComm, never()).send(anyString(), anyString(), anyString());
  }

  @Test(expected = ServiceException.class)
  public void log_problemWithOAuthRetrieval() throws Exception {
    Mockito.when(message.getCategory()).thenReturn(AuditLogCategory.CONFIGURATION_CHANGE);
    Mockito.when(auditLogComm.retrieveOAuthToken()).thenThrow(ServiceException.class);
    auditLogClient.log(message);
    Mockito.verify(auditLogComm, never()).send(anyString(), anyString(), anyString());
  }

  @Test
  public void getLogs() throws Exception {
    Log dummyLog = new Log();
    Log[] expectedResponse = new Log[]{dummyLog};

    Mockito.when(auditLogManagerComm.retrieveOAuthToken()).thenReturn(DUMMY_OAUTH_TOKEN);
    Mockito.when(auditLogManagerComm.get(RETRIEVE_AUDITLOG_PATH, DUMMY_OAUTH_TOKEN)).thenReturn(DUMMY_RESPONSE);

    Mockito.when(jsonMapper.readValue(DUMMY_RESPONSE, Log[].class)).thenReturn(expectedResponse);
    List<Log> actualResponse = auditLogClient.getLogs();

    Assert.assertEquals(expectedResponse.length, actualResponse.size());
    Assert.assertEquals(expectedResponse[0], actualResponse.get(0));
  }

  @Test(expected = ServiceException.class)
  public void getLogs_problemWithTokenRetrieval() throws Exception {
    Mockito.when(auditLogManagerComm.retrieveOAuthToken()).thenThrow(ServiceException.class);
    auditLogClient.getLogs();
  }

  @Test(expected = ServiceException.class)
  public void getLogs_problemWithRetrievalOfLogs() throws Exception {
    Mockito.when(auditLogManagerComm.retrieveOAuthToken()).thenReturn(DUMMY_OAUTH_TOKEN);
    Mockito.when(auditLogManagerComm.get(RETRIEVE_AUDITLOG_PATH, DUMMY_OAUTH_TOKEN)).thenThrow(ServiceException.class);
    auditLogClient.getLogs();
  }

  @Test(expected = ServiceException.class)
  public void getLogs_problemWithDeserializationOfResponse() throws Exception {
    Mockito.when(auditLogManagerComm.retrieveOAuthToken()).thenReturn(DUMMY_OAUTH_TOKEN);
    Mockito.when(auditLogManagerComm.get(RETRIEVE_AUDITLOG_PATH, DUMMY_OAUTH_TOKEN)).thenReturn(DUMMY_RESPONSE);

    Mockito.when(jsonMapper.readValue(DUMMY_RESPONSE, Log[].class)).thenThrow(JsonProcessingException.class);

    auditLogClient.getLogs();
  }
}