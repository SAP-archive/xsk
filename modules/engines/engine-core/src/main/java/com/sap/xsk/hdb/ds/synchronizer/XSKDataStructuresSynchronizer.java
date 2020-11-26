/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.synchronizer;

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
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizer;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKEnvironmentVariables;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntitiesModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;
import com.sap.xsk.hdb.ds.processors.XSKEntityCreateProcessor;
import com.sap.xsk.hdb.ds.processors.XSKEntityDropProcessor;
import com.sap.xsk.hdb.ds.processors.XSKEntityForeignKeysProcessor;
import com.sap.xsk.hdb.ds.processors.XSKEntityUpdateProcessor;
import com.sap.xsk.hdb.ds.processors.XSKTableCreateProcessor;
import com.sap.xsk.hdb.ds.processors.XSKTableDropProcessor;
import com.sap.xsk.hdb.ds.processors.XSKViewCreateProcessor;
import com.sap.xsk.hdb.ds.processors.XSKViewDropProcessor;
//import com.sap.xsk.hdb.ds.processors.calculationview.CalculationViewCreateProcessor;
//import com.sap.xsk.hdb.ds.processors.calculationview.CalculationViewDropProcessor;
import com.sap.xsk.hdb.ds.processors.hdbprocedure.HDBProcedureCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbprocedure.HDBProcedureDropProcessor;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaDropProcessor;
import com.sap.xsk.hdb.ds.processors.hdbtablefunction.HDBTableFunctionCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbtablefunction.HDBTableFunctionDropProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKHDIContainerCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdi.XSKHDIContainerDropProcessor;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import com.sap.xsk.utils.XSKUtils;

/**
 * The Data Structures Synchronizer.
 */
@Singleton
public class XSKDataStructuresSynchronizer extends AbstractSynchronizer {

	private static final Logger logger = LoggerFactory.getLogger(XSKDataStructuresSynchronizer.class);

    private static final Map<String, XSKDataStructureEntitiesModel> ENTITIES_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureEntitiesModel>());
    private static final Map<String, XSKDataStructureHDBTableModel> TABLES_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureHDBTableModel>());
    private static final Map<String, XSKDataStructureHDBViewModel> VIEWS_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureHDBViewModel>());
//    private static final Map<String, XSKDataStructureCalculationViewModel> CALCULATIONVIEWS_PREDELIVERED = Collections
//            .synchronizedMap(new HashMap<String, XSKDataStructureCalculationViewModel>());
//    private static final Map<String, XSKDataStructureHDBCalculationViewModel> HDBCALCULATIONVIEWS_PREDELIVERED = Collections
//            .synchronizedMap(new HashMap<String, XSKDataStructureHDBCalculationViewModel>());
    private static final Map<String, XSKDataStructureHDBProcedureModel> HDBPROCEDURES_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureHDBProcedureModel>());
    private static final Map<String, XSKDataStructureHDBTableFunctionModel> HDBTABLEFUNCTIONS_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureHDBTableFunctionModel>());
    private static final Map<String, XSKDataStructureHDBSchemaModel> HDBSCHEMAS_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureHDBSchemaModel>());
    private static final Map<String, XSKDataStructureHDIModel> HDI_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureHDIModel>());

    private static final List<String> ENTITIES_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> TABLES_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> VIEWS_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
//    private static final List<String> CALCULATIONVIEWS_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
//    private static final List<String> HDB_CALCULATIONVIEWS_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> HDB_PROCEDURES_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> HDB_TABLE_FUNCTION_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> HDB_SCHEMAS_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> HDI_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());

    private static final Map<String, XSKDataStructureEntitiesModel> DATA_STRUCTURE_ENTITIES_MODELS = new LinkedHashMap<String, XSKDataStructureEntitiesModel>();
    private static final Map<String, XSKDataStructureHDBTableModel> DATA_STRUCTURE_TABLES_MODELS = new LinkedHashMap<String, XSKDataStructureHDBTableModel>();
    private static final Map<String, XSKDataStructureHDBViewModel> DATA_STRUCTURE_VIEWS_MODELS = new LinkedHashMap<String, XSKDataStructureHDBViewModel>();
//    private static final Map<String, XSKDataStructureCalculationViewModel> DATA_STRUCTURE_CALCULATIONVIEWS_MODELS = new LinkedHashMap<String, XSKDataStructureCalculationViewModel>();
//    private static final Map<String, XSKDataStructureHDBCalculationViewModel> DATA_STRUCTURE_HDB_CALCULATIONVIEWS_MODELS = new LinkedHashMap<String, XSKDataStructureHDBCalculationViewModel>();
    private static final Map<String, XSKDataStructureHDBProcedureModel> DATA_STRUCTURE_HDB_PROCEDURES_MODELS = new LinkedHashMap<String, XSKDataStructureHDBProcedureModel>();
    private static final Map<String, XSKDataStructureHDBTableFunctionModel> DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS = new LinkedHashMap<String, XSKDataStructureHDBTableFunctionModel>();
    private static final Map<String, XSKDataStructureHDBSchemaModel> DATA_STRUCTURE_HDB_SCHEMAS_MODELS = new LinkedHashMap<String, XSKDataStructureHDBSchemaModel>();
    private static final Map<String, XSKDataStructureHDIModel> DATA_STRUCTURE_HDI_MODELS = new LinkedHashMap<String, XSKDataStructureHDIModel>();
    

    @Inject
    private XSKDataStructuresCoreService dataStructuresCoreService;
    @Inject
    private DataSource dataSource;
    /**
     * Force synchronization.
     */
    public static final void forceSynchronization() {
        XSKDataStructuresSynchronizer dataStructureSynchronizer = StaticInjector
                .getInjector()
                .getInstance(XSKDataStructuresSynchronizer.class);
        dataStructureSynchronizer.synchronize();
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

    /**
     * Register predelivered entities files.
     *
     * @param contentPath the data path
     * @throws Exception
     */
    public void registerPredeliveredEntities(String contentPath) throws Exception {
        String data = loadResourceContent(contentPath);
        XSKDataStructureEntitiesModel model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_ENTITIES, contentPath, data);
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
        XSKDataStructureHDBTableModel model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_TABLE, contentPath, data);
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
        XSKDataStructureHDBViewModel model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_VIEW, contentPath, data);
        VIEWS_PREDELIVERED.put(contentPath, model);
    }

//    /**
//     * Register predelivered calculation view files.
//     *
//     * @param contentPath the data path
//     * @throws Exception
//     */
//    public void registerPredeliveredCalculationView(String contentPath) throws Exception {
//        String data = loadResourceContent(contentPath);
//        XSKDataStructureCalculationViewModel model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_CALCVIEW, contentPath, data);
//        CALCULATIONVIEWS_PREDELIVERED.put(contentPath, model);
//    }
//    
//    /**
//     * Register predelivered hdb calculation view files.
//     *
//     * @param contentPath the data path
//     * @throws Exception
//     */
//    public void registerPredeliveredHDBCalculationView(String contentPath) throws Exception {
//        String data = loadResourceContent(contentPath);
//        XSKDataStructureHDBCalculationViewModel model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_CALCVIEW, contentPath, data);
//        HDBCALCULATIONVIEWS_PREDELIVERED.put(contentPath, model);
//    }

    /**
     * Register predelivered .hdbprocedure files.
     *
     * @param contentPath the data path
     * @throws IOException in case of an error
     * @throws XSKDataStructuresException in case of an error
     */
    public void registerPredeliveredHDBProcedure(String contentPath) throws IOException, XSKDataStructuresException {
        String data = loadResourceContent(contentPath);
        XSKDataStructureHDBProcedureModel model;
        model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_PROCEDURE, contentPath, data);
        HDBPROCEDURES_PREDELIVERED.put(contentPath, model);
    }
    
    /**
     * Register predelivered .hdbtablefunction files.
     *
     * @param contentPath the data path
     * @throws IOException in case of an error
     */
    public void registerPredeliveredHDBTableFunction(String contentPath) throws IOException, XSKDataStructuresException {
        String data = loadResourceContent(contentPath);
        XSKDataStructureHDBTableFunctionModel model;
        model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION, contentPath, data);
        HDBTABLEFUNCTIONS_PREDELIVERED.put(contentPath, model);
    }

    /**
     * Register predelivered *.hdbschema files.
     * 
     * @param contentPath the data path
     * @throws IOException in case of an error
     * @throws XSKDataStructuresException in case of an error
     */
    public void registerPredeliveredHDBSchema(String contentPath) throws IOException, XSKDataStructuresException {
        String data = loadResourceContent(contentPath);
        XSKDataStructureHDBSchemaModel model;
        model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_SCHEMA, contentPath, data);
        HDBSCHEMAS_PREDELIVERED.put(contentPath, model);
    }
    
    /**
     * Register predelivered *.hdi files.
     *
     * @param contentPath the data path
     * @throws Exception in case of an error
     */
    public void registerPredeliveredHDI(String contentPath) throws Exception {
        String data = loadResourceContent(contentPath);
        XSKDataStructureHDIModel model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDI, contentPath, data);
        HDI_PREDELIVERED.put(contentPath, model);
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
            logger.trace("Synchronizing Data Structures...");
            try {
                clearCache();
                synchronizePredelivered();
                synchronizeRegistry();
                updateEntities();
//                cleanup();
                clearCache();
            } catch (Exception e) {
                logger.error("Synchronizing process for Data Structures failed.", e);
            }
            logger.trace("Done synchronizing Data Structures.");
        }
    }

    /**
     * Clear cache.
     */
    private void clearCache() {
        DATA_STRUCTURE_ENTITIES_MODELS.clear();
        DATA_STRUCTURE_TABLES_MODELS.clear();
        DATA_STRUCTURE_VIEWS_MODELS.clear();
//        DATA_STRUCTURE_CALCULATIONVIEWS_MODELS.clear();
//        DATA_STRUCTURE_HDB_CALCULATIONVIEWS_MODELS.clear();
        DATA_STRUCTURE_HDB_PROCEDURES_MODELS.clear();
        DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.clear();
        DATA_STRUCTURE_HDB_SCHEMAS_MODELS.clear();
        DATA_STRUCTURE_HDI_MODELS.clear();
    }

    /**
     * Synchronize predelivered.
     *
     * @throws SynchronizationException the synchronization exception
     */
    private void synchronizePredelivered() throws SynchronizationException {

        logger.trace("Synchronizing predelivered Data Structures...");

        // HDBSchemas
        logger.trace("Synchronizing predelivered HDB Schemas...");
        for (XSKDataStructureHDBSchemaModel hdbSchema : HDBSCHEMAS_PREDELIVERED.values()) {
            try {
                synchronizeHDBSchema(hdbSchema);
            } catch (Exception e) {
                logger.error(format("Update hdbschema [{0}] skipped due to an error: {1}", hdbSchema, e.getMessage()), e);
            }
        }
        logger.trace("Done synchronizing predelivered HDB Schemas.");

        // Entities
        logger.trace("Synchronizing predelivered Entities...");
        for (XSKDataStructureEntitiesModel entities : ENTITIES_PREDELIVERED.values()) {
            try {
                synchronizeEntities(entities);
            } catch (Exception e) {
                logger.error(format("Update entities [{0}] skipped due to an error: {1}", entities, e.getMessage()), e);
            }
        }
        logger.trace("Done synchronizing predelivered Entities.");

        // Tables
        logger.trace("Synchronizing predelivered Tables...");
        for (XSKDataStructureHDBTableModel tables : TABLES_PREDELIVERED.values()) {
            try {
                synchronizeTable(tables);
            } catch (Exception e) {
                logger.error(format("Update tables [{0}] skipped due to an error: {1}", tables, e.getMessage()), e);
            }
        }
        logger.trace("Done synchronizing predelivered Tables.");

        // Views
        logger.trace("Synchronizing predelivered Views...");
        for (XSKDataStructureHDBViewModel views : VIEWS_PREDELIVERED.values()) {
            try {
                synchronizeView(views);
            } catch (Exception e) {
                logger.error(format("Update views [{0}] skipped due to an error: {1}", views, e.getMessage()), e);
            }
        }
        logger.trace("Done synchronizing predelivered Views.");

//        // Calculation Views
//        logger.trace("Synchronizing predelivered Calculation Views...");
//        for (XSKDataStructureCalculationViewModel calculationViews : CALCULATIONVIEWS_PREDELIVERED.values()) {
//            try {
//                synchronizeCalculationView(calculationViews);
//            } catch (Exception e) {
//                logger.error(format("Update calculationview [{0}] skipped due to an error: {1}", calculationViews, e.getMessage()), e);
//            }
//        }
//        logger.trace("Done synchronizing predelivered Calculation Views.");
//
//        
//        // HDBCalculationViews
//        logger.trace("Synchronizing predelivered HDB Calculation Views...");
//        for (XSKDataStructureHDBCalculationViewModel hdbCalculationView : HDBCALCULATIONVIEWS_PREDELIVERED.values()) {
//            try {
//                synchronizeHDBCalculationView(hdbCalculationView);
//            } catch (Exception e) {
//                logger.error(format("Update hdbcalculationview [{0}] skipped due to an error: {1}", hdbCalculationView, e.getMessage()), e);
//            }
//        }
//        logger.trace("Done synchronizing predelivered HDB Calculation Views.");
        
        // HDBProcedures
        logger.trace("Synchronizing predelivered HDB Procedures...");
        for (XSKDataStructureHDBProcedureModel hdbProcedure : HDBPROCEDURES_PREDELIVERED.values()) {
            try {
                synchronizeHDBProcedure(hdbProcedure);
            } catch (Exception e) {
                logger.error(format("Update hdbprocedure [{0}] skipped due to an error: {1}", hdbProcedure, e.getMessage()), e);
            }
        }
        logger.trace("Done synchronizing predelivered HDB Procedures.");
        
        // HDBTableFunctions
        logger.trace("Synchronizing predelivered HDB Table Functions...");
        for (XSKDataStructureHDBTableFunctionModel hdbTableFunction : HDBTABLEFUNCTIONS_PREDELIVERED.values()) {
            try {
                synchronizeHDBTableFunction(hdbTableFunction);
            } catch (Exception e) {
                logger.error(format("Update hdbtablefunction [{0}] skipped due to an error: {1}", hdbTableFunction, e.getMessage()), e);
            }
        }
        logger.trace("Done synchronizing predelivered HDB Table Function.");
        
        // HDI
        logger.trace("Synchronizing predelivered HDI Containers ...");
        for (XSKDataStructureHDIModel hdi : HDI_PREDELIVERED.values()) {
            try {
                synchronizeHDI(hdi);
            } catch (Exception e) {
                logger.error(format("Update hdi [{0}] skipped due to an error: {1}", hdi, e.getMessage()), e);
            }
        }
        logger.trace("Done synchronizing predelivered HDI Containers.");

        logger.trace("Done synchronizing predelivered Data Structures.");
    }


    /**
     * Synchronize entities.
     *
     * @param entitiesModel the entities model
     * @throws SynchronizationException the synchronization exception
     */
    private void synchronizeEntities(XSKDataStructureEntitiesModel entitiesModel) throws SynchronizationException {
        try {
            if (!dataStructuresCoreService.existsDataStructure(entitiesModel.getLocation(), entitiesModel.getType())) {
                dataStructuresCoreService
                        .createDataStructure(entitiesModel.getLocation(), entitiesModel.getName(), entitiesModel.getHash(), entitiesModel.getType());
                DATA_STRUCTURE_ENTITIES_MODELS.put(entitiesModel.getName(), entitiesModel);
                logger.info("Synchronized a new Entities file [{}] from location: {}", entitiesModel.getName(), entitiesModel.getLocation());
            } else {
                XSKDataStructureEntitiesModel existing = dataStructuresCoreService.getDataStructure(entitiesModel.getLocation(), entitiesModel.getType());
                if (!entitiesModel.equals(existing)) {
                    dataStructuresCoreService
                            .updateDataStructure(entitiesModel.getLocation(), entitiesModel.getName(), entitiesModel.getHash(), entitiesModel.getType());
                    DATA_STRUCTURE_ENTITIES_MODELS.put(entitiesModel.getName(), entitiesModel);
                    logger.info("Synchronized a modified Entities file [{}] from location: {}", entitiesModel.getName(), entitiesModel.getLocation());
                }
            }
            if (!ENTITIES_SYNCHRONIZED.contains(entitiesModel.getLocation())) {
                ENTITIES_SYNCHRONIZED.add(entitiesModel.getLocation());
            }
        } catch (XSKDataStructuresException e) {
            throw new SynchronizationException(e);
        }
    }

    /**
     * Synchronize tables.
     *
     * @param tableModel the table model
     * @throws SynchronizationException the synchronization exception
     */
    private void synchronizeTable(XSKDataStructureHDBTableModel tableModel) throws SynchronizationException {
        try {
            if (!dataStructuresCoreService.existsDataStructure(tableModel.getLocation(), tableModel.getType())) {
                dataStructuresCoreService.createDataStructure(tableModel.getLocation(), tableModel.getName(), tableModel.getHash(), tableModel.getType());
                DATA_STRUCTURE_TABLES_MODELS.put(tableModel.getName(), tableModel);
                logger.info("Synchronized a new Table file [{}] from location: {}", tableModel.getName(), tableModel.getLocation());
            } else {
                XSKDataStructureHDBTableModel existing = dataStructuresCoreService.getDataStructure(tableModel.getLocation(), tableModel.getType());
                if (!tableModel.equals(existing)) {
                    dataStructuresCoreService.updateDataStructure(tableModel.getLocation(), tableModel.getName(), tableModel.getHash(), tableModel.getType());
                    DATA_STRUCTURE_TABLES_MODELS.put(tableModel.getName(), tableModel);
                    logger.info("Synchronized a modified Table file [{}] from location: {}", tableModel.getName(), tableModel.getLocation());
                }
            }
            if (!TABLES_SYNCHRONIZED.contains(tableModel.getLocation())) {
                TABLES_SYNCHRONIZED.add(tableModel.getLocation());
            }
        } catch (XSKDataStructuresException e) {
            throw new SynchronizationException(e);
        }
    }

    /**
     * Synchronize views.
     *
     * @param viewModel the view model
     * @throws SynchronizationException the synchronization exception
     */
    private void synchronizeView(XSKDataStructureHDBViewModel viewModel) throws SynchronizationException {
        try {
            if (!dataStructuresCoreService.existsDataStructure(viewModel.getLocation(), viewModel.getType())) {
                dataStructuresCoreService.createDataStructure(viewModel.getLocation(), viewModel.getName(), viewModel.getHash(), viewModel.getType());
                DATA_STRUCTURE_VIEWS_MODELS.put(viewModel.getName(), viewModel);
                logger.info("Synchronized a new View file [{}] from location: {}", viewModel.getName(), viewModel.getLocation());
            } else {
                XSKDataStructureHDBViewModel existing = dataStructuresCoreService.getDataStructure(viewModel.getLocation(), viewModel.getType());
                if (!viewModel.equals(existing)) {
                    dataStructuresCoreService.updateDataStructure(viewModel.getLocation(), viewModel.getName(), viewModel.getHash(), viewModel.getType());
                    DATA_STRUCTURE_VIEWS_MODELS.put(viewModel.getName(), viewModel);
                    logger.info("Synchronized a modified View file [{}] from location: {}", viewModel.getName(), viewModel.getLocation());
                }
            }
            if (!VIEWS_SYNCHRONIZED.contains(viewModel.getLocation())) {
                VIEWS_SYNCHRONIZED.add(viewModel.getLocation());
            }
        } catch (XSKDataStructuresException e) {
            throw new SynchronizationException(e);
        }
    }

//    /**
//     * Synchronize calculation views.
//     *
//     * @param calculationViewModel the view model
//     * @throws SynchronizationException the synchronization exception
//     */
//    private void synchronizeCalculationView(XSKDataStructureCalculationViewModel calculationViewModel)
//            throws SynchronizationException {
//        try {
//            if (!dataStructuresCoreService.existsDataStructure(calculationViewModel.getLocation(), calculationViewModel.getType())) {
//                dataStructuresCoreService
//                        .createDataStructure(calculationViewModel.getLocation(), calculationViewModel.getName(),
//                                calculationViewModel.getHash(), calculationViewModel.getType());
//                DATA_STRUCTURE_CALCULATIONVIEWS_MODELS.put(calculationViewModel.getName(), calculationViewModel);
//                logger.info("Synchronized a new Calculation View file [{}] from location: {}", calculationViewModel.getName(), calculationViewModel.getLocation());
//            } else {
//                XSKDataStructureCalculationViewModel existing = dataStructuresCoreService
//                        .getDataStructure(calculationViewModel.getLocation(), calculationViewModel.getType());
//                if (!calculationViewModel.equals(existing)) {
//                    dataStructuresCoreService
//                            .updateDataStructure(calculationViewModel.getLocation(), calculationViewModel.getName(),
//                                    calculationViewModel.getHash(), calculationViewModel.getType());
//                    DATA_STRUCTURE_CALCULATIONVIEWS_MODELS.put(calculationViewModel.getName(), calculationViewModel);
//                    logger.info("Synchronized a modified Calculation View file [{}] from location: {}", calculationViewModel.getName(), calculationViewModel.getLocation());
//                }
//            }
//            if (!CALCULATIONVIEWS_SYNCHRONIZED.contains(calculationViewModel.getLocation())) {
//                CALCULATIONVIEWS_SYNCHRONIZED.add(calculationViewModel.getLocation());
//            }
//        } catch (XSKDataStructuresException e) {
//            throw new SynchronizationException(e);
//        }
//    }
    
//    /**
//     * Synchronize HDBCalculationView files
//     *
//     * @param hdbCalculationView the view model
//     * @throws SynchronizationException the synchronization exception
//     */
//    private void synchronizeHDBCalculationView(XSKDataStructureHDBCalculationViewModel hdbCalculationView) throws SynchronizationException {
//        try {
//            if (!dataStructuresCoreService.existsDataStructure(hdbCalculationView.getLocation(), hdbCalculationView.getType())) {
//                dataStructuresCoreService
//                        .createDataStructure(hdbCalculationView.getLocation(), hdbCalculationView.getName(), hdbCalculationView.getHash(), hdbCalculationView.getType());
//                DATA_STRUCTURE_HDB_CALCULATIONVIEWS_MODELS.put(hdbCalculationView.getName(), hdbCalculationView);
//                logger.info("Synchronized a new HDB Calculation View file [{}] from location: {}", hdbCalculationView.getName(), hdbCalculationView.getLocation());
//            } else {
//                XSKDataStructureHDBCalculationViewModel existing = dataStructuresCoreService.getDataStructure(hdbCalculationView.getLocation(), hdbCalculationView.getType());
//                if (!hdbCalculationView.equals(existing)) {
//                    dataStructuresCoreService
//                            .updateDataStructure(hdbCalculationView.getLocation(), hdbCalculationView.getName(), hdbCalculationView.getHash(), hdbCalculationView.getType());
//                    DATA_STRUCTURE_HDB_CALCULATIONVIEWS_MODELS.put(hdbCalculationView.getName(), hdbCalculationView);
//                    logger.info("Synchronized a modified HDB Calculation View file [{}] from location: {}", hdbCalculationView.getName(), hdbCalculationView.getLocation());
//                }
//            }
//            if (!HDB_CALCULATIONVIEWS_SYNCHRONIZED.contains(hdbCalculationView.getLocation())) {
//            	HDB_CALCULATIONVIEWS_SYNCHRONIZED.add(hdbCalculationView.getLocation());
//            }
//        } catch (XSKDataStructuresException e) {
//            throw new SynchronizationException(e);
//        }
//    }

    /**
     * Synchronize HDBProcedure files
     *
     * @param hdbProcedure the view model
     * @throws SynchronizationException the synchronization exception
     */
    private void synchronizeHDBProcedure(XSKDataStructureHDBProcedureModel hdbProcedure) throws SynchronizationException {
        try {
            // TODO: ommit double calling of finding the hdbProcedure by extracting it in
            // variable
            // String schemaNameConcatProcedureName = hdbProcedure.getSchemaName() + "." +
            // hdbProcedure.getName();
            if (!dataStructuresCoreService.existsDataStructure(hdbProcedure.getLocation(), hdbProcedure.getType())) {
                dataStructuresCoreService
                        .createDataStructure(hdbProcedure.getLocation(), hdbProcedure.getName(), hdbProcedure.getHash(), hdbProcedure.getType());
                DATA_STRUCTURE_HDB_PROCEDURES_MODELS.put(hdbProcedure.getName(), hdbProcedure);
                logger.info("Synchronized a new HDB Procedure file [{}] from location: {}", hdbProcedure.getName(), hdbProcedure.getLocation());
            } else {
            	XSKDataStructureHDBProcedureModel existing = dataStructuresCoreService.getDataStructure(hdbProcedure.getLocation(), hdbProcedure.getType());
                if (!hdbProcedure.equals(existing)) {
                    dataStructuresCoreService
                            .updateDataStructure(hdbProcedure.getLocation(), hdbProcedure.getName(), hdbProcedure.getHash(), hdbProcedure.getType());
                    DATA_STRUCTURE_HDB_PROCEDURES_MODELS.put(hdbProcedure.getName(), hdbProcedure);
                    logger.info("Synchronized a modified HDB Procedure file [{}] from location: {}", hdbProcedure.getName(), hdbProcedure.getLocation());
                }
            }
            if (!HDB_PROCEDURES_SYNCHRONIZED.contains(hdbProcedure.getLocation())) {
                HDB_PROCEDURES_SYNCHRONIZED.add(hdbProcedure.getLocation());
            }
        } catch (XSKDataStructuresException e) {
            throw new SynchronizationException(e);
        }
    }
    
    /**
     * Synchronize HDBTableFunction files
     *
     * @param hdbTableFunction the view model
     * @throws SynchronizationException the synchronization exception
     */
    private void synchronizeHDBTableFunction(XSKDataStructureHDBTableFunctionModel hdbTableFunction) throws SynchronizationException {
        try {
            // TODO: ommit double calling of finding the hdbTableFunction by extracting it in
            // variable
            // String schemaNameConcatTableFunctionName = hdbTableName.getSchemaName() + "." +
            // hdbProcedure.getName();
            if (!dataStructuresCoreService.existsDataStructure(hdbTableFunction.getLocation(), hdbTableFunction.getType())) {
                dataStructuresCoreService
                        .createDataStructure(hdbTableFunction.getLocation(), hdbTableFunction.getName(), hdbTableFunction.getHash(), hdbTableFunction.getType());
                DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.put(hdbTableFunction.getName(), hdbTableFunction);
                logger.info("Synchronized a new HDB Table Function file [{}] from location: {}", hdbTableFunction.getName(), hdbTableFunction.getLocation());
            } else {
                XSKDataStructureHDBTableFunctionModel existing = dataStructuresCoreService.getDataStructure(hdbTableFunction.getLocation(), hdbTableFunction.getType());
                if (!hdbTableFunction.equals(existing)) {
                    dataStructuresCoreService
                            .updateDataStructure(hdbTableFunction.getLocation(), hdbTableFunction.getName(), hdbTableFunction.getHash(), hdbTableFunction.getType());
                    DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.put(hdbTableFunction.getName(), hdbTableFunction);
                    logger.info("Synchronized a modified HDB Table Function file [{}] from location: {}", hdbTableFunction.getName(), hdbTableFunction.getLocation());
                }
            }
            if (!HDB_TABLE_FUNCTION_SYNCHRONIZED.contains(hdbTableFunction.getLocation())) {
            	HDB_TABLE_FUNCTION_SYNCHRONIZED.add(hdbTableFunction.getLocation());
            }
        } catch (XSKDataStructuresException e) {
            throw new SynchronizationException(e);
        }
    }

    /**
     * Synchronize HDBSchema files
     *
     * @param hdbSchema the view model
     * @throws SynchronizationException the synchronization exception
     */
    private void synchronizeHDBSchema(XSKDataStructureHDBSchemaModel hdbSchema) throws SynchronizationException {
        try {
            // TODO: ommit double calling of finding the hdbProcedure by extracting it in
            // variable
            // String schemaNameConcatProcedureName = hdbProcedure.getSchemaName() + "." +
            // hdbProcedure.getName();
            //logger.info("here");
            if (!dataStructuresCoreService.existsDataStructure(hdbSchema.getLocation(), hdbSchema.getType())) {
                dataStructuresCoreService
                        .createDataStructure(hdbSchema.getLocation(), hdbSchema.getName(), hdbSchema.getHash(), hdbSchema.getType());
                DATA_STRUCTURE_HDB_SCHEMAS_MODELS.put(hdbSchema.getName(), hdbSchema);
                logger.info("Synchronized a new HDB Schema file [{}] from location: {}", hdbSchema.getName(), hdbSchema.getLocation());
            } else {
                XSKDataStructureHDBSchemaModel existing = dataStructuresCoreService.getDataStructure(hdbSchema.getLocation(), hdbSchema.getType());
                if (!hdbSchema.equals(existing)) {
                    dataStructuresCoreService
                            .updateDataStructure(hdbSchema.getLocation(), hdbSchema.getName(), hdbSchema.getHash(), hdbSchema.getType());
                    DATA_STRUCTURE_HDB_SCHEMAS_MODELS.put(hdbSchema.getName(), hdbSchema);
                    logger.info("Synchronized a modified HDB Schema file [{}] from location: {}", hdbSchema.getName(), hdbSchema.getLocation());
                }
            }
            if (!HDB_SCHEMAS_SYNCHRONIZED.contains(hdbSchema.getLocation())) {
                HDB_SCHEMAS_SYNCHRONIZED.add(hdbSchema.getLocation());
            }
        } catch (XSKDataStructuresException e) {
            throw new SynchronizationException(e);
        }
    }
    
    /**
     * Synchronize HDI files
     *
     * @param hdi the view model
     * @throws SynchronizationException the synchronization exception
     */
    private void synchronizeHDI(XSKDataStructureHDIModel hdi) throws SynchronizationException {
        try {
            if (!dataStructuresCoreService.existsDataStructure(hdi.getLocation(), hdi.getType())) {
                dataStructuresCoreService
                        .createDataStructure(hdi.getLocation(), hdi.getName(), hdi.getHash(), hdi.getType());
                DATA_STRUCTURE_HDI_MODELS.put(hdi.getName(), hdi);
                logger.info("Synchronized a new HDI file [{}] from location: {}", hdi.getName(), hdi.getLocation());
            } else {
                XSKDataStructureHDIModel existing = dataStructuresCoreService.getDataStructure(hdi.getLocation(), hdi.getType());
                if (!hdi.equals(existing)) {
                    dataStructuresCoreService
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
        logger.trace("Synchronizing Data Structures from Registry...");

        super.synchronizeRegistry();

        logger.trace("Done synchronizing Data Structures from Registry.");
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
	            	hdi = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDI, registryPath, contentAsString);
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
        
        boolean hdiOnly = Boolean.parseBoolean(Configuration.get(IXSKEnvironmentVariables.XSK_HDI_ONLY, "false"));
        if (!hdiOnly) {
        	if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_ENTITIES)) {
	        	String contentAsString = getContent(resource);
	            XSKDataStructureEntitiesModel entitiesModel;
	            try {
	                entitiesModel = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_ENTITIES, registryPath, contentAsString);
	            } catch (XSKDataStructuresException e) {
					logger.error("Synchronized hdbdd artifact is not valid");
		        	logger.error(e.getMessage());
		        	return;
	            } catch (Exception e) {
	                throw new SynchronizationException(e);
	            }
	            entitiesModel.setLocation(registryPath);
	            synchronizeEntities(entitiesModel);
	            return;
	        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_TABLE)) {
	        	String contentAsString = getContent(resource);
	            XSKDataStructureHDBTableModel tableModel;
	            try {
	                tableModel = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_TABLE, registryPath, contentAsString);
	            } catch (XSKDataStructuresException e) {
					logger.error("Synchronized hdbtable artifact is not valid or is in the new format");
		        	logger.error(e.getMessage());
		        	return;
	            } catch (Exception e) {
	                throw new SynchronizationException(e);
	            }
	            tableModel.setLocation(registryPath);
	            synchronizeTable(tableModel);
	            return;
	        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_VIEW)) {
	        	String contentAsString = getContent(resource);
	            XSKDataStructureHDBViewModel viewModel;
	            try {
	                viewModel = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_VIEW, registryPath, contentAsString);
	            } catch (XSKDataStructuresException e) {
					logger.error("Synchronized hdbview artifact is not valid or is in the new format");
		        	logger.error(e.getMessage());
		        	return;
	            } catch (Exception e) {
	                throw new SynchronizationException(e);
	            }
	            viewModel.setLocation(registryPath);
	            synchronizeView(viewModel);
	            return;
	//        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_CALCULATION_VIEW)) {
	//            String contentAsString = getContent(resource);
	//            XSKDataStructureCalculationViewModel calculationViewModel;
	//            try {
	//                calculationViewModel = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_CALCVIEW, registryPath, contentAsString);
	//            } catch (Exception e) {
	//                throw new SynchronizationException(e);
	//            }
	//            calculationViewModel.setLocation(registryPath);
	//            synchronizeCalculationView(calculationViewModel);
	//            return;
	//        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBCALCULATION_VIEW)) {
	//            String contentAsString = getContent(resource);
	//        	XSKDataStructureHDBCalculationViewModel hdbCalculationView;
	//            try {
	//            	hdbCalculationView = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_CALCVIEW, registryPath, contentAsString);
	//            } catch (Exception e) {
	//                throw new SynchronizationException(e);
	//            }
	//            hdbCalculationView.setLocation(registryPath);
	//            synchronizeHDBCalculationView(hdbCalculationView);
	//            return;
	        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBPROCEDURE)) {
	        	String contentAsString = getContent(resource);
	        	XSKDataStructureHDBProcedureModel hdbProcedure;
	            try {
	                hdbProcedure = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_PROCEDURE, registryPath, contentAsString);
	            } catch (XSKDataStructuresException e) {
					logger.error("Synchronized hdbprocedure artifact is not valid");
		        	logger.error(e.getMessage());
		        	return;
	            } catch (Exception e) {
	                throw new SynchronizationException(e);
	            }
	            hdbProcedure.setLocation(registryPath);
	            synchronizeHDBProcedure(hdbProcedure);
	            return;
	        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBTABLEFUNCTION)) {
	        	String contentAsString = getContent(resource);
	            XSKDataStructureHDBTableFunctionModel hdbTableFunction;
	            try {
	            	hdbTableFunction = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION, registryPath, contentAsString);
	            } catch (XSKDataStructuresException e) {
					logger.error("Synchronized hdbtablefunction artifact is not valid");
		        	logger.error(e.getMessage());
		        	return;
	            } catch (Exception e) {
	                throw new SynchronizationException(e);
	            }
	            hdbTableFunction.setLocation(registryPath);
	            synchronizeHDBTableFunction(hdbTableFunction);
	            return;
	        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBSCHEMA)) {
	        	String contentAsString = getContent(resource);
	            XSKDataStructureHDBSchemaModel hdbSchema;
	            try {
	                hdbSchema = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_SCHEMA, registryPath, contentAsString);
	            } catch (XSKDataStructuresException e) {
					logger.error("Synchronized hdbschema artifact is not valid");
		        	logger.error(e.getMessage());
		        	return;
	            } catch (Exception e) {
	                throw new SynchronizationException(e);
	            }
	            hdbSchema.setLocation(registryPath);
	            synchronizeHDBSchema(hdbSchema);
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
        logger.trace("Cleaning up Data Structures...");

        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();

                // Entities
                List<XSKDataStructureEntitiesModel> entitiesModels = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDB_ENTITIES);
                for (XSKDataStructureEntitiesModel entitiesModel : entitiesModels) {
                    if (!ENTITIES_SYNCHRONIZED.contains(entitiesModel.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(entitiesModel.getLocation());
                        logger.warn("Cleaned up Entities Data file [{}] from location: {}", entitiesModel.getName(), entitiesModel.getLocation());
                    }
                }

                // Tables
                List<XSKDataStructureHDBTableModel> tableModels = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDB_TABLE);
                for (XSKDataStructureHDBTableModel tableModel : tableModels) {
                    if (!TABLES_SYNCHRONIZED.contains(tableModel.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(tableModel.getLocation());
                        logger.warn("Cleaned up Table Data file [{}] from location: {}", tableModel.getName(), tableModel.getLocation());
                    }
                }

                // Views
                List<XSKDataStructureHDBViewModel> viewModels = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDB_VIEW);
                for (XSKDataStructureHDBViewModel viewModel : viewModels) {
                    if (!VIEWS_SYNCHRONIZED.contains(viewModel.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(viewModel.getLocation());
                        logger.warn("Cleaned up View Data file [{}] from location: {}", viewModel.getName(), viewModel.getLocation());
                    }
                }

//                // Calculation Views
//                List<XSKDataStructureCalculationViewModel> calculationViewModels = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_CALCVIEW);
//                for (XSKDataStructureCalculationViewModel calculationViewModel : calculationViewModels) {
//                    if (!CALCULATIONVIEWS_SYNCHRONIZED.contains(calculationViewModel.getLocation())) {
//                        dataStructuresCoreService.removeDataStructure(calculationViewModel.getLocation());
//                        logger
//                                .warn("Cleaned up Calculation View Data file [{}] from location: {}", calculationViewModel.getName(),
//                                        calculationViewModel.getLocation());
//                    }
//                }
//                
//                // HDB Calculation Views
//                List<XSKDataStructureHDBCalculationViewModel> hdbCalculationViewsForDrop = new ArrayList<>();
//                List<XSKDataStructureHDBCalculationViewModel> hdbCalculationViews = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDB_CALCVIEW);
//                for (XSKDataStructureHDBCalculationViewModel hdbCalculationView : hdbCalculationViews) {
//                    if (!HDB_CALCULATIONVIEWS_SYNCHRONIZED.contains(hdbCalculationView.getLocation())) {
//                        dataStructuresCoreService.removeDataStructure(hdbCalculationView.getLocation());
//                        //DROP Deleted Calculation View
//                        hdbCalculationViewsForDrop.add(hdbCalculationView);
//                        logger.warn("Cleaned up HDB Calculation View Data file [{}] from location: {}", hdbCalculationView.getName(), hdbCalculationView.getLocation());
//                    }
//                }
//                HDBCalculationViewDropProcessor.execute(connection, hdbCalculationViewsForDrop);

                // HDB Procedures
                List<XSKDataStructureHDBProcedureModel> hdbProcedures = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDB_PROCEDURE);
                for (XSKDataStructureHDBProcedureModel hdbProcedure : hdbProcedures) {
                    if (!HDB_PROCEDURES_SYNCHRONIZED.contains(hdbProcedure.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(hdbProcedure.getLocation());
                        //DROP Deleted procedure
                        HDBProcedureDropProcessor.executeSingle(connection, hdbProcedure);
                        logger.warn("Cleaned up HDB Procedure Data file [{}] from location: {}", hdbProcedure.getName(), hdbProcedure.getLocation());
                    }
                }
                
                // HDB Table Functions
                List<XSKDataStructureHDBTableFunctionModel> hdbTableFunctions = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION);
                for (XSKDataStructureHDBTableFunctionModel hdbTableFunction : hdbTableFunctions) {
                    if (!HDB_TABLE_FUNCTION_SYNCHRONIZED.contains(hdbTableFunction.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(hdbTableFunction.getLocation());
                        //DROP Deleted procedure
                        HDBTableFunctionDropProcessor.executeSingle(connection, hdbTableFunction);
                        logger.warn("Cleaned up HDB Table Function Data file [{}] from location: {}", hdbTableFunction.getName(), hdbTableFunction.getLocation());
                    }
                }

                // HDB Schemas
                List<XSKDataStructureHDBSchemaModel> hdbSchemas = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDB_SCHEMA);
                for (XSKDataStructureHDBSchemaModel hdbSchema : hdbSchemas) {
                    if (!HDB_SCHEMAS_SYNCHRONIZED.contains(hdbSchema.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(hdbSchema.getLocation());
                        //DROP Deleted schema
                        //HDBSchemaDropProcessor.executeSingle(connection, hdbSchema);
                        logger.warn("Cleaned up HDB Schema Data file [{}] from location: {}", hdbSchema.getName(), hdbSchema.getLocation());
                    }
                }
                
                // HDI
                List<XSKDataStructureHDIModel> hdiForDrop = new ArrayList<>();
                List<XSKDataStructureHDIModel> hdiModels = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDI);
                for (XSKDataStructureHDIModel hdiModel : hdiModels) {
                    if (!HDI_SYNCHRONIZED.contains(hdiModel.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(hdiModel.getLocation());
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

        logger.trace("Done cleaning up Data Structures.");
    }
    
    boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));

    /**
     * Update entities.
     */
    private void updateEntities() {

        if (DATA_STRUCTURE_ENTITIES_MODELS.isEmpty()
                && DATA_STRUCTURE_TABLES_MODELS.isEmpty()
                && DATA_STRUCTURE_VIEWS_MODELS.isEmpty()
//                && DATA_STRUCTURE_CALCULATIONVIEWS_MODELS.isEmpty()
//                && DATA_STRUCTURE_HDB_CALCULATIONVIEWS_MODELS.isEmpty()
                && DATA_STRUCTURE_HDB_PROCEDURES_MODELS.isEmpty()
                && DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.isEmpty()
                && DATA_STRUCTURE_HDB_SCHEMAS_MODELS.isEmpty()
                && DATA_STRUCTURE_HDI_MODELS.isEmpty()) {
            logger.trace("No XSK Data Structures to update.");
            return;
        }

        List<String> errors = new ArrayList<String>();
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                
                List<String> sorted = new ArrayList<String>();
                if (sorted.isEmpty()) {
                    // something wrong happened with the sorting - probably cyclic dependencies
                    // we go for the back-up list and try to apply what would succeed
                    // logger.warn("Probably there are cyclic dependencies!");
                    sorted.addAll(DATA_STRUCTURE_ENTITIES_MODELS.keySet());
                    sorted.addAll(DATA_STRUCTURE_TABLES_MODELS.keySet());
                    sorted.addAll(DATA_STRUCTURE_VIEWS_MODELS.keySet());
//                    sorted.addAll(DATA_STRUCTURE_CALCULATIONVIEWS_MODELS.keySet());
//                    sorted.addAll(DATA_STRUCTURE_HDB_CALCULATIONVIEWS_MODELS.keySet());
                    sorted.addAll(DATA_STRUCTURE_HDB_PROCEDURES_MODELS.keySet());
                    sorted.addAll(DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.keySet());
                    sorted.addAll(DATA_STRUCTURE_HDB_SCHEMAS_MODELS.keySet());
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
                
                boolean hdiOnly = Boolean.parseBoolean(Configuration.get(IXSKEnvironmentVariables.XSK_HDI_ONLY, "false"));
                if (!hdiOnly) {
                		
	//                // drop calculation views
	//                List<XSKDataStructureCalculationViewModel> calculationViews = new ArrayList<XSKDataStructureCalculationViewModel>();
	//                for (int i = sorted.size() - 1; i >= 0; i--) {
	//                    String dsName = sorted.get(i);
	//                    XSKDataStructureCalculationViewModel model = DATA_STRUCTURE_CALCULATIONVIEWS_MODELS.get(dsName);
	//                    if (model != null) {
	//                    	calculationViews.add(model);
	//                    }
	//                }
	////                executeCalculationViewsDrop(connection, calculationViews);
	//                
	//                // drop HDB Calculation Views
	//                List<XSKDataStructureHDBCalculationViewModel> hdbCalculationsViewToUpdate = new ArrayList<XSKDataStructureHDBCalculationViewModel>();
	//                for (int i = sorted.size() - 1; i >= 0; i--) {
	//                    String dsName = sorted.get(i);
	//                    XSKDataStructureHDBCalculationViewModel model = DATA_STRUCTURE_HDB_CALCULATIONVIEWS_MODELS.get(dsName);
	//                    if (model != null) {
	//                    	hdbCalculationsViewToUpdate.add(model);
	//                    }
	//                }
	//                executeHDBCalculationViewsDrop(connection, hdbCalculationsViewToUpdate);
	
	                // drop HDB Procedures
	                List<XSKDataStructureHDBProcedureModel> hdbProceduresToUpdate = new ArrayList<XSKDataStructureHDBProcedureModel>();
	                for (int i = sorted.size() - 1; i >= 0; i--) {
	                    String dsName = sorted.get(i);
	                    XSKDataStructureHDBProcedureModel model = DATA_STRUCTURE_HDB_PROCEDURES_MODELS.get(dsName);
	                    if (model != null) {
	                    	hdbProceduresToUpdate.add(model);
	                    }
	                }
	                executeHDBProceduresDrop(connection, hdbProceduresToUpdate);
	                
	                // drop HDB Table Functions
	                List<XSKDataStructureHDBTableFunctionModel> hdbTableFunctionsToUpdate = new ArrayList<XSKDataStructureHDBTableFunctionModel>();
	                for (int i = sorted.size() - 1; i >= 0; i--) {
	                    String dsName = sorted.get(i);
	                    XSKDataStructureHDBTableFunctionModel model = DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.get(dsName);
	                    if (model != null) {
	                    	hdbTableFunctionsToUpdate.add(model);
	                    }
	                }
	                executeHDBTableFunctionsDrop(connection, hdbTableFunctionsToUpdate);
	
	                // drop views in a reverse order
	                for (int i = sorted.size() - 1; i >= 0; i--) {
	                    String dsName = sorted.get(i);
	                    XSKDataStructureHDBViewModel model = DATA_STRUCTURE_VIEWS_MODELS.get(dsName);
	                    try {
	                        if (model != null) {
	                            executeViewDrop(connection, model);
	                        }
	                    } catch (Exception e) {
	                        logger.error(e.getMessage(), e);
	                        errors.add(e.getMessage());
	                    }
	                }
	
	                // drop tables in a reverse order
	                for (int i = sorted.size() - 1; i >= 0; i--) {
	                    String dsName = sorted.get(i);
	                    XSKDataStructureHDBTableModel model = DATA_STRUCTURE_TABLES_MODELS.get(dsName);
	                    try {
	                        if (model != null) {
	                            if (SqlFactory.getNative(connection).exists(connection, model.getName())) {
	                                if (SqlFactory.getNative(connection).count(connection, model.getName()) == 0) {
	                                    executeTableDrop(connection, model);
	                                } else {
	                                    logger.warn(format("Table [{0}] cannot be deleted during the update process, because it is not empty", dsName));
	                                }
	                            }
	                        }
	                    } catch (Exception e) {
	                        logger.error(e.getMessage(), e);
	                        errors.add(e.getMessage());
	                    }
	                }
	
	                // drop entities in a reverse order
	                for (int i = sorted.size() - 1; i >= 0; i--) {
	                    String dsName = sorted.get(i);
	                    XSKDataStructureEntitiesModel entitiesModel = DATA_STRUCTURE_ENTITIES_MODELS.get(dsName);
	                    try {
	                        if (entitiesModel != null) {
	                        	for (XSKDataStructureEntityModel entityModel : entitiesModel.getContext().getЕntities()) {
	                        		String tableName = XSKUtils.getTableName(entityModel);
	                        		if (caseSensitive) {
	                        			tableName = "\"" + tableName + "\"";
	                        		}
	                        		if (SqlFactory.getNative(connection).exists(connection, tableName)) {
	                        			if (SqlFactory.getNative(connection).count(connection, tableName) == 0) {
	                        				executeEntityDrop(connection, entityModel);
	                        			} else {
	                        				logger.warn(format("Entity [{0}] cannot be deleted during the update process, because it is not empty", dsName));
	                        			}
	                        		}
	                        	}
	                        }
	                    } catch (Exception e) {
	                        logger.error(e.getMessage(), e);
	                        errors.add(e.getMessage());
	                    }
	                }
	
	                // drop HDB Schemas
	                List<XSKDataStructureHDBSchemaModel> hdbSchemasToUpdate = new ArrayList<XSKDataStructureHDBSchemaModel>();
	                for (int i = sorted.size() - 1; i >= 0; i--) {
	                    String dsName = sorted.get(i);
	                    XSKDataStructureHDBSchemaModel model = DATA_STRUCTURE_HDB_SCHEMAS_MODELS.get(dsName);
	                    if (model != null) {
	                    	hdbSchemasToUpdate.add(model);
	                    }
	                }
	//                executeHDBSchemasDrop(connection, hdbSchemasToUpdate);
	
	                // process hdbSchemas
	                executeHDBSchemasCreate(connection, hdbSchemasToUpdate);
	
	                // process tables in the proper order
	                for (String dsName : sorted) {
	                	XSKDataStructureHDBTableModel model = DATA_STRUCTURE_TABLES_MODELS.get(dsName);
	                    try {
	                        if (model != null) {
	                            if (!SqlFactory.getNative(connection).exists(connection, model.getName())) {
	                                executeTableCreate(connection, model);
	                            } else {
	                                logger.warn(format("Table [{0}] already exists during the update process", dsName));
	                                if (SqlFactory.getNative(connection).count(connection, model.getName()) != 0) {
	                                    executeTableAlter(connection, model);
	                                }
	                            }
	                        }
	                    } catch (Exception e) {
	                        logger.error(e.getMessage(), e);
	                        errors.add(e.getMessage());
	                    }
	                }
	
	                // process entities in the proper order
	                for (String dsName : sorted) {
	                	XSKDataStructureEntitiesModel entitesModel = DATA_STRUCTURE_ENTITIES_MODELS.get(dsName);
	                    try {
	                        if (entitesModel != null) {
	                        	for (XSKDataStructureEntityModel entityModel : entitesModel.getContext().getЕntities()) {
	                        		String tableName = XSKUtils.getTableName(entityModel);
	                        		if (caseSensitive) {
	                        			tableName = "\"" + tableName + "\"";
	                        		}
	                        		if (!SqlFactory.getNative(connection).exists(connection, tableName)) {
	                        			executeEntityCreate(connection, entityModel);
	                        		} else {
	                        			executeEntityUpdate(connection, entityModel);
	                        		}
	                        	}
	                        }
	                    } catch (Exception e) {
	                        logger.error(e.getMessage(), e);
	                        errors.add(e.getMessage());
	                    }
	                }
	                
	//                // process the foreign keys of the entities
	//                for (String dsName : sorted) {
	//                    XSKDataStructureModel model = DATA_STRUCTURE_MODELS.get(dsName);
	//                    try {
	//                        if (model != null && model instanceof XSKDataStructureEntityModel) {
	//                            XSKDataStructureEntityModel entityModel = (XSKDataStructureEntityModel) model;
	//                            String tableName = entityModel.getContext() + "_" + entityModel.getName();
	//                            executeForeignKeys(connection, (XSKDataStructureEntityModel) model);
	//                        }
	//                    } catch (Exception e) {
	//                        logger.error(e.getMessage(), e);
	//                        errors.add(e.getMessage());
	//                    }
	//                }
	
	                // process views in the proper order
	                for (String dsName : sorted) {
	                	XSKDataStructureHDBViewModel model = DATA_STRUCTURE_VIEWS_MODELS.get(dsName);
	                    try {
	                        if (model != null) {
	                            if (!SqlFactory.getNative(connection).exists(connection, model.getName())) {
	                                executeViewCreate(connection, model);
	                            } else {
	                                logger.warn(format("View [{0}] already exists during the update process", dsName));
	                            }
	                        }
	                    } catch (Exception e) {
	                        logger.error(e.getMessage(), e);
	                        errors.add(e.getMessage());
	                    }
	                }
	
	               
	
	                // process hdbProcedures
	                executeHDBProceduresCreate(connection, hdbProceduresToUpdate);
	                
	                // process hdbTableFunctions
	                executeHDBTableFunctionsCreate(connection, hdbTableFunctionsToUpdate);
	                
	                // process calculation views
	//              executeCalculationViewsCreate(connection, calculationViews);
	                
	                // process hdbCalculationViews
	//                executeHDBCalculationViewsCreate(connection, hdbCalculationsViewToUpdate);
	                
	            
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

	private void executeHDBSchemasCreate(Connection connection, List<XSKDataStructureHDBSchemaModel> hdbSchemasToUpdate) throws SQLException {
        HDBSchemaCreateProcessor.execute(connection, hdbSchemasToUpdate);
    }

    private void executeHDBSchemasDrop(Connection connection, List<XSKDataStructureHDBSchemaModel> hdbSchemas) throws SQLException {
        HDBSchemaDropProcessor.execute(connection, hdbSchemas);
    }

    private void executeHDBProceduresCreate(Connection connection, List<XSKDataStructureHDBProcedureModel> hdbProcedures)
            throws SQLException {
        HDBProcedureCreateProcessor.execute(connection, hdbProcedures);
    }

    private void executeHDBProceduresDrop(Connection connection, List<XSKDataStructureHDBProcedureModel> hdbProcedures)
            throws SQLException {
        HDBProcedureDropProcessor.execute(connection, hdbProcedures);
    }
    
    private void executeHDBTableFunctionsCreate(Connection connection, List<XSKDataStructureHDBTableFunctionModel> hdbTableFunctions)
            throws SQLException {
        HDBTableFunctionCreateProcessor.execute(connection, hdbTableFunctions);
    }

    private void executeHDBTableFunctionsDrop(Connection connection, List<XSKDataStructureHDBTableFunctionModel> hdbTableFunctions)
            throws SQLException {
        HDBTableFunctionDropProcessor.execute(connection, hdbTableFunctions);
    }

//    private void executeCalculationViewsCreate(Connection connection,
//                                               List<XSKDataStructureCalculationViewModel> calculationViews) throws SQLException {
//        CalculationViewCreateProcessor.execute(connection, calculationViews);
//
//    }
//
//    private void executeCalculationViewsDrop(Connection connection,
//                                             List<XSKDataStructureCalculationViewModel> calculationViews) throws SQLException {
//        CalculationViewDropProcessor.execute(connection, calculationViews);
//    }
    
//    private void executeHDBCalculationViewsCreate(Connection connection, List<XSKDataStructureHDBCalculationViewModel> hdbCalculationView)
//            throws SQLException {
//        HDBCalculationViewCreateProcessor.execute(connection, hdbCalculationView);
//    }
//
//    private void executeHDBCalculationViewsDrop(Connection connection, List<XSKDataStructureHDBCalculationViewModel> hdbCalculationView)
//            throws SQLException {
//        HDBCalculationViewDropProcessor.execute(connection, hdbCalculationView);
//    }
    
    private void executeHDI(Connection connection, List<XSKDataStructureHDIModel> hdiModels)
            throws SQLException {
        XSKHDIContainerCreateProcessor.execute(connection, hdiModels);
    }

    /**
     * Execute table update.
     *
     * @param connection the connection
     * @param tableModel the table model
     * @throws SQLException the SQL exception
     */
    public void executeTableUpdate(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
        logger.info("Processing Update Table: " + tableModel.getName());
        if (SqlFactory.getNative(connection).exists(connection, tableModel.getName())) {
            if (SqlFactory.getNative(connection).count(connection, tableModel.getName()) == 0) {
                executeTableDrop(connection, tableModel);
                executeTableCreate(connection, tableModel);
            } else {
                executeTableAlter(connection, tableModel);
            }
        } else {
            executeTableCreate(connection, tableModel);
        }
    }

    /**
     * Execute table create.
     *
     * @param connection the connection
     * @param tableModel the table model
     * @throws SQLException the SQL exception
     */
    private void executeTableCreate(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
        XSKTableCreateProcessor.execute(connection, tableModel);
    }

    /**
     * Execute table alter.
     *
     * @param connection the connection
     * @param tableModel the table model
     * @throws SQLException
     */
    private void executeTableAlter(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
        logger.error("Altering of a non-empty table is not implemented yet.");
        // TableAlterProcessor.execute(connection, tableModel);
    }

    /**
     * Execute table drop.
     *
     * @param connection the connection
     * @param tableModel the table model
     * @throws SQLException the SQL exception
     */
    public void executeTableDrop(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
        XSKTableDropProcessor.execute(connection, tableModel);
    }

    /**
     * Execute view create.
     *
     * @param connection the connection
     * @param viewModel  the view model
     * @throws SQLException the SQL exception
     */
    public void executeViewCreate(Connection connection, XSKDataStructureHDBViewModel viewModel) throws SQLException {
        XSKViewCreateProcessor.execute(connection, viewModel);
    }

    /**
     * Execute view drop.
     *
     * @param connection the connection
     * @param viewModel  the view model
     * @throws SQLException the SQL exception
     */
    public void executeViewDrop(Connection connection, XSKDataStructureHDBViewModel viewModel) throws SQLException {
        XSKViewDropProcessor.execute(connection, viewModel);
    }

    /**
     * Execute entity update.
     *
     * @param connection  the connection
     * @param entityModel the entity model
     * @throws SQLException the SQL exception
     */
    private void executeEntityUpdate(Connection connection, XSKDataStructureEntityModel entityModel)
            throws SQLException {
        XSKEntityUpdateProcessor.execute(connection, entityModel);
    }

    /**
     * Execute entity create.
     *
     * @param connection  the connection
     * @param entityModel the entity model
     * @throws SQLException the SQL exception
     */
    private void executeEntityCreate(Connection connection, XSKDataStructureEntityModel entityModel)
            throws SQLException {
        XSKEntityCreateProcessor.execute(connection, entityModel);
    }

    /**
     * Execute entity alter.
     *
     * @param connection  the connection
     * @param entityModel the entity model
     * @throws SQLException
     */
	private void executeEntityAlter(Connection connection, XSKDataStructureEntityModel entityModel)
            throws SQLException {
        // throw new NotImplementedException("Altering of a non-empty entity is not
        // implemented yet.");
        // XSKEntityAlterProcessor.execute(connection, entityModel);
    }

    /**
     * Execute entity drop.
     *
     * @param connection  the connection
     * @param entityModel the entity model
     * @throws SQLException the SQL exception
     */
    private void executeEntityDrop(Connection connection, XSKDataStructureEntityModel entityModel)
            throws SQLException {
        XSKEntityDropProcessor.execute(connection, entityModel);
    }
    
    /**
     * Process foreign keys per entity
     * @param connection the connection
     * @param entityModel the entity model
     * @throws SQLException 
     */
    public void executeForeignKeys(Connection connection, XSKDataStructureEntityModel entityModel) throws SQLException {
    	XSKEntityForeignKeysProcessor.execute(connection, entityModel);
	}
}
