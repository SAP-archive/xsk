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
package com.sap.xsk.hdb.ds.parser.hdi;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.api.helpers.GsonHelper;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

public class XSKHDIParser implements XSKDataStructureParser {
    @Override
    public XSKDataStructureHDIModel parse(String location, String content) throws XSKDataStructuresException, IOException {
    	XSKDataStructureHDIModel hdiModel = GsonHelper.GSON.fromJson(content, XSKDataStructureHDIModel.class);
        hdiModel.setName(new File(location).getName());
        hdiModel.setLocation(location);
        hdiModel.setType(getType());
        hdiModel.setHash(DigestUtils.md5Hex(content));
        hdiModel.setCreatedBy(UserFacade.getName());
        hdiModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        hdiModel.setContent(content);
        return hdiModel;
    }

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_HDI;
    }

    @Override
    public Class getDataStructureClass() {
        return XSKDataStructureHDIModel.class;
    }
}
