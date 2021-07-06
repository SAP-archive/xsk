package com.sap.xsk.migration.delivery.units.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseDriverManager {

  Connection getConnection(String url, String user, String password) throws SQLException;
}
