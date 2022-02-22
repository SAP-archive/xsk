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
package com.sap.xsk.hdb.ds.processors.view;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.artefacts.HDBViewSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.ISqlDialect;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The View Create Processor.
 */
public class XSKViewCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBViewModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKViewCreateProcessor.class);
  private static final HDBViewSynchronizationArtefactType VIEW_ARTEFACT = new HDBViewSynchronizationArtefactType();

  private Map<String, IXSKDataStructureManager> managerServices = XSKHDBModule.getManagerServices();

  /**
   * Execute the corresponding statement.
   *
   * @param connection the connection
   * @param viewModel  the view model
   * @throws SQLException the SQL exception
   */
  public void execute(Connection connection, XSKDataStructureHDBViewModel viewModel)
      throws SQLException {
    logger.info("Processing Create View: " + viewModel.getName());
    String viewNameWithSchema = XSKHDBUtils.escapeArtifactName(viewModel.getName(), viewModel.getSchema());

    if (!SqlFactory.getNative(connection).exists(connection, viewNameWithSchema, DatabaseArtifactTypes.VIEW)) {
      String sql = null;
      switch (viewModel.getDBContentType()) {
        case XS_CLASSIC: {
          sql = SqlFactory.getNative(connection).create().view(viewNameWithSchema).asSelect(viewModel.getQuery()).build();
          break;
        }
        case OTHERS: {
          ISqlDialect dialect = SqlFactory.deriveDialect(connection);
          if (dialect.getClass().equals(HanaSqlDialect.class)) {
            sql = XSKConstants.XSK_HDBVIEW_CREATE + viewModel.getRawContent();
            break;
          } else {
            String errorMessage = String.format("Views are not supported for %s !", dialect.getDatabaseName(connection));
            XSKCommonsUtils.logProcessorErrors(errorMessage, XSKCommonsConstants.PROCESSOR_ERROR, viewModel.getLocation(),
                XSKCommonsConstants.HDB_VIEW_PARSER);
            applyArtefactState(viewModel.getName(), viewModel.getLocation(), VIEW_ARTEFACT, ArtefactState.FAILED_CREATE, errorMessage);
            throw new IllegalStateException(errorMessage);
          }
        }
      }
      try {
        executeSql(sql, connection);
        String message = String.format("Create view %s successfully", viewModel.getName());
        applyArtefactState(viewModel.getName(), viewModel.getLocation(), VIEW_ARTEFACT, ArtefactState.SUCCESSFUL_CREATE, message);
      } catch (SQLException ex) {
        String errorMessage = String.format("Create view [%s] skipped due to an error: %s", viewModel.getName(), ex.getMessage());
        XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, viewModel.getLocation(),
            XSKCommonsConstants.HDB_VIEW_PARSER);
        applyArtefactState(viewModel.getName(), viewModel.getLocation(), VIEW_ARTEFACT, ArtefactState.FAILED_CREATE, errorMessage);
      }
    } else {
      String warningMessage = String.format("View [%s] already exists during the create process", viewModel.getName());
      logger.warn(warningMessage);
      applyArtefactState(viewModel.getName(), viewModel.getLocation(), VIEW_ARTEFACT, ArtefactState.FAILED_CREATE, warningMessage);
    }

    //Create public synonym
    if (managerServices != null) {
      if (SqlFactory.getNative(connection).exists(connection, viewNameWithSchema, DatabaseArtifactTypes.VIEW)) {
        XSKHDBUtils.createPublicSynonymForArtifact(managerServices
            .get(IXSKDataStructureModel.TYPE_HDB_SYNONYM), viewModel.getName(), viewModel.getSchema(), connection);
      }
    }
  }

}
