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
package com.sap.xsk.hdb.ds.parser.hdbschema;

import java.sql.Timestamp;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

public class XSKHDBSchemaParser implements XSKDataStructureParser {
  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_SCHEMA;
  }

  @Override
  public Class getDataStructureClass() {
    return XSKDataStructureHDBSchemaModel.class;
  }

  @Override
  public XSKDataStructureHDBSchemaModel parse(String location, String content) {
    XSKDataStructureHDBSchemaModel hdbProcedure = new XSKDataStructureHDBSchemaModel();
    hdbProcedure.setName(extractSchemaNameFromContent(content));
    hdbProcedure.setLocation(location);
    hdbProcedure.setType(IXSKDataStructureModel.TYPE_HDB_SCHEMA);
    hdbProcedure.setHash(DigestUtils.md5Hex(content));
    hdbProcedure.setCreatedBy(UserFacade.getName());
    hdbProcedure.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    return hdbProcedure;
  }

  private String extractSchemaNameFromContent(String content) {
    Integer indexOfEquals = content.indexOf('=');
    String schemaName = content.substring(indexOfEquals + 1);
    return schemaName.replaceAll("\\s", "");
  }
}
