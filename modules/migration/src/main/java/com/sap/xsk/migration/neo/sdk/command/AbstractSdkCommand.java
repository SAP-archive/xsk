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
package com.sap.xsk.migration.neo.sdk.command;

import com.sap.xsk.migration.neo.sdk.parse.SdkCommandOutputParser;
import com.sap.xsk.migration.tooling.MigrationToolExecutor;
import javax.inject.Inject;

public abstract class AbstractSdkCommand<TArgs extends SdkCommandArgs, TRes extends SdkCommandRes> implements SdkCommand<TArgs, TRes> {

  protected final MigrationToolExecutor migrationToolExecutor;
  protected final SdkCommandOutputParser sdkCommandOutputParser;

  @Inject
  public AbstractSdkCommand(MigrationToolExecutor migrationToolExecutor,
      SdkCommandOutputParser sdkCommandOutputParser) {
    this.migrationToolExecutor = migrationToolExecutor;
    this.sdkCommandOutputParser = sdkCommandOutputParser;
  }
}
