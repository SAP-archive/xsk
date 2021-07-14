package com.sap.xsk.migration.neo.sdk.command.tunnel;

import com.sap.xsk.migration.neo.sdk.command.BaseSdkCommandTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CloseDatabaseTunnelSdkCommandTest extends BaseSdkCommandTest {

  private static final String TEST_CLOSE_DATABASE_TUNNEL_COMMAND_NAME = "close-db-tunnel";
  private static final String TEST_SESSION_ID = "testSessionId";
  private static final CloseDatabaseTunnelSdkCommandArgs TEST_COMMAND_ARGS = new CloseDatabaseTunnelSdkCommandArgs(TEST_SESSION_ID);
  private static final String TEST_COMMAND_OUTPUT = "{\n"
      + "    \"command\": \"close-db-tunnel\",\n"
      + "    \"argLine\": \"--session-id 9820bdf8-4614-4474-87ac-eebe7d11ae93 --output json\",\n"
      + "    \"pid\": \"close-db-tunnel_1624286041782\",\n"
      + "    \"exitCode\": 0,\n"
      + "    \"errorMsg\": null,\n"
      + "    \"commandOutput\": \"\\nTrying to connect to background process...\\nConnected to background process\\nInvoking close tunnel command on background process ...\\nWARNING: The background tunnel session cannot be found. Maybe the background tunnel expired or was terminated. Check background tunnel manager\\u0027s log file \\u0027./log/console_tunnel_background_1919.log\\u0027 for further information.\\n\\n\",\n"
      + "    \"commandErrorOutput\": \"\",\n"
      + "    \"result\": null\n"
      + "}";

  private CloseDatabaseTunnelSdkCommand closeDatabaseTunnelSdkCommand;

  @Before
  @Override
  public void setup() {
    super.setup();
    closeDatabaseTunnelSdkCommand = new CloseDatabaseTunnelSdkCommand(migrationToolExecutor);
  }

  @Test
  public void testExecuteCommand() {
    when(migrationToolExecutor.executeMigrationTool(
        TEST_NEO_SDK_DIRECTORY,
        createProcessCommandAndArguments(TEST_COMMAND_ARGS, TEST_CLOSE_DATABASE_TUNNEL_COMMAND_NAME))
    ).thenReturn(TEST_COMMAND_OUTPUT);

    Void closeDatabaseTunnelSdkCommandRes = closeDatabaseTunnelSdkCommand.execute(TEST_COMMAND_ARGS);

    assertNull("Unexpected command result", closeDatabaseTunnelSdkCommandRes);
  }
}