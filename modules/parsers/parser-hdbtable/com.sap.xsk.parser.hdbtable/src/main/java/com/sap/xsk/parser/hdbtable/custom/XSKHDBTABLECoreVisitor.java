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
package com.sap.xsk.parser.hdbtable.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sap.xsk.parser.hdbtable.core.HdbtableBaseVisitor;
import com.sap.xsk.parser.hdbtable.core.HdbtableParser;
import com.sap.xsk.parser.hdbtable.custom.XSKHDBTABLECoreVisitor;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEColumnsModel;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEDefinitionModel;
import com.sap.xsk.parser.hdbtable.model.XSKHDBTABLEIndexesModel;


import java.util.stream.Collectors;

public class XSKHDBTABLECoreVisitor extends HdbtableBaseVisitor<JsonElement> {
    private JsonObject hdbtableObject = new JsonObject();

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
    public JsonElement visitIndexAssignIndexType(HdbtableParser.IndexAssignIndexTypeContext ctx) {
        return (ctx!=null && ctx.INDEXTYPE()!=null)? new JsonPrimitive(ctx.INDEXTYPE().getText()) : null;
    }

    @Override
    public JsonElement visitHdbtableDefinition(HdbtableParser.HdbtableDefinitionContext ctx) {
        hdbtableObject.add("schemaName", visitSchemaNameProp(ctx.schemaNameProp()));
        hdbtableObject.add("tableType", visitTableTypeProp(ctx.tableTypeProp()));
        hdbtableObject.add("columns", visitTableColumnsProp(ctx.tableColumnsProp()));
        hdbtableObject.add("indexes", visitTableIndexesProp(ctx.tableIndexesProp()));
        hdbtableObject.add("pkcolumns", visitTablePrimaryKeyProp(ctx.tablePrimaryKeyProp()));
        hdbtableObject.add("description", visitDescriptionProp(ctx.descriptionProp()));
        hdbtableObject.add("temporary", visitTemporaryProp(ctx.temporaryProp()));
        hdbtableObject.add("publicProp", visitPublicProp(ctx.publicProp()));
        hdbtableObject.add("loggingType", visitLoggingTypeProp(ctx.loggingTypeProp()));
        hdbtableObject.add("indexType", visitTablePrimaryKeyIndexTypeProp(ctx.tablePrimaryKeyIndexTypeProp()));

        return hdbtableObject;
    }

    @Override
    public JsonElement visitIndexAssignUnique(HdbtableParser.IndexAssignUniqueContext ctx) {
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
        return (ctx!=null && ctx.STRING()!=null)?new JsonPrimitive(handleStringLiteral(ctx.STRING().getText())):null;
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
    public JsonElement visitColumnAssignNullable(HdbtableParser.ColumnAssignNullableContext ctx) {
        return (ctx != null && ctx.BOOLEAN() !=null) ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText())) : null;
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
    public JsonElement visitIndexAssignOrder(HdbtableParser.IndexAssignOrderContext ctx) {
        return (ctx!=null && ctx.ORDER()!=null)?new JsonPrimitive(ctx.ORDER().getText()):null;
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
    public JsonElement visitTablePrimaryKeyProp(HdbtableParser.TablePrimaryKeyPropContext ctx) {
        return (ctx!=null && ctx.tablePrimaryKeyColumnsProp()!=null)? visitTablePrimaryKeyColumnsProp(ctx.tablePrimaryKeyColumnsProp()): new JsonArray();
    }

    @Override
    public JsonElement visitColumnAssignPrecision(HdbtableParser.ColumnAssignPrecisionContext ctx) {
        return (ctx!=null && ctx.INT()!=null)?new JsonPrimitive(Integer.parseInt(ctx.INT().getText())):null;
    }

    @Override
    public JsonElement visitIndexesObject(HdbtableParser.IndexesObjectContext ctx) {
        JsonObject indexesObject = new JsonObject();
        indexesObject.add("name", visitIndexAssignName(ctx.indexAssignName()));
        indexesObject.add("unique", visitIndexAssignUnique(ctx.indexAssignUnique()));
        indexesObject.add("order", visitIndexAssignOrder(ctx.indexAssignOrder()));
        indexesObject.add("indexColumns", visitIndexAssignIndexColumns(ctx.indexAssignIndexColumns()));
        indexesObject.add("indexType", visitIndexAssignIndexType(ctx.indexAssignIndexType()));

        return indexesObject;
    }

    @Override
    public JsonElement visitColumnsObject(HdbtableParser.ColumnsObjectContext ctx) {
        JsonObject columnsObject = new JsonObject();
        columnsObject.add("name", visitColumnAssignName(ctx.columnAssignName()));
        columnsObject.add("sqlType", visitColumnAssignSQLType(ctx.columnAssignSQLType()));
        columnsObject.add("nullable", visitColumnAssignNullable(ctx.columnAssignNullable()));
        columnsObject.add("unique", visitColumnAssignUnique(ctx.columnAssignUnique()));
        columnsObject.add("length", visitColumnAssignLength(ctx.columnAssignLength()));
        columnsObject.add("precision", visitColumnAssignPrecision(ctx.columnAssignPrecision()));
        columnsObject.add("comment", visitColumnAssignComment(ctx.columnAssignComment()));
        columnsObject.add("scale", visitColumnAssignScale(ctx.columnAssignScale()));
        columnsObject.add("defaultValue", visitColumnAssignDefaultValue(ctx.columnAssignDefaultValue()));

        return columnsObject;
    }

    @Override
    public JsonElement visitColumnAssignScale(HdbtableParser.ColumnAssignScaleContext ctx) {
        return (ctx!=null && ctx.INT()!=null)?new JsonPrimitive(Integer.parseInt(ctx.INT().getText())):null;
    }

    @Override
    public JsonElement visitSchemaNameProp(HdbtableParser.SchemaNamePropContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)?new JsonPrimitive(handleStringLiteral(ctx.STRING().getText())):null;
    }

    @Override
    public JsonElement visitColumnAssignSQLType(HdbtableParser.ColumnAssignSQLTypeContext ctx) {
        return (ctx!=null && ctx.SQLTYPES()!=null)?new JsonPrimitive(ctx.SQLTYPES().getText()):null;
    }

    @Override
    public JsonElement visitColumnAssignComment(HdbtableParser.ColumnAssignCommentContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)?new JsonPrimitive(handleStringLiteral(ctx.STRING().getText())):null;
    }

    @Override
    public JsonElement visitIndexAssignName(HdbtableParser.IndexAssignNameContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)?new JsonPrimitive(handleStringLiteral(ctx.STRING().getText())):null;
    }

    @Override
    public JsonElement visitLoggingTypeProp(HdbtableParser.LoggingTypePropContext ctx) {
        return (ctx!=null && ctx.TABLELOGGINGTYPE()!=null)?new JsonPrimitive(ctx.TABLELOGGINGTYPE().getText()):null;
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
    public JsonElement visitDescriptionProp(HdbtableParser.DescriptionPropContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)?new JsonPrimitive(handleStringLiteral(ctx.STRING().getText())):null;
    }

    @Override
    public JsonElement visitTablePrimaryKeyIndexTypeProp(HdbtableParser.TablePrimaryKeyIndexTypePropContext ctx) {
        return (ctx!=null && ctx.INDEXTYPE()!=null)?new JsonPrimitive(ctx.INDEXTYPE().getText()):null;
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
    public JsonElement visitColumnAssignUnique(HdbtableParser.ColumnAssignUniqueContext ctx) {
        return (ctx != null && ctx.BOOLEAN() !=null) ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText())) : null;
    }

    public JsonElement getHdbtableDefinitionObject() {
        return hdbtableObject;
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
