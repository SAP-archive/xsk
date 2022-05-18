import { process } from "@dirigible/bpm";
import { NeoTunnelService } from "../api/neo-tunnel-service.mjs";
import { MigrationTask } from "./task.mjs";

export class CloseHanaTunnelTask extends MigrationTask {
    execution = process.getExecutionContext();

    constructor() {
        super("TUNNEL_CLOSING", "TUNNEL_CLOSED", "TUNNEL_CLOSING_FAILED");
    }

    run() {
        const userDataJson = process.getVariable(this.execution.getId(), "userData");
        const userData = JSON.parse(userDataJson);

        const neoTunnelService = new NeoTunnelService();
        neoTunnelService.closeTunnel(userData.connectionId);
    }
}
