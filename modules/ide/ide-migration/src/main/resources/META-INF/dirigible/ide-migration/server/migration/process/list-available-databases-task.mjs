import { process } from "@dirigible/bpm";
import { NeoDatabasesService } from "../api/neo-databases-service.mjs";
import { MigrationTask } from "./task.mjs";

export class ListAvailableDatabasesTask extends MigrationTask {
    execution = process.getExecutionContext();

    constructor() {
        super("DATABASES_LISTING", "DATABASES_LISTED", "DATABASES_LISTING_FAILED");
    }

    run() {
        const userDataJson = process.getVariable(this.execution.getId(), "userData");
        const userJwtToken = process.getVariable(this.execution.getId(), "userJwtToken");
        const userData = JSON.parse(userDataJson);

        const account = userData.neo.subaccount;
        const host = userData.neo.hostName;

        const neoDatabasesService = new NeoDatabasesService();
        const databases = neoDatabasesService.getAvailableDatabases(
            account,
            host,
            userJwtToken
        );

        process.setVariable(this.execution.getId(), "databases", JSON.stringify(databases));
    }
}
