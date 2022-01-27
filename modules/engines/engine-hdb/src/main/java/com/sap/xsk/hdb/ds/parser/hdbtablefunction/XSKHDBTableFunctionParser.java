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
package com.sap.xsk.hdb.ds.parser.hdbtablefunction;

import java.sql.Timestamp;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.parser.AbstractXSKDataStructureParser;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKHDBUtils;

public class XSKHDBTableFunctionParser extends AbstractXSKDataStructureParser<XSKDataStructureHDBTableFunctionModel> {

  @Override
  public XSKDataStructureHDBTableFunctionModel parse(XSKDataStructureParametersModel parametersModel) throws XSKDataStructuresException {
    XSKDataStructureHDBTableFunctionModel hdbTableFunction = new XSKDataStructureHDBTableFunctionModel();
    hdbTableFunction.setName(XSKHDBUtils.extractTableFunctionNameFromContent(parametersModel.getContent(), parametersModel.getLocation(),
        XSKCommonsConstants.HDB_TABLE_FUNCTION_PARSER));
    hdbTableFunction.setLocation(parametersModel.getLocation());
    hdbTableFunction.setType(getType());
    hdbTableFunction.setHash(DigestUtils.md5Hex(parametersModel.getContent()));
    hdbTableFunction.setCreatedBy(UserFacade.getName());
    hdbTableFunction.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    hdbTableFunction.setContent(parametersModel.getContent());
    return hdbTableFunction;
  }

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION;
  }

  @Override
  public Class<XSKDataStructureHDBTableFunctionModel> getDataStructureClass() {
    return XSKDataStructureHDBTableFunctionModel.class;
  }
}
