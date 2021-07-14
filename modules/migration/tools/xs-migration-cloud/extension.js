// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
const vscode = require('vscode');

const migration = require('./xs-migration-bas');

// this lib contains the required checks for BAS extension execution
const basPreChecker = require('./lib/bas/pre-checker');

const projectMessages = require('./lib/bas/yeoman/projectMessages');
const { getLogger, initLogger } = require("./lib/bas/logging/logger");

const COMMAND_PREFIX = 'xs-migration';

// this method is called when your extension is activated
// your extension is activated the very first time the command is executed

/**
 * @param {vscode.ExtensionContext} context
 */
async function activate(context) {

	// Use the console to output diagnostic information (console.log) and errors (console.error)
	// This line of code will only be executed once when your extension is activated
	console.log('Congratulations, your extension "xs-migration" is now active!');
        /**
         * Callback for output channel resolving.
         *
         * @callback outputChannelCallback
         * @param {string} displayName - the label/name of the channel
         * @return {OutputChannel}
         */
        const outputChannel = (displayName) => {
        return vscode.window.createOutputChannel(displayName);
    };

    initLogger(context, outputChannel);

	// The command has been defined in the package.json file
	// Now provide the implementation of the command with  registerCommand
	// The commandId parameter must match the command field in package.json
	let disposable = vscode.commands.registerCommand(`${COMMAND_PREFIX}.start`, async function (uri) {
        try {
            basPreChecker.basEnvWorkspaceFoldersPreCheck(vscode.workspace.workspaceFolders);

            vscode.commands.executeCommand('loadYeomanUI', {
                generator: '@sapmigrgen/migration:app',
                messages: projectMessages.messages,
                data: {
                    migrationScript: async () => {
                        await migration.runMain();
                    }
                }
            });
        } catch (err) {
            vscode.window.showErrorMessage(err.message);
        }
	});

	context.subscriptions.push(disposable);
    getLogger().info("Extension is active, yoop yoop");
}

exports.activate = activate;

// this method is called when your extension is deactivated
function deactivate() {}

module.exports = {
	activate,
	deactivate
}
