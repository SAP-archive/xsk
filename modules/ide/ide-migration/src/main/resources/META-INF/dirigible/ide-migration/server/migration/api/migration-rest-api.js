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
const httpClient = require("http/v4/client");

const rs = require("http/v4/rs");
rs.service()
  .resource("start-process")
  .post(startProcess)
  .resource("continue-process")
  .post(continueProcess)
  .resource("get-process")
  .post(getProcessState)
  .execute();

function startProcess(ctx, req, res) {
  const userDataJson = req.getJSON();
  const neoData = userDataJson.neo;

  const tokenResponse = getJwtToken(neoData.hostName, neoData.username, neoData.password);
  if (tokenResponse.error) {
    res.setStatus(403);
    res.print(JSON.stringify({
      error: {
        message: tokenResponse.error_description
      }
    }))
    return;
  }

  const processInstanceId = processService.start('migrationProcess', {
    userData: JSON.stringify(userDataJson),
    userJwtToken: tokenResponse.access_token,
  });

  const response = {
    processInstanceId: processInstanceId
  };

  res.print(JSON.stringify(response));
}

function getJwtToken(host, username, password) {
  const jwtTokenServiceUrl = `https://oauthasservices.${host}/oauth2/api/v1/token?grant_type=password&username=${username}&password=${password}`;
  const jwtTokenResponse = httpClient.post(jwtTokenServiceUrl, {
    headers: [{
      name: "Content-Type",
      value: "application/x-www-form-urlencoded"
    }]
  });

  const jwtTokenResponseJson = JSON.parse(jwtTokenResponse.text);
  return jwtTokenResponseJson;
}

function continueProcess(ctx, req, res) {
  const userDataJson = req.getJSON();

  const tasksJson = org.eclipse.dirigible.api.v3.bpm.BpmFacade.getTasks();
  const tasks = JSON.parse(tasksJson);
  for (const task of tasks) {
    if (task.processInstanceId === userDataJson.processInstanceId.toString()) {
      tasksService.completeTask(task.id, {
        userData: JSON.stringify(userDataJson)
      });
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

  if (migrationState.endsWith("_FAILED")) {
    response.failed = true;
    const failureReason = processService.getVariable(processInstanceIdString, migrationState + "_REASON");
    if (failureReason) {
      response.failureReason = failureReason;
    }
  } else if (migrationState === "DATABASES_LISTED") {
    const databasesJson = processService.getVariable(processInstanceIdString, "databases");
    response.databases = JSON.parse(databasesJson);
  } else if (migrationState === "WORKSPACES_LISTED") {
    const workspacesJson = processService.getVariable(processInstanceIdString, "workspaces");
    const deliveryUnitsJson = processService.getVariable(processInstanceIdString, "deliveryUnits");
    const connectionId = processService.getVariable(processInstanceIdString, "connectionId");
    response.workspaces = JSON.parse(workspacesJson);
    response.deliveryUnits = JSON.parse(deliveryUnitsJson);
    response.connectionId = connectionId;
  }

  res.print(JSON.stringify(response));
}

