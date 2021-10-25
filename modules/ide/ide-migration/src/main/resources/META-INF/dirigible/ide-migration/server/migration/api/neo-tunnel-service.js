/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
const config = require("core/v4/configurations");
const MigrationToolExecutor = require("ide-migration/server/migration/api/migration-tool-executor");

const neoClientPath = config.get("user.dir") + "/target/dirigible/resources-neo-sdk/tools/neo.sh";

class NeoTunnelService {

  constructor() {
    this.migrationToolExecutor = new MigrationToolExecutor();
  }

  openTunnel(credentials, completion) {
    const account = credentials.account;
    const host = credentials.host;
    const user = credentials.user;
    const password = credentials.password;
    const db = credentials.db;

    const script = `${neoClientPath} open-db-tunnel -a "${account}" -h "${host}" -u "${user}" -p "${password}" -i "${db}" --output json --background`;

    const rawCommandResult = this.migrationToolExecutor.execute(script, {
      "JAVA_HOME": config.get("JAVA8_HOME"),
      "PATH": config.get("JAVA8_HOME") + "/bin:" + config.get("PATH")
    });

    const commandResult = JSON.parse(rawCommandResult);
    console.log(commandResult)
    if (commandResult.errorMsg) {
      throw "[NEO CLIENT ERROR]" + neoOutput.errorMsg
    }

    if (completion) {
      completion(null, commandResult.result);
    } else {
      return commandResult.result;
    }
  }

  closeTunnel(sessionId) {
    const script = `${neoClientPath} close-db-tunnel --session-id ${sessionId}`;
    this.migrationToolExecutor.execute(script, {
      "JAVA_HOME": config.get("JAVA8_HOME"),
      "PATH": config.get("JAVA8_HOME") + "/bin:" + config.get("PATH")
    });
  }
}

module.exports = NeoTunnelService;