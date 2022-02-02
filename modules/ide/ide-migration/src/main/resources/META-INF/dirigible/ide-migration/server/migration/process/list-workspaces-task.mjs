import { process } from "@dirigible/bpm";
import { workspace as workspaceManager } from "@dirigible/platform";
import { TrackService } from "../api/track-service";

export class ListWorkspacesTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    run() {
        try {
            process.setVariable(execution.getId(), "migrationState", "WORKSPACES_LISTING");
            trackService.updateMigrationStatus("WORKSPACES LISTING");
            const workspaces = workspaceManager.getWorkspacesNames();
            process.setVariable(execution.getId(), "workspaces", JSON.stringify(workspaces));
            process.setVariable(execution.getId(), "migrationState", "WORKSPACES_LISTED");
            trackService.updateMigrationStatus("WORKSPACES LISTED");
        } catch (e) {
            process.setVariable(execution.getId(), "migrationState", "WORKSPACES_LISTING_FAILED");
            trackService.updateMigrationStatus("WORKSPACES LISTING FAILED");
            process.setVariable(
                execution.getId(),
                "WORKSPACES_LISTING_FAILED_REASON",
                e.toString()
            );
        }
    }
}
