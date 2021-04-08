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
package com.sap.xsk.hdb.ds.processors.hdbsequence;


import com.sap.xsk.hdb.ds.model.XSKHanaVersion;
import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKConstants;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class XSKHDBSequenceUpdateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBSequenceModel> {
    private static final Logger logger = LoggerFactory.getLogger(XSKHDBSequenceUpdateProcessor.class);


    @Override
    public void execute(Connection connection, XSKDataStructureHDBSequenceModel hdbSequenceModel) throws SQLException {
        boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        String hdbSequenceName = hdbSequenceModel.getName();
        if (caseSensitive) {
            hdbSequenceName = "\"" + hdbSequenceName + "\"";
        }
        logger.info("Processing Update HdbSequence: " + hdbSequenceName);

        String sql = (hdbSequenceModel.getHanaVersion() == XSKHanaVersion.VERSION_1)
                ? getHanav1ModelSQL(connection, hdbSequenceModel, hdbSequenceName)
                : XSKConstants.XSK_HDBSEQUENCE_ALTER + hdbSequenceModel.getRawContent();
        executeSql(sql, connection);

    }

    private String getHanav1ModelSQL(Connection connection, XSKDataStructureHDBSequenceModel hdbSequenceModel, String modifiedSequenceName) {
        return SqlFactory.getNative(connection).alter().sequence(modifiedSequenceName)
                .start(hdbSequenceModel.getStart_with())
                .increment(hdbSequenceModel.getIncrement_by())
                .maxvalue(hdbSequenceModel.getMaxvalue())
                .nomaxvalue(hdbSequenceModel.getNomaxvalue())
                .minvalue(hdbSequenceModel.getMinvalue())
                .nominvalue(hdbSequenceModel.getNominvalue())
                .cycles(hdbSequenceModel.getCycles())
                .resetBy(hdbSequenceModel.getReset_by()).build();
    }
}
