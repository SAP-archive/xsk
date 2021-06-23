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
package com.sap.xsk.migration.neo.sdk.command.tunnel;

import com.sap.xsk.migration.neo.sdk.command.SdkCommandGenericArgs;
import java.util.ArrayList;
import java.util.List;

public class OpenDatabaseTunnelSdkCommandArgs extends SdkCommandGenericArgs {
  private final String id;

  public OpenDatabaseTunnelSdkCommandArgs(String databaseId, String account, String host, String user, String password) {
    super(account, host, user, password, true);
    id = databaseId;
  }

  @Override
  public List<String> commandLineArgs() {
    var tunnelArgs = new ArrayList<>(super.commandLineArgs());
    tunnelArgs.add("--id " + id);
    tunnelArgs.add("--background");
    return tunnelArgs;
  }
}
