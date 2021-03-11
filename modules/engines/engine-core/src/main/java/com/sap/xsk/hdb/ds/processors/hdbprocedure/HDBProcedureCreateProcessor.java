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
package com.sap.xsk.hdb.ds.processors.hdbprocedure;

import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class HDBProcedureCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBProcedureModel> {
    private static final Logger logger = LoggerFactory.getLogger(HDBProcedureCreateProcessor.class);

    public void execute(Connection connection, XSKDataStructureHDBProcedureModel hdbProcedure) throws SQLException {
        String sql = XSKConstants.XSK_HDBPROCEDURE_CREATE + hdbProcedure.getContent();
        executeSql(sql, connection);
    }
}
