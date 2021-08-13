package com.sap.xsk.hdb.ds.transformer.hdbdd;

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableColumnModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.parser.hdbdd.symbols.entity.AssociationSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntityElementSymbol;
import com.sap.xsk.parser.hdbdd.symbols.entity.EntitySymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.BuiltInTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.DataTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.custom.StructuredDataTypeSymbol;
import com.sap.xsk.parser.hdbdd.symbols.type.field.FieldSymbol;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class HdbddTransformer {

  public XSKDataStructureHDBTableModel transformEntitySymbolToTableModel(EntitySymbol entitySymbol) {
    XSKDataStructureHDBTableModel tableModel = new XSKDataStructureHDBTableModel();
    tableModel.setName(entitySymbol.getFullName());
    List<XSKDataStructureHDBTableColumnModel> tableColumns = new ArrayList<>();
    entitySymbol.getElements().forEach(currentElement -> {
      if (currentElement.getType() instanceof StructuredDataTypeSymbol) {
        List<EntityElementSymbol> subElements = getStructuredTypeSubElements(currentElement);
        subElements.forEach(subE -> {
          tableColumns.add(transformEntityElementToColumnModel(subE));
        });
      } else {
        tableColumns.add(transformEntityElementToColumnModel(currentElement));
      }
    });


    entitySymbol.getAssociations().forEach(associationSymbol -> {
      List<XSKDataStructureHDBTableColumnModel> associationColumns = transformAssociationToColumnModels(associationSymbol);
      XSKDataStructureHDBTableConstraintForeignKeyModel foreignKeyModel = new XSKDataStructureHDBTableConstraintForeignKeyModel();
      String[] referencedColumns = associationColumns.stream().map(XSKDataStructureHDBTableColumnModel::getName).toArray(String[]::new);
      associationColumns.forEach(ac -> ac.setName(associationSymbol.getName() + "." + ac.getName()));
      String[] foreignKeyColumns = associationColumns.stream().map(XSKDataStructureHDBTableColumnModel::getName).toArray(String[]::new);
      String foreignKeyName = tableModel.getName() + "." + associationSymbol.getName();

      foreignKeyModel.setName(foreignKeyName);
      foreignKeyModel.setReferencedTable(associationSymbol.getTarget().getFullName());
      foreignKeyModel.setReferencedColumns(referencedColumns);
      foreignKeyModel.setColumns(foreignKeyColumns);

      tableColumns.addAll(associationColumns);
      tableModel.getConstraints().getForeignKeys().add(foreignKeyModel);
    });

    return tableModel;
  }

  private XSKDataStructureHDBTableColumnModel transformEntityElementToColumnModel(EntityElementSymbol entityElement) {
    XSKDataStructureHDBTableColumnModel columnModel = new XSKDataStructureHDBTableColumnModel();
    columnModel.setName(entityElement.getName());
    columnModel.setPrimaryKey(entityElement.isKey());
    columnModel.setNullable(!entityElement.isNotNull());
    columnModel.setDefaultValue(entityElement.getValue());

    if (entityElement.getType() instanceof BuiltInTypeSymbol) {
      BuiltInTypeSymbol builtInTypeSymbol = (BuiltInTypeSymbol) entityElement.getType();
      setSqlType(columnModel, builtInTypeSymbol);
    } else if (entityElement.getType() instanceof DataTypeSymbol) {
      DataTypeSymbol dataType = (DataTypeSymbol) entityElement.getType();
      BuiltInTypeSymbol builtInType = (BuiltInTypeSymbol) dataType.getType();
      setSqlType(columnModel, builtInType);
    }

    return columnModel;
  }

  private List<XSKDataStructureHDBTableColumnModel> transformAssociationToColumnModels(AssociationSymbol associationSymbol) {
    List<XSKDataStructureHDBTableColumnModel> tableColumns = new ArrayList<>();
    associationSymbol.getForeignKeys().forEach(fk -> {
      if (fk.getType() instanceof StructuredDataTypeSymbol) {
        List<EntityElementSymbol> subElements = getStructuredTypeSubElements(fk);
        subElements.forEach(subE ->
          tableColumns.add(transformEntityElementToColumnModel(subE)));
      } else {
        tableColumns.add(transformEntityElementToColumnModel(fk));
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

  private List<EntityElementSymbol> getStructuredTypeSubElements(EntityElementSymbol entityElementSymbol) {
    StructuredDataTypeSymbol structuredDataType = (StructuredDataTypeSymbol) entityElementSymbol.getType();
    String elementName = entityElementSymbol.getName();
    List<EntityElementSymbol> subElements = new ArrayList<>();
    structuredDataType.getFields().values().forEach(v -> {
      FieldSymbol field = (FieldSymbol) v;
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
