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

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.SchedulerException;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.hdbti.api.IXSKTableImportModel;
import com.sap.xsk.hdbti.api.XSKTableImportException;
import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.hdbti.model.XSKTableImportToCsvRelation;
import com.sap.xsk.hdbti.processors.XSKTableImportProcessor;
import com.sap.xsk.hdbti.service.XSKCsvToHdbtiRelationService;
import com.sap.xsk.hdbti.service.XSKTableImportCoreService;
import com.sap.xsk.utils.XSKUtils;

@Singleton
public class XSKTableImportSynchronizer extends AbstractSynchronizer {

    @Inject
    private XSKTableImportCoreService xskTableImportCoreService;

    @Inject
    private XSKTableImportProcessor xskTableImportProcessor;

    @Inject
    private XSKCsvToHdbtiRelationService xskCsvToHdbtiRelationService;

    @Inject
    private static final Logger logger = LoggerFactory.getLogger(XSKTableImportSynchronizer.class);

    private static final Map<String, XSKTableImportArtifact> HDBTI_PREDELIVERED = Collections.synchronizedMap(new HashMap<>()); //ones which already exist in the JAR
    
    private static final List<String> HDBTI_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<>()); // used for leaving only the correct files after the sync
    
    private static final Map<String, XSKTableImportArtifact> HDBTI_MODELS = new LinkedHashMap<>(); // used for collecting all created/updated models and later for the actual execution of the query ( import/ alter etc)
    
    @Inject
    private DataSource dataSource;
    
    private final String SYNCHRONIZER_NAME = this.getClass().getCanonicalName();
    
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
						failedSynchronization(SYNCHRONIZER_NAME, "Skipped due to dependencies: org.eclipse.dirigible.database.ds.synchronizer.DataStructuresSynchronizer, "
								+ "com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer");
					}
		        } catch (Exception e) {
		            logger.debug("Error during HDBTI synchronization", e);
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
                XSKTableImportArtifact xskTableImportArtifact = xskTableImportCoreService.parseTableImportArtifact(registryPath, contentAsString);
                synchronizeTableImport(xskTableImportArtifact);
            } catch (IOException e) {
                throw new SynchronizationException();
            }
        } else if (resourceName.endsWith(IXSKTableImportModel.FILE_EXTENSION_CSV)) {
            List<XSKTableImportToCsvRelation> affectedHdbtiToCsvRelations = xskCsvToHdbtiRelationService.getAffectedHdbtiToCsvRelations(getRegistryPath(resource));
            if (!affectedHdbtiToCsvRelations.isEmpty()) {
                if (xskCsvToHdbtiRelationService.hasCsvChanged(affectedHdbtiToCsvRelations.get(0), contentAsString)) {
                    affectedHdbtiToCsvRelations.forEach(relation -> reimportAffectedHdbtiFiles(XSKUtils.convertToFullPath(relation.getHdbti())));
                }
            }
        }
    }

    private void reimportAffectedHdbtiFiles(String hdbtiFilePath) {
        IResource hdbtiResource = getRepository().getResource(hdbtiFilePath);
        try (Connection connection = dataSource.getConnection()) {
            XSKTableImportArtifact xskTableImportArtifact = xskTableImportCoreService.parseTableImportArtifact(hdbtiFilePath, getContentFromResource(hdbtiResource));
            executeTableImport(xskTableImportArtifact, connection);
        } catch (IOException | SynchronizationException | SQLException e) {
            logger.error("Error during the force reimport of an HDBTI file due to a linked csv file change", e);
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
            if (!xskTableImportCoreService.existsTableImportArtifact(xskTableImportArtifact.getLocation())) {
                xskTableImportCoreService.createTableImportArtifact(xskTableImportArtifact);
                HDBTI_MODELS.put(xskTableImportArtifact.getName(), xskTableImportArtifact);
                logger
                        .info("Synchronized a new HDBTI file [{}] from location: {}", xskTableImportArtifact.getName(),
                                xskTableImportArtifact.getLocation());
            } else {
                XSKTableImportArtifact existing = xskTableImportCoreService.getTableImportArtifact(xskTableImportArtifact.getLocation());
                if (!xskTableImportArtifact.equals(existing)) {
                    xskTableImportCoreService.updateTableImportArtifact(xskTableImportArtifact);
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
            throw new SynchronizationException(e);
        }
    }

    @Override
    protected void cleanup() throws SynchronizationException {
        try {
            List<XSKTableImportArtifact> tableImportArtifacts = xskTableImportCoreService.getTableImportArtifacts();

            IRepository repository = getRepository();
            for (XSKTableImportArtifact tableImportArtifact : tableImportArtifacts) {
                if (!repository.hasResource(XSKUtils.convertToFullPath(tableImportArtifact.getLocation()))) {
                    xskTableImportCoreService.removeTableImportArtifact(tableImportArtifact.getLocation());
                    logger
                            .warn("Cleaned up HDBTI file [{}] from location: {}", tableImportArtifact.getName(),
                                    tableImportArtifact.getLocation());
                }
            }
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

        xskCsvToHdbtiRelationService.deleteCsvAndHdbtiRelations(XSKUtils.convertToFullPath(tableImportArtifact.getLocation()), connection);
        xskCsvToHdbtiRelationService.persistNewCsvAndHdbtiRelations(tableImportArtifact, connection);
        for (XSKTableImportConfigurationDefinition configurationDefinition : configurationDefinitions) {
            try {
                xskTableImportProcessor.process(configurationDefinition, connection);
            } catch (IOException | SQLException e) {
                logger.error("Error while executing the HDBTI artifacts", e);
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

    public void registerPredeliveredTableImports(String contentPath) throws IOException {
        String data = loadResourceContent(contentPath);
        XSKTableImportArtifact tableImportArtifact = xskTableImportCoreService.parseTableImportArtifact(contentPath, data);
        HDBTI_PREDELIVERED.put(contentPath, tableImportArtifact);
    }

    private String loadResourceContent(String modelPath) throws IOException {
        try (InputStream in = XSKDataStructuresSynchronizer.class.getResourceAsStream(modelPath)) {
            return IOUtils.toString(in, StandardCharsets.UTF_8);
        }
    }
}
