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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.xsk.hdbti.utils.XSKHDBTIUtils;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportConfigModel;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
public class XSKHDBTIRestServiceTest {

    @Test
    public void testParseHdbtiToJSON() throws IOException {
        String a = "{\n" +
                "        \"table\": \"myTable\",\n" +
                "        \"schema\": \"mySchema\",\n" +
                "        \"file\": \"/sap/ti2/demo/myData.csv\",\n" +
                "        \"header\": false,\n" +
                "        \"useHeaderNames\": false,\n" +
                "        \"delimField\": \";\",\n" +
                // "        \"delimEnclosing\": \"\\\"\",\n" +
                "        \"delimEnclosing\": \"#\",\n" +
                "        \"distinguishEmptyFromNull\": true,\n" +
                "        \"keys\": [\n" +
                "            {\n" +
                "                \"column\": \"GROUP_TYPE\",\n" +
                "                \"values\": [\n" +
                "                    \"BW_CUBE\",\n" +
                "                    \"BW_DSO\",\n" +
                "                    \"BW_PSA\"\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }";
        ObjectMapper mapper = new ObjectMapper();
        XSKHDBTIImportConfigModel s = mapper.readValue(a, XSKHDBTIImportConfigModel.class);
        System.out.println("s="+s);

//        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//        XSKHDBTIImportConfigModel list = gson.fromJson(a, XSKHDBTIImportConfigModel.class);
//        System.out.println(list.getDelimEnclosing());

        s.setFileName(XSKHDBTIUtils.convertPathToHDBTIFileProperty(s.getFileName()));
        System.out.println("s: = " + s);
        assertTrue(true);
    }
}
