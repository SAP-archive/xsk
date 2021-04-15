package com.sap.xsk.hdb.ds.test.itest;

import org.junit.BeforeClass;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;

public class XSKHDBSequenceParserITCase {

  private static Network network = Network.newNetwork();

  public static PostgreSQLContainer<?> postgresContainer =
      new PostgreSQLContainer<>("postgres:alpine")
          .withNetwork(network)
          .withNetworkAliases("postgres");

  @BeforeClass
  public static void startContainers() {
    Startables.deepStart(Stream.of(
        postgresContainer))
        .join();
  }

  private Connection getConnection(
      PostgreSQLContainer<?> postgresContainer)
      throws SQLException {

    return DriverManager.getConnection(postgresContainer.getJdbcUrl(),
        postgresContainer.getUsername(),
        postgresContainer.getPassword());
  }
}
