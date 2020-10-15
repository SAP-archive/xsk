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
    private PersistenceManager<XSKPrivilegeDefinition> xscPrivilegeDefinitionPersistenceManager;

    @Override
    public XSKPrivilegeDefinition createXSKPrivilege(String name, String description) throws XSKPrivilegeException {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                XSKPrivilegeDefinition xscPrivilegeDefinition = new XSKPrivilegeDefinition();
                xscPrivilegeDefinition.setName(name);
                xscPrivilegeDefinition.setDescription(description);
                xscPrivilegeDefinition.setCreatedBy(UserFacade.getName());
                xscPrivilegeDefinition.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

                xscPrivilegeDefinitionPersistenceManager.insert(connection, xscPrivilegeDefinition);

                return xscPrivilegeDefinition;
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
                xscPrivilegeDefinitionPersistenceManager.update(connection, foundXscPrivilegeDefinition);

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
                return xscPrivilegeDefinitionPersistenceManager.findAll(connection, XSKPrivilegeDefinition.class);
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

                xscPrivilegeDefinitionPersistenceManager.delete(connection, XSKPrivilegeDefinition.class, name);
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

                XSKPrivilegeDefinition xscPrivilegeDefinition = xscPrivilegeDefinitionPersistenceManager.find(connection, XSKPrivilegeDefinition.class, name);

                return xscPrivilegeDefinition;
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
    public boolean xscPrivilegeExists(String name) throws XSKPrivilegeException {
        return getXSKPrivilegeByName(name) != null;
    }
}