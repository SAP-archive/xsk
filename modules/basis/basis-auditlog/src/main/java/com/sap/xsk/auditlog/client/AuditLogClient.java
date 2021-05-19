package com.sap.xsk.auditlog.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sap.xsk.auditlog.client.exceptions.ServiceException;
import com.sap.xsk.auditlog.client.logs.Log;
import com.sap.xsk.auditlog.client.messages.AuditLogMessage;
import java.time.Instant;
import java.util.List;

public interface AuditLogClient {

  void log(AuditLogMessage log) throws ServiceException, JsonProcessingException;

  List<Log> getLogs() throws ServiceException;

  List<Log> getLogs(Instant from, Instant to) throws ServiceException;
}