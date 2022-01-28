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
package com.sap.xsk.hdb.ds.processors.hdi;

import com.sap.xsk.hdb.ds.artefacts.CalculationViewSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBProcedureSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBSchemaSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBSequenceSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBSynonymSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBTableFunctionSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBTableSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBTableTypeSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBViewSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.hdb.ds.util.Constants;
import com.sap.xsk.hdb.ds.util.MetadataResult;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKListDeployedFilesProcessor extends XSKHDIAbstractProcessor {

  private static final Logger LOGGER = LoggerFactory.getLogger(XSKListDeployedFilesProcessor.class);
  private static final String ERROR_LOCATION = "-";

  private static final CalculationViewSynchronizationArtefactType CALCULATION_VIEW_SYNCHRONIZATION_ARTEFACT_TYPE = new CalculationViewSynchronizationArtefactType();
  private static final HDBTableSynchronizationArtefactType TABLE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBTableSynchronizationArtefactType();
  private static final HDBProcedureSynchronizationArtefactType PROCEDURE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBProcedureSynchronizationArtefactType();
  private static final HDBSchemaSynchronizationArtefactType SCHEMA_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBSchemaSynchronizationArtefactType();
  private static final HDBSequenceSynchronizationArtefactType SEQUENCE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBSequenceSynchronizationArtefactType();
  private static final HDBSynonymSynchronizationArtefactType SYNONYM_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBSynonymSynchronizationArtefactType();
  private static final HDBTableFunctionSynchronizationArtefactType TABLE_FUNCTION_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBTableFunctionSynchronizationArtefactType();
  private static final HDBTableTypeSynchronizationArtefactType TABLE_TYPE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBTableTypeSynchronizationArtefactType();
  private static final HDBViewSynchronizationArtefactType VIEW_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBViewSynchronizationArtefactType();
  private static final XSKDataStructuresSynchronizer dataStructuresSynchronizer = new XSKDataStructuresSynchronizer();

  public void execute(Connection connection, String container, XSKDataStructureHDIModel hdiModel) throws SQLException {

    executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #PARAMETERS LIKE _SYS_DI.TT_PARAMETERS;");
    executeUpdate(connection, "INSERT INTO #PARAMETERS ( KEY, VALUE ) VALUES ('recursive', 'true');");
    executeUpdate(connection, "INSERT INTO #PARAMETERS ( KEY, VALUE ) VALUES ('ignore_folders', 'true');");
    executeCall(connection, "CALL " + container + "#DI.LIST_DEPLOYED(_SYS_DI.T_NO_FILESFOLDERS, #PARAMETERS, ?, ?, ?, ?);",
        hdiModel);
    executeUpdate(connection, "DROP TABLE #PARAMETERS;");

  }

  protected void executeCall(Connection connection, String sql, XSKDataStructureHDIModel hdiModel) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      //setStatementParams(statement, parameters);
      try (ResultSet resultSet = statement.executeQuery()) {
        parseResultSet(resultSet);
      }
      if (statement.getMoreResults()) {
        try (ResultSet rs = statement.getResultSet()) {
          parseMetadataResultSet(rs, hdiModel);
        }
      }
    } catch (SQLException e) {
      LOGGER.error("Failed to execute SQL statement - " + sql, e);
      XSKCommonsUtils.logProcessorErrors(e.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, ERROR_LOCATION,
          XSKCommonsConstants.HDI_PROCESSOR);
    }
  }

  private void parseMetadataResultSet(ResultSet resultSet, XSKDataStructureHDIModel hdiModel) throws SQLException {
    ArrayList<MetadataResult> results = new ArrayList<>();
    ArrayList<String> paths = new ArrayList<>();
    while (resultSet.next()) {
      MetadataResult result = new MetadataResult(resultSet);
      results.add(result);
      paths.add(result.path);
    }
    for (String deployPath : hdiModel.getDeploy()) {
      String fileExtension = getExtension(deployPath);
      if(deployPath.indexOf(Constants.UNIX_SEPARATOR) != -1){
        String fileName = deployPath.substring(deployPath.lastIndexOf(Constants.UNIX_SEPARATOR));
        if (paths.contains(deployPath.substring(deployPath.indexOf(Constants.UNIX_SEPARATOR) + 1))) {
          applyState(fileExtension, fileName, ArtefactState.SUCCESSFUL_CREATE, hdiModel);
        } else {
          applyState(fileExtension, fileName, ArtefactState.FAILED_CREATE, hdiModel);
        }
      }
    }
  }

  private void applyState(String fileExtension, String fileName, ArtefactState artefactState, XSKDataStructureHDIModel hdiModel) {
    switch (fileExtension) {
      case Constants.CALCULATION_VIEW_EXTENSION:
        dataStructuresSynchronizer.applyArtefactState(fileName, hdiModel.getLocation(),
            CALCULATION_VIEW_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.TABLE_EXTENSION:
        dataStructuresSynchronizer.applyArtefactState(fileName, hdiModel.getLocation(),
            TABLE_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.SCHEMA_EXTENSION: dataStructuresSynchronizer.applyArtefactState(fileName, hdiModel.getLocation(),
          SCHEMA_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.PROCEDURE_EXTENSION: dataStructuresSynchronizer.applyArtefactState(fileName, hdiModel.getLocation(),
          PROCEDURE_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.SYNONYM_EXTENSION: dataStructuresSynchronizer.applyArtefactState(fileName, hdiModel.getLocation(),
          SYNONYM_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.SEQUENCE_EXTENSION: dataStructuresSynchronizer.applyArtefactState(fileName, hdiModel.getLocation(),
          SEQUENCE_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.TABLE_FUNCTION_EXTENSION: dataStructuresSynchronizer.applyArtefactState(fileName, hdiModel.getLocation(),
          TABLE_FUNCTION_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.TABLE_TYPE_EXTENSION: dataStructuresSynchronizer.applyArtefactState(fileName, hdiModel.getLocation(),
          TABLE_TYPE_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.VIEW_EXTENSIONS: dataStructuresSynchronizer.applyArtefactState(fileName, hdiModel.getLocation(),
          VIEW_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      default: break;
    }
  }

  private String getExtension(String filename) {
    String extension = "";
    if(filename.indexOf(Constants.DOT) != -1) {
      int indexOfLastDot = filename.lastIndexOf(Constants.DOT);
        int indexOfLastSeparator = filename.lastIndexOf(Constants.UNIX_SEPARATOR);
        if (indexOfLastDot > indexOfLastSeparator) {
          extension = filename.substring(indexOfLastDot + 1);
        }
    }
    return extension;
  }
}
