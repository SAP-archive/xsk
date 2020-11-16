/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsaccess.ds.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.database.persistence.PersistenceManager;

import com.sap.xsk.xsaccess.ds.api.IXSKPrivilegeCoreService;
import com.sap.xsk.xsaccess.ds.api.XSKPrivilegeException;
import com.sap.xsk.xsaccess.ds.model.privilege.XSKPrivilegeDefinition;

public class XSKPrivilegeCoreService implements IXSKPrivilegeCoreService {

    @Inject
    private DataSource dataSource;

    @Inject
    private PersistenceManager<XSKPrivilegeDefinition> xskPrivilegeDefinitionPersistenceManager;

    @Override
    public XSKPrivilegeDefinition createXSKPrivilege(String name, String description) throws XSKPrivilegeException {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                XSKPrivilegeDefinition xskPrivilegeDefinition = new XSKPrivilegeDefinition();
                xskPrivilegeDefinition.setName(name);
                xskPrivilegeDefinition.setDescription(description);
                xskPrivilegeDefinition.setCreatedBy(UserFacade.getName());
                xskPrivilegeDefinition.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

                xskPrivilegeDefinitionPersistenceManager.insert(connection, xskPrivilegeDefinition);

                return xskPrivilegeDefinition;
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new XSKPrivilegeException(e);
        }
    }

    @Override
    public XSKPrivilegeDefinition updateXSKPrivileges(String name, String description) throws XSKPrivilegeException {
        XSKPrivilegeDefinition foundXscPrivilegeDefinition = getXSKPrivilegeByName(name);
        if (foundXscPrivilegeDefinition == null) {
            throw new XSKPrivilegeException("XSK Privilege not found");
        }

        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                foundXscPrivilegeDefinition.setName(name);
                foundXscPrivilegeDefinition.setDescription(description);
                xskPrivilegeDefinitionPersistenceManager.update(connection, foundXscPrivilegeDefinition);

                return foundXscPrivilegeDefinition;
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new XSKPrivilegeException(e);
        }
    }



    @Override
    public List<XSKPrivilegeDefinition> getXSKPrivileges() throws XSKPrivilegeException {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                return xskPrivilegeDefinitionPersistenceManager.findAll(connection, XSKPrivilegeDefinition.class);
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new XSKPrivilegeException(e);
        }
    }

    @Override
    public void removeXSKPrivilegeByName(String name) throws XSKPrivilegeException {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();

                xskPrivilegeDefinitionPersistenceManager.delete(connection, XSKPrivilegeDefinition.class, name);
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new XSKPrivilegeException(e);
        }
    }

    @Override
    public XSKPrivilegeDefinition getXSKPrivilegeByName(String name) throws XSKPrivilegeException {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();

                XSKPrivilegeDefinition xskPrivilegeDefinition = xskPrivilegeDefinitionPersistenceManager.find(connection, XSKPrivilegeDefinition.class, name);

                return xskPrivilegeDefinition;
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new XSKPrivilegeException(e);
        }
    }

    @Override
    public boolean xskPrivilegeExists(String name) throws XSKPrivilegeException {
        return getXSKPrivilegeByName(name) != null;
    }
}