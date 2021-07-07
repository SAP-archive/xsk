package com.sap.xsk.migration.neo.db.hana;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

public class DeliveryUnitsProviderTest {

  private static final String TEST_DB_HOST = "jdbc:sap://localhost:30015";
  private static final String TEST_DB_USER = "testDbUser";
  private static final String TEST_DB_PASSWORD = "testDbPassword";
  private static final String TEST_DB_QUERY = "SELECT DELIVERY_UNIT, VENDOR FROM \"_SYS_REPO\".\"DELIVERY_UNITS\"";
  private static final String TEST_DELIVERY_UNIT_NAME = "testDeliveryUnitName";
  private static final String TEST_DELIVERY_UNIT_VENDOR = "testDeliveryUnitVendor";
  private static final String TEST_NO_DB_CONNECTION_EXCEPTION_MESSAGE = "Couldn't connect to database!";
  private static final String TEST_COULD_NOT_QUERY_DELIVERY_UNITS_EXCEPTION_MESSAGE = "Couldn't execute query!";

  @Mock
  private Connection mockConnection;

  @Mock
  private Statement mockStatement;

  @Mock
  private ResultSet mockResultSet;

  @Mock
  private ConnectionProvider databaseConnectionProvider;

  private DeliveryUnitsProvider deliveryUnitsProvider;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    deliveryUnitsProvider = new DeliveryUnitsProvider(databaseConnectionProvider);
  }

  @Test
  public void testGetDeliveryUnitsNames() throws SQLException {
    when(databaseConnectionProvider.getConnection(TEST_DB_HOST, TEST_DB_USER, TEST_DB_PASSWORD)).thenReturn(mockConnection);
    when(mockConnection.createStatement()).thenReturn(mockStatement);
    when(mockStatement.executeQuery(TEST_DB_QUERY)).thenReturn(mockResultSet);
    when(mockResultSet.next()).thenReturn(true, false);
    when(mockResultSet.getString(1)).thenReturn(TEST_DELIVERY_UNIT_NAME);
    when(mockResultSet.getString(2)).thenReturn(TEST_DELIVERY_UNIT_VENDOR);

    List<DeliveryUnit> deliveryUnits = deliveryUnitsProvider.getDeliveryUnitsNames(TEST_DB_USER, TEST_DB_PASSWORD);

    assertEquals("Unexpected delivery units list size", 1, deliveryUnits.size());
    assertEquals("Unexpected delivery unit name", TEST_DELIVERY_UNIT_NAME, deliveryUnits.get(0).getName());
    assertEquals("Unexpected delivery unit name", TEST_DELIVERY_UNIT_VENDOR, deliveryUnits.get(0).getVendor());
    verify(mockResultSet, times(1)).getString(1);
    verify(mockResultSet, times(1)).getString(2);
  }

  @Test
  public void testGetDeliveryUnitsNamesFailsWithDeliveryUnitsExceptionWhenCreateConnectionFails() throws SQLException {
    when(databaseConnectionProvider.getConnection(TEST_DB_HOST, TEST_DB_USER, TEST_DB_PASSWORD)).thenThrow(SQLException.class);

    DeliveryUnitsException caughtException = assertThrows(
        "Unexpected exception caught",
        DeliveryUnitsException.class,
        () -> deliveryUnitsProvider.getDeliveryUnitsNames(TEST_DB_USER, TEST_DB_PASSWORD)
    );

    assertEquals("Unexpected exception message", TEST_NO_DB_CONNECTION_EXCEPTION_MESSAGE, caughtException.getMessage());
    assertEquals("Unexpected exception cause class", SQLException.class, caughtException.getCause().getClass());
  }

  @Test
  public void testGetDeliveryUnitsNamesFailsWithDeliveryUnitsExceptionWhenCreateConnectionReturnsNull() throws SQLException {
    when(databaseConnectionProvider.getConnection(TEST_DB_HOST, TEST_DB_USER, TEST_DB_PASSWORD)).thenReturn(null);

    DeliveryUnitsException caughtException = assertThrows(
        "Unexpected exception caught",
        DeliveryUnitsException.class,
        () -> deliveryUnitsProvider.getDeliveryUnitsNames(TEST_DB_USER, TEST_DB_PASSWORD)
    );

    assertEquals("Unexpected exception message", TEST_NO_DB_CONNECTION_EXCEPTION_MESSAGE, caughtException.getMessage());
    assertNull("Unexpected exception cause", caughtException.getCause());
  }

  @Test
  public void testGetDeliveryUnitsNamesFailsWithDeliveryUnitsExceptionWhenCreateStatementFails() throws SQLException {
    when(databaseConnectionProvider.getConnection(TEST_DB_HOST, TEST_DB_USER, TEST_DB_PASSWORD)).thenReturn(mockConnection);
    when(mockConnection.createStatement()).thenThrow(SQLException.class);

    DeliveryUnitsException caughtException = assertThrows(
        "Unexpected exception caught",
        DeliveryUnitsException.class,
        () -> deliveryUnitsProvider.getDeliveryUnitsNames(TEST_DB_USER, TEST_DB_PASSWORD)
    );

    assertEquals("Unexpected exception message", TEST_COULD_NOT_QUERY_DELIVERY_UNITS_EXCEPTION_MESSAGE, caughtException.getMessage());
    assertEquals("Unexpected exception cause class", SQLException.class, caughtException.getCause().getClass());
  }
}