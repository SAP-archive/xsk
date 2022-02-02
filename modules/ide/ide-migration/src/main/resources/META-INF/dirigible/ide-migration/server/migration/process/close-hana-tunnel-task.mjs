import { process } from "@dirigible/bpm";
import { TrackService } from "../api/track-service";
import { NeoTunnelService } from "../api/neo-tunnel-service";

export class CloseHanaTunnelTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    run() {
        try {
            process.setVariable(execution.getId(), "migrationState", "TUNNEL_CLOSING");
            trackService.updateMigrationStatus("TUNNEL_CLOSING");

            const userDataJson = process.getVariable(execution.getId(), "userData");
            const userData = JSON.parse(userDataJson);

            const neoTunnelService = new NeoTunnelService();
            neoTunnelService.closeTunnel(userData.connectionId);

            process.setVariable(execution.getId(), "migrationState", "TUNNEL_CLOSED");
            trackService.updateMigrationStatus("TUNNEL CLOSED");
        } catch (e) {
            process.setVariable(execution.getId(), "migrationState", "TUNNEL_CLOSING_FAILED");
            trackService.updateMigrationStatus("TUNNEL CLOSING FAILED");
            process.setVariable(execution.getId(), "TUNNEL_CLOSING_FAILED_REASON", e.toString());
        }
    }
}
