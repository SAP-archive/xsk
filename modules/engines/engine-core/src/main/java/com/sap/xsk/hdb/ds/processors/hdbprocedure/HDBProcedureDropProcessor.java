/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.processors.hdbprocedure;

import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HDBProcedureDropProcessor {
    private static final Logger logger = LoggerFactory.getLogger(HDBProcedureDropProcessor.class);

    public void execute(Connection connection, XSKDataStructureHDBProcedureModel hdbProcedure) throws SQLException {
        executeSingle(connection, hdbProcedure);
    }

    private static void executePreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            logger.info(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(e.getMessage(), e);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void executeSingle(Connection connection, XSKDataStructureHDBProcedureModel hdbProcedure) throws SQLException {
        String sql = "DROP PROCEDURE " + hdbProcedure.getName();
        executePreparedStatement(connection, sql);
    }

}
