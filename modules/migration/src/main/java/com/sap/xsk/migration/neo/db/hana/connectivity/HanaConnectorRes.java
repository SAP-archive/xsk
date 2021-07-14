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
package com.sap.xsk.migration.neo.db.hana.connectivity;

public class HanaConnectorRes {

  private final String host;
  private final int port;
  private final String dbType;
  private final String jdbcUrl;
  private final String instanceNumber;
  private final String dbUser;
  private final String dbSchema;
  private final String sessionId;

  public HanaConnectorRes(String host, int port, String dbType, String jdbcUrl, String instanceNumber, String dbUser,
      String dbSchema, String sessionId) {
    this.host = host;
    this.port = port;
    this.dbType = dbType;
    this.jdbcUrl = jdbcUrl;
    this.instanceNumber = instanceNumber;
    this.dbUser = dbUser;
    this.dbSchema = dbSchema;
    this.sessionId = sessionId;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public String getDbType() {
    return dbType;
  }

  public String getJdbcUrl() {
    return jdbcUrl;
  }

  public String getInstanceNumber() {
    return instanceNumber;
  }

  public String getDbUser() {
    return dbUser;
  }

  public String getDbSchema() {
    return dbSchema;
  }

  public String getSessionId() {
    return sessionId;
  }
}
