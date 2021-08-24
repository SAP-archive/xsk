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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Convert fileName in format "workspace/folder/fileName.csv" to a format "workspace.folder:fileName.csv"
     * For example:
     * convert from "/sap/ti2/demo/myData.csv" to "sap.ti2.demo:myData.csv"
     *
     * @return converted string
     */
    public static String convertPathToHDBTIFileProperty(String fileNamePath) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("((?:[^\\/]*\\/)*)(.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(fileNamePath.trim());
        boolean matchFound = matcher.find();
        if (matchFound) {
            String fileName = matcher.group(2);
            if (!isCorrectPropertySyntax(fileName))
                throw new IllegalArgumentException("Incorrect format of filePath: " + fileNamePath);
            String filePath = matcher.group(1);
            if (!filePath.equals("")) {
                if (filePath.startsWith("/")) filePath = filePath.substring(1);
                if (filePath.endsWith("/")) filePath = filePath.substring(0, filePath.length() - 1);
                return filePath.replaceAll("\\/", ".") + ":" + fileName;
            }
            return fileName;
        } else {
            throw new IllegalArgumentException("Incorrect format of filePath: " + fileNamePath);
        }
    }


    /**
     * Check if the property support only symbols A-Za-z0-9_-$.
     */
    public static boolean isCorrectPropertySyntax(String property) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("[\\w\\-. $]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(property.trim());
        boolean matchFound = matcher.find();
        if (matchFound) {
            return true;
        } else {
            return false;
        }
    }
}
