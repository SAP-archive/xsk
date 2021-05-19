package com.sap.auditlog.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sap.auditlog.client.auditlogs.Log;
import com.sap.auditlog.client.exceptions.ServiceException;
import com.sap.auditlog.client.messages.AuditLogMessage;
import java.util.List;

public interface AuditLogClient {

  void log(AuditLogMessage log) throws ServiceException, JsonProcessingException;

  List<Log> getLogs() throws ServiceException, JsonProcessingException;
}