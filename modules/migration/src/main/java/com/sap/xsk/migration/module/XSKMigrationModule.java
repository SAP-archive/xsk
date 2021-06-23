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
package com.sap.xsk.migration.module;

import com.sap.xsk.migration.neo.db.DeliveryUnitsNamesProvider;
import com.sap.xsk.migration.neo.db.hana.HanaXSDeliveryUnitsNamesProvider;
import com.sap.xsk.migration.neo.db.hana.connectivity.HanaConnector;
import com.sap.xsk.migration.neo.db.hana.connectivity.TunneledHanaConnector;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandFactory;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandFactoryImpl;
import com.sap.xsk.migration.neo.sdk.parse.SdkCommandJsonOutputParser;
import com.sap.xsk.migration.neo.sdk.parse.SdkCommandOutputParser;
import com.sap.xsk.migration.tooling.CommandLineMigrationToolExecutor;
import com.sap.xsk.migration.tooling.MigrationToolExecutor;
import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;

public class XSKMigrationModule extends AbstractDirigibleModule {

  @Override
  protected void configure() {
    bind(DeliveryUnitsNamesProvider.class).to(HanaXSDeliveryUnitsNamesProvider.class);
    bind(HanaConnector.class).to(TunneledHanaConnector.class);
    bind(SdkCommandFactory.class).to(SdkCommandFactoryImpl.class);
    bind(MigrationToolExecutor.class).to(CommandLineMigrationToolExecutor.class);
//    bind(IRestService.class).to(XSKMigrationRestService.class); // TODO: see why binding here does not work and needs a 'services' file
    bind(SdkCommandOutputParser.class).to(SdkCommandJsonOutputParser.class);
  }

  @Override
  public String getName() {
    return "XSK Migration Module";
  }
}
