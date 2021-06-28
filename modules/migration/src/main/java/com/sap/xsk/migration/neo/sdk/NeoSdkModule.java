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
package com.sap.xsk.migration.neo.sdk;

import com.sap.xsk.migration.neo.sdk.command.SdkCommandModule;
import com.sap.xsk.migration.neo.sdk.parse.SdkOutputParseModule;
import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;

public class NeoSdkModule extends AbstractDirigibleModule {

  @Override
  protected void configure() {
    install(new SdkCommandModule());
    install(new SdkOutputParseModule());
  }

  @Override
  public String getName() {
    return "XSK Migration Neo SDK Module";
  }
}
