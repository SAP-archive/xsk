import { process } from "@dirigible/bpm";
import { MigrationService } from "../api/migration-service.mjs";
import { MigrationTask } from "./task.mjs";

export class ListDeliveryUnitsTask extends MigrationTask {
    execution = process.getExecutionContext();

    constructor() {
        super("DELIVERY_UNITS_LISTING", "DELIVERY_UNITS_LISTED", "DELIVERY_UNITS_LISTING_FAILED");
    }

    run() {
        const userDataJson = process.getVariable(this.execution.getId(), "userData");
        const userData = JSON.parse(userDataJson);
        const userDatabaseData = userData.hana;
        const connectionUrl = process.getVariable(this.execution.getId(), "connectionUrl");

        const migrationService = new MigrationService();
        migrationService.setupConnection(userDatabaseData.databaseSchema, userDatabaseData.username, userDatabaseData.password, connectionUrl);
        const deliveryUnits = migrationService.getAllDeliveryUnits();
        process.setVariable(this.execution.getId(), "deliveryUnits", JSON.stringify(deliveryUnits));
    }
}
