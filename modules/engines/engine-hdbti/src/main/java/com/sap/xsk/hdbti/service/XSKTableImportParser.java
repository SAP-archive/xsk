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
package com.sap.xsk.hdbti.service;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdbti.api.IXSKTableImportArtifactFactory;
import com.sap.xsk.hdbti.api.IXSKTableImportParser;
import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.model.XSKTableImportConfigurationDefinition;
import com.sap.xsk.parser.hdbti.exception.XSKHDBTISyntaxErrorException;
import com.sap.xsk.utils.XSKCommonsDBUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.sap.xsk.hdbti.api.IXSKTableImportModel.TYPE_HDBTI;

public class XSKTableImportParser implements IXSKTableImportParser {

    @Inject
    @Named("xskTableImportArtifactFactory")
    private IXSKTableImportArtifactFactory xskTableImportArtifactFactory;

    @Inject
    private DataSource dataSource;

    @Override
    public XSKTableImportArtifact parseTableImportArtifact(String location, String content)
            throws IOException, XSKHDBTISyntaxErrorException, XSKArtifactParserException, SQLException {
        XSKTableImportArtifact parsedArtifact = xskTableImportArtifactFactory.parseTableImport(content, location);
        parsedArtifact.setName(new File(location).getName());
        parsedArtifact.setLocation(location);
        parsedArtifact.setType(TYPE_HDBTI);
        parsedArtifact.setHash(DigestUtils.md5Hex(content));
        parsedArtifact.setCreatedBy(UserFacade.getName());
        parsedArtifact.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

        for (XSKTableImportConfigurationDefinition configurationDefinition : parsedArtifact.getImportConfigurationDefinition()) {
            configurationDefinition.setHdbtiFileName(location);
            if (configurationDefinition.getSchema() == null) {
                configurationDefinition.setSchema(XSKCommonsDBUtils.getTableSchema(dataSource, configurationDefinition.getTable()));
            }
        }

        return parsedArtifact;
    }
}
