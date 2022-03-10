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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.database.sql.ISqlKeywords;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.eclipse.dirigible.database.sql.builders.records.SelectBuilder;

public class HdbddTransformer {

  private static final String UNMANAGED_ASSOCIATION_MARKER = "@";
  private static final List<String> JOIN_TYPE_CONSTANTS = List.of(ISqlKeywords.KEYWORD_INNER, ISqlKeywords.KEYWORD_OUTER,
      ISqlKeywords.KEYWORD_LEFT, ISqlKeywords.KEYWORD_RIGHT, ISqlKeywords.KEYWORD_FULL);

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
    return tableModel;
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

  public XSKDataStructureHDBViewModel transformViewSymbolToHdbViewModel(ViewSymbol viewSymbol, String location) {
    XSKDataStructureHDBViewModel viewModel = new XSKDataStructureHDBViewModel();

    StringBuilder finalSql = new StringBuilder();
//    SelectBuilder controlSqlBuilder = SqlFactory.getDefault().select().column("");

    // Preparing the view sql
    // TODO use create().view()
    String viewSql = "VIEW \"" + viewSymbol.getSchema() + "\".\"" + viewSymbol.getFullName() + "\" AS ";

    // We add all select statements to a list
    List<String> selectStatements = new ArrayList<>();

    // We loop through each select statement
//    viewSymbol.getSelectStatements().forEach(ss -> {

    for(Symbol ss: viewSymbol.getSelectStatements()) {
      SelectBuilder controlSqlBuilder;
      String controlSql = "";

      // Get the table on which the select depends
      String dependsOnTable = ((((SelectSymbol) ss).getDependsOnTable() == null) ? "" : ((SelectSymbol) ss).getDependsOnTable());

      // Get the depnding table's alias. Returns null in case no alias
      String dependingTableAlias = ((SelectSymbol) ss).getDependingTableAlias();

      // If there is an alias for the depending table concatenate with AS
      //TODO remove
      String dependingTableAliasSql = (dependingTableAlias == null) ? ""
          : "AS \"" + dependingTableAlias + "\"";

      // Get the select columns which is the {...} part in the hdbdd view definition
      String selectColumns = ((((SelectSymbol) ss).getColumnsSql() == null) ? "" : ((SelectSymbol) ss).getColumnsSql());

      // Get the where part after the select columns
      // TODO possibly replace at the end
      String where = ((((SelectSymbol) ss).getWhereSql() == null) ? "" : ((SelectSymbol) ss).getWhereSql());

      // Define union and distict if they are true.
      //TODO replace below
      String union = ((((SelectSymbol) ss).getUnion() == false) ? "" : "UNION");
      String distinct = ((((SelectSymbol) ss).getDistinct() == false) ? "" : "DISTINCT");

      boolean unionBol = ((SelectSymbol) ss).getUnion();
      boolean distinctBol = ((SelectSymbol) ss).getDistinct();

      // Check if the depending table has :: to know whether short or full name is used in the hdbdd view definiton. In case it is not we should build the full name
      if (!dependsOnTable.contains("::")) {
        // Check if the depending table name is DUMMY. This is a reserved table name for hana dummy tables. We make sure to make it in uppercase
        if (dependsOnTable.equalsIgnoreCase("dummy")) {
          dependsOnTable = dependsOnTable.toUpperCase();
        } else {
          // Build the full name of the depending table.
          String dependsOnTableFullName = viewSymbol.getPackageId() + "::" + viewSymbol.getContext() + "." + dependsOnTable;
          // Replace the short ame in the select columns with the full name
          selectColumns = selectColumns.replace(dependsOnTable, dependsOnTableFullName);
          // Set the depending table to be with the full name
          dependsOnTable = dependsOnTableFullName;
        }
      }

      // If the depending table has an alias we replace it in the select columns to make sure it is with quotes
      // TODO check if needed when using builder
      if (dependingTableAlias != null) {
        selectColumns = selectColumns.replaceAll("" + dependingTableAlias + "[.]|\"" + dependingTableAlias + "\"[.]", "\"" + dependingTableAlias + "\".");
      }

      // Define two lists for join statements and join aliases
      List<String> joinStatements = new ArrayList<>();
      List<String> joinAliases = new ArrayList<>();

      // TODO leave only one
      String finalDependsOnTable = dependsOnTable;

      if(distinctBol) {
        controlSqlBuilder = SqlFactory.getDefault().select().distinct().column(selectColumns).from(finalDependsOnTable, dependingTableAlias);
      } else {
        controlSqlBuilder = SqlFactory.getDefault().select().column(selectColumns).from(finalDependsOnTable, dependingTableAlias);
      }

      // Loop through each join statement
//      ((SelectSymbol) ss).getJoinStatements().forEach(js -> {
      for (Symbol js: ((SelectSymbol) ss).getJoinStatements()) {
        // Get the join type
        String joinType = ((((JoinSymbol) js).getJoinType() == null) ? "JOIN" : ((JoinSymbol) js).getJoinType());
        joinType = joinType.substring(0, joinType.length() - 5).toUpperCase();
        // Get the join artifact name
        String joinArtifactName = ((((JoinSymbol) js).getJoinArtifactName() == null) ? "" : ((JoinSymbol) js).getJoinArtifactName());

        // Get the join artifact alias
        String joinTableAlias = ((JoinSymbol) js).getJoinTableAlias();
        // If not null add it to the join table aliases list for later use
        // TODO possibly remove
        if (joinTableAlias != null) {
          joinAliases.add(joinTableAlias);
        }

        // In case the alias is not null build concatenate with AS
        // TODO remove
        String joinTableAliasSql = ((joinTableAlias == null) ? "" : "AS \"" + joinTableAlias + "\"");

        // Get the rest part of the join
        String joinFieldsSql = ((((JoinSymbol) js).getJoinFields() == null) ? "" : ((JoinSymbol) js).getJoinFields());

        // Check if the join artifact name contains :: to determine if full artfact name is used and build the full name if not
        if (!joinArtifactName.contains("::")) {
          String joinArtifactFullName = viewSymbol.getPackageId() + "::" + viewSymbol.getContext() + "." + joinArtifactName;
          joinArtifactName = joinArtifactFullName;
        }

        // Replace the the select from depending table if anywhere in the join with it's full name
        joinFieldsSql = joinFieldsSql.replace(finalDependsOnTable.replace(viewSymbol.getPackageId() + "::" + viewSymbol.getContext() + ".", ""), finalDependsOnTable);

        // Build the final join statement and add to the join statements list
        // TODO remove
        String joinStatement = joinType + " \"" + joinArtifactName + "\"" + " " + joinTableAliasSql + " " + joinFieldsSql;
        joinStatements.add(joinStatement);

        // remove on keyword
        joinFieldsSql = joinFieldsSql.substring(3);
        if (JOIN_TYPE_CONSTANTS.contains(joinType)) {
          controlSqlBuilder.genericJoin(joinType, joinArtifactName, joinFieldsSql, joinTableAlias);
        } else {
          controlSqlBuilder.join(joinArtifactName, joinFieldsSql, joinTableAlias);
        }
      };

      // Concatenate the join statements into one string which will be used in the build of the select statement
      // TODO remove
      String joinStatement = String.join(" ", joinStatements);

      // If the depending table of the select statement's alias is not null we should replace it in the join statement and where statement to make sure it has quotes
      // TODO check if needed when using builder
      if (dependingTableAlias != null) {
        controlSql = controlSql.replaceAll("" + dependingTableAlias + "[.]|\"" + dependingTableAlias + "\"[.]", "\"" + dependingTableAlias + "\".");
        where = where.replaceAll("" + dependingTableAlias + "[.]|\"" + dependingTableAlias + "\"[.]", "\"" + dependingTableAlias + "\".");
      }

      // Replace the join aliases in the join, select columns and where statements to make sure it is with quotes
      // TODO check if needed when using builder
      for ( String joinAlias : joinAliases ) {
        controlSql = controlSql.replaceAll("" + joinAlias + "[.]|\"" + joinAlias + "\"[.]", "\"" + joinAlias + "\".");
        selectColumns = selectColumns.replaceAll("" + joinAlias + "[.]|\"" + joinAlias + "\"[.]", "\"" + joinAlias + "\".");
        where = where.replaceAll("" + joinAlias + "[.]|\"" + joinAlias + "\"[.]", "\"" + joinAlias + "\".");
      };

      if(!(((SelectSymbol) ss).getWhereSql() == null)) {
        // TODO add quotes
//        controlSqlBuilder.where(((SelectSymbol) ss).getWhereSql());
        // remove where keyword
        controlSqlBuilder.where(where.substring(6));
      }

      if(unionBol) {
        controlSql = ISqlKeywords.KEYWORD_UNION + " " + controlSqlBuilder.toString();
      } else {
        controlSql = controlSqlBuilder.toString();
      }

      // Build the final select statement and add it to the list of select statements
      String selectStatement =
          union + " SELECT " + distinct + " " + selectColumns + " FROM " + "\"" + dependsOnTable + "\"" + " " + dependingTableAliasSql + " "
              + joinStatement + " " + where;

      selectStatements.add(selectStatement);
      finalSql.append(controlSql).append(" ");
    };

    finalSql.insert(0, viewSql);
    // Build the final view sql
    viewSql = viewSql + String.join(" ", selectStatements);

    viewModel.setDbContentType(XSKDBContentType.OTHERS);
    viewModel.setName(viewSymbol.getFullName());
    viewModel.setSchema(viewSymbol.getSchema());
    viewModel.setRawContent(viewSql);
    viewModel.setLocation(location);
    return viewModel;
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