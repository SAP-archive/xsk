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
const process = require('bpm/v4/process');

const execution = process.getExecutionContext();
const userDataJson = process.getVariable(execution.getId(), 'userData');
const userData = JSON.parse(userDataJson);

process.setVariable(execution.getId(), 'migrationState', 'TUNNEL_OPENING');

const tunnelData = {
  account: userData.neo.subaccount,
  host: userData.neo.hostName,
  user: userData.neo.username,
  password: userData.neo.password,
  db: userData.hana.databaseSchema
};

const NeoTunnelService = require('ide-migration/server/migration/api/neo-tunnel-service');
const neoTunnelService = new NeoTunnelService();
const openedTunnelData = neoTunnelService.openTunnel(tunnelData);

userData.sessionId = openedTunnelData.sessionId;
process.setVariable(execution.getId(), 'userData', JSON.stringify(userData));
process.setVariable(execution.getId(), 'migrationState', 'TUNNEL_OPENED');
process.setVariable(execution.getId(), 'connectionId', openedTunnelData.sessionId.toString());
process.setVariable(execution.getId(), 'connectionUrl', openedTunnelData.jdbcUrl);