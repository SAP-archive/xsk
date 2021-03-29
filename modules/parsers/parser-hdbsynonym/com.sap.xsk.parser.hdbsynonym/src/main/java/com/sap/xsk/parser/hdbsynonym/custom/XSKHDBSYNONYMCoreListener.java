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
package com.sap.xsk.parser.hdbsynonym.custom;

import com.sap.xsk.parser.hdbsynonym.core.HdbsynonymBaseListener;
import com.sap.xsk.parser.hdbsynonym.core.HdbsynonymParser;
import com.sap.xsk.parser.hdbsynonym.models.XSKHDBSYNONYMDefinitionModel;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class XSKHDBSYNONYMCoreListener extends HdbsynonymBaseListener {
    private XSKHDBSYNONYMDefinitionModel model = new XSKHDBSYNONYMDefinitionModel();
    private ParseTreeProperty<Object> tokens = new ParseTreeProperty<>();
    public static final String TARGET_SCHEMA = "\"schema\"";
    public static final String TARGET_OBJECT = "\"object\"";

    /**
     * Currently the Parser do not take into account if a given property is mandatory.
     * If the property order is misplaced the parser will still parse the values.
     * If more than one value of one property is provided then only the first one is taken.
     */
    @Override
    public void exitHdbsynonymDefinition(HdbsynonymParser.HdbsynonymDefinitionContext ctx) {
        model.setLocation(handleStringLiteral((String) tokens.get(ctx.location())));
        if (ctx.synonymBody().synonymSchema() != null && ctx.synonymBody().synonymSchema().size() > 0) {
            model.setSynonymSchema(handleStringLiteral((String) tokens.get(ctx.synonymBody().synonymSchema().get(0))));
        }

        if (!(ctx.synonymBody().synonymTarget() == null || ctx.synonymBody().synonymTarget().isEmpty())) {
            HdbsynonymParser.SynonymTargetContext target = ctx.synonymBody().synonymTarget().get(0);

            target.synonymTargetProp().forEach(prop -> {
                if (prop.children != null) {
                    if (prop.children.get(0).getText().equals(TARGET_SCHEMA)) {
                        model.setTargetSchema(handleStringLiteral((String) tokens.get(prop)));
                    } else if (prop.children.get(0).getText().equals(TARGET_OBJECT)) {
                        model.setTargetObject(handleStringLiteral((String) tokens.get(prop)));
                    }
                }
            });
        }
    }

    @Override
    public void exitLocation(HdbsynonymParser.LocationContext ctx) {
        if (ctx != null && ctx.STRING() != null) {
            tokens.put(ctx, ctx.STRING().getText());
        }
    }

    @Override
    public void exitSynonymTargetProp(HdbsynonymParser.SynonymTargetPropContext ctx) {
        if (ctx != null && ctx.STRING() != null) {
            tokens.put(ctx, ctx.STRING().getText());
        }
    }

    @Override
    public void exitSynonymSchema(HdbsynonymParser.SynonymSchemaContext ctx) {
        if (ctx != null && ctx.STRING() != null) {
            tokens.put(ctx, ctx.STRING().getText());
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

    public XSKHDBSYNONYMDefinitionModel getModel() {
        return model;
    }
}
