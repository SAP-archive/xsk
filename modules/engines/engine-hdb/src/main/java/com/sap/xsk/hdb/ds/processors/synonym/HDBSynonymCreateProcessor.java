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
import com.sap.xsk.utils.XSKHDBUtils;
import org.eclipse.dirigible.database.sql.DatabaseArtifactTypes;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static java.text.MessageFormat.format;


public class HDBSynonymCreateProcessor extends AbstractXSKProcessor<XSKDataStructureHDBSynonymModel> {

    private static final Logger logger = LoggerFactory.getLogger(HDBSynonymCreateProcessor.class);

    /**
     * Execute :
     * CREATE [PUBLIC] SYNONYM <synonym_name> FOR <synonym_source_object_name>
     * <synonym_name> ::= [<schema_name>.]<identifier>
     * <synonym_source_object_name>:==[<object_schema_name>.]<object_name>
     *
     * @see <a href="https://help.sap.com/viewer/4fe29514fd584807ac9f2a04f6754767/1.0.12/en-US/20d5412b75191014bc7ec7e133ce5bf5.html">CREATE SYNONYM Statement (Data Definition)</a>
     */
    @Override
    public void execute(Connection connection, XSKDataStructureHDBSynonymModel synonymModel) throws SQLException {
        logger.info("Processing Create Synonym: " + synonymModel.getName());

        String synonymName = XSKHDBUtils.escapeArtifactName(synonymModel.getName());
        String targetObjectName = XSKHDBUtils.escapeArtifactName(synonymModel.getTargetObject(), synonymModel.getTargetSchema());
        if (!SqlFactory.getNative(connection).exists(connection, synonymModel.getName(), DatabaseArtifactTypes.SYNONYM)) {
            String sql = SqlFactory.getNative(connection).create().synonym(synonymName).forSource(targetObjectName).build();
            executeSql(sql, connection);
        } else {
            logger.warn(format("Synonym [{0}] already exists during the create process", synonymModel.getName()));
        }
    }
}
