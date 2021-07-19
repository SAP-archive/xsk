/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.auditlog.client;

import com.fatboyindustrial.gsonjavatime.InstantConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sap.xsk.auditlog.client.config.AuditLogReadConfig;
import com.sap.xsk.auditlog.client.config.AuditLogWriteConfig;
import com.sap.xsk.auditlog.client.config.MissingEnvVariableException;
import com.sap.xsk.auditlog.client.http.Communicator;
import com.sap.xsk.auditlog.client.http.ServiceCommunicator;
import java.net.http.HttpClient;
import java.time.Instant;

public class AuditLogClientFactory {

  public static AuditLogClient createClient() throws MissingEnvVariableException {
    AuditLogWriteConfig writeConfig = AuditLogWriteConfig.create();
    AuditLogReadConfig readConfig = AuditLogReadConfig.create();
    Gson mapper = getJsonMapper();
    Communicator auditLogService = new ServiceCommunicator(HttpClient.newHttpClient(), writeConfig, mapper);
    Communicator auditLogManagementService = new ServiceCommunicator(HttpClient.newHttpClient(), readConfig, mapper);
    return new AuditLogClientImpl(auditLogService, auditLogManagementService, mapper);
  }

  public static Gson getJsonMapper() {
    return new GsonBuilder()
        .registerTypeAdapter(Instant.class, new InstantConverter())
        .excludeFieldsWithoutExposeAnnotation()
        .create();
  }
}
