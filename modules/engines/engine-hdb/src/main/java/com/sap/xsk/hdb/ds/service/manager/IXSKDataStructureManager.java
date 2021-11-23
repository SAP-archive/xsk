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
package com.sap.xsk.hdb.ds.service.manager;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.OperationNotSupportedException;


public interface IXSKDataStructureManager<T extends XSKDataStructureModel> {

  Map<String, T> getDataStructureModels();

  void synchronizeRuntimeMetadata(T tableModel) throws XSKDataStructuresException;

  void createDataStructure(Connection connection, T tableModel) throws SQLException;

  void dropDataStructure(Connection connection, T tableModel) throws SQLException;

  void updateDataStructure(Connection connection, T tableModel)
      throws SQLException, OperationNotSupportedException;

  List<String> getDataStructureSynchronized();

  String getDataStructureType();

  void cleanup() throws XSKDataStructuresException;

  void clearCache();

  void synchronizeParsedByRootMetadata(T tableModel) throws XSKDataStructuresException;

  boolean existsArtifactMetadata(T tableModel) throws XSKDataStructuresException;
}
