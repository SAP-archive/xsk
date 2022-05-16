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
var db = require('xsk/db/db');

var handler = __context.get("handler");
var method = __context.get("method");
var type = __context.get("type");
var connection = __context.get("connection");
var beforeTableName = __context.get("beforeTableName");
var afterTableName = __context.get("afterTableName");


let parts = handler.split('::');
if (parts.length != 2) {
    throw "Path for the event handler provided is incorrect: " + handler;
}

let segments = parts[0].split(':');
let operation = parts[1];

var handlerParam = { connection: new db.XscConnection(connection), beforeTableName: beforeTableName, afterTableName: afterTableName };
$.import(segments[0], segments[1].split('.')[0])[operation](handlerParam);
