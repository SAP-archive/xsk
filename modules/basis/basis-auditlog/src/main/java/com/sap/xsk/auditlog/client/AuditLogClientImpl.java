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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sap.xsk.auditlog.client.exceptions.ServiceException;
import com.sap.xsk.auditlog.client.http.Communicator;
import com.sap.xsk.auditlog.client.logs.Log;
import com.sap.xsk.auditlog.client.messages.AuditLogCategory;
import com.sap.xsk.auditlog.client.messages.AuditLogMessage;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

class AuditLogClientImpl implements AuditLogClient {

  private static final String BASE_SEND_AUDITLOG_API_PATH = "/audit-log/oauth2/v2";
  private static final String RETRIEVE_AUDITLOG_PATH = "/auditlog/v2/auditlogrecords";

  private static final Logger logger = Logger.getLogger(AuditLogClientImpl.class.getName());

  private final Gson jsonMapper;
  private final Communicator writer;
  private final Communicator reader;

  AuditLogClientImpl(Communicator writer, Communicator reader, Gson jsonMapper) {
    this.writer = writer;
    this.reader = reader;
    this.jsonMapper = jsonMapper;
  }

  @Override
  public void log(AuditLogMessage message) throws ServiceException {
    String payload = serializeMessage(message);
    String endpoint = getSendEndpoint(message.getCategory());

    String token = null;
    if (Objects.isNull(message.getSubscriberTokenIssuer())) {
      logger.fine("Retrieving OAuth token for the Write API");
      token = writer.retrieveOAuthToken();
    } else {
      logger.fine("Retrieving OAuth token for the Write API from the subscriber token issuer [" + message.getSubscriberTokenIssuer() + "]");
      token = writer.retrieveOAuthToken(message.getSubscriberTokenIssuer());
    }

    logger.fine("Sending message to the Audit Log");
    writer.send(BASE_SEND_AUDITLOG_API_PATH + endpoint, payload, token);
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
    logger.fine("Retrieving OAuth token for the Read API");
    String token = reader.retrieveOAuthToken();

    logger.fine("Requesting logs from the Audit Log");
    String responseBody = reader.get(apiPath, token);

    try {
      return Arrays.asList(jsonMapper.fromJson(responseBody, Log[].class));
    } catch (JsonSyntaxException ex) {
      logger.warning("Couldn't deserialize the response [" + responseBody + "] from the server");
      throw new ServiceException("Problem with the response from the server. Reason :" + ex);
    }
  }

  private String serializeMessage(AuditLogMessage message) throws ServiceException {
    try {
      return jsonMapper.toJson(message);
    } catch (Exception ex) {
      throw new ServiceException("Problem with the serialization of the message. Reason :" + ex);
    }
  }

  private String getLogRetrievalInPeriodPath(Instant from, Instant to) {
    return String.format("%s?time_from=%s&time_to=%s", RETRIEVE_AUDITLOG_PATH, from, to);
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
