/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsodata.ds.dao;

import com.sap.xsk.xsodata.ds.api.IXSKODataArtifactDao;
import com.sap.xsk.xsodata.ds.api.XSKODataException;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.eclipse.dirigible.database.sql.SqlFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static java.text.MessageFormat.format;

public class XSKODataArtifactDao implements IXSKODataArtifactDao {

    private final DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);

    private final PersistenceManager<XSKODataModel> xskPersistenceManager = new PersistenceManager<>();

    @Override
    public XSKODataModel createXSKODataArtifact(XSKODataModel tableModel) throws XSKODataException {
        try (Connection connection = dataSource.getConnection()) {
            xskPersistenceManager.insert(connection, tableModel);
            return tableModel;
        } catch (SQLException e) {
            throw new XSKODataException(e);
        }
    }

    @Override
    public XSKODataModel getXSKODataArtifact(String location) throws XSKODataException {
        try (Connection connection = dataSource.getConnection()) {
            return xskPersistenceManager.find(connection, XSKODataModel.class, location);
        } catch (SQLException e) {
            throw new XSKODataException(e);
        }
    }

    @Override
    public XSKODataModel getXSKODataArtifactByName(String name) throws XSKODataException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = SqlFactory.getNative(connection).select().column("*").from("XSK_ODATA")
                    .where("OD_NAME = ?").toString();
            List<XSKODataModel> tableModels = xskPersistenceManager.query(connection, XSKODataModel.class, sql,
                    Collections.singletonList(name));
            if (tableModels.isEmpty()) {
                return null;
            }
            if (tableModels.size() > 1) {
                throw new XSKODataException(format("There are more that one OData with the same name [{0}] at locations: [{1}] and [{2}].",
                        name, tableModels.get(0).getLocation(), tableModels.get(1).getLocation()));
            }
            return tableModels.get(0);
        } catch (SQLException e) {
            throw new XSKODataException(e);
        }
    }

    @Override
    public void removeXSKODataArtifact(String location) throws XSKODataException {
        try (Connection connection = dataSource.getConnection()) {
            xskPersistenceManager.delete(connection, XSKODataModel.class, location);
        } catch (SQLException e) {
            throw new XSKODataException(e);
        }
    }

    @Override
    public void updateXSKODataArtifact(String location, String name, String hash) throws XSKODataException {
        try (Connection connection = dataSource.getConnection()) {
            XSKODataModel tableModel = getXSKODataArtifact(location);
            tableModel.setName(name);
            tableModel.setHash(hash);
            xskPersistenceManager.update(connection, tableModel);
        } catch (SQLException e) {
            throw new XSKODataException(e);
        }
    }

    @Override
    public List<XSKODataModel> getAllXSKODataArtifacts() throws XSKODataException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = SqlFactory.getNative(connection).select().column("*").from("XSK_ODATA").toString();
            return xskPersistenceManager.query(connection, XSKODataModel.class, sql);
        } catch (SQLException e) {
            throw new XSKODataException(e);
        }
    }

}
