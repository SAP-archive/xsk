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
package com.sap.xsk.migration.neo.db.hana;

import com.sap.xsk.migration.neo.db.DeliveryUnitsProvider;
import com.sap.xsk.migration.neo.db.hana.connectivity.HanaConnectorModule;
import org.eclipse.dirigible.commons.api.module.AbstractDirigibleModule;

public class HanaModule extends AbstractDirigibleModule {

  @Override
  protected void configure() {
    install(new HanaConnectorModule());
    bind(DeliveryUnitsProvider.class).to(HanaXSDeliveryUnitsProvider.class);
  }

  @Override
  public String getName() {
    return "XSK Migration Hana Module";
  }
}
