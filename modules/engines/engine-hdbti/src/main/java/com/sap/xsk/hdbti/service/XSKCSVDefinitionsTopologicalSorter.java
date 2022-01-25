/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
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

import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKCSVDefinitionsTopologicalSorter {

  private static final Logger logger = LoggerFactory.getLogger(XSKCSVDefinitionsTopologicalSorter.class);

  public static void sort(List<XSKTableImportConfigurationDefinition> configurationDefinitions,
      List<XSKTableImportConfigurationDefinition> sortedConfigurationDefinitions, Connection connection) {
    Map<String, XSKTableImportConfigurationDefinition> mappedConfigurationDefinititions = new HashMap<>();

    for (XSKTableImportConfigurationDefinition configurationDefinition : configurationDefinitions) {
      mappedConfigurationDefinititions.put(configurationDefinition.getTable(), configurationDefinition);
    }

    Set<XSKTableImportConfigurationDefinition> visitedConfigurationDefinitions = new HashSet<>();

    try {
      DatabaseMetaData metaData = connection.getMetaData();

      for (Entry<String, XSKTableImportConfigurationDefinition> entry : mappedConfigurationDefinititions.entrySet()) {
        Set<XSKTableImportConfigurationDefinition> cyclingDependencySet = new HashSet<>();
        visitConfigurationDefinition(entry.getValue(), visitedConfigurationDefinitions, sortedConfigurationDefinitions,
            metaData, mappedConfigurationDefinititions, cyclingDependencySet);
        if (!sortedConfigurationDefinitions.contains(entry.getValue())) {
          sortedConfigurationDefinitions.add(entry.getValue());
        }
      }
    } catch (SQLException exception) {
      logger.error(String.format("An error occurred while trying to get metadata. %s", exception.getMessage()), exception);
    }
  }

  private static void visitConfigurationDefinition(XSKTableImportConfigurationDefinition configurationDefinition,
      Set<XSKTableImportConfigurationDefinition> visitedConfigurationDefinitions,
      List<XSKTableImportConfigurationDefinition> sortedConfigurationDefinitions, DatabaseMetaData metaData,
      Map<String, XSKTableImportConfigurationDefinition> mappedConfigurationDefinititions,
      Set<XSKTableImportConfigurationDefinition> cyclingDependencySet)
      throws SQLException {

    if (mappedConfigurationDefinititions.containsKey(configurationDefinition.getTable())) {
      if (!visitedConfigurationDefinitions.contains(configurationDefinition)) {
        visitedConfigurationDefinitions.add(configurationDefinition);
        if (!cyclingDependencySet.contains(configurationDefinition)) {
          cyclingDependencySet.add(configurationDefinition);
          try {
            ResultSet foreignKeys = metaData.getImportedKeys(null, configurationDefinition.getSchema(), configurationDefinition.getTable());
            while (foreignKeys.next()) {
              String pk_table = foreignKeys.getString("PKTABLE_NAME");
              XSKTableImportConfigurationDefinition dependencyConfigDefinition = mappedConfigurationDefinititions.get(pk_table);
              if (!visitedConfigurationDefinitions.contains(dependencyConfigDefinition)) {
                visitConfigurationDefinition(dependencyConfigDefinition, visitedConfigurationDefinitions, sortedConfigurationDefinitions,
                    metaData, mappedConfigurationDefinititions, cyclingDependencySet);
                if(!sortedConfigurationDefinitions.contains(mappedConfigurationDefinititions.get(pk_table))) {
                  sortedConfigurationDefinitions.add(mappedConfigurationDefinititions.get(pk_table));
                }
              }
              if(!sortedConfigurationDefinitions.contains(configurationDefinition)){
                sortedConfigurationDefinitions.add(configurationDefinition);
              }
            }
          } catch (SQLException exception) {
            logger.error(String.format("An error occurred while trying to get metadata. %s", exception.getMessage()), exception);
          }
        }else {
          throw new SQLException(String.format("Cyclic dependency in %s ", configurationDefinition.getTable()));
        }
      }
    }
  }
}
