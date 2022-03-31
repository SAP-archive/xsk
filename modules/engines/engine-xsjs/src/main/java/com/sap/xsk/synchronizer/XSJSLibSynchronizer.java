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
package com.sap.xsk.synchronizer;

import com.sap.xsk.utils.XSKCommonsConstants;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.api.context.ThreadContextFacade;
import org.eclipse.dirigible.commons.api.scripting.ScriptingException;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.IOrderedSynchronizerContribution;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.engine.api.script.ScriptEngineExecutorsManager;
import org.eclipse.dirigible.engine.js.graalvm.processor.GraalVMJavascriptEngineExecutor;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class XSJSLibSynchronizer extends AbstractSynchronizer implements IOrderedSynchronizerContribution {

  private static final Logger logger = LoggerFactory.getLogger(XSJSLibSynchronizer.class);
  private static final String EXPORT_GENERATION_SOURCE_LOCATION = "/META-INF/exports/generateXSJSLibExports.js";

  private static final DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);
  private final GraalVMJavascriptEngineExecutor graalVMJavascriptEngineExecutor;
  private final String EXPORT_GENERATION_SOURCE;
  private final String targetLocation;

  public XSJSLibSynchronizer() throws IOException {
    this.graalVMJavascriptEngineExecutor = new GraalVMJavascriptEngineExecutor();
    this.EXPORT_GENERATION_SOURCE = getExportsGenerationSource();
    this.targetLocation = XSKCommonsConstants.XSK_REGISTRY_PUBLIC;
    logger.debug("INITIALIZING SYNC");
  }

  public XSJSLibSynchronizer(String targetLocation) throws IOException {
    this.graalVMJavascriptEngineExecutor = new GraalVMJavascriptEngineExecutor();
    this.EXPORT_GENERATION_SOURCE = getExportsGenerationSource();
    this.targetLocation = targetLocation;
    logger.debug("INITIALIZING SYNC");
  }

  private static String getExportsGenerationSource() throws IOException {
    try (InputStream is = XSJSLibSynchronizer.class.getResourceAsStream(EXPORT_GENERATION_SOURCE_LOCATION)) {
      if (is == null) {
        throw new IOException("Could not load resource: " + EXPORT_GENERATION_SOURCE_LOCATION);
      }
      return IOUtils.toString(is, StandardCharsets.UTF_8);
    }
  }

  public static void forceSynchronization(String targetLocation) throws IOException {
    XSJSLibSynchronizer synchronizer = new XSJSLibSynchronizer(targetLocation);
    synchronizer.setForcedSynchronization(true);
    try {
      synchronizer.synchronize();
    } finally {
      synchronizer.setForcedSynchronization(false);
    }
  }

  public static void cleanup(String targetLocation) throws SchedulerException {
    try {
      PreparedStatement pstmt = dataSource.getConnection()
          .prepareStatement("DELETE FROM \"PROCESSED_XSJSLIB_ARTEFATCTS\" WHERE \"LOCATION\" LIKE '" + targetLocation + "%'");
      pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new SchedulerException("Could not cleanup xsjslib synchronizer entries. ", e);
    }
  }

  @Override
  protected void synchronizeResource(IResource iResource) throws SynchronizationException {
    logger.trace("Synchronizing XSJSLib Resource...");
    // TODO: What?
  }

  @Override
  public int getPriority() {
    return 666;
  }

  @Override
  public void synchronize() {
    synchronized (XSJSLibSynchronizer.class) {
      if(beforeSynchronizing()) {
        logger.trace("Synchronizing XSJSLibs...");

        try {
          ThreadContextFacade.setUp();

          Map<Object, Object> context = new HashMap<>();
          context.put("registry_path", targetLocation);
          Object error = graalVMJavascriptEngineExecutor.executeServiceCode(EXPORT_GENERATION_SOURCE, context);

          if (error != null && error.toString() != null) {
            throw new ScriptingException(error.toString());
          }
        } finally {
          ThreadContextFacade.tearDown();
        }

        logger.trace("Done synchronizing XSJSLibs.");
        afterSynchronizing();
      }
    }
  }
}
