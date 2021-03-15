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

import java.io.*;

public class XSKUtils {

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

}
