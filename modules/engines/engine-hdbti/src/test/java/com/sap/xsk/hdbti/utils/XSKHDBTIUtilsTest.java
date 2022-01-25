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
package com.sap.xsk.hdbti.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XSKHDBTIUtilsTest {
    @Test
    public void testConvertHDBTIFilePropertyToPathSuccessfully() {
        String fileNamePath = "sap.ti2.demo:myData.csv";
        assertEquals(XSKHDBTIUtils.convertHDBTIFilePropertyToPath(fileNamePath), "/sap/ti2/demo/myData.csv");
    }

    @Test
    public void testConvertPathToHDBTIFilePropertySuccessfully() {
        String fileNamePath = "/sap/ti2/demo/myData.csv";
        assertEquals(XSKHDBTIUtils.convertPathToHDBTIFileProperty(fileNamePath), "sap.ti2.demo:myData.csv");
    }

    @Test
    public void testConvertPathToHDBTIFilePropertySuccessfully2() {
        String fileNamePath = "sap/ti2/demo/myData.csv";
        assertEquals(XSKHDBTIUtils.convertPathToHDBTIFileProperty(fileNamePath), "sap.ti2.demo:myData.csv");
    }
}
