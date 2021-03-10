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
package com.sap.xsk.hdb.ds.processors.hdbtablefunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;

public class HDBTableFunctionCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBTableFunctionModel> {
    private static final Logger logger = LoggerFactory.getLogger(HDBTableFunctionCreateProcessor.class);

    public void execute(Connection connection, XSKDataStructureHDBTableFunctionModel hdbTableFunctions) throws SQLException {
        String sql = "CREATE " + hdbTableFunctions.getContent();
        executeSql(sql, connection);
    }
}
