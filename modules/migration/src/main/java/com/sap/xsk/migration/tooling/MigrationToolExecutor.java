/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.migration.tooling;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface MigrationToolExecutor {

  String executeMigrationTool(String migrationToolDirectory, String migrationToolName, String command, List<String> args);

  String executeMigrationTool(String migrationToolDirectory, String migrationToolName, String command, List<String> args, long timeout, TimeUnit timeoutUnit);

  String executeMigrationTool(String migrationToolDirectory, String migrationToolName, List<String> args);

  String executeMigrationTool(String migrationToolDirectory, String migrationToolName, List<String> args, long timeout, TimeUnit timeoutUnit);
}
