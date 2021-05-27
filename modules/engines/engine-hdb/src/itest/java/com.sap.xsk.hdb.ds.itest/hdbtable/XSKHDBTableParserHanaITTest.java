package com.sap.xsk.hdb.ds.itest.hdbtable;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.itest.module.XSKHDBTestModule;
import com.sap.xsk.hdb.ds.itest.utils.TestConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.local.LocalResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XSKHDBTableParserHanaITTest {
  private static Connection connection;
  private static IXSKHDBCoreFacade facade;

  @BeforeClass
  public static void setUpBeforeClass() throws SQLException, IOException {
    com.sap.xsk.hdb.ds.test.itest.model.JDBCModel model = new com.sap.xsk.hdb.ds.test.itest.model.JDBCModel(TestConstants.HANA_DRIVER,
        TestConstants.HANA_URL,
        TestConstants.HANA_USERNAME,
        TestConstants.HANA_PASSWORD);
    Injector injector = Guice.createInjector(new XSKHDBTestModule(model));
    connection = injector.getInstance(DataSource.class).getConnection();
    facade = injector.getInstance(Key.get(IXSKHDBCoreFacade.class, Names.named("xskHDBCoreFacade")));
  }

  @Before
  public void setUpBeforeTest() throws SQLException {
    Statement stmt = connection.createStatement();
    DatabaseMetaData metaData = connection.getMetaData();
    String hanaUserName = Configuration.get("hana.username");
    ResultSet table = metaData.getTables(null, hanaUserName, "XSK_DATA_STRUCTURES", null);
    if (table.next()) {
      stmt.executeUpdate(String.format("drop table \"%s\".\"XSK_DATA_STRUCTURES\"", hanaUserName));
    }
  }

  @Test
  public void testHDBTableCreate() throws XSKDataStructuresException, SynchronizationException, IOException, SQLException {
    LocalResource resource = XSKHDBTestModule.getResources("/usr/local/target/dirigible/repository/root",
        "/registry/public/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable",
        "/hdbtable-itest/SamplePostgreXSClassicTable.hdbtable");

    this.facade.handleResourceSynchronization(resource);
    this.facade.updateEntities();

    Statement stmt = connection.createStatement();
    DatabaseMetaData metaData = connection.getMetaData();
    String hanaUserName = Configuration.get("hana.username");
    ResultSet table = metaData.getTables(null, hanaUserName, "hdbtable-itest::SamplePostgreXSClassicTable", null);
    assertTrue(table.next());

    stmt.executeUpdate(String.format("drop table \"%s\".\"hdbtable-itest::SamplePostgreXSClassicTable\"", hanaUserName));

  }

}
