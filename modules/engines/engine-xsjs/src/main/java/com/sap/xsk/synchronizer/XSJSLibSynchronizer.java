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

import com.sap.xsk.exceptions.XSJSLibSynchronizerUnresolvedPathTypeException;
import com.sap.xsk.synchronizer.XSJSLibSynchronizerPathTypeResolver.ResolvedPathType;
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

  private static final String XSJSLIB_GENERATION_RUNNER_LOCATION = "/exports/XSJSLibRunner.mjs";

  public static final String XSJSLIB_SYNCHRONIZER_STATE_TABLE_NAME = "PROCESSED_XSJSLIB_ARTEFACTS";

  private static final String DONE_SYNCHRONIZING_LOG_MESSAGE = "Done synchronizing XSJSLibs.";

  private final XSJSLibSynchronizerPathTypeResolver resolver = new XSJSLibSynchronizerPathTypeResolver();

  private final String targetRegistryPath;

  private final ResolvedPathType targetRegistryPathType;

  public XSJSLibSynchronizer() {
    this(XSKCommonsConstants.XSK_REGISTRY_PUBLIC);
  }

  public XSJSLibSynchronizer(String targetRegistryPath) {
    this.targetRegistryPath = targetRegistryPath;
    this.targetRegistryPathType = resolver.resolveWithResourceFirst(targetRegistryPath);
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

    if(resolver.isNonSynchronizableType(targetRegistryPathType)) {
      logger.trace(DONE_SYNCHRONIZING_LOG_MESSAGE);
      return;
    }

    Map<Object, Object> context = buildContext();
    GraalVMJavascriptEngineExecutor graalVMJavascriptEngineExecutor = new GraalVMJavascriptEngineExecutor();
    graalVMJavascriptEngineExecutor.executeService(
        XSJSLIB_GENERATION_RUNNER_LOCATION,
        context,
        true,
        false
    );

    logger.trace(DONE_SYNCHRONIZING_LOG_MESSAGE);
  }

  private Map<Object, Object> buildContext() {
    Map<Object, Object> context = new HashMap<>();

    context.put("targetRegistryPath", targetRegistryPath);
    context.put("stateTableName", XSJSLIB_SYNCHRONIZER_STATE_TABLE_NAME);

    switch(targetRegistryPathType) {
      case EXISTENT_XSJSLIB_FILE: context.put("targetRegistryPathType", "ExistentXSJSLibFile"); break;
      case EXISTENT_FOLDER: context.put("targetRegistryPathType", "ExistentFolder"); break;
      default: throw new XSJSLibSynchronizerUnresolvedPathTypeException("XSJSLibSynchronizer: Unhandled ResolvedPathType.");
    }

    return context;
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
        logger.trace(DONE_SYNCHRONIZING_LOG_MESSAGE);
      }
    }
  }
}
