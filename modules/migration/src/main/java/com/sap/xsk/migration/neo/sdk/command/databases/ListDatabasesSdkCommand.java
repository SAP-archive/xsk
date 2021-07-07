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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sap.xsk.migration.neo.sdk.command.SdkCommand;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandGenericArgs;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandParsedOutput;
import com.sap.xsk.migration.tooling.MigrationToolExecutor;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListDatabasesSdkCommand implements SdkCommand<SdkCommandGenericArgs, ListDatabasesSdkCommandRes> {

  private static final String LIST_DATABASES_COMMAND_NAME = "list-dbs";

  private final MigrationToolExecutor migrationToolExecutor;

  @Inject
  public ListDatabasesSdkCommand(MigrationToolExecutor migrationToolExecutor) {
    this.migrationToolExecutor = migrationToolExecutor;
  }

  @Override
  public ListDatabasesSdkCommandRes execute(SdkCommandGenericArgs commandArgs) {
    List<String> commandAndArgs = createProcessCommandAndArguments(commandArgs, LIST_DATABASES_COMMAND_NAME);
    String rawCommandOutput = migrationToolExecutor.executeMigrationTool(NEO_SDK_DIRECTORY, commandAndArgs);
    SdkCommandParsedOutput<Void> parsedCommandOutput = new Gson().fromJson(rawCommandOutput, new TypeToken<SdkCommandParsedOutput<Void>>() {
    }.getType());
    return parseCommandOutput(parsedCommandOutput.getCommandOutput());
  }

  private ListDatabasesSdkCommandRes parseCommandOutput(String commandOutput) {
    var outputLines = commandOutput.split("\\r?\\n");

    var dbIds = Arrays
        .stream(outputLines)
        .skip(2)
        .map(x -> x.replace(" ", ""))
        .collect(Collectors.toList());

    return new ListDatabasesSdkCommandRes(dbIds);
  }
}
