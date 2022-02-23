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
package com.sap.xsk.hdb.ds.parser.hdbscalarfunction;

import java.sql.Timestamp;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureParametersModel;
import com.sap.xsk.hdb.ds.model.hdbscalarfunction.XSKDataStructureHDBScalarFunctionModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.utils.XSKCommonsConstants;
import com.sap.xsk.utils.XSKHDBUtils;

public class XSKHDBScalarFunctionParser implements XSKDataStructureParser<XSKDataStructureHDBScalarFunctionModel> {

  @Override
  public XSKDataStructureHDBScalarFunctionModel parse(XSKDataStructureParametersModel parametersModel) throws XSKDataStructuresException {
	  XSKDataStructureHDBScalarFunctionModel hdbScalarFunction = new XSKDataStructureHDBScalarFunctionModel();
    hdbScalarFunction.setName(XSKHDBUtils.extractTableFunctionNameFromContent(parametersModel.getContent(), parametersModel.getLocation(),
        XSKCommonsConstants.HDB_SCALAR_FUNCTION_PARSER));
    hdbScalarFunction.setLocation(parametersModel.getLocation());
    hdbScalarFunction.setType(getType());
    hdbScalarFunction.setHash(DigestUtils.md5Hex(parametersModel.getContent()));
    hdbScalarFunction.setCreatedBy(UserFacade.getName());
    hdbScalarFunction.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    hdbScalarFunction.setContent(parametersModel.getContent());
    return hdbScalarFunction;
  }

  @Override
  public String getType() {
    return IXSKDataStructureModel.TYPE_HDB_SCALAR_FUNCTION;
  }

  @Override
  public Class<XSKDataStructureHDBScalarFunctionModel> getDataStructureClass() {
    return XSKDataStructureHDBScalarFunctionModel.class;
  }
}



