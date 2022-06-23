import { MigrationTask } from "./task.mjs";

export class AfterMigrationTask extends MigrationTask {

    constructor() {
        super("AFTER_MIGRATION_EXECUTING", "MIGRATION_EXECUTED", "AFTER_MIGRATION_FAILED");
    }

    run() {
        console.log("RUNNIN AFTER MIGRATION TASK")
    }
}