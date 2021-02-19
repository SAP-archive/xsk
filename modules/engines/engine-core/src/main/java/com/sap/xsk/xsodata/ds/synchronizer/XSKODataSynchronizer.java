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
package com.sap.xsk.xsodata.ds.synchronizer;

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
import org.eclipse.dirigible.engine.odata2.service.ODataCoreService;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.xsodata.ds.api.IXSKODataModel;
import com.sap.xsk.xsodata.ds.api.XSKODataException;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import com.sap.xsk.xsodata.ds.service.XSKOData2ODataMTransformer;
import com.sap.xsk.xsodata.ds.service.XSKOData2ODataXTransformer;
import com.sap.xsk.xsodata.ds.service.XSKODataCoreService;

/**
 * The XSOData Synchronizer.
 */
@Singleton
public class XSKODataSynchronizer extends AbstractSynchronizer {

	private static final Logger logger = LoggerFactory.getLogger(XSKODataSynchronizer.class);

	private static final Map<String, XSKODataModel> ODATA_PREDELIVERED = Collections
			.synchronizedMap(new HashMap<String, XSKODataModel>());

	private static final List<String> ODATA_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
	
	private static final Map<String, XSKODataModel> ODATA_MODELS = new LinkedHashMap<String, XSKODataModel>();

	@Inject
	private XSKODataCoreService xskODataCoreService;
	
	@Inject
	private ODataCoreService odataCoreService;

	@Inject
	private DataSource dataSource;

	@Inject
	private XSKOData2ODataMTransformer xskOData2ODataMTransformer;

	@Inject
	private XSKOData2ODataXTransformer xskOData2ODataXTransformer;
	
	private final String SYNCHRONIZER_NAME = this.getClass().getCanonicalName();
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.core.scheduler.api.ISynchronizer#synchronize()
	 */
	@Override
	public void synchronize() {
		synchronized (XSKODataSynchronizer.class) {
			if (beforeSynchronizing()) {
				logger.trace("Synchronizing XSOData...");
				try {
					if (isSynchronizerSuccessful("org.eclipse.dirigible.database.ds.synchronizer.DataStructuresSynchronizer")
							&& isSynchronizerSuccessful("com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer")
							&& isSynchronizerSuccessful("com.sap.xsk.hdbti.synchronizer.XSKTableImportSynchronizer")) {
						startSynchronization(SYNCHRONIZER_NAME);
						clearCache();
						synchronizePredelivered();
						synchronizeRegistry();
						updateXSOData();
						int immutableCount = ODATA_PREDELIVERED.size();
						int mutableCount = ODATA_SYNCHRONIZED.size();
						cleanup(); // TODO drop tables and views for non-existing models
						clearCache();
						successfulSynchronization(SYNCHRONIZER_NAME, format("Immutable: {0}, Mutable: {1}", immutableCount, mutableCount));
					} else {
						failedSynchronization(SYNCHRONIZER_NAME, "Skipped due to dependencies: org.eclipse.dirigible.database.ds.synchronizer.DataStructuresSynchronizer, "
								+ "com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer, "
								+ "com.sap.xsk.hdbti.synchronizer.XSKTableImportSynchronizer");
					}
				} catch (Exception e) {
					logger.error("Synchronizing process for XSOData failed.", e);
					try {
						failedSynchronization(SYNCHRONIZER_NAME, e.getMessage());
					} catch (SchedulerException e1) {
						logger.error("Synchronizing process for XSOData files failed in registering the state log.", e);
					}
				}
				logger.trace("Done synchronizing XSOData.");
				afterSynchronizing();
			}
		}
	}

	/**
	 * Force synchronization.
	 */
	public static final void forceSynchronization() {
		XSKODataSynchronizer synchronizer = StaticInjector.getInjector().getInstance(XSKODataSynchronizer.class);
		synchronizer.setForcedSynchronization(true);
		try {
			synchronizer.synchronize();
		} finally {
			synchronizer.setForcedSynchronization(false);
		}
	}
	
	/**
	 * Register predelivered odata files.
	 *
	 * @param contentPath
	 *            the data path
	 * @throws Exception 
	 */
	public void registerPredeliveredOData(String contentPath) throws Exception {
		String data = loadResourceContent(contentPath);
		XSKODataModel model = xskODataCoreService.parseOData(contentPath, data);
		ODATA_PREDELIVERED.put(contentPath, model);
	}

	private String loadResourceContent(String modelPath) throws IOException {
		InputStream in = XSKODataSynchronizer.class.getResourceAsStream(modelPath);
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
		ODATA_MODELS.clear();
	}

	/**
	 * Synchronize predelivered.
	 *
	 * @throws SynchronizationException
	 *             the synchronization exception
	 */
	private void synchronizePredelivered() throws SynchronizationException {
		logger.trace("Synchronizing predelivered XSOData...");

		// XSOData
		for (XSKODataModel odata : ODATA_PREDELIVERED.values()) {
			try {
				synchronizeOData(odata);
			} catch (Exception e) {
				logger.error(format("Update xsodata [{0}] skipped due to an error: {1}", odata, e.getMessage()), e);
			}
		}
		logger.trace("Done synchronizing predelivered XSOData.");
	}
	
	/**
	 * Synchronize xsodata.
	 *
	 * @param odataModel
	 *            the odata model
	 * @throws SynchronizationException
	 *             the synchronization exception
	 */
	private void synchronizeOData(XSKODataModel odataModel) throws SynchronizationException {
		try {
			if (!xskODataCoreService.existsOData(odataModel.getLocation())) {
				xskODataCoreService.createOData(odataModel.getLocation(), odataModel.getName(), odataModel.getHash());
				ODATA_MODELS.put(odataModel.getName(), odataModel);
				logger.info("Synchronized a new XSOData file [{}] from location: {}", odataModel.getName(), odataModel.getLocation());
			} else {
				XSKODataModel existing = xskODataCoreService.getOData(odataModel.getLocation());
				if (!odataModel.equals(existing)) {
					xskODataCoreService.updateOData(odataModel.getLocation(), odataModel.getName(), odataModel.getHash());
					ODATA_MODELS.put(odataModel.getName(), odataModel);
					logger.info("Synchronized a modified XSOData file [{}] from location: {}", odataModel.getName(), odataModel.getLocation());
				}
			}
			ODATA_SYNCHRONIZED.add(odataModel.getLocation());
		} catch (XSKODataException e) {
			throw new SynchronizationException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#synchronizeRegistry()
	 */
	@Override
	protected void synchronizeRegistry() throws SynchronizationException {
		logger.trace("Synchronizing XSOData from Registry...");

		super.synchronizeRegistry();

		logger.trace("Done synchronizing XSOData from Registry.");
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#synchronizeResource(com.sap.xsk.hdb.ds.parser.XSKDataStructureParser
	 * repository.api.IResource)
	 */
	@Override
	protected void synchronizeResource(IResource resource) throws SynchronizationException {
		String resourceName = resource.getName();
		String registryPath = getRegistryPath(resource);
		byte[] content = resource.getContent();
		String contentAsString;
		try {
			contentAsString = IOUtils.toString(new InputStreamReader(new ByteArrayInputStream(content), StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new SynchronizationException(e);
		}

		if (resourceName.endsWith(IXSKODataModel.FILE_EXTENSION_XSODATA)) {
			XSKODataModel odataModel;
			try {
				odataModel = xskODataCoreService.parseOData(registryPath, contentAsString);
			} catch (Exception e) {
				throw new SynchronizationException(e);
			}
			odataModel.setLocation(registryPath);
			synchronizeOData(odataModel);
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer#cleanup()
	 */
	@Override
	protected void cleanup() throws SynchronizationException {
		logger.trace("Cleaning up XSOData...");

		try {
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
				
				List<XSKODataModel> odataModels = xskODataCoreService.getODatas();
				for (XSKODataModel odataModel : odataModels) {
					if (!ODATA_SYNCHRONIZED.contains(odataModel.getLocation())) {
						xskODataCoreService.removeOData(odataModel.getLocation());
						logger.warn("Cleaned up XSOData Data file [{}] from location: {}", odataModel.getName(), odataModel.getLocation());
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (XSKODataException | SQLException e) {
			throw new SynchronizationException(e);
		}

		logger.trace("Done cleaning up XSOData.");
	}

	private void updateXSOData() {
		// Update XSOData
		
		if (ODATA_MODELS.isEmpty()) {
			logger.trace("No XSK XSOData to update.");
			return;
		}

		List<String> errors = new ArrayList<String>();
		try {
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
				// topology sort of dependencies
				List<String> sorted = new ArrayList<String>();
				List<String> external = new ArrayList<String>();
				

				if (sorted.isEmpty()) {
					// something wrong happened with the sorting - probably cyclic dependencies
					// we go for the back-up list and try to apply what would succeed
					sorted.addAll(ODATA_MODELS.keySet());
				}
				
				// drop xsodata in a reverse order
				for (int i = sorted.size() - 1; i >= 0; i--) {
					String dsName = sorted.get(i);
					XSKODataModel model = ODATA_MODELS.get(dsName);
					try {
						// CLEAN UP LOGIC
						odataCoreService.removeSchema(model.getLocation());
						odataCoreService.removeContainer(model.getLocation());
						odataCoreService.removeMappings(model.getLocation());
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
				}
				
				// process tables in the proper order
				for (String dsName : sorted) {
					XSKODataModel model = ODATA_MODELS.get(dsName);
					try {
						// METADATA AND MAPPINGS GENERATION LOGIC
						String[] odataxc = generateODataX(model);
						String odatax = odataxc[0];
						String odatac = odataxc[1];
						odataCoreService.createSchema(model.getLocation(), odatax.getBytes());
						odataCoreService.createContainer(model.getLocation(), odatac.getBytes());
						
						String[] odatams = generateODataMs(model);
						int i=1;
						for (String odatam : odatams) {
							odataCoreService.createMapping(model.getLocation() + "#" + i++, odatam.getBytes());
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
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
	
	

	private String[] generateODataX(XSKODataModel model) throws SQLException {
		return xskOData2ODataXTransformer.transform(model);
	}
	
	private String[] generateODataMs(XSKODataModel model) throws SQLException {
		return xskOData2ODataMTransformer.transform(model);
	}

	/**
	 * Concatenate list of strings.
	 *
	 * @param list
	 *            the list
	 * @param separator
	 *            the separator
	 * @return the string
	 */
	private static String concatenateListOfStrings(List<String> list, String separator) {
		StringBuffer buff = new StringBuffer();
		for (String s : list) {
			buff.append(s).append(separator);
		}
		return buff.toString();
	}

}
