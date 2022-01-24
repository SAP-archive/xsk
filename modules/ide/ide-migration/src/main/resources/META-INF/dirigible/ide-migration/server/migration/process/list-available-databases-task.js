/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
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

	process.setVariable(execution.getId(), 'migrationState', 'DATABASES_LISTING');
	trackService.addEntry('DATABASES_LISTING');
	process.setVariable(execution.getId(), 'migrationIndex', trackService.getCurrentMigrationIndex());
	const account = userData.neo.subaccount;
	const host = userData.neo.hostName;

	const NeoDatabasesService = require('ide-migration/server/migration/api/neo-databases-service');
	const neoDatabasesService = new NeoDatabasesService();
	const databases = neoDatabasesService.getAvailableDatabases(account, host, userJwtToken);

	process.setVariable(execution.getId(), 'databases', JSON.stringify(databases));
	process.setVariable(execution.getId(), 'migrationState', 'DATABASES_LISTED');
	trackService.updateMigrationStatus('DATABASES LISTED');
} catch (e) {
	process.setVariable(execution.getId(), 'migrationState', 'DATABASES_LISTING_FAILED');
	trackService.updateMigrationStatus('DATABASES LISTING FAILED');
	process.setVariable(execution.getId(), 'DATABASES_LISTING_FAILED_REASON', e.toString());
}
