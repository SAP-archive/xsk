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

public class XSKHDBSYNONYMCoreListener extends HdbsynonymBaseListener {

    public static final String TARGET_SCHEMA = "\"schema\"";
    public static final String TARGET_OBJECT = "\"object\"";
    private XSKHDBSYNONYMDefinitionModel model = new XSKHDBSYNONYMDefinitionModel();

    /**
     * Currently the Parser do not take into account if a given property is mandatory.
     * If the property order is misplaced the parser will still parse the values.
     * If more than one value of one property is provided then only the first one is taken.
     */
    @Override
    public void exitHdbsynonymDefinition(HdbsynonymParser.HdbsynonymDefinitionContext ctx) {
        model.setLocation(handleStringLiteral(ctx.location().STRING().getText()));
        ctx.synonymBody().synonymElement().forEach(el -> {
            if (el.synonymSchema() != null) {
                model.setSynonymSchema(handleStringLiteral(el.synonymSchema().STRING().getText()));
            }
            if (el.synonymTarget() != null) {
                HdbsynonymParser.SynonymTargetContext target = el.synonymTarget();
                target.synonymTargetProp().forEach(prop -> {
                    if (prop.children != null) {
                        if (prop.children.get(0).getText().equals(TARGET_SCHEMA)) {
                            model.setTargetSchema(handleStringLiteral(prop.STRING().getText()));
                        } else if (prop.children.get(0).getText().equals(TARGET_OBJECT)) {
                            model.setTargetObject(handleStringLiteral(prop.STRING().getText()));
                        }
                    }
                });
            }
        });
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
