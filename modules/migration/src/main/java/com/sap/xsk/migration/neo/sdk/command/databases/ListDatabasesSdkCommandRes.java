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
package com.sap.xsk.migration.neo.sdk.command.databases;

import com.sap.xsk.migration.neo.sdk.command.SdkCommandRes;
import java.util.List;

public class ListDatabasesSdkCommandRes implements SdkCommandRes {
  private final List<String> databaseIds;

  public ListDatabasesSdkCommandRes(List<String> databaseIds) {
    this.databaseIds = databaseIds;
  }

  public List<String> getDatabaseIds() {
    return databaseIds;
  }
}
