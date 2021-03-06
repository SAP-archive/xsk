/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.service.parser;

import com.google.inject.Inject;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

import java.io.IOException;
import java.util.Map;

public class XSKCoreParserService implements IXSKCoreParserService {

    @Inject
    private Map<String, XSKDataStructureParser> parsersByType;

    @Override
    public XSKDataStructureModel parseDataStructure(String type, String location, String content) throws XSKDataStructuresException, IOException {
        XSKDataStructureParser<?> parser = parsersByType.get(type);
        return parser.parse(location, content);
    }

    @Override
    public Class<XSKDataStructureModel> getDataStructureClass(String type) {
        return (Class<XSKDataStructureModel>) parsersByType.get(type).getDataStructureClass();
    }
}
