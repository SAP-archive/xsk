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
package com.sap.xsk.hdbti.service;

import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.hdbti.transformer.XSKTableImportArtifactFactory;
import com.sap.xsk.parser.hdbti.exception.XSKHDBTISyntaxErrorException;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import static com.sap.xsk.hdbti.api.IXSKTableImportModel.TYPE_HDBTI;

@Singleton
public class XSKTableImportParser {

    private static final Logger logger = LoggerFactory.getLogger(XSKTableImportParser.class);

    @Inject
    private XSKTableImportArtifactFactory xskTableImportArtifactFactory;

    public XSKTableImportArtifact parseTableImportArtifact(String location, String content) throws IOException, XSKHDBTISyntaxErrorException {
        XSKTableImportArtifact parsedArtifact = xskTableImportArtifactFactory.parseTableImport(content, location);
        parsedArtifact.setName(new File(location).getName());
        parsedArtifact.setLocation(location);
        parsedArtifact.setType(TYPE_HDBTI);
        parsedArtifact.setHash(DigestUtils.md5Hex(content));
        parsedArtifact.setCreatedBy(UserFacade.getName());
        parsedArtifact.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

        for (XSKTableImportConfigurationDefinition configurationDefinition : parsedArtifact.getImportConfigurationDefinition()) {
            configurationDefinition.setHdbtiFileName(location);
        }

        return parsedArtifact;
    }

    boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));

    public String convertToActualTableName(String tableName) {
        if (caseSensitive) {
            tableName = "\"" + tableName + "\"";
        }
        return tableName;
//        return tableName.substring(tableName.lastIndexOf(':') + 1).replace('.', '_').toUpperCase();
    }
}
