import { process } from "@dirigible/bpm";
import { MigrationService } from "../api/migration-service";
import { TrackService } from "../api/track-service";

export class ListDeliveryUnitsTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    run() {
        try {
            process.setVariable(this.execution.getId(), "migrationState", "DELIVERY_UNITS_LISTING");
            this.trackService.updateMigrationStatus("DELIVERY UNITS LISTING");
            const userDataJson = process.getVariable(this.execution.getId(), "userData");
            const userData = JSON.parse(userDataJson);
            const userDatabaseData = userData.hana;
            const connectionUrl = process.getVariable(this.execution.getId(), "connectionUrl");

            const migrationService = new MigrationService();
            migrationService.setupConnection(userDatabaseData.databaseSchema, userDatabaseData.username, userDatabaseData.password, connectionUrl);
            const deliveryUnits = migrationService.getAllDeliveryUnits();
            process.setVariable(this.execution.getId(), "deliveryUnits", JSON.stringify(deliveryUnits));
            process.setVariable(this.execution.getId(), "migrationState", "DELIVERY_UNITS_LISTED");
            this.trackService.updateMigrationStatus("DELIVERY UNITS LISTED");
        } catch (e) {
            process.setVariable(this.execution.getId(), "migrationState", "DELIVERY_UNITS_LISTING_FAILED");
            this.trackService.updateMigrationStatus("DELIVERY UNITS LISTING_FAILED");
            process.setVariable(this.execution.getId(), "DELIVERY_UNITS_LISTING_FAILED_REASON", e.toString());
        }
    }
}
