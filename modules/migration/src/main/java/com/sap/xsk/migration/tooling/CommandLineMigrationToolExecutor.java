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
package com.sap.xsk.migration.tooling;

import org.apache.commons.io.IOUtils;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

class CommandLineMigrationToolExecutor implements MigrationToolExecutor {

  protected static final long DEFAULT_TIMEOUT = 2;
  protected static final TimeUnit DEFAULT_TIMEOUT_UNIT = TimeUnit.MINUTES;
  private static final String MIGRATION_TOOLS_DIRECTORY = "migration-tools";
  private static final String PROCESS_NOT_FINISHED_ON_TIME = "Process did not finish on time!";
  private static final String PROCESS_WAITING_THREAD_INTERRUPTED = "Process waiting thread interrupted!";
  private static final String PROCESS_COULD_NOT_BE_EXECUTED = "Could not execute process!";
  private static final String PROCESS_DIRECTORY_COULD_NOT_BE_CREATED = "Could not create process directory! CATALINA_HOME not found!";

  private final SystemProcessBuilder systemProcessBuilder;
  private final SystemEnvironment systemEnvironment;
  private final InputStreamStringReader inputStreamStringReader;

  @Inject
  CommandLineMigrationToolExecutor(SystemProcessBuilder systemProcessBuilder, SystemEnvironment systemEnvironment,
      InputStreamStringReader inputStreamStringReader) {
    this.systemProcessBuilder = systemProcessBuilder;
    this.systemEnvironment = systemEnvironment;
    this.inputStreamStringReader = inputStreamStringReader;
  }

  @Override
  public String executeMigrationTool(String migrationToolDirectory, List<String> commandAndArgs) {
    return executeMigrationTool(migrationToolDirectory, commandAndArgs, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT_UNIT);
  }

  @Override
  public String executeMigrationTool(String migrationToolDirectory, List<String> commandAndArgs, long timeout, TimeUnit timeoutUnit) {
    File processDirectory = createProcessDirectory(migrationToolDirectory);

    try {
      var process = systemProcessBuilder.startProcess(processDirectory, commandAndArgs);
      var hasProcessFinished = process.waitFor(timeout, timeoutUnit);

      if (!hasProcessFinished) {
        throw new ShellExecutorException(PROCESS_NOT_FINISHED_ON_TIME);
      }

      var processExitCode = process.exitValue();
      if (processExitCode != 0) {
        var processErrorStream = process.getErrorStream();
        var processErrorString = createProcessErrorString(processExitCode, processErrorStream);
        throw new ShellExecutorException(processErrorString);
      }

      var processOutput = process.getInputStream();
      return inputStreamStringReader.readToString(processOutput, StandardCharsets.UTF_8);

    } catch (IOException e) {
      throw new ShellExecutorException(PROCESS_COULD_NOT_BE_EXECUTED, e);
    } catch (InterruptedException e) {
      throw new ShellExecutorException(PROCESS_WAITING_THREAD_INTERRUPTED, e);
    }
  }

  private File createProcessDirectory(String toolDirectory) {
    String catalinaHome = systemEnvironment.getEnvironmentVariableValue("CATALINA_HOME");
    if (catalinaHome == null) {
      throw new ShellExecutorException(PROCESS_DIRECTORY_COULD_NOT_BE_CREATED);
    }

    var toolsDirectoryPath = Paths.get(catalinaHome, MIGRATION_TOOLS_DIRECTORY, toolDirectory);
    return new File(toolsDirectoryPath.toString());
  }

  private String createProcessErrorString(int exitCode, InputStream errorStream) throws IOException {
    var errorBuilder = new StringBuilder();
    errorBuilder.append("Process exit code: ");
    errorBuilder.append(exitCode);

    var errorString = inputStreamStringReader.readToString(errorStream, StandardCharsets.UTF_8);
    errorBuilder.append(" Process error: ");
    errorBuilder.append(errorString);

    return errorBuilder.toString();
  }
}
