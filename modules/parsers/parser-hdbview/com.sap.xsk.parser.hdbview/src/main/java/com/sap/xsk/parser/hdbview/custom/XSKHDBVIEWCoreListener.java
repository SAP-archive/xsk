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
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class XSKHDBVIEWCoreListener extends HdbviewBaseListener {

    private XSKHDBVIEWDefinitionModel model = new XSKHDBVIEWDefinitionModel();
    private ParseTreeProperty<Object> tokens = new ParseTreeProperty<>();

    @Override
    public void exitHdbviewDefinition(HdbviewParser.HdbviewDefinitionContext ctx){
        List<ParseTree> ctxList = ctx.children;
        for (ParseTree tree : ctxList) {
            if (tree.getChild(0) instanceof HdbviewParser.SchemaPropContext) {
                model.setSchema(handleStringLiteral((String) tokens.get((HdbviewParser.SchemaPropContext) tree.getChild(0))));
            }
            if (tree.getChild(0) instanceof HdbviewParser.QueryPropContext) {
                model.setQuery(handleStringLiteral((String) tokens.get((HdbviewParser.QueryPropContext) tree.getChild(0))));
            }
            if (tree.getChild(0) instanceof HdbviewParser.DependsOnPropContext) {
                model.setDependsOn((ArrayList) tokens.get((HdbviewParser.DependsOnPropContext) tree.getChild(0)));
            }
            if (tree.getChild(0) instanceof HdbviewParser.DependsOnTableContext) {
                model.setDependsOnTable((ArrayList) tokens.get((HdbviewParser.DependsOnTableContext) tree.getChild(0)));
            }
            if (tree.getChild(0) instanceof HdbviewParser.DependsOnViewContext) {
                model.setDependsOnView((ArrayList) tokens.get((HdbviewParser.DependsOnViewContext) tree.getChild(0)));
            }
            if (tree.getChild(0) instanceof HdbviewParser.PublicPropContext) {
                Object publicProp = tokens.get((HdbviewParser.PublicPropContext) tree.getChild(0));
                if (publicProp != null) {
                    model.setPublic((Boolean) publicProp);
                } else {
                    model.setPublic(true);
                }
            }
        }
    }

    @Override
    public void exitQueryProp(HdbviewParser.QueryPropContext ctx) {
        if (ctx != null && ctx.STRING() != null) {
            tokens.put(ctx, ctx.STRING().getText());
        }
    }

    @Override
    public void exitDependsOnProp(HdbviewParser.DependsOnPropContext ctx) {
        if (ctx != null && ctx.STRING() != null) {
            List<String> dependsOnPropList = ctx.STRING().stream().map(el -> handleStringLiteral(el.getText())
            ).collect(Collectors.toList());
            tokens.put(ctx, dependsOnPropList);
        }
    }

    @Override
    public void exitDependsOnView(HdbviewParser.DependsOnViewContext ctx) {
        if (ctx != null && ctx.STRING() != null) {
            List<String> dependsOnViewPropList = ctx.STRING().stream().map(el -> handleStringLiteral(el.getText())
            ).collect(Collectors.toList());
            tokens.put(ctx, dependsOnViewPropList);
        }
    }

    @Override
    public void exitDependsOnTable(HdbviewParser.DependsOnTableContext ctx) {
        if (ctx != null && ctx.STRING() != null) {
            List<String> dependsOnTablePropList = ctx.STRING().stream().map(el -> handleStringLiteral(el.getText())
            ).collect(Collectors.toList());
            tokens.put(ctx, dependsOnTablePropList);
        }
    }

    @Override
    public void exitSchemaProp(HdbviewParser.SchemaPropContext ctx) {
        if (ctx != null && ctx.STRING() != null) {
            tokens.put(ctx, ctx.STRING().getText());
        }
    }

    @Override
    public void exitPublicProp(HdbviewParser.PublicPropContext ctx) {
        if (ctx != null && ctx.BOOLEAN() != null) {
            tokens.put(ctx, Boolean.valueOf(ctx.BOOLEAN().getText()));
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

    public XSKHDBVIEWDefinitionModel getModel() {
        return model;
    }
}
