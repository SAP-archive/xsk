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
package com.sap.xsk.auditlog.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.xsk.auditlog.client.config.AuditLogReadConfig;
import com.sap.xsk.auditlog.client.config.AuditLogWriteConfig;
import com.sap.xsk.auditlog.client.config.MissingEnvVariableException;
import com.sap.xsk.auditlog.client.http.Communicator;
import com.sap.xsk.auditlog.client.http.ServiceCommunicator;

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
