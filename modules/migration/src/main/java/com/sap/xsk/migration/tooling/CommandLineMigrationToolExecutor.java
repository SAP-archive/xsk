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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommandLineMigrationToolExecutor implements MigrationToolExecutor {

  protected static final long DEFAULT_TIMEOUT = 2;
  protected static final TimeUnit DEFAULT_TIMEOUT_UNIT = TimeUnit.MINUTES;
  private static final String MIGRATION_TOOLS_DIRECTORY = "migration-tools";

  @Override
  public String executeMigrationTool(String migrationToolDirectory, String migrationToolName, String command, List<String> args) {
    return executeMigrationTool(migrationToolDirectory, migrationToolName, command, args, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT_UNIT);
  }

  @Override
  public String executeMigrationTool(String migrationToolDirectory, String migrationToolName, String command, List<String> args, long timeout, TimeUnit timeoutUnit) {
    var allArgs = new ArrayList<String>();
    allArgs.add(command);
    allArgs.addAll(args);
    return executeMigrationTool(migrationToolDirectory, migrationToolName, allArgs, timeout, timeoutUnit);
  }

  @Override
  public String executeMigrationTool(String migrationToolDirectory, String migrationToolName, List<String> args) {
    return executeMigrationTool(migrationToolDirectory, migrationToolName, args, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT_UNIT);
  }

  @Override
  public String executeMigrationTool(String migrationToolDirectory, String migrationToolName, List<String> args, long timeout, TimeUnit timeoutUnit) {
    var processBuilder = new ProcessBuilder();

    processBuilder.directory(createToolDirectoryFile(migrationToolDirectory));
    processBuilder.command(args);

    try {
      var process = processBuilder.start();
      var hasProcessFinished = process.waitFor(timeout, timeoutUnit);

      if (!hasProcessFinished) {
        throw new ShellExecutorException("Process did not finish on time!");
      }

      var processExitCode = process.exitValue();
      if (processExitCode != 0) {
        var processErrorStream = process.getErrorStream();
        var processErrorString = createProcessErrorString(processExitCode, processErrorStream);
        throw new ShellExecutorException(processErrorString);
      }

      var processOutput = process.getInputStream();
      return IOUtils.toString(processOutput, StandardCharsets.UTF_8);

    } catch (IOException | InterruptedException e) {
      throw new ShellExecutorException("Could not execute process!", e);
    }
  }

  private File createToolDirectoryFile(String toolDirectory) {
    String catalinaHome = System.getenv("CATALINA_HOME");
    if (catalinaHome == null) {
      throw new ShellExecutorException("Could not create process directory! CATALINA_HOME not found!");
    }

    var toolsDirectoryPath = Paths.get(catalinaHome, MIGRATION_TOOLS_DIRECTORY, toolDirectory);
    return new File(toolsDirectoryPath.toString());
  }

  private String createProcessErrorString(int exitCode, InputStream errorStream) {
    var errorBuilder = new StringBuilder();
    errorBuilder.append("Process exit code: ");
    errorBuilder.append(exitCode);

    try {
      var errorString = IOUtils.toString(errorStream, StandardCharsets.UTF_8);
      errorBuilder.append(" Process error: ");
      errorBuilder.append(errorString);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return errorBuilder.toString();
  }
}
