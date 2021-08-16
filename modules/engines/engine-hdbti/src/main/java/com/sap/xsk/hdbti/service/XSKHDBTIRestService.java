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
package com.sap.xsk.hdbti.service;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdbti.utils.XSKHDBTIUtils;
import com.sap.xsk.parser.hdbti.custom.XSKHDBTIParser;
import com.sap.xsk.parser.hdbti.exception.XSKHDBTISyntaxErrorException;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportConfigModel;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportModel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class XSKHDBTIRestService {

    private final XSKHDBTIParser xskhdbtiParser = new XSKHDBTIParser();

    public List<XSKHDBTIImportConfigModel> parseHdbtiToJSON(String location, byte[] file) throws XSKArtifactParserException, IOException, XSKHDBTISyntaxErrorException {
        XSKHDBTIImportModel parsedFile = xskhdbtiParser.parse(location, new String(file, StandardCharsets.UTF_8));
        parsedFile.getConfigModels().forEach(el -> el.setFileName(XSKHDBTIUtils.convertHDBTIFilePropertyToPath(el.getFileName())));
        return parsedFile.getConfigModels();
    }

    public String parseJSONtoHdbti(ArrayList<XSKHDBTIImportConfigModel> json) {
        json.forEach(el -> el.setFileName(XSKHDBTIUtils.convertPathToHDBTIFileProperty(el.getFileName())));
        XSKHDBTIImportModel model = new XSKHDBTIImportModel();
        model.setConfigModels(json);
        return model.toString();
    }
}
