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
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintUniqueModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableIndexModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.model.hdbtabletype.XSKDataStructureHDBTableTypeModel;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.parser.hdbdd.exception.CDSRuntimeException;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AbstractAnnotationValue;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationArray;
import com.sap.xsk.parser.hdbdd.annotation.metadata.AnnotationObj;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.database.sql.ISqlKeywords;

import static org.eclipse.dirigible.database.sql.ISqlKeywords.SPACE;

public class HdbddTransformer {

  private static final String UNMANAGED_ASSOCIATION_MARKER = "@";
  private static final String CATALOG_ANNOTATION = "Catalog";
  private static final String CATALOG_OBJ_TABLE_TYPE = "tableType";
  private static final String SEARCH_INDEX_ANNOTATION = "SearchIndex";
  private static final String FUZZY_ANNOTATION = "fuzzy";
  private static final String FUZZY_SEARCH_INDEX_ENABLED = "enabled";
  private static final String DUMMY_TABLE = "DUMMY";
  private static final String QUOTE = "\"";
  private static final String DOT = ".";
  private static final String PACKAGE_DELIMITER = "::";
  private static final String INDEX = "index";
  private static final String UNIQUE = "unique";
  private static final String NAME = "name";
  private static final String ORDER = "order";
  private static final String ELEMENT_NAMES = "elementNames";

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
      String tableType = entitySymbol.getAnnotation(CATALOG_ANNOTATION).getKeyValuePairs().get(CATALOG_OBJ_TABLE_TYPE).getValue();
      tableModel.setTableType(tableType);

      if (entitySymbol.getAnnotation(CATALOG_ANNOTATION).getKeyValuePairs().get(INDEX) != null) {
        List<XSKDataStructureHDBTableIndexModel> indexes = new ArrayList<>();
        List<XSKDataStructureHDBTableConstraintUniqueModel> uniqueIndexes = new ArrayList<>();
        AnnotationArray catalogIndexAnnotationArray = (AnnotationArray) entitySymbol.getAnnotation(CATALOG_ANNOTATION).getKeyValuePairs()
            .get(INDEX);

        for (AbstractAnnotationValue currentAnnotationValue : catalogIndexAnnotationArray.getValues()) {
          AnnotationObj annotationObject = (AnnotationObj) currentAnnotationValue;
          boolean isUnique = Boolean.parseBoolean(getCatalogAnnotationValue(annotationObject, UNIQUE));
          String name = getCatalogAnnotationValue(annotationObject, NAME);
          String order = getCatalogAnnotationValue(annotationObject, ORDER);
          Set<String> indexColumnSet = new HashSet<>();

          ((AnnotationArray) annotationObject.getValue(ELEMENT_NAMES)).getValues()
              .forEach(currentElement -> indexColumnSet.add(currentElement.getValue()));

          if (!isUnique) {
            indexes.add(new XSKDataStructureHDBTableIndexModel(name, order, indexColumnSet, false));
          } else {
            uniqueIndexes.add(new XSKDataStructureHDBTableConstraintUniqueModel(name, order, indexColumnSet.toArray(String[]::new)));
          }
        }
        tableModel.setIndexes(indexes);
        tableModel.getConstraints().setUniqueIndices(uniqueIndexes);
      }
    }

    handlePossibleSearchIndexAnnotations(entitySymbol, tableModel);

    return tableModel;
  }

  private void handlePossibleSearchIndexAnnotations(EntitySymbol entitySymbol, XSKDataStructureHDBTableModel tableModel){
    for (int i = 0; i < entitySymbol.getElements().size(); i++) {
      EntityElementSymbol currentElement = entitySymbol.getElements().get(i);

      if (currentElement.getAnnotation(SEARCH_INDEX_ANNOTATION) != null) {

        boolean hasFuzzySearchIndex = false;
        Map<String, AbstractAnnotationValue> searchIndexAnnotationValueMap = currentElement.getAnnotation(SEARCH_INDEX_ANNOTATION).getKeyValuePairs();
        AnnotationObj fuzzyIndexAnnotationObject = (AnnotationObj) searchIndexAnnotationValueMap.get(FUZZY_ANNOTATION);
        AbstractAnnotationValue fuzzyIndexAnnotationValue = searchIndexAnnotationValueMap.get(FUZZY_SEARCH_INDEX_ENABLED);
        AbstractAnnotationValue fuzzyIndexAnnotationObjectValue = fuzzyIndexAnnotationObject != null ?
            fuzzyIndexAnnotationObject.getKeyValuePairs().get(FUZZY_SEARCH_INDEX_ENABLED) : null;

        if (fuzzyIndexAnnotationObjectValue != null){
          hasFuzzySearchIndex = Boolean.parseBoolean(fuzzyIndexAnnotationObjectValue.getValue());
        }
        else if (fuzzyIndexAnnotationValue != null){
          hasFuzzySearchIndex = Boolean.parseBoolean(fuzzyIndexAnnotationValue.getValue());
        }
        tableModel.getColumns().get(i).setFuzzySearchIndex(hasFuzzySearchIndex);
      }
    }
  }

  public XSKDataStructureHDBViewModel transformViewSymbolToHdbViewModel(ViewSymbol viewSymbol, String location) {
    XSKDataStructureHDBViewModel viewModel = new XSKDataStructureHDBViewModel();

    StringBuilder viewStatementSql = new StringBuilder();
    List<String> aliasesForReplacement = new ArrayList<>();

    viewStatementSql.append(ISqlKeywords.KEYWORD_VIEW).append(SPACE).append(QUOTE).append(viewSymbol.getSchema()).append(QUOTE)
        .append(DOT).append(QUOTE).append(viewSymbol.getFullName()).append(QUOTE).append(SPACE).append(ISqlKeywords.KEYWORD_AS)
        .append(SPACE);

    String selectStatementSql = traverseSelectStatements(viewSymbol, aliasesForReplacement, viewModel);
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

  public String traverseSelectStatements(ViewSymbol viewSymbol, List<String> aliasesForReplacement, XSKDataStructureHDBViewModel viewModel) {
    List<String> dependsOnTableList = new ArrayList<>();
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
        dependsOnTable = getFullTableName(viewSymbol, dependsOnTable);
        selectColumns = replaceWithQuotes(selectColumns, dependsOnTable, dependsOnTable);
      }
      dependsOnTableList.add(dependsOnTable);

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
    viewModel.setDependsOnTable(dependsOnTableList);

    return selectSql.toString();
  }

  public String traverseJoinStatements(SelectSymbol selectSymbol, ViewSymbol viewSymbol, String dependsOnTable,
      List<String> aliasesForReplacement) {
    StringBuilder joinStatements = new StringBuilder();

    for (Symbol symbol : selectSymbol.getJoinStatements()) {
      JoinSymbol joinSymbol = (JoinSymbol) symbol;
      String joinType = joinSymbol.getJoinType();
      String joinArtifactName = joinSymbol.getJoinArtifactName();
      String joinTableAlias = joinSymbol.getJoinTableAlias();
      String joinFieldsSql = joinSymbol.getJoinFields();

      // Check if the join artifact name contains :: to determine if full artifact name is used and build the full name if not
      if (!joinArtifactName.contains(PACKAGE_DELIMITER)) {
        joinArtifactName = getFullTableName(viewSymbol, joinArtifactName);
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

      columnModel.setCalculatedColumn(elementSymbol.isCalculatedColumn());
      columnModel.setStatement(elementSymbol.getStatement());
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

  private XSKDataStructureHDBTableColumnModel getAssociationForeignKeyColumn(AssociationSymbol associationSymbol,
      EntityElementSymbol foreignKey) {
    XSKDataStructureHDBTableColumnModel columnModel = transformFieldSymbolToColumnModel(foreignKey, false);
    columnModel.setPrimaryKey(associationSymbol.isKey());
    columnModel.setNullable(!associationSymbol.isNotNull());

    return columnModel;
  }

  private String getFullTableName(ViewSymbol dependingView, String tableName) {
    // Check if the dependant table name is DUMMY. This is a reserved table name for hana dummy tables. We make sure to make it in uppercase
    if (tableName.equalsIgnoreCase(DUMMY_TABLE)) {
      return tableName.toUpperCase();
    } else {
      Symbol resolvedDependsOnTable = dependingView.getEnclosingScope().resolve(tableName);
      if (resolvedDependsOnTable == null) {
        throw new CDSRuntimeException("Could not resolve referenced entity: " + tableName);
      }
      return resolvedDependsOnTable.getFullName();
    }
  }

  private String getCatalogAnnotationValue(AnnotationObj annotationObject, String value) {
    return annotationObject.getValue(value) != null ? annotationObject.getValue(value).getValue() : null;
  }
}