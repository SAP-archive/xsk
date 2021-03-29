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


public class HdbsequenceVisitor extends HdbsequenceBaseVisitor<JsonElement> {

    @Override public JsonElement visitHdbsequence( HdbsequenceParser.HdbsequenceContext ctx) {
        JsonObject parsedObj = new JsonObject();
        parsedObj.add("schema",visitSchema(ctx.schema()));
        parsedObj.add("increment_by" , visitIncrement_by(ctx.increment_by()));
        parsedObj.add("start_with" , visitStart_with(ctx.start_with()));
        parsedObj.add("maxvalue" , visitMaxvalue(ctx.maxvalue()));
        parsedObj.add("nomaxvalue" , visitNomaxvalue(ctx.nomaxvalue())); //boolean
        parsedObj.add("minvalue" , visitMinvalue(ctx.minvalue()));
        parsedObj.add("nominvalue" , visitNominvalue(ctx.nominvalue()));
        parsedObj.add("cycles" , visitCycles(ctx.cycles()));
        parsedObj.add("reset_by" , visitReset_by(ctx.reset_by()));
        parsedObj.add("public" , visitPublicc(ctx.publicc()));
        parsedObj.add("depends_on_table" , visitDepends_on_table(ctx.depends_on_table()));
        parsedObj.add("depends_on_view" , visitDepends_on_view(ctx.depends_on_view()));
        parsedObj.add("depends_on", visitDepends_on(ctx.depends_on()));

        return parsedObj;
    }


    @Override public JsonElement visitSchema(HdbsequenceParser.SchemaContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)
                ? new JsonPrimitive(ctx.STRING().getText())
                : null;
    }


    @Override public JsonElement visitPublicc(HdbsequenceParser.PubliccContext ctx) {
        return (ctx!=null && ctx.BOOLEAN()!=null)
                ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText()))
                : new JsonPrimitive(false);
    }


    @Override public JsonElement visitMaxvalue(HdbsequenceParser.MaxvalueContext ctx) {
        return (ctx!=null &&ctx.INT()!=null)
                ? new JsonPrimitive(Integer.parseInt(ctx.INT().getText()))
                : null;
    }


    @Override public JsonElement visitDepends_on(HdbsequenceParser.Depends_onContext ctx) {
        return (ctx!=null && ctx.depends_on_list()!=null)
                ? visitDepends_on_list(ctx.depends_on_list())
                : null;
    }


    @Override public JsonElement visitNomaxvalue(HdbsequenceParser.NomaxvalueContext ctx) {
        return (ctx!=null && ctx.BOOLEAN()!=null)
                ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText()))
                : new JsonPrimitive(false);
    }


    @Override public JsonElement visitDepends_on_table(HdbsequenceParser.Depends_on_tableContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)
                ? new JsonPrimitive(ctx.STRING().getText())
                : null;
    }


    @Override public JsonElement visitNominvalue(HdbsequenceParser.NominvalueContext ctx) {
        return (ctx!=null && ctx.BOOLEAN()!=null)
                ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText()))
                : new JsonPrimitive(false);
    }


    @Override public JsonElement visitCycles(HdbsequenceParser.CyclesContext ctx) {
        return (ctx!=null && ctx.BOOLEAN()!=null)
                ? new JsonPrimitive(Boolean.parseBoolean(ctx.BOOLEAN().getText()))
                : null;
    }


    @Override public JsonElement visitMinvalue(HdbsequenceParser.MinvalueContext ctx) {
        return (ctx!=null && ctx.INT()!=null)
                ? new JsonPrimitive(Integer.parseInt(ctx.INT().getText()))
                : null;
    }


    @Override public JsonElement visitDepends_on_view(HdbsequenceParser.Depends_on_viewContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)
                ? new JsonPrimitive(ctx.STRING().getText())
                : null;
    }


    @Override public JsonElement visitReset_by(HdbsequenceParser.Reset_byContext ctx) {
        return (ctx!=null && ctx.STRING()!=null)
                ? new JsonPrimitive(ctx.STRING().getText())
                : null;
    }


    @Override public JsonElement visitIncrement_by(HdbsequenceParser.Increment_byContext ctx) {
        return (ctx!=null && ctx.INT()!=null)
                ? new JsonPrimitive(Integer.parseInt(ctx.INT().getText()))
                : new JsonPrimitive(1);
    }


    @Override public JsonElement visitStart_with(HdbsequenceParser.Start_withContext ctx) {
        return (ctx!=null && ctx.INT()!=null)
                ? new JsonPrimitive(Integer.parseInt(ctx.INT().getText() ))
                : new JsonPrimitive(-1);
    }

    @Override public JsonElement visitDepends_on_list(HdbsequenceParser.Depends_on_listContext ctx) {
        JsonArray dependsOnList = new JsonArray();
        if(ctx!=null && ctx.STRING()!=null){
            ctx.STRING().forEach( t -> {
                dependsOnList.add(new JsonPrimitive(t.getText()));
            });
        }
        return dependsOnList;
    }

}
