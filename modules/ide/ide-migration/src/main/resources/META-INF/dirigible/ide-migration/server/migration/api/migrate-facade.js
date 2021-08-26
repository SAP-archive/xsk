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
const MigrationController = require('ide-migration/server/migration/controllers/migrate');
const TunnelController = require('ide-migration/server/migration/controllers/tunnel-controller');

class MigrationFacade {

    constructor() {
        console.log("Migration facade initialized");
        this.migrationController = new MigrationController();
        this.tunnelController = new TunnelController();
        
    }

    setupConnection(ctx, req, res) {
        this.migrationController = new MigrationController();
        const body = req.getJSON();
        try {
            this.migrationController.setupConnection(body.db, body.neoCredentials, body.cuser, body.hanaPass);
        } catch (error) {
            return res.print(JSON.stringify({success: false, error}));
        }
       
        res.print(JSON.stringify({success: true}));
    }

    getAllDeliveryUnits(ctx, req, res) {
        this.migrationController = new MigrationController();
        const body = req.getJSON();
        try {
            this.migrationController.setupConnection(body.db, body.neoCredentials, body.cuser, body.hanaPass);
            this.migrationController.getAllDeliveryUnits((err, dus) => {
                
            if (err) {              
                return res.print({success: false, err})
            }
            res.print(JSON.stringify({success: true, dus}));
        })
        } catch(err) {
            res.print(JSON.stringify({success: false, err}));
        }
        
    }

    copyAllFilesForDu(ctx, req, res) {
        this.migrationController = new MigrationController();
        const body = req.getJSON();
        const du = body.du;
        try {
            this.migrationController.setupConnection(body.db, body.neoCredentials, body.cuser, body.hanaPass);
            this.migrationController.copyAllFilesForDu(du, err => {
                return res.print(JSON.stringify({success: err === null}));
            })
        } catch (err) {
            res.print(JSON.stringify({success: false, err}))
        }
        
    }

    openTunnel(ctx, req, res) {
        this.tunnelController = new TunnelController();
        try {
            const credentials = req.getJSON().credentials;
            this.tunnelController.openTunnel(credentials, (err, result) => {
                res.print(JSON.stringify(result));
            })
        }catch (error) {
            res.print({error});
        }
        
    }

}

module.exports = MigrationFacade;


