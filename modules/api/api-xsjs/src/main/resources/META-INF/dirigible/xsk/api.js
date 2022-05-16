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

var $ = {};

try {
  console.log("0")
  $.db = require('xsk/db/db');
  console.log("1")
  $.hdb = require('xsk/hdb/hdb');
  console.log("2")
  $.net = require('xsk/net/net');
  console.log("3")
  $.import = require("xsk/import/import").import;
  console.log("4")
  $.trace = require('xsk/trace/trace');
  console.log("5")
  $.util = require('xsk/util/util');
  console.log("6")
  $.jobs = require('xsk/jobs/jobs');
  console.log("7")
  $.web = require('xsk/web/web');
  console.log("8")
  $.session = require('xsk/session/session');
  console.log("9")
  $.security = require('xsk/security/security');
  console.log("10")
  $.request = new $.web.WebRequest();
  console.log("11")
  $.response = new $.web.WebResponse();
  console.log("12")
} catch (e) {
  // $.trace.warning("Caught exception. Api.js is being used by xsk job.")
  console.error(e.message);
}

exports.$ = $;
exports.add = function (a, b) {
  return a + b;
}

//module.exports = $;
