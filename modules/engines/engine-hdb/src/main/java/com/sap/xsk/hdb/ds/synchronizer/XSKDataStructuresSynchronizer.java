/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.synchronizer;

import static java.text.MessageFormat.format;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.facade.IXSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.facade.XSKHDBCoreFacade;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntitiesModel;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.service.parser.IXSKCoreParserService;
import com.sap.xsk.hdb.ds.service.parser.XSKCoreParserService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The XSK Data Structures Synchronizer.
 */
public class XSKDataStructuresSynchronizer extends AbstractSynchronizer {

  private static final Logger logger = LoggerFactory.getLogger(XSKDataStructuresSynchronizer.class);

  private static final Map<String, XSKDataStructureEntitiesModel> ENTITIES_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<>());
  private static final Map<String, XSKDataStructureHDBTableModel> TABLES_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<>());
  private static final Map<String, XSKDataStructureHDBViewModel> VIEWS_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<>());
  private static final Map<String, XSKDataStructureHDBProcedureModel> PROCEDURES_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<>());
  private static final Map<String, XSKDataStructureHDBTableFunctionModel> TABLEFUNCTIONS_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<>());
  private static final Map<String, XSKDataStructureHDBSchemaModel> SCHEMAS_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<>());
  private static final Map<String, XSKDataStructureHDBSynonymModel> SYNONYMS_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<>());
  private static final Map<String, XSKDataStructureHDBTableTypeModel> TABLE_TYPES_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<>());
  private final String SYNCHRONIZER_NAME = this.getClass().getCanonicalName();
  private IXSKCoreParserService xskCoreParserService = new XSKCoreParserService();
  private IXSKHDBCoreFacade xskHDBCoreFacade = new XSKHDBCoreFacade();

  /**
   * Force synchronization.
   */
  public static final void forceSynchronization() {
    XSKDataStructuresSynchronizer dataStructureSynchronizer = new XSKDataStructuresSynchronizer();
    dataStructureSynchronizer.synchronize();
  }

  /**
   * Register predelivered entities files.
   *
   * @param contentPath the data path
   * @throws Exception
   */
  public void registerPredeliveredEntities(String contentPath) throws Exception {
    String data = loadResourceContent(contentPath);
    XSKDataStructureEntitiesModel model = (XSKDataStructureEntitiesModel) xskCoreParserService
        .parseDataStructure(IXSKDataStructureModel.FILE_EXTENSION_ENTITIES, contentPath, data);
    ENTITIES_PREDELIVERED.put(contentPath, model);
  }

  /**
   * Register predelivered table files.
   *
   * @param contentPath the data path
   * @throws Exception
   */
  public void registerPredeliveredTable(String contentPath) throws Exception {
    String data = loadResourceContent(contentPath);
    XSKDataStructureHDBTableModel model = (XSKDataStructureHDBTableModel) xskCoreParserService
        .parseDataStructure(IXSKDataStructureModel.FILE_EXTENSION_TABLE, contentPath, data);
    TABLES_PREDELIVERED.put(contentPath, model);
  }

  /**
   * Register predelivered view files.
   *
   * @param contentPath the data path
   * @throws Exception
   */
  public void registerPredeliveredView(String contentPath) throws Exception {
    String data = loadResourceContent(contentPath);
    XSKDataStructureHDBViewModel model = (XSKDataStructureHDBViewModel) xskCoreParserService
        .parseDataStructure(IXSKDataStructureModel.FILE_EXTENSION_VIEW, contentPath, data);
    VIEWS_PREDELIVERED.put(contentPath, model);
  }

  /**
   * Register predelivered view files.
   *
   * @param contentPath the data path
   * @throws Exception
   */
  public void registerPredeliveredSynonym(String contentPath) throws Exception {
    String data = loadResourceContent(contentPath);
    XSKDataStructureHDBSynonymModel model = (XSKDataStructureHDBSynonymModel) xskCoreParserService
        .parseDataStructure(IXSKDataStructureModel.FILE_EXTENSION_SYNONYM, contentPath, data);
    SYNONYMS_PREDELIVERED.put(contentPath, model);
  }

  /**
   * Register predelivered .hdbprocedure files.
   *
   * @param contentPath the data path
   * @throws IOException                in case of an error
   * @throws XSKDataStructuresException in case of an error
   */
  public void registerPredeliveredHDBProcedure(String contentPath)
      throws IOException, XSKDataStructuresException, XSKArtifactParserException {
    String data = loadResourceContent(contentPath);
    XSKDataStructureHDBProcedureModel model;
    model = (XSKDataStructureHDBProcedureModel) xskCoreParserService
        .parseDataStructure(IXSKDataStructureModel.FILE_EXTENSION_HDBPROCEDURE, contentPath, data);
    PROCEDURES_PREDELIVERED.put(contentPath, model);
  }

  /**
   * Register predelivered .hdbtablefunction files.
   *
   * @param contentPath the data path
   * @throws IOException in case of an error
   */
  public void registerPredeliveredHDBTableFunction(String contentPath)
      throws IOException, XSKDataStructuresException, XSKArtifactParserException {
    String data = loadResourceContent(contentPath);
    XSKDataStructureHDBTableFunctionModel model;
    model = (XSKDataStructureHDBTableFunctionModel) xskCoreParserService
        .parseDataStructure(IXSKDataStructureModel.FILE_EXTENSION_HDBTABLEFUNCTION, contentPath, data);
    TABLEFUNCTIONS_PREDELIVERED.put(contentPath, model);
  }

  /**
   * Register predelivered *.hdbschema files.
   *
   * @param contentPath the data path
   * @throws IOException                in case of an error
   * @throws XSKDataStructuresException in case of an error
   */
  public void registerPredeliveredHDBSchema(String contentPath)
      throws IOException, XSKDataStructuresException, XSKArtifactParserException {
    String data = loadResourceContent(contentPath);
    XSKDataStructureHDBSchemaModel model;
    model = (XSKDataStructureHDBSchemaModel) xskCoreParserService
        .parseDataStructure(IXSKDataStructureModel.FILE_EXTENSION_HDBSCHEMA, contentPath, data);
    SCHEMAS_PREDELIVERED.put(contentPath, model);
  }

  /**
   * Register predelivered *.hdbstructure files.
   *
   * @param contentPath the data path
   * @throws Exception
   */
  public void registerPredeliveredHDBStructure(String contentPath) throws Exception {
    String data = loadResourceContent(contentPath);
    XSKDataStructureHDBTableTypeModel model = (XSKDataStructureHDBTableTypeModel) xskCoreParserService
        .parseDataStructure(IXSKDataStructureModel.FILE_EXTENSION_STRUCTURE, contentPath, data);
    TABLE_TYPES_PREDELIVERED.put(contentPath, model);
  }

  private String loadResourceContent(String modelPath) throws IOException {
    InputStream in = XSKDataStructuresSynchronizer.class.getResourceAsStream(modelPath);
    try {
      String content = IOUtils.toString(in, StandardCharsets.UTF_8);
      return content;
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.dirigible.core.scheduler.api.ISynchronizer#synchronize()
   */
  @Override
  public void synchronize() {
    synchronized (XSKDataStructuresSynchronizer.class) {
      logger.trace("Synchronizing XSK Data Structures...");
      try {
        startSynchronization(SYNCHRONIZER_NAME);
        clearCache();
        synchronizePredelivered();
        synchronizeRegistry();
        xskHDBCoreFacade.updateEntities();

        int immutableEntitiesCount = ENTITIES_PREDELIVERED.size();
        int immutableTablesCount = TABLES_PREDELIVERED.size();
        int immutableViewsCount = VIEWS_PREDELIVERED.size();
        int immutableProceduresCount = PROCEDURES_PREDELIVERED.size();
        int immutableFunctionsCount = TABLEFUNCTIONS_PREDELIVERED.size();
        int immutableSchemasCount = SCHEMAS_PREDELIVERED.size();
        int immutableSynonymsCount = SYNONYMS_PREDELIVERED.size();
        int immutableTableTypesCount = TABLE_TYPES_PREDELIVERED.size();

//				int mutableEntitiesCount = ENTITIES_SYNCHRONIZED.size();
//				int mutableTablesCount = TABLES_SYNCHRONIZED.size();
//				int mutableViewsCount = VIEWS_SYNCHRONIZED.size();
//				int mutableProceduresCount = PROCEDURES_SYNCHRONIZED.size();
//				int mutableFunctionsCount = TABLEFUNCTIONS_SYNCHRONIZED.size();
//				int mutableSchemasCount = SCHEMAS_SYNCHRONIZED.size();
//				int mutableHDICount = HDI_SYNCHRONIZED.size();

//                cleanup();
        clearCache();

        successfulSynchronization(SYNCHRONIZER_NAME,
            format("Immutable: [Entities: {0}, Tables: {1}, Views: {2}, Procedures: {3}, Functions: {4}, Schemas: {5}, HDI: {6}], "
                    + "Mutable: [Entities: {7}, Tables: {8}, Views: {9}, Procedures: {10}, Functions: {11}, Schemas: {12}, HDI: {13}, Syninyms: {13}]",
                immutableEntitiesCount, immutableTablesCount, immutableViewsCount, immutableProceduresCount, immutableFunctionsCount,
                immutableSchemasCount));
//						mutableEntitiesCount, mutableTablesCount, mutableViewsCount, mutableProceduresCount, mutableFunctionsCount, mutableSchemasCount, mutableHDICount, immutableSynonymsCount));
      } catch (Exception e) {
        logger.error("Synchronizing process for Data Structures failed.", e);
        try {
          failedSynchronization(SYNCHRONIZER_NAME, e.getMessage());
        } catch (SchedulerException e1) {
          logger.error("Synchronizing process for HDB Data Structures files failed in registering the state log.", e);
        }
      }
      logger.trace("Done synchronizing XSK Data Structures.");
    }
  }

  /**
   * Clear cache.
   */
  private void clearCache() {
    xskHDBCoreFacade.clearCache();
  }

  /**
   * Synchronize predelivered.
   *
   * @throws SynchronizationException the synchronization exception
   */
  private void synchronizePredelivered() throws SynchronizationException {

    logger.trace("Synchronizing predelivered XSK Data Structures...");

    // HDBSchemas
    logger.trace("Synchronizing predelivered HDB Schemas...");
    for (XSKDataStructureHDBSchemaModel hdbSchema : SCHEMAS_PREDELIVERED.values()) {
      try {
        xskHDBCoreFacade.handleResourceSynchronization(IXSKDataStructureModel.FILE_EXTENSION_HDBSCHEMA, hdbSchema);
      } catch (Exception e) {
        logger.error(format("Update hdbschema [{0}] skipped due to an error: {1}", hdbSchema, e.getMessage()), e);
      }
    }
    logger.trace("Done synchronizing predelivered HDB Schemas.");

    // Entities
    logger.trace("Synchronizing predelivered Entities...");
    for (XSKDataStructureEntitiesModel entity : ENTITIES_PREDELIVERED.values()) {
      try {
        xskHDBCoreFacade.handleResourceSynchronization(IXSKDataStructureModel.FILE_EXTENSION_ENTITIES, entity);
      } catch (Exception e) {
        logger.error(format("Update entities [{0}] skipped due to an error: {1}", entity, e.getMessage()), e);
      }
    }
    logger.trace("Done synchronizing predelivered Entities.");

    // Tables
    logger.trace("Synchronizing predelivered Tables...");
    for (XSKDataStructureHDBTableModel table : TABLES_PREDELIVERED.values()) {
      try {
        xskHDBCoreFacade.handleResourceSynchronization(IXSKDataStructureModel.FILE_EXTENSION_TABLE, table);
      } catch (Exception e) {
        logger.error(format("Update tables [{0}] skipped due to an error: {1}", table, e.getMessage()), e);
      }
    }
    logger.trace("Done synchronizing predelivered Tables.");

    // Views
    logger.trace("Synchronizing predelivered Views...");
    for (XSKDataStructureHDBViewModel view : VIEWS_PREDELIVERED.values()) {
      try {
        xskHDBCoreFacade.handleResourceSynchronization(IXSKDataStructureModel.FILE_EXTENSION_VIEW, view);
      } catch (Exception e) {
        logger.error(format("Update views [{0}] skipped due to an error: {1}", view, e.getMessage()), e);
      }
    }
    logger.trace("Done synchronizing predelivered Views.");

    // HDBProcedures
    logger.trace("Synchronizing predelivered HDB Procedures...");
    for (XSKDataStructureHDBProcedureModel hdbProcedure : PROCEDURES_PREDELIVERED.values()) {
      try {
        xskHDBCoreFacade.handleResourceSynchronization(IXSKDataStructureModel.FILE_EXTENSION_HDBPROCEDURE, hdbProcedure);
      } catch (Exception e) {
        logger.error(format("Update hdbprocedure [{0}] skipped due to an error: {1}", hdbProcedure, e.getMessage()), e);
      }
    }
    logger.trace("Done synchronizing predelivered HDB Procedures.");

    // HDBTableFunctions
    logger.trace("Synchronizing predelivered HDB Table Functions...");
    for (XSKDataStructureHDBTableFunctionModel hdbTableFunction : TABLEFUNCTIONS_PREDELIVERED.values()) {
      try {
        xskHDBCoreFacade.handleResourceSynchronization(IXSKDataStructureModel.FILE_EXTENSION_HDBTABLEFUNCTION, hdbTableFunction);
      } catch (Exception e) {
        logger.error(format("Update hdbtablefunction [{0}] skipped due to an error: {1}", hdbTableFunction, e.getMessage()), e);
      }
    }
    logger.trace("Done synchronizing predelivered HDB Table Function.");

    // Synonyms
    logger.trace("Synchronizing predelivered Synonyms...");
    for (XSKDataStructureHDBSynonymModel synonym : SYNONYMS_PREDELIVERED.values()) {
      try {
        xskHDBCoreFacade.handleResourceSynchronization(IXSKDataStructureModel.FILE_EXTENSION_SYNONYM, synonym);
      } catch (Exception e) {
        logger.error(format("Update synonyms [{0}] skipped due to an error: {1}", synonym, e.getMessage()), e);
      }
    }
    logger.trace("Done synchronizing predelivered Synonyms.");

    // Table Types
    logger.trace("Synchronizing predelivered Table Types...");
    for (XSKDataStructureHDBTableTypeModel tableType : TABLE_TYPES_PREDELIVERED.values()) {
      try {
        xskHDBCoreFacade.handleResourceSynchronization(IXSKDataStructureModel.FILE_EXTENSION_STRUCTURE, tableType);
      } catch (Exception e) {
        logger.error(format("Update tableType [{0}] skipped due to an error: {1}", tableType, e.getMessage()), e);
      }
    }
    logger.trace("Done synchronizing predelivered Table Types.");

    logger.trace("Done synchronizing predelivered XSK Data Structures.");
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#
   * synchronizeRegistry()
   */
  @Override
  protected void synchronizeRegistry() throws SynchronizationException {
    logger.trace("Synchronizing XSK Data Structures from Registry...");

    super.synchronizeRegistry();

    logger.trace("Done synchronizing XSK Data Structures from Registry.");
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#
   * synchronizeResource(com.sap.xsk.hdb.ds.parser.XSKDataStructureParser
   * repository.api.IResource)
   */
  @Override
  protected void synchronizeResource(IResource resource) throws SynchronizationException {
    try {
      xskHDBCoreFacade.handleResourceSynchronization(resource);
    } catch (XSKDataStructuresException e) {
      logger.error(e.getMessage(), e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#cleanup()
   */
  @Override
  protected void cleanup() throws SynchronizationException {
    try {
      this.xskHDBCoreFacade.cleanup();
    } catch (XSKDataStructuresException e) {
      logger.error(e.getMessage(), e);
    }
  }
}
