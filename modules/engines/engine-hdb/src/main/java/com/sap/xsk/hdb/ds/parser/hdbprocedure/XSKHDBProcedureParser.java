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
package com.sap.xsk.hdb.ds.parser.hdbprocedure;

import java.sql.Timestamp;

import com.sap.xsk.utils.XSKHDBUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

public class XSKHDBProcedureParser implements XSKDataStructureParser {

  @Override
  public XSKDataStructureHDBProcedureModel parse(String location, String content) throws XSKDataStructuresException {
    XSKDataStructureHDBProcedureModel hdbProcedure = new XSKDataStructureHDBProcedureModel();
    hdbProcedure.setName(XSKHDBUtils.extractProcedureNameFromContent(content, location));
    hdbProcedure.setLocation(location);
    hdbProcedure.setType(getType());
    hdbProcedure.setHash(DigestUtils.md5Hex(content));
    hdbProcedure.setCreatedBy(UserFacade.getName());
    hdbProcedure.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    hdbProcedure.setContent(content);
    return hdbProcedure;
  }

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_PROCEDURE;
  }

  @Override
  public Class getDataStructureClass() {
    return XSKDataStructureHDBProcedureModel.class;
  }
}
