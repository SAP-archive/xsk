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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sap.xsk.migration.api.database.HanaDatabaseDeliveryUnitsResponseBody;
import com.sap.xsk.migration.api.database.HanaDatabaseIdsRequestBody;
import com.sap.xsk.migration.api.database.HanaDatabaseIdsResponseBody;
import com.sap.xsk.migration.api.database.connection.HanaConnectionRequestBody;
import com.sap.xsk.migration.api.database.connection.HanaConnectionResponseBody;
import com.sap.xsk.migration.neo.db.DeliveryUnitsNamesProvider;
import com.sap.xsk.migration.neo.db.hana.connectivity.HanaConnector;
import com.sap.xsk.migration.neo.db.hana.connectivity.HanaConnectorArgs;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandFactory;
import com.sap.xsk.migration.neo.sdk.command.SdkCommandGenericArgs;
import com.sap.xsk.migration.neo.sdk.command.databases.ListDatabasesSdkCommand;
import com.sap.xsk.migration.neo.sdk.command.databases.ListDatabasesSdkCommandRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.eclipse.dirigible.commons.api.service.AbstractRestService;
import org.eclipse.dirigible.commons.api.service.IRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

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
  private DeliveryUnitsNamesProvider deliveryUnitsNamesProvider;

  @Inject
  private SdkCommandFactory sdkCommandFactory;

  @GET
  @Path("/{path:.*}")
  @ApiOperation("Execute Server Side JavaScript HANA XS Classic Resource")
  @ApiResponses({@ApiResponse(code = 200, message = "Execution Result")})
  public Response doGet(@PathParam("path") String path) {
    try {
      return Response.ok().build();
    } catch (Throwable e) {
      String message = e.getMessage();
      createErrorResponseInternalServerError(message);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
    }
  }

  @POST
  @Path("hana-connection")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createHanaConnection(HanaConnectionRequestBody hanaConnectionRequestBody) {
    var hanaConnectorArgs = toHanaConnectorArgs(hanaConnectionRequestBody);
    var hanaConnectorRes = hanaConnector.connect(hanaConnectorArgs);
    var responseBody = new HanaConnectionResponseBody(hanaConnectorRes.getSessionId());
    return Response.ok(responseBody).build();
  }

  private HanaConnectorArgs toHanaConnectorArgs(HanaConnectionRequestBody hanaConnectionRequestBody) {
    return new HanaConnectorArgs(
        hanaConnectionRequestBody.getDatabaseId(),
        hanaConnectionRequestBody.getSubaccountTechnicalName(),
        hanaConnectionRequestBody.getHost(),
        hanaConnectionRequestBody.getUser(),
        hanaConnectionRequestBody.getPassword()
    );
  }

  @DELETE
  @Path("hana-connection/{connectionId}")
  public Response dropHanaConnection(@PathParam("connectionId") String connectionId) {
    hanaConnector.disconnect(connectionId);
    return Response.noContent().build();
  }

  @GET
  @Path("list-delivery-units")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDeliveryUnits() {
    List<String> deliveryUnitsNames = deliveryUnitsNamesProvider.getDeliveryUnitsNames();
    var responseBody = new HanaDatabaseDeliveryUnitsResponseBody(deliveryUnitsNames);
    return Response.ok(responseBody).build();
  }

  @POST
  @Path("list-databases")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getDatabaseIds(HanaDatabaseIdsRequestBody hanaDatabaseIdsRequestBody) {
    ListDatabasesSdkCommand listDatabasesSdkCommand = sdkCommandFactory.createListDatabasesSdkCommand();
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

  @POST
  @Path("migrate")
  public Response executeMigration() {
    return Response.ok().build();
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
