package com.sap.xsk.migration.tooling;

import org.apache.kerby.config.Conf;
import org.eclipse.dirigible.commons.config.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class CommandLineMigrationToolExecutorTest {

  private static final long TEST_TIMEOUT = 3;
  private static final TimeUnit TEST_TIMEOUT_UNIT = TimeUnit.SECONDS;
  private static final long TEST_DEFAULT_TIMEOUT = 2;
  private static final TimeUnit TEST_DEFAULT_TIMEOUT_UNIT = TimeUnit.MINUTES;
  private static final String TEST_MIGRATION_TOOLS_DIRECTORY = "migration-tools";
  private static final String TEST_MIGRATION_TOOL_DIRECTORY = "testMigrationTool";
  private static final List<String> TEST_MIGRATION_TOOL_COMMAND_AND_ARGS = Arrays.asList("testCommand", "testArg");
  private static final String TEST_CATALINA_HOME_ENV_VAR_NAME = "CATALINA_HOME";
  private static final String TEST_CATALINA_HOME = "/catalina";
  private static final String TEST_DIRECTORY_ABSOLUTE_PATH = Paths.get(TEST_CATALINA_HOME, TEST_MIGRATION_TOOLS_DIRECTORY,
      TEST_MIGRATION_TOOL_DIRECTORY).toString();
  private static final String TEST_PROCESS_OUTPUT = "testOutput";
  private static final String TEST_PROCESS_ERROR_OUTPUT = "testErrorOutput";
  private static final String TEST_PROCESS_NOT_FINISHED_ON_TIME = "Process did not finish on time!";
  private static final String TEST_PROCESS_WAITING_THREAD_INTERRUPTED = "Process waiting thread interrupted!";
  private static final String TEST_PROCESS_COULD_NOT_BE_EXECUTED = "Could not execute process!";
  private static final String TEST_PROCESS_DIRECTORY_COULD_NOT_BE_CREATED = "Could not create process directory! CATALINA_HOME not found!";
  private static final int TEST_PROCESS_SUCCESS_EXIT_CODE = 0;
  private static final int TEST_PROCESS_ERROR_EXIT_CODE = 1;

  @Mock
  private SystemProcessBuilder mockSystemProcessBuilder;

  @Mock
  private Process mockProcess;

  private InputStream mockProcessOutput;
  private InputStream mockProcessErrorOutput;
  private MigrationToolExecutor migrationToolExecutor;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    mockProcessOutput = IOUtils.toInputStream(TEST_PROCESS_OUTPUT, StandardCharsets.UTF_8);
    mockProcessErrorOutput = IOUtils.toInputStream(TEST_PROCESS_ERROR_OUTPUT, StandardCharsets.UTF_8);
    Configuration.set(TEST_CATALINA_HOME_ENV_VAR_NAME, TEST_CATALINA_HOME);

    migrationToolExecutor = new MigrationToolExecutor(mockSystemProcessBuilder);
  }

  @Test
  public void testExecuteMigrationToolWithDefaultTimeoutConfig() throws IOException, InterruptedException {
    when(mockSystemProcessBuilder.startProcess(
        argThat(dir -> TEST_DIRECTORY_ABSOLUTE_PATH.equals(dir.getAbsolutePath())),
        eq(TEST_MIGRATION_TOOL_COMMAND_AND_ARGS))
    ).thenReturn(mockProcess);
    when(mockProcess.waitFor(TEST_DEFAULT_TIMEOUT, TEST_DEFAULT_TIMEOUT_UNIT)).thenReturn(true);
    when(mockProcess.exitValue()).thenReturn(TEST_PROCESS_SUCCESS_EXIT_CODE);
    when(mockProcess.getInputStream()).thenReturn(mockProcessOutput);

    String processOutput = migrationToolExecutor.executeMigrationTool(
        TEST_MIGRATION_TOOL_DIRECTORY,
        TEST_MIGRATION_TOOL_COMMAND_AND_ARGS
    );

    assertEquals("Unexpected process output", TEST_PROCESS_OUTPUT, processOutput);
  }

  @Test
  public void testExecuteMigrationToolWithNonDefaultTimeoutConfig() throws IOException, InterruptedException {
    when(mockSystemProcessBuilder.startProcess(
        argThat(dir -> TEST_DIRECTORY_ABSOLUTE_PATH.equals(dir.getAbsolutePath())),
        eq(TEST_MIGRATION_TOOL_COMMAND_AND_ARGS))
    ).thenReturn(mockProcess);
    when(mockProcess.waitFor(TEST_TIMEOUT, TEST_TIMEOUT_UNIT)).thenReturn(true);
    when(mockProcess.exitValue()).thenReturn(TEST_PROCESS_SUCCESS_EXIT_CODE);
    when(mockProcess.getInputStream()).thenReturn(mockProcessOutput);

    String processOutput = migrationToolExecutor.executeMigrationTool(
        TEST_MIGRATION_TOOL_DIRECTORY,
        TEST_MIGRATION_TOOL_COMMAND_AND_ARGS,
        TEST_TIMEOUT,
        TEST_TIMEOUT_UNIT
    );

    assertEquals("Unexpected process output", TEST_PROCESS_OUTPUT, processOutput);
  }

  @Test
  public void testExecuteMigrationToolThreadInterrupted() throws IOException, InterruptedException {
    when(mockSystemProcessBuilder.startProcess(
        argThat(dir -> TEST_DIRECTORY_ABSOLUTE_PATH.equals(dir.getAbsolutePath())),
        eq(TEST_MIGRATION_TOOL_COMMAND_AND_ARGS))
    ).thenReturn(mockProcess);
    when(mockProcess.waitFor(anyLong(), any())).thenThrow(InterruptedException.class);

    ShellExecutorException caughtException = assertThrows(
        "Unexpected exception caught",
        ShellExecutorException.class,
        () -> migrationToolExecutor.executeMigrationTool(
            TEST_MIGRATION_TOOL_DIRECTORY,
            TEST_MIGRATION_TOOL_COMMAND_AND_ARGS,
            TEST_TIMEOUT,
            TEST_TIMEOUT_UNIT)
    );

    assertEquals("Unexpected exception message", TEST_PROCESS_WAITING_THREAD_INTERRUPTED, caughtException.getMessage());
    assertEquals("Unexpected exception cause", InterruptedException.class, caughtException.getCause().getClass());
  }

  @Test
  public void testExecuteMigrationToolTimeouts() throws IOException, InterruptedException {
    when(mockSystemProcessBuilder.startProcess(
        argThat(dir -> TEST_DIRECTORY_ABSOLUTE_PATH.equals(dir.getAbsolutePath())),
        eq(TEST_MIGRATION_TOOL_COMMAND_AND_ARGS))
    ).thenReturn(mockProcess);
    when(mockProcess.waitFor(anyLong(), any())).thenReturn(false);

    ShellExecutorException caughtException = assertThrows(
        "Unexpected exception caught",
        ShellExecutorException.class,
        () -> migrationToolExecutor.executeMigrationTool(
            TEST_MIGRATION_TOOL_DIRECTORY,
            TEST_MIGRATION_TOOL_COMMAND_AND_ARGS,
            TEST_TIMEOUT,
            TEST_TIMEOUT_UNIT)
    );

    assertEquals("Unexpected exception message", TEST_PROCESS_NOT_FINISHED_ON_TIME, caughtException.getMessage());
    assertNull("Unexpected exception cause", caughtException.getCause());
  }

  @Test
  public void testExecuteMigrationCouldNotBeStarted() throws IOException {
    when(mockSystemProcessBuilder.startProcess(any(), any())).thenThrow(IOException.class);

    ShellExecutorException caughtException = assertThrows(
        "Unexpected exception caught",
        ShellExecutorException.class,
        () -> migrationToolExecutor.executeMigrationTool(
            TEST_MIGRATION_TOOL_DIRECTORY,
            TEST_MIGRATION_TOOL_COMMAND_AND_ARGS,
            TEST_TIMEOUT,
            TEST_TIMEOUT_UNIT)
    );

    assertEquals("Unexpected exception message", TEST_PROCESS_COULD_NOT_BE_EXECUTED, caughtException.getMessage());
    assertEquals("Unexpected exception cause", IOException.class, caughtException.getCause().getClass());
  }

  @Test
  public void testExecuteMigrationToolExitsWithNonZero() throws IOException, InterruptedException {
    when(mockSystemProcessBuilder.startProcess(
        argThat(dir -> TEST_DIRECTORY_ABSOLUTE_PATH.equals(dir.getAbsolutePath())),
        eq(TEST_MIGRATION_TOOL_COMMAND_AND_ARGS))
    ).thenReturn(mockProcess);
    when(mockProcess.waitFor(TEST_TIMEOUT, TEST_TIMEOUT_UNIT)).thenReturn(true);
    when(mockProcess.exitValue()).thenReturn(TEST_PROCESS_ERROR_EXIT_CODE);
    when(mockProcess.getErrorStream()).thenReturn(mockProcessErrorOutput);

    ShellExecutorException caughtException = assertThrows(
        "Unexpected exception caught",
        ShellExecutorException.class,
        () -> migrationToolExecutor.executeMigrationTool(
            TEST_MIGRATION_TOOL_DIRECTORY,
            TEST_MIGRATION_TOOL_COMMAND_AND_ARGS,
            TEST_TIMEOUT,
            TEST_TIMEOUT_UNIT
        )
    );

    assertEquals("Unexpected exception message", createTestProcessErrorString(), caughtException.getMessage());
    assertNull("Unexpected exception cause", caughtException.getCause());
  }

  private String createTestProcessErrorString() throws IOException {
    return "Process exit code: "
        + TEST_PROCESS_ERROR_EXIT_CODE
        + " Process error: "
        + TEST_PROCESS_ERROR_OUTPUT;
  }

  @Test
  public void testExecuteMigrationToolCouldNotCreateProcessDirectory() throws IOException, InterruptedException {
    Configuration.remove(TEST_CATALINA_HOME_ENV_VAR_NAME);

    ShellExecutorException caughtException = assertThrows(
        "Unexpected exception caught",
        ShellExecutorException.class,
        () -> migrationToolExecutor.executeMigrationTool(
            TEST_MIGRATION_TOOL_DIRECTORY,
            TEST_MIGRATION_TOOL_COMMAND_AND_ARGS,
            TEST_TIMEOUT,
            TEST_TIMEOUT_UNIT
        )
    );

    assertEquals("Unexpected exception message", TEST_PROCESS_DIRECTORY_COULD_NOT_BE_CREATED, caughtException.getMessage());
    assertNull("Unexpected exception cause", caughtException.getCause());
  }

}