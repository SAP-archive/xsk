package com.sap.xsk.migration.api;

import com.sap.xsk.migration.api.dto.ExecuteMigrationRequestBody;
import com.sap.xsk.migration.api.dto.HanaData;
import com.sap.xsk.migration.api.dto.MigrationRequestBody;
import com.sap.xsk.migration.api.dto.MigrationResponseBody;
import com.sap.xsk.migration.api.dto.NeoData;
import com.sap.xsk.migration.neo.db.hana.DeliveryUnit;
import com.sap.xsk.migration.neo.db.hana.DeliveryUnitsExporter;
import com.sap.xsk.migration.neo.db.hana.DeliveryUnitsProvider;
import com.sap.xsk.migration.neo.db.hana.connectivity.HanaConnector;
import com.sap.xsk.migration.neo.db.hana.connectivity.HanaConnectorRes;
import com.sap.xsk.migration.neo.sdk.command.SdkCommand;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandGenericArgs;
import com.sap.xsk.migration.neo.sdk.command.databases.ListDatabasesSdkCommandRes;
import org.eclipse.dirigible.core.workspace.api.IWorkspace;
import org.eclipse.dirigible.core.workspace.service.WorkspacesCoreService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class XSKMigrationRestServiceTest {

  private static final String TEST_NEO_HOST_NAME = "testNeoHostName";
  private static final String TEST_NEO_SUBACCOUNT = "testNeoSubaccount";
  private static final String TEST_NEO_USERNAME = "testNeoUsername";
  private static final String TEST_NEO_PASSWORD = "testNeoPassword";

  private static final String TEST_DATABASE_SCHEMA = "testDatabaseSchema";
  private static final String TEST_DATABASE_USERNAME = "testDatabaseUsername";
  private static final String TEST_DATABASE_PASSWORD = "testDatabasePassword";

  private static final NeoData TEST_NEO_DATA = new NeoData(TEST_NEO_HOST_NAME, TEST_NEO_SUBACCOUNT, TEST_NEO_USERNAME, TEST_NEO_PASSWORD);
  private static final HanaData TEST_HANA_DATA = new HanaData(TEST_DATABASE_SCHEMA, TEST_DATABASE_USERNAME, TEST_DATABASE_PASSWORD);
  private static final String TEST_DATABASE_SESSION_ID = "testDatabaseSessionId";
  private static final String TEST_WORKSPACE_NAME = "testWorkspaceName";
  private static final String TEST_DELIVERY_UNIT_NAME = "testDeliveryUnitName";
  private static final String TEST_DELIVERY_UNIT_VENDOR = "testDeliveryUnitVendor";
  private static final String TEST_DATABASE_HOST = "localhost";
  private static final String TEST_DATABASE_PORT = "30015";

  @Mock
  private HanaConnector mockHanaConnector;

  @Mock
  private HanaConnectorRes mockHanaConnectorRes;

  @Mock
  private DeliveryUnitsProvider mockDeliveryUnitsProvider;

  @Mock
  private SdkCommand<SdkCommandGenericArgs, ListDatabasesSdkCommandRes> mockListDatabasesSdkCommand;

  @Mock
  DeliveryUnitsExporter mockDeliveryUnitsExporter;

  @Mock
  WorkspacesCoreService mockWorkspacesCoreService;

  @Mock
  IWorkspace mockWorkspace;

  private XSKMigrationRestService migrationRestService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    migrationRestService = new XSKMigrationRestService(
        mockHanaConnector,
        mockDeliveryUnitsProvider,
        mockListDatabasesSdkCommand,
        mockDeliveryUnitsExporter,
        mockWorkspacesCoreService);
  }

  @Test
  public void setupMigration() {
    when(mockHanaConnectorRes.getSessionId()).thenReturn(TEST_DATABASE_SESSION_ID);
    when(
        mockHanaConnector.connect(
            argThat(arg ->
                TEST_DATABASE_SCHEMA.equals(arg.getDatabaseId())
                    && TEST_NEO_HOST_NAME.equals(arg.getHost())
                    && TEST_NEO_SUBACCOUNT.equals(arg.getSubaccountTechnicalName())
                    && TEST_NEO_USERNAME.equals(arg.getUser())
                    && TEST_NEO_PASSWORD.equals(arg.getPassword())
            )
        )
    ).thenReturn(mockHanaConnectorRes);

    when(mockWorkspace.getName()).thenReturn(TEST_WORKSPACE_NAME);
    when(mockWorkspacesCoreService.getWorkspaces()).thenReturn(Collections.singletonList(mockWorkspace));

    when(mockDeliveryUnitsProvider.getDeliveryUnitsNames(TEST_DATABASE_USERNAME, TEST_DATABASE_PASSWORD))
        .thenReturn(Collections.singletonList(new DeliveryUnit(TEST_DELIVERY_UNIT_NAME, TEST_DELIVERY_UNIT_VENDOR)));

    Response response = migrationRestService.setupMigration(new MigrationRequestBody(TEST_NEO_DATA, TEST_HANA_DATA));
    MigrationResponseBody migrationResponseBody = (MigrationResponseBody) response.getEntity();

    assertEquals("Unexpected response status code", 200, response.getStatus());
    assertEquals("Unexpected database connection id", TEST_DATABASE_SESSION_ID, migrationResponseBody.getConnectionId());
    assertEquals("Unexpected workspaces count", 1, migrationResponseBody.getWorkspaces().size());
    assertEquals("Unexpected workspace name", TEST_WORKSPACE_NAME, migrationResponseBody.getWorkspaces().get(0));
    assertEquals("Unexpected delivery units count", 1, migrationResponseBody.getDu().size());
    assertEquals("Unexpected delivery unit name", TEST_DELIVERY_UNIT_NAME, migrationResponseBody.getDu().get(0).getName());
    assertEquals("Unexpected delivery unit vendor", TEST_DELIVERY_UNIT_VENDOR, migrationResponseBody.getDu().get(0).getVendor());
  }

  @Test
  public void testSetupMigrationMethodIsAnnotatedCorrectly() throws NoSuchMethodException {
    Method setupMigrationMethod = XSKMigrationRestService.class.getDeclaredMethod("setupMigration", MigrationRequestBody.class);
    Path pathAnnotation = setupMigrationMethod.getAnnotation(Path.class);
    POST postAnnotation = setupMigrationMethod.getAnnotation(POST.class);

    assertNotNull("Unexpected null @POST annotation", postAnnotation);
    assertNotNull("Unexpected null @Path annotation", pathAnnotation);
    assertEquals("Unexpected @Path annotation value", "setup-migration", pathAnnotation.value());
  }

  @Test
  public void executeMigration() {
    Response response = migrationRestService.executeMigration(
        new ExecuteMigrationRequestBody(
            TEST_NEO_DATA,
            TEST_HANA_DATA,
            TEST_DATABASE_SESSION_ID,
            TEST_DELIVERY_UNIT_VENDOR,
            TEST_WORKSPACE_NAME,
            TEST_DELIVERY_UNIT_NAME
        )
    );

    assertEquals("Unexpected response status code", 200, response.getStatus());

    verify(mockDeliveryUnitsExporter, times(1)).exportDeliveryUnits(
        argThat(config -> TEST_DELIVERY_UNIT_VENDOR.equals(config.getDeliveryUnitVendor())
            && TEST_DELIVERY_UNIT_NAME.equals(config.getDeliveryUnitName())
            && TEST_WORKSPACE_NAME.equals(config.getWorkspace())
            && TEST_DATABASE_USERNAME.equals(config.getDbUser())
            && TEST_DATABASE_PASSWORD.equals(config.getDbPassword())
            && TEST_DATABASE_HOST.equals(config.getDbHost())
            && TEST_DATABASE_PORT.equals(config.getDbPort())));

    verify(mockHanaConnector, times(1)).disconnect(TEST_DATABASE_SESSION_ID);
  }

  @Test
  public void testExecuteMigrationMethodIsAnnotatedCorrectly() throws NoSuchMethodException {
    Method setupMigrationMethod = XSKMigrationRestService.class.getDeclaredMethod("executeMigration", ExecuteMigrationRequestBody.class);
    Path pathAnnotation = setupMigrationMethod.getAnnotation(Path.class);
    POST postAnnotation = setupMigrationMethod.getAnnotation(POST.class);

    assertNotNull("Unexpected null @POST annotation", postAnnotation);
    assertNotNull("Unexpected null @Path annotation", pathAnnotation);
    assertEquals("Unexpected @Path annotation value", "execute-migration", pathAnnotation.value());
  }

  @Test
  public void testGetType() {
    var typeClass = migrationRestService.getType();

    assertEquals("Unexpected rest service type", XSKMigrationRestService.class, typeClass);
  }
}