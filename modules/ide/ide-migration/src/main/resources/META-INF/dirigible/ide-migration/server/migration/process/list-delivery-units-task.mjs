import { process } from "@dirigible/bpm";
import { MigrationService } from "../api/migration-service";
import { TrackService } from "../api/track-service";

export class ListDeliveryUnitsTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    run() {
        try {
            process.setVariable(execution.getId(), "migrationState", "DELIVERY_UNITS_LISTING");
            trackService.updateMigrationStatus("DELIVERY UNITS LISTING");
            const userDataJson = process.getVariable(execution.getId(), "userData");
            const userData = JSON.parse(userDataJson);
            const userDatabaseData = userData.hana;
            const connectionUrl = process.getVariable(execution.getId(), "connectionUrl");

            const migrationService = new MigrationService();
            migrationService.setupConnection(userDatabaseData.databaseSchema, userDatabaseData.username, userDatabaseData.password, connectionUrl);
            const deliveryUnits = migrationService.getAllDeliveryUnits();
            process.setVariable(execution.getId(), "deliveryUnits", JSON.stringify(deliveryUnits));
            process.setVariable(execution.getId(), "migrationState", "DELIVERY_UNITS_LISTED");
            trackService.updateMigrationStatus("DELIVERY UNITS LISTED");
        } catch (e) {
            process.setVariable(execution.getId(), "migrationState", "DELIVERY_UNITS_LISTING_FAILED");
            trackService.updateMigrationStatus("DELIVERY UNITS LISTING_FAILED");
            process.setVariable(execution.getId(), "DELIVERY_UNITS_LISTING_FAILED_REASON", e.toString());
        }
    }
}
