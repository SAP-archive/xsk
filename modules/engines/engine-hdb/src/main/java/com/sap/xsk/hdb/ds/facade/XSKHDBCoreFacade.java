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
package com.sap.xsk.hdb.ds.facade;

import static java.text.MessageFormat.format;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKEnvironmentVariables;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
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
import com.sap.xsk.hdb.ds.service.XSKDataStructureTopologicalSorter;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.hdb.ds.service.parser.IXSKCoreParserService;
import com.sap.xsk.hdb.ds.service.parser.XSKCoreParserService;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.repository.api.IResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKHDBCoreFacade implements IXSKHDBCoreFacade {

  private static final Logger logger = LoggerFactory.getLogger(XSKHDBCoreFacade.class);

  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  private Map<String, IXSKDataStructureManager> managerServices = XSKHDBModule.getManagerServices();

  private Map<String, XSKDataStructureParser> parserServices = XSKHDBModule.getParserServices();

  private Map<String, String> parserTypes = XSKHDBModule.getParserTypes();

  private IXSKCoreParserService xskCoreParserService = new XSKCoreParserService();

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

    XSKDataStructureModel dataStructureModel;
    try {
      if(!parserServices.containsKey(resourceExtension) || isParsed(registryPath, contentAsString, resourceExtension)) {
        return null;
      }

      XSKDataStructureParametersModel parametersModel =
          new XSKDataStructureParametersModel(resourceExtension, registryPath, contentAsString, workspace);
      dataStructureModel = xskCoreParserService.parseDataStructure(parametersModel);

      if (dataStructureModel == null) {
        return null;
      }
    } catch (ReflectiveOperationException e) {
      logger.error("Preparse hash check failed for path " + registryPath);
      logger.error(e.getMessage());
      return null;
    } catch (XSKDataStructuresException e) {
      logger.error("Synchronized artifact with path " + registryPath + " is not valid");
      logger.error(e.getMessage());
      return null;
    } catch (XSKArtifactParserException e) {
      logger.error(e.getMessage());
      return null;
    } catch (Exception e) {
      throw new SynchronizationException(e);
    }
    dataStructureModel.setLocation(registryPath);
    return dataStructureModel;
  }

  public XSKDataStructureModel parseDataStructureModel(IResource resource) throws SynchronizationException {
    return this.parseDataStructureModel(resource.getName(), getRegistryPath(resource), getContent(resource), XSKCommonsConstants.XSK_REGISTRY_PUBLIC);
  }

  @Override
  public void handleResourceSynchronization(IResource resource) throws SynchronizationException, XSKDataStructuresException {
    XSKDataStructureModel dataStructureModel = parseDataStructureModel(resource);
    if(dataStructureModel == null) {
      return;
    }
    managerServices.get(dataStructureModel.getType()).synchronizeRuntimeMetadata(dataStructureModel); // 4. we synchronize the metadata
  }

  @Override
  public void handleResourceSynchronization(String fileExtension, XSKDataStructureModel dataStructureModel)
      throws SynchronizationException, XSKDataStructuresException {
    managerServices.get(dataStructureModel.getType()).synchronizeRuntimeMetadata(dataStructureModel);
  }

  @Override
  public void updateEntities() {
    Map<String, XSKDataStructureModel> dataStructureCdsModel = managerServices.get(IXSKDataStructureModel.TYPE_HDBDD)
        .getDataStructureModels();
    Map<String, XSKDataStructureModel> dataStructureTablesModel = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE)
        .getDataStructureModels();
    Map<String, XSKDataStructureModel> dataStructureViewsModel = managerServices.get(IXSKDataStructureModel.TYPE_HDB_VIEW)
        .getDataStructureModels();
    Map<String, XSKDataStructureModel> dataStructureProceduresModel = managerServices.get(IXSKDataStructureModel.TYPE_HDB_PROCEDURE)
        .getDataStructureModels();
    Map<String, XSKDataStructureModel> dataStructureTableFunctionsModel = managerServices
        .get(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION).getDataStructureModels();
    Map<String, XSKDataStructureModel> dataStructureScalarFunctionsModel = managerServices
        .get(IXSKDataStructureModel.TYPE_HDB_SCALARFUNCTION).getDataStructureModels();
    Map<String, XSKDataStructureModel> dataStructureSchemasModel = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SCHEMA)
        .getDataStructureModels();
    Map<String, XSKDataStructureModel> dataStructureSynonymModel = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SYNONYM)
        .getDataStructureModels();
    Map<String, XSKDataStructureModel> dataStructureSequencesModel = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SEQUENCE)
        .getDataStructureModels();
    Map<String, XSKDataStructureModel> dataStructureTableTypesModel = managerServices.get(IXSKDataStructureModel.TYPE_HDB_TABLE_TYPE)
        .getDataStructureModels();

    if (dataStructureCdsModel.isEmpty()
        && dataStructureTablesModel.isEmpty()
        && dataStructureViewsModel.isEmpty()
        && dataStructureProceduresModel.isEmpty()
        && dataStructureTableFunctionsModel.isEmpty()
        && dataStructureSchemasModel.isEmpty()
        && dataStructureSynonymModel.isEmpty()
        && dataStructureSequencesModel.isEmpty()
        && dataStructureScalarFunctionsModel.isEmpty()
        && dataStructureTableTypesModel.isEmpty()) {

      logger.trace("No XSK Data Structures to update.");
      return;
    }

    List<String> errors = new ArrayList<>();
    try {
      try (Connection connection = dataSource.getConnection()) {

        List<String> sorted = new ArrayList<>();
        Map<String, XSKDataStructureModel> combined = new HashMap<>();
        combined.putAll(dataStructureTablesModel);
        combined.putAll(dataStructureViewsModel);
        putCdsModelTableTypes(dataStructureCdsModel, dataStructureTableTypesModel);

        // topology sort of dependencies
        List<String> external = new ArrayList<>();
        try {
          XSKDataStructureTopologicalSorter.sort(combined, sorted, external);
          logger.trace("topological sorting");
          for (String location : sorted) {
            logger.trace("artifact: " + location);
          }
        } catch (Exception e) {
          logger.error(e.getMessage(), e);
          errors.add(e.getMessage());
          sorted.clear();
        }

        // something wrong happened with the sorting - probably cyclic dependencies
        // we go for the back-up list and try to apply what would succeed
        // Probably there are cyclic dependencies!
        sorted.addAll(dataStructureCdsModel.keySet());
        sorted.addAll(dataStructureProceduresModel.keySet());
        sorted.addAll(dataStructureTableFunctionsModel.keySet());
        sorted.addAll(dataStructureSchemasModel.keySet());
        sorted.addAll(dataStructureSynonymModel.keySet());
        sorted.addAll(dataStructureSequencesModel.keySet());
        sorted.addAll(dataStructureScalarFunctionsModel.keySet());
        sorted.addAll(dataStructureTableTypesModel.keySet());

        boolean hdiOnly = Boolean.parseBoolean(Configuration.get(IXSKEnvironmentVariables.XSK_HDI_ONLY, "false"));
        if (!hdiOnly) {
          //drop HDB Sequences
                   /* List<XSKDataStructureHDBSequenceModel> hdbSequencesToUpdate = new ArrayList<>();
                    for (int i = sorted.size() - 1; i >= 0; i--) {
                        String dsName = sorted.get(i);
                        XSKDataStructureHDBSequenceModel model = (XSKDataStructureHDBSequenceModel) dataStructureSequencesModel.get(dsName);
                        if (model != null) {
                            hdbSequencesToUpdate.add(model);
                        }
                    }

                    IXSKDataStructureManager<XSKDataStructureModel> xskSequencesManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_SEQUENCE);
                    for (XSKDataStructureHDBSequenceModel sequenceToUpdate :
                            hdbSequencesToUpdate) {
                        xskSequencesManagerService.dropDataStructure(connection, sequenceToUpdate);
                    } */

          // ************** DROPPING ****************************************************************//
          // drop HDB Procedures
          List<XSKDataStructureHDBProcedureModel> hdbProceduresToUpdate = new ArrayList<>();
          for (int i = sorted.size() - 1; i >= 0; i--) {
            String dsName = sorted.get(i);
            XSKDataStructureHDBProcedureModel model = (XSKDataStructureHDBProcedureModel) dataStructureProceduresModel.get(dsName);
            if (model != null) {
              hdbProceduresToUpdate.add(model);
            }
          }
          IXSKDataStructureManager<XSKDataStructureModel> xskProceduresManagerService = managerServices
              .get(IXSKDataStructureModel.TYPE_HDB_PROCEDURE);
          for (XSKDataStructureHDBProcedureModel procedureToUpdate :
              hdbProceduresToUpdate) {
            xskProceduresManagerService.dropDataStructure(connection, procedureToUpdate);
          }

          // drop HDB Table Functions
          List<XSKDataStructureHDBTableFunctionModel> hdbTableFunctionsToUpdate = new ArrayList<>();
          for (int i = sorted.size() - 1; i >= 0; i--) {
            String dsName = sorted.get(i);
            XSKDataStructureHDBTableFunctionModel model = (XSKDataStructureHDBTableFunctionModel) dataStructureTableFunctionsModel
                .get(dsName);
            if (model != null) {
              hdbTableFunctionsToUpdate.add(model);
            }
          }
          IXSKDataStructureManager<XSKDataStructureModel> xskTableFunctionManagerService = managerServices
              .get(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION);
          for (XSKDataStructureHDBTableFunctionModel tableFunctionModel :
              hdbTableFunctionsToUpdate) {
            xskTableFunctionManagerService.dropDataStructure(connection, tableFunctionModel);
          }

          // drop HDB Scalar Functions
          List<XSKDataStructureHDBTableFunctionModel> hdbScalarFunctionsToUpdate = new ArrayList<>();
          for (int i = sorted.size() - 1; i >= 0; i--) {
            String dsName = sorted.get(i);
            XSKDataStructureHDBTableFunctionModel model = (XSKDataStructureHDBTableFunctionModel) dataStructureScalarFunctionsModel
                .get(dsName);
            if (model != null) {
              hdbScalarFunctionsToUpdate.add(model);
            }
          }
          IXSKDataStructureManager<XSKDataStructureModel> xskScalarFunctionManagerService = managerServices
              .get(IXSKDataStructureModel.TYPE_HDB_SCALARFUNCTION);
          for (XSKDataStructureHDBTableFunctionModel tableFunctionModel :
              hdbScalarFunctionsToUpdate) {
            xskScalarFunctionManagerService.dropDataStructure(connection, tableFunctionModel);
          }

          // drop Synonym in a reverse order
          IXSKDataStructureManager<XSKDataStructureModel> xskSynonymManagerService = managerServices
              .get(IXSKDataStructureModel.TYPE_HDB_SYNONYM);
          for (int i = sorted.size() - 1; i >= 0; i--) {
            String dsName = sorted.get(i);
            XSKDataStructureHDBSynonymModel model = (XSKDataStructureHDBSynonymModel) dataStructureSynonymModel.get(dsName);
            try {
              if (model != null) {
                xskSynonymManagerService.dropDataStructure(connection, model);
              }
            } catch (Exception e) {
              logger.error(e.getMessage(), e);
              errors.add(e.getMessage());
            }
          }

          // drop HDB Views in a reverse order
          IXSKDataStructureManager<XSKDataStructureModel> xskViewManagerService = managerServices.get(IXSKDataStructureModel.TYPE_HDB_VIEW);
          for (int i = sorted.size() - 1; i >= 0; i--) {
            String dsName = sorted.get(i);
            XSKDataStructureHDBViewModel model = (XSKDataStructureHDBViewModel) dataStructureViewsModel.get(dsName);
            try {
              if (model != null) {
                xskViewManagerService.dropDataStructure(connection, model);
              }
            } catch (Exception e) {
              logger.error(e.getMessage(), e);
              errors.add(e.getMessage());
            }
          }

          // drop Tables in a reverse order
          IXSKDataStructureManager<XSKDataStructureModel> xskTableManagerService = managerServices
              .get(IXSKDataStructureModel.TYPE_HDB_TABLE);
          for (int i = sorted.size() - 1; i >= 0; i--) {
            String dsName = sorted.get(i);
            XSKDataStructureHDBTableModel model = (XSKDataStructureHDBTableModel) dataStructureTablesModel.get(dsName);
            try {
              boolean caseSensitive = Boolean
                  .parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
              if (model != null) {
                String modelName = model.getName();
                if (caseSensitive) {
                  modelName = "\"" + modelName + "\"";
                }
                if (SqlFactory.getNative(connection).exists(connection, modelName)) {
                  if (SqlFactory.getNative(connection).count(connection, modelName) == 0) {
                    xskTableManagerService.dropDataStructure(connection, model);
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

          // drop HDB Table Types (HDB Structures)
          IXSKDataStructureManager<XSKDataStructureModel> xskTableTypeManagerService = managerServices
              .get(IXSKDataStructureModel.TYPE_HDB_TABLE_TYPE);
          for (int i = sorted.size() - 1; i >= 0; i--) {
            String dsName = sorted.get(i);
            XSKDataStructureHDBTableTypeModel model = (XSKDataStructureHDBTableTypeModel) dataStructureTableTypesModel.get(dsName);
            try {
              if (model != null) {
                xskTableTypeManagerService.dropDataStructure(connection, model);
              }
            } catch (Exception e) {
              logger.error(e.getMessage(), e);
              errors.add(e.getMessage());
            }
          }
          // drop HDB Schemas
          IXSKDataStructureManager<XSKDataStructureModel> xskSchemaManagerService = managerServices
              .get(IXSKDataStructureModel.TYPE_HDB_SCHEMA);
          for (int i = sorted.size() - 1; i >= 0; i--) {
            String dsName = sorted.get(i);
            XSKDataStructureHDBSchemaModel model = (XSKDataStructureHDBSchemaModel) dataStructureSchemasModel.get(dsName);
            if (model != null) {
              xskSchemaManagerService.dropDataStructure(connection, model);
            }
          }

          // ************** CREATING *************************************************************************//
          // process schemas in the proper order
          for (String dsName : sorted) {
            XSKDataStructureHDBSchemaModel model = (XSKDataStructureHDBSchemaModel) dataStructureSchemasModel.get(dsName);
            try {
              if (model != null) {
                if (!SqlFactory.getNative(connection).exists(connection, model.getName())) {
                  xskSchemaManagerService.createDataStructure(connection, model);
                } else {
                  logger.warn(format("Schema [{0}] already exists during the update process", dsName));
                }
              }
            } catch (Exception e) {
              logger.error(e.getMessage(), e);
              errors.add(e.getMessage());
            }
          }

          //process table types (structures) in the proper order
          for (String dsName : sorted) {
            XSKDataStructureHDBTableTypeModel model = (XSKDataStructureHDBTableTypeModel) dataStructureTableTypesModel.get(dsName);
            try {
              if (model != null) {
                if (!SqlFactory.getNative(connection).exists(connection, model.getName())) {
                  xskTableTypeManagerService.createDataStructure(connection, model);
                } else {
                  logger.warn(format("Table Type [{0}] already exists during the update process", dsName));
                }
              }
            } catch (Exception e) {
              logger.error(e.getMessage(), e);
              errors.add(e.getMessage());
            }
          }

          // process Tables in the proper order
          for (String dsName : sorted) {
            XSKDataStructureHDBTableModel model = (XSKDataStructureHDBTableModel) dataStructureTablesModel.get(dsName);
            try {
              if (model != null) {
                String escapedName = XSKHDBUtils.escapeArtifactName(connection, model.getName(), model.getSchema());
                if (!SqlFactory.getNative(connection).exists(connection, escapedName)) {
                  xskTableManagerService.createDataStructure(connection, model);
                } else {
                  logger.warn(format("Table [{0}] already exists during the update process", dsName));
                  if (SqlFactory.getNative(connection).count(connection, escapedName) != 0) {
                    xskTableManagerService.updateDataStructure(connection, model);
                  }
                }
              }
            } catch (Exception e) {
              logger.error(e.getMessage(), e);
              errors.add(e.getMessage());
            }
          }

          // process views in the proper order
          for (String dsName : sorted) {
            XSKDataStructureHDBViewModel model = (XSKDataStructureHDBViewModel) dataStructureViewsModel.get(dsName);
            try {
              if (model != null) {
                if (!SqlFactory.getNative(connection).exists(connection, model.getName())) {
                  xskViewManagerService.createDataStructure(connection, model);
                } else {
                  logger.warn(format("View [{0}] already exists during the update process", dsName));
                }
              }
            } catch (Exception e) {
              logger.error(e.getMessage(), e);
              errors.add(e.getMessage());
            }
          }

          // process Synonym in the proper order
          for (String dsName : sorted) {
            XSKDataStructureHDBSynonymModel model = (XSKDataStructureHDBSynonymModel) dataStructureSynonymModel.get(dsName);
            try {
              if (model != null) {
                if (!SqlFactory.getNative(connection).exists(connection, model.getName())) {
                  xskSynonymManagerService.createDataStructure(connection, model);
                } else {
                  logger.warn(format("Synonym [{0}] already exists during the update process", dsName));
                }
              }
            } catch (Exception e) {
              logger.error(e.getMessage(), e);
              errors.add(e.getMessage());
            }
          }

          // process Cds models in proper order
          IXSKDataStructureManager<XSKDataStructureModel> xskHdbddManagerService = managerServices
              .get(IXSKDataStructureModel.TYPE_HDBDD);
          for (String dsName : sorted) {
            XSKDataStructureCdsModel entitesModel = (XSKDataStructureCdsModel) dataStructureCdsModel.get(dsName);
            try {
              xskHdbddManagerService.createDataStructure(connection, entitesModel);
            } catch (Exception e) {
              logger.error(e.getMessage(), e);
              errors.add(e.getMessage());
            }
          }

          // process sequences in the proper order
          IXSKDataStructureManager<XSKDataStructureModel> xskSequenceManagerService = managerServices
              .get(IXSKDataStructureModel.TYPE_HDB_SEQUENCE);
          for (String dsName : sorted) {
            XSKDataStructureHDBSequenceModel model = (XSKDataStructureHDBSequenceModel) dataStructureSequencesModel.get(dsName);
            try {
              if (model != null) {
                if (!SqlFactory.getNative(connection)
                    .exists(connection, model.getSchema(), model.getName(), DatabaseArtifactTypes.SEQUENCE)) {
                  xskSequenceManagerService.createDataStructure(connection, model);
                } else {
                  xskSequenceManagerService.updateDataStructure(connection, model);

                }
              }
            } catch (Exception e) {
              logger.error(e.getMessage(), e);
              errors.add(e.getMessage());
            }
          }

          // process hdbProcedures
          for (XSKDataStructureHDBProcedureModel procedureModel :
              hdbProceduresToUpdate) {
            xskProceduresManagerService.createDataStructure(connection, procedureModel);
          }

          // process hdbTableFunctions
          for (XSKDataStructureHDBTableFunctionModel tableFunctionModel :
              hdbTableFunctionsToUpdate) {
            xskTableFunctionManagerService.createDataStructure(connection, tableFunctionModel);
          }

          // process hdbScalarFunctions
          for (XSKDataStructureHDBTableFunctionModel tableFunctionModel :
              hdbScalarFunctionsToUpdate) {
            xskScalarFunctionManagerService.createDataStructure(connection, tableFunctionModel);
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
          .map(cds -> (XSKDataStructureCdsModel) cds).collect(
              Collectors.toList());
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
    for (IXSKDataStructureManager dataStructureManager :
        managerServices.values()) {
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
}
