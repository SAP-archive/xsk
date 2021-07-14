/**
 * Created by SAP SE.
 */

var path = require('path');
var esprima = require('esprima');
var estraverse = require('estraverse');
var escodegen = require('escodegen');

var ParserError = require('../parser-error');
var mc = require('../message-categories');
var messages = require('../messages');
var utils = require('./plugin-utils');

const TEXTBUNDLE_REGEX = /(.*)\.hdbtextbundle(.*)/;
const GENERATOR_OPTIONS = {
    parse: esprima.parse,
    comment: true
};
const PARSER_OPTIONS = {
    comment: true,
    tokens: true,
    range: true,
    loc: true
};

var SEARCH_STRINGS = [
    {
        search: /\.hdbtextbundle/,
        message: messages.getMessage(mc.TRANSLATION, 1)
    },
    {
        search: /jQuery\.ajax/,
        message: messages.getMessage(mc.JS, 1)
    }
];
    
    
class JavascriptHandler{
    
    constructor() {}
    
    
    preprocessing(file, globalContext, callback) {

        var fileContent = file.content.toString('utf8');
        var parserError;
        
        try {
            this._collectTextbundleReferences(fileContent, file, globalContext);
        } catch (error) {
            error.filename = file.RunLocation;
            parserError = new ParserError(error.message, error);
        }
        
        return callback(parserError);
    }
    
    
    
    handleFile(file, globalContext, callback, sourceDb, sqlParser, pluginHandler){

        var fileContent = file.content.toString('utf8');
        var fileName = path.join(globalContext.pathMap["web-relative-path"], file.TargetFileName);
        
        try {
            var transformedAst = this._transformTextbundleReferences(fileContent, file, globalContext);    
        } catch(error) {
            error.filename = file.RunLocation;
            return callback(error);
        }
        
        var astWithComments = escodegen.attachComments(transformedAst, transformedAst.comments, transformedAst.tokens);
        var newFileContent = escodegen.generate(astWithComments, GENERATOR_OPTIONS);
        
        var msgs = utils.scanFileForString(fileContent, SEARCH_STRINGS, fileName);
        msgs.forEach(function (m) {
            utils.pushMessage(file, m);
        });
        
        file.toBeCreated.push({
            filename: path.join(globalContext.pathMap["web-dev-object-folder"], file.RunLocation),
            content: newFileContent,
            file_container: 'web'
        });
        file.doNotWriteContent();
        callback(null, file);
        
    }
    
    
    _collectTextbundleReferences(fileContent, file, globalContext){
        
        var ast = esprima.parse(fileContent);
        
        estraverse.traverse(ast, {
            enter: function (node) {
                if(node.type == 'Property' && node.value && node.value.value){
                    if(TEXTBUNDLE_REGEX.test(node.value.value)){

                        let relativeReference = node.value.value;
                        let absoluteRerence = JavascriptHandler._transformRelativePathtoAbsolute(relativeReference, file.packagePath);
                        
                        let textbundleReference = {
                            jsFile: file.RunLocation,
                            textbundleFile: absoluteRerence
                        };
                        
                        globalContext.textbundleReferences.push(textbundleReference);
                    }
                }
            }            
        });
    }
    
    
    static _transformRelativePathtoAbsolute(relativePath, jsFilePath){
        var joinedPath = path.join(jsFilePath, relativePath);
        joinedPath = joinedPath.replace(/\\/g, '/');
        
        return joinedPath;
    }
    
    
    _transformTextbundleReferences(fileContent, jsFile, globalContext) {

        var ast = esprima.parse(fileContent, PARSER_OPTIONS);

        estraverse.replace(ast, {
            enter: function (node) {
                if(node.type == 'Property' && node.value && node.value.value){
                    if(TEXTBUNDLE_REGEX.test(node.value.value)){
                        let relativeReference = node.value.value;
                        let absoluteReference = JavascriptHandler._transformRelativePathtoAbsolute(relativeReference, jsFile.packagePath);
                        if (JavascriptHandler._textbundleInContext(absoluteReference, globalContext)){
                            let replaced = node;
                            replaced.value.value = replaced.value.value.replace(TEXTBUNDLE_REGEX, "$1.properties$2");
                            replaced.value.raw = replaced.value.raw.replace(TEXTBUNDLE_REGEX, "$1.properties$2");
                            return replaced;
                        } else {
                            let errorMessage = messages.getMessage(mc.TRANSLATION, 6);
                            errorMessage.message.push(relativeReference, jsFile.RunLocation);
                            utils.pushMessage(jsFile, errorMessage);
                            return;
                        }                       
                    }
                } else if(node.type == 'NewExpression' && node.arguments && (node.arguments.length > 0)) {
                    let fullExpression = fileContent.substr(node.range[0], node.range[1]);
                    if (fullExpression.match(/sap\.ui\.model\.odata\.ODataModel/)) {
                        // see specification https://sapui5.hana.ondemand.com/#/api/sap.ui.model.odata.ODataModel
                        let newNode = node;
                        if ((node.arguments.length == 1) || (node.arguments[1].type == 'Literal')) {
                        //if (fullExpression.match(/ODataModel(.*,\s*false)/) || fullExpression.match(/ODataModel\([^,]*\)/)) {
                            let msg = messages.getMessage(mc.JS, 2);
                            msg.id = mc.getId(mc.JS, 2);
                            msg.file = path.join(globalContext.pathMap['web-dev-object-folder'], jsFile.RunLocation)
                            msg.loc = [node.loc];
                            utils.pushMessage(jsFile, msg);
                            newNode.arguments[1] = {
                                "type": "Literal",
                                "value": true,
                                "range": []
                            }
                        } else if (node.arguments[1] && (node.arguments[1].type == 'ObjectExpression')) {
                            let objectProperties = node.arguments[1].properties;
                            objectProperties.push({
                                "type": "Property",
                                "key": {
                                    "type": "Identifier",
                                    "name": "json"
                                },
                                "value": {
                                    "type": "Literal",
                                    "value": true
                                },
                                "kind": "init"
                            })
                            newNode.arguments[1] = {
                                "type": "ObjectExpression",
                                "properties": objectProperties,
                                "range": []
                            }
                        }

                        return newNode;
                    }

                }
            }
        });
        return ast;
    }
    
    
    static _textbundleInContext(textbundleReference, globalContext) {
        if(globalContext.textbundleFiles.indexOf(textbundleReference) > -1){
            return true;
        } else {
            return false;
        }
    }
    
}

module.exports = new JavascriptHandler();