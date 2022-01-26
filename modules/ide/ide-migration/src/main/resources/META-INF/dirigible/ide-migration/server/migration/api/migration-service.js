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
const XSKProjectMigrationInterceptor = Java.type("com.sap.xsk.modificators.XSKProjectMigrationInterceptor");
const HanaVisitor = require('./HanaVisitor');
const hdiFile = require('ide-migration/server/migration/repository/hdi-plugins')
const xskModificator = new XSKProjectMigrationInterceptor();
const git = require('git/v4/client');
const XSKHDBCoreFacade = Java.type("com.sap.xsk.hdb.ds.facade.XSKHDBCoreFacade");
const hdbDDModel = "com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureCdsModel";


class MigrationService {

  connection = null;
  repo = null;
  tableFunctionPaths = [];
  synonymFileName = 'hdi-synonyms.hdbsynonym';
  publicSynonymFileName = 'hdi-public-synonyms.hdbpublicsynonym'


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

    return { generated: generatedFiles, updated: updatedFiles };
  }

  createHdiConfigFile(workspaceName, project) {

    const hdiConfig = hdiFile.getHdiFilePlugins();

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
    const hdbFacade = new XSKHDBCoreFacade();

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

      const fileName = this._getFileNameWithExtension(file);
      const filePath = this._getAbsoluteFilePath(file);
      const fileContent = bytes.byteArrayToText(content);

      // Parse current artifacts and generate synonym files for it if necessary
      const parsedData = hdbFacade.parseDataStructureModel(fileName, filePath, fileContent, workspaceCollection.getPath() + "/");
      const synonymData = this._handleParsedData(parsedData, workspaceName, projectName, fileRunLocation);
      const hdbSynonyms = this._appendOrCreateSynonymsFile(this.synonymFileName, synonymData.hdbSynonyms, workspaceName, projectName);
      const hdbPublicSynonyms = this._appendOrCreateSynonymsFile(this.publicSynonymFileName, synonymData.hdbPublicSynonyms, workspaceName, projectName);

      // Add any generated synonym files to locals
      locals.push(...hdbSynonyms);
      locals.push(...hdbPublicSynonyms);

      locals.push({
        repositoryPath: localResource.getPath(),
        relativePath: fileRunLocation,
        projectName: projectName,
        runLocation: file.RunLocation
      })
    }

    return locals;
  }

  _getFileNameWithExtension(file) {
    return file._name + "." + file._suffix;
  }

  _getAbsoluteFilePath(file) {
    const filePath = file._packageName.replaceAll('.', "/") + "/";
    return filePath + this._getFileNameWithExtension(file);
  }

  _handleParsedData(parsedData, workspaceName, projectName, relativePath) {
    if (!parsedData) {
      return [];
    }

    const dataModelType = parsedData.getClass().getName();

    var synonyms = [];
    var publicSynonyms = [];
    if (dataModelType == hdbDDModel) {
      for (const tableModel of parsedData.tableModels) {
        const tableModelName = tableModel.getName();
        const tableModelSchema = tableModel.getSchema();
        const hdbPublicSynonym = this._generateHdbPublicSynonym(tableModelName);
        const hdbSynonym = this._generateHdbSynonym(tableModelName, tableModelSchema);

        publicSynonyms.push(hdbPublicSynonym);
        synonyms.push(hdbSynonym);
      }

      for (const tableTypeModel of parsedData.tableTypeModels) {
        const tableTypeModelName = tableTypeModel.getName();
        const tableTypeModelSchema = tableTypeModel.getSchema();
        const hdbPublicSynonym = this._generateHdbPublicSynonym(tableTypeModelName);
        const hdbSynonym = this._generateHdbSynonym(tableTypeModelName, tableTypeModelSchema);

        publicSynonyms.push(hdbPublicSynonym);
        synonyms.push(hdbSynonym);
      }
    }
    else {
      const modelName = parsedData.getName();
      const modelSchema = parsedData.getSchema();
      const hdbPublicSynonym = this._generateHdbPublicSynonym(modelName);
      const hdbSynonym = this._generateHdbSynonym(modelName, modelSchema);

      publicSynonyms.push(hdbPublicSynonym);
      synonyms.push(hdbSynonym);
    }

    return { hdbSynonyms: synonyms, hdbPublicSynonyms: publicSynonyms };
  }

  _generateHdbSynonym(name, schemaName) {
    const trimmedName = name.split(':').pop();
    return {
      name: name,
      value: {
        target: {
          object: trimmedName,
          schema: schemaName
        }
      }
    }
  }

  _generateHdbPublicSynonym(name) {
    return {
      name: name,
      value: {
        target: {
          object: name,
        }
      }
    }
  }

  _appendOrCreateSynonymsFile(fileName, synonyms, workspaceName, projectName) {
    const synonymLocalPaths = [];

    if (!synonyms) {
      return synonymLocalPaths;
    }

    for (const synonym of synonyms) {
      const synonymResourceAndPaths = this._getOrCreateHdbSynonymFile(workspaceName, projectName, fileName);
      const synonymFile = synonymResourceAndPaths.resource;
      if (synonymResourceAndPaths.localPaths) {
        synonymLocalPaths.push(synonymResourceAndPaths.localPaths);
      }

      const synonymFileContent = synonymFile.getContent();
      const synonymFileContentAsText = bytes.byteArrayToText(synonymFileContent);
      const content = JSON.parse(synonymFileContentAsText);

      content[synonym.name] = synonym.value;

      const newSynonymFileContent = JSON.stringify(content, null, 4);
      const newSynonymFileBytes = bytes.textToByteArray(newSynonymFileContent);

      synonymFile.setContent(newSynonymFileBytes);
    }

    return synonymLocalPaths;
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

  _getOrCreateHdbSynonymFile(workspaceName, projectName, hdbSynonymFileName) {
    const workspaceCollection = repositoryManager.getCollection(workspaceName);
    const projectCollection = workspaceCollection.getCollection(projectName);

    var synonymFile = projectCollection.getResource(hdbSynonymFileName);
    if (synonymFile.exists()) {
      return {
        resource: synonymFile,
        localPaths: null
      }
    }

    synonymFile = projectCollection.createResource(hdbSynonymFileName, bytes.textToByteArray("{}"));
    return {
      resource: synonymFile,
      localPaths: {
        repositoryPath: synonymFile.getPath(),
        relativePath: synonymFile.getPath().split(projectName).pop(),
        projectName: projectName,
        runLocation: synonymFile.getPath().split(workspaceName).pop()
      }
    }
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

  getSynonymFilePath(projectName) {
    return "/" + projectName + "/" + this.synonymFileName;
  }

  getPublicSynonymFilePath(projectName) {
    return "/" + projectName + "/" + this.publicSynonymFileName;
  }

  getProjectsWithSynonyms(locals) {
    const projectNames = [];
    for (const local of locals) {
      const projectSynonymPath = this.getSynonymFilePath(local.projectName)
      const projectPublicSynonymPath = this.getPublicSynonymFilePath(local.projectName);

      if (local.runLocation === projectSynonymPath
          || local.runLocation === projectPublicSynonymPath) {
        if (!projectNames.includes(local.projectName)) {
          projectNames.push(local.projectName);
        }
      }
    }
    return projectNames;
  }

  addFileToWorkspace(workspaceName, repositoryPath, relativePath, projectName) {
    const workspace = workspaceManager.getWorkspace(workspaceName)
    const project = workspace.getProject(projectName)

    if (project.existsFile(relativePath)) {
      project.deleteFile(relativePath);
    }
    const projectFile = project.createFile(relativePath);
    const resource = repositoryManager.getResource(repositoryPath);

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

  _visitCollection(project, collection, parentPath) {
    let resNames = collection.getResourcesNames();
    for (const resName of resNames) {
      var path = collection.getPath() + "/" + resName;
      let oldProjectRelativePath = parentPath + "/" + resName;
      if (path.endsWith(".hdbtablefunction")) {
        let resource = collection.getResource(resName);
        let content = resource.getText();
        let visitor = new HanaVisitor(content);
        visitor.visit();
        visitor.removeSchemaRefs();
        visitor.removeViewRefs();
        let splitted = resName.split(".");
        splitted[splitted.length - 1] = "tablefunction";
        let newName = splitted.join(".");
        let newProjectRelativePath = parentPath + "/" + newName;
        this.tableFunctionPaths.push(newProjectRelativePath);
        console.log("Creating new file at: " + newProjectRelativePath);
        let newFile = project.createFile(newProjectRelativePath);
        newFile.setText(visitor.content);
        console.log("Creating new resource at: " + newName);
        let newResource = collection.createResource(newName, [0]);
        newResource.setText(visitor.content);
        console.log("deleting file at: " + path);
        project.deleteFile(oldProjectRelativePath);
        console.log("deleting resource at: " + path);
        resource.delete();
      }
    }

    let collectionsNames = collection.getCollectionsNames();
    for (const name of collectionsNames) {
      let nestedCollection = collection.getCollection(name)
      this._visitCollection(project, nestedCollection, parentPath + "/" + name);
    }

  }

  handleHDBTableFunctions(workspaceName, projectName) {
    const workspaceCollection = this._getOrCreateTemporaryWorkspaceCollection(workspaceName);
    const projectCollection = this._getOrCreateTemporaryProjectCollection(workspaceCollection, projectName);

    const workspace = workspaceManager.getWorkspace(workspaceName);
    const project = workspace.getProject(projectName);
    this._visitCollection(project, projectCollection, ".");

    console.log("Adding tablefunctions to hdi file...")
    this._addTableFunctionsToHDI(project, projectName, projectCollection);
    this._resetTableFunctionPaths();
  }

  _resetTableFunctionPaths() {
    this.tableFunctionPaths = [];
  }

  _addTableFunctionsToHDI(project, projectName, projectCollection) {
    const hdiPath = `${projectName}.hdi`;
    const hdiFile = project.getFile(hdiPath);
    const hdiObject = JSON.parse(hdiFile.getText());

    for (const path of this.tableFunctionPaths) {
      let trimmed = path;
      if (path.startsWith('./')) {
        trimmed = path.substring(2);
      }
      hdiObject['deploy'].push(`/${projectName}/${trimmed}`);
    }

    const hdiJson = JSON.stringify(hdiObject, null, 4);
    let resource = projectCollection.getResource(hdiPath);
    resource.setText(hdiJson);
    hdiFile.setText(hdiJson);
  }

  addFilesWithoutGenerated(userData, workspace, localFiles) {
    for (const localFile of localFiles) {
      this.addFileToWorkspace(workspace, localFile.repositoryPath, localFile.relativePath, localFile.projectName);
      const projectName = localFile.projectName;
      let repos = git.getGitRepositories(workspace);
      let repoExists = false;
      for (const repo of repos) {
        if (repo.getName() === projectName) {
          repoExists = true;
          break;
        }
      }
      if (repoExists) {
        git.commit('migration', '', userData.workspace, projectName, 'Overwrite existing project', true);
      } else {
        console.log("Initializing repository...")
        git.initRepository('migration', '', workspace, projectName, projectName, "Migration initial commit");
      }
    }
  }

  addGeneratedFiles(userData, deliveryUnit, workspace, localFiles) {
    for (const localFile of localFiles) {
      const projectName = localFile.projectName;
      const generatedFiles = deliveryUnit['deployableArtifactsResult']['generated'].filter(x => x.projectName === projectName);
      for (const generatedFile of generatedFiles) {
        this.addFileToWorkspace(workspace, generatedFile.repositoryPath, generatedFile.relativePath, generatedFile.projectName);
      }
      git.commit('migration', '', userData.workspace, projectName, 'Artifacts handled', true);
      this.handleHDBTableFunctions(workspace, projectName);
      git.commit('migration', '', userData.workspace, projectName, 'HDB Functions handled', true);
    }
  }


  modifyFiles(workspace, localFiles) {
    for (const localFile of localFiles) {
      const projectName = localFile.projectName;
      xskModificator.interceptXSKProject(workspace, projectName);
    }
  }


}

module.exports = MigrationService;