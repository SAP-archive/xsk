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
package com.sap.xsk.hdb.ds.processors.synonym;

import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.processors.AbstractXSKProcessor;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static java.text.MessageFormat.format;

public class HDBSynonymDropProcessor extends AbstractXSKProcessor<XSKDataStructureHDBSynonymModel> {

    private static final Logger logger = LoggerFactory.getLogger(HDBSynonymDropProcessor.class);

    /**
     * Execute :
     * DROP [PUBLIC] SYNONYM <synonym_name> [<drop_option>]
     * If <drop_option> is not specified, then a non-cascaded drop is performed which only drops the specified synonym.
     * Dependent objects of the synonym are invalidated but not dropped.
     *
     * @see <a href="https://help.sap.com/viewer/4fe29514fd584807ac9f2a04f6754767/1.0.12/en-US/20d7e172751910148bccb49de92d9859.html">DROP SYNONYM Statement (Data Definition)</a>
     */
    @Override
    public void execute(Connection connection, XSKDataStructureHDBSynonymModel synonymModel) throws SQLException {
        logger.info("Processing Drop Synonym: " + synonymModel);

        //TODO: this is a workaround due to issue on AbstractSqlBuilder.encapsulateMany()
        boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        String synonymName = synonymModel.getName();
        String escSynonymSchema = synonymModel.getSynonymSchema();
        if (caseSensitive) {
            synonymName = "\"" + synonymName + "\"";
            if (!escSynonymSchema.isEmpty()) escSynonymSchema = "\"" + escSynonymSchema + "\"" + ".";
        }

        if (SqlFactory.getNative(connection).exists(connection, synonymModel.getName())) {
            String sql = SqlFactory.getNative(connection).drop().synonym(escSynonymSchema + synonymName).build();
            executeSql(sql, connection);
        } else {
            logger.warn(format("Synonym [{0}] does not exists during the drop process", synonymModel.getName()));
        }
    }
}
