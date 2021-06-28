/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.migration.api;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sap.xsk.migration.api.database.HanaDatabaseDeliveryUnitsResponseBody;
import com.sap.xsk.migration.api.database.HanaDatabaseIdsRequestBody;
import com.sap.xsk.migration.api.database.HanaDatabaseIdsResponseBody;
import com.sap.xsk.migration.api.dto.DeliveryUnitData;
import com.sap.xsk.migration.api.dto.ExecuteMigrationRequestBody;
import com.sap.xsk.migration.api.dto.MigrationRequestBody;
import com.sap.xsk.migration.api.dto.MigrationResponseBody;
import com.sap.xsk.migration.neo.db.DeliveryUnit;
import com.sap.xsk.migration.neo.db.DeliveryUnitsProvider;
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
import org.eclipse.dirigible.api.v3.platform.WorkspaceFacade;
import org.eclipse.dirigible.commons.api.service.AbstractRestService;
import org.eclipse.dirigible.commons.api.service.IRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Path("/migration-operations")
@Api(value = "JavaScript Engine - HANA XS Classic", authorizations = {@Authorization(value = "basicAuth", scopes = {})})
@ApiResponses({@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
    @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Server Error")})
public class XSKMigrationRestService extends AbstractRestService {

  private static final Logger logger = LoggerFactory.getLogger(XSKMigrationRestService.class);

  @Context
  private HttpServletResponse response;

  @Inject
  private HanaConnector hanaConnector;

  @Inject
  private DeliveryUnitsProvider deliveryUnitsProvider;

  @Inject
  private SdkCommand<SdkCommandGenericArgs, ListDatabasesSdkCommandRes> listDatabasesSdkCommand;

  @POST
  @Path("setup-migration")
  public Response setupMigration(MigrationRequestBody migrationRequestBody) {
    HanaConnectorRes hanaConnectorRes = createHanaConnection(migrationRequestBody);
    List<DeliveryUnitData> deliveryUnits = getDeliveryUnits(migrationRequestBody);
    List<String> workspacesNames = getWorkspaceNames();

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

  private List<String> getWorkspaceNames() {
    var stringListTypeToken = new TypeToken<List<String>>() {
    }.getType();
    String workspacesNamesJson = WorkspaceFacade.getWorkspacesNames();
    return new Gson().fromJson(workspacesNamesJson, stringListTypeToken);
  }

  private List<DeliveryUnitData> getDeliveryUnits(MigrationRequestBody migrationRequestBody) {
    var hanaData = migrationRequestBody.getHana();
    return deliveryUnitsProvider
        .getDeliveryUnitsNames(hanaData.getUsername(), hanaData.getPassword())
        .stream()
        .map(deliveryUnit -> new DeliveryUnitData(deliveryUnit.getName(), deliveryUnit.getVendor()))
        .collect(Collectors.toList());
  }

  @POST
  @Path("execute-migration")
  public Response executeMigration(ExecuteMigrationRequestBody executeMigrationRequestBody) {
    hanaConnector.disconnect(executeMigrationRequestBody.getConnectionId());
    return Response.ok().build();
  }

  @DELETE
  @Path("hana-connection/{connectionId}")
  public Response dropHanaConnection(@PathParam("connectionId") String connectionId) {
    hanaConnector.disconnect(connectionId);
    return Response.noContent().build();
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
