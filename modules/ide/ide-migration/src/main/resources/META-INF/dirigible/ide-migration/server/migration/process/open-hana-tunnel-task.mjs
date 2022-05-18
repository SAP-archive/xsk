import { process } from "@dirigible/bpm";
import { NeoTunnelService } from "../api/neo-tunnel-service.mjs";
import { MigrationTask } from "./task.mjs";

export class OpenHanaTunnelTask extends MigrationTask {
    execution = process.getExecutionContext();

    constructor() {
        super("TUNNEL_OPENING", "TUNNEL_OPENED", "TUNNEL_OPENING_FAILED");
    }

    run() {
        const userDataJson = process.getVariable(this.execution.getId(), "userData");
        const userJwtToken = process.getVariable(this.execution.getId(), "userJwtToken");
        const userData = JSON.parse(userDataJson);

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
        process.setVariable(this.execution.getId(), "userData", JSON.stringify(userData));
        process.setVariable(
            this.execution.getId(),
            "connectionId",
            openedTunnelData.sessionId.toString()
        );

        process.setVariable(this.execution.getId(), "connectionUrl", openedTunnelData.jdbcUrl);
    }
}
