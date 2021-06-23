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

import com.sap.xsk.migration.neo.sdk.command.databases.ListDatabasesSdkCommand;
import com.sap.xsk.migration.neo.sdk.command.tunnel.CloseDatabaseTunnelSdkCommand;
import com.sap.xsk.migration.neo.sdk.command.tunnel.OpenDatabaseTunnelSdkCommand;
import com.sap.xsk.migration.neo.sdk.parse.SdkCommandOutputParser;
import com.sap.xsk.migration.tooling.MigrationToolExecutor;
import javax.inject.Inject;

public class SdkCommandFactoryImpl implements SdkCommandFactory {

  private final MigrationToolExecutor migrationToolExecutor;
  private final SdkCommandOutputParser sdkCommandOutputParser;

  @Inject
  public SdkCommandFactoryImpl(MigrationToolExecutor migrationToolExecutor,
      SdkCommandOutputParser sdkCommandOutputParser) {
    this.migrationToolExecutor = migrationToolExecutor;
    this.sdkCommandOutputParser = sdkCommandOutputParser;
  }

  @Override
  public ListDatabasesSdkCommand createListDatabasesSdkCommand() {
    return new ListDatabasesSdkCommand(migrationToolExecutor, sdkCommandOutputParser);
  }

  @Override
  public OpenDatabaseTunnelSdkCommand createOpenDatabaseTunnelSdkCommand() {
    return new OpenDatabaseTunnelSdkCommand(migrationToolExecutor, sdkCommandOutputParser);
  }

  @Override
  public CloseDatabaseTunnelSdkCommand createCloseDatabaseTunnelSdkCommand() {
    return new CloseDatabaseTunnelSdkCommand(migrationToolExecutor, sdkCommandOutputParser);
  }
}
