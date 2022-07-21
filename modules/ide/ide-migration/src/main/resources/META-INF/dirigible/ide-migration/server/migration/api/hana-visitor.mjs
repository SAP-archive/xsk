const Parser = com.sap.xsk.parser.hana.core.HanaParser;
const HanaLexer = com.sap.xsk.parser.hana.core.HanaLexer;
const ByteArrayInputStream = java.io.ByteArrayInputStream;
const ANTLRInputStream = org.antlr.v4.runtime.ANTLRInputStream;
const CommonTokenStream = org.antlr.v4.runtime.CommonTokenStream;

export class HanaVisitor {
    content;
    impl;
    parser;
    fw_super;
    schemaRefs = [];
    viewRefs = [];
    tableRefs = [];
    schemaSeparator = '"."';
    viewSeparator = "/";

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
                that.addToTableRefs(text);
                that.addToSchemaRefsIfNeeded(text);
                return that.fw_super.visitChildren(ctx);
            },
            visitId_expression: function (ctx) {
                const text = ctx.getText();
                that.addToViewRefsIfNeeded(text);
                return that.fw_super.visitChildren(ctx);
            },
        });
        this.fw_super = Java.super(this.impl);
    }

    addToSchemaRefsIfNeeded(text) {
        if (text.split(this.schemaSeparator).length > 1) {
            this.schemaRefs.push(text);
        }
    }

    addToViewRefsIfNeeded(text) {
        if (text.split(this.viewSeparator).length > 1) {
            this.viewRefs.push(text.replace(/['"]+/g, ""));
        }
    }

    addToTableRefs(text) {
        this.tableRefs.push(text.replace(/['"]+/g, ""));
    }

    visit() {
        this.impl.visit(this.parser.unit_statement());
    }

    removeSchemaRefs() {
        for (const schemaRef of this.schemaRefs) {
            let startOfArtifact = schemaRef.indexOf(this.schemaSeparator) + this.schemaSeparator.length - 1;
            let artifact = schemaRef.substring(startOfArtifact, schemaRef.length);
            this.content = this.replaceAll(this.content, schemaRef, artifact);
        }
    }

    removeViewRefs() {
        for (const viewRef of this.viewRefs) {
            var startOfView = viewRef.indexOf(this.viewSeparator) + this.viewSeparator.length;
            let view = viewRef.substring(startOfView, viewRef.length);
            this.content = this.replaceAll(this.content, viewRef, view);
        }
    }

    replaceAll(str, find, replace) {
        return str.replace(new RegExp(find, "g"), replace);
    }
}

//usage:

// import { workspace as workspaceManager } from "@dirigible/platform";
// var workspace = workspaceManager.getWorkspace('workspace');
// let project = workspace.getProject('parser');
// let file = project.getFile('func.hdbtablefunction');
// var content = file.getText();
// import { HanaVisitor } from './hana-visitor';

// let visitor = new HanaVisitor(content);
// visitor.visit();
// console.log(visitor.viewRefs);
// visitor.removeSchemaRefs();
// visitor.removeViewRefs();
// console.log(visitor.content);
