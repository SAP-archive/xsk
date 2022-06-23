package com.sap.xsk.integration.tests.applications.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

import static com.sap.xsk.integration.tests.core.hdb.utils.TestConstants.HANA_DRIVER;
import static com.sap.xsk.integration.tests.core.hdb.utils.TestConstants.HANA_PASSWORD;
import static com.sap.xsk.integration.tests.core.hdb.utils.TestConstants.HANA_URL;
import static com.sap.xsk.integration.tests.core.hdb.utils.TestConstants.HANA_USERNAME;

public class HanaDataSourceFactory {

  private static DataSource dataSource;
  private final static HikariConfig config;

  static {
    config = new HikariConfig();

    config.setDriverClassName(HANA_DRIVER);
    config.setJdbcUrl(HANA_URL);
    config.setUsername(HANA_USERNAME);
    config.setPassword(HANA_PASSWORD);

    config.setLeakDetectionThreshold(10000);

    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
  }

  private HanaDataSourceFactory() {
  }

  public static DataSource createHanaDataSource() {
    if (dataSource == null) {
      dataSource = new HikariDataSource(config);
    }
    return dataSource;
  }
}
