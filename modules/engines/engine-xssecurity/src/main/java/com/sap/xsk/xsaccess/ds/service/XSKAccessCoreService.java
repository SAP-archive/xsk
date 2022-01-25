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
package com.sap.xsk.xsaccess.ds.service;

import static com.sap.xsk.utils.XSKUtils.objectToByteArray;

import com.sap.xsk.utils.XSKUtils;
import com.sap.xsk.xsaccess.ds.api.IXSKAccessCoreService;
import com.sap.xsk.xsaccess.ds.api.XSKAccessException;
import com.sap.xsk.xsaccess.ds.api.XSKPrivilegeException;
import com.sap.xsk.xsaccess.ds.model.access.XSKAccessArtifact;
import com.sap.xsk.xsaccess.ds.model.access.XSKAccessDefinition;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.persistence.PersistenceManager;

public class XSKAccessCoreService implements IXSKAccessCoreService {

  private static final List<XSKAccessDefinition> CACHE = Collections.synchronizedList(new ArrayList<>());
  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);
  private PersistenceManager<XSKAccessDefinition> xskAccessDefinitionPersistenceManager = new PersistenceManager<XSKAccessDefinition>();
  private XSKPrivilegeCoreService xskPrivilegeCoreService = new XSKPrivilegeCoreService();

  @Override
  public XSKAccessDefinition createXSKAccessDefinition(String path, List<String> authenticationMethodsAsList, String hash, boolean exposed,
      List<String> authorizationRolesAsList) throws XSKAccessException {
    try {
      XSKAccessDefinition xskAccessDefinition = new XSKAccessDefinition();
      xskAccessDefinition.setPath(path);

      if (authenticationMethodsAsList == null) {
        authenticationMethodsAsList = new ArrayList<String>();
        xskAccessDefinition.setAuthenticationMethodsAsList(new ArrayList<String>());
      }
      xskAccessDefinition.setAuthenticationMethods(objectToByteArray(authenticationMethodsAsList));

      xskAccessDefinition.setHash(hash);
      xskAccessDefinition.setExposed(exposed);
      if (authorizationRolesAsList == null) {
        authorizationRolesAsList = new ArrayList<String>();
        xskAccessDefinition.setAuthorizationRolesAsList(new ArrayList<String>());
      }

      validatePrivileges(authorizationRolesAsList);
      xskAccessDefinition.setAuthorizationRoles(objectToByteArray(authorizationRolesAsList));

      xskAccessDefinition.setCreatedBy(UserFacade.getName());
      xskAccessDefinition.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

      Connection connection = null;
      try {
        connection = dataSource.getConnection();
        xskAccessDefinitionPersistenceManager.insert(connection, xskAccessDefinition);
        clearCache();
        return xskAccessDefinition;
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
  public XSKAccessDefinition updateXSKAccessDefinition(String path, List<String> authenticationMethodsAsList, String hash, boolean exposed,
      List<String> authorizationRolesAsList) throws XSKAccessException {
    try {
      XSKAccessDefinition xskAccessDefinition = getXSKAccessDefinition(path);
      if (authenticationMethodsAsList != null && !authenticationMethodsAsList.isEmpty()) {
        xskAccessDefinition.setAuthenticationMethods(objectToByteArray(authenticationMethodsAsList));
      }
      xskAccessDefinition.setHash(hash);
      xskAccessDefinition.setExposed(exposed);
      if (authorizationRolesAsList != null && !authorizationRolesAsList.isEmpty()) {
        validatePrivileges(authorizationRolesAsList);
        xskAccessDefinition.setAuthorizationRoles(objectToByteArray(authorizationRolesAsList));
      }

      Connection connection = null;
      try {
        connection = dataSource.getConnection();
        xskAccessDefinitionPersistenceManager.update(connection, xskAccessDefinition);
        clearCache();
        return xskAccessDefinition;
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
        XSKAccessDefinition xskAccessDefinition = xskAccessDefinitionPersistenceManager.find(connection, XSKAccessDefinition.class, id);
        if (xskAccessDefinition == null) {
          return null;
        }

        List<String> authenticationMethods = XSKUtils.byteArrayToObject(xskAccessDefinition.getAuthenticationMethods());
        xskAccessDefinition.setAuthenticationMethodsAsList(authenticationMethods);

        List<String> authorizationRoles = XSKUtils.byteArrayToObject(xskAccessDefinition.getAuthorizationRoles());
        xskAccessDefinition.setAuthorizationRolesAsList(authorizationRoles);
        return xskAccessDefinition;
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
        List<XSKAccessDefinition> xskAccessDefinitions = xskAccessDefinitionPersistenceManager
            .findAll(connection, XSKAccessDefinition.class);
        for (XSKAccessDefinition xskAccessDefinition : xskAccessDefinitions) {
          if (xskAccessDefinition.getAuthorizationRoles() != null) {
            List<String> authorizationRoles = XSKUtils.byteArrayToObject(xskAccessDefinition.getAuthorizationRoles());
            xskAccessDefinition.setAuthorizationRolesAsList(authorizationRoles);
          } else {
            xskAccessDefinition.setAuthorizationRolesAsList(new ArrayList<String>());
          }
        }
        CACHE.addAll(xskAccessDefinitions);
        return Collections.unmodifiableList(xskAccessDefinitions);
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
        xskAccessDefinitionPersistenceManager.delete(connection, XSKAccessDefinition.class, path);
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
      if (!xskPrivilegeCoreService.xskPrivilegeExists(privilege)) {
        throw new XSKPrivilegeException(String.format("No XSK Privilege found with name: {%s}", privilege));
      }
    }
  }
}