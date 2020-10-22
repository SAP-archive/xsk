/**
 * Copyright (c) 2010-2019 SAP
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
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureCalculationViewModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureEntitiesModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureTableModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureViewModel;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedure;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchema;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunction;
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
    private static final Map<String, XSKDataStructureTableModel> TABLES_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureTableModel>());
    private static final Map<String, XSKDataStructureViewModel> VIEWS_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureViewModel>());
    private static final Map<String, XSKDataStructureCalculationViewModel> CALCULATIONVIEWS_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureCalculationViewModel>());
    private static final Map<String, XSKDataStructureHDBProcedure> HDBPROCEDURES_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureHDBProcedure>());
    private static final Map<String, XSKDataStructureHDBTableFunction> HDBTABLEFUNCTION_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureHDBTableFunction>());
    private static final Map<String, XSKDataStructureHDBSchema> HDBSCHEMAS_PREDELIVERED = Collections
            .synchronizedMap(new HashMap<String, XSKDataStructureHDBSchema>());

    private static final List<String> ENTITIES_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> TABLES_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> VIEWS_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> CALCULATIONVIEWS_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> HDB_PROCEDURES_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> HDB_TABLE_FUNCTION_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());
    private static final List<String> HDB_SCHEMAS_SYNCHRONIZED = Collections.synchronizedList(new ArrayList<String>());

    private static final Map<String, XSKDataStructureEntitiesModel> DATA_STRUCTURE_ENTITIES_MODELS = new LinkedHashMap<String, XSKDataStructureEntitiesModel>();
    private static final Map<String, XSKDataStructureTableModel> DATA_STRUCTURE_TABLES_MODELS = new LinkedHashMap<String, XSKDataStructureTableModel>();
    private static final Map<String, XSKDataStructureViewModel> DATA_STRUCTURE_VIEWS_MODELS = new LinkedHashMap<String, XSKDataStructureViewModel>();
    private static final Map<String, XSKDataStructureCalculationViewModel> DATA_STRUCTURE_CALCVULATIONVIEWS_MODELS = new LinkedHashMap<String, XSKDataStructureCalculationViewModel>();
    private static final Map<String, XSKDataStructureHDBProcedure> DATA_STRUCTURE_HDB_PROCEDURES_MODELS = new LinkedHashMap<String, XSKDataStructureHDBProcedure>();
    private static final Map<String, XSKDataStructureHDBTableFunction> DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS = new LinkedHashMap<String, XSKDataStructureHDBTableFunction>();
    private static final Map<String, XSKDataStructureHDBSchema> DATA_STRUCTURE_HDB_SCHEMAS_MODELS = new LinkedHashMap<String, XSKDataStructureHDBSchema>();
    
    

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
        XSKDataStructureEntitiesModel model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_ENTITIES, contentPath, data);
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
        XSKDataStructureTableModel model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_TABLE, contentPath, data);
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
        XSKDataStructureViewModel model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_VIEW, contentPath, data);
        VIEWS_PREDELIVERED.put(contentPath, model);
    }

    /**
     * Register predelivered calculation view files.
     *
     * @param contentPath the data path
     * @throws Exception
     */
    public void registerPredeliveredCalculationView(String contentPath) throws Exception {
        String data = loadResourceContent(contentPath);
        XSKDataStructureCalculationViewModel model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_CALCVIEW, contentPath, data);
        CALCULATIONVIEWS_PREDELIVERED.put(contentPath, model);
    }

    /**
     * Register predelivered .hdbprocedure files.
     *
     * @param contentPath the data path
     * @throws IOException
     */
    public void registerPredeliveredHDBProcedure(String contentPath) throws IOException, XSKDataStructuresException {
        String data = loadResourceContent(contentPath);
        XSKDataStructureHDBProcedure model;
        model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_PROCEDURE, contentPath, data);
        HDBPROCEDURES_PREDELIVERED.put(contentPath, model);
    }
    
    /**
     * Register predelivered .hdbtablefunction files.
     *
     * @param contentPath the data path
     * @throws IOException
     */
    public void registerPredeliveredHDBTableFunction(String contentPath) throws IOException, XSKDataStructuresException {
        String data = loadResourceContent(contentPath);
        XSKDataStructureHDBTableFunction model;
        model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION, contentPath, data);
        HDBTABLEFUNCTION_PREDELIVERED.put(contentPath, model);
    }

    public void registerPredeliveredHDBSchema(String contentPath) throws IOException, XSKDataStructuresException {
        String data = loadResourceContent(contentPath);
        XSKDataStructureHDBSchema model;
        model = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_SCHEMA, contentPath, data);
        HDBSCHEMAS_PREDELIVERED.put(contentPath, model);
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
        DATA_STRUCTURE_CALCVULATIONVIEWS_MODELS.clear();
        DATA_STRUCTURE_HDB_PROCEDURES_MODELS.clear();
        DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.clear();
        DATA_STRUCTURE_HDB_SCHEMAS_MODELS.clear();

        //Perhaps clear the other synchronized structures as well. during runtime these are not being cleared and, therefore, nothing would be deleted from the DB Table
        HDB_PROCEDURES_SYNCHRONIZED.clear();
        //This one too?
        HDB_SCHEMAS_SYNCHRONIZED.clear();
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
        for (XSKDataStructureHDBSchema hdbSchema : HDBSCHEMAS_PREDELIVERED.values()) {
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
        for (XSKDataStructureTableModel tables : TABLES_PREDELIVERED.values()) {
            try {
                synchronizeTable(tables);
            } catch (Exception e) {
                logger.error(format("Update tables [{0}] skipped due to an error: {1}", tables, e.getMessage()), e);
            }
        }
        logger.trace("Done synchronizing predelivered Tables.");

        // Views
        logger.trace("Synchronizing predelivered Views...");
        for (XSKDataStructureViewModel views : VIEWS_PREDELIVERED.values()) {
            try {
                synchronizeView(views);
            } catch (Exception e) {
                logger.error(format("Update views [{0}] skipped due to an error: {1}", views, e.getMessage()), e);
            }
        }
        logger.trace("Done synchronizing predelivered Views.");

        // Views
        logger.trace("Synchronizing predelivered Calculation Views...");
        for (XSKDataStructureCalculationViewModel calculationViews : CALCULATIONVIEWS_PREDELIVERED.values()) {
            try {
                synchronizeCalculationView(calculationViews);
            } catch (Exception e) {
                logger
                        .error(
                                format("Update calculationview [{0}] skipped due to an error: {1}", calculationViews, e.getMessage()),
                                e);
            }
        }
        logger.trace("Done synchronizing predelivered Calculation Views.");

        // HDBProcedures
        logger.trace("Synchronizing predelivered HDB Procedures...");
        for (XSKDataStructureHDBProcedure hdbProcedure : HDBPROCEDURES_PREDELIVERED.values()) {
            try {
                synchronizeHDBProcedure(hdbProcedure);
            } catch (Exception e) {
                logger.error(format("Update hdbprocedure [{0}] skipped due to an error: {1}", hdbProcedure, e.getMessage()), e);
            }
        }
        logger.trace("Done synchronizing predelivered HDB Procedures.");

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
                logger
                        .info("Synchronized a new Entities file [{}] from location: {}", entitiesModel.getName(),
                                entitiesModel.getLocation());
            } else {
                XSKDataStructureEntitiesModel existing = dataStructuresCoreService.getDataStructure(entitiesModel.getLocation(), entitiesModel.getType());
                if (!entitiesModel.equals(existing)) {
                    dataStructuresCoreService
                            .updateDataStructure(entitiesModel.getLocation(), entitiesModel.getName(), entitiesModel.getHash(), entitiesModel.getType());
                    DATA_STRUCTURE_ENTITIES_MODELS.put(entitiesModel.getName(), entitiesModel);
                    logger
                            .info("Synchronized a modified Entities file [{}] from location: {}", entitiesModel.getName(),
                                    entitiesModel.getLocation());
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
    private void synchronizeTable(XSKDataStructureTableModel tableModel) throws SynchronizationException {
        try {
            if (!dataStructuresCoreService.existsDataStructure(tableModel.getLocation(), tableModel.getType())) {
                dataStructuresCoreService.createDataStructure(tableModel.getLocation(), tableModel.getName(), tableModel.getHash(), tableModel.getType());
                DATA_STRUCTURE_TABLES_MODELS.put(tableModel.getName(), tableModel);
                logger
                        .info("Synchronized a new Table file [{}] from location: {}", tableModel.getName(),
                                tableModel.getLocation());
            } else {
                XSKDataStructureTableModel existing = dataStructuresCoreService.getDataStructure(tableModel.getLocation(), tableModel.getType());
                if (!tableModel.equals(existing)) {
                    dataStructuresCoreService.updateDataStructure(tableModel.getLocation(), tableModel.getName(), tableModel.getHash(), tableModel.getType());
                    DATA_STRUCTURE_TABLES_MODELS.put(tableModel.getName(), tableModel);
                    logger
                            .info("Synchronized a modified Table file [{}] from location: {}", tableModel.getName(),
                                    tableModel.getLocation());
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
    private void synchronizeView(XSKDataStructureViewModel viewModel) throws SynchronizationException {
        try {
            if (!dataStructuresCoreService.existsDataStructure(viewModel.getLocation(), viewModel.getType())) {
                dataStructuresCoreService.createDataStructure(viewModel.getLocation(), viewModel.getName(), viewModel.getHash(), viewModel.getType());
                DATA_STRUCTURE_VIEWS_MODELS.put(viewModel.getName(), viewModel);
                logger
                        .info("Synchronized a new View file [{}] from location: {}", viewModel.getName(), viewModel.getLocation());
            } else {
                XSKDataStructureViewModel existing = dataStructuresCoreService.getDataStructure(viewModel.getLocation(), viewModel.getType());
                if (!viewModel.equals(existing)) {
                    dataStructuresCoreService.updateDataStructure(viewModel.getLocation(), viewModel.getName(), viewModel.getHash(), viewModel.getType());
                    DATA_STRUCTURE_VIEWS_MODELS.put(viewModel.getName(), viewModel);
                    logger
                            .info("Synchronized a modified View file [{}] from location: {}", viewModel.getName(),
                                    viewModel.getLocation());
                }
            }
            if (!VIEWS_SYNCHRONIZED.contains(viewModel.getLocation())) {
                VIEWS_SYNCHRONIZED.add(viewModel.getLocation());
            }
        } catch (XSKDataStructuresException e) {
            throw new SynchronizationException(e);
        }
    }

    /**
     * Synchronize calculation views.
     *
     * @param calculationViewModel the view model
     * @throws SynchronizationException the synchronization exception
     */
    private void synchronizeCalculationView(XSKDataStructureCalculationViewModel calculationViewModel)
            throws SynchronizationException {
        try {
            if (!dataStructuresCoreService.existsDataStructure(calculationViewModel.getLocation(), calculationViewModel.getType())) {
                dataStructuresCoreService
                        .createDataStructure(calculationViewModel.getLocation(), calculationViewModel.getName(),
                                calculationViewModel.getHash(), calculationViewModel.getType());
                DATA_STRUCTURE_CALCVULATIONVIEWS_MODELS.put(calculationViewModel.getName(), calculationViewModel);
                logger
                        .info("Synchronized a new Calculation View file [{}] from location: {}", calculationViewModel.getName(),
                                calculationViewModel.getLocation());
            } else {
                XSKDataStructureCalculationViewModel existing = dataStructuresCoreService
                        .getDataStructure(calculationViewModel.getLocation(), calculationViewModel.getType());
                if (!calculationViewModel.equals(existing)) {
                    dataStructuresCoreService
                            .updateDataStructure(calculationViewModel.getLocation(), calculationViewModel.getName(),
                                    calculationViewModel.getHash(), calculationViewModel.getType());
                    DATA_STRUCTURE_CALCVULATIONVIEWS_MODELS.put(calculationViewModel.getName(), calculationViewModel);
                    logger
                            .info("Synchronized a modified Calculation View file [{}] from location: {}",
                                    calculationViewModel.getName(), calculationViewModel.getLocation());
                }
            }
            if (!CALCULATIONVIEWS_SYNCHRONIZED.contains(calculationViewModel.getLocation())) {
                CALCULATIONVIEWS_SYNCHRONIZED.add(calculationViewModel.getLocation());
            }
        } catch (XSKDataStructuresException e) {
            throw new SynchronizationException(e);
        }
    }

    /**
     * Synchronize HDBProcedure files
     *
     * @param hdbProcedure the view model
     * @throws SynchronizationException the synchronization exception
     */
    private void synchronizeHDBProcedure(XSKDataStructureHDBProcedure hdbProcedure) throws SynchronizationException {
        try {
            // TODO: ommit double calling of finding the hdbProcedure by extracting it in
            // variable
            // String schemaNameConcatProcedureName = hdbProcedure.getSchemaName() + "." +
            // hdbProcedure.getName();
            if (!dataStructuresCoreService.existsDataStructure(hdbProcedure.getLocation(), hdbProcedure.getType())) {
                dataStructuresCoreService
                        .createDataStructure(hdbProcedure.getLocation(), hdbProcedure.getName(), hdbProcedure.getHash(), hdbProcedure.getType());
                DATA_STRUCTURE_HDB_PROCEDURES_MODELS.put(hdbProcedure.getName(), hdbProcedure);
                logger
                        .info("Synchronized a new HDB Procedure file [{}] from location: {}", hdbProcedure.getName(),
                                hdbProcedure.getLocation());
            } else {
                XSKDataStructureHDBProcedure existing = dataStructuresCoreService.getDataStructure(hdbProcedure.getLocation(), hdbProcedure.getType());
                if (!hdbProcedure.equals(existing)) {
                    dataStructuresCoreService
                            .updateDataStructure(hdbProcedure.getLocation(), hdbProcedure.getName(), hdbProcedure.getHash(), hdbProcedure.getType());
                    DATA_STRUCTURE_HDB_PROCEDURES_MODELS.put(hdbProcedure.getName(), hdbProcedure);
                    logger
                            .info("Synchronized a modified HDB Procedure file [{}] from location: {}", hdbProcedure.getName(),
                                    hdbProcedure.getLocation());
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
    private void synchronizeHDBTableFunction(XSKDataStructureHDBTableFunction hdbTableFunction) throws SynchronizationException {
        try {
            // TODO: ommit double calling of finding the hdbTableFunction by extracting it in
            // variable
            // String schemaNameConcatTableFunctionName = hdbTableName.getSchemaName() + "." +
            // hdbProcedure.getName();
            if (!dataStructuresCoreService.existsDataStructure(hdbTableFunction.getLocation(), hdbTableFunction.getType())) {
                dataStructuresCoreService
                        .createDataStructure(hdbTableFunction.getLocation(), hdbTableFunction.getName(), hdbTableFunction.getHash(), hdbTableFunction.getType());
                DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.put(hdbTableFunction.getName(), hdbTableFunction);
                logger
                        .info("Synchronized a new HDB Table Function file [{}] from location: {}", hdbTableFunction.getName(),
                        		hdbTableFunction.getLocation());
            } else {
                XSKDataStructureHDBProcedure existing = dataStructuresCoreService.getDataStructure(hdbTableFunction.getLocation(), hdbTableFunction.getType());
                if (!hdbTableFunction.equals(existing)) {
                    dataStructuresCoreService
                            .updateDataStructure(hdbTableFunction.getLocation(), hdbTableFunction.getName(), hdbTableFunction.getHash(), hdbTableFunction.getType());
                    DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.put(hdbTableFunction.getName(), hdbTableFunction);
                    logger
                            .info("Synchronized a modified HDB Table Function file [{}] from location: {}", hdbTableFunction.getName(),
                            		hdbTableFunction.getLocation());
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
    private void synchronizeHDBSchema(XSKDataStructureHDBSchema hdbSchema) throws SynchronizationException {
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
                logger
                        .info("Synchronized a new HDB Schema file [{}] from location: {}", hdbSchema.getName(),
                                hdbSchema.getLocation());
            } else {
                XSKDataStructureHDBSchema existing = dataStructuresCoreService.getDataStructure(hdbSchema.getLocation(), hdbSchema.getType());
                if (!hdbSchema.equals(existing)) {
                    dataStructuresCoreService
                            .updateDataStructure(hdbSchema.getLocation(), hdbSchema.getName(), hdbSchema.getHash(), hdbSchema.getType());
                    DATA_STRUCTURE_HDB_SCHEMAS_MODELS.put(hdbSchema.getName(), hdbSchema);
                    logger
                            .info("Synchronized a modified HDB Schema file [{}] from location: {}", hdbSchema.getName(),
                                    hdbSchema.getLocation());
                }
            }
            if (!HDB_SCHEMAS_SYNCHRONIZED.contains(hdbSchema.getLocation())) {
                HDB_SCHEMAS_SYNCHRONIZED.add(hdbSchema.getLocation());
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
        byte[] content = resource.getContent();
        String contentAsString;
        try {
            contentAsString = IOUtils
                    .toString(new InputStreamReader(new ByteArrayInputStream(content), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new SynchronizationException(e);
        }

        if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_ENTITIES)) {
            XSKDataStructureEntitiesModel entitiesModel;
            try {
                entitiesModel = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_ENTITIES, registryPath, contentAsString);
            } catch (Exception e) {
                throw new SynchronizationException(e);
            }
            entitiesModel.setLocation(registryPath);
            synchronizeEntities(entitiesModel);
            return;
        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_TABLE)) {
            XSKDataStructureTableModel tableModel;
            try {
                tableModel = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_TABLE, registryPath, contentAsString);
            } catch (Exception e) {
                throw new SynchronizationException(e);
            }
            tableModel.setLocation(registryPath);
            synchronizeTable(tableModel);
            return;
        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_VIEW)) {
            XSKDataStructureViewModel viewModel;
            try {
                viewModel = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_VIEW, registryPath, contentAsString);
            } catch (Exception e) {
                throw new SynchronizationException(e);
            }
            viewModel.setLocation(registryPath);
            synchronizeView(viewModel);
            return;
        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_CALCULATION_VIEW)) {
            XSKDataStructureCalculationViewModel calculationViewModel;
            try {
                calculationViewModel = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_CALCVIEW, registryPath, contentAsString);
            } catch (Exception e) {
                throw new SynchronizationException(e);
            }
            calculationViewModel.setLocation(registryPath);
            synchronizeCalculationView(calculationViewModel);
            return;
        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBPROCEDURE)) {
            XSKDataStructureHDBProcedure hdbProcedure;
            try {
                hdbProcedure = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_PROCEDURE, registryPath, contentAsString);
            } catch (Exception e) {
                throw new SynchronizationException(e);
            }
            hdbProcedure.setLocation(registryPath);
            synchronizeHDBProcedure(hdbProcedure);
            return;
        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBTABLEFUNCTION)) {
            XSKDataStructureHDBTableFunction hdbTableFunction;
            try {
            	hdbTableFunction = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION, registryPath, contentAsString);
            } catch (Exception e) {
                throw new SynchronizationException(e);
            }
            hdbTableFunction.setLocation(registryPath);
            synchronizeHDBTableFunction(hdbTableFunction);
            return;
        } else if (resourceName.endsWith(IXSKDataStructureModel.FILE_EXTENSION_HDBSCHEMA)) {
            XSKDataStructureHDBSchema hdbSchema;
            try {
                hdbSchema = dataStructuresCoreService.parseDataStructure(IXSKDataStructureModel.TYPE_HDB_SCHEMA, registryPath, contentAsString);
            } catch (Exception e) {
                throw new SynchronizationException(e);
            }
            hdbSchema.setLocation(registryPath);
            synchronizeHDBSchema(hdbSchema);
            return;
        }
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
                List<XSKDataStructureEntitiesModel> entitiesModels = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_ENTITIES);
                for (XSKDataStructureEntitiesModel entitiesModel : entitiesModels) {
                    if (!ENTITIES_SYNCHRONIZED.contains(entitiesModel.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(entitiesModel.getLocation());
                        logger
                                .warn("Cleaned up Entities Data file [{}] from location: {}", entitiesModel.getName(),
                                        entitiesModel.getLocation());
                    }
                }

                // Tables
                List<XSKDataStructureTableModel> tableModels = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_TABLE);
                for (XSKDataStructureTableModel tableModel : tableModels) {
                    if (!TABLES_SYNCHRONIZED.contains(tableModel.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(tableModel.getLocation());
                        logger
                                .warn("Cleaned up Table Data file [{}] from location: {}", tableModel.getName(),
                                        tableModel.getLocation());
                    }
                }

                // Views
                List<XSKDataStructureViewModel> viewModels = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_VIEW);
                for (XSKDataStructureViewModel viewModel : viewModels) {
                    if (!VIEWS_SYNCHRONIZED.contains(viewModel.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(viewModel.getLocation());
                        logger
                                .warn("Cleaned up View Data file [{}] from location: {}", viewModel.getName(), viewModel.getLocation());
                    }
                }

                // Calculation Views
                List<XSKDataStructureCalculationViewModel> calculationViewModels = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_CALCVIEW);
                for (XSKDataStructureCalculationViewModel calculationViewModel : calculationViewModels) {
                    if (!CALCULATIONVIEWS_SYNCHRONIZED.contains(calculationViewModel.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(calculationViewModel.getLocation());
                        logger
                                .warn("Cleaned up Calculation View Data file [{}] from location: {}", calculationViewModel.getName(),
                                        calculationViewModel.getLocation());
                    }
                }

                // HDB Procedures
                List<XSKDataStructureHDBProcedure> hdbProcedures = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDB_PROCEDURE);
                for (XSKDataStructureHDBProcedure hdbProcedure : hdbProcedures) {
                    if (!HDB_PROCEDURES_SYNCHRONIZED.contains(hdbProcedure.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(hdbProcedure.getLocation());
                        //DROP Deleted procedure
                        HDBProcedureDropProcessor.executeSingle(connection, hdbProcedure);
                        logger
                                .warn("Cleaned up HDB Procedure Data file [{}] from location: {}", hdbProcedure.getName(),
                                        hdbProcedure.getLocation());
                    }
                }
                
                // HDB Table Functions
                List<XSKDataStructureHDBTableFunction> hdbTableFunctions = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION);
                for (XSKDataStructureHDBTableFunction hdbTableFunction : hdbTableFunctions) {
                    if (!HDB_TABLE_FUNCTION_SYNCHRONIZED.contains(hdbTableFunction.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(hdbTableFunction.getLocation());
                        //DROP Deleted procedure
                        HDBTableFunctionDropProcessor.executeSingle(connection, hdbTableFunction);
                        logger
                                .warn("Cleaned up HDB Table Function Data file [{}] from location: {}", hdbTableFunction.getName(),
                                        hdbTableFunction.getLocation());
                    }
                }

                // HDB Schemas
                List<XSKDataStructureHDBSchema> hdbSchemas = dataStructuresCoreService.getDataStructuresByType(IXSKDataStructureModel.TYPE_HDB_SCHEMA);
                for (XSKDataStructureHDBSchema hdbSchema : hdbSchemas) {
                    if (!HDB_SCHEMAS_SYNCHRONIZED.contains(hdbSchema.getLocation())) {
                        dataStructuresCoreService.removeDataStructure(hdbSchema.getLocation());
                        //DROP Deleted procedure
                        HDBSchemaDropProcessor.executeSingle(connection, hdbSchema);
                        logger
                                .warn("Cleaned up HDB Schema Data file [{}] from location: {}", hdbSchema.getName(),
                                        hdbSchema.getLocation());
                    }
                }
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
                && DATA_STRUCTURE_TABLES_MODELS.isEmpty() && DATA_STRUCTURE_VIEWS_MODELS.isEmpty()
                && DATA_STRUCTURE_CALCVULATIONVIEWS_MODELS.isEmpty() 
                && DATA_STRUCTURE_HDB_PROCEDURES_MODELS.isEmpty()
                && DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.isEmpty()
                && DATA_STRUCTURE_HDB_SCHEMAS_MODELS.isEmpty()) {
            logger.trace("No XSK Data Structures to update.");
            return;
        }

        List<String> errors = new ArrayList<String>();
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                // topology sort of dependencies
                List<String> sorted = new ArrayList<String>();
                // List<String> external = new ArrayList<String>();

                // TODO - skip sorting for now
                // try {
                // DataStructureTopologicalSorter.sort(DATA_STRUCTURE_MODELS, sorted, external);
                //
                // logger.trace("topological sorting");
                //
                // for (String location : sorted) {
                // logger.trace("location: " + location);
                // }
                // } catch (Exception e) {
                // logger.error(e.getMessage(), e);
                // errors.add(e.getMessage());
                // sorted.clear();
                // }

                if (sorted.isEmpty()) {
                    // something wrong happened with the sorting - probably cyclic dependencies
                    // we go for the back-up list and try to apply what would succeed
                    // logger.warn("Probably there are cyclic dependencies!");
                    sorted.addAll(DATA_STRUCTURE_ENTITIES_MODELS.keySet());
                    sorted.addAll(DATA_STRUCTURE_TABLES_MODELS.keySet());
                    sorted.addAll(DATA_STRUCTURE_VIEWS_MODELS.keySet());
                    sorted.addAll(DATA_STRUCTURE_CALCVULATIONVIEWS_MODELS.keySet());
                    sorted.addAll(DATA_STRUCTURE_HDB_PROCEDURES_MODELS.keySet());
                    sorted.addAll(DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.keySet());
                    sorted.addAll(DATA_STRUCTURE_HDB_SCHEMAS_MODELS.keySet());
                }

                // drop HDB Procedures
                List<XSKDataStructureHDBProcedure> hdbProceduresToUpdate = new ArrayList<XSKDataStructureHDBProcedure>();
                for (int i = sorted.size() - 1; i >= 0; i--) {
                    String dsName = sorted.get(i);
                    XSKDataStructureHDBProcedure model = DATA_STRUCTURE_HDB_PROCEDURES_MODELS.get(dsName);
                    if (model != null) {
                    	hdbProceduresToUpdate.add(model);
                    }
                }
                executeHDBProceduresDrop(connection, hdbProceduresToUpdate);
                
                // drop HDB Table Functions
                List<XSKDataStructureHDBTableFunction> hdbTableFunctionsToUpdate = new ArrayList<XSKDataStructureHDBTableFunction>();
                for (int i = sorted.size() - 1; i >= 0; i--) {
                    String dsName = sorted.get(i);
                    XSKDataStructureHDBTableFunction model = DATA_STRUCTURE_HDB_TABLE_FUNCTIONS_MODELS.get(dsName);
                    if (model != null) {
                    	hdbTableFunctionsToUpdate.add(model);
                    }
                }
                executeHDBTableFunctionsDrop(connection, hdbTableFunctionsToUpdate);

                // drop calculation views
                List<XSKDataStructureCalculationViewModel> calculationViews = new ArrayList<XSKDataStructureCalculationViewModel>();
                for (int i = sorted.size() - 1; i >= 0; i--) {
                    String dsName = sorted.get(i);
                    XSKDataStructureCalculationViewModel model = DATA_STRUCTURE_CALCVULATIONVIEWS_MODELS.get(dsName);
                    if (model != null) {
                    	calculationViews.add(model);
                    }
                }
//                executeCalculationViewsDrop(connection, calculationViews);

                // drop views in a reverse order
                for (int i = sorted.size() - 1; i >= 0; i--) {
                    String dsName = sorted.get(i);
                    XSKDataStructureViewModel model = DATA_STRUCTURE_VIEWS_MODELS.get(dsName);
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
                    XSKDataStructureTableModel model = DATA_STRUCTURE_TABLES_MODELS.get(dsName);
                    try {
                        if (model != null) {
                            if (SqlFactory.getNative(connection).exists(connection, model.getName())) {
                                if (SqlFactory.getNative(connection).count(connection, model.getName()) == 0) {
                                    executeTableDrop(connection, model);
                                } else {
                                    logger
                                            .warn(format("Table [{0}] cannot be deleted during the update process, because it is not empty",
                                                    dsName));
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
                        				logger
                        				.warn(format("Entity [{0}] cannot be deleted during the update process, because it is not empty",
                        						dsName));
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
                List<XSKDataStructureHDBSchema> hdbSchemasToUpdate = new ArrayList<XSKDataStructureHDBSchema>();
                for (int i = sorted.size() - 1; i >= 0; i--) {
                    String dsName = sorted.get(i);
                    XSKDataStructureHDBSchema model = DATA_STRUCTURE_HDB_SCHEMAS_MODELS.get(dsName);
                    if (model != null) {
                    	hdbSchemasToUpdate.add(model);
                    }
                }
//                executeHDBSchemasDrop(connection, hdbSchemasToUpdate);

                // process hdbSchemas
                executeHDBSchemasCreate(connection, hdbSchemasToUpdate);

                // process tables in the proper order
                for (String dsName : sorted) {
                	XSKDataStructureTableModel model = DATA_STRUCTURE_TABLES_MODELS.get(dsName);
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
                	XSKDataStructureViewModel model = DATA_STRUCTURE_VIEWS_MODELS.get(dsName);
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

                // process calculation views
//                executeCalculationViewsCreate(connection, calculationViews);

                // process hdbProcedures
                executeHDBProceduresCreate(connection, hdbProceduresToUpdate);
                
                // process hdbTableFunctions
                executeHDBTableFunctionsCreate(connection, hdbTableFunctionsToUpdate);



            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            logger.error(concatenateListOfStrings(errors, "\n---\n"), e);
        }
    }

	private void executeHDBSchemasCreate(Connection connection, List<XSKDataStructureHDBSchema> hdbSchemasToUpdate) throws SQLException {
        HDBSchemaCreateProcessor.execute(connection, hdbSchemasToUpdate);
    }

    private void executeHDBSchemasDrop(Connection connection, List<XSKDataStructureHDBSchema> hdbSchemas) throws SQLException {
        HDBSchemaDropProcessor.execute(connection, hdbSchemas);
    }

    private void executeHDBProceduresCreate(Connection connection, List<XSKDataStructureHDBProcedure> hdbProcedures)
            throws SQLException {
        HDBProcedureCreateProcessor.execute(connection, hdbProcedures);
    }

    private void executeHDBProceduresDrop(Connection connection, List<XSKDataStructureHDBProcedure> hdbProcedures)
            throws SQLException {
        HDBProcedureDropProcessor.execute(connection, hdbProcedures);
    }
    
    private void executeHDBTableFunctionsCreate(Connection connection, List<XSKDataStructureHDBTableFunction> hdbTableFunctions)
            throws SQLException {
        HDBTableFunctionCreateProcessor.execute(connection, hdbTableFunctions);
    }

    private void executeHDBTableFunctionsDrop(Connection connection, List<XSKDataStructureHDBTableFunction> hdbTableFunctions)
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

    /**
     * Execute table update.
     *
     * @param connection the connection
     * @param tableModel the table model
     * @throws SQLException the SQL exception
     */
    public void executeTableUpdate(Connection connection, XSKDataStructureTableModel tableModel) throws SQLException {
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
    private void executeTableCreate(Connection connection, XSKDataStructureTableModel tableModel) throws SQLException {
        XSKTableCreateProcessor.execute(connection, tableModel);
    }

    /**
     * Execute table alter.
     *
     * @param connection the connection
     * @param tableModel the table model
     * @throws SQLException
     */
    private void executeTableAlter(Connection connection, XSKDataStructureTableModel tableModel) throws SQLException {
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
    public void executeTableDrop(Connection connection, XSKDataStructureTableModel tableModel) throws SQLException {
        XSKTableDropProcessor.execute(connection, tableModel);
    }

    /**
     * Execute view create.
     *
     * @param connection the connection
     * @param viewModel  the view model
     * @throws SQLException the SQL exception
     */
    public void executeViewCreate(Connection connection, XSKDataStructureViewModel viewModel) throws SQLException {
        XSKViewCreateProcessor.execute(connection, viewModel);
    }

    /**
     * Execute view drop.
     *
     * @param connection the connection
     * @param viewModel  the view model
     * @throws SQLException the SQL exception
     */
    public void executeViewDrop(Connection connection, XSKDataStructureViewModel viewModel) throws SQLException {
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
