/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.migration.neo.sdk.command.databases;

import com.sap.xsk.migration.neo.sdk.command.BaseSdkCommandTest;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandGenericArgs;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ListDatabasesSdkCommandTest extends BaseSdkCommandTest {

  private final SdkCommandGenericArgs TEST_COMMAND_ARGS = new SdkCommandGenericArgs(TEST_ACCOUNT, TEST_HOST, TEST_USER, TEST_PASSWORD);
  private static final String TEST_LIST_DATABASES_COMMAND_NAME = "list-dbs";
  private static final String TEST_DB_ID = "testDbId";
  private static final String TEST_COMMAND_OUTPUT = "{\n"
      + "    \"command\": \"list-dbs\",\n"
      + "    \"argLine\": \"--account e6c9b8dff --host eu2.hana.ondemand.com --user pesho@pesho.com --password ******** --output json\",\n"
      + "    \"pid\": \"list-dbs_1624286088541\",\n"
      + "    \"exitCode\": 0,\n"
      + "    \"errorMsg\": null,\n"
      + "    \"commandOutput\": \"\\nDatabase ID\\n  " + TEST_DB_ID + "  \\n\",\n"
      + "    \"commandErrorOutput\": \"\",\n"
      + "    \"result\": null\n"
      + "}";

  private ListDatabasesSdkCommand listDatabasesSdkCommand;

  @Before
  @Override
  public void setup() {
    super.setup();
    listDatabasesSdkCommand = new ListDatabasesSdkCommand(migrationToolExecutor);
  }

  @Test
  public void testExecuteCommand() {
    when(migrationToolExecutor.executeMigrationTool(
        TEST_NEO_SDK_DIRECTORY,
        createProcessCommandAndArguments(TEST_COMMAND_ARGS, TEST_LIST_DATABASES_COMMAND_NAME))
    ).thenReturn(TEST_COMMAND_OUTPUT);

    ListDatabasesSdkCommandRes listDatabasesSdkCommandRes = listDatabasesSdkCommand.execute(TEST_COMMAND_ARGS);

    assertEquals("Unexpected databases count", 1, listDatabasesSdkCommandRes.getDatabaseIds().size());
    assertEquals("Unexpected database id", TEST_DB_ID, listDatabasesSdkCommandRes.getDatabaseIds().get(0));
  }
}