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

public class HdbddTransformer {

  private static final String UNMANAGED_ASSOCIATION_MARKER = "@";

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

    // Preparing the view sql
    String viewSql = "VIEW \"" + viewSymbol.getSchema() + "\".\"" + viewSymbol.getFullName() + "\" AS ";

    // We add all select statements to a list
    List<String> selectStatements = new ArrayList<>();

    // We loop through each select statement
    viewSymbol.getSelectStatements().forEach(ss -> {

      // Get the table on which the select depends
      String dependsOnTable = ((((SelectSymbol) ss).getDependsOnTable() == null) ? "" : ((SelectSymbol) ss).getDependsOnTable());

      // Get the depnding table's alias. Returns null in case no alias
      String dependingTableAlias = ((SelectSymbol) ss).getDependingTableAlias();

      // If there is an alias for the depending table concatenate with AS
      String dependingTableAliasSql = (dependingTableAlias == null) ? ""
          : "AS \"" + dependingTableAlias + "\"";

      // Get the select columns which is the {...} part in the hdbdd view definition
      String selectColumns = ((((SelectSymbol) ss).getColumnsSql() == null) ? "" : ((SelectSymbol) ss).getColumnsSql());

      // Get the where part after the select columns
      String where = ((((SelectSymbol) ss).getWhereSql() == null) ? "" : ((SelectSymbol) ss).getWhereSql());

      // Define union and distict if they are true.
      String union = ((((SelectSymbol) ss).getUnion() == false) ? "" : "UNION");
      String distinct = ((((SelectSymbol) ss).getDistinct() == false) ? "" : "DISTINCT");

      // Check if the depending table has :: to know whether short or full name is used in the hdbdd view definiton. In case it is not we should build the full name
      if (!dependsOnTable.contains("::")) {
        // Check if the depending table name is DUMMY. This is a reserved table name for hana dummy tables. We make sure to make it in uppercase
        if (dependsOnTable.equalsIgnoreCase("dummy")) {
          dependsOnTable = dependsOnTable.toUpperCase();
        }
        else {
          // Build the full name of the depending table.
          String dependsOnTableFullName = viewSymbol.getPackageId() + "::" + viewSymbol.getContext() + "." + dependsOnTable;
          // Replace the short ame in the select columns with the full name
          selectColumns = selectColumns.replace(dependsOnTable, dependsOnTableFullName);
          // Set the depending table to be with the full name
          dependsOnTable = dependsOnTableFullName;
        }
      }

      // If the depending table has an alias we replace it in the select columns to make sure it is with quotes
      if (dependingTableAlias != null) {
        selectColumns = selectColumns.replaceAll("" + dependingTableAlias + "[.]|\"" + dependingTableAlias + "\"[.]", "\"" + dependingTableAlias + "\".");
      }

      // Define two lists for join statements and join aliases
      List<String> joinStatements = new ArrayList<>();
      List<String> joinAliases = new ArrayList<>();

      String finalDependsOnTable = dependsOnTable;

      // Loop through each join statement
      ((SelectSymbol) ss).getJoinStatements().forEach(js -> {
        // Get the join type
        String joinType = ((((JoinSymbol) js).getJoinType() == null) ? "JOIN" : ((JoinSymbol) js).getJoinType());
        // Get the join artifact name
        String joinArtifactName = ((((JoinSymbol) js).getJoinArtifactName() == null) ? "" : ((JoinSymbol) js).getJoinArtifactName());

        // Get the join artifact alias
        String joinTableAlias = ((JoinSymbol) js).getJoinTableAlias();
        // If not null add it to the join table aliases list for later use
        if (joinTableAlias != null) {
          joinAliases.add(joinTableAlias);
        }

        // In case the alias is not null build concatenate with AS
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
        String joinStatement = joinType + " \"" + joinArtifactName + "\"" + " " + joinTableAliasSql + " " + joinFieldsSql;
        joinStatements.add(joinStatement);
      });

      // Concatenate the join statements into one string which will be used in the build of the select statement
      String joinStatement = String.join(" ", joinStatements);

      // If the depending table of the select statement's alias is not null we should replace it in the join statement and where statement to make sure it has quotes
      if (dependingTableAlias != null) {
        joinStatement = joinStatement.replaceAll("" + dependingTableAlias + "[.]|\"" + dependingTableAlias + "\"[.]", "\"" + dependingTableAlias + "\".");
        where = where.replaceAll("" + dependingTableAlias + "[.]|\"" + dependingTableAlias + "\"[.]", "\"" + dependingTableAlias + "\".");
      }

      // Replace the join aliases in the join, select columns and where statements to make sure it is with quotes
      for ( String joinAlias : joinAliases ) {
        joinStatement = joinStatement.replaceAll("" + joinAlias + "[.]|\"" + joinAlias + "\"[.]", "\"" + joinAlias + "\".");
        selectColumns = selectColumns.replaceAll("" + joinAlias + "[.]|\"" + joinAlias + "\"[.]", "\"" + joinAlias + "\".");
        where = where.replaceAll("" + joinAlias + "[.]|\"" + joinAlias + "\"[.]", "\"" + joinAlias + "\".");
      };

      // Build the final select statement and add it to the list of select statements
      String selectStatement =
          union + " SELECT " + distinct + " " + selectColumns + " FROM " + "\"" + dependsOnTable + "\"" + " " + dependingTableAliasSql + " "
              + joinStatement + " " + where;

      selectStatements.add(selectStatement);
    });

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