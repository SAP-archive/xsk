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
package com.sap.xsk.hdbti.dao;

import com.sap.xsk.hdbti.api.IXSKTableImportArtifactDao;
import com.sap.xsk.hdbti.api.XSKTableImportException;
import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSKTableImportArtifactDao implements IXSKTableImportArtifactDao {

    private static final Logger logger = LoggerFactory.getLogger(XSKTableImportArtifactDao.class);

    @Inject
    private DataSource dataSource;

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
}
