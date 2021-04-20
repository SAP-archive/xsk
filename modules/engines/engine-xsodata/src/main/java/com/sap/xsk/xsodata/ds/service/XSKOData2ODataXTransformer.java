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
            List<PersistenceTableColumnModel> idColumns = tableMetadata.getColumns().stream().filter(PersistenceTableColumnModel::isPrimaryKey)
                    .collect(Collectors.toList());
            if (idColumns.isEmpty()) {
                logger.error("Table {} not available for entity {}, so it will be skipped.", tableName, entitySetName);
                continue;
            }

            buff.append("\t<EntityType Name=\"").append(entitySetName).append("Type\">\n");
            buff.append("\t\t<Key>\n");
            idColumns.forEach(column -> buff.append("\t\t\t<PropertyRef Name=\"").append(column.getName()).append("\" />\n"));
            buff.append("\t\t</Key>\n");
            tableMetadata.getColumns().forEach(column -> buff.append("\t\t<Property Name=\"").append(column.getName()).append("\"").append(" Type=\"").append(column.getType()).append("\"").append(" Nullable=\"").append(column.isNullable()).append("\"/>\n"));

            entity.getNavigates().forEach(relation -> {
                XSKHDBXSODATAAssociation association = XSKODataCoreService.getAssociation(model, relation.getAssociation(), relation.getAliasNavigation());
                String toRole = association.getPrincipal().getEntitySetName();
                String fromRole = association.getDependent().getEntitySetName();
                buff.append("\t\t<NavigationProperty Name=\"").append(relation.getAliasNavigation()).append("\"").append(" Relationship=\"").append(getServiceNamespace(model)).append(relation.getAssociation()).append("Type\"").append(" FromRole=\"").append(fromRole).append("Dependent").append("\"").append(" ToRole=\"").append(toRole).append("Principal").append("\"/>\n");
            });

            // keep associations for later use
            entity.getNavigates().forEach(relation -> {
                XSKHDBXSODATAAssociation association = XSKODataCoreService.getAssociation(model, relation.getAssociation(), relation.getAliasNavigation());
                String fromRole = association.getPrincipal().getEntitySetName();
                String toRole = association.getDependent().getEntitySetName();
                String fromMultiplicity = association.getPrincipal().getMultiplicityType().getText();
                String toMultiplicity = association.getDependent().getMultiplicityType().getText();
                associations.append("\t<Association Name=\"").append(relation.getAssociation()).append("Type\">\n").append("\t\t<End Type=\"").append(getServiceNamespace(model)).append(fromRole).append("Type\"").append(" Role=\"").append(fromRole).append("Principal").append("\" Multiplicity=\"").append(fromMultiplicity).append("\"/>\n").append(" \t\t<End Type=\"").append(getServiceNamespace(model)).append(toRole).append("Type\"").append(" Role=\"").append(toRole).append("Dependent").append("\" Multiplicity=\"").append(toMultiplicity).append("\"/>\n").append("\t</Association>\n");
            });

            // keep entity sets for later use
            entitySets.append("\t\t<EntitySet Name=\"").append(entitySetName).append("\" EntityType=\"").append(getServiceNamespace(model)).append(entitySetName).append("Type\" />\n");

            // keep associations sets for later use
            entity.getNavigates().forEach(relation -> {
                XSKHDBXSODATAAssociation association = XSKODataCoreService.getAssociation(model, relation.getAssociation(), relation.getAliasNavigation());
                String fromRole = association.getPrincipal().getEntitySetName();
                String toRole = association.getDependent().getEntitySetName();
                associationsSets.append("        <AssociationSet Name=\"").append(relation.getAssociation()).append("\"").append(" Association=\"").append(getServiceNamespace(model)).append(relation.getAssociation()).append("Type\">\n").append("\t\t\t<End Role=\"").append(fromRole).append("Principal").append("\"").append(" EntitySet=\"").append(fromRole).append("\"/>\n").append("\t\t\t<End Role=\"").append(toRole).append("Dependent").append("\"").append(" EntitySet=\"").append(toRole).append("\"/>\n").append("\t\t</AssociationSet>\n");
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

/**
 * <Schema Namespace="org.apache.olingo.odata2.ODataCars"
 * xmlns="http://schemas.microsoft.com/ado/2008/09/edm">
 * <EntityType Name="Car">
 * <Key>
 * <PropertyRef Name="Id" />
 * </Key>
 * <Property Name="Id" Type="Edm.Int32" Nullable="false" />
 * <Property Name="Model" Type="Edm.String" Nullable="false"
 * DefaultValue="Hugo" MaxLength="100"
 * m:FC_TargetPath="SyndicationTitle" />
 * <Property Name="ManufacturerId" Type="Edm.Int32" />
 * <Property Name="Price" Type="Edm.Decimal" />
 * <Property Name="Currency" Type="Edm.String" MaxLength="3" />
 * <Property Name="ModelYear" Type="Edm.String" MaxLength="4" />
 * <Property Name="Updated" Type="Edm.DateTime"
 * Nullable="false" ConcurrencyMode="Fixed"
 * m:FC_TargetPath="SyndicationUpdated" />
 * <Property Name="ImagePath" Type="Edm.String" />
 * <NavigationProperty Name="Manufacturer"
 * Relationship="org.apache.olingo.odata2.ODataCars.Car_Manufacturer_Manufacturer_Cars"
 * FromRole="Car_Manufacturer" ToRole="Manufacturer_Cars" />
 * </EntityType>
 * <EntityType Name="Manufacturer">
 * <Key>
 * <PropertyRef Name="Id" />
 * </Key>
 * <Property Name="Id" Type="Edm.Int32" Nullable="false" />
 * <Property Name="Name" Type="Edm.String" Nullable="false"
 * MaxLength="100" m:FC_TargetPath="SyndicationTitle" />
 * <!-- <Property Name="Address"
 * Type="org.apache.olingo.odata2.ODataCars.Address" /> -->
 * <Property Name="Updated" Type="Edm.DateTime"
 * Nullable="false" ConcurrencyMode="Fixed"
 * m:FC_TargetPath="SyndicationUpdated" />
 * <NavigationProperty Name="Cars"
 * Relationship="org.apache.olingo.odata2.ODataCars.Car_Manufacturer_Manufacturer_Cars"
 * FromRole="Manufacturer_Cars" ToRole="Car_Manufacturer" />
 * </EntityType>
 * <!-- <ComplexType Name="Address">
 * <Property Name="Street" Type="Edm.String" />
 * <Property Name="City" Type="Edm.String" />
 * <Property Name="ZipCode" Type="Edm.String" />
 * <Property Name="Country" Type="Edm.String" />
 * </ComplexType> -->
 * <Association Name="Car_Manufacturer_Manufacturer_Cars">
 * <End Type="org.apache.olingo.odata2.ODataCars.Car"
 * Multiplicity="*" Role="Car_Manufacturer" />
 * <End Type="org.apache.olingo.odata2.ODataCars.Manufacturer"
 * Multiplicity="1" Role="Manufacturer_Cars" />
 * </Association>
 * <EntityContainer Name="ODataCarsEntityContainer"
 * m:IsDefaultEntityContainer="true">
 * <EntitySet Name="Cars"
 * EntityType="org.apache.olingo.odata2.ODataCars.Car" />
 * <EntitySet Name="Manufacturers"
 * EntityType="org.apache.olingo.odata2.ODataCars.Manufacturer" />
 * <AssociationSet Name="Cars_Manufacturers"
 * Association="org.apache.olingo.odata2.ODataCars.Car_Manufacturer_Manufacturer_Cars">
 * <End EntitySet="Manufacturers" Role="Manufacturer_Cars" />
 * <End EntitySet="Cars" Role="Car_Manufacturer" />
 * </AssociationSet>
 * <!-- <FunctionImport Name="NumberOfCars"
 * ReturnType="Collection(org.apache.olingo.odata2.ODataCars.Car)"
 * m:HttpMethod="GET" /> -->
 * </EntityContainer>
 * </Schema>
 */
