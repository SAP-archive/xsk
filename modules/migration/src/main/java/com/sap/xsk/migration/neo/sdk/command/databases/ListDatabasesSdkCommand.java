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

import com.sap.xsk.migration.neo.sdk.command.SdkCommand;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandGenericArgs;
import com.sap.xsk.migration.neo.sdk.parse.SdkCommandOutputParser;
import com.sap.xsk.migration.neo.sdk.parse.SdkCommandParsedOutput;
import com.sap.xsk.migration.tooling.MigrationToolExecutor;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ListDatabasesSdkCommand implements SdkCommand<SdkCommandGenericArgs, ListDatabasesSdkCommandRes> {

  private static final String LIST_DATABASES_COMMAND_NAME = "list-dbs";

  private final MigrationToolExecutor migrationToolExecutor;
  private final SdkCommandOutputParser sdkCommandOutputParser;

  public ListDatabasesSdkCommand(MigrationToolExecutor migrationToolExecutor,
      SdkCommandOutputParser sdkCommandOutputParser) {
    this.migrationToolExecutor = migrationToolExecutor;
    this.sdkCommandOutputParser = sdkCommandOutputParser;
  }

  @Override
  public ListDatabasesSdkCommandRes execute(SdkCommandGenericArgs commandArgs) {
    String rawCommandOutput = migrationToolExecutor
        .executeMigrationTool(NEO_SDK_DIRECTORY, NEO_SDK_NAME, LIST_DATABASES_COMMAND_NAME, commandArgs.commandLineArgs());
    SdkCommandParsedOutput<Object> parsedCommandOutput = sdkCommandOutputParser.parse(rawCommandOutput);
    return parseCommandOutput(parsedCommandOutput.getCommandOutput());
  }

  private ListDatabasesSdkCommandRes parseCommandOutput(String commandOutput) {
    // "\nDatabase ID  DB System  DB Type   DB Version                DB State \n  slbinno      slbinno    hanaxs    1.00.122.33.1602166441    CREATED\n"
    var outputLines = commandOutput.split("\\r?\\n");

    var dbIds = Arrays
        .stream(outputLines)
        .skip(1)
        .map(this::getDbIdFromDbInfoLine)
        .collect(Collectors.toList());

    return new ListDatabasesSdkCommandRes(dbIds);
  }

  private String getDbIdFromDbInfoLine(String dbInfoLine) {
    return dbInfoLine.split("\\s*")[0];
  }
}
