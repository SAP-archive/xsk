import { process } from "@dirigible/bpm";
import { TrackService } from "../api/track-service.mjs";

export class MigrationTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    constructor(onTaskStartedMessage, onTaskExecutedMessage, onTaskFailedMessage) {
        this.onTaskStartedMessage = onTaskStartedMessage;
        this.onTaskExecutedMessage = onTaskExecutedMessage;
        this.onTaskFailedMessage = onTaskFailedMessage;
    }

    execute() {
        try {
            process.setVariable(this.execution.getId(), "migrationState", this.onTaskStartedMessage);
            this.trackService.updateMigrationStatus(this.onTaskStartedMessage);

            this.run();

            process.setVariable(this.execution.getId(), "migrationState", this.onTaskExecutedMessage);
            this.trackService.updateMigrationStatus(this.onTaskExecutedMessage);

        } catch (e) {
            console.error(e.message);
            console.error(e.stack);

            process.setVariable(this.execution.getId(), "migrationState", this.onTaskFailedMessage);
            process.setVariable(this.execution.getId(), this.onTaskFailedMessage + "_REASON", e.toString());

            this.trackService.updateMigrationStatus(this.onTaskFailedMessage);

            const BpmnError = Java.type("org.flowable.engine.delegate.BpmnError");
            throw new BpmnError(this.onTaskFailedMessage);
        }
    }

    run() {
        throw new Error("Did you forget to override the 'run()' method?");
    }
}