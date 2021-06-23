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
package com.sap.xsk.migration.api.database;

public class HanaDatabaseIdsRequestBody {
  private final String account;
  private final String host;
  private final String user;
  private final String password;

  public HanaDatabaseIdsRequestBody(String account, String host, String user, String password) {
    this.account = account;
    this.host = host;
    this.user = user;
    this.password = password;
  }

  public String getAccount() {
    return account;
  }

  public String getHost() {
    return host;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }
}
