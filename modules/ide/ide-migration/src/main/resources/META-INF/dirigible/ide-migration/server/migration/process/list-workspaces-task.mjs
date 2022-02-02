import { process } from "@dirigible/bpm";
import { workspace as workspaceManager } from "@dirigible/platform";
import { TrackService } from "../api/track-service";

export class ListWorkspacesTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    run() {
        try {
            process.setVariable(this.execution.getId(), "migrationState", "WORKSPACES_LISTING");
            this.trackService.updateMigrationStatus("WORKSPACES LISTING");
            const workspaces = workspaceManager.getWorkspacesNames();
            process.setVariable(this.execution.getId(), "workspaces", JSON.stringify(workspaces));
            process.setVariable(this.execution.getId(), "migrationState", "WORKSPACES_LISTED");
            this.trackService.updateMigrationStatus("WORKSPACES LISTED");
        } catch (e) {
            process.setVariable(this.execution.getId(), "migrationState", "WORKSPACES_LISTING_FAILED");
            this.trackService.updateMigrationStatus("WORKSPACES LISTING FAILED");
            process.setVariable(
                this.execution.getId(),
                "WORKSPACES_LISTING_FAILED_REASON",
                e.toString()
            );
        }
    }
}
