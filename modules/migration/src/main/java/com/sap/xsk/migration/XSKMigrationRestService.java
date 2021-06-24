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

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.apache.commons.io.FileUtils;
import org.eclipse.dirigible.commons.api.service.AbstractRestService;
import org.eclipse.dirigible.commons.api.service.IRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Future;
import org.eclipse.dirigible.runtime.transport.processor.TransportProcessor;

@Singleton
@Path("/migration")
public class XSKMigrationRestService extends AbstractRestService {

  @Inject
  private TransportProcessor processor;

  private static final Logger logger = LoggerFactory.getLogger(XSKMigrationRestService.class);

  @POST
  @Path("/")
  public Response doPost(@FormParam("workspace") String workspace, @FormParam("du") String du, @FormParam("vendor") String vendor) {
    try {
      String fileURL = System.getenv("CATALINA_HOME") + "/" + System.getenv("ZIP_OUTPUT_URL");
      File file = new File(fileURL);

      file.createNewFile();

      ProcessBuilder pb = new ProcessBuilder("node", System.getenv("CATALINA_HOME") + "/migration-tools/xs-migration-cloud/xs-migration.js", du + "," + vendor);
      pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
      pb.redirectError(ProcessBuilder.Redirect.INHERIT);
      Process p = pb.start();
      Future<Boolean> identical = p.onExit().thenApply(p1 -> p1.exitValue() == 0);
      if (identical.get()) {
        BufferedReader reader;
        try {
          reader = new BufferedReader(new FileReader(fileURL));
          String line = reader.readLine();
          while (line != null) {
            String fullPath = System.getenv("CATALINA_HOME") + "/" + line;
            File zipFile = new File(fullPath);
            byte[] project = FileUtils.readFileToByteArray(zipFile);
            processor.importProject(workspace, project);
            line = reader.readLine();
          }
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
          return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading results file").build();
        }

        file.delete();
        return Response.ok().build();
      } else {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Script failure").build();
      }

    } catch (Throwable e) {
      String message = e.getMessage();
      createErrorResponseInternalServerError(message);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
    }
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
