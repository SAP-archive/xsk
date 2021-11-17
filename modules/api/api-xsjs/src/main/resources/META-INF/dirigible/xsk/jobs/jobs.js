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

exports.Job = function Job(constructJob) {
  if (!constructJob.uri) throw "URI not specified";

  this.names = com.sap.xsk.xsjob.ds.facade.XSKJobFacade.newJob(registry.getText(constructJob.uri));

  this.activate = function () {
    com.sap.xsk.xsjob.ds.facade.XSKJobFacade.activate(this.names);
  }

  this.deactivate = function () {
    com.sap.xsk.xsjob.ds.facade.XSKJobFacade.deactivate(this.names);
  }

  this.configure = function (config) {
    if(!config.start_time) throw "Start time must be provided";

    com.sap.xsk.xsjob.ds.facade.XSKJobFacade.configure(this.names, config.status, parseDate(config.start_time), config.end_time ? parseDate(config.end_time) : null);
  }

  this.getConfiguration = function () {
    let configuration = com.sap.xsk.xsjob.ds.facade.XSKJobFacade.getConfiguration(this.names[0]);
    let startAtTimestamp = configuration.getStartAt();
    let endAtTimestamp = configuration.getEndAt();

    return {
      status: com.sap.xsk.xsjob.ds.facade.XSKJobFacade.isActive(this.names[0]),
      start_time: startAtTimestamp ? new Date(startAtTimestamp.getTime()) : null,
      end_time: endAtTimestamp ? new Date(endAtTimestamp.getTime()) : null
    };
  }
}

function parseDate(dateObj) {
  if (dateObj instanceof Date) {
    return new java.sql.Timestamp(dateObj.getTime());
  } else {
    var timestamp = Date.parse(dateObj.value);

    if (!timestamp) throw "Invalid date format";

    return new java.sql.Timestamp(timestamp);
  }
}
