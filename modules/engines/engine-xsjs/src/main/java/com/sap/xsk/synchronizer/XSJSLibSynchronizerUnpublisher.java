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

import com.sap.xsk.synchronizer.XSJSLibSynchronizerPathTypeResolver.ResolvedPathType;
import com.sap.xsk.synchronizer.cleaners.XSJSLibSynchronizerCleaner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSJSLibSynchronizerUnpublisher {
  private static final Logger logger = LoggerFactory.getLogger(XSJSLibSynchronizerUnpublisher.class);

  private final XSJSLibSynchronizerPathTypeResolver resolver;

  private final XSJSLibSynchronizerCleaner fileCleaner;

  private final XSJSLibSynchronizerCleaner dbCleaner;

  XSJSLibSynchronizerUnpublisher(
      XSJSLibSynchronizerPathTypeResolver resolver,
      XSJSLibSynchronizerCleaner fileCleaner,
      XSJSLibSynchronizerCleaner dbCleaner
  ) {
    this.resolver = resolver;
    this.fileCleaner = fileCleaner;
    this.dbCleaner = dbCleaner;
  }

  public void unpublish(String targetRegistryPath) {
      ResolvedPathType type = resolver.resolveWithCollectionFirst(targetRegistryPath);

      switch (type) {
        case EXISTENT_XSJSLIB_FILE: {
          dbCleaner.cleanup(targetRegistryPath);
          fileCleaner.cleanup(targetRegistryPath);
        }
        break;

        case EXISTENT_FOLDER:
          dbCleaner.cleanup(targetRegistryPath);
          break;

        default:
          logger.info("XSJSLibSynchronizer: Nothing to cleanup.");
          break;
      }
  }
}
