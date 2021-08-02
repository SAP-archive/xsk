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

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;

import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static java.text.MessageFormat.format;

/**
 * The View Drop Processor.
 */
public class XSKViewDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBViewModel> {

  private static final Logger logger = LoggerFactory.getLogger(XSKViewDropProcessor.class);

    private Map<String, IXSKDataStructureManager> managerServices = (Map<String, IXSKDataStructureManager>) StaticObjects.get("managerServices");

  /**
   * Execute the corresponding statement.
   *
   * @param connection the connection
   * @param viewModel  the view model
   * @throws SQLException the SQL exception
   */
  public void execute(Connection connection, XSKDataStructureHDBViewModel viewModel) throws SQLException {
    logger.info("Processing Drop View: " + viewModel.getName());
        String viewNameWithSchema = XSKHDBUtils.escapeArtifactName(connection, viewModel.getName(), viewModel.getSchema());

        //Drop public synonym
        if (managerServices != null)
            XSKHDBUtils.dropPublicSynonymForArtifact(managerServices
                    .get(IXSKDataStructureModel.TYPE_HDB_SYNONYM), viewModel.getName(), viewModel.getSchema(), connection);

        //Drop view
        if (SqlFactory.getNative(connection).exists(connection, XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA, viewNameWithSchema, DatabaseArtifactTypes.VIEW)) {
            String sql = SqlFactory.getNative(connection).drop().view(viewNameWithSchema).build();
      executeSql(sql, connection);
    } else {
      logger.warn(format("View [{0}] does not exists during the drop process", viewModel.getName()));
    }
  }

}
