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

exports.Job = function Job(name) {
  this.name = name;

  this.activate = function(){
    com.sap.xsk.xsjob.ds.facade.XSKJobFacade.newJob(name);
    com.sap.xsk.xsjob.ds.facade.XSKJobFacade.activate(this.name);
  }

  this.deactivate = function(){
    com.sap.xsk.xsjob.ds.facade.XSKJobFacade.deactivate(this.name);
    com.sap.xsk.xsjob.ds.facade.XSKJobFacade.newJob(name);
  }
}
