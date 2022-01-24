/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
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
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.eclipse.dirigible.core.scheduler.api.ISynchronizerArtefactType.ArtefactState;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The View Drop Processor.
 */
public class XSKViewDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBViewModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKViewDropProcessor.class);
  private static final HDBViewSynchronizationArtefactType VIEW_ARTEFACT = new HDBViewSynchronizationArtefactType();
  private static final XSKDataStructuresSynchronizer dataStructuresSynchronizer = new XSKDataStructuresSynchronizer();

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
    logger.info("Processing Drop View: " + viewModel.getName());
    String viewNameWithSchema = XSKHDBUtils.escapeArtifactName(connection, viewModel.getName(), viewModel.getSchema());

    //Drop public synonym
    if (managerServices != null) {
      XSKHDBUtils.dropPublicSynonymForArtifact(managerServices
          .get(IXSKDataStructureModel.TYPE_HDB_SYNONYM), viewModel.getName(), viewModel.getSchema(), connection);
    }

    //Drop view
    if (SqlFactory.getNative(connection).exists(connection, viewNameWithSchema, DatabaseArtifactTypes.VIEW)) {
      String sql = SqlFactory.getNative(connection).drop().view(viewNameWithSchema).build();
      try {
        executeSql(sql, connection);
        String message = String.format("Drop view %s successfully", viewModel.getName());
        dataStructuresSynchronizer.applyArtefactState(viewModel.getName(), viewModel.getLocation(), VIEW_ARTEFACT, ArtefactState.SUCCESSFUL_DELETE, message);
      } catch (SQLException ex) {
        String errorMessage = String.format("Drop view [%s] skipped due to an error: %s", viewModel.getName(), ex.getMessage());
        XSKCommonsUtils.logProcessorErrors(ex.getMessage(), XSKCommonsConstants.PROCESSOR_ERROR, viewModel.getLocation(), XSKCommonsConstants.HDB_VIEW_PARSER);
        dataStructuresSynchronizer.applyArtefactState(viewModel.getName(), viewModel.getLocation(), VIEW_ARTEFACT, ArtefactState.FAILED_DELETE, errorMessage);
      }
    } else {
      String warningMessage = String.format("View [%s] does not exists during the drop process", viewModel.getName());
      logger.warn(warningMessage);
      dataStructuresSynchronizer.applyArtefactState(viewModel.getName(), viewModel.getLocation(), VIEW_ARTEFACT, ArtefactState.FAILED_DELETE, warningMessage);
    }
  }

}
