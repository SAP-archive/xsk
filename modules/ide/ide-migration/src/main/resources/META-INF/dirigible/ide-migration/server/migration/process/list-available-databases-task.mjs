import { process } from "@dirigible/bpm";
import { TrackService } from "../api/track-service";
import { NeoDatabasesService } from "../api/neo-databases-service";

export class ListAvailableDatabasesTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    run() {
        try {
            const userDataJson = process.getVariable(this.execution.getId(), "userData");
            const userJwtToken = process.getVariable(this.execution.getId(), "userJwtToken");
            const userData = JSON.parse(userDataJson);

            process.setVariable(this.execution.getId(), "migrationState", "DATABASES_LISTING");
            this.trackService.addEntry("DATABASES_LISTING");
            process.setVariable(this.execution.getId(), "migrationIndex", this.trackService.getCurrentMigrationIndex());
            const account = userData.neo.subaccount;
            const host = userData.neo.hostName;

            const neoDatabasesService = new NeoDatabasesService();
            const databases = neoDatabasesService.getAvailableDatabases(
                account,
                host,
                userJwtToken
            );

            process.setVariable(this.execution.getId(), "databases", JSON.stringify(databases));
            process.setVariable(this.execution.getId(), "migrationState", "DATABASES_LISTED");
            this.trackService.updateMigrationStatus("DATABASES LISTED");
        } catch (e) {
            process.setVariable(this.execution.getId(), "migrationState", "DATABASES_LISTING_FAILED");
            this.trackService.updateMigrationStatus("DATABASES LISTING FAILED");
            process.setVariable(this.execution.getId(), "DATABASES_LISTING_FAILED_REASON", e.toString());
        }
    }
}
