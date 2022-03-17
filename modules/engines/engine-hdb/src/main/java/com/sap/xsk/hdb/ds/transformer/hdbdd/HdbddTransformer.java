/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.transformer.hdbdd;

import com.sap.xsk.hdb.ds.model.XSKDBContentType;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintPrimaryKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.parser.hdbdd.symbols.Symbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.AssociationSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntityElementSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.BuiltInTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.DataTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.StructuredDataTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.field.FieldSymbol;
import com.sap.xsk.parser.hdbdd.symbols.view.JoinSymbol;
import com.sap.xsk.parser.hdbdd.symbols.view.SelectSymbol;
import com.sap.xsk.parser.hdbdd.symbols.view.ViewSymbol;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.database.sql.ISqlKeywords;

import static org.eclipse.dirigible.database.sql.ISqlKeywords.SPACE;

public class HdbddTransformer {

  private static final String UNMANAGED_ASSOCIATION_MARKER = "@";
  private static final String CATALOG_ANNOTATION = "Catalog";
  private static final String CATALOG_OBJ_TABLE_TYPE = "tableType";
  private static final String QUOTE = "\"";
  private static final String DOT = ".";

  public XSKDataStructureHDBTableModel transformEntitySymbolToTableModel(EntitySymbol entitySymbol, String location) {
    XSKDataStructureHDBTableModel tableModel = new XSKDataStructureHDBTableModel();
    tableModel.setDbContentType(XSKDBContentType.XS_CLASSIC);
    tableModel.setName(entitySymbol.getFullName());
    tableModel.setSchema(entitySymbol.getSchema());

    List<EntityElementSymbol> entityPks = entitySymbol.getElements().stream().filter(EntityElementSymbol::isKey)
        .collect(Collectors.toList());
    XSKDataStructureHDBTableConstraintPrimaryKeyModel primaryKey = new XSKDataStructureHDBTableConstraintPrimaryKeyModel();
    primaryKey.setColumns(entityPks.stream().map(EntityElementSymbol::getName).toArray(String[]::new));
    primaryKey.setName("PK_" + tableModel.getName());
    tableModel.getConstraints().setPrimaryKey(primaryKey);

    List<XSKDataStructureHDBTableColumnModel> tableColumns = new ArrayList<>();
    entitySymbol.getElements().forEach(currentElement -> {
      if (currentElement.getType() instanceof StructuredDataTypeSymbol) {
        List<EntityElementSymbol> subElements = getStructuredTypeSubElements(currentElement);
        subElements.forEach(subE -> {
          tableColumns.add(transformFieldSymbolToColumnModel(subE, true));
        });
      } else {
        tableColumns.add(transformFieldSymbolToColumnModel(currentElement, true));
      }
    });

    entitySymbol.getAssociations().forEach(associationSymbol -> {
      List<XSKDataStructureHDBTableColumnModel> associationColumns = transformAssociationToColumnModels(associationSymbol);
      XSKDataStructureHDBTableConstraintForeignKeyModel foreignKeyModel = new XSKDataStructureHDBTableConstraintForeignKeyModel();
      String[] referencedColumns = associationColumns.stream().map(XSKDataStructureHDBTableColumnModel::getName).toArray(String[]::new);
      String foreignKeyName = tableModel.getName() + "." + associationSymbol.getName();
      if (associationSymbol.isManaged()) {
        associationColumns.forEach(ac -> {
          if (ac.getAlias() == null) {
            ac.setName(associationSymbol.getName() + "." + ac.getName());
          } else {
            ac.setName(ac.getAlias());
          }
        });
      } else {
        String associationSymbolName = associationSymbol.getName();
        associationColumns.forEach(ac -> ac.setName(associationSymbolName.substring(associationSymbolName.indexOf(
            UNMANAGED_ASSOCIATION_MARKER) + 1)));
        foreignKeyName = foreignKeyName.replace(UNMANAGED_ASSOCIATION_MARKER, "");
      }

      String[] foreignKeyColumns = associationColumns.stream().map(XSKDataStructureHDBTableColumnModel::getName).toArray(String[]::new);

      foreignKeyModel.setName(foreignKeyName);
      foreignKeyModel.setReferencedTable(associationSymbol.getTarget().getFullName());
      foreignKeyModel.setReferencedTableSchema(associationSymbol.getTarget().getSchema());
      foreignKeyModel.setReferencedColumns(referencedColumns);
      foreignKeyModel.setColumns(foreignKeyColumns);

      if (associationSymbol.isManaged()) {
        tableColumns.addAll(associationColumns);
      }

      if (!associationSymbol.getForeignKeys().isEmpty()) {
        tableModel.getConstraints().getForeignKeys().add(foreignKeyModel);
      }
    });

    tableModel.setColumns(tableColumns);
    tableModel.setLocation(location);
    if (entitySymbol.getAnnotation(CATALOG_ANNOTATION) != null) {
      tableModel.setTableType(entitySymbol.getAnnotation(CATALOG_ANNOTATION).getKeyValuePairs().get(CATALOG_OBJ_TABLE_TYPE).getValue());
    }
    return tableModel;
  }

  public XSKDataStructureHDBViewModel transformViewSymbolToHdbViewModel(ViewSymbol viewSymbol, String location) {
    XSKDataStructureHDBViewModel viewModel = new XSKDataStructureHDBViewModel();

    StringBuilder viewSelectSql = new StringBuilder();
    List<String> forReplacement = new ArrayList<>();

    viewSelectSql.append(ISqlKeywords.KEYWORD_VIEW).append(SPACE).append(QUOTE).append(viewSymbol.getSchema()).append(QUOTE)
        .append(DOT).append(QUOTE).append(viewSymbol.getFullName()).append(QUOTE).append(SPACE).append(ISqlKeywords.KEYWORD_AS).append(SPACE);

    String selectStatements = traverseSelectStatements(viewSymbol, forReplacement);

    viewSelectSql.append(selectStatements);

    viewModel.setDbContentType(XSKDBContentType.OTHERS);
    viewModel.setName(viewSymbol.getFullName());
    viewModel.setSchema(viewSymbol.getSchema());
    viewModel.setRawContent(viewSelectSql.toString());
    viewModel.setLocation(location);
    return viewModel;
  }

  public String traverseSelectStatements(ViewSymbol viewSymbol, List<String> forReplacement) {
    StringBuilder selectSql = new StringBuilder();
    String returnedSql = "";

    for(Symbol ss: viewSymbol.getSelectStatements()) {
      // Get the table on which the select depends
      String dependsOnTable = ((((SelectSymbol) ss).getDependsOnTable() == null) ? "" : ((SelectSymbol) ss).getDependsOnTable());

      // Get the depnding table's alias. Returns null in case no alias
      String dependingTableAlias = ((SelectSymbol) ss).getDependingTableAlias();

      // Get the select columns which is the {...} part in the hdbdd view definition
      String selectColumns = ((((SelectSymbol) ss).getColumnsSql() == null) ? "" : ((SelectSymbol) ss).getColumnsSql());

      // Define union and distict if they are true.
      boolean unionBol = ((SelectSymbol) ss).getUnion();
      boolean distinctBol = ((SelectSymbol) ss).getDistinct();

      // Check if the depending table has :: to know whether short or full name is used in the hdbdd view definiton. In case it is not we should build the full name
      if (!dependsOnTable.contains("::")) {
        // Check if the depending table name is DUMMY. This is a reserved table name for hana dummy tables. We make sure to make it in uppercase
        if (dependsOnTable.equalsIgnoreCase("dummy")) {
          dependsOnTable = dependsOnTable.toUpperCase();
        } else {
          String dependsOnTableFullName = fullTableNameBuilderFromViewSymbol(dependsOnTable, viewSymbol);
          // Replace the short name in the select columns with the full name
          selectColumns = selectColumns.replace(dependsOnTable, dependsOnTableFullName);
          // Set the depending table to be with the full name
          dependsOnTable = dependsOnTableFullName.toString();
        }
      }

      if(unionBol) {
        selectSql.append(ISqlKeywords.KEYWORD_UNION).append(SPACE);
      }

      selectSql.append(ISqlKeywords.KEYWORD_SELECT).append(SPACE);

      if(distinctBol) {
        selectSql.append(ISqlKeywords.KEYWORD_DISTINCT).append(SPACE);
      }

      String joinStatements = traverseJoinStatements((SelectSymbol) ss, viewSymbol, dependsOnTable, forReplacement);

      selectSql.append(selectColumns).append(SPACE).append(ISqlKeywords.KEYWORD_FROM).append(SPACE)
          .append(QUOTE).append(dependsOnTable).append(QUOTE).append(SPACE);

      if(dependingTableAlias != null) {
        forReplacement.add(dependingTableAlias);
        selectSql.append(ISqlKeywords.KEYWORD_AS).append(SPACE).append(QUOTE).append(dependingTableAlias).append(QUOTE).append(SPACE);
      }

      selectSql.append(joinStatements).append(SPACE);

      if(!(((SelectSymbol) ss).getWhereSql() == null)) {
        String where = ((SelectSymbol) ss).getWhereSql();
        selectSql.append(ISqlKeywords.KEYWORD_WHERE).append(SPACE).append(where).append(SPACE);
      }

      returnedSql = selectSql.toString();

      for(String alias : forReplacement) {
        returnedSql = putQuotesOnAliases(returnedSql, alias);
      }
    }

    return returnedSql;
  }

  public String traverseJoinStatements(SelectSymbol ss, ViewSymbol viewSymbol, String dependsOnTable, List<String> forReplacement) {
    StringBuilder joinStatements = new StringBuilder();

    // Loop through each join statement
    for (Symbol js: ss.getJoinStatements()) {
      // Get the join type
      String joinType = (((JoinSymbol) js).getJoinType() == null) ? ISqlKeywords.KEYWORD_JOIN : ((JoinSymbol) js).getJoinType();

      // Get the join artifact name
      String joinArtifactName = (((JoinSymbol) js).getJoinArtifactName() == null) ? "" : ((JoinSymbol) js).getJoinArtifactName();

      // Get the join artifact alias
      String joinTableAlias = ((JoinSymbol) js).getJoinTableAlias();

      // Get the rest part of the join and use substring to remove the ON keyword
      String joinFieldsSql = (((JoinSymbol) js).getJoinFields() == null) ? "" : ((JoinSymbol) js).getJoinFields();

      // Check if the join artifact name contains :: to determine if full artifact name is used and build the full name if not
      if (!joinArtifactName.contains("::")) {
        joinArtifactName = fullTableNameBuilderFromViewSymbol(joinArtifactName, viewSymbol);
      }

      // Replace the select from depending table if anywhere in the join with it's full name
      String dependsOnTableShortName = dependsOnTable.replace(viewSymbol.getPackageId() + "::" + viewSymbol.getContext() + ".", "");
      joinFieldsSql = joinFieldsSql.replaceAll("\"" + dependsOnTableShortName + "\"[.]|" + dependsOnTableShortName + "[.]", "\"" + dependsOnTable + "\".");

      joinStatements.append(joinType).append(SPACE).append(QUOTE).append(joinArtifactName).append(QUOTE).append(SPACE);

      if(joinTableAlias != null) {
        joinStatements.append(ISqlKeywords.KEYWORD_AS).append(SPACE).append(QUOTE).append(joinTableAlias).append(QUOTE).append(SPACE);
        forReplacement.add(joinTableAlias);
      }

      joinStatements.append(joinFieldsSql).append(SPACE);
    };

    return joinStatements.toString();
  }

  public String fullTableNameBuilderFromViewSymbol(String tableName, ViewSymbol viewSymbol) {
    StringBuilder fullTableName = new StringBuilder();
    fullTableName.append(viewSymbol.getPackageId()).append("::").append(viewSymbol.getContext()).append(DOT).append(tableName);
    return fullTableName.toString();
  }

  private String putQuotesOnAliases(String toBeReplaced, String alias) {
    return toBeReplaced.replaceAll("" + alias + "[.]|\"" + alias + "\"[.]", "\"" + alias + "\".");
  }

  public XSKDataStructureHDBTableTypeModel transformStructuredDataTypeToHdbTableType(StructuredDataTypeSymbol structuredDataTypeSymbol) {
    XSKDataStructureHDBTableTypeModel hdbTableTypeModel = new XSKDataStructureHDBTableTypeModel();
    List<XSKDataStructureHDBTableColumnModel> tableColumns = new ArrayList<>();
    structuredDataTypeSymbol.getFields().forEach(field -> {
      if (field.getType() instanceof StructuredDataTypeSymbol) {
        List<EntityElementSymbol> subElements = getStructuredTypeSubElements(field);
        subElements.forEach(subE -> {
          tableColumns.add(transformFieldSymbolToColumnModel(subE, true));
        });
      } else {
        tableColumns.add(transformFieldSymbolToColumnModel(field, true));
      }
    });

    hdbTableTypeModel.setColumns(tableColumns);
    hdbTableTypeModel.setDbContentType(XSKDBContentType.XS_CLASSIC);
    hdbTableTypeModel.setName(structuredDataTypeSymbol.getFullName());
    hdbTableTypeModel.setSchema(structuredDataTypeSymbol.getSchema());
    hdbTableTypeModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
    hdbTableTypeModel.setCreatedBy(UserFacade.getName());

    return hdbTableTypeModel;
  }

  /**
   * @param fieldSymbol: fieldSymbol
   * @param bAssignPK:   false if the entityElement is coming from  association, otherwise it should be true
   */
  private XSKDataStructureHDBTableColumnModel transformFieldSymbolToColumnModel(FieldSymbol fieldSymbol, boolean bAssignPK) {
    XSKDataStructureHDBTableColumnModel columnModel = new XSKDataStructureHDBTableColumnModel();

    columnModel.setAlias(fieldSymbol.getAlias());
    columnModel.setName(fieldSymbol.getName());

    columnModel.setNullable(true);

    if (fieldSymbol instanceof EntityElementSymbol) {
      EntityElementSymbol elementSymbol = (EntityElementSymbol) fieldSymbol;
      if (bAssignPK) {
        columnModel.setPrimaryKey(elementSymbol.isKey());
      }

      columnModel.setNullable(!elementSymbol.isNotNull());
      columnModel.setDefaultValue(elementSymbol.getValue());
    }

    if (fieldSymbol.getType() instanceof BuiltInTypeSymbol) {
      BuiltInTypeSymbol builtInTypeSymbol = (BuiltInTypeSymbol) fieldSymbol.getType();
      if (builtInTypeSymbol.isHanaType()) {
        setHanaType(columnModel, builtInTypeSymbol);
      } else {
        setSqlType(columnModel, builtInTypeSymbol);
      }

    } else if (fieldSymbol.getType() instanceof DataTypeSymbol) {
      DataTypeSymbol dataType = (DataTypeSymbol) fieldSymbol.getType();
      if (!(dataType.getType() instanceof StructuredDataTypeSymbol)){
        BuiltInTypeSymbol builtInType = (BuiltInTypeSymbol) dataType.getType();
        setSqlType(columnModel, builtInType);
      } else {
        StructuredDataTypeSymbol structuredDataTypeSymbol = (StructuredDataTypeSymbol) dataType.getType();
        transformStructuredDataTypeToHdbTableType(structuredDataTypeSymbol);
      }
    }

    return columnModel;
  }

  private List<XSKDataStructureHDBTableColumnModel> transformAssociationToColumnModels(AssociationSymbol associationSymbol) {
    List<XSKDataStructureHDBTableColumnModel> tableColumns = new ArrayList<>();
    associationSymbol.getForeignKeys().forEach(fk -> {
      if (fk.getType() instanceof StructuredDataTypeSymbol) {
        List<EntityElementSymbol> subElements = getStructuredTypeSubElements(fk);
        subElements.forEach(subE ->
            tableColumns.add(transformFieldSymbolToColumnModel(subE, false)));
      } else {
        tableColumns.add(transformFieldSymbolToColumnModel(fk, false));
      }
    });

    return tableColumns;
  }

  private void setSqlType(XSKDataStructureHDBTableColumnModel columnModel, BuiltInTypeSymbol builtInTypeSymbol) {
    String typeName = builtInTypeSymbol.getName();
    CdsTypeEnum cdsTypeEnum = CdsTypeEnum.valueOf(typeName);

    if (cdsTypeEnum.equals(CdsTypeEnum.String)) {
      columnModel.setLength(builtInTypeSymbol.getArgsValues().get(0).toString());
    } else if (cdsTypeEnum.equals(CdsTypeEnum.Decimal)) {
      columnModel.setPrecision(builtInTypeSymbol.getArgsValues().get(0).toString());
      columnModel.setScale(builtInTypeSymbol.getArgsValues().get(1).toString());
    }

    columnModel.setType(cdsTypeEnum.getSqlType());
  }

  private void setHanaType(XSKDataStructureHDBTableColumnModel columnModel, BuiltInTypeSymbol builtInTypeSymbol) {
    String typeName = builtInTypeSymbol.getName();
    CdsHanaTypeEnum cdsHanaTypeEnum = CdsHanaTypeEnum.valueOf(typeName);

    if (cdsHanaTypeEnum.equals(CdsHanaTypeEnum.NVARCHAR)) {
      columnModel.setLength(builtInTypeSymbol.getArgsValues().get(0).toString());
    }

    columnModel.setType(typeName);
  }

  private List<EntityElementSymbol> getStructuredTypeSubElements(FieldSymbol entityElementSymbol) {
    StructuredDataTypeSymbol structuredDataType = (StructuredDataTypeSymbol) entityElementSymbol.getType();
    String elementName = entityElementSymbol.getName();
    List<EntityElementSymbol> subElements = new ArrayList<>();
    structuredDataType.getFields().forEach(field -> {
      EntityElementSymbol subElement = new EntityElementSymbol(elementName + "." + field.getName(), entityElementSymbol.getScope());
      if (field.getType() instanceof BuiltInTypeSymbol) {
        subElement.setType(field.getType());
        subElements.add(subElement);
      } else if (field.getType() instanceof StructuredDataTypeSymbol) {
        subElement.setType(field.getType());
        subElements.addAll(getStructuredTypeSubElements(subElement));
      }
    });

    return subElements;
  }
}