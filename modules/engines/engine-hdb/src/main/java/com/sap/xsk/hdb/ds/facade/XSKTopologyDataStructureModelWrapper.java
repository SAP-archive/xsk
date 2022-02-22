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

import static java.text.MessageFormat.format;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.artefacts.HDBSequenceSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBSynonymSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBTableSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBTableTypeSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBViewSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureDependencyModel;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureCdsModel;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.model.hdbscalarfunction.XSKDataStructureHDBScalarFunctionModel;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.OperationNotSupportedException;
import org.eclipse.dirigible.commons.api.topology.ITopologicallyDepletable;
import org.eclipse.dirigible.commons.api.topology.ITopologicallySortable;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.AbstractSynchronizationArtefactType;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKTopologyDataStructureModelWrapper implements ITopologicallySortable, ITopologicallyDepletable {

  private static final Logger logger = LoggerFactory.getLogger(XSKTopologyDataStructureModelWrapper.class);

  private Map<String, IXSKDataStructureManager> managerServices = XSKHDBModule.getManagerServices();

  private final IXSKDataStructureManager<XSKDataStructureModel> xskProceduresManagerService = managerServices.get(
      IXSKDataStructureModel.TYPE_HDB_PROCEDURE);
  private final IXSKDataStructureManager<XSKDataStructureModel> xskTableFunctionManagerService = managerServices.get(
      IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION);
  private final IXSKDataStructureManager<XSKDataStructureModel> xskScalarFunctionManagerService = managerServices.get(
      IXSKDataStructureModel.TYPE_HDB_SCALAR_FUNCTION);
  private final IXSKDataStructureManager<XSKDataStructureModel> xskSynonymManagerService = managerServices.get(
      IXSKDataStructureModel.TYPE_HDB_SYNONYM);
  private final IXSKDataStructureManager<XSKDataStructureModel> xskViewManagerService = managerServices.get(
      IXSKDataStructureModel.TYPE_HDB_VIEW);
  private final IXSKDataStructureManager<XSKDataStructureModel> xskTableManagerService = managerServices.get(
      IXSKDataStructureModel.TYPE_HDB_TABLE);
  private final IXSKDataStructureManager<XSKDataStructureModel> xskTableTypeManagerService = managerServices.get(
      IXSKDataStructureModel.TYPE_HDB_TABLE_TYPE);
  private final IXSKDataStructureManager<XSKDataStructureModel> xskSchemaManagerService = managerServices.get(
      IXSKDataStructureModel.TYPE_HDB_SCHEMA);
  private final IXSKDataStructureManager<XSKDataStructureModel> xskHdbddManagerService = managerServices.get(
      IXSKDataStructureModel.TYPE_HDBDD);
  private final IXSKDataStructureManager<XSKDataStructureModel> xskSequenceManagerService = managerServices.get(
      IXSKDataStructureModel.TYPE_HDB_SEQUENCE);

  private static final HDBTableSynchronizationArtefactType TABLE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBTableSynchronizationArtefactType();
  private static final HDBTableTypeSynchronizationArtefactType TABLE_TYPE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBTableTypeSynchronizationArtefactType();
  private static final HDBViewSynchronizationArtefactType VIEW_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBViewSynchronizationArtefactType();
  private static final HDBSynonymSynchronizationArtefactType SYNONYM_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBSynonymSynchronizationArtefactType();
  private static final HDBSequenceSynchronizationArtefactType SEQUENCE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBSequenceSynchronizationArtefactType();

  private Connection connection;

  private XSKDataStructureModel model;

  private Map<String, XSKTopologyDataStructureModelWrapper> wrappers;

  private boolean caseSensitive = Boolean.parseBoolean(
      Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));

  public XSKTopologyDataStructureModelWrapper(Connection connection, XSKDataStructureModel model,
      Map<String, XSKTopologyDataStructureModelWrapper> wrappers) {
    this.connection = connection;
    this.model = model;
    this.wrappers = wrappers;
    this.wrappers.put(getId(), this);
  }

  public XSKDataStructureModel getModel() {
    return model;
  }

  @Override
  public String getId() {
    return this.model.getName();
  }

  @Override
  public List<ITopologicallySortable> getDependencies() {
    List<ITopologicallySortable> dependencies = new ArrayList<ITopologicallySortable>();
    for (XSKDataStructureDependencyModel dependency : this.model.getDependencies()) {
      String dependencyName = dependency.getName();
      if (!wrappers.containsKey(dependencyName)) {
        logger.warn("Dependency is not present in this cycle: " + dependencyName);
      } else {
        dependencies.add(wrappers.get(dependencyName));
      }
    }
    return dependencies;
  }

  @Override
  public boolean complete(String flow) {
    try {
      XSKTopologyDataStructureModelEnum flag = XSKTopologyDataStructureModelEnum.valueOf(flow);
      switch (flag) {
        case EXECUTE_PROCEDURE_DROP:
          if (model instanceof XSKDataStructureHDBProcedureModel) {
            xskProceduresManagerService.dropDataStructure(connection, this.model);
          }
          break;
        case EXECUTE_SCALAR_FUNCTION_DROP:
        case EXECUTE_TABLE_FUNCTION_DROP:
          if (model instanceof XSKDataStructureHDBTableFunctionModel) {
            xskTableFunctionManagerService.dropDataStructure(connection, this.model);
          }
          break;
        case EXECUTE_SYNONYM_DROP:
          if (model instanceof XSKDataStructureHDBSynonymModel) {
            xskSynonymManagerService.dropDataStructure(connection, this.model);
          }
          break;
        case EXECUTE_VIEW_DROP:
          if (model instanceof XSKDataStructureHDBViewModel) {
            xskViewManagerService.dropDataStructure(connection, this.model);
          }
          break;
        case EXECUTE_TABLE_DROP:
          if (model instanceof XSKDataStructureHDBTableModel) {
            String modelName = model.getName();
            if (caseSensitive) {
              modelName = "\"" + modelName + "\"";
            }
            if (SqlFactory.getNative(connection).exists(connection, modelName)) {
              if (SqlFactory.getNative(connection).count(connection, modelName) == 0) {
                xskTableManagerService.dropDataStructure(connection, this.model);
              } else {
                String message = format("Table [{0}] cannot be deleted during the update process, because it is not empty",
                    this.model.getName());
                logger.warn(message);
                applyArtefactState(this.model.getName(), this.model.getLocation(), TABLE_SYNCHRONIZATION_ARTEFACT_TYPE,
                    ArtefactState.FAILED, message);
              }
            }
          }
          break;
        case EXECUTE_TABLE_TYPE_DROP:
          if (model instanceof XSKDataStructureHDBTableTypeModel) {
            xskTableTypeManagerService.dropDataStructure(connection, this.model);
          }
          break;
        case EXECUTE_SCHEMA_DROP:
          if (model instanceof XSKDataStructureHDBSchemaModel) {
            xskSchemaManagerService.dropDataStructure(connection, this.model);
          }
          break;
        case EXECUTE_SCHEMA_CREATE:
          if (model instanceof XSKDataStructureHDBSchemaModel) {
            xskSchemaManagerService.createDataStructure(connection, this.model);
          }
          break;
        case EXECUTE_TABLE_TYPE_CREATE:
          if (model instanceof XSKDataStructureHDBTableTypeModel) {
            processTableType((XSKDataStructureHDBTableTypeModel) model);
          }
          break;
        case EXECUTE_TABLE_CREATE:
          if (model instanceof XSKDataStructureHDBTableModel) {
            String escapedName = XSKHDBUtils.escapeArtifactName(connection, model.getName(), model.getSchema());
            if (!SqlFactory.getNative(connection).exists(connection, escapedName)) {
              xskTableManagerService.createDataStructure(connection, this.model);
            } else {
              String message = format("Table [{0}] in schema [{1}] already exists during the update process", this.model.getName(),
                  this.model.getSchema());
              logger.warn(message);
              if (SqlFactory.getNative(connection).count(connection, escapedName) == 0) {
                try {
                  xskTableManagerService.updateDataStructure(connection, this.model);
                } catch (OperationNotSupportedException e) {
                  logger.error(e.getMessage(), e);
                  applyArtefactState(this.model.getName(), this.model.getLocation(), TABLE_SYNCHRONIZATION_ARTEFACT_TYPE,
                      ArtefactState.FAILED, e.getMessage());
                }
              } else {
                message = format("Table [{0}] in schema [{1}] already exists and it is not empty during the update process",
                    this.model.getName(), this.model.getSchema());
                logger.warn(message);
                applyArtefactState(this.model.getName(), this.model.getLocation(), TABLE_SYNCHRONIZATION_ARTEFACT_TYPE,
                    ArtefactState.FAILED, message);
              }
            }
          }
          break;
        case EXECUTE_VIEW_CREATE:
          if (model instanceof XSKDataStructureHDBViewModel) {
            if (!SqlFactory.getNative(connection).exists(connection, model.getSchema(), model.getName(), DatabaseArtifactTypes.VIEW)) {
              xskViewManagerService.createDataStructure(connection, this.model);
            } else {
              String message = format("View [{0}] in schema [{1}] already exists during the update process", this.model.getName(),
                  this.model.getSchema());
              logger.warn(message);
              applyArtefactState(this.model.getName(), this.model.getLocation(), VIEW_SYNCHRONIZATION_ARTEFACT_TYPE, ArtefactState.FAILED,
                  message);
            }
          }
          break;
        case EXECUTE_SYNONYM_CREATE:
          if (model instanceof XSKDataStructureHDBSynonymModel) {
            if (!SqlFactory.getNative(connection).exists(connection, model.getSchema(), model.getName(), DatabaseArtifactTypes.SYNONYM)) {
              xskSynonymManagerService.createDataStructure(connection, this.model);
            } else {
              String message = format("Synonym [{0}] in schema [{1}] already exists during the update process", this.model.getName(),
                  this.model.getSchema());
              logger.warn(message);
              applyArtefactState(this.model.getName(), this.model.getLocation(), SYNONYM_SYNCHRONIZATION_ARTEFACT_TYPE,
                  ArtefactState.FAILED, message);
            }
          }
          break;
        case EXECUTE_SEQUENCE_CREATE:
          if (model instanceof XSKDataStructureHDBSequenceModel) {
            if (!SqlFactory.getNative(connection)
                .exists(connection, model.getSchema(), this.model.getName(), DatabaseArtifactTypes.SEQUENCE)) {
              xskSequenceManagerService.createDataStructure(connection, model);
            } else {
              try {
                xskSequenceManagerService.updateDataStructure(connection, this.model);
              } catch (OperationNotSupportedException e) {
                logger.error(e.getMessage(), e);
                applyArtefactState(this.model.getName(), this.model.getLocation(), SEQUENCE_SYNCHRONIZATION_ARTEFACT_TYPE,
                    ArtefactState.FAILED, e.getMessage());
              }
            }
          }
          break;
        case EXECUTE_PROCEDURE_CREATE:
          if (model instanceof XSKDataStructureHDBProcedureModel) {
            xskProceduresManagerService.createDataStructure(connection, this.model);
          }
          break;
        case EXECUTE_SCALAR_FUNCTION_CREATE:
        case EXECUTE_TABLE_FUNCTION_CREATE:
          if (model instanceof XSKDataStructureHDBTableFunctionModel) {
            xskTableFunctionManagerService.createDataStructure(connection, this.model);
          }
          break;

        default:
          throw new UnsupportedOperationException(flow);
      }
      return true;
    } catch (SQLException e) {
      logger.warn("Failed on trying to complete the artefact: " + e.getMessage());
      return false;
    }
  }

  private void processTableType(XSKDataStructureHDBTableTypeModel tableType) throws SQLException {
    if (!SqlFactory.getNative(connection)
        .exists(connection, tableType.getSchema(), tableType.getName(), DatabaseArtifactTypes.TABLE_TYPE)) {

      xskTableTypeManagerService.createDataStructure(connection, tableType);

    } else {
      String message = format("Table Type [{0}] in schema [{1}] already exists during the update process", tableType.getName(),
          tableType.getSchema());
      logger.warn(message);
      applyArtefactState(tableType.getName(), tableType.getLocation(), TABLE_TYPE_SYNCHRONIZATION_ARTEFACT_TYPE,
          ArtefactState.FAILED, message);
    }
  }

  private static final XSKDataStructuresSynchronizer DATA_STRUCTURES_SYNCHRONIZER = new XSKDataStructuresSynchronizer();

  public void applyArtefactState(String artefactName, String artefactLocation, AbstractSynchronizationArtefactType type,
      ISynchronizerArtefactType.ArtefactState state, String message) {
    DATA_STRUCTURES_SYNCHRONIZER.applyArtefactState(artefactName, artefactLocation, type, state, message);
  }

}
