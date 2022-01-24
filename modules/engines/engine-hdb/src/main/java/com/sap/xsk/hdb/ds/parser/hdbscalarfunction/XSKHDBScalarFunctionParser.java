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
package com.sap.xsk.hdb.ds.parser.hdbscalarfunction;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKHDBUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import java.sql.Timestamp;

public class XSKHDBScalarFunctionParser implements XSKDataStructureParser<XSKDataStructureHDBTableFunctionModel> {

  @Override
  public XSKDataStructureHDBTableFunctionModel parse(String location, String content) throws XSKDataStructuresException {
    XSKDataStructureHDBTableFunctionModel hdbTableFunction = new XSKDataStructureHDBTableFunctionModel();
    hdbTableFunction.setName(XSKHDBUtils.extractTableFunctionNameFromContent(content, location, XSKCommonsConstants.HDB_SCALAR_FUNCTION_PARSER));
    hdbTableFunction.setLocation(location);
    hdbTableFunction.setType(getType());
    hdbTableFunction.setHash(DigestUtils.md5Hex(content));
    hdbTableFunction.setCreatedBy(UserFacade.getName());
    hdbTableFunction.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    hdbTableFunction.setContent(content);
    return hdbTableFunction;
  }

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_SCALARFUNCTION;
  }

  @Override
  public Class<XSKDataStructureHDBTableFunctionModel> getDataStructureClass() {
    return XSKDataStructureHDBTableFunctionModel.class;
  }
}



