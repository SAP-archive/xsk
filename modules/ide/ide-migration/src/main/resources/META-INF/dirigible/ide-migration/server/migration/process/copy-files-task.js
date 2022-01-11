const process = require('bpm/v4/process');
const execution = process.getExecutionContext();
const MigrationService = require('ide-migration/server/migration/api/migration-service');
const TrackService = require('ide-migration/server/migration/api/track-service');
const trackService = new TrackService();

try {
	process.setVariable(execution.getId(), 'migrationState', 'COPY_FILES_EXECUTING');
	trackService.updateMigrationStatus('COPYING FILES');

	const userDataJson = process.getVariable(execution.getId(), 'userData');
	const userData = JSON.parse(userDataJson);
	const userDatabaseData = userData.hana;
	const connectionUrl = process.getVariable(execution.getId(), 'connectionUrl');

	const migrationService = new MigrationService();

	for (const deliveryUnit of userData.du) {
		trackService.updateMigrationStatus('COPYING FILES');
		migrationService.setupConnection(
			userDatabaseData.databaseSchema,
			userDatabaseData.username,
			userDatabaseData.password,
			connectionUrl
		);
		const files = migrationService.getAllFilesForDU(deliveryUnit);
		const locals = migrationService.copyFilesLocally(userData.workspace, files);
		deliveryUnit.locals = locals;
	}

	process.setVariable(execution.getId(), 'userData', JSON.stringify(userData));
	process.setVariable(execution.getId(), 'migrationState', 'COPY_FILES_EXECUTED');
	trackService.updateMigrationStatus('COPYING FILES DONE');
} catch (e) {
	console.log('COPY_FILES failed with error:');
	console.log(e.message);
	process.setVariable(execution.getId(), 'migrationState', 'COPY_FILES_FAILED');
	trackService.updateMigrationStatus('COPYING FILES DONE');
	process.setVariable(execution.getId(), 'COPY_FILES_FAILED_REASON', e.toString());
}
