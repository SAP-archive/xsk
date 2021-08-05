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

import javax.validation.constraints.NotNull;

public class ExecuteMigrationRequestBody extends MigrationRequestBody {

  private final String connectionId;
  private final String vendor;
  private final String workspace;
  private final String du;

  public ExecuteMigrationRequestBody(NeoData neo, HanaData hana, String connectionId, String vendor, String workspace, String du) {
    super(neo, hana);
    this.connectionId = connectionId;
    this.vendor = vendor;
    this.workspace = workspace;
    this.du = du;
  }

  @NotNull
  public String getConnectionId() {
    return connectionId;
  }

  @NotNull
  public String getVendor() {
    return vendor;
  }

  @NotNull
  public String getWorkspace() {
    return workspace;
  }

  @NotNull
  public String getDu() {
    return du;
  }
}
