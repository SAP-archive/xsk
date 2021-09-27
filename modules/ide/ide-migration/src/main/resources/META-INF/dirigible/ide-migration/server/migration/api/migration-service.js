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


class MigrationService {

    connection = null;
    repo = null;

    setupConnection(databaseName, databaseUser, databaseUserPassword) {
        database.createDataSource(databaseName, "com.sap.db.jdbc.Driver", "jdbc:sap://localhost:30015/", databaseUser, databaseUserPassword, null);

        this.connection = database.getConnection('dynamic', databaseName);
        this.repo = new HanaRepository(this.connection);
    }

    getAllDeliveryUnits() {
        if (!this.repo) {
            throw new Error("Repository not initialized");
        }

        return this.repo.getAllDeliveryUnits();
    }

    copyAllFilesForDu(du, workspaceName) {
        if (!this.repo) {
            throw new Error("Repository not initialized");
        }

        let context = {};
        const filesAndPackagesObject = this.repo.getAllFilesForDu(context, du)
        console.log("Files list: " + JSON.stringify(filesAndPackagesObject.files));
        this.dumpSourceFiles(workspaceName, filesAndPackagesObject.files, du)
    }

    dumpSourceFiles(workspaceName, lists, du) {
        let workspace;
        if (!workspaceName) {
            workspace = workspaceManager.getWorkspace(du.name)
            if (!workspace) {
                workspaceManager.createWorkspace(du.name)
                workspace = workspaceManager.getWorkspace(du.name)
            }
        }
        workspace = workspaceManager.getWorkspace(workspaceName);

        for (let i = 0; i < lists.length; i++) {
            const file = lists[i];
            // each file's package id is based on its directory
            // if we do not get only the first part of the package id, we would have several XSK projects created for directories in the same XS app
            const filePackageId = file.packageId.split('.')[0]; 

            let project = workspace.getProject(filePackageId)
            if (!project) {
                workspace.createProject(filePackageId)
                project = workspace.getProject(filePackageId)
            }

            let fileRunLocation = file.RunLocation;

            if (fileRunLocation.startsWith("/" + filePackageId)) { 
                // remove package id from file location in order to remove XSK project and folder nesting
                fileRunLocation = fileRunLocation.slice(filePackageId.length + 1);
            }

            let projectFile = project.createFile(fileRunLocation);
            projectFile.setContent(file._content);
        }
    }

}

module.exports = MigrationService;


