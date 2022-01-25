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
const HanaRepository = require('ide-migration/server/migration/repository/hana-repository');
const workspaceManager = require("platform/v4/workspace");
const repositoryManager = require("platform/v4/repository");
const bytes = require("io/v4/bytes");
const database = require("db/v4/database");
const config = require("core/v4/configurations");
const HANA_USERNAME = "HANA_USERNAME";
const TransformerFactory = Java.type("javax.xml.transform.TransformerFactory");
const StreamSource = Java.type("javax.xml.transform.stream.StreamSource");
const StreamResult = Java.type("javax.xml.transform.stream.StreamResult")
const StringReader = Java.type("java.io.StringReader");
const StringWriter = Java.type("java.io.StringWriter");
const ByteArrayInputStream = Java.type("java.io.ByteArrayInputStream");
const ByteArrayOutputStream = Java.type("java.io.ByteArrayOutputStream");
const XSKProjectMigrationInterceptor = Java.type("com.sap.xsk.modificators.XSKProjectMigrationInterceptor")


class MigrationService {

    connection = null;
    repo = null;

    setupConnection(databaseName, databaseUser, databaseUserPassword, connectionUrl) {
        database.createDataSource(databaseName, "com.sap.db.jdbc.Driver", connectionUrl, databaseUser, databaseUserPassword, null);

        this.connection = database.getConnection('dynamic', databaseName);
        this.repo = new HanaRepository(this.connection);
    }

    getAllDeliveryUnits() {
        if (!this.repo) {
            throw new Error("Repository not initialized");
        }

        return this.repo.getAllDeliveryUnits();
    }

    handlePossibleDeployableArtifacts(workspaceName, deployables) {
        let generatedFiles = [];
        let updatedFiles = [];
        for (const deployable of deployables) {
            if (deployable.artifacts && deployable.artifacts.length > 0) {
                const hdiConfigPath = this.createHdiConfigFile(workspaceName, deployable.project);
                generatedFiles.push(hdiConfigPath);
                let hdiPath = this.createHdiFile(workspaceName, deployable.project, hdiConfigPath, deployable.artifacts);
                generatedFiles.push(hdiPath);
            }
        }

        return {generated: generatedFiles, updated: updatedFiles};
    }

    createHdiConfigFile(workspaceName, project) {
        const hdiConfig = {
            file_suffixes: {
                hdbcalculationview: {
                    plugin_name: "com.sap.hana.di.calculationview"
                },
                calculationview: {
                    plugin_name: "com.sap.hana.di.calculationview"
                },
                hdbanalyticprivilege: {
                    plugin_name: "com.sap.hana.di.analyticprivilege"
                },
                analyticprivilege: {
                    plugin_name: "com.sap.hana.di.analyticprivilege"
                }
            }
        };


        const projectName = project.getName();
        const hdiConfigPath = `${projectName}.hdiconfig`;
        const hdiConfigJson = JSON.stringify(hdiConfig, null, 4);
        const hdiConfigJsonBytes = bytes.textToByteArray(hdiConfigJson);

        const workspaceCollection = this._getOrCreateTemporaryWorkspaceCollection(workspaceName);
        const projectCollection = this._getOrCreateTemporaryProjectCollection(workspaceCollection, projectName);
        let localResource = projectCollection.createResource(hdiConfigPath, hdiConfigJsonBytes);

        return {
            repositoryPath: localResource.getPath(),
            relativePath: hdiConfigPath,
            projectName: projectName
        }
    }

    createHdiFile(workspaceName, project, hdiConfigPath, deployables) {
        const projectName = project.getName();
        const defaultHanaUser = this.getDefaultHanaUser();

        const hdi = {
            configuration: `/${projectName}/${hdiConfigPath.relativePath}`,
            users: [defaultHanaUser],
            group: projectName,
            container: projectName,
            deploy: deployables,
            undeploy: []
        };

        const hdiPath = `${projectName}.hdi`;
        const hdiJson = JSON.stringify(hdi, null, 4);
        const hdiJsonBytes = bytes.textToByteArray(hdiJson);

        const workspaceCollection = this._getOrCreateTemporaryWorkspaceCollection(workspaceName);
        const projectCollection = this._getOrCreateTemporaryProjectCollection(workspaceCollection, projectName);
        let localResource = projectCollection.createResource(hdiPath, hdiJsonBytes);

        return {
            repositoryPath: localResource.getPath(),
            relativePath: hdiPath,
            projectName: projectName
        }
    }

    getDefaultHanaUser() {
        return config.get(HANA_USERNAME, "DBADMIN");
    }

    copyFilesLocally(workspaceName, lists) {
        const workspaceCollection = this._getOrCreateTemporaryWorkspaceCollection(workspaceName);

        const locals = [];
        for (const file of lists) {
            let fileRunLocation = file.RunLocation;

            // each file's package id is based on its directory
            // if we do not get only the first part of the package id, we would have several XSK projects created for directories in the same XS app
            const projectName = file.packageId.split('.')[0];

            if (fileRunLocation.startsWith("/" + projectName)) {
                // remove package id from file location in order to remove XSK project and folder nesting
                fileRunLocation = fileRunLocation.slice(projectName.length + 1);
            }
            let content = this.repo.getContentForObject(file._name, file._packageName, file._suffix);

            if (this._isFileCalculationView(fileRunLocation)) {
                content = this._transformColumnObject(content);
            }

            const projectCollection = this._getOrCreateTemporaryProjectCollection(workspaceCollection, projectName);
            const localResource = projectCollection.createResource(fileRunLocation, content);

            locals.push({
                repositoryPath: localResource.getPath(),
                relativePath: fileRunLocation,
                projectName: projectName,
                runLocation: file.RunLocation
            })
        }
        return locals;
    }

    _isFileCalculationView(filePath) {
        return filePath.endsWith('hdbcalculationview') || filePath.endsWith('calculationview');
    }

    _transformColumnObject(calculationViewXmlBytes) {
        try {
            const columnObjectToResourceUriXslt = `<?xml version="1.0" encoding="UTF8"?>
            <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

                <xsl:template match="node()|@*">
                    <xsl:copy>
                        <xsl:apply-templates select="node()|@*"/>
                    </xsl:copy>
                </xsl:template>

                <xsl:template match="DataSource[@type='DATA_BASE_TABLE']/columnObject[@columnObjectName]">
                    <xsl:element name="resourceUri">
                        <xsl:value-of select="@columnObjectName"/>
                    </xsl:element>
                </xsl:template>
            </xsl:stylesheet>
        `;

            const factory = TransformerFactory.newInstance();
            const source = new StreamSource(new StringReader(columnObjectToResourceUriXslt));
            const transformer = factory.newTransformer(source);

            const text = new StreamSource(new ByteArrayInputStream(calculationViewXmlBytes));
            const bout = new ByteArrayOutputStream();

            transformer.transform(text, new StreamResult(bout));
            return bout.toByteArray();
        } catch (e) {
            console.log("Error json: " + JSON.stringify(e));
        }
    }

    _getOrCreateTemporaryWorkspaceCollection(workspaceName) {
        const existing = repositoryManager.getCollection(workspaceName);
        if (existing) {
            return existing;
        }

        return repositoryManager.createCollection(workspaceName);
    }

    _getOrCreateTemporaryProjectCollection(workspaceCollection, projectName) {
        const existing = workspaceCollection.getCollection(projectName);
        if (existing) {
            return existing;
        }

        return workspaceCollection.createCollection(projectName);
    }

    createMigratedWorkspace(workspaceName) {
        let workspace;
        if (!workspaceName) {
            workspace = workspaceManager.getWorkspace(workspaceName);
            if (!workspace) {
                workspaceManager.createWorkspace(workspaceName);
            }
        }
        workspace = workspaceManager.getWorkspace(workspaceName);

        return workspace;
    }

    collectDeployables(workspaceName, filePath, runLocation, projectName, oldDeployables) {

        let workspace = workspaceManager.getWorkspace(workspaceName)
        if (!workspace) {
            workspaceManager.createWorkspace(workspaceName)
            workspace = workspaceManager.getWorkspace(workspaceName)
        }

        const deployables = oldDeployables;

        let project = workspace.getProject(projectName)
        if (!project) {
            workspace.createProject(projectName)
            project = workspace.getProject(projectName)
        }

        if (!deployables.find(x => x.projectName === projectName)) {
            deployables.push({
                project: project,
                projectName: projectName,
                artifacts: []
            });
        }

        if (filePath.endsWith('.hdbcalculationview')
            || filePath.endsWith('.calculationview')
            || filePath.endsWith('.analyticprivilege')
            || filePath.endsWith('.hdbanalyticprivilege')) {
            deployables.find(x => x.projectName === projectName).artifacts.push(runLocation);
        }

        return deployables;
    }

    addFileToWorkspace(workspaceName, repositoryPath, relativePath, projectName) {
        const workspace = workspaceManager.getWorkspace(workspaceName)
        const project = workspace.getProject(projectName)
        const projectFile = project.createFile(relativePath);
        const resource = repositoryManager.getResource(repositoryPath);
        const xskModificator = new XSKProjectMigrationInterceptor();

        if (relativePath.endsWith('.hdbcalculationview') || relativePath.endsWith('.calculationview') || repositoryPath.endsWith('.hdbcalculationview') || repositoryPath.endsWith('.calculationview')) {
            const modifiedContent = xskModificator.modify(resource.getContent());
            projectFile.setContent(modifiedContent);
        } else {
            projectFile.setContent(resource.getContent());
        }
    }

    getAllFilesForDU(du) {
        let context = {};
        const filesAndPackagesObject = this.repo.getAllFilesForDu(context, du);
        return filesAndPackagesObject.files;
    }

}

module.exports = MigrationService;
