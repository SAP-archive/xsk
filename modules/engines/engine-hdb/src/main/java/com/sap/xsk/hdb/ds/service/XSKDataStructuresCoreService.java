/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.service;

import static java.text.MessageFormat.format;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.eclipse.dirigible.database.sql.SqlFactory;

import com.sap.xsk.hdb.ds.api.IXSKDataStructuresCoreService;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.service.parser.IXSKCoreParserService;
import com.sap.xsk.hdb.ds.service.parser.XSKCoreParserService;

public class XSKDataStructuresCoreService implements IXSKDataStructuresCoreService {

  private DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.SYSTEM_DATASOURCE);

  private PersistenceManager<XSKDataStructureModel> persistenceManager = new PersistenceManager<XSKDataStructureModel>();

  private IXSKCoreParserService xskCoreParserService = new XSKCoreParserService();

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#createDataStructure(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public XSKDataStructureModel createDataStructure(String location, String name, String hash, String type)
      throws XSKDataStructuresException {
    XSKDataStructureModel dataStructure = new XSKDataStructureModel();
    dataStructure.setLocation(location);
    dataStructure.setName(name);
    dataStructure.setType(type);
    dataStructure.setHash(hash);
    dataStructure.setCreatedBy(UserFacade.getName());
    dataStructure.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

    try (Connection connection = dataSource.getConnection()) {
      persistenceManager.insert(connection, dataStructure);
      return dataStructure;
    } catch (SQLException e) {
      throw new XSKDataStructuresException(e);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#getDataStructure(java.lang.String, java.lang.String)
   */
  @Override
  public <T extends XSKDataStructureModel> T getDataStructure(String location, String type) throws XSKDataStructuresException {
    try (Connection connection = dataSource.getConnection()) {
      return (T) persistenceManager.find(connection, xskCoreParserService.getDataStructureClass(type), location);
    } catch (SQLException e) {
      throw new XSKDataStructuresException(e);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#getDataStructureByName(java.lang.String, java.lang.String)
   */
  @Override
  public <T extends XSKDataStructureModel> T getDataStructureByName(String name, String type) throws XSKDataStructuresException {
    try (Connection connection = dataSource.getConnection()) {
      String sql = SqlFactory.getNative(connection).select().column("*").from("XSK_DATA_STRUCTURES")
          .where("DS_NAME = ? AND DS_TYPE = ?").toString();
      List<XSKDataStructureModel> dataStructures = persistenceManager.query(connection, XSKDataStructureModel.class, sql,
          Arrays.asList(name, type));
      if (dataStructures.isEmpty()) {
        return null;
      }
      if (dataStructures.size() > 1) {
        throw new XSKDataStructuresException(format("There are more that one Table with the same name [{0}] at locations: [{1}] and [{2}].",
            name, dataStructures.get(0).getLocation(), dataStructures.get(1).getLocation()));
      }
      return (T) dataStructures.get(0);
    } catch (SQLException e) {
      throw new XSKDataStructuresException(e);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#removeDataStructure(java.lang.String)
   */
  @Override
  public void removeDataStructure(String location) throws XSKDataStructuresException {
    try (Connection connection = dataSource.getConnection()) {
      persistenceManager.delete(connection, XSKDataStructureModel.class, location);
    } catch (SQLException e) {
      throw new XSKDataStructuresException(e);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#updateDataStructure(java.lang.String,
   * java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public void updateDataStructure(String location, String name, String hash, String type) throws XSKDataStructuresException {
    try (Connection connection = dataSource.getConnection()) {
      XSKDataStructureModel dataStructure = getDataStructure(location, type);
      dataStructure.setName(name);
      dataStructure.setHash(hash);
      persistenceManager.update(connection, dataStructure);
    } catch (SQLException e) {
      throw new XSKDataStructuresException(e);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#getDataStructures(java.lang.String)
   */
  @Override
  public <T extends XSKDataStructureModel> List<T> getDataStructuresByType(String type) throws XSKDataStructuresException {
    try (Connection connection = dataSource.getConnection()) {
      String sql = SqlFactory.getNative(connection).select().column("*").from("XSK_DATA_STRUCTURES").where("DS_TYPE = ?").toString();
      return (List<T>) persistenceManager.query(connection, xskCoreParserService.getDataStructureClass(type), sql,
          Arrays.asList(type));
    } catch (SQLException e) {
      throw new XSKDataStructuresException(e);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#existsDataStructure(java.lang.String, java.lang.String)
   */
  @Override
  public boolean existsDataStructure(String location, String type) throws XSKDataStructuresException {
    return getDataStructure(location, type) != null;
  }

  @Override
  public boolean existsDataStructureByLocationAndHash(String location, String hash, String type) throws XSKDataStructuresException {
    try (Connection connection = dataSource.getConnection()) {
      String sql = SqlFactory.getNative(connection).select().column("*").from("XSK_DATA_STRUCTURES")
          .where("DS_LOCATION = ? AND DS_HASH = ?").toString();
      return !persistenceManager.query(connection, xskCoreParserService.getDataStructureClass(type), sql,
          Arrays.asList(location, hash)).isEmpty();
    } catch (SQLException e) {
      throw new XSKDataStructuresException(e);
    }
  }
}
