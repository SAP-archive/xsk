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

import com.sap.xsk.migration.neo.sdk.command.SdkCommandArgs;
import java.util.ArrayList;
import java.util.List;

public class CloseDatabaseTunnelSdkCommandArgs implements SdkCommandArgs {

  private final String sessionId;

  public CloseDatabaseTunnelSdkCommandArgs(String sessionId) {
    this.sessionId = sessionId;
  }

  @Override
  public List<String> commandLineArgs() {
    var commandLineArgs = new ArrayList<String>();
    commandLineArgs.add("--session-id " + sessionId);
    return commandLineArgs;
  }
}