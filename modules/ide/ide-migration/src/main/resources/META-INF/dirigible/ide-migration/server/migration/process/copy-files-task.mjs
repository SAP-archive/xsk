import { process } from "@dirigible/bpm";
import { MigrationService } from "../api/migration-service";
import { TrackService } from "../api/track-service";

export class CopyFilesTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    run() {
        try {
            process.setVariable(this.execution.getId(), "migrationState", "COPY_FILES_EXECUTING");
            this.trackService.updateMigrationStatus("COPYING FILES");

            const userDataJson = process.getVariable(this.execution.getId(), "userData");
            const userData = JSON.parse(userDataJson);
            const userDatabaseData = userData.hana;
            const connectionUrl = process.getVariable(this.execution.getId(), "connectionUrl");

            const migrationService = new MigrationService();

            for (const deliveryUnit of userData.du) {
                this.trackService.updateMigrationStatus("COPYING FILES");
                migrationService.setupConnection(
                    userDatabaseData.databaseSchema,
                    userDatabaseData.username,
                    userDatabaseData.password,
                    connectionUrl
                );
                const files = migrationService.getAllFilesForDU(deliveryUnit);
                const locals = migrationService.copyFilesLocally(userData.workspace, files);
                deliveryUnit.locals = locals;
            }

            process.setVariable(this.execution.getId(), "userData", JSON.stringify(userData));
            process.setVariable(this.execution.getId(), "migrationState", "COPY_FILES_EXECUTED");
            this.trackService.updateMigrationStatus("COPYING FILES DONE");
        } catch (e) {
            console.log("COPY_FILES failed with error:");
            console.log(e.message);
            process.setVariable(this.execution.getId(), "migrationState", "COPY_FILES_FAILED");
            this.trackService.updateMigrationStatus("COPYING FILES DONE");
            process.setVariable(this.execution.getId(), "COPY_FILES_FAILED_REASON", e.toString());
        }
    }
}
