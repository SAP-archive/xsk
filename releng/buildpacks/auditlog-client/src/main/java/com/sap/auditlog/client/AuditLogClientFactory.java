package com.sap.auditlog.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.auditlog.client.config.AuditLogReadConfig;
import com.sap.auditlog.client.config.AuditLogWriteConfig;
import com.sap.auditlog.client.config.MissingEnvVariableException;
import com.sap.auditlog.client.http.Communicator;
import com.sap.auditlog.client.http.ServiceCommunicator;
import java.net.http.HttpClient;

public class AuditLogClientFactory {

  public static AuditLogClient createClient() throws MissingEnvVariableException {
    AuditLogWriteConfig writeConfig = AuditLogWriteConfig.create();
    AuditLogReadConfig readConfig = AuditLogReadConfig.create();
    Communicator auditLogService = new ServiceCommunicator(HttpClient.newHttpClient(), writeConfig, new ObjectMapper());
    Communicator auditLogManagementService = new ServiceCommunicator(HttpClient.newHttpClient(), readConfig, new ObjectMapper());
    return new AuditLogClientImpl(auditLogService, auditLogManagementService, new ObjectMapper());
  }
}
