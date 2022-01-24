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
package com.sap.xsk.hdbti.synchronizer;

import com.sap.xsk.hdbti.api.IXSKTableImportModel;
import java.io.IOException;
import org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKTableImportClasspathContentHandler extends AbstractClasspathContentHandler {

  private static final Logger logger = LoggerFactory.getLogger(com.sap.xsk.hdbti.synchronizer.XSKTableImportClasspathContentHandler.class);

  private XSKTableImportSynchronizer tableImportSynchronizer = new XSKTableImportSynchronizer();

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler#isValid(java.lang.String)
   */
  @Override
  protected boolean isValid(String path) {
    try {
      if (path.endsWith(IXSKTableImportModel.FILE_EXTENSION_TABLE_IMPORT)) {
        tableImportSynchronizer.registerPredeliveredTableImports(path);
        return true;
      }
    } catch (IOException e) {
      logger.error("Predelvered table import is not valid", e);
    } catch (Exception e) {
      logger.error("Predelvered table import is not valid", e);
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
