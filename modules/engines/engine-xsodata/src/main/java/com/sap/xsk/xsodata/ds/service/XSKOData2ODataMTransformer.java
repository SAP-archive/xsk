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
package com.sap.xsk.xsodata.ds.service;

import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAAssociation;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAEntity;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableColumnModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class XSKOData2ODataMTransformer {

    private static final Logger logger = LoggerFactory.getLogger(XSKOData2ODataMTransformer.class);

    @Inject
    private DBMetadataUtil dbMetadataUtil;

    public String[] transform(XSKODataModel model) throws SQLException {

        if (model.getService() == null) {
            logger.error("Service element is null for xsodata file {}, so it will be skipped. Maybe the format is wrong and cannot be parsed.",
                    model.getName());
        }

        List<String> result = new ArrayList<>();

        for (XSKHDBXSODATAEntity entity : model.getService().getEntities()) {
            String namespace = model.getService().getNamespace() != null ? model.getService().getNamespace() : "Default";
            String tableName = entity.getRepositoryObject().getCatalogObjectName();
            String entitySetName = entity.getAlias();
            StringBuilder buff = new StringBuilder();
            buff.append("{\n");
            buff.append("\t\"edmType\" : \"");
            buff.append(entity.getAlias());
            buff.append("Type\",\n");
            buff.append("\t\"edmTypeFqn\" : \"").append(namespace).append(".").append(entity.getAlias()).append("Type\",\n");
            buff.append("\t\"sqlTable\" : \"").append(tableName).append("\",\n");

            PersistenceTableModel tableMetadata = dbMetadataUtil.getTableMetadata(tableName);
            List<PersistenceTableColumnModel> idColumns = tableMetadata.getColumns().stream().filter(PersistenceTableColumnModel::isPrimaryKey)
                    .collect(Collectors.toList());
            if (idColumns.isEmpty()) {
                logger.error("Table {} not available for entity {}, so it will be skipped.", tableName, entitySetName);
                continue;
            }

            tableMetadata.getColumns().forEach(column -> buff.append("\t\"").append(column.getName()).append("\" : \"").append(column.getName()).append("\",\n"));
            tableMetadata.getRelations().forEach(relation -> {
                String toEntityName = relation.getToTableName();
                toEntityName = toEntityName.substring(toEntityName.lastIndexOf(".") + 1);
                buff.append("\t\"_ref_").append(toEntityName).append("Type\":{ \"joinColumn\" : \"").append(relation.getFkColumnName()).append("\"\n").append("}\n").append(",\n");
            });
            entity.getNavigates().forEach(navigate -> {
                XSKHDBXSODATAAssociation association = XSKODataCoreService.getAssociation(model, navigate.getAssociation(), navigate.getAliasNavigation());
                String toRole = association.getDependent().getEntitySetName();
                StringBuilder fromRoleProperty = new StringBuilder();
                for (int i = 0; i < association.getPrincipal().getBindingRole().getKeys().size(); i++) {
                    if (i == 0) {
                        fromRoleProperty.append("[\n\t\t\t");
                    }
                    fromRoleProperty.append("\"").append(association.getPrincipal().getBindingRole().getKeys().get(i)).append("\"");
                    if (i < association.getPrincipal().getBindingRole().getKeys().size() - 1) {
                        fromRoleProperty.append(",");
                    }
                    if (i == association.getPrincipal().getBindingRole().getKeys().size() - 1) {
                        fromRoleProperty.append("\n\t\t]");
                    }
                }
                XSKHDBXSODATAEntity toSetEntity = XSKODataCoreService.getEntity(model, toRole, navigate.getAliasNavigation());
                String dependentEntity = toSetEntity.getAlias();
                //TODO: issue on dirigible: the joinColumn should be an array https://github.com/eclipse/dirigible/issues/814
                buff.append("\t\"_ref_").append(dependentEntity).append("Type\":{\n\t\t\"joinColumn\" : ").append(fromRoleProperty).append("\n\t}").append(",\n");
            });

            String[] pks = idColumns.stream().map(PersistenceTableColumnModel::getName).collect(Collectors.toList()).toArray(new String[]{});
            buff.append("\t\"_pk_\" : \"").append(String.join(",", pks)).append("\"");
            buff.append("\n}");

            result.add(buff.toString());
        }
        return result.toArray(new String[]{});
    }
}
