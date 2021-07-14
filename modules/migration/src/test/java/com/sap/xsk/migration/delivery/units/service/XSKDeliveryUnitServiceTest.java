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

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

public class XSKDeliveryUnitServiceTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Connection mockConnection;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private Statement mockStatement;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private ResultSet mockResultSet;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private DatabaseDriverManager databaseDriverManager;

  private static final String url = "jdbc:sap://localhost:30015/";
  private static final String userName = "username";
  private static final String password = "password";

  private static final String deliveryUnitName = "deliveryUnit";
  private static final String vendorName = "vendor";

  @Before
  public void openMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testDeliveryUnitExtraction() throws SQLException {
    when(mockResultSet.next()).thenReturn(true, false);
    when(mockResultSet.getString(1)).thenReturn(deliveryUnitName);
    when(mockResultSet.getString(2)).thenReturn(vendorName);

    when(mockStatement.executeQuery("SELECT DELIVERY_UNIT,VENDOR FROM \"_SYS_REPO\".\"DELIVERY_UNITS\"")).thenReturn(mockResultSet);
    when(mockConnection.createStatement()).thenReturn(mockStatement);

    when(databaseDriverManager.getConnection(url, userName, password)).thenReturn(mockConnection);

    XSKDeliveryUnitService sq = new XSKDeliveryUnitService(databaseDriverManager);
    List<DeliveryUnit> dUnitList = sq.getDeliveryUnits(userName, password);
    assertFalse(dUnitList.isEmpty());
  }

  @Test
  public void testGetDeliverUnitsFailsWhenOpenConnectionThrowsSQLException() throws SQLException {
    XSKDeliveryUnitService sq = new XSKDeliveryUnitService(databaseDriverManager);

    when(databaseDriverManager.getConnection(url, userName, password)).thenThrow(SQLException.class);

    DeliveryUnitsException caughtException = assertThrows(DeliveryUnitsException.class, () -> {
      sq.getDeliveryUnits(userName,password);
    });

    String expectedMessage = "Connection to database failed!";
    String actualMessage = caughtException.getMessage();
    Throwable actualCause = caughtException.getCause();

    assertEquals("Unexpected exception message", expectedMessage, actualMessage);
    assertEquals("Unexpected exception cause", SQLException.class, actualCause.getClass());
  }

  @Test
  public void testGetDeliverUnitsFailsWhenOpenConnectionReturnsNull() throws SQLException {
    XSKDeliveryUnitService sq = new XSKDeliveryUnitService(databaseDriverManager);

    when(databaseDriverManager.getConnection(url, userName, password)).thenReturn(null);

    DeliveryUnitsException caughtException = assertThrows(DeliveryUnitsException.class, () -> {
      sq.getDeliveryUnits(userName,password);
    });

    String expectedMessage = "Connection to database failed! Connection was null";
    String actualMessage = caughtException.getMessage();
    Throwable actualCause = caughtException.getCause();

    assertEquals("Unexpected exception message", expectedMessage, actualMessage);
    assertNull("Unexpected exception cause", actualCause);
  }

  @Test
  public void testGetDeliverUnitsFailsWhenExtractionFailed() throws SQLException {
    XSKDeliveryUnitService sq = new XSKDeliveryUnitService(databaseDriverManager);

    when(mockConnection.createStatement()).thenThrow(SQLException.class);

    when(databaseDriverManager.getConnection(url, userName, password)).thenReturn(mockConnection);

    DeliveryUnitsException caughtException = assertThrows(DeliveryUnitsException.class, () -> {
      sq.getDeliveryUnits(userName,password);
    });

    String expectedMessage = "Extraction of data from delivery unit table failed!";
    String actualMessage = caughtException.getMessage();
    Throwable actualCause = caughtException.getCause();

    assertEquals("Unexpected exception message", expectedMessage, actualMessage);
    assertEquals("Unexpected exception cause", SQLException.class, actualCause.getClass());
  }

  @Test
  public void testGetDeliverUnitsFailsWhenExtractionTimeout() throws SQLException {
    XSKDeliveryUnitService sq = new XSKDeliveryUnitService(databaseDriverManager);

    when(mockConnection.createStatement()).thenThrow(SQLTimeoutException.class);

    when(databaseDriverManager.getConnection(url, userName, password)).thenReturn(mockConnection);

    DeliveryUnitsException caughtException = assertThrows(DeliveryUnitsException.class, () -> {
      sq.getDeliveryUnits(userName,password);
    });

    String expectedMessage = "Extraction of data from delivery unit table timeout!";
    String actualMessage = caughtException.getMessage();
    Throwable actualCause = caughtException.getCause();

    assertEquals("Unexpected exception message", expectedMessage, actualMessage);
    assertEquals("Unexpected exception cause", SQLTimeoutException.class, actualCause.getClass());
  }

  @Test
  public void testGetDeliverUnitsFailsWhenSQLQueryFails() throws SQLException {
    XSKDeliveryUnitService sq = new XSKDeliveryUnitService(databaseDriverManager);

    when(mockStatement.executeQuery("SELECT DELIVERY_UNIT,VENDOR FROM \"_SYS_REPO\".\"DELIVERY_UNITS\"")).thenThrow(SQLException.class);
    when(mockConnection.createStatement()).thenReturn(mockStatement);
    when(databaseDriverManager.getConnection(url, userName, password)).thenReturn(mockConnection);

    DeliveryUnitsException caughtException = assertThrows(DeliveryUnitsException.class, () -> {
      sq.getDeliveryUnits(userName,password);
    });

    String expectedMessage = "Extraction of data from delivery unit table failed!";
    String actualMessage = caughtException.getMessage();
    Throwable actualCause = caughtException.getCause();

    assertEquals("Unexpected exception message", expectedMessage, actualMessage);
    assertEquals("Unexpected exception cause", SQLException.class, actualCause.getClass());
  }
}