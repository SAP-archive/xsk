const exec = require("core/v4/exec");
const config = require("core/v4/configurations");

const DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED = "DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED";

class MigrationToolExecutor {
    execute(script, data) {
        const defaultLoggingConfig = config.get(DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED);
        config.set(DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED, "false");
        const execResult = exec.exec(script, data);

        if (defaultLoggingConfig) {
            config.set(DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED, defaultLoggingConfig);
        }

        return execResult;
    }
}

module.exports = MigrationToolExecutor;
