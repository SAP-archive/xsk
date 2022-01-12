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
const NeoTunnelService = require('ide-migration/server/migration/api/neo-tunnel-service')

const migrationService = new MigrationService();
const neoTunnelService = new NeoTunnelService();

let neoCredentials = null;

let callback = __context.get("__async_callback");

function openTunnelTest() {
    console.log("opening tunnel...")
    let credentials = {
            account: __context.get("__account"),
            host: __context.get("__host"),
            user: __context.get("__user"),
            password: __context.get("__password"),
            db: __context.get("__db")
        }

        neoTunnelService.openTunnel(credentials, (err, result) => {
        console.log(result)
        if (result.host) {
            neoCredentials = result;
            setupConnectionTest();
        } else {
            throw err;
        }

    })
}

function setupConnectionTest() {
    let db = __context.get("__db")
    let cuser = __context.get("__cuser");
    let hanaPass = __context.get("__hana_pass");
    migrationService.setupConnection(db, neoCredentials, cuser, hanaPass) 
    getAllDUsTest()
}

function getAllDUsTest() {
    migrationService.getAllDeliveryUnits((err, units) => {
        // console.log(units)
        if (units.length > 0) {
            const du = {"ach":"","caption":"","lastUpdate":"2021-06-18 11:47:41.1100000","name":"MIGR_TOOLS","ppmsID":"","responsible":"","sp_PPMS_ID":"","vendor":"migration.sap.com","version":"","version_patch":"","version_sp":""};
            migrationService.copyAllFilesForDu(null, du, (err) => {
                if (err) {
                    throw err;
                } else {
                    callback.callAssertTrue(true);
                    console.log("MIGRATION DONE!")
                }
            })
        }
    })
}


openTunnelTest();