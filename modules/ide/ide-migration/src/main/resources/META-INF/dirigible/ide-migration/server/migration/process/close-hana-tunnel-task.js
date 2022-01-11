const process = require('bpm/v4/process');
const execution = process.getExecutionContext();
const TrackService = require('ide-migration/server/migration/api/track-service');
const trackService = new TrackService();

try {
	process.setVariable(execution.getId(), 'migrationState', 'TUNNEL_CLOSING');
	trackService.updateMigrationStatus('TUNNEL_CLOSING');

	const userDataJson = process.getVariable(execution.getId(), 'userData');
	const userData = JSON.parse(userDataJson);

	const NeoTunnelService = require('ide-migration/server/migration/api/neo-tunnel-service');
	const neoTunnelService = new NeoTunnelService();
	neoTunnelService.closeTunnel(userData.connectionId);

	process.setVariable(execution.getId(), 'migrationState', 'TUNNEL_CLOSED');
	trackService.updateMigrationStatus('TUNNEL CLOSED');
} catch (e) {
	process.setVariable(execution.getId(), 'migrationState', 'TUNNEL_CLOSING_FAILED');
	trackService.updateMigrationStatus('TUNNEL CLOSING FAILED');
	process.setVariable(execution.getId(), 'TUNNEL_CLOSING_FAILED_REASON', e.toString());
}
