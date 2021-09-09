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
package com.sap.xsk.hdb.ds.processors.hdbsequence;

import static java.text.MessageFormat.format;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.ISqlDialect;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbsequence.XSKDataStructureHDBSequenceModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKConstants;

public class XSKHDBSequenceDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBSequenceModel> {

    private static final Logger logger = LoggerFactory.getLogger(XSKHDBSequenceDropProcessor.class);

    @Override
    public void execute(Connection connection, XSKDataStructureHDBSequenceModel hdbSequenceModel) throws SQLException {

        boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        String hdbSequenceName = hdbSequenceModel.getName();
        if (caseSensitive) {
            hdbSequenceName = "\"" + hdbSequenceName + "\"";
        }
        logger.info("Processing Drop HdbSequence: " + hdbSequenceName);

        if (SqlFactory.getNative(connection).exists(connection, hdbSequenceName, DatabaseArtifactTypes.SEQUENCE)) {
            String sql = null;
            switch (hdbSequenceModel.getDBContentType()) {
                case XS_CLASSIC: {
                    sql = getDatabaseSpecificSQL(connection, hdbSequenceModel, hdbSequenceName);
                    break;
                }
                case OTHERS: {
                    ISqlDialect dialect = SqlFactory.deriveDialect(connection);
                    if (dialect.getClass().equals(HanaSqlDialect.class)) {
                        sql = XSKConstants.XSK_HDBSEQUENCE_DROP + hdbSequenceModel.getRawContent();
                        break;
                    } else {
                        throw new IllegalStateException(String.format("Sequences are not supported for %s !", dialect.getDatabaseName(connection)));
                    }
                }
            }
            executeSql(sql, connection);
        } else {
            logger.warn(format("Sequence [{0}] does not exists during the drop process", hdbSequenceModel.getName()));
        }
    }

    private String getDatabaseSpecificSQL(Connection connection, XSKDataStructureHDBSequenceModel hdbSequenceModel, String modifiedSequenceName) {
      if (hdbSequenceModel.getSchema() != null) {
        modifiedSequenceName = "\"" + hdbSequenceModel.getSchema() + "\"."+ modifiedSequenceName;
      }
        return SqlFactory.getNative(connection).drop().sequence(modifiedSequenceName).build();
    }

}