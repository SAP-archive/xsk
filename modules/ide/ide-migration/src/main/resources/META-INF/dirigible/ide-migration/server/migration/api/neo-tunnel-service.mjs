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
import { configurations as config } from "@dirigible/core";
import { MigrationToolExecutor } from "./migration-tool-executor";

const neoClientPath = config.get("user.dir") + "/target/dirigible/resources-neo-sdk/sdk/tools/neo.sh";

export class NeoTunnelService {
    constructor() {
        this.migrationToolExecutor = new MigrationToolExecutor();
    }

    openTunnel(account, host, jwtToken, databaseId) {
        const script = `${neoClientPath} open-db-tunnel -a "${account}" -h "${host}" -u JWT -p "${jwtToken}" -i "${databaseId}" --output json --background`;

        const rawCommandResult = this.migrationToolExecutor.execute(script);

        const commandResult = JSON.parse(rawCommandResult);
        if (commandResult.errorMsg) {
            throw "[NEO CLIENT ERROR]" + commandResult.errorMsg;
        }

        return commandResult.result;
    }

    closeTunnel(sessionId) {
        const script = `${neoClientPath} close-db-tunnel --session-id ${sessionId}`;
        this.migrationToolExecutor.execute(script);
    }
}
