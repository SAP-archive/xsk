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

var registry = require("platform/v4/registry");

exports.Job = function Job(path) {
  this.path = path;

  com.sap.xsk.xsjob.ds.facade.XSKJobFacade.newJob(registry.getText(path), path);

  this.activate = function(){
    com.sap.xsk.xsjob.ds.facade.XSKJobFacade.activate(this.path);
  }

  this.deactivate = function(){
    com.sap.xsk.xsjob.ds.facade.XSKJobFacade.deactivate(this.path);
  }

  this.configure = function(status, startAt, endAt) {
    com.sap.xsk.xsjob.ds.facade.XSKJobFacade.configure(this.path, status, parseDate(startAt), parseDate(endAt));
  }
}

function parseDate(dateObj) {
  if(dateObj instanceof Date) {
    return new java.sql.Timestamp(dateObj.getTime());
  } else {
    var timestamp = Date.parse(dateObj.value);

    if(!timestamp) throw "Invalid date format";

    return new java.sql.Timestamp(timestamp);
  }
}
