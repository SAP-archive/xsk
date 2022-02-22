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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKEnvironmentVariables;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.artefacts.HDBDDEntitySynchronizationArtefactType;
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
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.hdb.ds.service.parser.IXSKCoreParserService;
import com.sap.xsk.hdb.ds.service.parser.XSKCoreParserService;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.utils.XSKCommonsConstants;

public class XSKHDBCoreSynchronizationFacade implements IXSKHDBCoreSynchronizationFacade {

	private static final Logger logger = LoggerFactory.getLogger(XSKHDBCoreSynchronizationFacade.class);

	private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

	private Map<String, IXSKDataStructureManager> managerServices = XSKHDBModule.getManagerServices();

	private Map<String, XSKDataStructureParser> parserServices = XSKHDBModule.getParserServices();

	private Map<String, String> parserTypes = XSKHDBModule.getParserTypes();

	private IXSKCoreParserService xskCoreParserService = new XSKCoreParserService();

	private static final HDBTableSynchronizationArtefactType TABLE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBTableSynchronizationArtefactType();
	private static final HDBProcedureSynchronizationArtefactType PROCEDURE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBProcedureSynchronizationArtefactType();
	private static final HDBSchemaSynchronizationArtefactType SCHEMA_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBSchemaSynchronizationArtefactType();
	private static final HDBSequenceSynchronizationArtefactType SEQUENCE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBSequenceSynchronizationArtefactType();
	private static final HDBSynonymSynchronizationArtefactType SYNONYM_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBSynonymSynchronizationArtefactType();
	private static final HDBTableFunctionSynchronizationArtefactType TABLE_FUNCTION_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBTableFunctionSynchronizationArtefactType();
	private static final HDBScalarFunctionSynchronizationArtefactType SCALAR_FUNCTION_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBScalarFunctionSynchronizationArtefactType();
	private static final HDBTableTypeSynchronizationArtefactType TABLE_TYPE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBTableTypeSynchronizationArtefactType();
	private static final HDBViewSynchronizationArtefactType VIEW_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBViewSynchronizationArtefactType();
	private static final HDBDDEntitySynchronizationArtefactType HDBDD_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBDDEntitySynchronizationArtefactType();

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

	public XSKDataStructureModel parseDataStructureModel(String fileName, String path, String content, String workspace)
			throws SynchronizationException {
		String[] splitResourceName = fileName.split("\\.");
		String resourceExtension = "." + splitResourceName[splitResourceName.length - 1];
		String registryPath = path;
		String contentAsString = content;

		XSKDataStructureModel dataStructureModel = null;
		try {
			if (parserServices.containsKey(resourceExtension)
					&& !isParsed(registryPath, contentAsString, resourceExtension)) {
				XSKDataStructureParametersModel parametersModel = new XSKDataStructureParametersModel(resourceExtension,
						registryPath, contentAsString, workspace);
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
		return this.parseDataStructureModel(resource.getName(), getRegistryPath(resource), getContent(resource),
				XSKCommonsConstants.XSK_REGISTRY_PUBLIC);
	}

	@Override
	public void handleResourceSynchronization(IResource resource)
			throws SynchronizationException, XSKDataStructuresException {
		XSKDataStructureModel dataStructureModel = parseDataStructureModel(resource);
		if (dataStructureModel != null) {
			managerServices.get(dataStructureModel.getType()).synchronizeRuntimeMetadata(dataStructureModel); // 4. we synchronize the metadata
		}
	}

	@Override
	public void handleResourceSynchronization(String fileExtension, XSKDataStructureModel dataStructureModel)
			throws SynchronizationException, XSKDataStructuresException {
		managerServices.get(dataStructureModel.getType()).synchronizeRuntimeMetadata(dataStructureModel);
	}

	@Override
	public void updateEntities() {
		Map<String, XSKDataStructureModel> dataStructureCdsModels = managerServices.get(IXSKDataStructureModel.TYPE_HDBDD).getDataStructureModels();
		Map<String, XSKDataStructureModel> dataStructureTablesModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE).getDataStructureModels();
		Map<String, XSKDataStructureModel> dataStructureViewsModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_VIEW).getDataStructureModels();
		Map<String, XSKDataStructureModel> dataStructureProceduresModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_PROCEDURE).getDataStructureModels();
		Map<String, XSKDataStructureModel> dataStructureTableFunctionsModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION).getDataStructureModels();
		Map<String, XSKDataStructureModel> dataStructureScalarFunctionsModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SCALAR_FUNCTION).getDataStructureModels();
		Map<String, XSKDataStructureModel> dataStructureSchemasModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SCHEMA).getDataStructureModels();
		Map<String, XSKDataStructureModel> dataStructureSynonymModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SYNONYM).getDataStructureModels();
		Map<String, XSKDataStructureModel> dataStructureSequencesModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SEQUENCE).getDataStructureModels();
		Map<String, XSKDataStructureModel> dataStructureTableTypesModels = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE_TYPE).getDataStructureModels();

		if (dataStructureCdsModels.isEmpty() 
				&& dataStructureTablesModels.isEmpty() 
				&& dataStructureViewsModels.isEmpty()
				&& dataStructureProceduresModels.isEmpty() 
				&& dataStructureTableFunctionsModels.isEmpty()
				&& dataStructureSchemasModels.isEmpty() 
				&& dataStructureSynonymModels.isEmpty()
				&& dataStructureSequencesModels.isEmpty() 
				&& dataStructureScalarFunctionsModels.isEmpty()
				&& dataStructureTableTypesModels.isEmpty()) {

			logger.trace("No XSK Data Structures to update.");
			return;
		}

		Map<String, XSKDataStructureModel> XSK_DATA_STRUCTURE_MODELS = new HashMap<>();
		for (XSKDataStructureModel cds : dataStructureCdsModels.values()) {
			((XSKDataStructureCdsModel) cds).getTableModels().stream().forEach(tableModel -> XSK_DATA_STRUCTURE_MODELS.put(tableModel.getName(), tableModel));
			((XSKDataStructureCdsModel) cds).getTableTypeModels().stream().forEach(tableTypeModel -> XSK_DATA_STRUCTURE_MODELS.put(tableTypeModel.getName(), tableTypeModel));
		}
		XSK_DATA_STRUCTURE_MODELS.putAll(dataStructureTablesModels);
		XSK_DATA_STRUCTURE_MODELS.putAll(dataStructureViewsModels);
		XSK_DATA_STRUCTURE_MODELS.putAll(dataStructureProceduresModels);
		XSK_DATA_STRUCTURE_MODELS.putAll(dataStructureTableFunctionsModels);
		XSK_DATA_STRUCTURE_MODELS.putAll(dataStructureScalarFunctionsModels);
		XSK_DATA_STRUCTURE_MODELS.putAll(dataStructureSchemasModels);
		XSK_DATA_STRUCTURE_MODELS.putAll(dataStructureSynonymModels);
		XSK_DATA_STRUCTURE_MODELS.putAll(dataStructureSequencesModels);
		XSK_DATA_STRUCTURE_MODELS.putAll(dataStructureTableTypesModels);

		List<String> errors = new ArrayList<>();
		try {
			try (Connection connection = dataSource.getConnection()) {

				TopologicalSorter<XSKTopologyDataStructureModelWrapper> sorter = new TopologicalSorter<>();
				TopologicalDepleter<XSKTopologyDataStructureModelWrapper> depleter = new TopologicalDepleter<>();

				List<XSKTopologyDataStructureModelWrapper> list = new ArrayList<XSKTopologyDataStructureModelWrapper>();
				Map<String, XSKTopologyDataStructureModelWrapper> wrappers = new HashMap<String, XSKTopologyDataStructureModelWrapper>();
				for (XSKDataStructureModel model : XSK_DATA_STRUCTURE_MODELS.values()) {
					XSKTopologyDataStructureModelWrapper wrapper = new XSKTopologyDataStructureModelWrapper(connection,
							model, wrappers);
					list.add(wrapper);
				}

				// Topological sorting by dependencies
				list = sorter.sort(list);

				// Reverse the order
				Collections.reverse(list);

				boolean hdiOnly = Boolean.parseBoolean(Configuration.get(IXSKEnvironmentVariables.XSK_HDI_ONLY, "false"));
				if (!hdiOnly) {
					
					
					// ************** DROPPING ****************************************************************//

					// drop HDB Procedures
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_PROCEDURE_DROP.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_PROCEDURE_DROP.toString(), PROCEDURE_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}

					// drop HDB Table Functions
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_FUNCTION_DROP.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_FUNCTION_DROP.toString(), TABLE_FUNCTION_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}

					// drop HDB Scalar Functions
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_SCALAR_FUNCTION_DROP.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_SCALAR_FUNCTION_DROP.toString(), SCALAR_FUNCTION_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}

					// drop HDB Synonym in a reverse order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_SYNONYM_DROP.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_SYNONYM_DROP.toString(), SYNONYM_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
					
					// drop HDB Views in a reverse order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_VIEW_DROP.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_VIEW_DROP.toString(), VIEW_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
					
					// drop HDB Tables in a reverse order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_DROP.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_DROP.toString(), TABLE_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}

					// drop HDB Table Types (HDB Structures)
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_TYPE_DROP.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_TYPE_DROP.toString(), TABLE_TYPE_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
					
					// drop HDB Schemas
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_SCHEMA_DROP.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_SCHEMA_DROP.toString(), SCHEMA_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
					
					// Return back to the sorted order
					Collections.reverse(list);

					
					// ************** CREATING *************************************************************************//
					
					// process HDB Schemas in the proper order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_SCHEMA_CREATE.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_SCHEMA_CREATE.toString(), SCHEMA_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
					
					// process HDB Table Types (structures) in the proper order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_TYPE_CREATE.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_TYPE_CREATE.toString(), TABLE_TYPE_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
					
					
					// process HDB Tables in the proper order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_CREATE.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_CREATE.toString(), TABLE_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
					
					// process HDB Views in the proper order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_VIEW_CREATE.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_VIEW_CREATE.toString(), VIEW_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
					
					// process HDB Synonym in the proper order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_SYNONYM_CREATE.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_SYNONYM_CREATE.toString(), SYNONYM_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
					
					// process HDB Sequences in the proper order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_SEQUENCE_CREATE.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_SEQUENCE_CREATE.toString(), SEQUENCE_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
					
					// process HDB Procedures in the proper order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_PROCEDURE_CREATE.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_PROCEDURE_CREATE.toString(), PROCEDURE_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}

					// process HDB Table Functions in the proper order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_FUNCTION_CREATE.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_TABLE_FUNCTION_CREATE.toString(), TABLE_FUNCTION_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
					
					// process HDB Scalar Functions in the proper order
					try {
						List<XSKTopologyDataStructureModelWrapper> results = depleter.deplete(list, XSKTopologyDataStructureModelEnum.EXECUTE_SCALAR_FUNCTION_CREATE.toString());
						printErrors(errors, results, XSKTopologyDataStructureModelEnum.EXECUTE_SCALAR_FUNCTION_CREATE.toString(), SCALAR_FUNCTION_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED_DELETE);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						errors.add(e.getMessage());
					}
				}
			}
		} catch (SQLException e) {
			logger.error(concatenateListOfStrings(errors), e);
		}
	}

	private void putCdsModelTableTypes(Map<String, XSKDataStructureModel> dataStructureCdsModel,
			Map<String, XSKDataStructureModel> dataStructureTableTypesModel) {
		if (!dataStructureCdsModel.isEmpty()) {
			Collection<XSKDataStructureCdsModel> dataStructureCdsModels = dataStructureCdsModel.values().stream()
					.map(cds -> (XSKDataStructureCdsModel) cds).collect(Collectors.toList());
			dataStructureCdsModels.forEach(cds -> {
				if (!cds.getTableTypeModels().isEmpty()) {
					cds.getTableTypeModels().forEach(tableType -> {
						dataStructureTableTypesModel.put(tableType.getName(), tableType);
					});
				}
			});
		}
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
		return resourcePath.startsWith("/registry/public") ? resourcePath.substring("/registry/public".length())
				: resourcePath;
	}

	private String getContent(IResource resource) throws SynchronizationException {
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

	private String getType(String resourceExtension) {
		return parserTypes.get(resourceExtension);
	}

	private boolean isParsed(String location, String content, String resourceExtension)
			throws XSKDataStructuresException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {

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

	private void printErrors(List<String> errors, List<XSKTopologyDataStructureModelWrapper> results, String flow,
			AbstractSynchronizationArtefactType artefact, ISynchronizerArtefactType.ArtefactState state) {
		if (results.size() > 0) {
			for (XSKTopologyDataStructureModelWrapper result : results) {
				String errorMessage = String.format("Undepleted: %s in operation: %s", result.getId(), flow);
				logger.error(errorMessage);
				errors.add(errorMessage);
				applyArtefactState(result.getModel().getName(), result.getModel().getLocation(), artefact, state, errorMessage);
			}
		}
	}
	
	private static final XSKDataStructuresSynchronizer DATA_STRUCTURES_SYNCHRONIZER = new XSKDataStructuresSynchronizer();

	public void applyArtefactState(String artefactName, String artefactLocation, AbstractSynchronizationArtefactType type, ISynchronizerArtefactType.ArtefactState state, String message) {
		DATA_STRUCTURES_SYNCHRONIZER.applyArtefactState(artefactName, artefactLocation, type, state, message);
	}
}
