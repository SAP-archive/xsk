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
package com.sap.xsk.hdb.ds.processors.hdbschema;

import static java.text.MessageFormat.format;

import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import com.sap.xsk.utils.XSKCommonsUtils;
import com.sap.xsk.utils.XSKHDBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.ISqlDialect;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.hana.HanaSqlDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDBSchemaCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBSchemaModel> {

    private static final Logger logger = LoggerFactory.getLogger(HDBSchemaCreateProcessor.class);

    public HDBSchemaCreateProcessor() {
    }

    public void execute(Connection connection, XSKDataStructureHDBSchemaModel hdbSchema) throws SQLException, ProblemsException {
        logger.info("Processing Create Schema: " + hdbSchema.getSchema());

        ISqlDialect dialect = SqlFactory.deriveDialect(connection);
        if (!(dialect.getClass().equals(HanaSqlDialect.class))) {
          String errorMessage = String.format("%s does not support Schema", dialect.getDatabaseName(connection));
          XSKCommonsUtils.logProcessorErrors(errorMessage, "PROCESSOR", hdbSchema.getLocation(), "HDB Schema");
          throw new IllegalStateException(errorMessage);
        } else {
            if (!SqlFactory.getNative(connection).exists(connection, hdbSchema.getSchema(), DatabaseArtifactTypes.SCHEMA)) {
                String schemaName = XSKHDBUtils.escapeArtifactName(connection, hdbSchema.getSchema());
                String sql = SqlFactory.getNative(connection).create().schema(schemaName).build();
                executeSql(sql, connection);
            } else {
                logger.warn(format("Schema [{0}] already exists during the create process", hdbSchema.getSchema()));
            }
        }
    }
}
