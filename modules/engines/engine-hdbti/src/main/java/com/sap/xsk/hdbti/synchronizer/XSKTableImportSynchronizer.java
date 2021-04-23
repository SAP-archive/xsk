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
package com.sap.xsk.hdbti.synchronizer;

import static java.text.MessageFormat.format;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.hdbti.api.IXSKCsvToHdbtiRelationDao;
import com.sap.xsk.hdbti.api.IXSKHDBTICoreService;
import com.sap.xsk.hdbti.api.IXSKHDBTIProcessor;
import com.sap.xsk.hdbti.api.IXSKTableImportArtifactDao;
import com.sap.xsk.hdbti.api.IXSKTableImportModel;
import com.sap.xsk.hdbti.api.IXSKTableImportParser;
import com.sap.xsk.hdbti.api.XSKTableImportException;
import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.hdbti.model.XSKTableImportToCsvRelation;
import com.sap.xsk.parser.hdbti.exception.XSKHDBTISyntaxErrorException;
import com.sap.xsk.utils.XSKUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class XSKTableImportSynchronizer extends AbstractSynchronizer {

  private static final Logger logger = LoggerFactory.getLogger(XSKTableImportSynchronizer.class);

  private static final Map<String, XSKTableImportArtifact> HDBTI_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<>()); //ones which already exist in the JAR

  private static final List<String> HDBTI_SYNCHRONIZED = Collections
      .synchronizedList(new ArrayList<>()); // used for leaving only the correct files after the sync

  private static final Map<String, XSKTableImportArtifact> HDBTI_MODELS = new LinkedHashMap<>(); // used for collecting all created/updated models and later for the actual execution of the query ( import/ alter etc)

  private final String SYNCHRONIZER_NAME = this.getClass().getCanonicalName();

  @javax.inject.Inject
  private DataSource dataSource;
  @Inject
  @Named("xskTableImportParser")
  private IXSKTableImportParser xskTableImportParser;
  @Inject
  @Named("xskHdbtiProcessor")
  private IXSKHDBTIProcessor xskHdbtiProcessor;
  @Inject
  @Named("xskCsvToHdbtiRelationDao")
  private IXSKCsvToHdbtiRelationDao xskCsvToHdbtiRelationDao;
  @Inject
  @Named("xskTableImportArtifactDao")
  private IXSKTableImportArtifactDao xskTableImportArtifactDao;
  @Inject
  @Named("xskHdbtiCoreService")
  private IXSKHDBTICoreService xskHdbtiCoreService;

  @Override
  public void synchronize() {
    synchronized (XSKDataStructuresSynchronizer.class) {
      if (beforeSynchronizing()) {
        logger.trace("Synchronizing HDBTI files ...");
        try {
          if (isSynchronizerSuccessful("org.eclipse.dirigible.database.ds.synchronizer.DataStructuresSynchronizer")
              && isSynchronizerSuccessful("com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer")) {
            startSynchronization(SYNCHRONIZER_NAME);
            clearCache();
            synchronizePredelivered();
            synchronizeRegistry();
            processTableImports();
            int immutableCount = HDBTI_PREDELIVERED.size();
            int mutableCount = HDBTI_SYNCHRONIZED.size();
            cleanup();
            clearCache();
            successfulSynchronization(SYNCHRONIZER_NAME, format("Immutable: {0}, Mutable: {1}", immutableCount, mutableCount));
          } else {
            failedSynchronization(SYNCHRONIZER_NAME,
                "Skipped due to dependencies: org.eclipse.dirigible.database.ds.synchronizer.DataStructuresSynchronizer, "
                    + "com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer");
          }
        } catch (Exception e) {
          logger.error("Error during HDBTI synchronization", e);
          try {
            failedSynchronization(SYNCHRONIZER_NAME, e.getMessage());
          } catch (SchedulerException e1) {
            logger.error("Synchronizing process for HDBTI files failed in registering the state log.", e);
          }
        }
        logger.trace("Done synchronizing HDBTI files.");
        afterSynchronizing();
      }
    }
  }

  /**
   * Force synchronization.
   */
  public static final void forceSynchronization() {
    XSKTableImportSynchronizer synchronizer = StaticInjector.getInjector().getInstance(XSKTableImportSynchronizer.class);
    synchronizer.setForcedSynchronization(true);
    try {
      synchronizer.synchronize();
    } finally {
      synchronizer.setForcedSynchronization(false);
    }
  }

  @Override
  protected void synchronizeResource(IResource resource) throws SynchronizationException {
    String resourceName = resource.getName();
    String registryPath = getRegistryPath(resource);
    String contentAsString = getContentFromResource(resource);

    if (resourceName.endsWith(IXSKTableImportModel.FILE_EXTENSION_TABLE_IMPORT)) {
      try {
        XSKTableImportArtifact xskTableImportArtifact = xskTableImportParser.parseTableImportArtifact(registryPath, contentAsString);
        synchronizeTableImport(xskTableImportArtifact);
      } catch (IOException e) {
        throw new SynchronizationException();
      } catch (XSKHDBTISyntaxErrorException syntaxErrorException) {
        logger.error(syntaxErrorException.getMessage(), syntaxErrorException);
      }
    } else if (resourceName.endsWith(IXSKTableImportModel.FILE_EXTENSION_CSV)) {
      List<XSKTableImportToCsvRelation> affectedHdbtiToCsvRelations = xskCsvToHdbtiRelationDao
          .getAffectedHdbtiToCsvRelations(getRegistryPath(resource));
      if (!affectedHdbtiToCsvRelations.isEmpty()) {
        if (xskCsvToHdbtiRelationDao.hasCsvChanged(affectedHdbtiToCsvRelations.get(0), contentAsString)) {
          affectedHdbtiToCsvRelations.forEach(relation -> reimportAffectedHdbtiFiles(relation.getHdbti()));
        }
      }
    }
  }

  private void reimportAffectedHdbtiFiles(String hdbtiFilePath) {
    IResource hdbtiResource = getRepository().getResource(XSKUtils.convertToFullPath(hdbtiFilePath));
    try (Connection connection = dataSource.getConnection()) {
      XSKTableImportArtifact xskTableImportArtifact = xskTableImportParser
          .parseTableImportArtifact(hdbtiFilePath, getContentFromResource(hdbtiResource));
      executeTableImport(xskTableImportArtifact, connection);
    } catch (IOException | SynchronizationException | SQLException e) {
      logger.error("Error during the force reimport of an HDBTI file due to a linked csv file change", e);
    } catch (XSKHDBTISyntaxErrorException syntaxErrorException) {
      logger.error(syntaxErrorException.getMessage());
    }

  }

  private String getContentFromResource(IResource resource) throws SynchronizationException {
    byte[] content = resource.getContent();
    String contentAsString;

    try {
      contentAsString = IOUtils
          .toString(new InputStreamReader(new ByteArrayInputStream(content), StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw new SynchronizationException(e);
    }
    return contentAsString;
  }

  private void synchronizeTableImport(XSKTableImportArtifact xskTableImportArtifact) throws SynchronizationException {
    try {
      if (!xskTableImportArtifactDao.existsTableImportArtifact(xskTableImportArtifact.getLocation())) {
        xskTableImportArtifactDao.createTableImportArtifact(xskTableImportArtifact);
        HDBTI_MODELS.put(xskTableImportArtifact.getName(), xskTableImportArtifact);
        logger
            .info("Synchronized a new HDBTI file [{}] from location: {}", xskTableImportArtifact.getName(),
                xskTableImportArtifact.getLocation());
      } else {
        XSKDataStructureModel existing = xskTableImportArtifactDao.getTableImportArtifact(xskTableImportArtifact.getLocation());
        if (!xskTableImportArtifact.equals(existing)) {
          xskTableImportArtifactDao.updateTableImportArtifact(xskTableImportArtifact);
          HDBTI_MODELS.put(xskTableImportArtifact.getName(), xskTableImportArtifact);
          logger
              .info("Synchronized a modified HDBTI file [{}] from location: {}",
                  xskTableImportArtifact.getName(), xskTableImportArtifact.getLocation());
        }
      }
      if (!HDBTI_SYNCHRONIZED.contains(xskTableImportArtifact.getLocation())) {
        HDBTI_SYNCHRONIZED.add(xskTableImportArtifact.getLocation());
      }
    } catch (XSKTableImportException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void cleanup() throws SynchronizationException {
    try {
      xskHdbtiCoreService.cleanUpHdbtiRelatedData();
    } catch (XSKTableImportException e) {
      throw new SynchronizationException(e);
    }
  }

  private void processTableImports() {
    try (Connection connection = dataSource.getConnection()) {
      for (String tableImportArtifact : HDBTI_MODELS.keySet()) {
        executeTableImport(HDBTI_MODELS.get(tableImportArtifact), connection);
      }
    } catch (SQLException e) {
      logger.error("Error occurred while importing the data from HDBTI files", e);
    }
  }

  private void executeTableImport(XSKTableImportArtifact tableImportArtifact, Connection connection) {
    List<XSKTableImportConfigurationDefinition> configurationDefinitions = tableImportArtifact.getImportConfigurationDefinition();

    xskHdbtiCoreService.refreshCsvRelations(tableImportArtifact);
    for (XSKTableImportConfigurationDefinition configurationDefinition : configurationDefinitions) {
      try {
        xskHdbtiProcessor.process(configurationDefinition, connection);
      } catch (XSKDataStructuresException | SQLException | XSKTableImportException | IOException e) {
        logger.error(String.format("An error occurred while trying to execute import. %s", e.getMessage()), e);
      }
    }
  }

  private void clearCache() {
    HDBTI_MODELS.clear();
    HDBTI_SYNCHRONIZED.clear();
  }

  private void synchronizePredelivered() {
    HDBTI_PREDELIVERED.values().forEach(tableImport -> {
      try {
        synchronizeTableImport(tableImport);
      } catch (SynchronizationException e) {
        logger.error("Error while synchronizing the predelivered HDBTI artifacts", e);
      }
    });
  }

  public void registerPredeliveredTableImports(String contentPath) throws Exception {
    String data = loadResourceContent(contentPath);
    XSKTableImportArtifact tableImportArtifact = xskTableImportParser.parseTableImportArtifact(contentPath, data);
    HDBTI_PREDELIVERED.put(contentPath, tableImportArtifact);
  }

  private String loadResourceContent(String modelPath) throws IOException {
    try (InputStream in = XSKDataStructuresSynchronizer.class.getResourceAsStream(modelPath)) {
      return IOUtils.toString(in, StandardCharsets.UTF_8);
    }
  }
}
