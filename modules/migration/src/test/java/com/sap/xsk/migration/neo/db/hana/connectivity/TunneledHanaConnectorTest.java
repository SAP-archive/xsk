package com.sap.xsk.migration.neo.db.hana.connectivity;

import com.sap.xsk.migration.neo.sdk.command.SdkCommand;
import com.sap.xsk.migration.neo.sdk.command.tunnel.CloseDatabaseTunnelSdkCommandArgs;
import com.sap.xsk.migration.neo.sdk.command.tunnel.CloseDatabaseTunnelSdkCommandRes;
import com.sap.xsk.migration.neo.sdk.command.tunnel.OpenDatabaseTunnelSdkCommandArgs;
import com.sap.xsk.migration.neo.sdk.command.tunnel.OpenDatabaseTunnelSdkCommandRes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

@RunWith(MockitoJUnitRunner.class)
public class TunneledHanaConnectorTest {

  private static final String TEST_DATABASE_ID = "testDatabaseId";
  private static final String TEST_SUBACCOUNT_TECHNICAL_NAME = "testSubaccountTechnicalName";
  private static final String TEST_HOST = "testHost";
  private static final String TEST_USER = "testUser";
  private static final String TEST_PASSWORD = "testPassword";

  public static final String TEST_DB_HOST = "localhost";
  public static final int TEST_DB_PORT = 30015;
  public static final String TEST_DB_TYPE = "HANAXS";
  public static final String TEST_DB_JDBC_URL = "jdbc:sap://localhost:30015/";
  public static final String TEST_DB_INSTANCE_NUMBER = "00";
  public static final String TEST_DB_USER = "C123123";
  public static final String TEST_DB_SESSION_ID = "4d279730-6451-4984-bec3-0f4dd0e88b9d";

  @Mock
  private SdkCommand<OpenDatabaseTunnelSdkCommandArgs, OpenDatabaseTunnelSdkCommandRes> mockOpenDatabaseTunnelSdkCommand;

  @Mock
  private SdkCommand<CloseDatabaseTunnelSdkCommandArgs, CloseDatabaseTunnelSdkCommandRes> mockCloseDatabaseTunnelSdkCommand;

  private HanaConnector hanaConnector;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    hanaConnector = new TunneledHanaConnector(mockOpenDatabaseTunnelSdkCommand, mockCloseDatabaseTunnelSdkCommand);
  }

  @Test
  public void testConnect() {
    var openDatabaseTunnelSdkCommandArgs = new OpenDatabaseTunnelSdkCommandArgs(
        TEST_DATABASE_ID,
        TEST_SUBACCOUNT_TECHNICAL_NAME,
        TEST_HOST,
        TEST_USER,
        TEST_PASSWORD);

    when(mockOpenDatabaseTunnelSdkCommand.execute(
        argThat(arg -> openDatabaseTunnelSdkCommandArgs.commandLineArgs().equals(arg.commandLineArgs()))
        )
    ).thenReturn(
        new OpenDatabaseTunnelSdkCommandRes(
            TEST_DB_HOST,
            TEST_DB_PORT,
            TEST_DB_TYPE,
            TEST_DB_JDBC_URL,
            TEST_DB_INSTANCE_NUMBER,
            TEST_DB_USER,
            null,
            TEST_DB_SESSION_ID
        )
    );

    HanaConnectorRes hanaConnectorRes = hanaConnector
        .connect(new HanaConnectorArgs(TEST_DATABASE_ID, TEST_SUBACCOUNT_TECHNICAL_NAME, TEST_HOST, TEST_USER, TEST_PASSWORD));

    assertEquals("Unexpected database host", TEST_DB_HOST, hanaConnectorRes.getHost());
    assertEquals("Unexpected database port", TEST_DB_PORT, hanaConnectorRes.getPort());
    assertEquals("Unexpected database type", TEST_DB_TYPE, hanaConnectorRes.getDbType());
    assertEquals("Unexpected database jdbc url", TEST_DB_JDBC_URL, hanaConnectorRes.getJdbcUrl());
    assertEquals("Unexpected database instance number", TEST_DB_INSTANCE_NUMBER, hanaConnectorRes.getInstanceNumber());
    assertEquals("Unexpected database user", TEST_DB_USER, hanaConnectorRes.getDbUser());
    assertNull("Unexpected database schema", hanaConnectorRes.getDbSchema());
    assertEquals("Unexpected database session id", TEST_DB_SESSION_ID, hanaConnectorRes.getSessionId());
  }

  @Test
  public void disconnect() {
    var closeDatabaseTunnelSdkCommandArgs = new CloseDatabaseTunnelSdkCommandArgs(TEST_DB_SESSION_ID);

    hanaConnector.disconnect(TEST_DB_SESSION_ID);

    verify(mockCloseDatabaseTunnelSdkCommand, times(1))
        .execute(argThat(arg -> closeDatabaseTunnelSdkCommandArgs.commandLineArgs().equals(arg.commandLineArgs())));
  }

}