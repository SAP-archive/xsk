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
package com.sap.xsk.hdb.ds.parser.hdbprocedure;

import java.sql.Timestamp;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

public class XSKHDBProcedureParser implements XSKDataStructureParser {

    private String extractProcedureNameFromContent(String content) throws XSKDataStructuresException {
       int indexOfBracket = content.indexOf('(');
       int indexOfEndOfProcKeyword = content.toLowerCase().indexOf("procedure") + "procedure".length();
       if(indexOfBracket > -1 && indexOfEndOfProcKeyword > -1){
           String procNameWithWhiteSymbols = content.substring(indexOfEndOfProcKeyword, indexOfBracket);
           return procNameWithWhiteSymbols.replace("\\s", "");
       }
       throw new XSKDataStructuresException("HDB Procedure file not correct");
    }


    @Override
    public XSKDataStructureHDBProcedureModel parse(String location, String content) throws XSKDataStructuresException {
        XSKDataStructureHDBProcedureModel hdbProcedure = new XSKDataStructureHDBProcedureModel();
        hdbProcedure.setName(extractProcedureNameFromContent(content));
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
