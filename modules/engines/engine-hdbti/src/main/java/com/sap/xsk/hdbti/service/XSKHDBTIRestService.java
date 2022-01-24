/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdbti.service;

import com.sap.xsk.hdbti.processors.XSKHDBTIProcessor;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportConfigModel;
import io.swagger.annotations.*;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.eclipse.dirigible.commons.api.service.AbstractRestService;
import org.eclipse.dirigible.commons.api.service.IRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/parse")
@Api(value = "HDBTI Engine - HANA XS Classic", authorizations = {@Authorization(value = "basicAuth", scopes = {})})
@ApiResponses({@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Server Error")})
public class XSKHDBTIRestService extends AbstractRestService implements IRestService {

    private static final Logger logger = LoggerFactory.getLogger(XSKHDBTIRestService.class);

    @Context
    private HttpServletResponse response;

    private final XSKHDBTIProcessor hdbtiProcessor = new XSKHDBTIProcessor();

    @POST
    @Path("/hdbti")
    @ApiOperation("Parse HDBTI file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses({@ApiResponse(code = 200, message = "HDBTI file was parsed")})
    public Response parseHdbtiToJSON(@ApiParam(value = "File registry path", required = true) @QueryParam("location") String location,
                                     @ApiParam(value = "The HDBTI file", required = true)
                                     @Multipart("file") byte[] file) {
        try {
            return Response.ok(hdbtiProcessor.parseHdbtiToJSON(location, file)).build();
        } catch (Throwable e) {
            String message = e.getMessage();
            logger.error(message, e);
            createErrorResponseInternalServerError(message);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
        }
    }

    @POST
    @Path("/csvim")
    @ApiOperation("Parse CSVIM file")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @ApiResponses({@ApiResponse(code = 200, message = "SCVIM file was parsed")})
    public Response parseJSONtoHdbti(ArrayList<XSKHDBTIImportConfigModel> json) {
        try {
            return Response.ok(hdbtiProcessor.parseJSONtoHdbti(json)).build();
        } catch (Throwable e) {
            String message = e.getMessage();
            logger.error(message, e);
            createErrorResponseInternalServerError(message);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
        }
    }


    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.commons.api.service.IRestService#getType()
     */
    @Override
    public Class<? extends IRestService> getType() {
        return XSKHDBTIRestService.class;
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
