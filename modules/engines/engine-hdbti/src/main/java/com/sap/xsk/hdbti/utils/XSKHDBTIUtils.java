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
package com.sap.xsk.hdbti.utils;

public class XSKHDBTIUtils {
    private XSKHDBTIUtils() {
    }

    /**
     * Convert fileName in format "workspace.folder:fileName.csv" to a format "workspace.folder:fileName.csv"
     * For example:
     * convert from "sap.ti2.demo:myData.csv" to "/sap/ti2/demo/myData.csv"
     *
     * @return
     */
    public static String convertHDBTIFilePropertyToPath(String fileNamePath) {
        String fileName = fileNamePath.substring(fileNamePath.lastIndexOf(':') + 1);
        return "/" + fileNamePath.substring(0, fileNamePath.indexOf(':')).replaceAll("\\.", "/") + "/" + fileName;
    }

    /**
     * Convert fileName in format "workspace.folder:fileName.csv" to a format "workspace.folder:fileName.csv"
     * For example:
     * convert from "/sap/ti2/demo/myData.csv" to "sap.ti2.demo:myData.csv"
     *
     * @return converted string
     */
    public static String convertPathToHDBTIFileProperty(String fileNamePath) {
        String fileName = fileNamePath.substring(fileNamePath.lastIndexOf('/') + 1);
        if (fileNamePath.startsWith("/")) fileNamePath = fileNamePath.substring(1, fileNamePath.length());
        return fileNamePath.substring(0, fileNamePath.indexOf('/' + fileName)).replaceAll("\\/", ".") + ":" + fileName;
    }
}
