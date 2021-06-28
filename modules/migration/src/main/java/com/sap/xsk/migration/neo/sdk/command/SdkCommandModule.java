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

import com.sap.xsk.migration.neo.sdk.command.databases.DatabasesSdkCommandModule;
import com.sap.xsk.migration.neo.sdk.command.tunnel.TunnelSdkCommandModule;
import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;

public class SdkCommandModule extends AbstractDirigibleModule {

  @Override
  protected void configure() {
    install(new DatabasesSdkCommandModule());
    install(new TunnelSdkCommandModule());
  }

  @Override
  public String getName() {
    return "XSK Migration SDK Command Module";
  }
}
