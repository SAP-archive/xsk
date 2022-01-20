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
const MigrationService = require('ide-migration/server/migration/api/migration-service');
const TrackService = require('ide-migration/server/migration/api/track-service');
const trackService = new TrackService();

try {
	process.setVariable(execution.getId(), 'migrationState', 'HANDLE_DEPLOYABLES_EXECUTING');
	trackService.updateMigrationStatus('HANDLE DEPLOYABLES EXECUTING');
	const userDataJson = process.getVariable(execution.getId(), 'userData');
	const userData = JSON.parse(userDataJson);

	const migrationService = new MigrationService();
    var projectNames = [];
    for (const deliveryUnit of userData.du) {
        const locals = deliveryUnit.locals;
        let deployables = [];
        for (const local of locals) {
            deployables = migrationService.collectDeployables(
                userData.workspace,
                local.repositoryPath,
                local.runLocation,
                local.projectName,
                deployables
            );

            if(local.runLocation === "/" + local.projectName + "/" + migrationService.synonymFileName
            || local.runLocation === "/" + local.projectName + "/" + migrationService.publicSynonymFileName) {
                if(!projectNames.includes(local.projectName)) {
                    projectNames.push(local.projectName);
                }
            }
        }

        // Add synonym files to deployables
        for(const projectName of projectNames) {
            deployables.find(x => x.projectName === projectName).artifacts.push("/" + projectName + "/" + migrationService.synonymFileName);
            deployables.find(x => x.projectName === projectName).artifacts.push("/" + projectName + "/" + migrationService.publicSynonymFileName);
        }
        deliveryUnit['deployableArtifactsResult'] = migrationService.handlePossibleDeployableArtifacts(userData.workspace, deployables);
    }
    process.setVariable(execution.getId(), 'userData', JSON.stringify(userData));
    process.setVariable(execution.getId(), 'migrationState', 'HANDLE_DEPLOYABLES_EXECUTED');
	trackService.updateMigrationStatus('HANDLE DEPLOYABLES EXECUTED');
} catch (e) {
	console.log('HANDLE_DEPLOYABLES failed with error:');
	console.log(e.message);
	process.setVariable(execution.getId(), 'migrationState', 'HANDLE_DEPLOYABLES_FAILED');
	trackService.updateMigrationStatus('HANDLE DEPLOYABLES FAILED');
	process.setVariable(execution.getId(), 'HANDLE_DEPLOYABLES_FAILED_REASON', e.toString());
}
