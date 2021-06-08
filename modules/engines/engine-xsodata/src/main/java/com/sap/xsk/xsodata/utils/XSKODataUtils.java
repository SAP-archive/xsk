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
package com.sap.xsk.xsodata.utils;

import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAAssociation;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAEntity;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAEventType;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import com.sap.xsk.xsodata.ds.service.XSKOData2TransformerException;
import com.sap.xsk.xsodata.ds.service.XSKODataCoreService;
import org.apache.olingo.odata2.api.edm.EdmMultiplicity;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableColumnModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.engine.odata2.definition.*;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class XSKODataUtils {

    private static final Logger logger = LoggerFactory.getLogger(XSKODataUtils.class);

    private XSKODataUtils() {
    }

    public static ODataDefinition convertXSKODataModelToODataDefinition(XSKODataModel xskoDataModel, DBMetadataUtil dbMetadataUtil) {
        ODataDefinition oDataDefinitionModel = new ODataDefinition();
        String namespace = xskoDataModel.getService().getNamespace() != null ? xskoDataModel.getService().getNamespace() : "Default";
        oDataDefinitionModel.setNamespace(namespace);

        for (XSKHDBXSODATAEntity entity : xskoDataModel.getService().getEntities()) {
            String tableName = entity.getRepositoryObject().getCatalogObjectName();

            ODataEntityDefinition oDataEntityDefinition = new ODataEntityDefinition();
            oDataEntityDefinition.setName(entity.getAlias());
            oDataEntityDefinition.setAlias(entity.getAlias());
            oDataEntityDefinition.setTable(tableName);

            entity.getNavigates().forEach(navigate -> {
                ODataNavigation oDataNavigation = new ODataNavigation();
                oDataNavigation.setName(navigate.getAliasNavigation());
                oDataNavigation.setAssociation(navigate.getAssociation());
                oDataEntityDefinition.setNavigations(Collections.singletonList(oDataNavigation));

                //set navigations
                ODataAssociationDefinition oDataAssociationDefinition = new ODataAssociationDefinition();
                oDataAssociationDefinition.setName(navigate.getAssociation());
                XSKHDBXSODATAAssociation xsOdataAssoc = XSKODataCoreService.getAssociation(xskoDataModel, navigate.getAssociation(), navigate.getAliasNavigation());

                ODataAssiciationEndDefinition fromDef = new ODataAssiciationEndDefinition();
                fromDef.setEntity(xsOdataAssoc.getPrincipal().getEntitySetName());

                validateEdmMultiplicity(xsOdataAssoc.getPrincipal().getMultiplicityType().getText(), navigate.getAssociation());
                fromDef.setMultiplicity(xsOdataAssoc.getPrincipal().getMultiplicityType().getText());
                fromDef.setProperty((ArrayList<String>) xsOdataAssoc.getPrincipal().getBindingRole().getKeys());
                ODataAssiciationEndDefinition toDef = new ODataAssiciationEndDefinition();
                toDef.setEntity(xsOdataAssoc.getDependent().getEntitySetName());

                validateEdmMultiplicity(xsOdataAssoc.getDependent().getMultiplicityType().getText(), navigate.getAssociation());
                toDef.setMultiplicity(xsOdataAssoc.getDependent().getMultiplicityType().getText());

                toDef.setProperty((ArrayList<String>) xsOdataAssoc.getDependent().getBindingRole().getKeys());
                oDataAssociationDefinition.setFrom(fromDef);
                oDataAssociationDefinition.setTo(toDef);

                oDataDefinitionModel.getAssociations().add(oDataAssociationDefinition);

                //set properties
                try {
                    PersistenceTableModel tableMetadata = dbMetadataUtil.getTableMetadata(tableName);
                    List<PersistenceTableColumnModel> allEntityDbColumns = tableMetadata.getColumns();

                    entity.getWithPropertyProjections().forEach(prop -> {
                        ODataProperty oDataProperty = new ODataProperty();
                        oDataProperty.setName(prop);
                        oDataProperty.setColumn(prop);
                        List<PersistenceTableColumnModel> dbProp = allEntityDbColumns.stream().filter(x -> x.getName().equals(prop)).collect(Collectors.toList());
                        if (dbProp.size() > 0) {
                            oDataProperty.setNullable(dbProp.get(0).isNullable());
                            oDataProperty.setType(dbProp.get(0).getType());
                        }
                        oDataEntityDefinition.getProperties().add(oDataProperty);
                    });

                    if (!entity.getWithoutPropertyProjections().isEmpty()) {
                        allEntityDbColumns.forEach(el -> {
                            if (entity.getWithoutPropertyProjections().stream().filter(x -> x.equals(el.getName())).count() == 0) {
                                ODataProperty oDataProperty = new ODataProperty();
                                oDataProperty.setName(el.getName());
                                oDataProperty.setColumn(el.getName());
                                oDataProperty.setNullable(el.isNullable());
                                oDataProperty.setType(el.getType());
                                oDataEntityDefinition.getProperties().add(oDataProperty);
                            }
                        });
                    }
                } catch (SQLException e) {
                    throw new XSKOData2TransformerException(e);
                }
            });
            List<ODataHandler> handlers = new ArrayList<>();
            entity.getModifications().forEach(modification -> {
                modification.getSpecification().getEvents().forEach(event -> {
                    if (validateHandlerType(event.getType())) {
                        ODataHandler oDataHandler = new ODataHandler();
                        oDataHandler.setHandler(event.getAction());
                        oDataHandler.setType(event.getType().getOdataHandlerType());
                        oDataHandler.setMethod(modification.getMethod().getOdataHandlerType());
                        handlers.add(oDataHandler);
                    }
                });
                if (modification.getSpecification().isForbidden()) {
                    ODataHandler oDataHandler = new ODataHandler();
                    oDataHandler.setType(ODataHandlerTypes.forbid.name());
                    oDataHandler.setMethod(modification.getMethod().getOdataHandlerType());
                    handlers.add(oDataHandler);
                }
                if (modification.getSpecification().getModificationAction() != null) {
                    ODataHandler oDataHandler = new ODataHandler();
                    oDataHandler.setHandler(modification.getSpecification().getModificationAction());
                    oDataHandler.setType(ODataHandlerTypes.on.name());
                    oDataHandler.setMethod(modification.getMethod().getOdataHandlerType());
                    handlers.add(oDataHandler);
                }
            });
            handlers.forEach(el -> {
                oDataEntityDefinition.getHandlers().add(el);
            });

            oDataDefinitionModel.getEntities().add(oDataEntityDefinition);
        }
        return oDataDefinitionModel;
    }

    /**
     * Validate if provided multiplicity from xsodata can be mapped to olingo ones.
     */
    private static void validateEdmMultiplicity(String actualValue, String assName) {
        try {
            EdmMultiplicity.fromLiteral(actualValue);
        } catch (IllegalArgumentException ex) {
            throw new XSKOData2TransformerException(String.format("Unsupported multiplicity %s for association %s", actualValue, assName));
        }
    }

    /**
     * Validate if provided handler type is one of the org.eclipse.dirigible.engine.odata2.definition.ODataHandlerTypes
     */
    public static boolean validateHandlerType(XSKHDBXSODATAEventType eventType) {
        try {
            ODataHandlerTypes.fromValue(eventType.getOdataHandlerType());
        } catch (IllegalArgumentException ex) {
            logger.error(String.format("%s type is not supported", eventType.name()));
            return false;
        }
        return true;
    }
}
