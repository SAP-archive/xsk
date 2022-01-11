const process = require('bpm/v4/process');
const execution = process.getExecutionContext();
const MigrationService = require('ide-migration/server/migration/api/migration-service');
const TrackService = require('ide-migration/server/migration/api/track-service');
const trackService = new TrackService();
try {
	process.setVariable(execution.getId(), 'migrationState', 'EXECUTING_CREATE_WORKSPACE');
	trackService.updateMigrationStatus('CREATING WORKSPACE');
	const userDataJson = process.getVariable(execution.getId(), 'userData');
	const userData = JSON.parse(userDataJson);

	const migrationService = new MigrationService();

	for (let i = 0; i < userData.du.length; i++) {
		migrationService.createMigratedWorkspace(userData.workspace, userData.du[i]);
	}
	process.setVariable(execution.getId(), 'userData', JSON.stringify(userData));
	process.setVariable(execution.getId(), 'migrationState', 'WORKSPACE_CREATE_EXECUTED');
	trackService.updateMigrationStatus('CREATING WORKSPACE EXECUTED');
} catch (e) {
	console.log('WORKSPACE_CREATE failed with error:');
	console.log(e.message);
	process.setVariable(execution.getId(), 'migrationState', 'WORKSPACE_CREATE_FAILED');
	trackService.updateMigrationStatus('CREATING WORKSPACE FAILED');
	process.setVariable(execution.getId(), 'WORKSPACE_CREATE_FAILED_REASON', e.toString());
}
