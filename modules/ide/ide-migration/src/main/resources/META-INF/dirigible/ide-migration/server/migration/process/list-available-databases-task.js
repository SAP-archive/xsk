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

process.setVariable(execution.getId(), 'migrationState', 'DATABASES_LISTING');

try {
  const neoData = {
    account: userData.neo.subaccount,
    host: userData.neo.hostName,
    user: userData.neo.username,
    password: userData.neo.password
  };
  
  const NeoDatabasesService = require('ide-migration/server/migration/api/neo-databases-service');
  const neoDatabasesService = new NeoDatabasesService();
  const databases = neoDatabasesService.getAvailableDatabases(neoData);
  
  process.setVariable(execution.getId(), 'databases', JSON.stringify(databases));
  process.setVariable(execution.getId(), 'migrationState', 'DATABASES_LISTED');
} catch (e) {
  process.setVariable(execution.getId(), 'migrationState', 'DATABASES_LISTING_FAILED');
  process.setVariable(execution.getId(), 'DATABASES_LISTING_FAILED_REASON', e.toString());
}


