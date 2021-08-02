/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.parser.hdbtable.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sap.xsk.parser.hdbtable.core.HdbtableBaseVisitor;
import com.sap.xsk.parser.hdbtable.core.HdbtableParser;
import com.sap.xsk.parser.hdbtable.exceptions.XSKHDBTableDuplicatePropertyException;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.HashSet;
import java.util.List;

import static com.sap.xsk.parser.hdbtable.constants.HdbtablePropertiesConstants.*;

public class XSKHDBTABLECoreVisitor extends HdbtableBaseVisitor<JsonElement> {

    @Override
    public JsonElement visitHdbtableDefinition(HdbtableParser.HdbtableDefinitionContext ctx) {
        JsonObject hdbtableObject = new JsonObject();
        HashSet<String> uniqueTableProperties = new HashSet<>();
        List<ParseTree> ctxList = ctx.children;

        for(ParseTree tree : ctxList) {
            if(tree.getChild(0) instanceof HdbtableParser.SchemaNamePropContext){
                checkPropertyDeclarationUniqueness(HDBTABLE_PROPERTY_SCHEMA_NAME, uniqueTableProperties);
                hdbtableObject.add(HDBTABLE_PROPERTY_SCHEMA_NAME, visitSchemaNameProp((HdbtableParser.SchemaNamePropContext) tree.getChild(0)));
            }else if(tree.getChild(0) instanceof HdbtableParser.TableTypePropContext) {
                checkPropertyDeclarationUniqueness(HDBTABLE_PROPERTY_TABLE_TYPE, uniqueTableProperties);
                hdbtableObject.add(HDBTABLE_PROPERTY_TABLE_TYPE, visitTableTypeProp((HdbtableParser.TableTypePropContext) tree.getChild(0)));
            }else if (tree.getChild(0) instanceof HdbtableParser.TableColumnsPropContext) {
                checkPropertyDeclarationUniqueness(HDBTABLE_PROPERTY_COLUMNS, uniqueTableProperties);
                hdbtableObject.add (HDBTABLE_PROPERTY_COLUMNS, visitTableColumnsProp((HdbtableParser.TableColumnsPropContext) tree.getChild(0)));
            }else if (tree.getChild(0) instanceof HdbtableParser.TableIndexesPropContext){
                checkPropertyDeclarationUniqueness(HDBTABLE_PROPERTY_INDEXES, uniqueTableProperties);
                hdbtableObject.add (HDBTABLE_PROPERTY_INDEXES, visitTableIndexesProp((HdbtableParser.TableIndexesPropContext) tree.getChild(0)));
            }else if (tree.getChild(0) instanceof HdbtableParser.TablePrimaryKeyPropContext){
                checkPropertyDeclarationUniqueness(HDBTABLE_PROPERTY_PKCOLUMNS, uniqueTableProperties);
                hdbtableObject.add(HDBTABLE_PROPERTY_PKCOLUMNS, visitTablePrimaryKeyProp((HdbtableParser.TablePrimaryKeyPropContext) tree.getChild(0)));
            }else if (tree.getChild(0) instanceof HdbtableParser.DescriptionPropContext){
                checkPropertyDeclarationUniqueness(HDBTABLE_PROPERTY_DESCRIPTION, uniqueTableProperties);
                hdbtableObject.add(HDBTABLE_PROPERTY_DESCRIPTION, visitDescriptionProp((HdbtableParser.DescriptionPropContext) tree.getChild(0)));
            }else if(tree.getChild(0) instanceof HdbtableParser.TemporaryPropContext) {
                checkPropertyDeclarationUniqueness(HDBTABLE_PROPERTY_TEMPORARY, uniqueTableProperties);
                hdbtableObject.add(HDBTABLE_PROPERTY_TEMPORARY, visitTemporaryProp((HdbtableParser.TemporaryPropContext) tree.getChild(0)));
            }else if(tree.getChild(0) instanceof HdbtableParser.PublicPropContext) {
                checkPropertyDeclarationUniqueness(HDBTABLE_PROPERTY_PUBLIC, uniqueTableProperties);
                hdbtableObject.add(HDBTABLE_PROPERTY_PUBLIC, visitPublicProp((HdbtableParser.PublicPropContext) tree.getChild(0)));
            }else if(tree.getChild(0) instanceof HdbtableParser.LoggingTypePropContext) {
                checkPropertyDeclarationUniqueness(HDBTABLE_PROPERTY_LOGGING_TYPE, uniqueTableProperties);
                hdbtableObject.add (HDBTABLE_PROPERTY_LOGGING_TYPE, visitLoggingTypeProp((HdbtableParser.LoggingTypePropContext) tree.getChild(0)));
            }else if(tree.getChild(0) instanceof HdbtableParser.TablePrimaryKeyIndexTypePropContext) {
                checkPropertyDeclarationUniqueness(HDBTABLE_PROPERTY_INDEX_TYPE, uniqueTableProperties);
                hdbtableObject.add(HDBTABLE_PROPERTY_INDEX_TYPE, visitTablePrimaryKeyIndexTypeProp((HdbtableParser.TablePrimaryKeyIndexTypePropContext) tree.getChild(0)));
            }
        }

        return hdbtableObject;
    }

    @Override
    public JsonElement visitTableColumnsProp(HdbtableParser.TableColumnsPropContext ctx) {
       JsonArray tableColumns = new JsonArray();
        if(ctx!=null && ctx.columnsObject()!=null) {
            ctx.columnsObject().forEach(column -> {
                tableColumns.add(visitColumnsObject(column));
            });
        }
        return tableColumns;
    }

    @Override
    public JsonElement visitIndexesObject(HdbtableParser.IndexesObjectContext ctx) {
        JsonObject indexesObject = new JsonObject();
        List<ParseTree> ctxList = ctx.children;
        HashSet<String> uniqueIndexProperties = new HashSet<>();
        for(ParseTree tree : ctxList) {
            if (tree.getChild(0) instanceof HdbtableParser.IndexAssignNameContext) {
                checkPropertyDeclarationUniqueness(INDEX_PROPERTY_NAME,uniqueIndexProperties);
                indexesObject.add(INDEX_PROPERTY_NAME, visitIndexAssignName((HdbtableParser.IndexAssignNameContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.IndexAssignUniqueContext) {
                checkPropertyDeclarationUniqueness(INDEX_PROPERTY_UNIQUE,uniqueIndexProperties);
                indexesObject.add(INDEX_PROPERTY_UNIQUE, visitIndexAssignUnique((HdbtableParser.IndexAssignUniqueContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.IndexAssignOrderContext) {
                checkPropertyDeclarationUniqueness(INDEX_PROPERTY_ORDER,uniqueIndexProperties);
                indexesObject.add(INDEX_PROPERTY_ORDER, visitIndexAssignOrder((HdbtableParser.IndexAssignOrderContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.IndexAssignIndexColumnsContext) {
                checkPropertyDeclarationUniqueness(INDEX_PROPERTY_INDEX_COLUMNS,uniqueIndexProperties);
                indexesObject.add(INDEX_PROPERTY_INDEX_COLUMNS, visitIndexAssignIndexColumns((HdbtableParser.IndexAssignIndexColumnsContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.IndexAssignIndexTypeContext) {
                checkPropertyDeclarationUniqueness(INDEX_PROPERTY_INDEX_TYPE,uniqueIndexProperties);
                indexesObject.add(INDEX_PROPERTY_INDEX_TYPE, visitIndexAssignIndexType((HdbtableParser.IndexAssignIndexTypeContext) tree.getChild(0)));
            }
        }

        return indexesObject;
    }

    @Override
    public JsonElement visitColumnAssignUnique(HdbtableParser.ColumnAssignUniqueContext ctx) {
        return (ctx != null && ctx.BOOLEAN() !=null) ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText())) : null;
    }

    @Override
    public JsonElement visitTableIndexesProp(HdbtableParser.TableIndexesPropContext ctx) {
        JsonArray tableIndexes = new JsonArray();
        if(ctx!=null && ctx.indexesObject()!=null){
            ctx.indexesObject().forEach(index -> {
                tableIndexes.add(visitIndexesObject(index));
            });
        }
        return tableIndexes;
    }

    @Override
    public JsonElement visitColumnAssignDefaultValue(HdbtableParser.ColumnAssignDefaultValueContext ctx) {
        if(ctx!=null && ctx.STRING()!=null) {
            return new JsonPrimitive(handleStringLiteral(ctx.STRING().getText()));
        }else if(ctx!=null && ctx.INT()!=null) {
            return new JsonPrimitive(Integer.parseInt(ctx.INT().getText()));
        } else {
            return null;
        }
    }

    @Override
    public JsonElement visitColumnAssignName(HdbtableParser.ColumnAssignNameContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)?new JsonPrimitive(handleStringLiteral(ctx.STRING().getText())):null;
    }

    @Override
    public JsonElement visitIndexAssignIndexColumns(HdbtableParser.IndexAssignIndexColumnsContext ctx) {
        return (ctx!=null && ctx.indexColumnsArray()!=null)? visitIndexColumnsArray(ctx.indexColumnsArray()): new JsonArray();
    }

    @Override
    public JsonElement visitColumnsObject(HdbtableParser.ColumnsObjectContext ctx) {
        JsonObject columnsObject = new JsonObject();
        List<ParseTree> ctxList = ctx.children;
        HashSet<String> uniqueColumnProperties = new HashSet<>();
        for(ParseTree tree : ctxList) {
            if (tree.getChild(0) instanceof HdbtableParser.ColumnAssignNameContext) {
                checkPropertyDeclarationUniqueness(COLUMN_PROPERTY_NAME,uniqueColumnProperties);
                columnsObject.add(COLUMN_PROPERTY_NAME, visitColumnAssignName((HdbtableParser.ColumnAssignNameContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.ColumnAssignSQLTypeContext) {
                checkPropertyDeclarationUniqueness(COLUMN_PROPERTY_SQL_TYPE, uniqueColumnProperties);
                columnsObject.add(COLUMN_PROPERTY_SQL_TYPE, visitColumnAssignSQLType((HdbtableParser.ColumnAssignSQLTypeContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.ColumnAssignNullableContext) {
                checkPropertyDeclarationUniqueness(COLUMN_PROPERTY_NULLABLE, uniqueColumnProperties);
                columnsObject.add(COLUMN_PROPERTY_NULLABLE, visitColumnAssignNullable((HdbtableParser.ColumnAssignNullableContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.ColumnAssignUniqueContext) {
                checkPropertyDeclarationUniqueness(COLUMN_PROPERTY_UNIQUE, uniqueColumnProperties);
                columnsObject.add(COLUMN_PROPERTY_UNIQUE, visitColumnAssignUnique((HdbtableParser.ColumnAssignUniqueContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.ColumnAssignLengthContext) {
                checkPropertyDeclarationUniqueness(COLUMN_PROPERTY_LENGTH, uniqueColumnProperties);
                columnsObject.add(COLUMN_PROPERTY_LENGTH, visitColumnAssignLength((HdbtableParser.ColumnAssignLengthContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.ColumnAssignPrecisionContext) {
                checkPropertyDeclarationUniqueness(COLUMN_PROPERTY_PRECISION, uniqueColumnProperties);
                columnsObject.add(COLUMN_PROPERTY_PRECISION, visitColumnAssignPrecision((HdbtableParser.ColumnAssignPrecisionContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.ColumnAssignCommentContext) {
                checkPropertyDeclarationUniqueness(COLUMN_PROPERTY_COMMENT, uniqueColumnProperties);
                columnsObject.add(COLUMN_PROPERTY_COMMENT, visitColumnAssignComment((HdbtableParser.ColumnAssignCommentContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.ColumnAssignScaleContext) {
                checkPropertyDeclarationUniqueness(COLUMN_PROPERTY_SCALE, uniqueColumnProperties);
                columnsObject.add(COLUMN_PROPERTY_SCALE, visitColumnAssignScale((HdbtableParser.ColumnAssignScaleContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbtableParser.ColumnAssignDefaultValueContext) {
                checkPropertyDeclarationUniqueness(COLUMN_PROPERTY_DEFAULT_VALUE, uniqueColumnProperties);
                columnsObject.add(COLUMN_PROPERTY_DEFAULT_VALUE, visitColumnAssignDefaultValue((HdbtableParser.ColumnAssignDefaultValueContext) tree.getChild(0)));
            }
        }
        return columnsObject;
    }

    @Override
    public JsonElement visitTableTypeProp(HdbtableParser.TableTypePropContext ctx) {
        return (ctx!=null && ctx.TABLETYPE()!=null)?new JsonPrimitive(ctx.TABLETYPE().getText()):null;
    }

    @Override
    public JsonElement visitColumnAssignLength(HdbtableParser.ColumnAssignLengthContext ctx) {
        return (ctx!=null && ctx.INT()!=null)?new JsonPrimitive(Integer.parseInt(ctx.INT().getText())):null;
    }

    @Override
    public JsonElement visitIndexColumnsArray(HdbtableParser.IndexColumnsArrayContext ctx) {
        JsonArray indexColumnsArray = new JsonArray();
        if(ctx!=null && ctx.STRING()!=null){
            ctx.STRING().forEach(column -> {
                indexColumnsArray.add(new JsonPrimitive(handleStringLiteral(column.getText())));
            });
        }
        return indexColumnsArray;
    }

    @Override
    public JsonElement visitColumnAssignScale(HdbtableParser.ColumnAssignScaleContext ctx) {
        return (ctx!=null && ctx.INT()!=null)?new JsonPrimitive(Integer.parseInt(ctx.INT().getText())):null;
    }

    @Override
    public JsonElement visitTablePrimaryKeyIndexTypeProp(HdbtableParser.TablePrimaryKeyIndexTypePropContext ctx) {
        return (ctx!=null && ctx.INDEXTYPE()!=null)?new JsonPrimitive(ctx.INDEXTYPE().getText()):null;
    }

    @Override
    public JsonElement visitColumnAssignSQLType(HdbtableParser.ColumnAssignSQLTypeContext ctx) {
        return (ctx!=null && ctx.SQLTYPES()!=null)?new JsonPrimitive(ctx.SQLTYPES().getText()):null;
    }

    @Override
    public JsonElement visitIndexAssignIndexType(HdbtableParser.IndexAssignIndexTypeContext ctx) {
        return (ctx!=null && ctx.INDEXTYPE()!=null)? new JsonPrimitive(ctx.INDEXTYPE().getText()) : null;
    }

    @Override
    public JsonElement visitIndexAssignUnique(HdbtableParser.IndexAssignUniqueContext ctx) {
        return (ctx != null && ctx.BOOLEAN() !=null) ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText())) : null;
    }

    @Override
    public JsonElement visitLoggingTypeProp(HdbtableParser.LoggingTypePropContext ctx) {
        return (ctx!=null && ctx.TABLELOGGINGTYPE()!=null)?new JsonPrimitive(ctx.TABLELOGGINGTYPE().getText()):null;
    }

    @Override
    public JsonElement visitColumnAssignNullable(HdbtableParser.ColumnAssignNullableContext ctx) {
        return (ctx != null && ctx.BOOLEAN() !=null) ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText())) : null;
    }

    @Override
    public JsonElement visitTemporaryProp(HdbtableParser.TemporaryPropContext ctx) {
        return (ctx != null && ctx.BOOLEAN() !=null) ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText())) : null;
    }

    @Override
    public JsonElement visitPublicProp(HdbtableParser.PublicPropContext ctx) {
        return (ctx != null && ctx.BOOLEAN() !=null) ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText())) : null;
    }

    @Override
    public JsonElement visitIndexAssignOrder(HdbtableParser.IndexAssignOrderContext ctx) {
        return (ctx!=null && ctx.ORDER()!=null)?new JsonPrimitive(ctx.ORDER().getText()):null;
    }

    @Override
    public JsonElement visitTablePrimaryKeyProp(HdbtableParser.TablePrimaryKeyPropContext ctx) {
        return (ctx!=null && ctx.tablePrimaryKeyColumnsProp()!=null)? visitTablePrimaryKeyColumnsProp(ctx.tablePrimaryKeyColumnsProp()): new JsonArray();
    }

    @Override
    public JsonElement visitColumnAssignPrecision(HdbtableParser.ColumnAssignPrecisionContext ctx) {
        return (ctx!=null && ctx.INT()!=null)?new JsonPrimitive(Integer.parseInt(ctx.INT().getText())):null;
    }

    @Override
    public JsonElement visitSchemaNameProp(HdbtableParser.SchemaNamePropContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)?new JsonPrimitive(handleStringLiteral(ctx.STRING().getText())):null;
    }

    @Override
    public JsonElement visitTablePrimaryKeyColumnsProp(HdbtableParser.TablePrimaryKeyColumnsPropContext ctx) {
        JsonArray primaryKeyArray = new JsonArray();
        if(ctx!=null && ctx.STRING()!=null){
            ctx.STRING().forEach(primaryKey -> {
                primaryKeyArray.add(new JsonPrimitive(handleStringLiteral(primaryKey.getText())));
            });
        }
        return primaryKeyArray;
    }

    @Override
    public JsonElement visitColumnAssignComment(HdbtableParser.ColumnAssignCommentContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)?new JsonPrimitive(handleStringLiteral(ctx.STRING().getText())):null;
    }

    @Override
    public JsonElement visitDescriptionProp(HdbtableParser.DescriptionPropContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)?new JsonPrimitive(handleStringLiteral(ctx.STRING().getText())):null;
    }

    @Override
    public JsonElement visitIndexAssignName(HdbtableParser.IndexAssignNameContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)?new JsonPrimitive(handleStringLiteral(ctx.STRING().getText())):null;
    }

    private void checkPropertyDeclarationUniqueness(String property, HashSet<String> uniqueProperties) {
        if (!uniqueProperties.contains(property)) {
            uniqueProperties.add(property);
        } else {
            throw new XSKHDBTableDuplicatePropertyException(String.format("Property %s is already declared!", property));
        }
    }

    private String handleStringLiteral(String value) {
        if (value != null && value.length() > 1) {
            String subStr = value.substring(1, value.length() - 1);
            String escapedQuote = subStr.replace("\\\"", "\"");
            return escapedQuote.replace("\\\\", "\\");
        }

        return null;
    }
}
