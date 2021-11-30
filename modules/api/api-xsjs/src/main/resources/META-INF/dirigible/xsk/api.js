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

try {
  $.db = require('xsk/db/db');
  $.hdb = require('xsk/hdb/hdb');
  $.net = require('xsk/net/net');
  $.import = require("xsk/import/import").import;
  $.trace = require('xsk/trace/trace');
  $.util = require('xsk/util/util');
  $.jobs = require('xsk/jobs/jobs');
  $.web = require('xsk/web/web');
  $.session = require('xsk/session/session');
  $.security = require('xsk/security/security');
  $.request = new $.web.WebRequest();
  $.response = new $.web.WebResponse();
} catch (e) {
  // $.trace.warning("Caught exception. Api.js is being used by xsk job.")
  console.error(e.message);
}

module.exports = $;
