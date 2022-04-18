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

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.api.scripting.ScriptingDependencyException;
import org.eclipse.dirigible.commons.api.service.AbstractRestService;
import org.eclipse.dirigible.commons.api.service.IRestService;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.engine.js.graalium.execution.CodeRunner;
import org.eclipse.dirigible.engine.js.graalium.execution.GraalJSCodeRunner;
import org.eclipse.dirigible.engine.js.graalium.execution.modules.DirigibleModuleProvider;
import org.eclipse.dirigible.engine.js.graalium.execution.polyfills.RequirePolyfill;
import org.eclipse.dirigible.engine.js.graalium.execution.polyfills.internal.DirigibleContextGlobalObject;
import org.eclipse.dirigible.engine.js.graalium.execution.polyfills.internal.DirigibleEngineTypeGlobalObject;
import org.eclipse.dirigible.repository.api.ICollection;
import org.eclipse.dirigible.repository.api.IRepository;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

@Path("/xsk")
public class XSKJavascriptEngineRestService extends AbstractRestService {

  private static final Logger logger = LoggerFactory.getLogger(XSKJavascriptEngineRestService.class);
  private static String XSK_API_CONTENT = null;
  private static final String ENGINE_JAVA_SCRIPT = "js";
  private static final String XSK_API_LOCATION = "/xsk/api.js";

  @GET
  @Path("/{projectName}/{projectFilePath:.*}")
  public Response get(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeXSJS(projectName, projectFilePath);
  }

  @POST
  @Path("/{projectName}/{projectFilePath:.*}")
  public Response post(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeXSJS(projectName, projectFilePath);
  }

  @PUT
  @Path("/{projectName}/{projectFilePath:.*}")
  public Response put(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeXSJS(projectName, projectFilePath);
  }

  @DELETE
  @Path("/{projectName}/{projectFilePath:.*}")
  public Response delete(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeXSJS(projectName, projectFilePath);
  }

  @HEAD
  @Path("/{projectName}/{projectFilePath:.*}")
  public Response head(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeXSJS(projectName, projectFilePath);
  }

  @PATCH
  @Path("/{projectName}/{projectFilePath:.*}")
  public Response patch(
      @PathParam("projectName") String projectName,
      @PathParam("projectFilePath") String projectFilePath
  ) {
    return executeXSJS(projectName, projectFilePath);
  }

  private Response executeXSJS(String projectName, String projectFilePath) {
    try {
      CodeRunner codeRunner = createXSJSCodeRunner(projectName);

      Source xskApiSource = Source.newBuilder("js", DirigibleModuleProvider.loadSource("/xsk/api"), "xsk-api.js")
          .internal(true)
          .build();
      codeRunner.run(xskApiSource);

      codeRunner.run(java.nio.file.Path.of(projectFilePath));
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

  private CodeRunner createXSJSCodeRunner(String projectName) {
    IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
    java.nio.file.Path projectPath = java.nio.file.Path.of(projectName);
    java.nio.file.Path repositoryRootPath = java.nio.file.Path.of(repository.getRepositoryPath());
    java.nio.file.Path projectDirectoryPath = repositoryRootPath.resolve("registry/public").resolve(projectPath);

    return GraalJSCodeRunner
        .newBuilder(projectDirectoryPath, getOrCreateInternalFolder("dependencies"), getOrCreateInternalFolder("core-modules"))
        .addJSPolyfill(new RequirePolyfill())
        .addGlobalObject(new DirigibleEngineTypeGlobalObject())
        .addGlobalObject(new DirigibleContextGlobalObject(new HashMap<>()))
        .waitForDebugger(false)
        .build();
  }

  private static java.nio.file.Path getOrCreateInternalFolder(String folderName) {
    IRepository repository = (IRepository) StaticObjects.get(StaticObjects.REPOSITORY);
    ICollection folder = repository.getCollection(folderName);
    if (!folder.exists()) {
      folder.create();
    }

    String dependenciesCollectionPathString = folder.getPath();
    String dependenciesCollectionInternalPathString = repository.getInternalResourcePath(dependenciesCollectionPathString);
    return java.nio.file.Path.of(dependenciesCollectionInternalPathString);
  }

  private static Source getJSErrorFileNamePolyfillSource() {
    String errorFileNamePolyfillName = "/ErrorFileNamePolyfill.js";
    InputStream errorFileNamePolyfillInputStream = XSKJavascriptEngineExecutor.class
        .getResourceAsStream("/js/polyfills" + errorFileNamePolyfillName);

    try {
      String errorFileNamePolyfillCode = IOUtils.toString(Objects.requireNonNull(errorFileNamePolyfillInputStream), StandardCharsets.UTF_8);
      return Source
          .newBuilder(ENGINE_JAVA_SCRIPT, errorFileNamePolyfillCode, errorFileNamePolyfillName)
          .internal(true)
          .build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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
