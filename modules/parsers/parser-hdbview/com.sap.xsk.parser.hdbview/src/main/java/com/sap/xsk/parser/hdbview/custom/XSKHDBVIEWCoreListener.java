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
package com.sap.xsk.parser.hdbview.custom;

import com.sap.xsk.parser.hdbview.core.HdbviewBaseListener;
import com.sap.xsk.parser.hdbview.core.HdbviewParser;
import com.sap.xsk.parser.hdbview.models.XSKHDBVIEWDefinitionModel;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.List;
import java.util.stream.Collectors;

public class XSKHDBVIEWCoreListener extends HdbviewBaseListener {

    private XSKHDBVIEWDefinitionModel model = new XSKHDBVIEWDefinitionModel();
    private ParseTreeProperty<Object> tokens = new ParseTreeProperty<>();

    @Override
    public void exitHdbviewDefinition(HdbviewParser.HdbviewDefinitionContext ctx) {
        model.setSchema(handleStringLiteral((String) tokens.get(ctx.schemaProp())));
        model.setPublicProp((Boolean) tokens.get(ctx.publicProp()));
        model.setQuery(handleStringLiteral((String) tokens.get(ctx.queryProp())));
        model.setDependsOn((List<String>) tokens.get(ctx.dependsOnProp()));
        model.setDependsOnTable((List<String>) tokens.get(ctx.dependsOnTable()));
        model.setDependsOnView((List<String>) tokens.get(ctx.dependsOnView()));
    }

    @Override
    public void exitQueryProp(HdbviewParser.QueryPropContext ctx) {
        tokens.put(ctx, ctx.STRING().getText());
    }

    @Override
    public void exitDependsOnProp(HdbviewParser.DependsOnPropContext ctx) {
        List<String> dependsOnPropList = ctx.STRING().stream().map(el -> handleStringLiteral(el.getText())
        ).collect(Collectors.toList());
        tokens.put(ctx, dependsOnPropList);
    }

    @Override
    public void exitDependsOnView(HdbviewParser.DependsOnViewContext ctx) {
        List<String> dependsOnViewPropList = ctx.STRING().stream().map(el -> handleStringLiteral(el.getText())
        ).collect(Collectors.toList());
        tokens.put(ctx, dependsOnViewPropList);
    }

    @Override
    public void exitDependsOnTable(HdbviewParser.DependsOnTableContext ctx) {
        List<String> dependsOnTablePropList = ctx.STRING().stream().map(el -> handleStringLiteral(el.getText())
        ).collect(Collectors.toList());
        tokens.put(ctx, dependsOnTablePropList);
    }

    @Override
    public void exitSchemaProp(HdbviewParser.SchemaPropContext ctx) {
        tokens.put(ctx, ctx.STRING().getText());
    }

    @Override
    public void exitPublicProp(HdbviewParser.PublicPropContext ctx) {
        tokens.put(ctx, Boolean.valueOf(ctx.BOOLEAN().getText()));
    }

    private String handleStringLiteral(String value) {
        if (value != null && value.length() > 1) {
            String subStr = value.substring(1, value.length() - 1);
            String escapedQuote = subStr.replace("\\\"", "\"");
            return escapedQuote.replace("\\\\", "\\");
        }

        return null;
    }

    public XSKHDBVIEWDefinitionModel getModel() {
        return model;
    }
}
