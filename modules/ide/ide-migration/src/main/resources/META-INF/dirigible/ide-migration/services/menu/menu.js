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
let extensions = require("core/v4/extensions");
let response = require("http/v4/response");

let mainmenu = [];
let menuExtensions = extensions.getExtensions("ide-migration-menu");

for (const menuExtensionModule of menuExtensions) {
    let menuExtension = require(menuExtensionModule);
    let menu = menuExtension.getMenu();
    mainmenu.push(menu);
}

mainmenu.sort(function (p, n) {
    return parseInt(p.order) - parseInt(n.order);
});
response.println(JSON.stringify(mainmenu));
