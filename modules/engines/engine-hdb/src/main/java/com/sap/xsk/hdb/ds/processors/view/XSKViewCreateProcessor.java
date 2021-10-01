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
package com.sap.xsk.hdb.ds.processors.view;

import static java.text.MessageFormat.format;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.module.XSKHDBModule;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
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

  private Map<String, IXSKDataStructureManager> managerServices = XSKHDBModule.getManagerServices();

  /**
   * Execute the corresponding statement.
   *
   * @param connection the connection
   * @param viewModel  the view model
   * @throws SQLException the SQL exception
   */
  public void execute(Connection connection, XSKDataStructureHDBViewModel viewModel)
      throws SQLException, ProblemsException {
    logger.info("Processing Create View: " + viewModel.getName());
    String viewNameWithSchema = XSKHDBUtils.escapeArtifactName(connection, viewModel.getName(), viewModel.getSchema());

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
            XSKCommonsUtils.logProcessorErrors(errorMessage, "PROCESSOR", viewModel.getLocation(), "HDB View");
            throw new IllegalStateException(errorMessage);
          }
        }
      }
      try {
        executeSql(sql, connection);
      } catch (SQLException ex) {
        XSKCommonsUtils.logProcessorErrors(ex.getMessage(), "PROCESSOR", viewModel.getLocation(), "HDB View");
      }
    } else {
      logger.warn(format("View [{0}] already exists during the create process", viewModel.getName()));
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
