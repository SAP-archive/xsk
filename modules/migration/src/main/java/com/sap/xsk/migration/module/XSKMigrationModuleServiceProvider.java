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
package com.sap.xsk.migration.module;

import com.sap.xsk.migration.neo.db.hana.ConnectionProvider;
import com.sap.xsk.migration.neo.db.hana.DeliveryUnitsExporter;
import com.sap.xsk.migration.neo.db.hana.DeliveryUnitsProvider;
import com.sap.xsk.migration.neo.db.hana.connectivity.HanaConnector;
import com.sap.xsk.migration.neo.db.hana.connectivity.TunneledHanaConnector;
import com.sap.xsk.migration.neo.sdk.command.SdkCommand;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandGenericArgs;
import com.sap.xsk.migration.neo.sdk.command.databases.ListDatabasesSdkCommand;
import com.sap.xsk.migration.neo.sdk.command.databases.ListDatabasesSdkCommandRes;
import com.sap.xsk.migration.neo.sdk.command.tunnel.CloseDatabaseTunnelSdkCommand;
import com.sap.xsk.migration.neo.sdk.command.tunnel.OpenDatabaseTunnelSdkCommand;
import com.sap.xsk.migration.tooling.MigrationToolExecutor;
import com.sap.xsk.migration.tooling.SystemProcessBuilder;
import org.eclipse.dirigible.core.workspace.service.WorkspacesCoreService;
import org.eclipse.dirigible.runtime.transport.processor.TransportProcessor;

public class XSKMigrationModuleServiceProvider {

  private XSKMigrationModuleServiceProvider() {
  }

  public static SdkCommand<SdkCommandGenericArgs, ListDatabasesSdkCommandRes> createListDatabasesSdkCommand() {
    var systemProcessBuilder = new SystemProcessBuilder();
    var migrationToolsExecutor = new MigrationToolExecutor(systemProcessBuilder);
    return new ListDatabasesSdkCommand(migrationToolsExecutor);
  }

  public static HanaConnector createHanaConnector() {
    var systemProcessBuilder = new SystemProcessBuilder();
    var migrationToolsExecutor = new MigrationToolExecutor(systemProcessBuilder);
    var openDatabaseTunnelCommand = new OpenDatabaseTunnelSdkCommand(migrationToolsExecutor);
    var closeDatabaseTunnelCommand = new CloseDatabaseTunnelSdkCommand(migrationToolsExecutor);
    return new TunneledHanaConnector(openDatabaseTunnelCommand, closeDatabaseTunnelCommand);
  }

  public static DeliveryUnitsProvider createDeliveryUnitsProvider() {
    var connectionProvider = new ConnectionProvider();
    return new DeliveryUnitsProvider(connectionProvider);
  }

  public static DeliveryUnitsExporter createDeliveryUnitsExporter() {
    var transportProcessor = new TransportProcessor();
    return new DeliveryUnitsExporter(transportProcessor);
  }

  public static WorkspacesCoreService createWorkspacesCoreService() {
    return new WorkspacesCoreService();
  }
}
