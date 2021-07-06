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
package com.sap.xsk.migration.neo.sdk.command;

import com.sap.xsk.migration.tooling.MigrationToolExecutor;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSdkCommand<TArgs extends SdkCommandArgs, TRes extends SdkCommandRes> implements SdkCommand<TArgs, TRes> {

  protected final MigrationToolExecutor migrationToolExecutor;

  @Inject
  public AbstractSdkCommand(MigrationToolExecutor migrationToolExecutor) {
    this.migrationToolExecutor = migrationToolExecutor;;
  }

  protected List<String> createProcessCommandAndArguments(SdkCommandArgs commandArgs, String concreteCommandName) {
    var commandAndArguments = new ArrayList<>(NEO_SDK_JAVA8_COMMAND_AND_ARGUMENTS);
    commandAndArguments.add(concreteCommandName);
    commandAndArguments.addAll(commandArgs.commandLineArgs());
    return commandAndArguments;
  }
}
