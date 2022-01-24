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
package com.sap.xsk.hdb.ds.synchronizer;

import org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler;
import org.eclipse.dirigible.commons.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKEnvironmentVariables;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;

/**
 * The XSK Data Structures Classpath Content Handler.
 */
public class XSKDataStructuresClasspathContentHandler extends AbstractClasspathContentHandler {

  private static final Logger logger = LoggerFactory.getLogger(XSKDataStructuresClasspathContentHandler.class);

  private XSKDataStructuresSynchronizer dataStructuresSynchronizer = new XSKDataStructuresSynchronizer();

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.commons.api.content.AbstractClasspathContentHandler#isValid(java.lang.String)
   */
  @Override
  protected boolean isValid(String path) {

    try {

      boolean hdiOnly = Boolean.parseBoolean(Configuration.get(IXSKEnvironmentVariables.XSK_HDI_ONLY, "false"));
      if (!hdiOnly) {
        try {
          if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_ENTITIES)) {
            dataStructuresSynchronizer.registerPredeliveredEntities(path);
            return true;
          }
        } catch (XSKDataStructuresException e) {
          logger.error("Predelivered hdbdd artifact is not valid");
          logger.error(e.getMessage());
        }

        try {
          if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_TABLE)) {
            dataStructuresSynchronizer.registerPredeliveredTable(path);
            return true;
          }
        } catch (XSKDataStructuresException e) {
          logger.error("Predelivered hdbtable artifact is not valid or is in the new format");
          logger.error(e.getMessage());
        }

        try {
          if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_VIEW)) {
            dataStructuresSynchronizer.registerPredeliveredView(path);
            return true;
          }
        } catch (XSKDataStructuresException e) {
          logger.error("Predelivered hdbview artifact is not valid or is in the new format");
          logger.error(e.getMessage());
        }

        //            if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_CALCULATION_VIEW)) {
        //                dataStructuresSynchronizer.registerPredeliveredCalculationView(path);
        //                return true;
        //            }
        //            if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBCALCULATION_VIEW)) {
        //                dataStructuresSynchronizer.registerPredeliveredHDBCalculationView(path);
        //                return true;
        //            }

        try {
          if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBPROCEDURE)) {
            dataStructuresSynchronizer.registerPredeliveredHDBProcedure(path);
            return true;
          }
        } catch (XSKDataStructuresException e) {
          logger.error("Predelivered hdbprocedure artifact is not valid");
          logger.error(e.getMessage());
        }

        try {
          if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBTABLEFUNCTION)) {
            dataStructuresSynchronizer.registerPredeliveredHDBTableFunction(path);
            return true;
          }
        } catch (XSKDataStructuresException e) {
          logger.error("Predelivered hdbtablefunction artifact is not valid");
          logger.error(e.getMessage());
        }

        try {
          if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBSCHEMA)) {
            dataStructuresSynchronizer.registerPredeliveredHDBSchema(path);
            return true;
          }
        } catch (XSKDataStructuresException e) {
          logger.error("Predelivered hdbschema artifact is not valid");
          logger.error(e.getMessage());
        }

        try {
          if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_SYNONYM)) {
            dataStructuresSynchronizer.registerPredeliveredSynonym(path);
            return true;
          }
        } catch (XSKDataStructuresException e) {
          logger.error("Predelivered hdbsynonym artifact is not valid");
          logger.error(e.getMessage());
        }

        try {
          if (path.endsWith(IXSKDataStructureModel.FILE_EXTENSION_STRUCTURE)) {
            dataStructuresSynchronizer.registerPredeliveredHDBStructure(path);
            return true;
          }
        } catch (XSKDataStructuresException e) {
          logger.error("Predelivered hdbstructure artifact is not valid");
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
