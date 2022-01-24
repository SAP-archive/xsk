/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdbti.service;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class XSKCSVDefinitionsTopologicalSorterTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private DatabaseMetaData mockDatabaseMetadata;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private ResultSet mockResultSet;

  @Before
  public void openMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSort() throws SQLException {

    List<XSKTableImportConfigurationDefinition> configurationDefinitions = new ArrayList<>();
    Map<String, XSKTableImportConfigurationDefinition> mappedConfigurationDefinitions = new HashMap<String, XSKTableImportConfigurationDefinition>();

    XSKTableImportConfigurationDefinition ordersTableConfigurationDefinition = new XSKTableImportConfigurationDefinition();
    ordersTableConfigurationDefinition.setTable("products::Orders");
    ordersTableConfigurationDefinition.setFile("Orders.csv");
    ordersTableConfigurationDefinition.setSchema("DBADMIN");

    configurationDefinitions.add(ordersTableConfigurationDefinition);
    mappedConfigurationDefinitions.put("products::Orders", ordersTableConfigurationDefinition);

    XSKTableImportConfigurationDefinition countryTableConfigurationDefinition = new XSKTableImportConfigurationDefinition();
    countryTableConfigurationDefinition.setTable("products::Countries");
    countryTableConfigurationDefinition.setFile("Countries.csv");
    countryTableConfigurationDefinition.setSchema("DBADMIN");

    configurationDefinitions.add(countryTableConfigurationDefinition);
    mappedConfigurationDefinitions.put("products::Countries", countryTableConfigurationDefinition);

    XSKTableImportConfigurationDefinition itemTableConfigurationDefinition = new XSKTableImportConfigurationDefinition();
    itemTableConfigurationDefinition.setTable("products::Items");
    itemTableConfigurationDefinition.setFile("Items.csv");
    itemTableConfigurationDefinition.setSchema("DBADMIN");

    configurationDefinitions.add(itemTableConfigurationDefinition);
    mappedConfigurationDefinitions.put("products::Items", itemTableConfigurationDefinition);

    for (Entry<String, XSKTableImportConfigurationDefinition> entry : mappedConfigurationDefinitions.entrySet()) {
      System.out.println(entry.getKey());
    }

    List<XSKTableImportConfigurationDefinition> sortedConfigurationDefinitions = new ArrayList<>();

    when(mockConnection.getMetaData()).thenReturn(mockDatabaseMetadata);
    when(mockDatabaseMetadata.getImportedKeys(null,"DBADMIN","products::Orders")).thenReturn(mockResultSet);
    when(mockResultSet.next()).thenReturn(true,false,false);
    when(mockResultSet.getString("PKTABLE_NAME")).thenReturn("products::Countries");
    when(mockDatabaseMetadata.getImportedKeys(null,"DBADMIN","products::Countries")).thenReturn(mockResultSet);
    when(mockDatabaseMetadata.getImportedKeys(null,"DBADMIN","products::Items")).thenReturn(mockResultSet);

    XSKCSVDefinitionsTopologicalSorter.sort(configurationDefinitions,sortedConfigurationDefinitions,mockConnection);

    for (XSKTableImportConfigurationDefinition configurationDefinition : sortedConfigurationDefinitions) {
      System.out.println(configurationDefinition.getTable());
    }

    assertEquals(sortedConfigurationDefinitions.get(0).getTable(), "products::Countries");
    assertEquals(sortedConfigurationDefinitions.get(1).getTable(), "products::Orders");
    assertEquals(sortedConfigurationDefinitions.get(2).getTable(), "products::Items");
  }
}
