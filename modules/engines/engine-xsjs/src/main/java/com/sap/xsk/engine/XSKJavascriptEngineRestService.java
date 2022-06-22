/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.engine;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.eclipse.dirigible.commons.api.context.ContextException;
import org.eclipse.dirigible.commons.api.context.ThreadContextFacade;
import org.eclipse.dirigible.commons.api.scripting.ScriptingDependencyException;
import org.eclipse.dirigible.commons.api.service.AbstractRestService;
import org.eclipse.dirigible.commons.api.service.IRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/xsk")
@Api(value = "JavaScript Engine - HANA XS Classic", authorizations = {@Authorization(value = "basicAuth", scopes = {})})
@ApiResponses({@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
    @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Server Error")})
public class XSKJavascriptEngineRestService extends AbstractRestService {

  private static final Logger logger = LoggerFactory.getLogger(XSKJavascriptEngineRestService.class);

  private XSKJavascriptEngineProcessor processor = new XSKJavascriptEngineProcessor();

  @Context
  private HttpServletResponse response;

  @Context
  private HttpServletRequest httpServletRequest;

  /**
   * Execute service.
   *
   * @param path the path
   * @return result of the execution of the service
   */
  @GET
  @Path("/{path:.*}")
  @ApiOperation("Execute Server Side JavaScript HANA XS Classic Resource")
  @ApiResponses({@ApiResponse(code = 200, message = "Execution Result")})
  public Response executeServiceGet(@PathParam("path") String path) {
    try {
      processor.executeService(path);
      return Response.ok().build();
    } catch (ScriptingDependencyException e) {
      logger.error(e.getMessage(), e);
      return Response.status(Response.Status.ACCEPTED).entity(e.getMessage()).build();
    } catch (Throwable e) {
      String message = e.getMessage();
      logger.error(message, e);
      createErrorResponseInternalServerError(message);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
    }
  }

  /**
   * Execute service post.
   *
   * @param path the path
   * @return result of the execution of the service
   */
  @POST
  @Path("/{path:.*}")
  @ApiOperation("Execute Server Side JavaScript HANA XS Classic Resource")
  @ApiResponses({@ApiResponse(code = 200, message = "Execution Result")})
  public Response executeServicePost(@PathParam("path") String path) {
    ThreadContextFacade.setUp();
    try {
      ThreadContextFacade.set(HttpServletRequest.class.getCanonicalName(), httpServletRequest);
    } catch (ContextException e) {
      throw new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
    }
    return executeServiceGet(path);
  }

  /**
   * Execute service put.
   *
   * @param path the path
   * @return result of the execution of the service
   */
  @PUT
  @Path("/{path:.*}")
  @ApiOperation("Execute Server Side JavaScript HANA XS Classic Resource")
  @ApiResponses({@ApiResponse(code = 200, message = "Execution Result")})
  public Response executeServicePut(@PathParam("path") String path) {
    return executeServiceGet(path);
  }

  /**
   * Execute service delete.
   *
   * @param path the path
   * @return result of the execution of the service
   */
  @DELETE
  @Path("/{path:.*}")
  @ApiOperation("Execute Server Side JavaScript HANA XS Classic Resource")
  @ApiResponses({@ApiResponse(code = 200, message = "Execution Result")})
  public Response executeServiceDelete(@PathParam("path") String path) {
    return executeServiceGet(path);
  }

  /**
   * Execute service head.
   *
   * @param path the path
   * @return result of the execution of the service
   */
  @HEAD
  @Path("/{path:.*}")
  @ApiOperation("Execute Server Side JavaScript HANA XS Classic Resource")
  @ApiResponses({@ApiResponse(code = 200, message = "Execution Result")})
  public Response executeServiceHead(@PathParam("path") String path) {
    return executeServiceGet(path);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.commons.api.service.IRestService#getType()
   */
  @Override
  public Class<? extends IRestService> getType() {
    return XSKJavascriptEngineRestService.class;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.commons.api.service.AbstractRestService#getLogger()
   */
  @Override
  protected Logger getLogger() {
    return logger;
  }
}
