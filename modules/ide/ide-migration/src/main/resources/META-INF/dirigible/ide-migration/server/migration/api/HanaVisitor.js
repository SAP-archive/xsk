/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
let Parser = com.sap.xsk.parser.hana.core.HanaParser;
let HanaLexer = com.sap.xsk.parser.hana.core.HanaLexer;
let ByteArrayInputStream = java.io.ByteArrayInputStream;
let ANTLRInputStream = org.antlr.v4.runtime.ANTLRInputStream
let CommonTokenStream = org.antlr.v4.runtime.CommonTokenStream

class HanaVisitor {

    content;
    impl;
    parser;
    fw_super;
    schemaRefs = [];
    viewRefs = [];

    constructor(content) {
        this.content = content;

        let is = new ByteArrayInputStream(this.content.getBytes());
        let inputStream = new ANTLRInputStream(is);
        let hdbtiLexer = new HanaLexer(inputStream);
        let tokenStream = new CommonTokenStream(hdbtiLexer);

        this.parser = new Parser(tokenStream);


        var HanaBaseVisitor = Java.extend(Java.type("com.sap.xsk.parser.hana.core.HanaBaseVisitor"));
        var that = this;
        this.impl = new HanaBaseVisitor({
            visitProc_name: function (ctx) {
                const text = ctx.getText();
                that.addToSchemaRefsIfNeeded(text);
                return that.fw_super.visitChildren(ctx);
            },
            visitTableview_name: function (ctx) {
                const text = ctx.getText();
                that.addToSchemaRefsIfNeeded(text);
                return that.fw_super.visitChildren(ctx);
            },
            visitId_expression: function (ctx) {
                const text = ctx.getText();
                that.addToViewRefsIfNeeded(text);
                return that.fw_super.visitChildren(ctx);
            }
        });
        this.fw_super = Java.super(this.impl);
    }

    addToSchemaRefsIfNeeded(text) {
        if (text.split('"."').length > 1) {
            this.schemaRefs.push(text);
        }
    }

    addToViewRefsIfNeeded(text) {
        if (text.split('/').length > 1) {
            this.viewRefs.push(text.replace(/['"]+/g, ''));
        }
    }

    visit() {
        this.impl.visit(this.parser.unit_statement());
    }

    removeSchemaRefs() {
        for (let i = 0; i < this.schemaRefs.length; i++) {
            let str = this.schemaRefs[i];
            let edited = str.split('"."')[1].replace(/['"]+/g, '');
            this.content = this.replaceAll(this.content, str, edited);
        }
    }

    removeViewRefs() {
        for (let i = 0; i < this.viewRefs.length; i++) {
            let str = this.viewRefs[i];
            let edited = str.split("/")[1].replace(/['"]+/g, '');
            this.content = this.replaceAll(this.content, str, edited);
        }
    }

    replaceAll(str, find, replace) {
        return str.replace(new RegExp(find, 'g'), replace);
    }
}

module.exports = HanaVisitor;

//usage:

// var workspaceManager = require("platform/v4/workspace");
// var workspace = workspaceManager.getWorkspace('workspace');
// let project = workspace.getProject('parser');
// let file = project.getFile('func.hdbtablefunction');
// var content = file.getText();
// let HanaVisitor = require('./HanaVisitor');

// let visitor = new HanaVisitor(content);
// visitor.visit();
// console.log(visitor.viewRefs);
// visitor.removeSchemaRefs();
// visitor.removeViewRefs();
// console.log(visitor.content);