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
package com.sap.xsk.hdb.ds.facade;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKEnvironmentVariables;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.artefacts.HDBProcedureSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBScalarFunctionSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBSchemaSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBSequenceSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBSynonymSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBTableFunctionSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBTableSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBTableTypeSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBViewSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureCdsModel;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.hdb.ds.service.parser.IXSKCoreParserService;
import com.sap.xsk.hdb.ds.service.parser.XSKCoreParserService;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.utils.XSKCommonsConstants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.api.topology.TopologicalDepleter;
import org.eclipse.dirigible.commons.api.topology.TopologicalSorter;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizationArtefactType;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.repository.api.IResource;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKHDBCoreSynchronizationFacade implements IXSKHDBCoreSynchronizationFacade {

    private static final Logger logger = LoggerFactory.getLogger(XSKHDBCoreSynchronizationFacade.class);
    private static final XSKDataStructuresSynchronizer DATA_STRUCTURES_SYNCHRONIZER = new XSKDataStructuresSynchronizer();
    private final DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);
    private final Map<String, IXSKDataStructureManager> managerServices = XSKHDBModule.getManagerServices();
    private final Map<String, XSKDataStructureParser> parserServices = XSKHDBModule.getParserServices();
    private final Map<String, String> parserTypes = XSKHDBModule.getParserTypes();
    private final IXSKCoreParserService xskCoreParserService = new XSKCoreParserService();

    /**
     * Concatenate list of strings.
     *
     * @param list the list
     * @return the string
     */
    private static String concatenateListOfStrings(List<String> list) {
        StringBuilder buff = new StringBuilder();
        for (String s : list) {
            buff.append(s).append("\n---\n");
        }
        return buff.toString();
    }

    public XSKDataStructureModel parseDataStructureModel(String fileName, String path, String content, String workspace) throws SynchronizationException {
        String[] splitResourceName = fileName.split("\\.");
        String resourceExtension = "." + splitResourceName[splitResourceName.length - 1];
        String registryPath = path;
        String contentAsString = content;

        XSKDataStructureModel dataStructureModel = null;
        try {
            if (parserServices.containsKey(resourceExtension) && !isParsed(registryPath, contentAsString, resourceExtension)) {
                XSKDataStructureParametersModel parametersModel = new XSKDataStructureParametersModel(resourceExtension, registryPath, contentAsString, workspace);
                dataStructureModel = xskCoreParserService.parseDataStructure(parametersModel);
                dataStructureModel.setLocation(registryPath);
            }
        } catch (ReflectiveOperationException e) {
            logger.error("Preparse hash check failed for path " + registryPath + ". " + e.getMessage(), e);
        } catch (XSKDataStructuresException e) {
            logger.error("Synchronized artifact with path " + registryPath + " is not valid. " + e.getMessage(), e);
        } catch (XSKArtifactParserException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            throw new SynchronizationException(e);
        }
        return dataStructureModel;
    }

    public XSKDataStructureModel parseDataStructureModel(IResource resource) throws SynchronizationException {
        return this.parseDataStructureModel(resource.getName(), getRegistryPath(resource), getContent(resource), XSKCommonsConstants.XSK_REGISTRY_PUBLIC);
    }

    @Override
    public void handleResourceSynchronization(IResource resource) throws SynchronizationException, XSKDataStructuresException {
        XSKDataStructureModel dataStructureModel = parseDataStructureModel(resource);
        if (dataStructureModel != null) {
            managerServices.get(dataStructureModel.getType()).synchronizeRuntimeMetadata(dataStructureModel); // 4. we synchronize the metadata
        }
    }

    @Override
    public void handleResourceSynchronization(String fileExtension, XSKDataStructureModel dataStructureModel) throws XSKDataStructuresException {
        managerServices.get(dataStructureModel.getType()).synchronizeRuntimeMetadata(dataStructureModel);
    }

    @Override
    public void updateEntities() {
        List<String> errors = new ArrayList<>();
        try {
            try (Connection connection = dataSource.getConnection()) {
                boolean hdiOnly = Boolean.parseBoolean(Configuration.get(IXSKEnvironmentVariables.XSK_HDI_ONLY, "false"));
                if (!hdiOnly) {
                    //Process artefacts for phase 1
                    final List<XSKTopologyDataStructureModelWrapper> listSchemaWrappers = constructListOfSchemaModelWrappers(connection);
                    createArtefactsOnPhaseOne(errors, listSchemaWrappers);

                    //Process artefacts for phase two
                    final List<XSKTopologyDataStructureModelWrapper> listOfWrappersPhaseTwo = new ArrayList<>();
                    Map<String, XSKTopologyDataStructureModelWrapper> wrappersPhaseTwo = new HashMap<>();
                    listOfWrappersPhaseTwo.addAll(constructListOfCdsModelWrappers(connection, wrappersPhaseTwo));
                    listOfWrappersPhaseTwo.addAll(constructListOfTableModelWrappers(connection, wrappersPhaseTwo));
                    listOfWrappersPhaseTwo.addAll(constructListOfSequenceModelWrapper(connection, wrappersPhaseTwo));
                    listOfWrappersPhaseTwo.addAll(constructListOfTableTypesModelWrappers(connection, wrappersPhaseTwo));
                    createArtefactsOnPhaseTwo(errors, listOfWrappersPhaseTwo);

                    //Process artefacts for phase three
                    Map<String, XSKTopologyDataStructureModelWrapper> wrappersPhaseThree = new HashMap<>();
                    final List<XSKTopologyDataStructureModelWrapper> listOfWrappersPhaseThree = new ArrayList<>();
                    listOfWrappersPhaseThree.addAll(constructListOfViewModelWrappers(connection, wrappersPhaseThree));
                    listOfWrappersPhaseThree.addAll(constructListOfProcedureModelWrappers(connection, wrappersPhaseThree));
                    listOfWrappersPhaseThree.addAll(constructListOfTableFunctionModelWrappers(connection, wrappersPhaseThree));
                    listOfWrappersPhaseThree.addAll(constructListOfScalarFunctionModelWrappers(connection, wrappersPhaseThree));
                    listOfWrappersPhaseThree.addAll(constructListOfSynonymModelWrappers(connection, wrappersPhaseThree));
                    createArtefactsOnPhaseThree(errors, listOfWrappersPhaseThree);
                }
            }
        } catch (SQLException e) {
            logger.error(concatenateListOfStrings(errors), e);
        }
    }

    @NotNull
    private List<XSKTopologyDataStructureModelWrapper> constructListOfCdsModelWrappers(Connection connection, Map<String, XSKTopologyDataStructureModelWrapper> wrappers) {
        final Map<String, XSKDataStructureModel> dataStructureCdsModels = managerServices.get(IXSKDataStructureModel.TYPE_HDBDD).getDataStructureModels();
        final List<XSKTopologyDataStructureModelWrapper> listOfWrappers = new ArrayList<>();
        dataStructureCdsModels.values().forEach(cdsStructure -> {
            final IXSKDataStructureManager<XSKDataStructureHDBTableModel> xskTableManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE);
            ((XSKDataStructureCdsModel) cdsStructure).getTableModels().forEach(tableModel -> {
                HDBTableSynchronizationArtefactType artefactType = new HDBTableSynchronizationArtefactType();
                XSKTopologyDataStructureModelWrapper<XSKDataStructureHDBTableModel> tableWrapper = new XSKTopologyDataStructureModelWrapper(connection, xskTableManagerService, tableModel,
                    artefactType, wrappers);
                listOfWrappers.add(tableWrapper);
            });

            final IXSKDataStructureManager<XSKDataStructureHDBTableTypeModel> xskTableTypeManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE_TYPE);
            ((XSKDataStructureCdsModel) cdsStructure).getTableTypeModels().forEach(tableTypeModel -> {
                HDBTableTypeSynchronizationArtefactType artefactType = new HDBTableTypeSynchronizationArtefactType();
                XSKTopologyDataStructureModelWrapper<XSKDataStructureHDBTableTypeModel> tableTypeWrapper = new XSKTopologyDataStructureModelWrapper(connection, xskTableTypeManagerService,
                    tableTypeModel, artefactType, wrappers);
                listOfWrappers.add(tableTypeWrapper);
            });
        });
        return listOfWrappers;
    }

    private void createArtefactsOnPhaseThree(List<String> errors, List<XSKTopologyDataStructureModelWrapper> listOfWrappersPhaseThree) {
        TopologicalDepleter<XSKTopologyDataStructureModelWrapper> depleter = new TopologicalDepleter<>();
        TopologicalSorter<XSKTopologyDataStructureModelWrapper> sorter = new TopologicalSorter<>();
        List<XSKTopologyDataStructureModelWrapper> sortedListOfWrappers = sorter.sort(listOfWrappersPhaseThree);
        try {
            List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(sortedListOfWrappers, "");
            printErrors(errors, results, "Executing phase three of DB artefact creation.", ArtefactState.FAILED);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            errors.add(e.getMessage());
        }
    }

    @NotNull
    private List<XSKTopologyDataStructureModelWrapper> constructListOfSynonymModelWrappers(Connection connection, Map<String, XSKTopologyDataStructureModelWrapper> wrappersPhaseThree) {
        final Map<String, XSKDataStructureModel> dataStructureSynonymModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SYNONYM).getDataStructureModels();
        final List<XSKTopologyDataStructureModelWrapper> listOfWrappers = new ArrayList<>();
        final IXSKDataStructureManager<XSKDataStructureHDBSynonymModel> xskSynonymManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SYNONYM);
        dataStructureSynonymModels.values().forEach(synonymModel -> {
            HDBSynonymSynchronizationArtefactType artefactType = new HDBSynonymSynchronizationArtefactType();
            XSKTopologyDataStructureModelWrapper<XSKDataStructureHDBSynonymModel> synonymModelWrapper = new XSKTopologyDataStructureModelWrapper(connection, xskSynonymManagerService, synonymModel,
                artefactType, wrappersPhaseThree);
            listOfWrappers.add(synonymModelWrapper);
        });
        return listOfWrappers;
    }

    @NotNull
    private List<XSKTopologyDataStructureModelWrapper> constructListOfScalarFunctionModelWrappers(Connection connection, Map<String, XSKTopologyDataStructureModelWrapper> wrappers) {
        final Map<String, XSKDataStructureModel> dataStructureScalarFunctionsModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SCALAR_FUNCTION).getDataStructureModels();
        final List<XSKTopologyDataStructureModelWrapper> listOfWrappers = new ArrayList<>();
        final IXSKDataStructureManager<XSKDataStructureHDBTableFunctionModel> xskTableFunctionManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION);
        dataStructureScalarFunctionsModels.values().forEach(scalarFunctionModel -> {
            HDBScalarFunctionSynchronizationArtefactType artefactType = new HDBScalarFunctionSynchronizationArtefactType();
            XSKTopologyDataStructureModelWrapper<XSKDataStructureHDBTableFunctionModel> scalarFunctionWrapper = new XSKTopologyDataStructureModelWrapper(connection, xskTableFunctionManagerService,
                scalarFunctionModel, artefactType, wrappers);
            listOfWrappers.add(scalarFunctionWrapper);
        });
        return listOfWrappers;
    }

    @NotNull
    private List<XSKTopologyDataStructureModelWrapper> constructListOfTableFunctionModelWrappers(Connection connection, Map<String, XSKTopologyDataStructureModelWrapper> wrappers) {
        final Map<String, XSKDataStructureModel> dataStructureTableFunctionsModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION).getDataStructureModels();
        final List<XSKTopologyDataStructureModelWrapper> listOfWrappers = new ArrayList<>();
        final IXSKDataStructureManager<XSKDataStructureHDBTableFunctionModel> xskTableFunctionManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION);
        dataStructureTableFunctionsModels.values().forEach(tableFunctionModel -> {
            HDBTableFunctionSynchronizationArtefactType artefactType = new HDBTableFunctionSynchronizationArtefactType();
            XSKTopologyDataStructureModelWrapper<XSKDataStructureHDBTableFunctionModel> tableFunctionWrapper = new XSKTopologyDataStructureModelWrapper(connection, xskTableFunctionManagerService,
                tableFunctionModel, artefactType, wrappers);
            listOfWrappers.add(tableFunctionWrapper);
        });
        return listOfWrappers;
    }

    @NotNull
    private List<XSKTopologyDataStructureModelWrapper> constructListOfProcedureModelWrappers(Connection connection, Map<String, XSKTopologyDataStructureModelWrapper> wrappers) {
        final Map<String, XSKDataStructureModel> dataStructureProceduresModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_PROCEDURE).getDataStructureModels();
        final List<XSKTopologyDataStructureModelWrapper> listOfWrappers = new ArrayList<>();
        final IXSKDataStructureManager<XSKDataStructureHDBProcedureModel> xskProceduresManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_PROCEDURE);
        dataStructureProceduresModels.values().forEach(procedureModel -> {
            HDBProcedureSynchronizationArtefactType artefactType = new HDBProcedureSynchronizationArtefactType();
            XSKTopologyDataStructureModelWrapper<XSKDataStructureHDBProcedureModel> procedureWrapper = new XSKTopologyDataStructureModelWrapper(connection, xskProceduresManagerService, procedureModel,
                artefactType, wrappers);
            listOfWrappers.add(procedureWrapper);
        });
        return listOfWrappers;
    }

    @NotNull
    private List<XSKTopologyDataStructureModelWrapper> constructListOfViewModelWrappers(Connection connection, Map<String, XSKTopologyDataStructureModelWrapper> wrappers) {
        final Map<String, XSKDataStructureModel> dataStructureViewsModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_VIEW).getDataStructureModels();
        final List<XSKTopologyDataStructureModelWrapper> listOfWrappers = new ArrayList<>();
        final IXSKDataStructureManager<XSKDataStructureHDBViewModel> xskViewManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_VIEW);
        dataStructureViewsModels.values().forEach(viewModel -> {
            HDBViewSynchronizationArtefactType artefactType = new HDBViewSynchronizationArtefactType();
            XSKTopologyDataStructureModelWrapper<XSKDataStructureHDBViewModel> viewWrapper = new XSKTopologyDataStructureModelWrapper(connection, xskViewManagerService, viewModel, artefactType,
                wrappers);
            listOfWrappers.add(viewWrapper);
        });
        return listOfWrappers;
    }

    private void createArtefactsOnPhaseTwo(List<String> errors, List<XSKTopologyDataStructureModelWrapper> listOfWrappers) {
        TopologicalDepleter<XSKTopologyDataStructureModelWrapper> depleter = new TopologicalDepleter<>();
        TopologicalSorter<XSKTopologyDataStructureModelWrapper> sorter = new TopologicalSorter<>();

        List<XSKTopologyDataStructureModelWrapper> sortListOfWrapper = sorter.sort(listOfWrappers);
        try {
            List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(sortListOfWrapper, "");
            printErrors(errors, results, "Executing phase two of DB artefact creation.", ArtefactState.FAILED);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            errors.add(e.getMessage());
        }
    }

    private List<XSKTopologyDataStructureModelWrapper> constructListOfTableTypesModelWrappers(Connection connection, Map<String, XSKTopologyDataStructureModelWrapper> wrappers) {
        final Map<String, XSKDataStructureModel> dataStructureTableTypesModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE_TYPE).getDataStructureModels();
        final List<XSKTopologyDataStructureModelWrapper> listOfWrappers = new ArrayList<>();
        final IXSKDataStructureManager<XSKDataStructureHDBTableTypeModel> xskTableTypeManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE_TYPE);
        dataStructureTableTypesModels.values().forEach(tableTypeModel -> {
            HDBTableTypeSynchronizationArtefactType artefactType = new HDBTableTypeSynchronizationArtefactType();
            XSKTopologyDataStructureModelWrapper<XSKDataStructureHDBTableTypeModel> tableTypeWrapper = new XSKTopologyDataStructureModelWrapper(connection, xskTableTypeManagerService, tableTypeModel,
                artefactType, wrappers);
            listOfWrappers.add(tableTypeWrapper);
        });

        return listOfWrappers;
    }

    private List<XSKTopologyDataStructureModelWrapper> constructListOfSequenceModelWrapper(Connection connection, Map<String, XSKTopologyDataStructureModelWrapper> wrappers) {
        final Map<String, XSKDataStructureModel> dataStructureSequencesModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SEQUENCE).getDataStructureModels();
        final List<XSKTopologyDataStructureModelWrapper> listOfWrappers = new ArrayList<>();
        final IXSKDataStructureManager<XSKDataStructureHDBSequenceModel> xskSequenceManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SEQUENCE);
        dataStructureSequencesModels.values().forEach(sequenceModel -> {
            HDBSequenceSynchronizationArtefactType artefactType = new HDBSequenceSynchronizationArtefactType();
            XSKTopologyDataStructureModelWrapper<XSKDataStructureHDBSequenceModel> sequenceWrapper = new XSKTopologyDataStructureModelWrapper(connection, xskSequenceManagerService, sequenceModel,
                artefactType, wrappers);
            listOfWrappers.add(sequenceWrapper);
        });

        return listOfWrappers;
    }

    private List<XSKTopologyDataStructureModelWrapper> constructListOfTableModelWrappers(Connection connection, Map<String, XSKTopologyDataStructureModelWrapper> wrappers) {
        final Map<String, XSKDataStructureModel> dataStructureTablesModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE).getDataStructureModels();
        final List<XSKTopologyDataStructureModelWrapper> listOfWrappers = new ArrayList<>();
        final IXSKDataStructureManager<XSKDataStructureHDBTableModel> xskTableManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE);
        dataStructureTablesModels.values().forEach(tableModel -> {
            HDBTableSynchronizationArtefactType artefactType = new HDBTableSynchronizationArtefactType();
            XSKTopologyDataStructureModelWrapper<XSKDataStructureHDBTableModel> tableWrapper = new XSKTopologyDataStructureModelWrapper(connection, xskTableManagerService, tableModel, artefactType,
                wrappers);
            listOfWrappers.add(tableWrapper);
        });

        return listOfWrappers;
    }

    private void createArtefactsOnPhaseOne(List<String> errors, List<XSKTopologyDataStructureModelWrapper> listSchemaWrappers) {
        TopologicalDepleter<XSKTopologyDataStructureModelWrapper> depleter = new TopologicalDepleter<>();
        TopologicalSorter<XSKTopologyDataStructureModelWrapper> sorter = new TopologicalSorter<>();

        List<XSKTopologyDataStructureModelWrapper> sortedSchemaModelWrappers = sorter.sort(listSchemaWrappers);
        try {
            List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(sortedSchemaModelWrappers, "");
            printErrors(errors, results, "Executing phase one of DB artefact creation.", ArtefactState.FAILED);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            errors.add(e.getMessage());
        }
    }

    @NotNull
    private List<XSKTopologyDataStructureModelWrapper> constructListOfSchemaModelWrappers(Connection connection) {
        Map<String, XSKDataStructureModel> dataStructureSchemasModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SCHEMA).getDataStructureModels();
        final List<XSKTopologyDataStructureModelWrapper> listSchemaWrappers = new ArrayList<>();
        Map<String, XSKTopologyDataStructureModelWrapper> wrappers = new HashMap<>();
        IXSKDataStructureManager schemaModelManager = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SCHEMA);
        dataStructureSchemasModels.values().forEach(schemaModel -> {
            XSKTopologyDataStructureModelWrapper<XSKDataStructureHDBSchemaModel> schemaWrapper = new XSKTopologyDataStructureModelWrapper<>(connection, schemaModelManager, schemaModel,
                new HDBSchemaSynchronizationArtefactType(), wrappers);
            listSchemaWrappers.add(schemaWrapper);
        });

        return listSchemaWrappers;
    }

    @Override
    public void cleanup() throws XSKDataStructuresException {
        for (IXSKDataStructureManager dataStructureManager : managerServices.values()) {
            dataStructureManager.cleanup();
        }

        logger.trace("Done cleaning up XSK Data Structures.");
    }

    @Override
    public void clearCache() {
        this.managerServices.values().forEach(IXSKDataStructureManager::clearCache);
    }

    private String getRegistryPath(IResource resource) {
        String resourcePath = resource.getPath();
        return resourcePath.startsWith("/registry/public") ? resourcePath.substring("/registry/public".length()) : resourcePath;
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

    private String getType(String resourceExtension) {
        return parserTypes.get(resourceExtension);
    }

    private boolean isParsed(String location, String content, String resourceExtension)
        throws XSKDataStructuresException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        String modelType = getType(resourceExtension);
        if (modelType == null) {
            return false;
        }

        Class<XSKDataStructureModel> clazz = xskCoreParserService.getDataStructureClass(modelType);
        XSKDataStructureModel baseDataStructureModel = clazz.getDeclaredConstructor().newInstance();
        baseDataStructureModel.setLocation(location);
        baseDataStructureModel.setType(modelType);
        baseDataStructureModel.setHash(DigestUtils.md5Hex(content));

        return managerServices.get(baseDataStructureModel.getType()).existsArtifactMetadata(baseDataStructureModel);
    }

    private void printErrors(List<String> errors, List<XSKTopologyDataStructureModelWrapper> results, String flow, ISynchronizerArtefactType.ArtefactState state) {
        if (results.size() > 0) {
            for (XSKTopologyDataStructureModelWrapper result : results) {
                String errorMessage = String.format("Undepleted: %s in operation: %s", result.getId(), flow);
                logger.error(errorMessage);
                errors.add(errorMessage);
                applyArtefactState(result.getModel().getName(), result.getModel().getLocation(), result.getArtefactType(), state, errorMessage);
            }
        }
    }

    public void applyArtefactState(String artefactName, String artefactLocation, AbstractSynchronizationArtefactType type, ISynchronizerArtefactType.ArtefactState state, String message) {
        DATA_STRUCTURES_SYNCHRONIZER.applyArtefactState(artefactName, artefactLocation, type, state, message);
    }
}
