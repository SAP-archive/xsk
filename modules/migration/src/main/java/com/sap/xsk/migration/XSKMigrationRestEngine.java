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
package com.sap.xsk.migration;

import com.google.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.eclipse.dirigible.commons.api.service.AbstractRestService;
import org.eclipse.dirigible.commons.api.service.IRestService;

@Singleton
@Path("/migration")
public class XSKMigrationRestEngine extends AbstractRestService {

  @GET
  @Path("/{path:.*}")
  public Response executeRhinoServiceGet(@PathParam("path") String path) {
    try {
      return Response.ok().build();
    } catch (Throwable e) {
      String message = e.getMessage();
      createErrorResponseInternalServerError(message);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
    }
  }

  @Override
  protected org.slf4j.Logger getLogger() {
    return null;
  }

  @Override
  public Class<? extends IRestService> getType() {
    return XSKMigrationRestEngine.class;
  }
}
