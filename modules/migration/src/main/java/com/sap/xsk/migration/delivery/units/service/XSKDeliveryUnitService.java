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
package com.sap.xsk.migration.delivery.units.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XSKDeliveryUnitService implements DeliveryUnitsService {

  private static final String TUNNELED_HANA_CONNECTION_URL = "jdbc:sap://localhost:30015/";
  private final DatabaseDriverManager databaseDriverManager;

  public XSKDeliveryUnitService(DatabaseDriverManager databaseDriverManager) {
    this.databaseDriverManager = databaseDriverManager;
  }

  public List<DeliveryUnit> getDeliveryUnits(String userName, String password) {
    var connection = openConnection(userName, password);
    return listDeliveryUnits(connection);
  }

  private Connection openConnection(String userName, String password) {
    try {
      var connection = databaseDriverManager.getConnection(TUNNELED_HANA_CONNECTION_URL, userName, password);

      if (connection == null) {
        throw new DeliveryUnitsException("Connection to database failed! Connection was null");
      }

      return connection;
    } catch (SQLException e) {
      throw new DeliveryUnitsException("Connection to database failed!", e);
    }
  }

  private List<DeliveryUnit> listDeliveryUnits(Connection connection) {
    var deliveryUnits = new ArrayList<DeliveryUnit>();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT DELIVERY_UNIT,VENDOR FROM \"_SYS_REPO\".\"DELIVERY_UNITS\"");

      while (resultSet.next()) {
        String deliveryUnitName = resultSet.getString(1);
        String vendor = resultSet.getString(2);
        deliveryUnits.add(new DeliveryUnit(deliveryUnitName, vendor));
      }
    } catch(SQLTimeoutException e){
      throw new DeliveryUnitsException("Extraction of data from delivery unit table timeout!", e);
    } catch (SQLException e) {
      throw new DeliveryUnitsException("Extraction of data from delivery unit table failed!", e);
    }

    return deliveryUnits;
  }
}
