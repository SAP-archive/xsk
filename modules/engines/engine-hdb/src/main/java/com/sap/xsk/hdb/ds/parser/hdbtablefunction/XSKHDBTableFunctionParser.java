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
package com.sap.xsk.hdb.ds.parser.hdbtablefunction;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import java.sql.Timestamp;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

public class XSKHDBTableFunctionParser implements XSKDataStructureParser<XSKDataStructureHDBTableFunctionModel> {

  private String extractTableFunctionNameFromContent(String content) throws XSKDataStructuresException {
    int indexOfBracket = content.indexOf('(');
    int indexOfEndOfProcKeyword = content.toLowerCase().indexOf("function") + "function".length();
    if (indexOfBracket > -1 && indexOfEndOfProcKeyword > -1) {
      String procNameWithWhiteSymbols = content.substring(indexOfEndOfProcKeyword, indexOfBracket);
      return procNameWithWhiteSymbols.replace("\\s", "").trim();
    }
    throw new XSKDataStructuresException("HDB Table Function file not correct");
  }


  @Override
  public XSKDataStructureHDBTableFunctionModel parse(String location, String content) throws XSKDataStructuresException {
    XSKDataStructureHDBTableFunctionModel hdbTableFunction = new XSKDataStructureHDBTableFunctionModel();
    hdbTableFunction.setName(extractTableFunctionNameFromContent(content));
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
    return IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION;
  }

  @Override
  public Class<XSKDataStructureHDBTableFunctionModel> getDataStructureClass() {
    return XSKDataStructureHDBTableFunctionModel.class;
  }
}
