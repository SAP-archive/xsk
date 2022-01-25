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
package com.sap.xsk.xsaccess.ds.api;

import com.sap.xsk.xsaccess.ds.model.access.XSKAccessDefinition;
import java.util.List;

public interface IXSKAccessCoreService {

  String XSK_FILE_EXTENSION_ACCESS = ".xsaccess";

  XSKAccessDefinition createXSKAccessDefinition(String path, List<String> authenticationMethodAsList, String hash, boolean exposed,
      List<String> authorizationRolesAsList) throws XSKAccessException;

  XSKAccessDefinition updateXSKAccessDefinition(String path, List<String> authenticationMethodAsList, String hash, boolean exposed,
      List<String> authorizationRolesAsList) throws XSKAccessException;

  XSKAccessDefinition getXSKAccessDefinition(String id) throws XSKAccessException;

  List<XSKAccessDefinition> getAccessXSKDefinitions() throws XSKAccessException;

  void removeXSKAccessDefinition(String path) throws XSKAccessException;

  boolean existsXSKAccessDefinition(String path) throws XSKAccessException;

  XSKAccessDefinition parseXSAccessArtifact(byte[] json);

  void clearCache();
}