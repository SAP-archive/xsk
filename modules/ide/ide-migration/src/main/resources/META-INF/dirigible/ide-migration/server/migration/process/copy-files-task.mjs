import { process } from "@dirigible/bpm";
import { MigrationService } from "../api/migration-service.mjs";
import { MigrationTask } from "./task.mjs";

export class CopyFilesTask extends MigrationTask {
    execution = process.getExecutionContext();

    constructor() {
        super("COPY_FILES_EXECUTING", "COPY_FILES_EXECUTED", "COPY_FILES_FAILED");
    }

    run() {
        const userDataJson = process.getVariable(this.execution.getId(), "userData");
        const userData = JSON.parse(userDataJson);
        const userDatabaseData = userData.hana;
        const connectionUrl = process.getVariable(this.execution.getId(), "connectionUrl");

        const migrationService = new MigrationService();
        migrationService.removeTemporaryFolders(userData.workspace);
        for (const deliveryUnit of userData.du) {
            migrationService.setupConnection(
                userDatabaseData.databaseSchema,
                userDatabaseData.username,
                userDatabaseData.password,
                connectionUrl
            );
            const files = migrationService.getAllFilesForDU(deliveryUnit);
            if (files) {
                const duName = deliveryUnit.name;
                const { projectNames, synonyms } = migrationService.copyFilesLocally(userData.workspace, duName, files);
                deliveryUnit.projectNames = projectNames;
                deliveryUnit.synonyms = synonyms;
            }
        }

        process.setVariable(this.execution.getId(), "userData", JSON.stringify(userData));
    }
}
