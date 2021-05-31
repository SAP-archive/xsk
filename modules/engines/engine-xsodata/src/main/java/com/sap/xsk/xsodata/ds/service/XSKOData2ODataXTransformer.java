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
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAHandlerMethod;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableColumnModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class XSKOData2ODataXTransformer {

    private static final Logger logger = LoggerFactory.getLogger(XSKOData2ODataXTransformer.class);

    @Inject
    private DBMetadataUtil dbMetadataUtil;

    public String[] transform(XSKODataModel model) throws SQLException {

        if (model.getService() == null) {
            logger.error("Service element is null for xsodata file {}, so it will be skipped. Maybe the format is wrong and cannot be parsed.",
                    model.getName());
        }

        String[] result = new String[2];
        StringBuilder buff = new StringBuilder();
        String namespace = model.getService().getNamespace() != null ? model.getService().getNamespace() : "Default";
        buff.append("<Schema Namespace=\"").append(namespace).append("\"\n\t");
        buff.append("xmlns=\"http://schemas.microsoft.com/ado/2008/09/edm\">\n");

        StringBuilder associations = new StringBuilder();
        StringBuilder entitySets = new StringBuilder();
        StringBuilder associationsSets = new StringBuilder();
        for (XSKHDBXSODATAEntity entity : model.getService().getEntities()) {
            String tableName = entity.getRepositoryObject().getCatalogObjectName();
            String entitySetName = entity.getAlias();

            PersistenceTableModel tableMetadata = dbMetadataUtil.getTableMetadata(tableName);
            //Views don't have primary keys or indexes
            List<PersistenceTableColumnModel> idColumns = tableMetadata.getColumns().stream().filter(PersistenceTableColumnModel::isPrimaryKey)
                    .collect(Collectors.toList());

            // entity.getKeyList() can not exist for a table object.
            if (idColumns.isEmpty() && entity.getKeyList().isEmpty() && tableMetadata.getColumns().isEmpty()) {
                logger.error("Table {} not available for entity {}, so it will be skipped.", tableName, entitySetName);
                continue;
            }

            buff.append("\t<EntityType Name=\"").append(entitySetName).append("Type\"");
            if (entity.getAggregationType() != null) {
                buff.append(" sap:semantics=\"aggregate\"");
            }
            buff.append(">\n");
            buff.append("\t\t<Key>\n");
            if (!entity.getKeyList().isEmpty()) {
                entity.getKeyList().forEach(key -> buff.append("\t\t\t<PropertyRef Name=\"").append(key).append("\" />\n"));
            } else if (entity.getKeyGenerated() != null) {
                buff.append("\t\t\t<PropertyRef Name=\"").append(entity.getKeyGenerated()).append("\" />\n");
            } else {
                idColumns.forEach(column -> buff.append("\t\t\t<PropertyRef Name=\"").append(column.getName()).append("\" />\n"));
            }

            buff.append("\t\t</Key>\n");
            if (entity.getKeyGenerated() != null) {
                buff.append("\t\t<Property Name=\"").append(entity.getKeyGenerated()).append("\"").append(" Type=\"").append("Edm.String").append("\"").append(" Nullable=\"").append("false").append("\" MaxLength=\"2147483647\"").append(" sap:filterable=\"false\"").append("/>\n");
            }
            if (!entity.getWithPropertyProjections().isEmpty()) {
                entity.getWithPropertyProjections().forEach(prop -> {
                    if (tableMetadata.getColumns().stream().anyMatch(x -> x.getName().equals(prop))) {
                        PersistenceTableColumnModel column = tableMetadata.getColumns().stream().filter(x -> x.getName().equals(prop)).findAny().get();
                        buff.append("\t\t<Property Name=\"").append(prop).append("\"").append(" Type=\"").append(column.getType()).append("\"").append(" Nullable=\"").append(column.isNullable()).append("\"/>\n");
                    }
                });
            } else if (!entity.getWithoutPropertyProjections().isEmpty()) {
                entity.getWithoutPropertyProjections().forEach(prop -> tableMetadata.getColumns().forEach(column ->
                {
                    if (!column.getName().equals(prop)) {
                        buff.append("\t\t<Property Name=\"").append(column.getName()).append("\"").append(" Type=\"").append(column.getType()).append("\"").append(" Nullable=\"").append(column.isNullable()).append("\"/>\n");
                    }
                }));
            } else {
                tableMetadata.getColumns().forEach(column -> buff.append("\t\t<Property Name=\"").append(column.getName()).append("\"").append(" Type=\"").append(column.getType()).append("\"").append(" Nullable=\"").append(column.isNullable()).append("\"/>\n"));
            }

            entity.getNavigates().forEach(relation -> {
                XSKHDBXSODATAAssociation association = XSKODataCoreService.getAssociation(model, relation.getAssociation(), relation.getAliasNavigation());
                String toRole = association.getDependent().getEntitySetName();
                String fromRole = association.getPrincipal().getEntitySetName();
                buff.append("\t\t<NavigationProperty Name=\"").append(relation.getAliasNavigation()).append("\"").append(" Relationship=\"").append(getServiceNamespace(model)).append(relation.getAssociation()).append("Type\"").append(" FromRole=\"").append(fromRole).append("Principal").append("\"").append(" ToRole=\"").append(toRole).append("Dependent").append("\"/>\n");
            });

            // keep associations for later use
            entity.getNavigates().forEach(relation -> {
                XSKHDBXSODATAAssociation association = XSKODataCoreService.getAssociation(model, relation.getAssociation(), relation.getAliasNavigation());
                String fromRole = association.getPrincipal().getEntitySetName();
                String toRole = association.getDependent().getEntitySetName();
                String fromMultiplicity = association.getPrincipal().getMultiplicityType().getText();
                String toMultiplicity = association.getDependent().getMultiplicityType().getText();
                associations.append("\t<Association Name=\"").append(relation.getAssociation()).append("Type\">\n").append("\t\t<End Type=\"").append(getServiceNamespace(model)).append(fromRole).append("Type\"").append(" Role=\"").append(fromRole).append("Principal").append("\" Multiplicity=\"").append(fromMultiplicity).append("\"/>\n").append(" \t\t<End Type=\"").append(getServiceNamespace(model)).append(toRole).append("Type\"").append(" Role=\"").append(toRole).append("Dependent").append("\" Multiplicity=\"").append(toMultiplicity).append("\"/>\n");
                if (association.isWithReferentialConstraint()) {
                    associations.append("\t<ReferentialConstraint>\n");
                    associations.append("\t\t<Principal Role=\"").append(fromRole).append("Principal\">\n");
                    association.getPrincipal().getBindingRole().getKeys().forEach(key -> associations.append("\t\t\t<PropertyRef Name=\"").append(key).append("\"/>\n"));
                    associations.append("\t\t</Principal>\n");
                    associations.append("\t\t<Dependent Role=\"").append(toRole).append("Dependent\">\n");
                    association.getDependent().getBindingRole().getKeys().forEach(key -> associations.append("\t\t\t<PropertyRef Name=\"").append(key).append("\"/>\n"));
                    associations.append("\t\t</Dependent>\n");
                    associations.append("\t</ReferentialConstraint>\n");
                }
                associations.append("\t</Association>\n");
            });

            // keep entity sets for later use
            entitySets.append("\t\t<EntitySet Name=\"").append(entitySetName).append("\" EntityType=\"").append(getServiceNamespace(model)).append(entitySetName).append("Type\"");
            entity.getModifications().forEach(event -> {
                if (event.getMethod().equals(XSKHDBXSODATAHandlerMethod.CREATE) && event.getSpecification().isForbidden()) {
                    entitySets.append(" sap:creatable=\"false\"");
                }
                if (event.getMethod().equals(XSKHDBXSODATAHandlerMethod.UPDATE) && event.getSpecification().isForbidden()) {
                    entitySets.append(" sap:updatable=\"false\"");
                }
                if (event.getMethod().equals(XSKHDBXSODATAHandlerMethod.DELETE) && event.getSpecification().isForbidden()) {
                    entitySets.append(" sap:deletable=\"false\"");
                }
            });
            entitySets.append("/>\n");

            // keep associations sets for later use
            entity.getNavigates().forEach(relation -> {
                XSKHDBXSODATAAssociation association = XSKODataCoreService.getAssociation(model, relation.getAssociation(), relation.getAliasNavigation());
                String fromRole = association.getPrincipal().getEntitySetName();
                String toRole = association.getDependent().getEntitySetName();
                associationsSets.append("\t\t<AssociationSet Name=\"").append(relation.getAssociation()).append("\"").append(" Association=\"").append(getServiceNamespace(model)).append(relation.getAssociation()).append("Type\">\n").append("\t\t\t<End Role=\"").append(fromRole).append("Principal").append("\"").append(" EntitySet=\"").append(fromRole).append("\"/>\n").append("\t\t\t<End Role=\"").append(toRole).append("Dependent").append("\"").append(" EntitySet=\"").append(toRole).append("\"/>\n").append("\t\t</AssociationSet>\n");
            });
            buff.append("\t</EntityType>\n");
        }

        buff.append(associations.toString());

        StringBuilder container = new StringBuilder();
        container.append(entitySets.toString());
        container.append(associationsSets.toString());
        buff.append("</Schema>\n");

        result[0] = buff.toString();
        result[1] = container.toString();
        return result;
    }

    private String getServiceNamespace(XSKODataModel model) {
        if (model.getService().getNamespace() != null) {
            return model.getService().getNamespace() + ".";
        }
        return "";
    }
}