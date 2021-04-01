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
package com.sap.xsk.parser.hdbsequence.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sap.xsk.parser.hdbsequence.core.HdbsequenceBaseVisitor;
import com.sap.xsk.parser.hdbsequence.core.HdbsequenceParser;
import com.sap.xsk.parser.hdbsequence.exceptions.XSKHDBSequenceDuplicatePropertyException;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashSet;
import java.util.List;


public class HdbsequenceVisitor extends HdbsequenceBaseVisitor<JsonElement> {

    private HashSet<String> visitedProperties = new HashSet<>();

    private JsonObject getDefaultParsedObj() {
        JsonObject parsedObj = new JsonObject();
        parsedObj.add("schema", null);
        parsedObj.add("increment_by", null);
        parsedObj.add("start_with", null);
        parsedObj.add("maxvalue", null);
        parsedObj.add("nomaxvalue", null); //boolean
        parsedObj.add("minvalue", null);
        parsedObj.add("nominvalue", null);
        parsedObj.add("cycles", null);
        parsedObj.add("reset_by", null);
        parsedObj.add("public", null);
        parsedObj.add("depends_on_table", null);
        parsedObj.add("depends_on_view", null);
        parsedObj.add("depends_on", null);

        return parsedObj;
    }

    private void checkForPropertyRepetition(String property) throws XSKHDBSequenceDuplicatePropertyException {
        if (!visitedProperties.contains(property)) {
            visitedProperties.add(property);
        } else {

            throw new XSKHDBSequenceDuplicatePropertyException(String.format("Property %s is already declared!", property));
        }
    }

    @Override
    public JsonElement visitHdbsequence(@NotNull HdbsequenceParser.HdbsequenceContext ctx) {
        JsonObject parsedObj = this.getDefaultParsedObj();
        List<ParseTree> ctxList = ctx.children;
        for (ParseTree tree : ctxList) {
            if (tree.getChild(0) instanceof HdbsequenceParser.SchemaContext) {
                parsedObj.add("schema", visitSchema((HdbsequenceParser.SchemaContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.PubliccContext) {
                parsedObj.add("public", visitPublicc((HdbsequenceParser.PubliccContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.MaxvalueContext) {
                parsedObj.add("maxvalue", visitMaxvalue((HdbsequenceParser.MaxvalueContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.Depends_onContext) {
                parsedObj.add("depends_on", visitDepends_on((HdbsequenceParser.Depends_onContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.NomaxvalueContext) {
                parsedObj.add("nomaxvalue", visitNomaxvalue((HdbsequenceParser.NomaxvalueContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.Depends_on_tableContext) {
                parsedObj.add("depends_on_table", visitDepends_on_table((HdbsequenceParser.Depends_on_tableContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.NominvalueContext) {
                parsedObj.add("nominvalue", visitNominvalue((HdbsequenceParser.NominvalueContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.CyclesContext) {
                parsedObj.add("cycles", visitCycles((HdbsequenceParser.CyclesContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.MinvalueContext) {
                parsedObj.add("minvalue", visitMinvalue((HdbsequenceParser.MinvalueContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.Depends_on_viewContext) {
                parsedObj.add("depends_on_view", visitDepends_on_view((HdbsequenceParser.Depends_on_viewContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.Reset_byContext) {
                parsedObj.add("reset_by", visitReset_by((HdbsequenceParser.Reset_byContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.Increment_byContext) {
                parsedObj.add("increment_by", visitIncrement_by((HdbsequenceParser.Increment_byContext) tree.getChild(0)));
            } else if (tree.getChild(0) instanceof HdbsequenceParser.Start_withContext) {
                parsedObj.add("start_with", visitStart_with((HdbsequenceParser.Start_withContext) tree.getChild(0)));
            }
        }
        return parsedObj;
    }


    @Override
    public JsonElement visitSchema(@NotNull HdbsequenceParser.SchemaContext ctx) {
        checkForPropertyRepetition("schema");
        return (ctx != null && ctx.STRING() != null)
                ? new JsonPrimitive(ctx.STRING().getText())
                : null;

    }


    @Override
    public JsonElement visitPublicc(@NotNull HdbsequenceParser.PubliccContext ctx) {
        checkForPropertyRepetition("public");
        return (ctx != null && ctx.BOOLEAN() != null)
                ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText()))
                : new JsonPrimitive(false);
    }


    @Override
    public JsonElement visitMaxvalue(HdbsequenceParser.MaxvalueContext ctx)  {
        checkForPropertyRepetition("maxvalue");
        return (ctx != null && ctx.INT() != null)
                ? new JsonPrimitive(Integer.parseInt(ctx.INT().getText()))
                : null;
    }

    @Override
    public JsonElement visitDepends_on(HdbsequenceParser.Depends_onContext ctx)  {
        checkForPropertyRepetition("depends_on");
        return (ctx != null && ctx.depends_on_list() != null)
                ? visitDepends_on_list(ctx.depends_on_list())
                : null;
    }


    @Override
    public JsonElement visitNomaxvalue(HdbsequenceParser.NomaxvalueContext ctx) {
        checkForPropertyRepetition("nomaxvalue");
        return (ctx != null && ctx.BOOLEAN() != null)
                ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText()))
                : new JsonPrimitive(false);
    }


    @Override
    public JsonElement visitDepends_on_table(HdbsequenceParser.Depends_on_tableContext ctx) throws XSKHDBSequenceDuplicatePropertyException {
        checkForPropertyRepetition("depends_on_table");
        return (ctx != null && ctx.STRING() != null)
                ? new JsonPrimitive(ctx.STRING().getText())
                : null;
    }


    @Override
    public JsonElement visitNominvalue(HdbsequenceParser.NominvalueContext ctx) throws XSKHDBSequenceDuplicatePropertyException {
        checkForPropertyRepetition("nominvalue");
        return (ctx != null && ctx.BOOLEAN() != null)
                ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText()))
                : new JsonPrimitive(false);
    }


    @Override
    public JsonElement visitCycles(HdbsequenceParser.CyclesContext ctx) throws XSKHDBSequenceDuplicatePropertyException {
        checkForPropertyRepetition("cycles");
        return (ctx != null && ctx.BOOLEAN() != null)
                ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText()))
                : null;
    }


    @Override
    public JsonElement visitMinvalue(HdbsequenceParser.MinvalueContext ctx) throws XSKHDBSequenceDuplicatePropertyException {
        checkForPropertyRepetition("minvalue");
        return (ctx != null && ctx.INT() != null)
                ? new JsonPrimitive(Integer.parseInt(ctx.INT().getText()))
                : null;
    }


    @Override
    public JsonElement visitDepends_on_view(HdbsequenceParser.Depends_on_viewContext ctx) throws XSKHDBSequenceDuplicatePropertyException {
        checkForPropertyRepetition("depends_on_view");
        return (ctx != null && ctx.STRING() != null)
                ? new JsonPrimitive(ctx.STRING().getText())
                : null;
    }


    @Override
    public JsonElement visitDepends_on_list(HdbsequenceParser.Depends_on_listContext ctx) {
        JsonArray dependsOnList = new JsonArray();
        if (ctx != null && ctx.STRING() != null) {
            ctx.STRING().forEach(t -> {
                dependsOnList.add(new JsonPrimitive(t.getText()));
            });
        }
        return dependsOnList;
    }


    @Override
    public JsonElement visitReset_by(HdbsequenceParser.Reset_byContext ctx) throws XSKHDBSequenceDuplicatePropertyException {
        checkForPropertyRepetition("reset_by");
        return (ctx != null && ctx.STRING() != null)
                ? new JsonPrimitive(ctx.STRING().getText())
                : null;
    }


    @Override
    public JsonElement visitIncrement_by(HdbsequenceParser.Increment_byContext ctx) throws XSKHDBSequenceDuplicatePropertyException {
        checkForPropertyRepetition("increment_by");
        return (ctx != null && ctx.INT() != null)
                ? new JsonPrimitive(Integer.parseInt(ctx.INT().getText()))
                : new JsonPrimitive(1);
    }


    @Override
    public JsonElement visitStart_with(HdbsequenceParser.Start_withContext ctx) throws XSKHDBSequenceDuplicatePropertyException {
        checkForPropertyRepetition("start_with");
        return (ctx != null && ctx.INT() != null)
                ? new JsonPrimitive(Integer.parseInt(ctx.INT().getText()))
                : new JsonPrimitive(-1);
    }

}
