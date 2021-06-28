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

  public String getConnectionId() {
    return connectionId;
  }

  public String getVendor() {
    return vendor;
  }

  public String getWorkspace() {
    return workspace;
  }

  public String getDu() {
    return du;
  }
}
