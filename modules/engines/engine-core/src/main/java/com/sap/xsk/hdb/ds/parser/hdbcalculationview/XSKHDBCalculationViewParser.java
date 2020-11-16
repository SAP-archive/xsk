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
package com.sap.xsk.hdb.ds.parser.hdbcalculationview;

import java.io.File;
import java.sql.Timestamp;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbcalculationview.XSKDataStructureHDBCalculationViewModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

public class XSKHDBCalculationViewParser implements XSKDataStructureParser {

    @Override
    public XSKDataStructureHDBCalculationViewModel parse(String location, String content) throws XSKDataStructuresException {
    	XSKDataStructureHDBCalculationViewModel hdbCalculationView = new XSKDataStructureHDBCalculationViewModel();
        hdbCalculationView.setName(new File(location).getName());
        hdbCalculationView.setLocation(location);
        hdbCalculationView.setType(getType());
        hdbCalculationView.setHash(DigestUtils.md5Hex(content));
        hdbCalculationView.setCreatedBy(UserFacade.getName());
        hdbCalculationView.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        hdbCalculationView.setContent(content);
        return hdbCalculationView;
    }

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_HDB_CALCVIEW;
    }

    @Override
    public Class getDataStructureClass() {
        return XSKDataStructureHDBCalculationViewModel.class;
    }
}
