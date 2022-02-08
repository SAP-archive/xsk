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
package com.sap.xsk.hdb.ds.processors.hdi;

import static com.sap.xsk.hdb.ds.util.Constants.RETURN_CODE_SUCCESS;
import static com.sap.xsk.hdb.ds.util.Constants.RETURN_CODE_WARNING;

import com.sap.xsk.hdb.ds.artefacts.CalculationViewSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBProcedureSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBSchemaSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBSequenceSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBSynonymSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBTableFunctionSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBTableSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBTableTypeSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.artefacts.HDBViewSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.util.Constants;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKDeployContainerContentProcessor extends XSKHDIAbstractProcessor {

  private static final Logger LOGGER = LoggerFactory.getLogger(XSKDeployContainerContentProcessor.class);
  private static final String ERROR_LOCATION = "-";
  private static final String SQL_SELECT_FROM_DEPLOY_PATHS = "SELECT * FROM #DEPLOY_PATHS";
  private static final int FIRST_OUTPUT_PARAMETER_INDEX = 1;

  private static final CalculationViewSynchronizationArtefactType CALCULATION_VIEW_SYNCHRONIZATION_ARTEFACT_TYPE = new CalculationViewSynchronizationArtefactType();
  private static final HDBTableSynchronizationArtefactType TABLE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBTableSynchronizationArtefactType();
  private static final HDBProcedureSynchronizationArtefactType PROCEDURE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBProcedureSynchronizationArtefactType();
  private static final HDBSchemaSynchronizationArtefactType SCHEMA_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBSchemaSynchronizationArtefactType();
  private static final HDBSequenceSynchronizationArtefactType SEQUENCE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBSequenceSynchronizationArtefactType();
  private static final HDBSynonymSynchronizationArtefactType SYNONYM_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBSynonymSynchronizationArtefactType();
  private static final HDBTableFunctionSynchronizationArtefactType TABLE_FUNCTION_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBTableFunctionSynchronizationArtefactType();
  private static final HDBTableTypeSynchronizationArtefactType TABLE_TYPE_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBTableTypeSynchronizationArtefactType();
  private static final HDBViewSynchronizationArtefactType VIEW_SYNCHRONIZATION_ARTEFACT_TYPE = new HDBViewSynchronizationArtefactType();

  public final void execute(Connection connection, String container, String[] deployPaths, String[] undeployPaths) throws SQLException {
    executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #DEPLOY_PATHS LIKE _SYS_DI.TT_FILESFOLDERS;");
    executeUpdate(connection, "INSERT INTO #DEPLOY_PATHS (PATH) VALUES ('.hdiconfig');");
    for (String next : deployPaths) {
      executeUpdate(connection, "INSERT INTO #DEPLOY_PATHS (PATH) VALUES (?);", next.substring(1));
    }
    executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #UNDEPLOY_PATHS LIKE _SYS_DI.TT_FILESFOLDERS;");
    for (String next : undeployPaths) {
      executeUpdate(connection, "INSERT INTO #UNDEPLOY_PATHS (PATH) VALUES (?);", next.substring(1));
    }
    executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #PATH_PARAMETERS LIKE _SYS_DI.TT_FILESFOLDERS_PARAMETERS;");

    executeCall(connection,
        "CALL " + container + "#DI.MAKE(#DEPLOY_PATHS, #UNDEPLOY_PATHS, #PATH_PARAMETERS, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");

    executeUpdate(connection, "DROP TABLE #DEPLOY_PATHS;");
    executeUpdate(connection, "DROP TABLE #UNDEPLOY_PATHS;");
    executeUpdate(connection, "DROP TABLE #PATH_PARAMETERS;");
  }

  protected void executeCall(Connection connection, String sql) {
    try (CallableStatement statement = connection.prepareCall(sql)) {
      statement.registerOutParameter(FIRST_OUTPUT_PARAMETER_INDEX, Types.INTEGER);

      try (ResultSet resultSet = statement.executeQuery()) {
        int returnCode = statement.getInt(FIRST_OUTPUT_PARAMETER_INDEX);        // 1st output parameter (Return_Code)
        parseResultSet(resultSet);
        try (PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_FROM_DEPLOY_PATHS)) {
          try (ResultSet rs = prepareStatement.executeQuery()) {
            while (rs.next()) {
              String deployPath = rs.getString(1);
              if (deployPath.indexOf(Constants.UNIX_SEPARATOR) != -1) {
                String fileExtension = getExtension(deployPath);
                String fileName = deployPath.substring(deployPath.lastIndexOf(Constants.UNIX_SEPARATOR) + 1);
                if (returnCode == RETURN_CODE_SUCCESS || returnCode == RETURN_CODE_WARNING) {
                  applyState(fileExtension, fileName, ArtefactState.SUCCESSFUL_CREATE, deployPath);
                } else {
                  applyState(fileExtension, fileName, ArtefactState.FAILED_CREATE, deployPath);
                }
              }
            }
          }
        }
      }
    } catch (SQLException e) {
      LOGGER.error("Failed to execute SQL statement - " + sql, e);
      XSKCommonsUtils.logProcessorErrors(e.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, ERROR_LOCATION,
          XSKCommonsConstants.HDI_PROCESSOR);
    }
  }

  protected void applyState(String fileExtension, String fileName, ArtefactState artefactState, String location) {
    switch (fileExtension) {
      case Constants.CALCULATION_VIEW_EXTENSION:
        applyArtefactState(fileName, location,
            CALCULATION_VIEW_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.TABLE_EXTENSION:
        applyArtefactState(fileName, location,
            TABLE_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.SCHEMA_EXTENSION:
        applyArtefactState(fileName, location,
            SCHEMA_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.PROCEDURE_EXTENSION:
        applyArtefactState(fileName, location,
            PROCEDURE_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.SYNONYM_EXTENSION:
        applyArtefactState(fileName, location,
            SYNONYM_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.SEQUENCE_EXTENSION:
        applyArtefactState(fileName, location,
            SEQUENCE_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.TABLE_FUNCTION_EXTENSION:
        applyArtefactState(fileName, location,
            TABLE_FUNCTION_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.TABLE_TYPE_EXTENSION:
        applyArtefactState(fileName, location,
            TABLE_TYPE_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      case Constants.VIEW_EXTENSIONS:
        applyArtefactState(fileName, location,
            VIEW_SYNCHRONIZATION_ARTEFACT_TYPE, artefactState, "");
        break;
      default:
        break;
    }
  }

  private String getExtension(String filename) {
    String extension = "";
    if (filename.indexOf(Constants.DOT) != -1) {
      int indexOfLastDot = filename.lastIndexOf(Constants.DOT);
      int indexOfLastSeparator = filename.lastIndexOf(Constants.UNIX_SEPARATOR);
      if (indexOfLastDot > indexOfLastSeparator) {
        extension = filename.substring(indexOfLastDot + 1);
      }
    }
    return extension;
  }

}
