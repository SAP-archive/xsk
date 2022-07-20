import { workspace as workspaceManager, repository as repositoryManager } from "@dirigible/platform";
import { bytes } from "@dirigible/io";
import { database } from "@dirigible/db";
import { configurations as config } from "@dirigible/core";
import { client as git } from "@dirigible/git";
import { repository } from "@dirigible/platform";
import { xml } from "@dirigible/utils";

import { HanaRepository } from "../repository/hana-repository";
import { HanaVisitor } from "./hana-visitor.mjs";
import { getHdiFilePlugins } from "../repository/hdi-plugins";

const HANA_USERNAME = "HANA_USERNAME";
const TransformerFactory = Java.type("javax.xml.transform.TransformerFactory");
const StreamSource = Java.type("javax.xml.transform.stream.StreamSource");
const StreamResult = Java.type("javax.xml.transform.stream.StreamResult");
const StringReader = Java.type("java.io.StringReader");
const ByteArrayInputStream = Java.type("java.io.ByteArrayInputStream");
const ByteArrayOutputStream = Java.type("java.io.ByteArrayOutputStream");
const XSKProjectMigrationInterceptor = Java.type("com.sap.xsk.modificators.XSKProjectMigrationInterceptor");
const XSKHDBCoreFacade = Java.type("com.sap.xsk.hdb.ds.facade.XSKHDBCoreSynchronizationFacade");
const hdbDDModel = "com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureCdsModel";
const xskModificator = new XSKProjectMigrationInterceptor();
const streams = org.eclipse.dirigible.api.v3.io.StreamsFacade;

export class MigrationService {
    repo = null;
    tableFunctionPaths = [];

    xskTechnicalPrivilegesFileName = "xsk_technical_privileges";
    synonymFileName = "hdi-synonyms.hdbsynonym";
    publicSynonymFileName = "hdi-public-synonyms.hdbpublicsynonym";
    modelsWithoutSynonym = ["com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel", "com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel"];
    fileExtsForHDI = [".hdbcalculationview", ".calculationview", ".analyticprivilege", ".hdbanalyticprivilege", ".hdbflowgraph", ".hdbtablefunction"];

    setupConnection(databaseName, databaseUser, databaseUserPassword, connectionUrl) {
        database.createDataSource(databaseName, "com.sap.db.jdbc.Driver", connectionUrl, databaseUser, databaseUserPassword, null);

        const connection = database.getConnection("dynamic", databaseName);
        this.repo = new HanaRepository(connection);
    }

    getAllDeliveryUnits() {
        if (!this.repo) {
            throw new Error("Repository not initialized");
        }

        return this.repo.getAllDeliveryUnits();
    }

    handlePossibleDeployableArtifacts(deliveryUnitName, workspaceName, deployables) {
        let generatedFiles = [];
        let updatedFiles = [];
        for (const deployable of deployables) {
            if (deployable.artifacts && deployable.artifacts.length > 0) {
                const hdiConfigPath = this.createHdiConfigFile(workspaceName, deployable.project);
                generatedFiles.push(hdiConfigPath);
                let hdiPath = this.createHdiFile(deliveryUnitName, workspaceName, deployable.project, hdiConfigPath, deployable.artifacts);
                generatedFiles.push(hdiPath);
            }
        }

        return { generated: generatedFiles, updated: updatedFiles };
    }

    createHdiConfigFile(workspaceName, project) {
        const hdiConfig = getHdiFilePlugins();

        const projectName = project.getName();
        const hdiConfigPath = ".hdiconfig";
        const hdiConfigJson = JSON.stringify(hdiConfig, null, 4);
        const hdiConfigJsonBytes = bytes.textToByteArray(hdiConfigJson);

        const workspaceCollection = this._getOrCreateTemporaryWorkspaceCollection(workspaceName);
        const projectCollection = this._getOrCreateTemporaryProjectCollection(workspaceCollection, projectName);
        let localResource = projectCollection.createResource(hdiConfigPath, hdiConfigJsonBytes);

        return {
            repositoryPath: localResource.getPath(),
            relativePath: hdiConfigPath,
            projectName: projectName,
        };
    }

    createHdiFile(deliveryUnitName, workspaceName, project, hdiConfigPath, deployables) {
        const projectName = project.getName();
        const groupOrContainerHdiValue = this._buildHDIContainerName(deliveryUnitName, projectName);
        const defaultHanaUser = config.get(HANA_USERNAME, "DBADMIN");

        const hdi = {
            configuration: `/${projectName}/${hdiConfigPath.relativePath}`,
            users: [defaultHanaUser],
            group: groupOrContainerHdiValue,
            container: groupOrContainerHdiValue,
            deploy: deployables,
            undeploy: [],
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
            projectName: projectName,
        };
    }

    _buildHDIContainerName(duName, projectName) {
        const groupAndContainerNamePattern = /[^\w]/g;
        const forbiddenCharactersReplacer = "_";

        const nameAccordingToConvention = `hdi_${duName}_${projectName}`.toUpperCase().replace(groupAndContainerNamePattern, forbiddenCharactersReplacer);

        return nameAccordingToConvention;
    }

    copyFilesLocally(workspaceName, duName, lists) {
        const workspaceCollection = this._getOrCreateTemporaryWorkspaceCollection(workspaceName);
        const unmodifiedWorkspaceCollection = this._getOrCreateTemporaryWorkspaceCollection(workspaceName + "_unmodified");
        const hdbFacade = new XSKHDBCoreFacade();


        const projectNames = [];
        const synonyms = [];
        const calcViews = {};

        let scopes = [{
            "name": "$XSAPPNAME.Developer",
            "description": "Developer scope",
        },
        {
            "name": "$XSAPPNAME.Operator",
            "description": "Operator scope"
        }
        ];
        let roleTemplates = [{
            "description": "Developer related roles",
            "name": "Developer",
            "scope-references": ["$XSAPPNAME.Developer"]
        },
        {
            "description": "Operator related roles",
            "name": "Operator",
            "scope-references": ["$XSAPPNAME.Operator"]
        }];
        let roleCollections = [{
            "description": " XSK Developer",
            "name": "XSK Developer",
            "role-template-references": ["$XSAPPNAME.Developer"]
        },
        {
            "description": "XSK Operator",
            "name": "XSK Operator",
            "role-template-references": ["$XSAPPNAME.Operator"]
        }];
        let lastProjectName = "";

        for (const file of lists) {
            let fileRunLocation = file.RunLocation;

            // each file's package id is based on its directory
            // if we do not get only the first part of the package id, we would have several XSK projects created for directories in the same XS app
            const fileProjectName = file.packageId.split(".")[0];
            if (!projectNames.includes(fileProjectName)) {
                projectNames.push(fileProjectName);
            }

            if (fileRunLocation.startsWith("/" + fileProjectName)) {
                // remove package id from file location in order to remove XSK project and folder nesting
                fileRunLocation = fileRunLocation.slice(fileProjectName.length + 1);
            }
            let content = this.repo.getContentForObject(file._name, file._packageName, file._suffix);

            const unmodifiedProjectCollection = this._getOrCreateTemporaryProjectCollection(
                unmodifiedWorkspaceCollection,
                fileProjectName
            );
            unmodifiedProjectCollection.createResource(fileRunLocation, content);

            const projectCollection = this._getOrCreateTemporaryProjectCollection(workspaceCollection, fileProjectName);
            const resource = projectCollection.createResource(fileRunLocation, content);

            //collect calcviews in order to create synonyms for external schemas later
            if (this._isFileCalculationView(fileRunLocation)) {
                calcViews[resource.getPath()] = fileProjectName; //TODO: optimize so getContentForObject is not called multiple times
            }

            // Parse current artifacts and generate synonym files for it if necessary

            if (fileRunLocation.endsWith(".xsprivileges")) {
                // convert build xs privilege prefix
                let filePath = fileRunLocation.substring(0, fileRunLocation.lastIndexOf("/"));
                if (filePath[0] == "/") {
                    filePath = filePath.substring(1, filePath.length);
                }
                const filePathWithDots = filePath.replace(/\//g, ".");
                const privilegePrefix = fileProjectName + "." + filePathWithDots + "::";
                const jsonContent = JSON.parse(bytes.byteArrayToText(content));
                this._buildXSSecurityElementsFromXSPrivileges(jsonContent, privilegePrefix, scopes, roleTemplates, roleCollections);
            }

            lastProjectName = fileProjectName;
            const fileName = this._getFileNameWithExtension(file);
            const filePath = this._getAbsoluteFilePath(file);
            const fileContent = bytes.byteArrayToText(content);
            this._generateSynonymIfNeeded(fileName, filePath, fileContent, fileProjectName, workspaceName, duName, workspaceCollection, hdbFacade, synonyms);

        }
        this._handleCalculationViews(calcViews, synonyms, workspaceName);
        this._createXsSecurityJson(lastProjectName, scopes, roleTemplates, roleCollections, workspaceCollection);
        return { projectNames, synonyms };
    }

    generateSynonymsForProject(workspaceName, projectName) {

        const workspacePath = `/${workspaceName}`
        const repositoryPath = `${workspacePath}/${projectName}`;
        const duRootCollection = repository.getCollection(repositoryPath);
        const workspaceCollection = repository.getCollection(workspacePath);

        const hdbFacade = new XSKHDBCoreFacade();

        const synonyms = [];
        const calcViews = {};

        const that = this;

        function localHandler(collection, localName) {

            const local = collection.getResource(localName);
            const repositoryPath = local.getPath();
            const runLocation = repositoryPath.substring(`/${workspacePath}`.length);

            if (that._isFileCalculationView(runLocation)) {
                calcViews[local.getPath()] = projectName; //TODO: optimize so getContentForObject is not called multiple times
            }

            that._generateSynonymIfNeeded(localName, runLocation, local.getText(), projectName, workspaceName, projectName, workspaceCollection, hdbFacade, synonyms);

        }

        this.iterateCollection(duRootCollection, localHandler);
        this._handleCalculationViews(calcViews, synonyms, workspaceName);

        return { projectNames: [projectName], synonyms };

    }

    _generateSynonymIfNeeded(fileName, filePath, fileContent, fileProjectName, workspaceName, duName, workspaceCollection, hdbFacade, synonyms) {
        const parsedData = this._parseArtifact(
            fileName,
            filePath,
            fileContent,
            workspaceCollection.getPath() + "/",
            hdbFacade
        );

        const hdiContainerName = this._buildHDIContainerName(duName, fileProjectName);
        const synonymData = this._handleParsedData(parsedData, hdiContainerName);
        if (synonymData.hdbSynonyms) {
            const hdbSynonyms = synonymData.hdbSynonyms;
            for (const synonym of hdbSynonyms) {
                const schemaName = synonym.value.target.schema;
                const schemaObject = synonym.value.target.object;
                synonyms.push(`${schemaName}_${schemaObject}`);
            }

        }

        this._appendOrCreateSynonymsFile(this.synonymFileName, synonymData.hdbSynonyms, workspaceName, fileProjectName);

        this._appendOrCreateSynonymsFile(
            this.publicSynonymFileName,
            synonymData.hdbPublicSynonyms,
            workspaceName,
            fileProjectName
        );
    }

    _handleCalculationViews(calcViews, synonyms, workspaceName) {
        for (const key in calcViews) {
            if (calcViews.hasOwnProperty(key)) {
                const resource = repositoryManager.getResource(key);
                const fileProjectName = calcViews[key]
                console.log(`Checking calcview: ${key}`)
                this._generateSynonymsForExternalResources(resource.getContent(), synonyms, workspaceName, fileProjectName);
                const newContent = this._transformColumnObject(resource.getContent(), synonyms);
                resource.setContent(newContent);
            }

        }
    }

    _generateSynonymsForExternalResources(calcViewBytes, localSynonyms, workspaceName, fileProjectName) {

        const inputStream = streams.createByteArrayInputStream(calcViewBytes);
        const builderFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        const builder = builderFactory.newDocumentBuilder();
        const xmlDocument = builder.parse(inputStream);
        const xPath = javax.xml.xpath.XPathFactory.newInstance().newXPath();
        const expression = "//*[name()='Calculation:scenario']/dataSources/DataSource/columnObject";
        const nodeList = xPath.compile(expression).evaluate(xmlDocument, javax.xml.xpath.XPathConstants.NODESET);

        const synonyms = [];

        for (let i = 0; i < nodeList.getLength(); i++) {
            const dataSource = nodeList.item(i);
            const objectName = dataSource.getAttribute('columnObjectName');
            const schema = dataSource.getAttribute('schemaName');
            if (localSynonyms.indexOf(`${schema}_${objectName}`) === -1) {
                //create synonym
                console.log("Generating synonym for calculation view... ");
                const hdbSynonym = this._generateHdbSynonym(
                    objectName,
                    schema
                );
                synonyms.push(hdbSynonym);
            }
        }
        this._appendOrCreateSynonymsFile(this.synonymFileName, synonyms, workspaceName, fileProjectName);
    }

    _parseArtifact(fileName, filePath, fileContent, workspacePath, hdbFacade) {
        if (this._isFileCalculationView(filePath)) {
            return this._buildCalcViewModel(filePath);
        }

        return hdbFacade.parseDataStructureModel(
            fileName,
            filePath,
            fileContent,
            workspacePath
        );
    }

    _getFileNameWithExtension(file) {
        return file._name + "." + file._suffix;
    }

    _getAbsoluteFilePath(file) {
        const filePath = file._packageName.replaceAll(".", "/") + "/";
        return filePath + this._getFileNameWithExtension(file);
    }

    _handleParsedData(parsedData, hdiSchema) {
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
                const hdbSynonym = this._generateHdbSynonym(
                    tableModelName,
                    tableModelSchema
                );

                synonyms.push(hdbSynonym);
            }
        } else {
            const modelName = parsedData.getName();
            const loc = parsedData.getLocation();
            if (this.modelsWithoutSynonym.indexOf(dataModelType) >= 0) {
                // synonym not needed
                console.log("Synonym won't be generated for file " + loc);
            } else if (this._shouldGeneratePublicSynonym(loc)) {
                // public synonym needed
                const artifactName = this._getPublicSynonymArtifactName(modelName, loc);
                const hdbPublicSynonym = this._generateHdbPublicSynonym(modelName, hdiSchema, artifactName);
                publicSynonyms.push(hdbPublicSynonym);
            } else {
                // hdb synonym needed
                const modelSchema = parsedData.getSchema();
                const hdbSynonym = this._generateHdbSynonym(modelName, modelSchema);
                synonyms.push(hdbSynonym);
            }
        }

        return { hdbSynonyms: synonyms, hdbPublicSynonyms: publicSynonyms };
    }

    _buildCalcViewModel(filePath) {
        // transforms "project/package/name.calculationview" to "project.package::name"
        let pathWithoutExt = filePath.substring(0, filePath.lastIndexOf('.'));
        let pathWithDots = pathWithoutExt.replaceAll('/', '.');
        let lastDotIndex = pathWithDots.lastIndexOf('.');
        const calcViewName = pathWithDots.substring(0, lastDotIndex) + "::" + pathWithDots.substring(lastDotIndex + 1, pathWithDots.length);
        const calcViewModelClass = {
            getName: () => "migration.calc.view.model"
        }
        const calcViewModel = {
            getName: () => calcViewName,
            getClass: () => calcViewModelClass,
            getLocation: () => filePath
        }
        return calcViewModel;
    }

    _generateHdbSynonym(name, schemaName) {
        return {
            name: name,
            value: {
                target: {
                    object: name,
                    schema: schemaName,
                },
            },
        };
    }

    _generateHdbPublicSynonym(synonymName, schemaName, artifactName) {
        return {
            name: synonymName,
            value: {
                target: {
                    object: artifactName,
                    schema: schemaName
                },
            },
        };
    }

    _generateHdbRole(name, privileges) {
        return {
            role: {
                name,
                schema_analytic_privileges: [
                    {
                        privileges
                    }
                ]
            }
        };
    }

    _buildXSSecurityElementsFromXSPrivileges(content, privilegePrefix, scopes, roleTemplates, roleCollections) {
        if (content["privileges"]) {
            let privileges = content["privileges"];
            for (let i = 0; i < privileges.length; i++) {
                let privilege = privileges[i];
                const privilegeName = privilege["name"];
                const privilegeDesc = privilege["description"];
                const fullPrivilegeName = privilegePrefix + privilegeName;
                const scopeName = "$XSAPPNAME." + fullPrivilegeName;
                scopes.push({
                    "name": scopeName,
                    "description": privilegeDesc
                });
                roleTemplates.push({
                    "name": "RoleTemplate" + privilegeName,
                    "description": "XSK role template for " + fullPrivilegeName,
                    "scope-references": [scopeName]
                })
                roleCollections.push({
                    "name": "Role" + privilegeName,
                    "description": "XSK role for " + fullPrivilegeName,
                    "role-template-references": ["$XSAPPNAME.RoleTemplate" + privilegeName]
                })
            }
        }
    }

    _createXsSecurityJson(projectName, scopes, roleTemplates, roleCollections,workspaceCollection) {
        let xsappid = config.get("DIRIGIBLE_OAUTH_APPLICATION_NAME", "default-app-name"); //xsapp id
        let xsappname = "";
        const indexOfExclamation = xsappid.indexOf("!");
        if (indexOfExclamation >= 0) {
            xsappname = xsappid.substring(0, indexOfExclamation);
        }
        const xsSecurityJson = {
            "xsappname": xsappname,
            "scopes": scopes,
            "role-templates": roleTemplates,
            "role-collections": roleCollections
        }
        const projectCollection = workspaceCollection.getCollection(projectName);
        projectCollection.createResource("xs-security.json", bytes.textToByteArray(JSON.stringify(xsSecurityJson)));
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
        return filePath.endsWith(".hdbcalculationview") || filePath.endsWith(".calculationview");
    }

    _isFileOldExtensionCalculationView(filePath) {
        return filePath.endsWith(".calculationview");
    }

    _transformColumnObject(calculationViewXmlBytes, synonymsArray) {
        try {
            const columnObjectToResourceUriXslt = `<?xml version="1.0" encoding="UTF8"?>
            <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
            <xsl:param name="synonymsArray" />
                <xsl:template match="node()|@*">
                    <xsl:copy>
                        <xsl:apply-templates select="node()|@*"/>
                    </xsl:copy>
                </xsl:template>
                <xsl:template match="DataSource[@type='DATA_BASE_TABLE']/columnObject[@columnObjectName]">
                    <xsl:element name="resourceUri">   
                        <xsl:choose>
                            <xsl:when test="contains($synonymsArray,concat(@schemaName, '_', @columnObjectName))">
                                <xsl:value-of select="@columnObjectName"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:value-of select="concat(@schemaName, '_', @columnObjectName)"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:element>
                    
                </xsl:template>
            </xsl:stylesheet>
            
        `;

            const factory = TransformerFactory.newInstance();
            const source = new StreamSource(new StringReader(columnObjectToResourceUriXslt));
            const transformer = factory.newTransformer(source);
            transformer.setParameter("synonymsArray", JSON.stringify(synonymsArray));

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
    removeTemporaryFolders(workspaceName) {
        let collectionNames = [workspaceName, workspaceName + '_unmodified']
        for (const collectionName of collectionNames) {
            const workspaceCollection = repositoryManager.getCollection(collectionName);
            if (workspaceCollection.exists()) {
                workspaceCollection.delete();
            }
        }
    }

    _getOrCreateHdbSynonymFile(workspaceName, projectName, hdbSynonymFileName) {
        const workspaceCollection = repositoryManager.getCollection(workspaceName);
        const projectCollection = workspaceCollection.getCollection(projectName);

        var synonymFile = projectCollection.getResource(hdbSynonymFileName);
        if (synonymFile.exists()) {
            return {
                resource: synonymFile,
                localPaths: null,
            };
        }

        synonymFile = projectCollection.createResource(hdbSynonymFileName, bytes.textToByteArray("{}"));
        return {
            resource: synonymFile,
            localPaths: {
                repositoryPath: synonymFile.getPath(),
                relativePath: synonymFile.getPath().split(projectName).pop(),
                projectName: projectName,
                runLocation: synonymFile.getPath().split(workspaceName).pop(),
            },
        };
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
        let workspace = workspaceManager.getWorkspace(workspaceName);
        if (!workspace) {
            workspaceManager.createWorkspace(workspaceName);
            workspace = workspaceManager.getWorkspace(workspaceName);
        }

        const deployables = oldDeployables;

        let project = workspace.getProject(projectName);
        if (!project) {
            workspace.createProject(projectName);
            project = workspace.getProject(projectName);
        }

        if (!deployables.find((x) => x.projectName === projectName)) {
            deployables.push({
                project: project,
                projectName: projectName,
                artifacts: [],
            });
        }

        let fileExt = this._getFileExtension(filePath);
        if (this.fileExtsForHDI.indexOf(fileExt) >= 0) {
            deployables.find((x) => x.projectName === projectName).artifacts.push(runLocation);
        }

        return deployables;
    }

    addFileToWorkspace(workspaceName, repositoryPath, relativePath, projectName) {
        const workspace = workspaceManager.getWorkspace(workspaceName);
        const project = workspace.getProject(projectName);

        const relativeSavePath = this._isFileOldExtensionCalculationView(relativePath) ? 
            relativePath.substr(0, relativePath.lastIndexOf('.')) + ".hdbcalculationview" : 
            relativePath;

        if (project.existsFile(relativeSavePath)) {
            project.deleteFile(relativeSavePath);
        }

        const projectFile = project.createFile(relativeSavePath);
        const resource = repositoryManager.getResource(repositoryPath);

        if (this._isFileCalculationView(relativeSavePath) || this._isFileCalculationView(repositoryPath)) {
            const modifiedContent = xskModificator.modify(resource.getContent());
            projectFile.setContent(modifiedContent);
        } else {
            projectFile.setContent(resource.getContent());
        }
    }

    getAllFilesForDU(du) {
        let context = {};
        const filesAndPackagesObject = this.repo.getAllFilesForDu(context, du);
        if (!filesAndPackagesObject) {
            return null;
        }
        return filesAndPackagesObject.files;
    }

    _visitCollection(project, collection, parentPath, synonyms, projectName, workspaceName, apIds) {
        let resNames = collection.getResourcesNames();
        const synonymsToGenerate = [];
        for (const resName of resNames) {
            var path = collection.getPath() + "/" + resName;
            let oldProjectRelativePath = parentPath + "/" + resName;

            if (path.endsWith(".hdbtablefunction") || path.endsWith(".hdbscalarfunction")) {
                let resource = collection.getResource(resName);
                let content = resource.getText();

                let visitor = new HanaVisitor(content);
                visitor.visit();
                visitor.removeSchemaRefs();
                visitor.removeViewRefs();

                const schemaNames = [];
                for (const schemaRef of visitor.schemaRefs) {
                    const schemaRefArray = schemaRef.split(`"."`).map(e => e.replace(/['"]+/g, ''));// i.e. [schemaName, objectName]
                    const schemaName = schemaRefArray[0];
                    schemaNames.push(schemaName);
                }

                if (schemaNames.length == 1) {
                    const schemaName = schemaNames[0]
                    for (const tableRef of visitor.tableRefs) {
                        const objectName = tableRef.replace(/['"]+/g, '');
                        if (synonyms.indexOf(`${schemaName}_${objectName}`) === -1) {
                            console.log(`External schema found. Generating synonym for ${schemaName} -> ${objectName}`);
                            const hdbSynonym = this._generateHdbSynonym(
                                objectName,
                                schemaName
                            );
                            synonymsToGenerate.push(hdbSynonym);
                            synonyms.push(`${schemaName}_${objectName}`);
                        }
                    }
                } else {
                    console.warn("Multiple schema references found in hdbtablefuncton. Synonyms won't be generated.")
                }

                resource.setText(visitor.content);
                project.getFile(oldProjectRelativePath).setText(visitor.content);
                this.tableFunctionPaths.push(oldProjectRelativePath);
            }

            if (path.endsWith(".hdbanalyticprivilege") || path.endsWith(".analyticprivilege")) {
                const resource = collection.getResource(resName);
                const content = resource.getText();
                const json = JSON.parse(xml.toJson(content));
                if (json["Privilege:analyticPrivilege"]) {
                    const apId = json["Privilege:analyticPrivilege"]["-id"];
                    if (apId) {
                        apIds.push(apId);
                    }
                }
            }
        }

        this._appendOrCreateSynonymsFile(this.synonymFileName, synonymsToGenerate, workspaceName, projectName);

        let collectionsNames = collection.getCollectionsNames();
        for (const name of collectionsNames) {
            let nestedCollection = collection.getCollection(name);
            this._visitCollection(project, nestedCollection, parentPath + "/" + name, synonyms, projectName, workspaceName, apIds);
        }
    }

    handleHDBTableFunctionsAndHDBRoles(workspaceName, projectName, synonyms) {
        const workspaceCollection = this._getOrCreateTemporaryWorkspaceCollection(workspaceName);
        const projectCollection = this._getOrCreateTemporaryProjectCollection(workspaceCollection, projectName);

        const workspace = workspaceManager.getWorkspace(workspaceName);
        const project = workspace.getProject(projectName);
        const apIds = [];
        this._visitCollection(project, projectCollection, ".", synonyms, projectName, workspaceName, apIds);

        const hdbRoleContent = JSON.stringify(this._generateHdbRole(this.xskTechnicalPrivilegesFileName, apIds));

        const hdbRoleName = `${this.xskTechnicalPrivilegesFileName}.hdbrole`;
        const hdbRoleFile = project.createFile(hdbRoleName);
        hdbRoleFile.setText(hdbRoleContent);

        console.log("Adding tablefunctions to hdi file...");
        this._addTableFunctionsToHDI(project, projectName, projectCollection);
        this._addHdbRolesToHDI(project, projectName, projectCollection, hdbRoleName);
        this._resetTableFunctionPaths();
    }

    _resetTableFunctionPaths() {
        this.tableFunctionPaths = [];
    }

    _addTableFunctionsToHDI(project, projectName, projectCollection) {
        if (!this.tableFunctionPaths || this.tableFunctionPaths.length < 1) {
            return;
        }

        const hdiPath = `${projectName}.hdi`;
        const hdiFile = project.getFile(hdiPath);
        const hdiObject = JSON.parse(hdiFile.getText());

        for (const path of this.tableFunctionPaths) {
            this._addPathToHDI(hdiObject, path, projectName);
        }

        this._populateHdiFile(projectCollection, hdiFile, hdiObject, hdiPath);
    }

    _addHdbRolesToHDI(project, projectName, projectCollection, rolePath) {
        const hdiPath = `${projectName}.hdi`;
        const hdiFile = project.getFile(hdiPath);
        const hdiObject = JSON.parse(hdiFile.getText());
        this._addPathToHDI(hdiObject, rolePath, projectName)

        this._populateHdiFile(projectCollection, hdiFile, hdiObject, hdiPath);
        
    }

    _populateHdiFile(projectCollection, hdiFile, hdiObject, hdiPath) {
        const hdiJson = JSON.stringify(hdiObject, null, 4);
        let resource = projectCollection.getResource(hdiPath);
        resource.setText(hdiJson);
        hdiFile.setText(hdiJson);
    }

    _addPathToHDI(hdiObject, path, projectName) {
        let trimmed = path;
        if (path.startsWith("./")) {
            trimmed = path.substring(2);
        }
        hdiObject["deploy"].push(`/${projectName}/${trimmed}`);
    }

    _getPublicSynonymArtifactName(artifactName, filePath) {
        if (this._isFileCalculationView(filePath)) {
            return artifactName.split(":").pop()
        }
        return artifactName;
    }

    _shouldGeneratePublicSynonym(filePath) {
        const fileExtension = this._getFileExtension(filePath);
        return this.fileExtsForHDI.indexOf(fileExtension) >= 0;
    }

    _getFileExtension(filePath) {
        return filePath.substring(filePath.lastIndexOf('.'), filePath.length);
    }

    addFilesWithoutGenerated(userData, workspace, localFiles) {
        for (const localFile of localFiles) {
            this.addFileToWorkspace(workspace, localFile.repositoryPath, localFile.relativePath, localFile.projectName);
        }
    }

    modifyFiles(workspace, localFiles) {
        for (const localFile of localFiles) {
            const projectName = localFile.projectName;
            xskModificator.interceptXSKProject(workspace, projectName);
        }
    }

    interceptProject(workspace, projectName) {
        xskModificator.interceptXSKProject(workspace, projectName);
    }

    commitProjectModifications(workspace, projectName) {
        let repos = git.getGitRepositories(workspace);
        let repoExists = false;
        for (const repo of repos) {
            if (repo.getName() === projectName) {
                repoExists = true;
                break;
            }
        }

        if (repoExists) {
            git.commit("migration", "", workspace, projectName, "Overwrite existing project", true);
        } else {
            console.log("Initializing repository...");
            git.initRepository("migration", "", workspace, projectName, projectName, "Migration initial commit");
        }
    }

    iterateCollection(collection, handler) {
        const localNames = collection.getResourcesNames();

        for (const name of localNames) {

            handler(collection, name);
        }
        const subcollectionNames = collection.getCollectionsNames();
        for (const name of subcollectionNames) {
            this.iterateCollection(collection.getCollection(name), handler);
        }

    }
}
