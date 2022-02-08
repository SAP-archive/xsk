/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
const exec = require("core/v4/exec");
const config = require("core/v4/configurations");

const DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED = "DIRIGIBLE_EXEC_COMMAND_LOGGING_ENABLED";

class MigrationToolExecutor {
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

module.exports = MigrationToolExecutor;
