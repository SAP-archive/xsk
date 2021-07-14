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
import com.sap.xsk.migration.tooling.MigrationToolExecutor;

import javax.inject.Inject;
import java.util.List;

public class CloseDatabaseTunnelSdkCommand implements SdkCommand<CloseDatabaseTunnelSdkCommandArgs, Void> {

  private static final String CLOSE_DATABASE_TUNNEL_COMMAND_NAME = "close-db-tunnel";

  private final MigrationToolExecutor migrationToolExecutor;

  @Inject
  public CloseDatabaseTunnelSdkCommand(MigrationToolExecutor migrationToolExecutor) {
    this.migrationToolExecutor = migrationToolExecutor;
  }

  @Override
  public Void execute(CloseDatabaseTunnelSdkCommandArgs commandArgs) {
    List<String> commandAndArgs = createProcessCommandAndArguments(commandArgs, CLOSE_DATABASE_TUNNEL_COMMAND_NAME);
    migrationToolExecutor.executeMigrationTool(NEO_SDK_DIRECTORY, commandAndArgs);
    return null;
  }
}
