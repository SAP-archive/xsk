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
package com.sap.xsk.migration.neo.sdk.command.tunnel;

import com.sap.xsk.migration.neo.sdk.command.BaseSdkCommandTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class OpenDatabaseTunnelSdkCommandTest extends BaseSdkCommandTest {

  private static final String TEST_OPEN_DATABASE_TUNNEL_COMMAND_NAME = "open-db-tunnel";
  private static final OpenDatabaseTunnelSdkCommandArgs TEST_COMMAND_ARGS = new OpenDatabaseTunnelSdkCommandArgs(
      "testDatabaseId",
      TEST_ACCOUNT,
      TEST_HOST,
      TEST_USER,
      TEST_PASSWORD
  );

  public static final String TEST_DB_HOST = "localhost";
  public static final int TEST_DB_PORT = 30015;
  public static final String TEST_DB_TYPE = "HANAXS";
  public static final String TEST_DB_JDBC_URL = "jdbc:sap://localhost:30015/";
  public static final String TEST_DB_INSTANCE_NUMBER = "00";
  public static final String TEST_DB_USER = "C123123";
  public static final String TEST_DB_SESSION_ID = "4d279730-6451-4984-bec3-0f4dd0e88b9d";

  private static final String TEST_COMMAND_OUTPUT = "{\n"
      + "    \"command\": \"open-db-tunnel\",\n"
      + "    \"argLine\": \"--id slbinno --account e6c9b8dff --host eu2.hana.ondemand.com --user pesho@pesho.com --password ******** --output json --background\",\n"
      + "    \"pid\": \"open-db-tunnel_1624366694413\",\n"
      + "    \"exitCode\": 0,\n"
      + "    \"errorMsg\": null,\n"
      + "    \"commandOutput\": \"Trying to connect to background process...\\nNo background process is running\\nCreating new background process...\\nConnected to background process\\nInvoking open tunnel command on background process ...\\nTunnel opened.\\n\\nUse these properties to connect to your schema:\\n  Host name        : localhost\\n  Database type    : HANAXS\\n  JDBC Url         : jdbc:sap://localhost:30015/\\n  Instance number  : 00\\n  User             : C5326377\\n  Session ID       : 4d279730-6451-4984-bec3-0f4dd0e88b9d\\n\\n\\nThis tunnel session will close automatically in 1 hour on Jun 22, 2021 at about 4:59 PM.\\n\\nFor closing the tunnel, use the following command:\\n    neo close-db-tunnel --session-id 4d279730-6451-4984-bec3-0f4dd0e88b9d\\n\",\n"
      + "    \"commandErrorOutput\": \"\",\n"
      + "    \"result\": {\n"
      + "        \"host\": \"" + TEST_DB_HOST + "\",\n"
      + "        \"port\": " + TEST_DB_PORT + ",\n"
      + "        \"dbType\": \"" + TEST_DB_TYPE + "\",\n"
      + "        \"jdbcUrl\": \"" + TEST_DB_JDBC_URL + "\",\n"
      + "        \"instanceNumber\": \"" + TEST_DB_INSTANCE_NUMBER + "\",\n"
      + "        \"dbUser\": \"" + TEST_DB_USER + "\",\n"
      + "        \"dbUserPassword\": null,\n"
      + "        \"dbSchema\": null,\n"
      + "        \"sessionId\": \"" + TEST_DB_SESSION_ID + "\"\n"
      + "    }\n"
      + "}";


  private OpenDatabaseTunnelSdkCommand openDatabaseTunnelSdkCommand;

  @Before
  public void setup() {
    super.setup();
    openDatabaseTunnelSdkCommand = new OpenDatabaseTunnelSdkCommand(migrationToolExecutor);
  }

  @Test
  public void testExecuteCommand() {
    when(migrationToolExecutor.executeMigrationTool(
        TEST_NEO_SDK_DIRECTORY,
        createProcessCommandAndArguments(TEST_COMMAND_ARGS, TEST_OPEN_DATABASE_TUNNEL_COMMAND_NAME))
    ).thenReturn(TEST_COMMAND_OUTPUT);

    OpenDatabaseTunnelSdkCommandRes openDatabaseTunnelSdkCommandRes = openDatabaseTunnelSdkCommand.execute(TEST_COMMAND_ARGS);

    assertEquals("Unexpected database host", TEST_DB_HOST, openDatabaseTunnelSdkCommandRes.getHost());
    assertEquals("Unexpected database port", TEST_DB_PORT, openDatabaseTunnelSdkCommandRes.getPort());
    assertEquals("Unexpected database type", TEST_DB_TYPE, openDatabaseTunnelSdkCommandRes.getDbType());
    assertEquals("Unexpected database jdbc url", TEST_DB_JDBC_URL, openDatabaseTunnelSdkCommandRes.getJdbcUrl());
    assertEquals("Unexpected database instance number", TEST_DB_INSTANCE_NUMBER, openDatabaseTunnelSdkCommandRes.getInstanceNumber());
    assertEquals("Unexpected database user", TEST_DB_USER, openDatabaseTunnelSdkCommandRes.getDbUser());
    assertNull("Unexpected database schema", openDatabaseTunnelSdkCommandRes.getDbSchema());
    assertEquals("Unexpected database session id", TEST_DB_SESSION_ID, openDatabaseTunnelSdkCommandRes.getSessionId());
  }
}