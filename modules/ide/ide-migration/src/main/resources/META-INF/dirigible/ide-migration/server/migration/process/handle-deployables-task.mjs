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
import { process } from "@dirigible/bpm";
import { MigrationService } from "../api/migration-service.mjs";
import { MigrationTask } from "./task.mjs";
import { repository } from "@dirigible/platform";

export class HandleDeployablesTask extends MigrationTask {
    execution = process.getExecutionContext();
    synonymFileName = "hdi-synonyms.hdbsynonym";
    publicSynonymFileName = "hdi-public-synonyms.hdbpublicsynonym";

    constructor() {
        super("HANDLE_DEPLOYABLES_EXECUTING", "HANDLE_DEPLOYABLES_EXECUTED", "HANDLE_DEPLOYABLES_FAILED");
    }

    getSynonymFilePath(projectName) {
        return "/" + projectName + "/" + this.synonymFileName;
    }

    getPublicSynonymFilePath(projectName) {
        return "/" + projectName + "/" + this.publicSynonymFileName;
    }

    run() {
        const userDataJson = process.getVariable(this.execution.getId(), "userData");
        const userData = JSON.parse(userDataJson);

        const migrationService = new MigrationService();
        const workspaceName = userData.workspace;
        for (const deliveryUnit of userData.du) {
            let deployables = [];
            for (const projectName of deliveryUnit.projectNames) {

                const projectSynonymPath = this.getSynonymFilePath(projectName);
                const projectPublicSynonymPath = this.getPublicSynonymFilePath(projectName);

                let synonymsPaths = [];
                let projectsWithSynonyms = [];

                const workspacePath = workspaceName;

                const repositoryPath = `${workspacePath}/${projectName}`;
                const duRootCollection = repository.getCollection(repositoryPath);

                function localHandler(collection, localName) {
                    const local = collection.getResource(localName);
                    const repositoryPath = local.getPath();
                    const runLocation = repositoryPath.substring(`/${workspacePath}`.length);
                    if (runLocation === projectSynonymPath || runLocation === projectPublicSynonymPath) {
                        if (!projectsWithSynonyms.includes(projectName)) {
                            projectsWithSynonyms.push(projectName);
                        }
                        if (!synonymsPaths.includes(runLocation)) {
                            synonymsPaths.push(runLocation);
                        }
                    }
                    deployables = migrationService.collectDeployables(
                        userData.workspace,
                        repositoryPath,
                        runLocation,
                        projectName,
                        deployables
                    );
                }

                migrationService.iterateCollection(duRootCollection, localHandler);

                // Get names of projects with generated synonyms and add them to deployables
                if (projectsWithSynonyms) {
                    for (const projectName of projectsWithSynonyms) {
                        const projectDeployables = deployables.find((x) => x.projectName === projectName).artifacts;
                        projectDeployables.push(...synonymsPaths);
                    }
                }

            }

            deliveryUnit["deployableArtifactsResult"] =
                migrationService.handlePossibleDeployableArtifacts(
                    deliveryUnit.name,
                    userData.workspace,
                    deployables
                );
        }
        process.setVariable(this.execution.getId(), "userData", JSON.stringify(userData));
    }
}
