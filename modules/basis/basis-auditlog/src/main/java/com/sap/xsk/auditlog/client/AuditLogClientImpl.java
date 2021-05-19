package com.sap.xsk.auditlog.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.xsk.auditlog.client.exceptions.ServiceException;
import com.sap.xsk.auditlog.client.http.Communicator;
import com.sap.xsk.auditlog.client.logs.Log;
import com.sap.xsk.auditlog.client.messages.AuditLogCategory;
import com.sap.xsk.auditlog.client.messages.AuditLogMessage;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class AuditLogClientImpl implements AuditLogClient {

  private static final String BASE_SEND_AUDITLOG_API_PATH = "/audit-log/oauth2/v2";
  private static final String RETRIEVE_AUDITLOG_PATH = "/auditlog/v2/auditlogrecords";

  private final ObjectMapper jsonMapper;
  private final Communicator auditLogService;
  private final Communicator auditLogManagementService;

  AuditLogClientImpl(Communicator auditLogService, Communicator auditLogManagementService, ObjectMapper jsonMapper) {
    this.auditLogService = auditLogService;
    this.auditLogManagementService = auditLogManagementService;
    this.jsonMapper = jsonMapper;
  }

  @Override
  public void log(AuditLogMessage message) throws ServiceException, JsonProcessingException {
    String payload = jsonMapper.writeValueAsString(message);
    String endpoint = getSendEndpoint(message.getCategory());

    String token = null;
    if (Objects.isNull(message.getSubscriberTokenIssuer())) {
      token = auditLogService.retrieveOAuthToken();
    } else {
      token = auditLogService.retrieveOAuthToken(message.getSubscriberTokenIssuer());
    }

    auditLogService.send(BASE_SEND_AUDITLOG_API_PATH + endpoint, payload, token);
  }

  @Override
  public List<Log> getLogs() throws ServiceException {
    return getLogs(RETRIEVE_AUDITLOG_PATH);
  }

  @Override
  public List<Log> getLogs(Instant from, Instant to) throws ServiceException {
    String path = getLogRetrievalInPeriodPath(from, to);
    return getLogs(path);
  }

  private List<Log> getLogs(String apiPath) throws ServiceException {
    String token = auditLogManagementService.retrieveOAuthToken();
    String responseBody = auditLogManagementService.get(apiPath, token);

    try {
      return Arrays.asList(jsonMapper.readValue(responseBody, Log[].class));
    } catch (JsonProcessingException ex) {
      throw new ServiceException("Problem with the response from the server. Reason :" + ex);
    }
  }

  private String getLogRetrievalInPeriodPath(Instant from, Instant to) {
    return RETRIEVE_AUDITLOG_PATH + "?time_from=" + from + "&time_to=" + to;
  }

  private String getSendEndpoint(AuditLogCategory messageCategory) {
    switch (messageCategory) {
      case DATA_ACCESS:
        return "/data-accesses";
      case DATA_MODIFICATION:
        return "/data-modifications";
      case CONFIGURATION_CHANGE:
        return "/configuration-changes";
      case SECURITY_EVENT:
        return "/security-events";
      default:
        throw new IllegalArgumentException("Non-supported type of message");
    }
  }

}
