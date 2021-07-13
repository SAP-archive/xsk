class BasPreChecker {
    static basEnvWorkspaceFoldersPreCheck(workspacesFolders) {
        if (!workspacesFolders || workspacesFolders.length < 1) {
            throw new Error('No active workspaces found, please select any folder before the migration script will be started');
        }
    }
}

module.exports = BasPreChecker;