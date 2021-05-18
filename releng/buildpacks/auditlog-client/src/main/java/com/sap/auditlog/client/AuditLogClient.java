package com.sap.auditlog.client;

import com.sap.auditlog.client.auditlogs.Log;
import com.sap.auditlog.client.messages.AuditLogMessage;
import java.util.List;

public interface AuditLogClient {

  void log(AuditLogMessage log) throws Exception;

  List<Log> getLogs() throws Exception;
}