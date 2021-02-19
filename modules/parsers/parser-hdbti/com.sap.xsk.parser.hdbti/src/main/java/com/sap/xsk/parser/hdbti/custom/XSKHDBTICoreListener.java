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
package com.sap.xsk.parser.hdbti.custom;

import com.sap.xsk.parser.hdbti.core.HdbtiBaseListener;
import com.sap.xsk.parser.hdbti.core.HdbtiParser;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportConfigModel;
import com.sap.xsk.parser.hdbti.models.XSKHDBTIImportModel;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.List;

public class XSKHDBTICoreListener extends HdbtiBaseListener {
    private ParseTreeProperty<String> tokens = new ParseTreeProperty<>();
    private List<XSKHDBTIImportConfigModel.Pair> pairs;
    private XSKHDBTIImportConfigModel configModel;
    private XSKHDBTIImportModel importModel = new XSKHDBTIImportModel();

    @Override
    public void enterObjConfig(HdbtiParser.ObjConfigContext ctx) {
        configModel = new XSKHDBTIImportConfigModel();
    }

    @Override
    public void exitObjConfig(HdbtiParser.ObjConfigContext ctx) {
        String tableName = tokens.get(ctx.assingTable());
        String schemaName = tokens.get(ctx.assignSchema());
        String fileName = tokens.get(ctx.assignFile());
        String header = tokens.get(ctx.assignHeader());
        String useHeaderNames = tokens.get(ctx.assignUseHeaderNames());
        String delimField = tokens.get(ctx.assignDelimField());
        String delimEnclosing = tokens.get(ctx.assignDelimEnclosing());
        String distinguishEmptyFromNull = tokens.get(ctx.assignDistinguishEmptyFromNull());

        configModel.setTableName(handleStringLiteral(tableName));
        configModel.setSchemaName(handleStringLiteral(schemaName));
        configModel.setFileName(handleStringLiteral(fileName));
        configModel.setHeader(Boolean.parseBoolean(header));
        configModel.setUseHeaderNames(Boolean.parseBoolean(useHeaderNames));
        configModel.setDelimField(handleStringLiteral(delimField));
        configModel.setDelimEnclosing(handleStringLiteral(delimEnclosing));
        configModel.setDistinguishEmptyFromNull(Boolean.parseBoolean(distinguishEmptyFromNull));
        configModel.setKeys(pairs);

        importModel.getConfigModels().add(configModel);
    }

    @Override
    public void exitAssingTable(HdbtiParser.AssingTableContext ctx) {
        tokens.put(ctx, handleStringLiteral(ctx.STRING().getText()));
    }

    @Override
    public void exitAssignFile(HdbtiParser.AssignFileContext ctx) {
        tokens.put(ctx, ctx.STRING().getText());
    }

    @Override
    public void exitAssignSchema(HdbtiParser.AssignSchemaContext ctx) {
        tokens.put(ctx, ctx.STRING().getText());
    }

    @Override
    public void exitPair(HdbtiParser.PairContext ctx) {
        XSKHDBTIImportConfigModel.Pair pair = new XSKHDBTIImportConfigModel.Pair(tokens.get(ctx.pairKey()), tokens.get(ctx.pairValue()));
        pairs.add(pair);
    }

    @Override
    public void enterKeyArr(HdbtiParser.KeyArrContext ctx) {

        pairs = new ArrayList<>();
    }

    @Override
    public void exitPairKey(HdbtiParser.PairKeyContext ctx) {

        tokens.put(ctx, handleStringLiteral(ctx.STRING().getText()));
    }

    @Override
    public void exitPairValue(HdbtiParser.PairValueContext ctx) {

        tokens.put(ctx, handleStringLiteral(ctx.STRING().getText()));
    }

    @Override
    public void exitAssignHeader(HdbtiParser.AssignHeaderContext ctx) {
        tokens.put(ctx, ctx.BOOLEAN().getText());
    }

    @Override
    public void exitAssignUseHeaderNames(HdbtiParser.AssignUseHeaderNamesContext ctx) {
        tokens.put(ctx, ctx.BOOLEAN().getText());
    }

    @Override
    public void exitAssignDelimField(HdbtiParser.AssignDelimFieldContext ctx) {
        tokens.put(ctx, ctx.STRING().getText());
    }

    @Override
    public void exitAssignDelimEnclosing(HdbtiParser.AssignDelimEnclosingContext ctx) {
        tokens.put(ctx, ctx.STRING().getText());
    }

    @Override
    public void exitAssignDistinguishEmptyFromNull(HdbtiParser.AssignDistinguishEmptyFromNullContext ctx) {
        tokens.put(ctx, ctx.BOOLEAN().getText());
    }

    public XSKHDBTIImportModel getImportModel() {

        return importModel;
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
