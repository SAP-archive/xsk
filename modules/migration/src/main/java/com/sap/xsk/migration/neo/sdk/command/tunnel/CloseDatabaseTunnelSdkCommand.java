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

import com.sap.xsk.migration.neo.sdk.command.SdkCommand;
import com.sap.xsk.migration.neo.sdk.parse.SdkCommandParsedOutput;
import com.sap.xsk.migration.neo.sdk.parse.SdkCommandOutputParser;
import com.sap.xsk.migration.tooling.MigrationToolExecutor;

public class CloseDatabaseTunnelSdkCommand implements SdkCommand<CloseDatabaseTunnelSdkCommandArgs, CloseDatabaseTunnelSdkCommandRes> {

  private static final String CLOSE_DATABASE_TUNNEL_COMMAND_NAME = "close-db-tunnel";

  private final MigrationToolExecutor migrationToolExecutor;
  private final SdkCommandOutputParser sdkCommandOutputParser;

  public CloseDatabaseTunnelSdkCommand(MigrationToolExecutor migrationToolExecutor,
      SdkCommandOutputParser sdkCommandOutputParser) {
    this.migrationToolExecutor = migrationToolExecutor;
    this.sdkCommandOutputParser = sdkCommandOutputParser;
  }

  @Override
  public CloseDatabaseTunnelSdkCommandRes execute(CloseDatabaseTunnelSdkCommandArgs commandArgs) {
    String rawCommandOutput = migrationToolExecutor
        .executeMigrationTool(NEO_SDK_DIRECTORY, NEO_SDK_NAME, CLOSE_DATABASE_TUNNEL_COMMAND_NAME, commandArgs.commandLineArgs());
    SdkCommandParsedOutput<CloseDatabaseTunnelSdkCommandRes> parsedCommandOutput = sdkCommandOutputParser.parse(rawCommandOutput);
    return parsedCommandOutput.getResult();
  }
}
