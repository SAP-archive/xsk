import { process } from "@dirigible/bpm";
import { MigrationService } from "../api/migration-service.mjs";
import { MigrationTask } from "./task.mjs";
import { configurations as config } from "@dirigible/core";
import { DiffToolService } from "../api/diff-tool-executor.mjs";
import { repository } from "@dirigible/platform";

export class PopulateProjectsTask extends MigrationTask {
    execution = process.getExecutionContext();

    constructor() {
        super("POPULATING_PROJECTS", "POPULATING_PROJECTS_EXECUTED", "POPULATING_PROJECTS_FAILED");
    }

    run() {
        const userDataJson = process.getVariable(this.execution.getId(), "userData");
        const userData = JSON.parse(userDataJson);

        const migrationService = new MigrationService();
        const workspaceName = userData.workspace;

        this.trackService.updateMigrationTarget(workspaceName, userData.du);

        for (const deliveryUnit of userData.du) {

            for (const projectName of deliveryUnit.projectNames) {

                const workspacePath = workspaceName;

                const repositoryPath = `${workspacePath}/${projectName}`;
                const duRootCollection = repository.getCollection(repositoryPath);

                function localHandler(collection, localName) {
                    const local = collection.getResource(localName);
                    const repositoryPath = local.getPath();

                    const runLocation = repositoryPath.substring(`/${workspacePath}`.length);
                    const relativePath = runLocation.substring(`/${projectName}`.length);
                    //add non generated
                    console.log("Adding file: " + relativePath);
                    migrationService.addFileToWorkspace(workspaceName, repositoryPath, relativePath, projectName)
                }

                migrationService.iterateCollection(duRootCollection, localHandler);

                //add generated files
                console.log("Adding generated files...")
                const generatedFiles = deliveryUnit["deployableArtifactsResult"]["generated"].filter((x) => x.projectName === projectName);
                for (const generatedFile of generatedFiles) {
                    migrationService.addFileToWorkspace(workspaceName, generatedFile.repositoryPath, generatedFile.relativePath, generatedFile.projectName);
                }
                migrationService.handleHDBTableFunctionsAndHDBRoles(workspaceName, projectName, deliveryUnit.synonyms);

                //modify files
                console.log("Modifying files...")
                migrationService.interceptProject(workspaceName, projectName);

                //commit
                console.log("Commiting changes...")
                migrationService.commitProjectModifications(workspaceName, projectName);

            }

        }

        process.setVariable(this.execution.getId(), "migrationState", "POPULATING_PROJECTS_EXECUTED");

        const workspaceHolderFolder = config.get("user.dir") + "/target/dirigible/repository/root"
        const diffTool = new DiffToolService();
        const diffViewData = diffTool.diffFolders(`${workspaceHolderFolder}/${workspaceName}_unmodified`, `${workspaceHolderFolder}/${workspaceName}`);
        migrationService.removeTemporaryFolders(workspaceName);
        this._persistDiffViewData(diffViewData);
    }

    _persistDiffViewData(diffViewData) {
        const diffViewsCollectionPath = "/diff-views";
        const diffViewsCollection = repository.getCollection(diffViewsCollectionPath);
        if (!diffViewsCollection.exists()) {
            diffViewsCollection.create();
        }

        const executionId = this.execution.getId();
        const currentDiffViewFileName = `migration-process-id-${executionId}`;

        const currentDiffViewResource = diffViewsCollection.getResource(currentDiffViewFileName);
        if (!currentDiffViewResource.exists()) {
            currentDiffViewResource.create();
        }

        const diffViewDataJson = JSON.stringify(diffViewData);
        currentDiffViewResource.setText(diffViewDataJson);
        process.setVariable(this.execution.getId(), "diffViewDataFileName", `${diffViewsCollectionPath}/${currentDiffViewFileName}`);
    }

}
