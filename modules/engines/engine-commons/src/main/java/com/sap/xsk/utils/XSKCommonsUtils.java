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
package com.sap.xsk.utils;

import com.sap.xsk.exceptions.XSKArtifactParserException;

import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;

public class XSKCommonsUtils {

    private XSKCommonsUtils() {
    }

    /**
     * Extract the object base name from catalog name.
     * Catalog name includes the package path, the separating dots, and the object base name.
     * For example: sap.test.hana.db::myObject
     *
     * @param catalogName string in format PACKAGE_PATH::OBJECT_BASE_NAME
     * @return string representing the OBJECT_BASE_NAME
     * @see <a href="https://help.sap.com/viewer/52715f71adba4aaeb480d946c742d1f6/2.0.03/en-US/016a60fe929a4e9e89bbb3b6f7aad409.html">SAP HANA Repository Packages and Namespaces</a>
     */
    public static String extractBaseObjectNameFromCatalogName(String catalogName) {
        String[] extractedName = catalogName.split("::");
        if (extractedName.length == 2)
            return extractedName[1];
        return catalogName;
    }

    /**
     * Assemble Repository Package name from file location.
     * For example "com.sap.test.hana.db"
     *
     * @param location String representing file location path
     * @return String representing assemble repository package
     * @see <a href="https://help.sap.com/viewer/52715f71adba4aaeb480d946c742d1f6/2.0.03/en-US/016a60fe929a4e9e89bbb3b6f7aad409.html">SAP HANA Repository Packages and Namespaces</a>
     */
    public static String getRepositoryNamespace(String location) {
        String namespacePart = FilenameUtils.getFullPathNoEndSeparator(location);
        namespacePart = namespacePart.replace(XSKCommonsConstants.UNIX_SEPARATOR, '.');
        namespacePart = namespacePart.replace(XSKCommonsConstants.WINDOWS_SEPARATOR, '.');
        if (namespacePart.startsWith(".")) {
            namespacePart = namespacePart.substring(1);
        }
        return namespacePart;
    }

    /**
     * User to log errors from antl4 parsers
     * @param errorMessages
     * @param location
     * @param artifactType
     * @param logger
     * @throws XSKArtifactParserException
     */
    public static void logParserErrors(ArrayList<String> errorMessages, String location, String artifactType, Logger logger) throws XSKArtifactParserException {
        if (errorMessages.size() > 0) {
            for (String errorMessage : errorMessages) {
                logger.error(String.format(
                        "Wrong format of %s: [%s] during parsing.: %s",
                        artifactType, location, errorMessage));
            }
            throw new XSKArtifactParserException(String.format(
                    "Wrong format of HDB View: [%s] during parsing. Ensure you are using the correct format for the correct compatibility version.",
                    location));
        }
    }
}
