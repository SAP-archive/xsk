import { process } from "@dirigible/bpm";
import { MigrationService } from "../api/migration-service";
import { configurations as config } from "@dirigible/core";
import { TrackService } from "../api/track-service";
import { DiffToolService } from "../api/diff-tool-executor.mjs";

export class PopulateProjectsTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    run() {
        try {
            process.setVariable(this.execution.getId(), "migrationState", "POPULATING_PROJECTS");
            this.trackService.updateMigrationStatus("POPULATING PROJECTS");
            const userDataJson = process.getVariable(this.execution.getId(), "userData");
            const userData = JSON.parse(userDataJson);

            const migrationService = new MigrationService();
            const workspace = userData.workspace;

            for (const deliveryUnit of userData.du) {
                const localFiles = deliveryUnit.locals;
                if (!(localFiles && localFiles.length > 0)) {
                    throw "Delivery unit is empty";
                }

                migrationService.addFilesWithoutGenerated(userData, workspace, localFiles);
                migrationService.addGeneratedFiles(userData, deliveryUnit, workspace, localFiles);
                migrationService.modifyFiles(workspace, localFiles);
            }
            process.setVariable(this.execution.getId(), "migrationState", "MIGRATION_EXECUTED");
            this.trackService.updateMigrationStatus("MIGRATION EXECUTED");

            const workspaceHolderFolder = config.get("user.dir") + "/target/dirigible/repository/root"
            const diffTool = new DiffToolService();
            const diffViewData = diffTool.diffFolders(`${workspaceHolderFolder}/${workspace}_unmodified`, `${workspaceHolderFolder}/${workspace}`);
            process.setVariable(this.execution.getId(), "diffViewData", JSON.stringify(diffViewData));
        } catch (e) {
            console.log("POPULATING_PROJECTS failed with error:");
            console.log(e.message);
            console.log(e.stack);
            process.setVariable(this.execution.getId(), "migrationState", "POPULATING_PROJECTS_FAILED");
            this.trackService.updateMigrationStatus("POPULATING PROJECTS FAILED");
            process.setVariable(
                this.execution.getId(),
                "POPULATING_PROJECTS_FAILED_REASON",
                e.toString()
            );
        }
    }
}
