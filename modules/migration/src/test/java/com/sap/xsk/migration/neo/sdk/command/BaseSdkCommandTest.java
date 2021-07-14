package com.sap.xsk.migration.neo.sdk.command;

import com.sap.xsk.migration.tooling.MigrationToolExecutor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

public abstract class BaseSdkCommandTest {

  protected static final String TEST_ACCOUNT = "testAccount";
  protected static final String TEST_HOST = "testHost";
  protected static final String TEST_USER = "testUser";
  protected static final String TEST_PASSWORD = "testPassword";
  protected static final List<String> TEST_NEO_COMMAND_PROCESS_AND_ARGS = Arrays.asList("/bin/bash", "./neo-java8.sh");
  protected static final String TEST_NEO_SDK_DIRECTORY = "neo";

  protected MigrationToolExecutor migrationToolExecutor;

  protected void setup() {
    migrationToolExecutor = mock(MigrationToolExecutor.class);
  }

  protected List<String> createProcessCommandAndArguments(SdkCommandArgs commandArgs, String concreteCommandName) {
    var commandAndArguments = new ArrayList<>(TEST_NEO_COMMAND_PROCESS_AND_ARGS);
    commandAndArguments.add(concreteCommandName);
    commandAndArguments.addAll(commandArgs.commandLineArgs());
    return commandAndArguments;
  }
}
