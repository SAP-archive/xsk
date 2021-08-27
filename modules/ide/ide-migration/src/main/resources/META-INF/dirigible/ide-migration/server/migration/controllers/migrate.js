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
let HanaRepository = require('ide-migration/server/migration/repository/hana-repository');
let workspaceManager = require("platform/v4/workspace");
var database = require("db/v4/database");


class MigrationController {

    connection = null;
    repo = null;

    setupConnection(db, neoCredentials, cuser, hanaPass) {
            database.createDataSource(db, "com.sap.db.jdbc.Driver", neoCredentials['url'], cuser, hanaPass, null);

            this.connection = database.getConnection('dynamic', db);
            this.repo = new HanaRepository(this.connection);
    }

    getAllDeliveryUnits(completion) {
        
        if (!this.repo) {
            
            return completion("Repository not initialized", null);       
        }
        
        try {
            this.repo.getAllDeliveryUnits(function(error, result){
                completion(null, result);
            });
        } catch (error) {
            completion(error)
        } 
    }

    copyAllFilesForDu(workspaceName, du, completion) {
        if (!this.repo) {
            return completion("Repository not initialized", null);
        }

        let context = {};
        try {
            this.repo.getAllFilesForDu(context, du, (err, files, packages) => {
                console.log("Files list: " + JSON.stringify(files));
                this.dumpSourceFiles(workspaceName, files, du, function(err){
                    completion(err);
                })
            })
        } catch (error) {
            console.error(error)
            completion(error);
        } 
    }

    dumpSourceFiles(workspaceName, lists, du, callback) {
        let workspace;
        if (!workspaceName) {
            workspace = workspaceManager.getWorkspace(du.name)
            if (!workspace) {
                workspaceManager.createWorkspace(du.name)
                workspace = workspaceManager.getWorkspace(du.name)
            }
        } 
        workspace = workspaceManager.getWorkspace(workspaceName);

        for(let i = 0; i < lists.length; i++) {
            const file = lists[i];
            let project = workspace.getProject(file.packageId)
            if (!project) {
                workspace.createProject(file.packageId)
                project = workspace.getProject(file.packageId)
            }
 
            project.createFile(file.RunLocation)
            
        }
        callback(null);
    }

}

module.exports = MigrationController;


