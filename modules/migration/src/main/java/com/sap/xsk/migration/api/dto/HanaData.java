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
package com.sap.xsk.migration.api.dto;

public class HanaData {

  private final String databaseSchema;
  private final String username;
  private final String password;

  public HanaData(String databaseSchema, String username, String password) {
    this.databaseSchema = databaseSchema;
    this.username = username;
    this.password = password;
  }

  public String getDatabaseSchema() {
    return databaseSchema;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
