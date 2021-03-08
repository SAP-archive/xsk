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



import java.util.stream.Collectors;

public class XSKHDBTABLECoreVisitor extends HdbtableBaseVisitor<JsonElement> {
    private JsonObject hdbtableObject = new JsonObject();
    private JsonArray tableIndexes = new JsonArray();
    private JsonArray tableColumns = new JsonArray();


    @Override
    public JsonElement visitTableColumnsProp(HdbtableParser.TableColumnsPropContext ctx) {
        tableColumns.add(ctx.columnsObject().stream().map(terminalNode -> terminalNode.getText()).collect(Collectors.joining(",")));
        return tableColumns;
    }

    @Override
    public JsonElement visitIndexAssignIndexType(HdbtableParser.IndexAssignIndexTypeContext ctx) {
        return new JsonPrimitive(ctx.INDEXTYPE().getText());
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

        return hdbtableObject;
    }

    @Override
    public JsonElement visitIndexAssignUnique(HdbtableParser.IndexAssignUniqueContext ctx) {
        return new JsonPrimitive(ctx.BOOLEAN().getText());
    }

    @Override
    public JsonElement visitTableIndexesProp(HdbtableParser.TableIndexesPropContext ctx) {
        tableIndexes.add(ctx.indexesObject().stream().map(terminalNode -> terminalNode.getText()).collect(Collectors.joining(",")));
        return tableIndexes;
    }

    @Override
    public JsonElement visitColumnAssignDefaultValue(HdbtableParser.ColumnAssignDefaultValueContext ctx) {
        return new JsonPrimitive(ctx.STRING().getText());
    }

    @Override
    public JsonElement visitColumnAssignName(HdbtableParser.ColumnAssignNameContext ctx) {
        return new JsonPrimitive(ctx.STRING().getText());
    }

    @Override
    public JsonElement visitIndexAssignIndexColumns(HdbtableParser.IndexAssignIndexColumnsContext ctx) {
        JsonArray indexColumnsArray = new JsonArray();
        indexColumnsArray.add(visitIndexColumnsArray(ctx.indexColumnsArray()));
        return indexColumnsArray;
    }

    @Override
    public JsonElement visitColumnAssignNullable(HdbtableParser.ColumnAssignNullableContext ctx) {
        return new JsonPrimitive(ctx.BOOLEAN().getText());
    }

    @Override
    public JsonElement visitIndexColumnsArray(HdbtableParser.IndexColumnsArrayContext ctx) {
        JsonPrimitive indexColumnsArray = new JsonPrimitive(ctx.STRING().stream().map(terminalNode -> terminalNode.getText()).collect(Collectors.joining(",")));
        return indexColumnsArray;
    }

    @Override
    public JsonElement visitIndexAssignOrder(HdbtableParser.IndexAssignOrderContext ctx) {
        return new JsonPrimitive(ctx.ORDER().getText());
    }

    @Override
    public JsonElement visitTableTypeProp(HdbtableParser.TableTypePropContext ctx) {
        return new JsonPrimitive(ctx.TABLETYPE().getText());
    }

    @Override
    public JsonElement visitColumnAssignLength(HdbtableParser.ColumnAssignLengthContext ctx) {
        return new JsonPrimitive(ctx.INT().getText());
    }

    @Override
    public JsonElement visitTablePrimaryKeyProp(HdbtableParser.TablePrimaryKeyPropContext ctx) {
        JsonPrimitive primaryKeysArray = new JsonPrimitive(ctx.STRING().stream().map(terminalNode -> terminalNode.getText()).collect(Collectors.joining(",")));
        return primaryKeysArray;
    }

    @Override
    public JsonElement visitColumnAssignPrecision(HdbtableParser.ColumnAssignPrecisionContext ctx) {
        return new JsonPrimitive(ctx.INT().getText());
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
        columnsObject.add("length", visitColumnAssignLength(ctx.columnAssignLength()));
        columnsObject.add("precision", visitColumnAssignPrecision(ctx.columnAssignPrecision()));
        columnsObject.add("comment", visitColumnAssignComment(ctx.columnAssignComment()));
        columnsObject.add("scale", visitColumnAssignScale(ctx.columnAssignScale()));
        columnsObject.add("defaultValue", visitColumnAssignDefaultValue(ctx.columnAssignDefaultValue()));

        return columnsObject;
    }

    @Override
    public JsonElement visitColumnAssignScale(HdbtableParser.ColumnAssignScaleContext ctx) {
        return new JsonPrimitive(ctx.INT().getText());
    }

    @Override
    public JsonElement visitSchemaNameProp(HdbtableParser.SchemaNamePropContext ctx) {
        return new JsonPrimitive(ctx.STRING().getText());
    }

    @Override
    public JsonElement visitColumnAssignSQLType(HdbtableParser.ColumnAssignSQLTypeContext ctx) {
        return new JsonPrimitive(ctx.SQLTYPES().getText());
    }

    @Override
    public JsonElement visitColumnAssignComment(HdbtableParser.ColumnAssignCommentContext ctx) {
        return new JsonPrimitive(ctx.STRING().getText());
    }

    @Override
    public JsonElement visitIndexAssignName(HdbtableParser.IndexAssignNameContext ctx) {
        return new JsonPrimitive(ctx.STRING().getText());
    }

    @Override
    public JsonElement visitLoggingTypeProp(HdbtableParser.LoggingTypePropContext ctx) {
        return new JsonPrimitive(ctx.TABLELOGGINGTYPE().getText());
    }

    @Override
    public JsonElement visitTemporaryProp(HdbtableParser.TemporaryPropContext ctx) {
        return new JsonPrimitive(ctx.BOOLEAN().getText());
    }

    @Override
    public JsonElement visitPublicProp(HdbtableParser.PublicPropContext ctx) {
        return new JsonPrimitive(ctx.BOOLEAN().getText());
    }

    @Override
    public JsonElement visitDescriptionProp(HdbtableParser.DescriptionPropContext ctx) {
        return new JsonPrimitive(ctx.STRING().getText());
    }

    public JsonElement getHdbtableDefinitionObject() {
        return hdbtableObject;
    }

    public JsonElement getIndexesObject() {
        return tableIndexes;
    }

    public JsonElement getColumnsObject() {
        return tableColumns;
    }
}
