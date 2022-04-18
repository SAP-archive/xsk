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
/*
 * HANA XS Classic Bridge
 */

globalThis.$ = {};

try {
$.response = {};
  $.db = require('xsk/db/db');
  console.log("!!!! VM: 1");
  $.hdb = require('xsk/hdb/hdb');
  console.log("!!!! VM: 2");
  $.net = require('xsk/net/net');
  console.log("!!!! VM: 3");
  $.import = require("xsk/import/import").import;
  console.log("!!!! VM: 4");
  $.trace = require('xsk/trace/trace');
  console.log("!!!! VM: 5");
  $.util = require('xsk/util/util');
  console.log("!!!! VM: 6");
  $.jobs = require('xsk/jobs/jobs');
  console.log("!!!! VM: 7");
  $.web = require('xsk/web/web');
  console.log("!!!! VM: 8");
  $.session = require('xsk/session/session');
  console.log("!!!! VM: 9");
  $.security = require('xsk/security/security');
  console.log("!!!! VM: 10");
  $.request = new $.web.WebRequest();
  console.log("!!!! VM: 11");
  $.response = new $.web.WebResponse();
  console.log("!!!! VM: 12");
} catch (e) {
  // $.trace.warning("Caught exception. Api.js is being used by xsk job.")
  console.error(e.message);
}
