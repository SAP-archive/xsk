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
package com.sap.xsk.hdb.ds.hdi.synchronizer;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKEnvironmentVariables;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.commons.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The XSK Data Structures HDI Classpath Content Handler.
 */
public class XSKDataStructuresHDIClasspathContentHandler extends AbstractClasspathContentHandler {

  private static final Logger logger = LoggerFactory.getLogger(XSKDataStructuresHDIClasspathContentHandler.class);

  private XSKDataStructuresHDISynchronizer dataStructuresHDISynchronizer = StaticInjector.getInjector()
      .getInstance(XSKDataStructuresHDISynchronizer.class);

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler#isValid(java.lang.String)
   */
  @Override
  protected boolean isValid(String path) {

    try {

      boolean hdiSupported = Boolean.parseBoolean(Configuration.get(IXSKEnvironmentVariables.XSK_HDI_SUPPORTED, "true"));
      if (hdiSupported) {
        try {
          if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDI)) {
            dataStructuresHDISynchronizer.registerPredeliveredHDI(path);
            return true;
          }
        } catch (XSKDataStructuresException e) {
          logger.error("Predelivered hdi artifact is not valid");
          logger.error(e.getMessage());
        }
      }

    } catch (Exception e) {
      logger.error("Predelivered Artifact is not valid", e);
    }

    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler#getLogger()
   */
  @Override
  protected Logger getLogger() {
    return logger;
  }

}
