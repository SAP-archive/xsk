const process = require('bpm/v4/process');
const execution = process.getExecutionContext();
const workspaceManager = require('platform/v4/workspace');
const TrackService = require('ide-migration/server/migration/api/track-service');
const trackService = new TrackService();
try {
	process.setVariable(execution.getId(), 'migrationState', 'WORKSPACES_LISTING');
	trackService.updateMigrationStatus('WORKSPACES LISTING');
	const workspaces = workspaceManager.getWorkspacesNames();
	process.setVariable(execution.getId(), 'workspaces', JSON.stringify(workspaces));
	process.setVariable(execution.getId(), 'migrationState', 'WORKSPACES_LISTED');
	trackService.updateMigrationStatus('WORKSPACES LISTED');
} catch (e) {
	process.setVariable(execution.getId(), 'migrationState', 'WORKSPACES_LISTING_FAILED');
	trackService.updateMigrationStatus('WORKSPACES LISTING FAILED');
	process.setVariable(execution.getId(), 'WORKSPACES_LISTING_FAILED_REASON', e.toString());
}
