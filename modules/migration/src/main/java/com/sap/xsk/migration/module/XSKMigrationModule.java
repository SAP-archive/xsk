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
package com.sap.xsk.migration.module;

import com.google.inject.TypeLiteral;
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
import com.sap.xsk.migration.neo.sdk.command.tunnel.CloseDatabaseTunnelSdkCommandArgs;
import com.sap.xsk.migration.neo.sdk.command.tunnel.OpenDatabaseTunnelSdkCommand;
import com.sap.xsk.migration.neo.sdk.command.tunnel.OpenDatabaseTunnelSdkCommandArgs;
import com.sap.xsk.migration.neo.sdk.command.tunnel.OpenDatabaseTunnelSdkCommandRes;
import com.sap.xsk.migration.tooling.MigrationToolExecutor;
import com.sap.xsk.migration.tooling.SystemProcessBuilder;
import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;

public class XSKMigrationModule extends AbstractDirigibleModule {

  @Override
  protected void configure() {
    // Neo SDK commands
    bind(new TypeLiteral<SdkCommand<SdkCommandGenericArgs, ListDatabasesSdkCommandRes>>() {
    }).to(ListDatabasesSdkCommand.class);
    bind(new TypeLiteral<SdkCommand<CloseDatabaseTunnelSdkCommandArgs, Void>>() {
    }).to(CloseDatabaseTunnelSdkCommand.class);
    bind(new TypeLiteral<SdkCommand<OpenDatabaseTunnelSdkCommandArgs, OpenDatabaseTunnelSdkCommandRes>>() {
    }).to(OpenDatabaseTunnelSdkCommand.class);

    // Migration tooling
    bind(MigrationToolExecutor.class);
    bind(SystemProcessBuilder.class);

    // HANA
    bind(HanaConnector.class).to(TunneledHanaConnector.class);
    bind(DeliveryUnitsProvider.class);
    bind(DeliveryUnitsExporter.class);
    bind(ConnectionProvider.class);
  }

  @Override
  public String getName() {
    return "XSK Migration Module";
  }
}
