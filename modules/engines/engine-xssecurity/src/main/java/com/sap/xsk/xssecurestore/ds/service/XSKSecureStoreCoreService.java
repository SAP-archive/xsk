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
package com.sap.xsk.xssecurestore.ds.service;

import com.google.gson.JsonSyntaxException;
import com.sap.xsk.xssecurestore.ds.api.IXSKSecureStoreCoreService;
import com.sap.xsk.xssecurestore.ds.api.IXSKSecureStoreModel;
import com.sap.xsk.xssecurestore.ds.api.XSKSecureStoreException;
import com.sap.xsk.xssecurestore.ds.model.XSKSecureStore;
import com.sap.xsk.xssecurestore.ds.model.XSKSecureStoreContent;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;
import org.eclipse.dirigible.database.persistence.PersistenceManager;

@Singleton
public class XSKSecureStoreCoreService implements IXSKSecureStoreCoreService {

  @Inject
  private DataSource dataSource;

  @Inject
  private PersistenceManager<XSKSecureStore> secureStorePersistenceManager;

  @Inject
  private PersistenceManager<XSKSecureStoreContent> secureStoreContentPersistenceManager;

  @Inject
  private XSKSecureStoreEncryptor xskSecureStoreEncryptor;

  @Override
  public XSKSecureStore createSecureStore(String location, String content) throws XSKSecureStoreException {
    if (!isJSONValid(content) || content.isEmpty()) {
      throw new XSKSecureStoreException("Invalid json at " + location);
    }

    XSKSecureStore XSKSecureStore1 = new XSKSecureStore();
    XSKSecureStore1.setLocation(location);
    XSKSecureStore1.setActive(true);

    try {
      Connection connection = null;
      try {
        connection = dataSource.getConnection();
        secureStorePersistenceManager.insert(connection, XSKSecureStore1);
        return XSKSecureStore1;
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    } catch (SQLException e) {
      throw new XSKSecureStoreException(e.getMessage());
    }
  }

  @Override
  public List<XSKSecureStore> getXSKSecureStores() throws XSKSecureStoreException {
    try {
      Connection connection = null;
      try {
        connection = dataSource.getConnection();
        return secureStorePersistenceManager.findAll(connection, XSKSecureStore.class);
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    } catch (SQLException e) {
      throw new XSKSecureStoreException(e);
    }
  }

  @Override
  public void removeXSKSecureStore(String location) throws XSKSecureStoreException {
    try {
      Connection connection = null;
      try {
        connection = dataSource.getConnection();
        secureStorePersistenceManager.delete(connection, XSKSecureStore.class, location);
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    } catch (SQLException e) {
      throw new XSKSecureStoreException(e);
    }
  }

  @Override
  public boolean existsSecureStore(String location) throws XSKSecureStoreException {
    return getSecureStore(location) != null;
  }

  @Override
  public XSKSecureStore getSecureStore(String location) throws XSKSecureStoreException {
    try {
      Connection connection = null;
      try {
        connection = dataSource.getConnection();
        return secureStorePersistenceManager.find(connection, XSKSecureStore.class, location);
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    } catch (SQLException e) {
      throw new XSKSecureStoreException(e.getMessage());
    }
  }

  @Override
  public void createSecureStoreValue(String storeId, String userId, String dataId, String value) throws XSKSecureStoreException {
    if (!existsSecureStore(storeId)) {
      throw new XSKSecureStoreException("Non existing active secure store with id: " + storeId);
    }

    byte[] dataValueAsBytes = value.getBytes(StandardCharsets.UTF_8);

    XSKSecureStoreContent existingXscSecureStoreContent = findSecureStoreContent(storeId, userId, dataId);
    if (existingXscSecureStoreContent != null) {
      existingXscSecureStoreContent.setDataValue(xskSecureStoreEncryptor.encode(dataValueAsBytes));
      updateSecureStoreValue(existingXscSecureStoreContent);
      return;
    }

    XSKSecureStoreContent xskSecureStoreContent = new XSKSecureStoreContent();
    xskSecureStoreContent.setStoreId(storeId);
    xskSecureStoreContent.setUserId(userId);
    xskSecureStoreContent.setDataId(dataId);

    xskSecureStoreContent.setDataValue(xskSecureStoreEncryptor.encode(dataValueAsBytes));

    try {
      Connection connection = null;
      try {
        connection = dataSource.getConnection();
        secureStoreContentPersistenceManager.insert(connection, xskSecureStoreContent);
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    } catch (SQLException e) {
      throw new XSKSecureStoreException(e.getMessage());
    }
  }

  @Override
  public void updateSecureStoreValue(XSKSecureStoreContent xskSecureStoreContent) throws XSKSecureStoreException {
    try {
      Connection connection = null;
      try {
        connection = dataSource.getConnection();
        secureStoreContentPersistenceManager.update(connection, xskSecureStoreContent);
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    } catch (SQLException e) {
      throw new XSKSecureStoreException(e.getMessage());
    }
  }

  @Override
  public XSKSecureStoreContent findSecureStoreContent(String storeId, String userId, String dataId) throws XSKSecureStoreException {
    List<Object> queryArguments = Arrays.asList(storeId, userId, dataId);

    try {
      Connection connection = null;

      try {
        connection = dataSource.getConnection();
        List<XSKSecureStoreContent> foundContent = secureStoreContentPersistenceManager
            .query(connection, XSKSecureStoreContent.class, IXSKSecureStoreModel.SECURE_STORE_VALUE_FIND_STATEMENT, queryArguments);

        if (foundContent.size() == 0) {
          return null;
        } else if (foundContent.size() == 1) {
          XSKSecureStoreContent xskSecureStoreContent = foundContent.get(0);
          byte[] encodedData = xskSecureStoreContent.getDataValue();
          xskSecureStoreContent.setDataValue(xskSecureStoreEncryptor.decode(encodedData));
          return foundContent.get(0);
        } else {
          throw new SQLException("Duplicate security createSecureStoreValue content identifier");
        }

      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    } catch (SQLException e) {
      throw new XSKSecureStoreException(e.getMessage());
    }
  }

  @Override
  public void deleteSecureStoreValue(String storeId, String userId, String dataId) throws XSKSecureStoreException {
    if (!existsSecureStoreContent(storeId, userId, dataId)) {
      throw new XSKSecureStoreException("No such secure createSecureStoreValue value to be removed.");
    }

    List<Object> queryArguments = Arrays.asList(storeId, userId, dataId);

    try {
      Connection connection = null;

      try {
        connection = dataSource.getConnection();
        secureStoreContentPersistenceManager.execute(connection, IXSKSecureStoreModel.SECURE_STORE_VALUE_DELETE_STATEMENT, queryArguments);
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    } catch (SQLException e) {
      throw new XSKSecureStoreException(e.getMessage());
    }
  }

  @Override
  public void deleteSecureStoreValuesByStoreId(String userId) throws XSKSecureStoreException {

    try {
      Connection connection = null;

      try {
        connection = dataSource.getConnection();
        secureStoreContentPersistenceManager
            .execute(connection, IXSKSecureStoreModel.SECURE_STORE_DELETE_BY_STORE_ID, Arrays.asList(userId));
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    } catch (SQLException e) {
      throw new XSKSecureStoreException(e.getMessage());
    }
  }

  @Override
  public boolean existsSecureStoreContent(String storeId, String userId, String dataId) throws XSKSecureStoreException {
    return findSecureStoreContent(storeId, userId, dataId) != null;
  }

  private boolean isJSONValid(String content) {
    try {
      GsonHelper.PARSER.parse(content);
      return true;
    } catch (JsonSyntaxException ex) {
      return false;
    }

  }
}
