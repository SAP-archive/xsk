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

import static com.sap.xsk.hdbti.api.IXSKTableImportModel.TYPE_HDBTI;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import com.sap.xsk.parser.hdbti.exception.XSKHDBTISyntaxErrorException;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.api.module.StaticInjector;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdbti.api.IXSKTableImportCoreService;
import com.sap.xsk.hdbti.api.XSKTableImportException;
import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.transformer.XSKTableImportArtifactFactory;

public class XSKTableImportCoreService implements IXSKTableImportCoreService {

    private static final Logger logger = LoggerFactory.getLogger(XSKTableImportCoreService.class);

    @Inject
    private XSKTableImportArtifactFactory xskTableImportArtifactFactory;

    private DataSource dataSource = StaticInjector.getInjector().getInstance(DataSource.class);

    @Inject
    private PersistenceManager<XSKTableImportArtifact> xskPersistenceManager;

    @Override
    public XSKTableImportArtifact createTableImportArtifact(XSKTableImportArtifact xskTableImportArtifact) throws XSKTableImportException {

        try (Connection connection = dataSource.getConnection()) {
            xskPersistenceManager.insert(connection, xskTableImportArtifact);
            return xskTableImportArtifact;
        } catch (SQLException e) {
            throw new XSKTableImportException(e);
        }
    }

    @Override
    public void updateTableImportArtifact(XSKTableImportArtifact artifact) throws XSKTableImportException {
        try (Connection connection = dataSource.getConnection()) {
            XSKTableImportArtifact tableImportArtifact = getTableImportArtifact(artifact.getLocation());
            tableImportArtifact.setName(artifact.getName());
            tableImportArtifact.setHash(artifact.getHash());
            xskPersistenceManager.update(connection, tableImportArtifact);
        } catch (SQLException e) {
            throw new XSKTableImportException(e);
        }
    }

    @Override
    public XSKTableImportArtifact getTableImportArtifact(String location) throws XSKTableImportException {
        try (Connection connection = dataSource.getConnection()) {
            return xskPersistenceManager.find(connection, XSKTableImportArtifact.class, location);
        } catch (SQLException e) {
            throw new XSKTableImportException(e);
        }
    }

    @Override
    public void removeTableImportArtifact(String location) {
        try (Connection connection = dataSource.getConnection()) {
            xskPersistenceManager.delete(connection, XSKTableImportArtifact.class, location);
        } catch (SQLException e) {
            logger.error("Error cleaning up and HDBTI file from DB", e);
        }
    }

    @Override
    public List<XSKTableImportArtifact> getTableImportArtifacts() throws XSKTableImportException {
        try (Connection connection = dataSource.getConnection()) {
            return xskPersistenceManager.findAll(connection, XSKTableImportArtifact.class);
        } catch (SQLException e) {
            logger.error("Error getting the HDBTI artifacts from DB");
            throw new XSKTableImportException(e);
        }
    }

    @Override
    public boolean existsTableImportArtifact(String location) throws XSKTableImportException {
        return getTableImportArtifact(location) != null;
    }

    @Override
    public XSKTableImportArtifact parseTableImportArtifact(String location, String content) throws IOException, XSKHDBTISyntaxErrorException {
        XSKTableImportArtifact parsedArtifact = xskTableImportArtifactFactory.parseTableImport(content, location);
        parsedArtifact.setName(new File(location).getName());
        parsedArtifact.setLocation(location);
        parsedArtifact.setType(TYPE_HDBTI);
        parsedArtifact.setHash(DigestUtils.md5Hex(content));
        parsedArtifact.setCreatedBy(UserFacade.getName());
        parsedArtifact.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
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
