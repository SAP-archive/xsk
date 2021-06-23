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
package com.sap.xsk.migration.neo.sdk.parse;

public class SdkCommandParsedOutput<TCommandRes> {
  private final String command;
  private final String argLine;
  private final String pid;
  private final int exitCode;
  private final String errorMsg;
  private final String commandOutput;
  private final String commandErrorOutput;
  private final TCommandRes result;

  SdkCommandParsedOutput(String command, String argLine, String pid, int exitCode, String errorMsg, String commandOutput,
      String commandErrorOutput, TCommandRes result) {
    this.command = command;
    this.argLine = argLine;
    this.pid = pid;
    this.exitCode = exitCode;
    this.errorMsg = errorMsg;
    this.commandOutput = commandOutput;
    this.commandErrorOutput = commandErrorOutput;
    this.result = result;
  }

  public String getCommand() {
    return command;
  }

  public String getArgLine() {
    return argLine;
  }

  public String getPid() {
    return pid;
  }

  public int getExitCode() {
    return exitCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public String getCommandOutput() {
    return commandOutput;
  }

  public String getCommandErrorOutput() {
    return commandErrorOutput;
  }

  public TCommandRes getResult() {
    return result;
  }
}
