import { process } from "@dirigible/bpm";
import { TrackService } from "../api/track-service";
import { NeoDatabasesService } from "../api/neo-databases-service";

export class ListAvailableDatabasesTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    run() {
        try {
            const userDataJson = process.getVariable(execution.getId(), "userData");
            const userJwtToken = process.getVariable(execution.getId(), "userJwtToken");
            const userData = JSON.parse(userDataJson);

    process.setVariable(execution.getId(), "migrationState", "DATABASES_LISTING");
    trackService.addEntry("DATABASES_LISTING");
    process.setVariable(execution.getId(), "migrationIndex", trackService.getCurrentMigrationIndex());
    const account = userData.neo.subaccount;
    const host = userData.neo.hostName;

            const neoDatabasesService = new NeoDatabasesService();
            const databases = neoDatabasesService.getAvailableDatabases(
                account,
                host,
                userJwtToken
            );

            process.setVariable(execution.getId(), "databases", JSON.stringify(databases));
            process.setVariable(execution.getId(), "migrationState", "DATABASES_LISTED");
            trackService.updateMigrationStatus("DATABASES LISTED");
        } catch (e) {
            process.setVariable(execution.getId(), "migrationState", "DATABASES_LISTING_FAILED");
            trackService.updateMigrationStatus("DATABASES LISTING FAILED");
            process.setVariable(execution.getId(), "DATABASES_LISTING_FAILED_REASON", e.toString());
        }
    }
}
