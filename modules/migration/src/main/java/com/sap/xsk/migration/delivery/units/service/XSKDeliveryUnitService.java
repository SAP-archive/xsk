package com.sap.xsk.migration.delivery.units.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XSKDeliveryUnitService implements DeliveryUnitsService {

  private static final String TUNNELED_HANA_CONNECTION_URL = "jdbc:sap://localhost:30015/";

  public List<DeliveryUnit> getDeliveryUnits(String userName, String password){
    Connection connection = null;
    connection = openConnection(connection, userName, password);

    if (connection != null) {
      return listDeliveryUnits(connection);
    }
    throw new DeliveryUnitsException("Connection to database failed!");
  }

  private Connection openConnection(Connection con, String userName, String password) {
    try {
      con = DriverManager.getConnection(TUNNELED_HANA_CONNECTION_URL, userName, password);
    } catch (SQLException e) {
      System.err.println("Connection Failed:");
      System.err.println(e);
    }
    return con;
  }

  private List<DeliveryUnit> listDeliveryUnits(Connection con){
    List<DeliveryUnit> dUnitList = new ArrayList<DeliveryUnit>();
    try {
      Statement stmt = con.createStatement();
      ResultSet resultSet = stmt.executeQuery("SELECT DELIVERY_UNIT,VENDOR FROM \"_SYS_REPO\".\"DELIVERY_UNITS\"");

      while (resultSet.next()) {
        String deliveryUnitName = resultSet.getString(1);
        String vendor = resultSet.getString(2);
        dUnitList.add(new DeliveryUnit(deliveryUnitName, vendor));
      }
    } catch (SQLException e) {
      System.err.println("Query failed!");
      System.err.println(e);
      throw new DeliveryUnitsException("Extraction of data from delivery unit table failed!");
    }
    return dUnitList;
  }
}
