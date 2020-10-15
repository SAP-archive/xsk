package com.sap.xsk.xsaccess.ds.service;

import static com.sap.xsk.utils.XSKUtils.objectToByteArray;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.database.persistence.PersistenceManager;

import com.sap.xsk.utils.XSKUtils;
import com.sap.xsk.xsaccess.ds.api.IXSKAccessCoreService;
import com.sap.xsk.xsaccess.ds.api.XSKAccessException;
import com.sap.xsk.xsaccess.ds.api.XSKPrivilegeException;
import com.sap.xsk.xsaccess.ds.model.access.XSKAccessArtifact;
import com.sap.xsk.xsaccess.ds.model.access.XSKAccessDefinition;

@Singleton
public class XSKAccessCoreService implements IXSKAccessCoreService {

    @Inject
    private DataSource dataSource;

    @Inject
    private PersistenceManager<XSKAccessDefinition> xscAccessDefinitionPersistenceManager;

    @Inject
    private XSKPrivilegeCoreService xscPrivilegeCoreService;

    private static final List<XSKAccessDefinition> CACHE = Collections.synchronizedList(new ArrayList<>());


    @Override
    public XSKAccessDefinition createXSKAccessDefinition(String path, String authenticationMethod, String hash, boolean exposed, List<String> authorizationRolesAsList) throws XSKAccessException {
        try {
            XSKAccessDefinition xscAccessDefinition = new XSKAccessDefinition();
            xscAccessDefinition.setPath(path);
            xscAccessDefinition.setAuthenticationMethod(authenticationMethod);
            xscAccessDefinition.setHash(hash);
            xscAccessDefinition.setExposed(exposed);
            if (authorizationRolesAsList == null) {
            	authorizationRolesAsList = new ArrayList();
                xscAccessDefinition.setAuthorizationRolesAsList(new ArrayList());
            }
           
	        validatePrivileges(authorizationRolesAsList);
	        xscAccessDefinition.setAuthorizationRoles(objectToByteArray(authorizationRolesAsList));
            
            xscAccessDefinition.setCreatedBy(UserFacade.getName());
            xscAccessDefinition.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                xscAccessDefinitionPersistenceManager.insert(connection, xscAccessDefinition);
                clearCache();
                return xscAccessDefinition;
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException | IOException | XSKPrivilegeException е) {
            throw new XSKAccessException(е);
        }
    }

    @Override
    public XSKAccessDefinition updateXSKAccessDefinition(String path, String authenticationMethod, String hash, boolean exposed, List<String> authorizationRolesAsList) throws XSKAccessException {
        try {
            XSKAccessDefinition xscAccessDefinition = getXSKAccessDefinition(path);
            xscAccessDefinition.setAuthenticationMethod(authenticationMethod);
            xscAccessDefinition.setHash(hash);
            xscAccessDefinition.setExposed(exposed);
            if (authorizationRolesAsList != null && !authorizationRolesAsList.isEmpty()) {
	            validatePrivileges(authorizationRolesAsList);
	            xscAccessDefinition.setAuthorizationRoles(objectToByteArray(authorizationRolesAsList));
            }

            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                xscAccessDefinitionPersistenceManager.update(connection, xscAccessDefinition);
                clearCache();
                return xscAccessDefinition;
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException | IOException | XSKPrivilegeException е) {
            throw new XSKAccessException(е);
        }
    }

    @Override
    public XSKAccessDefinition getXSKAccessDefinition(String id) throws XSKAccessException {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                XSKAccessDefinition xscAccessDefinition = xscAccessDefinitionPersistenceManager.find(connection, XSKAccessDefinition.class, id);
                if (xscAccessDefinition == null) {
                    return null;
                }

                List<String> authorizationRoles = XSKUtils.byteArrayToObject(xscAccessDefinition.getAuthorizationRoles());
                xscAccessDefinition.setAuthorizationRolesAsList(authorizationRoles);
                return xscAccessDefinition;
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new XSKAccessException(e);
        }
    }

    @Override
    public List<XSKAccessDefinition> getAccessXSKDefinitions() throws XSKAccessException {
        if (!CACHE.isEmpty()) {
            return Collections.unmodifiableList(CACHE);
        }

        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                List<XSKAccessDefinition> xscAccessDefinitions = xscAccessDefinitionPersistenceManager.findAll(connection, XSKAccessDefinition.class);
                for (XSKAccessDefinition xscAccessDefinition : xscAccessDefinitions) {
                	if (xscAccessDefinition.getAuthorizationRoles() != null) {
                		List<String> authorizationRoles = XSKUtils.byteArrayToObject(xscAccessDefinition.getAuthorizationRoles());
                		xscAccessDefinition.setAuthorizationRolesAsList(authorizationRoles);
                	} else {
                		xscAccessDefinition.setAuthorizationRolesAsList(new ArrayList());
                	}
                }
                CACHE.addAll(xscAccessDefinitions);
                return Collections.unmodifiableList(xscAccessDefinitions);
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new XSKAccessException(e);
        }
    }

    @Override
    public void removeXSKAccessDefinition(String path) throws XSKAccessException {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();
                xscAccessDefinitionPersistenceManager.delete(connection, XSKAccessDefinition.class, path);
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new XSKAccessException(e);
        }
    }



    @Override
    public boolean existsXSKAccessDefinition(String path) throws XSKAccessException {
        return getXSKAccessDefinition(path) != null;
    }

    @Override
    public XSKAccessDefinition parseXSAccessArtifact(byte[] json) {
        XSKAccessArtifact accessArtifact = XSKAccessArtifact.parse(json);
        return accessArtifact.toXSKAccessDefinition();
    }

    @Override
    public void clearCache() {
        CACHE.clear();
    }

    private void validatePrivileges(List<String> privileges) throws XSKPrivilegeException {
        for (String privilege : privileges) {
            if (!xscPrivilegeCoreService.xscPrivilegeExists(privilege)) {
                throw new XSKPrivilegeException(String.format("No XSK Privilege found with name:{%s}", privilege));
            }
        }
    }
}