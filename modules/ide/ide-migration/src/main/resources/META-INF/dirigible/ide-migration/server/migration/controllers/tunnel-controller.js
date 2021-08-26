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
const exec = require("core/v4/exec");
const config = require("core/v4/configurations");
const canonicalPrefix = "/usr/local/tomcat/target/dirigible/repository/root/users/dirigible/workspace/"

const neoPath = __context.get("__neo_path");
const neoClientPath = __context.get("__neo_client_path") || config.get("user.dir") + "/target/dirigible/resources-neo-sdk/neo-sdk/tools/neo.sh";

class TunnelController {

    openTunnel(credentials, completion) {
        const account = credentials.account;
        const host = credentials.host;
        const user = credentials.user;
        const password = credentials.password;
        const db = credentials.db;

        const script = `bash ${neoPath} -a "${account}" -h "${host}" -u "${user}" -p "${password}" -i "${db}"`;

        const response = exec.exec(script, {"NEO_CLIENT_PATH": neoClientPath});
        console.log(response)
        const neoCredentials = JSON.parse(response.substring(response.indexOf("{")));

        completion(null, neoCredentials);
    }
}

module.exports = TunnelController;