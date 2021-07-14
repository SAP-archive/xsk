package com.sap.xsk.migration.delivery.units.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HanaDriverManager implements DatabaseDriverManager {
  @Override
  public Connection getConnection(String url, String user, String password) throws SQLException {
    return DriverManager.getConnection(url,user,password);
  }
}
