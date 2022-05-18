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
import java.util.stream.Collectors;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.database.sql.ISqlKeywords;

import static org.eclipse.dirigible.database.sql.ISqlKeywords.SPACE;

public class HdbddTransformer {

  private static final String UNMANAGED_ASSOCIATION_MARKER = "@";
  private static final String CATALOG_ANNOTATION = "Catalog";
  private static final String CATALOG_OBJ_TABLE_TYPE = "tableType";
  private static final String SEARCH_INDEX_ANNOTATION = "SearchIndex";
  private static final String FUZZY_SEARCH_INDEX_ENABLED = "enabled";
  private static final String DUMMY_TABLE = "DUMMY";
  private static final String QUOTE = "\"";
  private static final String DOT = ".";
  private static final String PACKAGE_DELIMITER = "::";

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

    for (int i = 0; i < entitySymbol.getElements().size(); i++) {
      EntityElementSymbol currentElement = entitySymbol.getElements().get(i);
      if (currentElement.getAnnotation(SEARCH_INDEX_ANNOTATION) != null) {
        tableModel.getColumns().get(i).setFuzzySearchIndex(Boolean.parseBoolean(
            currentElement.getAnnotation(SEARCH_INDEX_ANNOTATION).getKeyValuePairs().get(FUZZY_SEARCH_INDEX_ENABLED).getValue()));
      }
    }
    return tableModel;
  }

  public XSKDataStructureHDBViewModel transformViewSymbolToHdbViewModel(ViewSymbol viewSymbol, String location) {
    XSKDataStructureHDBViewModel viewModel = new XSKDataStructureHDBViewModel();

    StringBuilder viewStatementSql = new StringBuilder();
    List<String> aliasesForReplacement = new ArrayList<>();

    viewStatementSql.append(ISqlKeywords.KEYWORD_VIEW).append(SPACE).append(QUOTE).append(viewSymbol.getSchema()).append(QUOTE)
        .append(DOT).append(QUOTE).append(viewSymbol.getFullName()).append(QUOTE).append(SPACE).append(ISqlKeywords.KEYWORD_AS)
        .append(SPACE);

    String selectStatementSql = traverseSelectStatements(viewSymbol, aliasesForReplacement);
    viewStatementSql.append(selectStatementSql);

    String finalViewSql = viewStatementSql.toString();

    for (String alias : aliasesForReplacement) {
      finalViewSql = replaceWithQuotes(finalViewSql, alias, alias);
    }

    viewModel.setDbContentType(XSKDBContentType.OTHERS);
    viewModel.setName(viewSymbol.getFullName());
    viewModel.setSchema(viewSymbol.getSchema());
    viewModel.setRawContent(finalViewSql);
    viewModel.setLocation(location);
    return viewModel;
  }

  public String traverseSelectStatements(ViewSymbol viewSymbol, List<String> aliasesForReplacement) {
    StringBuilder selectSql = new StringBuilder();

    for (Symbol symbol : viewSymbol.getSelectStatements()) {
      SelectSymbol selectSymbol = (SelectSymbol) symbol;
      String dependsOnTable = selectSymbol.getDependsOnTable();
      String dependingTableAlias = selectSymbol.getDependingTableAlias();
      String selectColumns = selectSymbol.getColumnsSql();
      String where = selectSymbol.getWhereSql();
      boolean unionBol = selectSymbol.getUnion();
      boolean distinctBol = selectSymbol.getDistinct();

      // Check if the dependant table has :: to know whether short or full name is used in the hdbdd view definition. In case it is not we should build the full name
      if (!dependsOnTable.contains(PACKAGE_DELIMITER)) {
        // Check if the dependant table name is DUMMY. This is a reserved table name for hana dummy tables. We make sure to make it in uppercase
        if (dependsOnTable.equalsIgnoreCase(DUMMY_TABLE)) {
          dependsOnTable = dependsOnTable.toUpperCase();
        } else {
          String dependsOnTableFullName = fullTableNameBuilderFromViewSymbol(dependsOnTable, viewSymbol);
          // Replace the short name in the select columns with the full name
          selectColumns = replaceWithQuotes(selectColumns, dependsOnTable, dependsOnTableFullName);
          // Set the dependant table to be with the full name
          dependsOnTable = dependsOnTableFullName;
        }
      }

      if (unionBol) {
        selectSql.append(ISqlKeywords.KEYWORD_UNION).append(SPACE);
      }

      selectSql.append(ISqlKeywords.KEYWORD_SELECT).append(SPACE);

      if (distinctBol) {
        selectSql.append(ISqlKeywords.KEYWORD_DISTINCT).append(SPACE);
      }

      selectSql.append(selectColumns).append(SPACE).append(ISqlKeywords.KEYWORD_FROM).append(SPACE)
          .append(QUOTE).append(dependsOnTable).append(QUOTE).append(SPACE);

      if (dependingTableAlias != null) {
        selectSql.append(ISqlKeywords.KEYWORD_AS).append(SPACE).append(QUOTE).append(dependingTableAlias).append(QUOTE).append(SPACE);
        aliasesForReplacement.add(dependingTableAlias);
      }

      String joinStatements = traverseJoinStatements(selectSymbol, viewSymbol, dependsOnTable, aliasesForReplacement);

      selectSql.append(joinStatements).append(SPACE);

      if (where != null) {
        selectSql.append(ISqlKeywords.KEYWORD_WHERE).append(SPACE).append(where).append(SPACE);
      }
    }

    return selectSql.toString();
  }

  public String traverseJoinStatements(SelectSymbol selectSymbol, ViewSymbol viewSymbol, String dependsOnTable, List<String> aliasesForReplacement) {
    StringBuilder joinStatements = new StringBuilder();

    for (Symbol symbol : selectSymbol.getJoinStatements()) {
      JoinSymbol joinSymbol = (JoinSymbol) symbol;
      String joinType = joinSymbol.getJoinType();
      String joinArtifactName = joinSymbol.getJoinArtifactName();
      String joinTableAlias = joinSymbol.getJoinTableAlias();
      String joinFieldsSql = joinSymbol.getJoinFields();

      // Check if the join artifact name contains :: to determine if full artifact name is used and build the full name if not
      if (!joinArtifactName.contains(PACKAGE_DELIMITER)) {
        joinArtifactName = fullTableNameBuilderFromViewSymbol(joinArtifactName, viewSymbol);
      }

      // Replace the select from dependant table if anywhere in the join with its full name
      String dependsOnTableShortName = shortTableNameExtractorFromViewSymbol(dependsOnTable, viewSymbol);
      joinFieldsSql = replaceWithQuotes(joinFieldsSql, dependsOnTableShortName, dependsOnTable);

      joinStatements.append(joinType).append(SPACE).append(QUOTE).append(joinArtifactName).append(QUOTE).append(SPACE);

      if (joinTableAlias != null) {
        joinStatements.append(ISqlKeywords.KEYWORD_AS).append(SPACE).append(QUOTE).append(joinTableAlias).append(QUOTE).append(SPACE);
        aliasesForReplacement.add(joinTableAlias);
      }

      joinStatements.append(joinFieldsSql).append(SPACE);
    }

    return joinStatements.toString();
  }

  private String fullTableNameBuilderFromViewSymbol(String tableName, ViewSymbol viewSymbol) {
    StringBuilder fullTableName = new StringBuilder();
    fullTableName.append(viewSymbol.getPackageId()).append(PACKAGE_DELIMITER).append(viewSymbol.getContext()).append(DOT).append(tableName);
    return fullTableName.toString();
  }

  private String shortTableNameExtractorFromViewSymbol(String fullTableName, ViewSymbol viewSymbol) {
    return fullTableName.replace(viewSymbol.getPackageId() + PACKAGE_DELIMITER + viewSymbol.getContext() + DOT, "");
  }

  private String replaceWithQuotes(String inContent, String toBeReplaced, String replacement) {
    return inContent.replaceAll(toBeReplaced + "[.]|\"" + toBeReplaced + "\"[.]", "\"" + replacement + "\".");
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
      columnModel.setDefaultValue(elementSymbol.getDefaultValue());
      columnModel.setDefaultValueDateTimeFunction(elementSymbol.isDefaultValueDateTimeFunction());
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
      if (!(dataType.getType() instanceof StructuredDataTypeSymbol)) {
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
        subElements.forEach(subE -> tableColumns.add(getAssociationForeignKeyColumn(associationSymbol, subE)));
      } else {
        tableColumns.add(getAssociationForeignKeyColumn(associationSymbol, fk));
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

  private XSKDataStructureHDBTableColumnModel getAssociationForeignKeyColumn(AssociationSymbol associationSymbol, EntityElementSymbol foreignKey){
    XSKDataStructureHDBTableColumnModel columnModel = transformFieldSymbolToColumnModel(foreignKey, false);
    columnModel.setPrimaryKey(associationSymbol.isKey());
    columnModel.setNullable(!associationSymbol.isNotNull());

    return columnModel;
  }
}