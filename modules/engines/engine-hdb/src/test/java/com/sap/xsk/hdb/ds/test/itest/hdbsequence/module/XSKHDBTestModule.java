package com.sap.xsk.hdb.ds.test.itest.hdbsequence.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import org.apache.commons.dbcp2.BasicDataSource;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.eclipse.dirigible.repository.api.IRepository;
import org.junit.BeforeClass;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import javax.sql.DataSource;
import java.util.stream.Stream;

public class XSKHDBTestModule extends AbstractModule {

  private static Network network = Network.newNetwork();

  private static PostgreSQLContainer<?> postgresContainer =
      new PostgreSQLContainer<>("postgres:alpine")
          .withNetwork(network)
          .withNetworkAliases("postgres");


  public static void startContainers() {
    Startables.deepStart(Stream.of(
        postgresContainer))
        .join();
  }

  @Override
  protected void configure() {
    install(new XSKHDBModule());
  }

  @Provides
  static DataSource getDataSource() {
    startContainers();
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName(postgresContainer.getDriverClassName());
    basicDataSource.setUrl(postgresContainer.getJdbcUrl());
    basicDataSource.setUsername(postgresContainer.getUsername());
    basicDataSource.setPassword(postgresContainer.getPassword());
    basicDataSource.setDefaultAutoCommit(true);
    basicDataSource.setAccessToUnderlyingConnectionAllowed(true);

    return basicDataSource;

  }
}
