/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.processors.view;

import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static java.text.MessageFormat.format;

/**
 * The View Create Processor.
 */
public class XSKViewCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBViewModel> {

    private static final Logger logger = LoggerFactory.getLogger(XSKViewCreateProcessor.class);

    /**
     * Execute the corresponding statement.
     *
     * @param connection the connection
     * @param viewModel  the view model
     * @throws SQLException the SQL exception
     */
    public void execute(Connection connection, XSKDataStructureHDBViewModel viewModel) throws SQLException {
        logger.info("Processing Create View: " + viewModel.getName());

        String viewName =  XSKHDBUtils.escapeArtifactName(viewModel.getName());
        if (!SqlFactory.getNative(connection).exists(connection, viewName, DatabaseArtifactTypes.VIEW)) {
            String sql = null;
            switch (viewModel.getHanaVersion()) {
                case VERSION_1: {
                    sql = SqlFactory.getNative(connection).create().view(viewName).asSelect(viewModel.getQuery()).build();
                    break;
                }
                case VERSION_2: {
                    sql = XSKConstants.XSK_HDBVIEW_CREATE + viewModel.getRawContent();
                    break;
                }
            }
            executeSql(sql, connection);
        }else {
            logger.warn(format("View [{0}] already exists during the create process", viewModel.getName()));
        }
    }

}
