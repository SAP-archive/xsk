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
package com.sap.xsk.hdb.ds.hdi.synchronizer;

import static java.text.MessageFormat.format;

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

import javax.sql.DataSource;

import com.sap.xsk.hdb.ds.processors.hdi.XSKConfigureLibrariesProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKCreateContainerGroupProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKCreateContainerProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKDeployContainerContentProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKGrantPrivilegesContainerAPIProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKGrantPrivilegesContainerGroupAPIProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKGrantPrivilegesContainerGroupProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKGrantPrivilegesContainerSchemaProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKGrantPrivilegesDefaultRoleProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKGrantPrivilegesExternalArtifactsSchemaProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKWriteContainerContentProcessor;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.IOrderedSynchronizerContribution;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKDataStructuresCoreService;
import com.sap.xsk.hdb.ds.api.IXSKEnvironmentVariables;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;
import com.sap.xsk.hdb.ds.processors.hdi.XSKHDIContainerCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKHDIContainerDropProcessor;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import com.sap.xsk.hdb.ds.service.parser.IXSKCoreParserService;
import com.sap.xsk.hdb.ds.service.parser.XSKCoreParserService;
import com.sap.xsk.utils.XSKCommonsConstants;

/**
 * The XSK Data Structures HDI Synchronizer.
 */
public class XSKDataStructuresHDISynchronizer extends AbstractSynchronizer implements IOrderedSynchronizerContribution {

  private static final Logger logger = LoggerFactory.getLogger(XSKDataStructuresHDISynchronizer.class);

  private static final Map<String, XSKDataStructureHDIModel> HDI_PREDELIVERED = Collections
      .synchronizedMap(new HashMap<>());

  private static final List<String> HDI_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());

  private static final Map<String, XSKDataStructureHDIModel> DATA_STRUCTURE_HDI_MODELS = new LinkedHashMap<String, XSKDataStructureHDIModel>();
  private final String SYNCHRONIZER_NAME = this.getClass().getCanonicalName();
  private IXSKCoreParserService xskCoreParserService = new XSKCoreParserService();
  private IXSKDataStructuresCoreService xskDataStructuresCoreService = new XSKDataStructuresCoreService();

  private XSKHDIContainerCreateProcessor xskhdiContainerCreateProcessor;
  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  public XSKDataStructuresHDISynchronizer() {
    XSKGrantPrivilegesContainerGroupAPIProcessor grantPrivilegesContainerGroupAPIProcessor = new XSKGrantPrivilegesContainerGroupAPIProcessor();
    XSKCreateContainerGroupProcessor createContainerGroupProcessor = new XSKCreateContainerGroupProcessor();
    XSKGrantPrivilegesContainerGroupProcessor grantPrivilegesContainerGroupProcessor = new XSKGrantPrivilegesContainerGroupProcessor();
    XSKCreateContainerProcessor createContainerProcessor = new XSKCreateContainerProcessor();
    XSKGrantPrivilegesContainerAPIProcessor grantPrivilegesContainerAPIProcessor = new XSKGrantPrivilegesContainerAPIProcessor();
    XSKWriteContainerContentProcessor writeContainerContentProcessor = new XSKWriteContainerContentProcessor();
    XSKConfigureLibrariesProcessor configureLibrariesProcessor = new XSKConfigureLibrariesProcessor();
    XSKDeployContainerContentProcessor deployContainerContentProcessor = new XSKDeployContainerContentProcessor();
    XSKGrantPrivilegesContainerSchemaProcessor grantPrivilegesContainerSchemaProcessor = new XSKGrantPrivilegesContainerSchemaProcessor();
    XSKGrantPrivilegesExternalArtifactsSchemaProcessor grantPrivilegesExternalArtifactsSchemaProcessor = new XSKGrantPrivilegesExternalArtifactsSchemaProcessor();
    XSKGrantPrivilegesDefaultRoleProcessor grantPrivilegesDefaultRoleProcessor = new XSKGrantPrivilegesDefaultRoleProcessor();

    this.xskhdiContainerCreateProcessor = new XSKHDIContainerCreateProcessor(grantPrivilegesContainerGroupAPIProcessor,
        createContainerGroupProcessor,
        grantPrivilegesContainerGroupProcessor,
        createContainerProcessor,
        grantPrivilegesContainerAPIProcessor,
        writeContainerContentProcessor,
        configureLibrariesProcessor,
        deployContainerContentProcessor,
        grantPrivilegesContainerSchemaProcessor,
        grantPrivilegesExternalArtifactsSchemaProcessor,
        grantPrivilegesDefaultRoleProcessor
    );
  }



  /**
   * Concatenate list of strings.
   *
   * @param list      the list
   * @param separator the separator
   * @return the string
   */
  private static String concatenateListOfStrings(List<String> list, String separator) {
    StringBuffer buff = new StringBuffer();
    for (String s : list) {
      buff.append(s).append(separator);
    }
    return buff.toString();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.dirigible.core.scheduler.api.ISynchronizer#synchronize()
   */
  @Override
  public void synchronize() {
    synchronized (XSKDataStructuresHDISynchronizer.class) {
      if (beforeSynchronizing()) {
        logger.trace("Synchronizing XSK Data Structures HDI...");
        try {
          if (isSynchronizerSuccessful("org.eclipse.dirigible.database.ds.synchronizer.DataStructuresSynchronizer")
              && isSynchronizerSuccessful("com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer")
              && isSynchronizerSuccessful("com.sap.xsk.hdbti.synchronizer.XSKTableImportSynchronizer")) {
            startSynchronization(SYNCHRONIZER_NAME);
            clearCache();
            synchronizePredelivered();
            synchronizeRegistry();
            updateEntities();

            int immutableHDICount = HDI_PREDELIVERED.size();

            int mutableHDICount = HDI_SYNCHRONIZED.size();

            //                cleanup();
            clearCache();

            successfulSynchronization(SYNCHRONIZER_NAME, format("Immutable: {0}, Mutable: {1}", immutableHDICount, mutableHDICount));
          } else {
            failedSynchronization(SYNCHRONIZER_NAME,
                "Skipped due to dependencies: org.eclipse.dirigible.database.ds.synchronizer.DataStructuresSynchronizer, "
                    + "com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer, "
                    + "com.sap.xsk.hdbti.synchronizer.XSKTableImportSynchronizer");
          }
        } catch (Exception e) {
          logger.error("Synchronizing process for Data Structures HDI failed.", e);
          try {
            failedSynchronization(SYNCHRONIZER_NAME, e.getMessage());
          } catch (SchedulerException e1) {
            logger.error("Synchronizing process for HDB Data Structures HDI files failed in registering the state log.", e);
          }
        }
        logger.trace("Done synchronizing XSK Data Structures HDI.");
        afterSynchronizing();
      }
    }
  }

  /**
   * Register predelivered *.hdi files.
   *
   * @param contentPath the data path
   * @throws Exception in case of an error
   */
  public void registerPredeliveredHDI(String contentPath) throws Exception {
    String data = loadResourceContent(contentPath);
    XSKDataStructureParametersModel parametersModel =
        new XSKDataStructureParametersModel(IXSKDataStructureModel.TYPE_HDI, contentPath, data, XSKCommonsConstants.XSK_REGISTRY_PUBLIC);
    XSKDataStructureHDIModel model = (XSKDataStructureHDIModel) xskCoreParserService
        .parseDataStructure(parametersModel);
    HDI_PREDELIVERED.put(contentPath, model);
  }

  private String loadResourceContent(String modelPath) throws IOException {
    InputStream in = XSKDataStructuresHDISynchronizer.class.getResourceAsStream(modelPath);
    try {
      String content = IOUtils.toString(in, StandardCharsets.UTF_8);
      return content;
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }

  /**
   * Clear cache.
   */
  private void clearCache() {
    DATA_STRUCTURE_HDI_MODELS.clear();
  }

  /**
   * Synchronize predelivered.
   *
   * @throws SynchronizationException the synchronization exception
   */
  private void synchronizePredelivered() throws SynchronizationException {

    logger.trace("Synchronizing predelivered XSK Data Structures HDI...");

    logger.trace("Synchronizing predelivered HDI Containers ...");
    for (XSKDataStructureHDIModel hdi : HDI_PREDELIVERED.values()) {
      try {
        synchronizeHDI(hdi);
      } catch (Exception e) {
        logger.error(format("Update hdi [{0}] skipped due to an error: {1}", hdi, e.getMessage()), e);
      }
    }
    logger.trace("Done synchronizing predelivered HDI Containers.");

    logger.trace("Done synchronizing predelivered XSK Data Structures HDI.");
  }

  /**
   * Synchronize HDI files
   *
   * @param hdi the view model
   * @throws SynchronizationException the synchronization exception
   */
  private void synchronizeHDI(XSKDataStructureHDIModel hdi) throws SynchronizationException {
    try {
      if (!xskDataStructuresCoreService.existsDataStructure(hdi.getLocation(), hdi.getType())) {
        xskDataStructuresCoreService
            .createDataStructure(hdi.getLocation(), hdi.getName(), hdi.getHash(), hdi.getType());
        DATA_STRUCTURE_HDI_MODELS.put(hdi.getName(), hdi);
        logger.info("Synchronized a new HDI file [{}] from location: {}", hdi.getName(), hdi.getLocation());
      } else {
        XSKDataStructureHDIModel existing = xskDataStructuresCoreService.getDataStructure(hdi.getLocation(), hdi.getType());
        if (!hdi.equals(existing)) {
          xskDataStructuresCoreService
              .updateDataStructure(hdi.getLocation(), hdi.getName(), hdi.getHash(), hdi.getType());
          DATA_STRUCTURE_HDI_MODELS.put(hdi.getName(), hdi);
          logger.info("Synchronized a modified HDI file [{}] from location: {}", hdi.getName(), hdi.getLocation());
        }
      }
      if (!HDI_SYNCHRONIZED.contains(hdi.getLocation())) {
        HDI_SYNCHRONIZED.add(hdi.getLocation());
      }
    } catch (XSKDataStructuresException e) {
      throw new SynchronizationException(e);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#
   * synchronizeRegistry()
   */
  @Override
  protected void synchronizeRegistry() throws SynchronizationException {
    logger.trace("Synchronizing XSK Data Structures HDI from Registry...");

    super.synchronizeRegistry();

    logger.trace("Done synchronizing XSK Data Structures HDI from Registry.");
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
    String resourceName = resource.getName();
    String registryPath = getRegistryPath(resource);

    boolean hdiSupported = Boolean.parseBoolean(Configuration.get(IXSKEnvironmentVariables.XSK_HDI_SUPPORTED, "true"));
    if (hdiSupported) {
      if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDI)) {
        String contentAsString = getContent(resource);
        XSKDataStructureHDIModel hdi;
        try {
          XSKDataStructureParametersModel parametersModel =
              new XSKDataStructureParametersModel(IXSKDataStructureModel.TYPE_HDI, registryPath, contentAsString, XSKCommonsConstants.XSK_REGISTRY_PUBLIC);
          hdi = (XSKDataStructureHDIModel) xskCoreParserService
              .parseDataStructure(parametersModel);
        } catch (XSKDataStructuresException e) {
          logger.error("Synchronized hdi artifact is not valid");
          logger.error(e.getMessage());
          return;
        } catch (Exception e) {
          throw new SynchronizationException(e);
        }
        hdi.setLocation(registryPath);
        synchronizeHDI(hdi);
        return;
      }
    }

  }

  private String getContent(IResource resource) throws SynchronizationException {
    byte[] content = resource.getContent();
    String contentAsString;
    try {
      contentAsString = IOUtils.toString(new InputStreamReader(new ByteArrayInputStream(content), StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw new SynchronizationException(e);
    }
    return contentAsString;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#cleanup()
   */
  @Override
  protected void cleanup() throws SynchronizationException {
    logger.trace("Cleaning up XSK Data Structures HDI...");

    try {
      Connection connection = null;
      try {
        connection = dataSource.getConnection();

        // HDI
        List<XSKDataStructureHDIModel> hdiForDrop = new ArrayList<>();
        List<XSKDataStructureHDIModel> hdiModels = xskDataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDI);
        for (XSKDataStructureHDIModel hdiModel : hdiModels) {
          if (!HDI_SYNCHRONIZED.contains(hdiModel.getLocation())) {
            xskDataStructuresCoreService.removeDataStructure(hdiModel.getLocation());
            //DROP Deleted HDI
            hdiForDrop.add(hdiModel);
            logger.warn("Cleaned up HDI Container file [{}] from location: {}", hdiModel.getName(), hdiModel.getLocation());
          }
        }
        XSKHDIContainerDropProcessor.execute(connection, hdiForDrop);

      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    } catch (XSKDataStructuresException | SQLException e) {
      throw new SynchronizationException(e);
    }

    logger.trace("Done cleaning up XSK Data Structures HDI.");
  }

  /**
   * Update entities.
   */
  private void updateEntities() {

    if (DATA_STRUCTURE_HDI_MODELS.isEmpty()) {
      logger.trace("No XSK Data Structures HDI to update.");
      return;
    }

    List<String> errors = new ArrayList<String>();
    try {
      Connection connection = null;
      try {
        connection = dataSource.getConnection();

        List<String> sorted = new ArrayList<String>();
        if (sorted.isEmpty()) {
          sorted.addAll(DATA_STRUCTURE_HDI_MODELS.keySet());
        }

        boolean hdiSupported = Boolean.parseBoolean(Configuration.get(IXSKEnvironmentVariables.XSK_HDI_SUPPORTED, "true"));
        if (hdiSupported) {
          // HDI Containers
          List<XSKDataStructureHDIModel> hdiModels = new ArrayList<XSKDataStructureHDIModel>();
          for (int i = sorted.size() - 1; i >= 0; i--) {
            String dsName = sorted.get(i);
            XSKDataStructureHDIModel model = DATA_STRUCTURE_HDI_MODELS.get(dsName);
            if (model != null) {
              hdiModels.add(model);
            }
          }
          executeHDI(connection, hdiModels);
        }

      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    } catch (SQLException e) {
      logger.error(concatenateListOfStrings(errors, "\n---\n"), e);
    }
  }

  private void executeHDI(Connection connection, List<XSKDataStructureHDIModel> hdiModels)
      throws SQLException {
    hdiModels.forEach(hdiModel -> {
      this.xskhdiContainerCreateProcessor.execute(connection, hdiModel);
    });
  }

	@Override
	public int getPriority() {
		return 270;
	}

}
