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
const tasksService = require('bpm/v4/tasks');
const processService = require('bpm/v4/process');

const rs = require("http/v4/rs");
rs.service()
  .resource("start-process")
  .post(startProcess)
  .resource("continue-process")
  .post(selectDeliveryUnitAndWorkspaceForProcess)
  .resource("get-process")
  .post(getProcessState)
  .execute();

function startProcess(ctx, req, res) {
  var process = require('bpm/v4/process');
  const userDataJson = req.getJSON(); // "{\"neo\":{\"hostName\":\"eu2.hana.ondemand.com\",\"subaccount\":\"e6c9b8dff\",\"username\":\"v.mutafov@sap.com\",\"password\":\"test123\"},\"hana\":{\"databaseSchema\":\"slbinno\",\"username\":\"C5326377\",\"password\":\"test123321\"},\"connectionId\":\"63e31364-7d7e-4740-9f56-e05579268e58\",\"vendor\":\"migration.sap.com\",\"workspace\":\"workspace\",\"du\":\"MIGR_TOOLS\"}"

  const processInstanceId = process.start('migrationProcess', { "userData": JSON.stringify(userDataJson) });

  const response = {
    processInstanceId: processInstanceId
  };

  res.print(JSON.stringify(response));
}

function selectDeliveryUnitAndWorkspaceForProcess(ctx, req, res) {
  const userDataJson = req.getJSON();
  const tasksJson = org.eclipse.dirigible.api.v3.bpm.BpmFacade.getTasks();
  const tasks = JSON.parse(tasksJson);
  for (const task of tasks) {
    if (task.processInstanceId === userDataJson.processInstanceId.toString()) {
      tasksService.completeTask(task.id, { "userData": JSON.stringify(userDataJson) });
      break;
    }
  }
}

function getProcessState(ctx, req, res) {
  const userDataJson = req.getJSON();
  const processInstanceIdString = userDataJson.processInstanceId.toString();
  const migrationState = processService.getVariable(processInstanceIdString, "migrationState");
  const response = {
    migrationState: migrationState
  };

  if (migrationState === "WORKSPACES_LISTED") {
    const workspacesJson = processService.getVariable(processInstanceIdString, "workspaces");
    const deliveryUnitsJson = processService.getVariable(processInstanceIdString, "deliveryUnits");
    const connectionId = processService.getVariable(processInstanceIdString, "connectionId");
    response.workspaces = JSON.parse(workspacesJson);
    response.deliveryUnits = JSON.parse(deliveryUnitsJson);
    response.connectionId = connectionId;
  }

  res.print(JSON.stringify(response));
}

