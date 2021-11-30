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
try {
    const process = require('bpm/v4/process');
    const execution = process.getExecutionContext();

    process.setVariable(execution.getId(), 'migrationState', 'TUNNEL_CLOSING');

    const userDataJson = process.getVariable(execution.getId(), 'userData');
    const userData = JSON.parse(userDataJson);

    const NeoTunnelService = require('ide-migration/server/migration/api/neo-tunnel-service');
    const neoTunnelService = new NeoTunnelService();
    neoTunnelService.closeTunnel(userData.connectionId);

    process.setVariable(execution.getId(), 'migrationState', 'TUNNEL_CLOSED');
} catch (e) {
    process.setVariable(execution.getId(), 'migrationState', 'TUNNEL_CLOSING_FAILED');
    process.setVariable(execution.getId(), 'TUNNEL_CLOSING_FAILED_REASON', e.toString());
}