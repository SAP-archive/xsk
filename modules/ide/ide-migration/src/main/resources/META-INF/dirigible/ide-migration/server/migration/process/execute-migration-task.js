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
const MigrationService = require('ide-migration/server/migration/api/migration-service');
const process = require('bpm/v4/process');
const execution = process.getExecutionContext();

try {
    process.setVariable(execution.getId(), 'migrationState', 'MIGRATION_EXECUTING');
    const userDataJson = process.getVariable(execution.getId(), 'userData');
    const userData = JSON.parse(userDataJson);
    const userDatabaseData = userData.hana;
    const connectionUrl = process.getVariable(execution.getId(), 'connectionUrl');

    const migrationService = new MigrationService();

    for (let i = 0; i < userData.du.length; i++) {
        console.log(`MIGRATING ${userData.du[i].name} IN WORKSPACE ${userData.workspace} (${(i + 1).toString()} of ${userData.du.length})`);
        migrationService.setupConnection(userDatabaseData.databaseSchema, userDatabaseData.username, userDatabaseData.password, connectionUrl);
        migrationService.copyAllFilesForDu(
            {
                name: userData.du[i].name,
                vendor: userData.du[i].vendor
            },
            userData.workspace
        );

    }
    process.setVariable(execution.getId(), 'migrationState', 'MIGRATION_EXECUTED');
} catch (e) {
    process.setVariable(execution.getId(), 'migrationState', 'MIGRATION_FAILED');
    process.setVariable(execution.getId(), 'MIGRATION_FAILED_REASON', e.toString());
}