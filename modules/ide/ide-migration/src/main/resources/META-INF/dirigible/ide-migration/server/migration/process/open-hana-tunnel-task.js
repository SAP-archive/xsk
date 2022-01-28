/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
const process = require('bpm/v4/process');
const execution = process.getExecutionContext();
const TrackService = require('ide-migration/server/migration/api/track-service');
const trackService = new TrackService();

try {
	const userDataJson = process.getVariable(execution.getId(), 'userData');
	const userJwtToken = process.getVariable(execution.getId(), 'userJwtToken');
	const userData = JSON.parse(userDataJson);

	process.setVariable(execution.getId(), 'migrationState', 'TUNNEL_OPENING');
	trackService.updateMigrationStatus('TUNNEL OPENING');
	const account = userData.neo.subaccount;
	const host = userData.neo.hostName;
	const databaseId = userData.hana.databaseSchema;

	const NeoTunnelService = require('ide-migration/server/migration/api/neo-tunnel-service');
	const neoTunnelService = new NeoTunnelService();
	const openedTunnelData = neoTunnelService.openTunnel(account, host, userJwtToken, databaseId);

	userData.sessionId = openedTunnelData.sessionId;
	process.setVariable(execution.getId(), 'userData', JSON.stringify(userData));
	process.setVariable(execution.getId(), 'migrationState', 'TUNNEL_OPENED');
	trackService.updateMigrationStatus('TUNNEL OPENED');
	process.setVariable(execution.getId(), 'connectionId', openedTunnelData.sessionId.toString());
	process.setVariable(execution.getId(), 'connectionUrl', openedTunnelData.jdbcUrl);
} catch (e) {
	process.setVariable(execution.getId(), 'migrationState', 'TUNNEL_OPENING_FAILED');
	trackService.updateMigrationStatus('TUNNEL OPENING FAILED');
	process.setVariable(execution.getId(), 'TUNNEL_OPENING_FAILED_REASON', e.toString());
}
