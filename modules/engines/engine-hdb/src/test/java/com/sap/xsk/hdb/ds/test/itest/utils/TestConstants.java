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
package com.sap.xsk.hdb.ds.test.itest.utils;

import org.eclipse.dirigible.commons.config.Configuration;

public class TestConstants {

  private TestConstants() {
  }
  
  public static final String HANA_DRIVER = "com.sap.db.jdbc.Driver";
  public static final String HANA_URL = Configuration.get("hana.url");
  public static final String HANA_USERNAME = Configuration.get("hana.username");
  public static final String HANA_PASSWORD = Configuration.get("hana.password");

}
