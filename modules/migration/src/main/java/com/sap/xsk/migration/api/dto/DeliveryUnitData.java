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
package com.sap.xsk.migration.api.dto;

public class DeliveryUnitData {

  private final String name;
  private final String vendor;

  public DeliveryUnitData(String name, String vendor) {
    this.name = name;
    this.vendor = vendor;
  }

  public String getName() {
    return name;
  }

  public String getVendor() {
    return vendor;
  }
}
