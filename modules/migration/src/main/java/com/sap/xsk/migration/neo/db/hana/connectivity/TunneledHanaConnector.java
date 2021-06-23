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
package com.sap.xsk.migration.neo.db.hana.connectivity;

import com.sap.xsk.migration.neo.sdk.command.SdkCommandFactory;
import com.sap.xsk.migration.neo.sdk.command.tunnel.CloseDatabaseTunnelSdkCommand;
import com.sap.xsk.migration.neo.sdk.command.tunnel.CloseDatabaseTunnelSdkCommandArgs;
import com.sap.xsk.migration.neo.sdk.command.tunnel.OpenDatabaseTunnelSdkCommand;
import com.sap.xsk.migration.neo.sdk.command.tunnel.OpenDatabaseTunnelSdkCommandArgs;
import com.sap.xsk.migration.neo.sdk.command.tunnel.OpenDatabaseTunnelSdkCommandRes;
import javax.inject.Inject;

public class TunneledHanaConnector implements HanaConnector {

  private final OpenDatabaseTunnelSdkCommand openDatabaseTunnelSdkCommand;
  private final CloseDatabaseTunnelSdkCommand closeDatabaseTunnelSdkCommand;

  @Inject
  public TunneledHanaConnector(SdkCommandFactory sdkCommandFactory) {
    openDatabaseTunnelSdkCommand = sdkCommandFactory.createOpenDatabaseTunnelSdkCommand();
    closeDatabaseTunnelSdkCommand = sdkCommandFactory.createCloseDatabaseTunnelSdkCommand();
  }

  @Override
  public HanaConnectorRes connect(HanaConnectorArgs connectorArgs) {
    var openDatabaseTunnelSdkCommandArgs = toOpenDatabaseTunnelSdkCommandArgs(connectorArgs);
    OpenDatabaseTunnelSdkCommandRes commandOutput = openDatabaseTunnelSdkCommand.execute(openDatabaseTunnelSdkCommandArgs);
    return toTunneledConnectorResult(commandOutput);
  }

  private OpenDatabaseTunnelSdkCommandArgs toOpenDatabaseTunnelSdkCommandArgs(HanaConnectorArgs connectorArgs) {
    return new OpenDatabaseTunnelSdkCommandArgs(
        connectorArgs.getDatabaseId(),
        connectorArgs.getSubaccountTechnicalName(),
        connectorArgs.getHost(),
        connectorArgs.getUser(),
        connectorArgs.getPassword()
    );
  }

  private HanaConnectorRes toTunneledConnectorResult(OpenDatabaseTunnelSdkCommandRes openDatabaseTunnelSdkCommandRes) {
    return new HanaConnectorRes(
        openDatabaseTunnelSdkCommandRes.getHost(),
        openDatabaseTunnelSdkCommandRes.getPort(),
        openDatabaseTunnelSdkCommandRes.getDbType(),
        openDatabaseTunnelSdkCommandRes.getJdbcUrl(),
        openDatabaseTunnelSdkCommandRes.getInstanceNumber(),
        openDatabaseTunnelSdkCommandRes.getDbUser(),
        openDatabaseTunnelSdkCommandRes.getDbSchema(),
        openDatabaseTunnelSdkCommandRes.getSessionId()
    );
  }

  @Override
  public void disconnect(String connectionId) {
    var closeDatabaseTunnelSdkCommandArgs = new CloseDatabaseTunnelSdkCommandArgs(connectionId);
    closeDatabaseTunnelSdkCommand.execute(closeDatabaseTunnelSdkCommandArgs);
  }
}
