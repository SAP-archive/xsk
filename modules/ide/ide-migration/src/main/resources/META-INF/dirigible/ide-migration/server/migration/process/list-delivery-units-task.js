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
const process = require("bpm/v4/process");
const execution = process.getExecutionContext();
const MigrationService = require("ide-migration/server/migration/api/migration-service");
const TrackService = require("ide-migration/server/migration/api/track-service");
const trackService = new TrackService();
try {
    process.setVariable(execution.getId(), "migrationState", "DELIVERY_UNITS_LISTING");
    trackService.updateMigrationStatus("DELIVERY UNITS LISTING");
    const userDataJson = process.getVariable(execution.getId(), "userData");
    const userData = JSON.parse(userDataJson);
    const userDatabaseData = userData.hana;
    const connectionUrl = process.getVariable(execution.getId(), "connectionUrl");

    const migrationService = new MigrationService();
    migrationService.setupConnection(
        userDatabaseData.databaseSchema,
        userDatabaseData.username,
        userDatabaseData.password,
        connectionUrl
    );
    const deliveryUnits = migrationService.getAllDeliveryUnits();
    process.setVariable(execution.getId(), "deliveryUnits", JSON.stringify(deliveryUnits));
    process.setVariable(execution.getId(), "migrationState", "DELIVERY_UNITS_LISTED");
    trackService.updateMigrationStatus("DELIVERY UNITS LISTED");
} catch (e) {
    process.setVariable(execution.getId(), "migrationState", "DELIVERY_UNITS_LISTING_FAILED");
    trackService.updateMigrationStatus("DELIVERY UNITS LISTING_FAILED");
    process.setVariable(execution.getId(), "DELIVERY_UNITS_LISTING_FAILED_REASON", e.toString());
}
