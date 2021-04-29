package com.sap.xsk.hdb.ds.test.itest.hdbtable;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.test.itest.module.XSKHDBTestContainersModule;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.sap.xsk.hdb.ds.test.itest.utils.TestContainerConstants.*;
import static com.sap.xsk.hdb.ds.test.itest.utils.TestContainerConstants.HDBTABLE_POSTGRESQL_EXPECTED_CREATED_RAWS_COUNT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XSKHDBTableParserMySQLITCase {

  private static MySQLContainer jdbcContainer;
  private static Connection connection;
  private static IXSKHDBCoreFacade facade;


  @BeforeClass
  public static void setUp() throws SQLException {
    Network network = Network.newNetwork();
    jdbcContainer =
        new MySQLContainer<>(HDBTABLE_MYSQL_DOCKER_IMAGE);
    jdbcContainer.start();
    Injector injector = Guice.createInjector(new XSKHDBTestContainersModule(jdbcContainer));
    connection = injector.getInstance(DataSource.class).getConnection();
    facade = injector.getInstance(Key.get(IXSKHDBCoreFacade.class, Names.named("xskHDBCoreFacade")));
  }

  @Test
  public void testHDBTableCreate() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    LocalResource resource = XSKHDBTestContainersModule.getResources(HDBTABLE_MYSQL_ROOT_FOLDER,
        HDBTABLE_MYSQL_REPO_PATH,
        HDBTABLE_MYSQL_RELATIVE_RESOURCES_PATH);

    this.facade.handleResourceSynchronization(resource);
    this.facade.updateEntities();

    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery(String.format("SELECT COUNT(*) as rawsCount FROM \"%s\"", HDBTABLE_MYSQL_EXPECTED_TABLE_NAME));
    assertTrue(rs.next());
    assertEquals(HDBTABLE_MYSQL_EXPECTED_CREATED_RAWS_COUNT, rs.getInt("rawsCount"));
  }
}
