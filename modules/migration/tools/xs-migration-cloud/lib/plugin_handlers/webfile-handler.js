// --------------------------------------------------------------------------
//
// Handler for detecting migration relevant items in Web files
//
// --------------------------------------------------------------------------

var utils = require('./plugin-utils');
var path = require('path');
var mc = require('../message-categories');
var msgs = require('../messages');
var xml2js = require('xml2js');
var async = require('async');
var escodegen = require('escodegen');
var esprima = require('esprima');
var createHtmlDom  = require('htmldom');

var ParserError = require('../parser-error');
var javascriptHandler = require('./js-handler');

const GENERATOR_OPTIONS = {
    parse: esprima.parse,
    comment: true
};

var SEARCH_STRINGS = [
    {
        search: /jQuery\.ajax/,
        message: msgs.getMessage(mc.JS, 1)
    }
];

var jsHandlingFunction;

function replaceUI5src(content) {
    var tmp_content = content.replace(/<![\s\S]*?>/g, "");
    var finds = tmp_content.match(/<script[\s\S]*?<\/script>/g);
    if (!finds) {
        return content;
    }
    var foundUI5 = [];
    async.each(finds, function (find, callback) {
        xml2js.parseString(find, function (err, result) {
            if (result && result.script && result.script["$"] && result.script["$"].id && result.script["$"].id.trim() === "sap-ui-bootstrap" && result.script["$"].src) {
                foundUI5.push({
                    id: result.script["$"].id,
                    src: result.script["$"].src
                });
            }
            callback(null);
        });
    }, function (err) {});
    for (var i = 0; i < foundUI5.length; i++) {
        var reg = new RegExp(foundUI5[i].src, "g");
        content = content.replace(reg, "https://sapui5.hana.ondemand.com/resources/sap-ui-core.js");
    }
    return content;
}


function parseHtml(content, htmlFile, globalContext) {

    var html = createHtmlDom (content);

    var scriptTags = html('script');
    var scriptTagsLength = scriptTags.length;
    var scriptAttributes = [];

    for (let i = 0; i < scriptTagsLength; i++) {
        let scriptTag = scriptTags[i];

        if(scriptTag.textContent && scriptTag.textContent.length > 0){
            let newJsContent = jsHandlingFunction(scriptTag.textContent, htmlFile, globalContext);
            if(newJsContent != null){
                let astWithComments = escodegen.attachComments(newJsContent, newJsContent.comments, newJsContent.tokens);
                if(astWithComments.body.length > 0 || astWithComments.comments.length > 0 || astWithComments.tokens.length > 0) {
                    let newFileContent = '\n' + escodegen.generate(astWithComments, GENERATOR_OPTIONS) + '\n';
                    scriptTag.textContent = newFileContent;
                }
            }
        }
        // if(scriptTag.children && scriptTag.children.length > 0){
        //     let newJsContent = jsHandlingFunction(scriptTag.children[0].value, htmlFile, globalContext);
        //     if(newJsContent != null){
        //         let astWithComments = escodegen.attachComments(newJsContent, newJsContent.comments, newJsContent.tokens);
        //         if(astWithComments.body.length > 0 || astWithComments.comments.length > 0 || astWithComments.tokens.length > 0) {
        //             let newFileContent = '\n' + escodegen.generate(astWithComments, GENERATOR_OPTIONS) + '\n';
        //             scriptTag.children[0].value = newFileContent;
        //         }
        //     }
        // }
        let properties = Object.getOwnPropertyNames(scriptTag.attributes);
        properties.forEach( (propertyName) => {
            let propertyValue = scriptTag.attributes[propertyName];
            propertyValue = propertyValue.replace(/"/g, "&quot;");
            propertyValue = propertyValue.replace(/'/g, "&quot;");
            scriptTag.attributes[propertyName] = propertyValue;
            scriptAttributes.push(propertyValue);

        });

    }

    var newHtml = html.html();
    scriptAttributes.forEach( (attribute) => {

        let escapedAttribute = attribute.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&');

        newHtml = newHtml.replace(new RegExp("\"" + escapedAttribute + "\"", 'g'), "'" + attribute.replace(/&quot;/g, "\"") + "'");
    });

    return newHtml;
}



var collectTextbundleReferences = function (jsContent, htmlFile, globalContext) {
    javascriptHandler._collectTextbundleReferences(jsContent, htmlFile, globalContext);
};


var transformTextbundleReferences = function (jsContent, htmlFile, globalContext) {
    var newJsContent = javascriptHandler._transformTextbundleReferences(jsContent, htmlFile, globalContext);
    return newJsContent;
};


module.exports = new function () {

    this.preprocessing = function (file, globalContext, callback) {
        jsHandlingFunction = collectTextbundleReferences;

        try {
            parseHtml(file.content.toString('utf-8'), file, globalContext);
        } catch (error){
            error.filename = file.RunLocation;
            let parserError = new ParserError(error.message, error);
            return callback(parserError);
        }


        callback();
    };


    this.handleFile = function (file, globalContext, callback) {

        var fileContent = file.content.toString('utf8');
        var fileName = path.join(globalContext.pathMap["web-relative-path"], file.TargetFileName);
        var msgs = utils.scanFileForString(fileContent, SEARCH_STRINGS, fileName);
        msgs.forEach(function (m) {
            utils.pushMessage(file, m);
        });

        file.content = replaceUI5src(fileContent);

        jsHandlingFunction = transformTextbundleReferences;

        try {
            file.content = parseHtml(file.content.toString('utf-8'), file, globalContext);
        } catch (error) {
            error.filename = file.RunLocation;
            return callback(error);
        }



        file.toBeCreated.push({
            filename: path.join(globalContext.pathMap["web-dev-object-folder"], file.RunLocation),
            content: file.content,
            file_container: 'web'
        });
        file.doNotWriteContent();
        callback(null, file);
    }
};
