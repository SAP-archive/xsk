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

import com.google.gson.reflect.TypeToken;
import com.sap.xsk.migration.neo.sdk.command.AbstractSdkCommand;
import com.sap.xsk.migration.neo.sdk.command.SdkCommand;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandArgs;
import com.sap.xsk.migration.neo.sdk.parse.SdkCommandParsedOutput;
import com.sap.xsk.migration.neo.sdk.parse.SdkCommandOutputParser;
import com.sap.xsk.migration.tooling.MigrationToolExecutor;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

class CloseDatabaseTunnelSdkCommand extends AbstractSdkCommand<CloseDatabaseTunnelSdkCommandArgs, CloseDatabaseTunnelSdkCommandRes> {

  private static final String CLOSE_DATABASE_TUNNEL_COMMAND_NAME = "close-db-tunnel";

  @Inject
  public CloseDatabaseTunnelSdkCommand(MigrationToolExecutor migrationToolExecutor, SdkCommandOutputParser sdkCommandOutputParser) {
    super(migrationToolExecutor, sdkCommandOutputParser);
  }

  @Override
  public CloseDatabaseTunnelSdkCommandRes execute(CloseDatabaseTunnelSdkCommandArgs commandArgs) {
    List<String> commandAndArgs = createProcessCommandAndArguments(commandArgs);
    String rawCommandOutput = migrationToolExecutor.executeMigrationTool(NEO_SDK_DIRECTORY, commandAndArgs);
    SdkCommandParsedOutput<CloseDatabaseTunnelSdkCommandRes> parsedCommandOutput = sdkCommandOutputParser.parse(rawCommandOutput, new TypeToken<>(){});
    return parsedCommandOutput.getResult();
  }

  private List<String> createProcessCommandAndArguments(SdkCommandArgs commandArgs) {
    var commandAndArguments = new ArrayList<>(NEO_SDK_JAVA8_COMMAND_AND_ARGUMENTS);
    commandAndArguments.add(CLOSE_DATABASE_TUNNEL_COMMAND_NAME);
    commandAndArguments.addAll(commandArgs.commandLineArgs());
    return commandAndArguments;
  }
}
