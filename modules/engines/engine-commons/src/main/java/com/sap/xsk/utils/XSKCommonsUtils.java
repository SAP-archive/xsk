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
package com.sap.xsk.utils;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.parser.models.BaseParserErrorsModel;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.dirigible.api.v3.problems.ProblemsFacade;
import org.eclipse.dirigible.core.problems.exceptions.ProblemsException;
import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Assemble the catalog name of a Repository Base Object(e.g hdbtable, hdbview, hdbsequence, hdbstructure, hdbprocedure)
     * The catalog name includes the package path, the separating dots, and the object base name, as NAMESPACE::OBJECT_BASE_NAME
     * For example: Given location "/projectname/com/sap/hana/example/ItemsByOrder.hdbview",
     * the method will return "com.sap.hana.example::ItemsByOrder"
     *
     * @param location String representing file location path
     * @return String representing assemble catalog name in format "NAMESPACE::OBJECT_BASE_NAME"
     * @implNote Do not apply for CDS Objects
     * @see <a href="https://help.sap.com/viewer/52715f71adba4aaeb480d946c742d1f6/2.0.03/en-US/016a60fe929a4e9e89bbb3b6f7aad409.html">SAP HANA Repository Packages and Namespaces</a>
     */
    public static String getRepositoryBaseObjectName(String location) {
        String objBaseName = FilenameUtils.getBaseName(location);
        String namespace = getRepositoryNamespace(location);
        if (namespace.length() > 0) {
            return new StringBuilder().append(namespace).append("::").append(objBaseName).toString();
        }
        return objBaseName;
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
     * Extraxt corret RepositoryBaseObject definition from content, by removing tabs or spaces between the
     * syntax word and the Object name.
     * For example "VIEW              "hdb_view::ItemsByOrderHANAv2" should be return as "VIEW "hdb_view::ItemsByOrderHANAv2"
     *
     * @param repositoryObjectSyntax syntax as VIEW, TABLE ..etc
     * @param content                String representing file content of a Repository Object
     * @return normalized definition of a Repository Object, consisting of 'SYNTAX_WORD "NAMESPACE::OBJECT_BASE_NAME"'
     */
    public static String extractRepositoryBaseObjectNameFromContent(String repositoryObjectSyntax, String content) {
        Pattern pattern = Pattern.compile("(" + repositoryObjectSyntax + ")(\\t\\n)*(\\s)*(\".+\")", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content.trim());
        boolean matchFound = matcher.find();
        if (matchFound) {
            return matcher.group(1) + matcher.group(4);
        } else {
            return "";
        }
    }

    /**
     * Use to log errors from antlr4 parsers
     */
    public static void logParserErrors(ArrayList<BaseParserErrorsModel> errorList, String errorType, String location,
        String artifactType)
        throws XSKArtifactParserException, ProblemsException {
        if (errorList.size() > 0) {
            for (BaseParserErrorsModel errorModel : errorList) {
              ProblemsFacade.save(location, errorType, Integer.toString(errorModel.getLine()), Integer.toString(errorModel.getCharPositionInLine()),
                 errorModel.getOffendingSymbol(), errorModel.getMsg(), artifactType, "Parsers", "Publish Request", "XSK");

              //Left for development purposes until ProblemsFacade is properly tested
//              logger.error(String.format(
//                  "Wrong format of %s: [%s] during parsing.: %s",
//                  artifactType, location, errorModel.getErrorMessage()));
            }
            throw new XSKArtifactParserException(String.format(
                    "Wrong format of HDB %s: [%s] during parsing. Ensure you are using the correct format for the correct compatibility version.",
                    artifactType, location));
        }
    }


    public static String[] extractArtifactNameWhenSchemaIsProvided(String artifactName) {
        Pattern pattern = Pattern.compile("\"?(.*?)\"?\\.\"(.*?)\"", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(artifactName.trim());
        boolean matchFound = matcher.find();
        if (matchFound) {
            return new String[]{matcher.group(1), matcher.group(2)};
        } else {
            return new String[]{null,artifactName};
        }
    }
}

