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
package com.sap.xsk.migration.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NeoData {

  private final String hostName;
  private final String subaccount;
  private final String username;
  private final String password;

  public NeoData(String hostName, String subaccount, String username, String password) {
    this.hostName = hostName;
    this.subaccount = subaccount;
    this.username = username;
    this.password = password;
  }

  @NotBlank
  public String getHostName() {
    return hostName;
  }

  @NotBlank
  public String getSubaccount() {
    return subaccount;
  }

  @NotBlank
  @Email
  public String getUsername() {
    return username;
  }

  @NotBlank
  public String getPassword() {
    return password;
  }
}
