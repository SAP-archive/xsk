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
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.IOrderedSynchronizerContribution;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.engine.js.graalvm.processor.GraalVMJavascriptEngineExecutor;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class XSJSLibSynchronizer extends AbstractSynchronizer implements IOrderedSynchronizerContribution {

  private static final Logger logger = LoggerFactory.getLogger(XSJSLibSynchronizer.class);

  private static final String XSJSLIB_RUN_GENERATION_LOCATION = "/exports/XSJSLibRunGeneration.mjs";

  public static final String XSJSLIB_SYNCHRONIZER_STATE_TABLE_NAME = "PROCESSED_XSJSLIB_ARTEFACTS";

  private final String targetRegistryPath;

  public XSJSLibSynchronizer() {
    this(XSKCommonsConstants.XSK_REGISTRY_PUBLIC);
  }

  public XSJSLibSynchronizer(String targetRegistryPath) {
    this.targetRegistryPath = targetRegistryPath;
  }

  public static void forceSynchronization(String targetRegistryPath) {
    XSJSLibSynchronizer synchronizer = new XSJSLibSynchronizer(targetRegistryPath);
    synchronizer.setForcedSynchronization(true);
    try {
      synchronizer.synchronize();
    } finally {
      synchronizer.setForcedSynchronization(false);
    }
  }

  public void synchronizeXSJSLibs() {
    logger.trace("Synchronizing XSJSLibs...");

    Map<Object, Object> context = new HashMap<>();
    context.put("targetRegistryPath", targetRegistryPath);
    context.put("stateTableName", XSJSLIB_SYNCHRONIZER_STATE_TABLE_NAME);

    GraalVMJavascriptEngineExecutor graalVMJavascriptEngineExecutor = new GraalVMJavascriptEngineExecutor();
    graalVMJavascriptEngineExecutor.executeService(
        XSJSLIB_RUN_GENERATION_LOCATION,
        context,
        true,
        false
    );

    logger.trace("Done synchronizing XSJSLibs.");
  }

  @Override
  public int getPriority() {
    return 666;
  }

  @Override
  protected void synchronizeResource(IResource iResource)
      throws SynchronizationException {
    // Not used.
  }

  @Override
  public void synchronize() {
    synchronized (XSJSLibSynchronizer.class) {
      if (beforeSynchronizing()) {
        logger.trace("Synchronizing XSJSLibs...");
        synchronizeXSJSLibs();
        afterSynchronizing();
        logger.trace("Done synchronizing XSJSLibs.");
      }
    }
  }
}
