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

import com.google.gson.reflect.TypeToken;
import com.sap.xsk.migration.neo.sdk.command.AbstractSdkCommand;
import com.sap.xsk.migration.neo.sdk.command.SdkCommand;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandArgs;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandGenericArgs;
import com.sap.xsk.migration.neo.sdk.parse.SdkCommandOutputParser;
import com.sap.xsk.migration.neo.sdk.parse.SdkCommandParsedOutput;
import com.sap.xsk.migration.tooling.MigrationToolExecutor;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ListDatabasesSdkCommand extends AbstractSdkCommand<SdkCommandGenericArgs, ListDatabasesSdkCommandRes> {

  private static final String LIST_DATABASES_COMMAND_NAME = "list-dbs";

  @Inject
  public ListDatabasesSdkCommand(MigrationToolExecutor migrationToolExecutor, SdkCommandOutputParser sdkCommandOutputParser) {
    super(migrationToolExecutor, sdkCommandOutputParser);
  }

  @Override
  public ListDatabasesSdkCommandRes execute(SdkCommandGenericArgs commandArgs) {
    List<String> commandAndArgs = createProcessCommandAndArguments(commandArgs);
    String rawCommandOutput = migrationToolExecutor.executeMigrationTool(NEO_SDK_DIRECTORY, commandAndArgs);
    SdkCommandParsedOutput<Object> parsedCommandOutput = sdkCommandOutputParser.parse(rawCommandOutput, new TypeToken<>(){});
    return parseCommandOutput(parsedCommandOutput.getCommandOutput());
  }

  private List<String> createProcessCommandAndArguments(SdkCommandArgs commandArgs) {
    var commandAndArguments = new ArrayList<>(NEO_SDK_JAVA8_COMMAND_AND_ARGUMENTS);
    commandAndArguments.add(LIST_DATABASES_COMMAND_NAME);
    commandAndArguments.addAll(commandArgs.commandLineArgs());
    return commandAndArguments;
  }

  private ListDatabasesSdkCommandRes parseCommandOutput(String commandOutput) {
    var outputLines = commandOutput.split("\\r?\\n");

    var dbIds = Arrays
        .stream(outputLines)
        .skip(2)
        .map(x -> x.replace(" ",""))
        .collect(Collectors.toList());

    return new ListDatabasesSdkCommandRes(dbIds);
  }
}
