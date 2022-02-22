import { process } from "@dirigible/bpm";
import { MigrationService } from "../api/migration-service.mjs";
import { MigrationTask } from "./task.mjs";

export class PopulateProjectsTask extends MigrationTask {
    execution = process.getExecutionContext();

    constructor() {
        super("POPULATING_PROJECTS", "MIGRATION_EXECUTED", "POPULATING_PROJECTS_FAILED");
    }

    run() {
        const userDataJson = process.getVariable(this.execution.getId(), "userData");
        const userData = JSON.parse(userDataJson);

        const migrationService = new MigrationService();
        const workspace = userData.workspace;

        for (const deliveryUnit of userData.du) {
            const localFiles = deliveryUnit.locals;
            if (!(localFiles && localFiles.length > 0)) {
                console.warn("Delivery unit is empty.");
                continue;
            }

            migrationService.addFilesWithoutGenerated(userData, workspace, localFiles);
            migrationService.addGeneratedFiles(userData, deliveryUnit, workspace, localFiles);
            migrationService.modifyFiles(workspace, localFiles);
        }
    }
}
