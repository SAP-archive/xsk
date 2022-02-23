import { process } from "@dirigible/bpm";
import { MigrationService } from "../api/migration-service.mjs";
import { MigrationTask } from "./task.mjs";

export class CreateWorkspaceTask extends MigrationTask {
    execution = process.getExecutionContext();

    constructor() {
        super("EXECUTING_CREATE_WORKSPACE", "WORKSPACE_CREATE_EXECUTED", "WORKSPACE_CREATE_FAILED");
    }

    run() {
        const userDataJson = process.getVariable(this.execution.getId(), "userData");
        const migrationType = process.getVariable(this.execution.getId(), "migrationType")
        const userData = JSON.parse(userDataJson);

        const migrationService = new MigrationService();

        if (migrationType === 'FROM_LOCAL_ZIP') {
            migrationService.createMigratedWorkspace(userData.selectedWorkspace);
        } else {
            for (const deliveryUnit of userData.du) {
                migrationService.createMigratedWorkspace(userData.workspace, deliveryUnit);
            }
        }

        process.setVariable(this.execution.getId(), "userData", JSON.stringify(userData));
    }
}
