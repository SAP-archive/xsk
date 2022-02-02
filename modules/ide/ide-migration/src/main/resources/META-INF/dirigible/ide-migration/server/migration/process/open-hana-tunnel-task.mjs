import { process } from "@dirigible/bpm";
import { TrackService } from "../api/track-service";
import { NeoTunnelService } from "../api/neo-tunnel-service";

export class OpenHanaTunnelTask {
    execution = process.getExecutionContext();
    trackService = new TrackService();

    run() {
        try {
            const userDataJson = process.getVariable(execution.getId(), "userData");
            const userJwtToken = process.getVariable(execution.getId(), "userJwtToken");
            const userData = JSON.parse(userDataJson);

            process.setVariable(execution.getId(), "migrationState", "TUNNEL_OPENING");
            trackService.updateMigrationStatus("TUNNEL OPENING");
            const account = userData.neo.subaccount;
            const host = userData.neo.hostName;
            const databaseId = userData.hana.databaseSchema;

            const neoTunnelService = new NeoTunnelService();
            const openedTunnelData = neoTunnelService.openTunnel(
                account,
                host,
                userJwtToken,
                databaseId
            );

            userData.sessionId = openedTunnelData.sessionId;
            process.setVariable(execution.getId(), "userData", JSON.stringify(userData));
            process.setVariable(execution.getId(), "migrationState", "TUNNEL_OPENED");
            trackService.updateMigrationStatus("TUNNEL OPENED");
            process.setVariable(
                execution.getId(),
                "connectionId",
                openedTunnelData.sessionId.toString()
            );
            process.setVariable(execution.getId(), "connectionUrl", openedTunnelData.jdbcUrl);
        } catch (e) {
            process.setVariable(execution.getId(), "migrationState", "TUNNEL_OPENING_FAILED");
            trackService.updateMigrationStatus("TUNNEL OPENING FAILED");
            process.setVariable(execution.getId(), "TUNNEL_OPENING_FAILED_REASON", e.toString());
        }
    }
}
