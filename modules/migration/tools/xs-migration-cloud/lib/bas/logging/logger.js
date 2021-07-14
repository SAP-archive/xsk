const {loggerConfig} = require("./logger-config");
const { ok } = require("assert");
const { resolve } = require("path");
const { promisify } = require("util");
const { readFileSync } = require("fs");
const { configureLogger, NOOP_LOGGER } = require("@vscode-logging/wrapper");

const XS_MIGRATION_LOGGING_KEY = "Xs_Migration_Logging";

// On file load we init our logger to `NOOP_LOGGER`
// this is done because the "real" logger cannot be initialized during file load.
// only once the `activate` function has been called in extension.js
// as the `ExtensionContext` argument to `activate` contains the required `logPath`
/**
 * @type {IVSCodeExtLogger}
 */
let loggerImpl = NOOP_LOGGER;

/**
 * @return {IChildLogger}
 */
function getLogger() {
    return loggerImpl;
}

/**
 * @param newLogger {IVSCodeExtLogger}
 */
function setLogger(newLogger) {
    loggerImpl = newLogger;
}

const LOGGING_LEVEL_PROP = `${XS_MIGRATION_LOGGING_KEY}.loggingLevel`;
const SOURCE_LOCATION_PROP = `${XS_MIGRATION_LOGGING_KEY}.sourceLocationTracking`;
const MASK_DEBUG_PASSWORD_PROP = `${XS_MIGRATION_LOGGING_KEY}.maskDebugPassword`;

/**
 * @param context
 * @param outputChannel {outputChannelCallback}
 */
function initLogger(context, outputChannel) {
    const meta = JSON.parse(
        readFileSync(resolve(context.extensionPath, "package.json"), "utf8")
    );

    const configProps = meta.contributes.configuration.properties;
    ok(configProps[LOGGING_LEVEL_PROP]);
    ok(configProps[SOURCE_LOCATION_PROP]);
    ok(configProps[MASK_DEBUG_PASSWORD_PROP]);

    loggerConfig.maskPasswords = !!configProps[MASK_DEBUG_PASSWORD_PROP];

    const extLogger = configureLogger({
        extName: meta.displayName,
        logPath: context.logPath,
        logOutputChannel: outputChannel(meta.displayName),
        // set to `true` if you also want your VSCode extension to log the console
        logConsole: true,
        loggingLevelProp: LOGGING_LEVEL_PROP,
        sourceLocationProp: SOURCE_LOCATION_PROP,
        subscriptions: context.subscriptions
    });

    setLogger(extLogger);
}

module.exports.getLogger = getLogger;
module.exports.initLogger = initLogger;