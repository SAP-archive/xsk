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

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKDataStructureHDBSynonymModel;
import com.sap.xsk.hdb.ds.model.hdbsynonym.XSKHDBSYNONYMDefinitionModel;
import com.sap.xsk.hdb.ds.service.manager.IXSKDataStructureManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.dialects.mysql.MySQLSqlDialect;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class XSKHDBUtils {
    private XSKHDBUtils() {
    }

    public static String getTableName(XSKDataStructureEntityModel model) {
        return getTableName(model, model.getName());
    }

    public static String getTableName(XSKDataStructureEntityModel model, String tableName) {
        return new StringBuilder()
                .append(model.getNamespace()).append("::").append(model.getContext()).append(".").append(tableName)
                .toString();
    }

    /**
     * Escape artifact name if DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE is activated
     *
     * @param artifactName name of the artifact
     * @param schemaName   name of the schema that will be assembled to the artifact name
     * @return escaped in quotes artifact name
     */
    public static String escapeArtifactName(Connection connection, String artifactName, String schemaName) {
        boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "true"));
        String escapeSymbol = getEscapeSymbol(connection);
        if (!artifactName.startsWith(escapeSymbol)) {
            if (caseSensitive) {
            artifactName = escapeSymbol + artifactName + escapeSymbol;
        }
        }

        if (schemaName != null && !schemaName.trim().isEmpty()) {
            if (!schemaName.startsWith(escapeSymbol)) {
                if (caseSensitive) {
                    schemaName = escapeSymbol + schemaName + escapeSymbol + ".";
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
     * See also {@link #escapeArtifactName(Connection, String, String)}.
     */
    public static String escapeArtifactName(Connection connection, String artifactName) {
        return escapeArtifactName(connection, artifactName, null);
    }

    public static String getEscapeSymbol(Connection connection) {
        return (SqlFactory.deriveDialect(connection).getClass().equals(MySQLSqlDialect.class))
                ? "`"
                : "\"";
    }

    public static void populateXSKDataStructureModel(String location, String content, XSKDataStructureModel model, String artifactType, XSKDBContentType DbContentType) {
        model.setName(XSKCommonsUtils.getRepositoryBaseObjectName(location));
        model.setLocation(location);
        model.setType(artifactType);
        model.setHash(DigestUtils.md5Hex(content));
        model.setCreatedBy(UserFacade.getName());
        model.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        model.setDbContentType(DbContentType);
    }

    public static void createPublicSynonymForArtifact(IXSKDataStructureManager<XSKDataStructureModel> xskSynonymManagerService, String artifactName, String artifactSchema, Connection connection) throws SQLException {
        xskSynonymManagerService.createDataStructure(connection, assemblePublicSynonym(artifactName, artifactSchema));
    }

    public static void dropPublicSynonymForArtifact(IXSKDataStructureManager<XSKDataStructureModel> xskSynonymManagerService, String artifactName, String artifactSchema, Connection connection) throws SQLException {
        xskSynonymManagerService.dropDataStructure(connection, assemblePublicSynonym(artifactName, artifactSchema));
    }

    public static XSKDataStructureHDBSynonymModel assemblePublicSynonym(String artifactName, String artifactSchema) {
        XSKDataStructureHDBSynonymModel model = new XSKDataStructureHDBSynonymModel();

        XSKHDBSYNONYMDefinitionModel defModel = new XSKHDBSYNONYMDefinitionModel();
        defModel.setSynonymSchema(XSKConstants.XSK_SYNONYM_PUBLIC_SCHEMA);
        XSKHDBSYNONYMDefinitionModel.Target target = new XSKHDBSYNONYMDefinitionModel.Target();
        target.setObject(artifactName);
        target.setSchema(artifactSchema);
        defModel.setTarget(target);

        model.getSynonymDefinitions().put(artifactName, defModel);
        model.setType(IXSKDataStructureModel.TYPE_HDB_SYNONYM);
        model.setCreatedBy(UserFacade.getName());
        model.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        return model;
    }
}
