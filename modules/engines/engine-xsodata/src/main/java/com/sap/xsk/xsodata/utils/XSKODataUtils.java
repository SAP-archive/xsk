/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsodata.utils;

import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAAssociation;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAEntity;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAEventType;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAModification;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATAMultiplicityType;
import com.sap.xsk.parser.xsodata.model.XSKHDBXSODATANavigation;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import com.sap.xsk.xsodata.ds.service.XSKOData2TransformerException;
import com.sap.xsk.xsodata.ds.service.XSKODataCoreService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.apache.olingo.odata2.api.edm.EdmMultiplicity;
import org.eclipse.dirigible.commons.config.StaticObjects;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableColumnModel;
import org.eclipse.dirigible.database.persistence.model.PersistenceTableModel;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.engine.odata2.definition.ODataAssociationDefinition;
import org.eclipse.dirigible.engine.odata2.definition.ODataAssociationEndDefinition;
import org.eclipse.dirigible.engine.odata2.definition.ODataDefinition;
import org.eclipse.dirigible.engine.odata2.definition.ODataEntityDefinition;
import org.eclipse.dirigible.engine.odata2.definition.ODataHandler;
import org.eclipse.dirigible.engine.odata2.definition.ODataHandlerTypes;
import org.eclipse.dirigible.engine.odata2.definition.ODataNavigation;
import org.eclipse.dirigible.engine.odata2.definition.ODataProperty;
import org.eclipse.dirigible.engine.odata2.transformers.DBMetadataUtil;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

import static com.sap.xsk.utils.XSKCommonsDBUtils.getSynonymTargetObjectMetadata;

public class XSKODataUtils {

  private static final Logger logger = LoggerFactory.getLogger(XSKODataUtils.class);
  private static final DataSource dataSource = (DataSource) StaticObjects.get(StaticObjects.DATASOURCE);

  private XSKODataUtils() {
  }

  public static ODataDefinition convertXSKODataModelToODataDefinition(XSKODataModel xskoDataModel, DBMetadataUtil dbMetadataUtil) {
    ODataDefinition oDataDefinitionModel = new ODataDefinition();

    oDataDefinitionModel.setLocation(xskoDataModel.getLocation());

    String namespace = xskoDataModel.getService().getNamespace() != null ? xskoDataModel.getService().getNamespace() : "Default";
    oDataDefinitionModel.setNamespace(namespace);

    for (XSKHDBXSODATAEntity entity : xskoDataModel.getService().getEntities()) {
      String tableName = entity.getRepositoryObject().getCatalogObjectName();

      ODataEntityDefinition oDataEntityDefinition = new ODataEntityDefinition();
      oDataEntityDefinition.setName(entity.getAlias());
      oDataEntityDefinition.setAlias(entity.getAlias());
      oDataEntityDefinition.setTable(tableName);

      entity.getNavigates().forEach(processNavigation(xskoDataModel, oDataDefinitionModel, oDataEntityDefinition));

      //set properties
      try {
        PersistenceTableModel tableMetadata = dbMetadataUtil
            .getTableMetadata(tableName, dbMetadataUtil.getOdataArtifactTypeSchema(tableName));
        List<PersistenceTableColumnModel> allEntityDbColumns = tableMetadata.getColumns();

        if (ISqlKeywords.METADATA_SYNONYM.equals(tableMetadata.getTableType())) {
          PersistenceTableModel targetObjectMetadata = getSynonymTargetObjectMetadata(dataSource,tableMetadata.getTableName(),
              tableMetadata.getSchemaName());

          if (targetObjectMetadata.getTableName() == null) {
            throw new XSKOData2TransformerException("Failed to build ODataEntityDefinition for entity: " + entity.getAlias() + ". Reason: cannot find details for synonym - " + tableMetadata.getTableName());
          }

          tableMetadata = dbMetadataUtil.getTableMetadata(targetObjectMetadata.getTableName(), targetObjectMetadata.getSchemaName());
        }

        if (ISqlKeywords.METADATA_CALC_VIEW.equals(tableMetadata.getTableType()) && entity.getWithPropertyProjections().isEmpty() && entity
            .getWithoutPropertyProjections().isEmpty()) {
          allEntityDbColumns.forEach(el -> {
            ODataProperty oDataProperty = new ODataProperty();
            oDataProperty.setName(el.getName());
            oDataProperty.setColumn(el.getName());
            oDataProperty.setNullable(el.isNullable());
            oDataProperty.setType(el.getType());
            oDataEntityDefinition.getProperties().add(oDataProperty);
          });
        }

        entity.getWithPropertyProjections().forEach(prop -> {
          ODataProperty oDataProperty = new ODataProperty();
          oDataProperty.setName(prop);
          oDataProperty.setColumn(prop);
          List<PersistenceTableColumnModel> dbProp = allEntityDbColumns.stream().filter(x -> x.getName().equals(prop))
              .collect(Collectors.toList());
          if (dbProp.size() > 0) {
            oDataProperty.setNullable(dbProp.get(0).isNullable());
            oDataProperty.setType(dbProp.get(0).getType());
          }
          oDataEntityDefinition.getProperties().add(oDataProperty);
        });

        if (!entity.getWithoutPropertyProjections().isEmpty()) {
          allEntityDbColumns.forEach(el -> {
            if (entity.getWithoutPropertyProjections().stream().noneMatch(x -> x.equals(el.getName()))) {
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

      List<ODataHandler> handlers = new ArrayList<>();
      entity.getModifications().forEach(processModification(oDataEntityDefinition, handlers));
      handlers.forEach(el -> oDataEntityDefinition.getHandlers().add(el));

      if (!entity.getKeyList().isEmpty()) {
        oDataEntityDefinition.setKeys(entity.getKeyList());
      } else if (entity.getKeyGenerated() != null) {
        oDataEntityDefinition.getKeys().add(entity.getKeyGenerated());
      }

      //process Aggregations
      if (entity.getAggregations().size() > 0) {
        oDataEntityDefinition.getAnnotationsEntityType().put("sap:semantics", "aggregate");
      }

      oDataDefinitionModel.getEntities().add(oDataEntityDefinition);
    }
    return oDataDefinitionModel;
  }

  @NotNull
  private static Consumer<XSKHDBXSODATAModification> processModification(ODataEntityDefinition oDataEntityDefinition, List<ODataHandler> handlers) {
    return modification -> {
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
        oDataEntityDefinition.getAnnotationsEntitySet().put(modification.getMethod().getOdataSAPAnnotation(), "false");
      }
      if (modification.getSpecification().getModificationAction() != null) {
        ODataHandler oDataHandler = new ODataHandler();
        oDataHandler.setHandler(modification.getSpecification().getModificationAction());
        oDataHandler.setType(ODataHandlerTypes.on.name());
        oDataHandler.setMethod(modification.getMethod().getOdataHandlerType());
        handlers.add(oDataHandler);
      }
    };
  }

  @NotNull
  static Consumer<XSKHDBXSODATANavigation> processNavigation(XSKODataModel xskoDataModel,
      ODataDefinition oDataDefinitionModel, ODataEntityDefinition oDataEntityDefinition) {
    return navigate -> {
      ODataNavigation oDataNavigation = new ODataNavigation();
      oDataNavigation.setName(navigate.getAliasNavigation());
      oDataNavigation.setAssociation(navigate.getAssociation());
      oDataEntityDefinition.getNavigations().add(oDataNavigation);

      //set navigations
      ODataAssociationDefinition oDataAssociationDefinition = new ODataAssociationDefinition();
      oDataAssociationDefinition.setName(navigate.getAssociation());
      XSKHDBXSODATAAssociation xsOdataAssoc = XSKODataCoreService
          .getAssociation(xskoDataModel, navigate.getAssociation(), navigate.getAliasNavigation());

      ODataAssociationEndDefinition fromDef = new ODataAssociationEndDefinition();
      fromDef.setEntity(xsOdataAssoc.getPrincipal().getEntitySetName());

      //The Multiplicity of the Principal role must be 1 or 0..1
      validateEdmMultiplicity(xsOdataAssoc.getPrincipal().getMultiplicityType().getText(), navigate.getAssociation());
      fromDef.setMultiplicity(xsOdataAssoc.getPrincipal().getMultiplicityType().getText());
      fromDef.setProperties((ArrayList<String>) xsOdataAssoc.getPrincipal().getBindingRole().getKeys());
      ODataAssociationEndDefinition toDef = new ODataAssociationEndDefinition();
      toDef.setEntity(xsOdataAssoc.getDependent().getEntitySetName());

      //The Multiplicity of the Principal role must be 1, 0..1, 1..*, *
      //convert 1..* to *, because odata do not support it
      if (xsOdataAssoc.getDependent().getMultiplicityType().getText().equals(XSKHDBXSODATAMultiplicityType.ONE_TO_MANY.getText())) {
        toDef.setMultiplicity(EdmMultiplicity.MANY.toString());
      } else {
        validateEdmMultiplicity(xsOdataAssoc.getDependent().getMultiplicityType().getText(), navigate.getAssociation());
        toDef.setMultiplicity(xsOdataAssoc.getDependent().getMultiplicityType().getText());
      }

      toDef.setProperties((ArrayList<String>) xsOdataAssoc.getDependent().getBindingRole().getKeys());
      oDataAssociationDefinition.setFrom(fromDef);
      oDataAssociationDefinition.setTo(toDef);

      oDataDefinitionModel.getAssociations().add(oDataAssociationDefinition);
    };
  }

  /**
   * Validate if provided multiplicity from xsodata can be mapped to olingo ones.
   */
  public static void validateEdmMultiplicity(String actualValue, String assName) {
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
