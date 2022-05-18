import { exec, configurations as config } from "@dirigible/core";

const DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED = "DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED";

export class MigrationToolExecutor {
    execute(script) {
        const defaultLoggingConfig = config.get(DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED);
        config.set(DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED, "false");
        const envVars = Java.type("java.lang.System").getenv();
        const execResult = exec.exec(script, envVars);

        if (defaultLoggingConfig) {
            config.set(DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED, defaultLoggingConfig);
        }

        return execResult;
    }
}
