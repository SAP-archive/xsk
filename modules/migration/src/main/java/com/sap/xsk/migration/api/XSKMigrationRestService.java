/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.migration.api;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sap.xsk.migration.api.dto.database.HanaDatabaseIdsRequestBody;
import com.sap.xsk.migration.api.dto.database.HanaDatabaseIdsResponseBody;
import com.sap.xsk.migration.api.dto.DeliveryUnitData;
import com.sap.xsk.migration.api.dto.ExecuteMigrationRequestBody;
import com.sap.xsk.migration.api.dto.MigrationRequestBody;
import com.sap.xsk.migration.api.dto.MigrationResponseBody;
import com.sap.xsk.migration.module.XSKMigrationModuleServiceProvider;
import com.sap.xsk.migration.neo.db.hana.DeliveryUnitsExportConfig;
import com.sap.xsk.migration.neo.db.hana.DeliveryUnitsExporter;
import com.sap.xsk.migration.neo.db.hana.DeliveryUnitsProvider;
import com.sap.xsk.migration.neo.db.hana.connectivity.HanaConnector;
import com.sap.xsk.migration.neo.db.hana.connectivity.HanaConnectorArgs;
import com.sap.xsk.migration.neo.db.hana.connectivity.HanaConnectorRes;
import com.sap.xsk.migration.neo.sdk.command.SdkCommand;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandGenericArgs;
import com.sap.xsk.migration.neo.sdk.command.databases.ListDatabasesSdkCommandRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.eclipse.dirigible.commons.api.service.AbstractRestService;
import org.eclipse.dirigible.commons.api.service.IRestService;
import org.eclipse.dirigible.core.workspace.service.WorkspacesCoreService;
import org.eclipse.dirigible.repository.api.IEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Path("/migration-operations")
@Api(value = "XSK Migration Service", authorizations = {@Authorization(value = "basicAuth", scopes = {})})
@ApiResponses({@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
    @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Server Error")})
public class XSKMigrationRestService extends AbstractRestService {

  private static final Logger logger = LoggerFactory.getLogger(XSKMigrationRestService.class);

  @Context
  private HttpServletResponse response;

  private final HanaConnector hanaConnector;

  private final DeliveryUnitsProvider deliveryUnitsProvider;

  private final SdkCommand<SdkCommandGenericArgs, ListDatabasesSdkCommandRes> listDatabasesSdkCommand;

  private final DeliveryUnitsExporter deliveryUnitsExporter;

  private final WorkspacesCoreService workspacesCoreService;

  public XSKMigrationRestService() {
    hanaConnector = XSKMigrationModuleServiceProvider.createHanaConnector();
    listDatabasesSdkCommand = XSKMigrationModuleServiceProvider.createListDatabasesSdkCommand();
    deliveryUnitsProvider = XSKMigrationModuleServiceProvider.createDeliveryUnitsProvider();
    deliveryUnitsExporter = XSKMigrationModuleServiceProvider.createDeliveryUnitsExporter();
    workspacesCoreService = XSKMigrationModuleServiceProvider.createWorkspacesCoreService();
  }

  public XSKMigrationRestService(HanaConnector hanaConnector, DeliveryUnitsProvider deliveryUnitsProvider,
      SdkCommand<SdkCommandGenericArgs, ListDatabasesSdkCommandRes> listDatabasesSdkCommand,
      DeliveryUnitsExporter deliveryUnitsExporter, WorkspacesCoreService workspacesCoreService) {
    this.hanaConnector = hanaConnector;
    this.deliveryUnitsProvider = deliveryUnitsProvider;
    this.listDatabasesSdkCommand = listDatabasesSdkCommand;
    this.deliveryUnitsExporter = deliveryUnitsExporter;
    this.workspacesCoreService = workspacesCoreService;
  }

  @POST
  @Path("setup-migration")
  @ApiResponse(code = 200, message = "Ok", response = MigrationResponseBody.class)
  public Response setupMigration(MigrationRequestBody migrationRequestBody) {
    HanaConnectorRes hanaConnectorRes = createHanaConnection(migrationRequestBody);
    List<DeliveryUnitData> deliveryUnits = getDeliveryUnits(migrationRequestBody);
    List<String> workspacesNames = workspacesCoreService.getWorkspaces().stream().map(IEntity::getName).collect(Collectors.toList());

    var responseBody = new MigrationResponseBody(hanaConnectorRes.getSessionId(), workspacesNames, deliveryUnits);

    return Response.ok(responseBody).build();
  }

  private HanaConnectorRes createHanaConnection(MigrationRequestBody migrationRequestBody) {
    var hanaConnectorArgs = toHanaConnectorArgs(migrationRequestBody);
    return hanaConnector.connect(hanaConnectorArgs);
  }

  private HanaConnectorArgs toHanaConnectorArgs(MigrationRequestBody migrationRequestBody) {
    var hanaData = migrationRequestBody.getHana();
    var neoData = migrationRequestBody.getNeo();
    return new HanaConnectorArgs(
        hanaData.getDatabaseSchema(),
        neoData.getSubaccount(),
        neoData.getHostName(),
        neoData.getUsername(),
        neoData.getPassword()
    );
  }

  private List<DeliveryUnitData> getDeliveryUnits(MigrationRequestBody migrationRequestBody) {
    var hanaData = migrationRequestBody.getHana();
    return deliveryUnitsProvider
        .getDeliveryUnits(hanaData.getUsername(), hanaData.getPassword())
        .stream()
        .map(deliveryUnit -> new DeliveryUnitData(deliveryUnit.getName(), deliveryUnit.getVendor()))
        .collect(Collectors.toList());
  }

  @POST
  @Path("execute-migration")
  @ApiResponse(code = 200, message = "Ok")
  public Response executeMigration(ExecuteMigrationRequestBody executeMigrationRequestBody) {
    var exportConfig = new DeliveryUnitsExportConfig.Builder()
        .withWorkspace(executeMigrationRequestBody.getWorkspace())
        .withDeliveryUnitName(executeMigrationRequestBody.getDu())
        .withDeliveryUnitVendor(executeMigrationRequestBody.getVendor())
        .withDbHost("localhost")
        .withDbPort("30015")
        .withDbUser(executeMigrationRequestBody.getHana().getUsername())
        .withDbPassword(executeMigrationRequestBody.getHana().getPassword())
        .build();

    deliveryUnitsExporter.exportDeliveryUnits(exportConfig);
    hanaConnector.disconnect(executeMigrationRequestBody.getConnectionId());
    return Response.ok().build();
  }

  @POST
  @Path("list-databases")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDatabaseIds(HanaDatabaseIdsRequestBody hanaDatabaseIdsRequestBody) {
    var sdkCommandArgs = toSdkCommandGenericArgs(hanaDatabaseIdsRequestBody);
    ListDatabasesSdkCommandRes listDatabasesSdkCommandRes = listDatabasesSdkCommand.execute(sdkCommandArgs);
    var databasesIds = listDatabasesSdkCommandRes.getDatabaseIds();
    var responseBody = new HanaDatabaseIdsResponseBody(databasesIds);
    return Response.ok(responseBody).build();
  }

  private SdkCommandGenericArgs toSdkCommandGenericArgs(HanaDatabaseIdsRequestBody hanaDatabaseIdsRequestBody) {
    return new SdkCommandGenericArgs(
        hanaDatabaseIdsRequestBody.getAccount(),
        hanaDatabaseIdsRequestBody.getHost(),
        hanaDatabaseIdsRequestBody.getUser(),
        hanaDatabaseIdsRequestBody.getPassword()
    );
  }

  @Override
  protected org.slf4j.Logger getLogger() {
    return logger;
  }

  @Override
  public Class<? extends IRestService> getType() {
    return XSKMigrationRestService.class;
  }
}
