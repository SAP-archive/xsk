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
/*
 * HANA XS Classic Bridge
 */

var $ = {};
//
$.db = require('xsk/db/db');
$.hdb = require('xsk/hdb/hdb');
$.net = require('xsk/net/net');
$.import = require("xsk/import/import").import;
$.trace = require('xsk/trace/trace');
$.util = require('xsk/util/util');
$.jobs = require('xsk/jobs/jobs');

try {
    let dResponse = require('xsk/web/web').WebResponse;
    let dRequest = require('xsk/web/web').WebRequest;
    $.session = require('xsk/session/session');
    $.request = new dRequest();
    $.response = new dResponse();
    $.security = require('xsk/security/security');
} catch (e) {
//     $.trace.warning("Caught exception. Api.js is being used by xsk job.")
}

$;