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

import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.xsodata.ds.model.XSKODataEntity;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XSKUtils {

    private Matcher matcher;

    private XSKUtils() {

    }

    public static byte[] objectToByteArray(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            out.flush();

            return bos.toByteArray();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T byteArrayToObject(byte[] byteArray) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
             ObjectInput in = new ObjectInputStream(bis)) {
            return (T) in.readObject();
        }
    }

    public static String convertToFullPath(String filePath) {
        if (!filePath.startsWith("/registry/public")) {
            return "/registry/public" + filePath;
        }
        return filePath;
    }

    public static String getTableName(XSKDataStructureEntityModel model) {
        return getTableName(model, model.getName());
    }

    public static String getTableName(XSKDataStructureEntityModel model, String tableName) {
        return new StringBuilder()
                .append(model.getNamespace()).append("::").append(model.getContext()).append(".").append(tableName)
                .toString();
    }

    public static String getTableName(XSKODataEntity model) {
        return new StringBuilder().append(model.getNamespace()).append("::").append(model.getName()).toString();
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
        return new StringBuilder().append(getRepositoryNamespace(location)).append("::").append(objBaseName).toString();
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
        namespacePart = namespacePart.replace(XSKConstants.UNIX_SEPARATOR, '.');
        namespacePart = namespacePart.replace(XSKConstants.WINDOWS_SEPARATOR, '.');
        if (namespacePart.startsWith(".")) {
            namespacePart = namespacePart.substring(1);
        }
        return namespacePart;
    }

    /**
     * Extraxt corret RepositoryBaseObject definition from content, by removing tabs or spaces between the
     * sybtaxx word and the Object name.
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
     * Escape artifact name if DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE is activated
     *
     * @param artifactName name of the artifact
     * @param schemaName   name of teh schema that will be assembled to the artifact name
     * @return escaped in quotes artifact name
     */
    public static String escapeArtifactName(String artifactName, String schemaName) {
        boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));

        if (!artifactName.startsWith("\"")) {
            if (caseSensitive) {
                artifactName = "\"" + artifactName + "\"";
            }
        }

        if (schemaName != null && !schemaName.trim().isEmpty()) {
            if (!schemaName.startsWith("\"")) {
                if (caseSensitive) {
                    schemaName = "\"" + schemaName + "\"" + ".";
                } else {
                    schemaName = schemaName + ".";
                }
            }
        } else {
            schemaName = "";
        }

        return schemaName + artifactName;
    }

    /**
     * See also {@link #escapeArtifactName(String, String)}.
     */
    public static String escapeArtifactName(String artifactName) {
        return escapeArtifactName(artifactName, null);
    }


}
