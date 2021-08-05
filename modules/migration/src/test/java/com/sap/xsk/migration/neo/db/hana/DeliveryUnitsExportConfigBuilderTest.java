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
package com.sap.xsk.migration.neo.db.hana;

import com.sap.xsk.migration.neo.db.hana.DeliveryUnitsExportConfig.Builder;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeliveryUnitsExportConfigBuilderTest {

  private static final String TEST_WORKSPACE = "testWorkspace";
  private static final String TEST_DELIVERY_UNIT_NAME = "testDeliveryUnitName";
  private static final String TEST_DELIVERY_UNIT_VENDOR = "testDeliveryUnitVendor";
  private static final String TEST_DB_HOST = "testDbHost";
  private static final String TEST_DB_PORT = "testDbPort";
  private static final String TEST_DB_USER = "testDbUser";
  private static final String TEST_DB_PASSWORD = "testDbPassword";

  private final DeliveryUnitsExportConfig.Builder deliveryUnitsExportConfigBuilder = new Builder();

  @Test
  public void testWithWorkspace() {
    deliveryUnitsExportConfigBuilder.withWorkspace(TEST_WORKSPACE);

    assertEquals("Unexpected workspace", TEST_WORKSPACE, deliveryUnitsExportConfigBuilder.getWorkspace());
  }

  @Test
  public void testWithDeliveryUnitName() {
    deliveryUnitsExportConfigBuilder.withDeliveryUnitName(TEST_DELIVERY_UNIT_NAME);

    assertEquals("Unexpected delivery unit name", TEST_DELIVERY_UNIT_NAME, deliveryUnitsExportConfigBuilder.getDeliveryUnitName());
  }

  @Test
  public void testWithDeliveryUnitVendor() {
    deliveryUnitsExportConfigBuilder.withDeliveryUnitVendor(TEST_DELIVERY_UNIT_VENDOR);

    assertEquals("Unexpected delivery unit vendor", TEST_DELIVERY_UNIT_VENDOR, deliveryUnitsExportConfigBuilder.getDeliveryUnitVendor());
  }

  @Test
  public void testWithDbHost() {
    deliveryUnitsExportConfigBuilder.withDbHost(TEST_DB_HOST);

    assertEquals("Unexpected db host", TEST_DB_HOST, deliveryUnitsExportConfigBuilder.getDbHost());
  }

  @Test
  public void testWithDbPort() {
    deliveryUnitsExportConfigBuilder.withDbPort(TEST_DB_PORT);

    assertEquals("Unexpected db port", TEST_DB_PORT, deliveryUnitsExportConfigBuilder.getDbPort());
  }

  @Test
  public void testWithDbUser() {
    deliveryUnitsExportConfigBuilder.withDbUser(TEST_DB_USER);

    assertEquals("Unexpected db user", TEST_DB_USER, deliveryUnitsExportConfigBuilder.getDbUser());
  }

  @Test
  public void testWithDbPassword() {
    deliveryUnitsExportConfigBuilder.withDbPassword(TEST_DB_PASSWORD);

    assertEquals("Unexpected db password", TEST_DB_PASSWORD, deliveryUnitsExportConfigBuilder.getDbPassword());
  }

  @Test
  public void testBuild() {
    DeliveryUnitsExportConfig deliveryUnitsExportConfig = deliveryUnitsExportConfigBuilder.withWorkspace(TEST_WORKSPACE)
        .withDeliveryUnitName(TEST_DELIVERY_UNIT_NAME)
        .withDeliveryUnitVendor(TEST_DELIVERY_UNIT_VENDOR)
        .withDbHost(TEST_DB_HOST)
        .withDbPort(TEST_DB_PORT)
        .withDbUser(TEST_DB_USER)
        .withDbPassword(TEST_DB_PASSWORD)
        .build();

      assertEquals("Unexpected workspace", TEST_WORKSPACE, deliveryUnitsExportConfig.getWorkspace());
      assertEquals("Unexpected delivery unit name", TEST_DELIVERY_UNIT_NAME, deliveryUnitsExportConfig.getDeliveryUnitName());
      assertEquals("Unexpected delivery unit vendor", TEST_DELIVERY_UNIT_VENDOR, deliveryUnitsExportConfig.getDeliveryUnitVendor());
      assertEquals("Unexpected db host", TEST_DB_HOST, deliveryUnitsExportConfig.getDbHost());
      assertEquals("Unexpected db port", TEST_DB_PORT, deliveryUnitsExportConfig.getDbPort());
      assertEquals("Unexpected db user", TEST_DB_USER, deliveryUnitsExportConfig.getDbUser());
      assertEquals("Unexpected db password", TEST_DB_PASSWORD, deliveryUnitsExportConfig.getDbPassword());
  }
}