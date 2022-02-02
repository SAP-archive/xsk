import { process } from "@dirigible/bpm";
import { MigrationService } from "../api/migration-service";
import { TrackService } from "../api/track-service";

export class CreateWorkspaceTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    run() {
        try {
            process.setVariable(execution.getId(), "migrationState", "EXECUTING_CREATE_WORKSPACE");
            trackService.updateMigrationStatus("CREATING WORKSPACE");
            const userDataJson = process.getVariable(execution.getId(), "userData");
            const userData = JSON.parse(userDataJson);

            const migrationService = new MigrationService();

            for (const deliveryUnit of userData.du) {
                migrationService.createMigratedWorkspace(userData.workspace, deliveryUnit);
            }

            process.setVariable(execution.getId(), "userData", JSON.stringify(userData));
            process.setVariable(execution.getId(), "migrationState", "WORKSPACE_CREATE_EXECUTED");
            trackService.updateMigrationStatus("CREATING WORKSPACE EXECUTED");
        } catch (e) {
            console.log("WORKSPACE_CREATE failed with error:");
            console.log(e.message);
            process.setVariable(execution.getId(), "migrationState", "WORKSPACE_CREATE_FAILED");
            trackService.updateMigrationStatus("CREATING WORKSPACE FAILED");
            process.setVariable(execution.getId(), "WORKSPACE_CREATE_FAILED_REASON", e.toString());
        }
    }
}
