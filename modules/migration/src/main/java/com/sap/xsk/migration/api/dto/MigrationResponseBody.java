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

import java.util.List;

public class MigrationResponseBody {

  private final String connectionId;
  private final List<String> workspaces;
  private final List<DeliveryUnitData> du;

  public MigrationResponseBody(String connectionId, List<String> workspaces, List<DeliveryUnitData> du) {
    this.connectionId = connectionId;
    this.workspaces = workspaces;
    this.du = du;
  }

  public String getConnectionId() {
    return connectionId;
  }

  public List<String> getWorkspaces() {
    return workspaces;
  }

  public List<DeliveryUnitData> getDu() {
    return du;
  }
}
