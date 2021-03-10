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
package java.com.sap.xsk.parser.hdbsequence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.sap.xsk.parser.hdbsequence.core.HdbsequenceBaseVisitor;
import com.sap.xsk.parser.hdbsequence.core.HdbsequenceLexer;
import com.sap.xsk.parser.hdbsequence.core.HdbsequenceParser;
import com.sap.xsk.parser.hdbsequence.custom.CustomDeserializers;
import com.sap.xsk.parser.hdbsequence.custom.HdbsequenceVisitor;
import com.sap.xsk.parser.hdbsequence.models.XSKHDBSEQUENCEModel;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
        public static void main(String[] args) throws IOException {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) {
            is = new FileInputStream(inputFile);
        }

        ANTLRInputStream inputStream = new ANTLRInputStream(is);
        HdbsequenceLexer lexer = new HdbsequenceLexer(inputStream);


        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        HdbsequenceParser parser = new HdbsequenceParser((tokenStream));



        ParseTree parseTree = parser.hdbsequence();

        HdbsequenceBaseVisitor<JsonElement> visitor = new HdbsequenceVisitor();

        JsonElement parsedResult = visitor.visit(parseTree);

        Gson gson = new GsonBuilder()
                                .registerTypeAdapter(XSKHDBSEQUENCEModel.class, new CustomDeserializers.XSKHDBSEQUENCEModelAdapter())
                                .create();
        XSKHDBSEQUENCEModel model = gson.fromJson(parsedResult, XSKHDBSEQUENCEModel.class);

         System.out.println(model);

    }
}
