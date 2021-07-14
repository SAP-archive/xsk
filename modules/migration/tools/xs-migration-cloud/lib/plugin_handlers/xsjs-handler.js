// --------------------------------------------------------------------------
// Plugin handler for xsjs Javascript objects
//
// These are the warnings and errors that will be generated in case one of
// the following expressions are found:
//
// $.session.hasAppPrivilege()
//   Either this function does not exist anymore or it returns always true.
//   As the security concept are changed, in XS2 we use technical user +
//   business user propagation, the XS application should re-think the
//   idea of asking if a user has a privilege.
//
// $.session.getSecurityToken()
//   This should return a CSRF token, but currently i do not see such a
//   function in the new runtime
//
// $.repo
//   this is clear
//
// Array.<<static methods>>
//   This is like Array.slice(), Array.map() , Array.filter() and some
//   other static functions, which are defined in SpiderMonkey but not
//   defined in Node.js
//   It is questionable if you have to do warning here, since I expect
//   that some day the XSJS team in Sofia will define these methods.
//   Very important : instance methods slice(), map() and filter() are
//   defined!
//   Good example:
//     Var x = []
//     x.map(function(e){…})
//
// Bad example:
//   Var x = []
//   Array.map(x,function(e){})
//
// Number.isInteger()
//   The same problem as with Array static functions. XSJS runtime can
//   provide that as well.
//
// string.startsWith()  string.endsWith()  contains()
//   These string instance methods are defined in SpiderMonkey and not
//   defined in Node.js
//
// I was able to define Array.* and Number.isIntiger() functions, I was
// not able to define String.* functions.
// Hence the real problem is with these String functions I would say.
//
// --------------------------------------------------------------------------

var esprima = require('esprima');
var estraverse = require('estraverse');
var escodegen = require('escodegen');


var path = require('path');

const SQL_PRIVILEGE = require('../sql-privilege');

var putils = require('./plugin-utils');
var utils = require('../generator-utils');
var logUtil = require('../log-util');
var xsjs_parser = require('../xsjs-parser');
var mc = require('../message-categories');
var xsjs = require('../xsjs-generator');
var msgs = require('../messages');
var alterSearch = require('../alter-search-strings');

var UsedObject = require('../used-object');
var SqlSecurity = require('../sql-security');

var esprima_options = {
	    loc: true,
        comment: true,
        tokens: true,
        range: true
	};

var checkStatIndex = -1;
var getIndex = function(){
	checkStatIndex += 1;
	return checkStatIndex
};

var checkStatement = [
    {
        text:{
            "type": "VariableDeclaration",
            "kind": "const"
        },
        text2: "const",
        pattern: "\\const",
        id: mc.getId(mc.XSJS, 4),
        mid: getIndex()
    },
    {
        text:{
            "type": "$any$",
            "kind": "let"
        },
        text2: "let",
        pattern: "\\let",
        id: mc.getId(mc.XSJS, 4),
        mid: getIndex()
    },
    {
        text:{
            "type": "BinaryExpression",
            "operator": "instanceof"
        },
        text2: "instanceof",
        pattern: "\\instanceof",
        id: mc.getId(mc.XSJS, 6),
        mid: getIndex()
    },
    {
    	text: "$.import(\"sap.hana.xs.libs.dbutils\", \"procedures\")",
        pattern: "\\$.import(\"sap.hana.xs.libs.dbutils\", \"procedures\")",
        id: mc.getId(mc.XSJS, 8),
        mid: getIndex()
    },
    {
    	text: "$.import('sap.hana.xs.libs.dbutils', 'procedures')",
        pattern: "\\$.import('sap.hana.xs.libs.dbutils', 'procedures')",
        id: mc.getId(mc.XSJS, 8),
        mid: getIndex()
    },
    {
    	text: "$.import(\"sap.hana.xs.libs.dbutils\", \"xsds\")",
        pattern: "\\$.import(\"sap.hana.xs.libs.dbutils\", \"xsds\")",
        id: mc.getId(mc.XSJS, 8),
        mid: getIndex()
    },
    {
    	text: "$.import('sap.hana.xs.libs.dbutils', 'xsds')",
        pattern: "\\$.import('sap.hana.xs.libs.dbutils', 'xsds')",
        id: mc.getId(mc.XSJS, 8),
        mid: getIndex()
    },
    {
    	text: "$.session.hasAppPrivilege",
        pattern: "\\$.session.hasAppPrivilege",
        id: mc.getId(mc.SECURITY, 3),
        mid: getIndex()
    },
    {
    	text: "$.session.assertAppPrivilege",
        pattern: "\\$.session.assertAppPrivilege",
        id: mc.getId(mc.SECURITY, 3),
        mid: getIndex()
    },
    {
    	text: "$.session.hasSystemPrivilege",
        pattern: "\\$.session.hasSystemPrivilege",
        id: mc.getId(mc.SECURITY, 5),
        mid: getIndex()
    },
    {
    	text: "$.session.assertSystemPrivilege",
        pattern: "\\$.session.assertSystemPrivilege",
        id: mc.getId(mc.SECURITY, 5),
        mid: getIndex()
    },
    {
        text: "$.repo.writeObject",
        pattern: "\\$.repo.writeObject",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.readObject",
        pattern: "\\$.repo.readObject",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.createActiveSession",
        pattern: "\\$.repo.createActiveSession",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.existsPackage",
        pattern: "\\$.repo.existsPackage",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.createInactiveSession",
        pattern: "\\$.repo.createInactiveSession",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.newPackageInfo",
        pattern: "\\$.repo.newPackageInfo",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.createPackage",
        pattern: "\\$.repo.createPackage",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.deletePackage",
        pattern: "\\$.repo.deletePackage",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.createObjectFilter",
        pattern: "\\$.repo.createObjectFilter",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.findObjects",
        pattern: "\\$.repo.findObjects",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.createObjectId",
        pattern: "\\$.repo.createObjectId",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.createInactiveMetadata",
        pattern: "\\$.repo.createInactiveMetadata",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.activateObjects",
        pattern: "\\$.repo.activateObjects",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.deleteObject",
        pattern: "\\$.repo.deleteObject",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.objectExists",
        pattern: "\\$.repo.objectExists",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.createActiveVersion",
        pattern: "\\$.repo.createActiveVersion",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.repo.revalidateObjects",
        pattern: "\\$.repo.revalidateObjects",
        id: mc.getId(mc.XSJS, 2),
        mid: getIndex()
    },
    {
        text: "$.config",
        pattern: "\\$.config",
        id: mc.getId(mc.XSJS, 3),
        mid: getIndex()
    },
    {
        text: "$.db.getConnection",
        pattern: "\\$.db.getConnection",
        id: mc.getId(mc.SECURITY, 4),
		mid: getIndex()
    },
    {
        text: "$.hdb.getConnection",
        pattern: "\\$.hdb.getConnection",
        id: mc.getId(mc.SECURITY, 4),
		mid: getIndex()
    },
    {
        text: "$.db.getConnection",
        pattern: "\\$.db.getConnection",
        id: mc.getId(mc.XSJS, 9),
        mid: getIndex()
    },
    {
        text: "$.hdb.getConnection",
        pattern: "\\$.hdb.getConnection",
        id: mc.getId(mc.XSJS, 9),
        mid: getIndex()
    },
    {
        text: "jQuery.ajax",
        pattern: "\\jQuery.ajax",
        id: mc.getId(mc.JS, 1),
        mid: getIndex()
    }
];

for(var i=0; i < checkStatement.length; i++) {
    var stmt = checkStatement[i];
    var msg = msgs.getMessage(stmt.id);
    stmt.type = msg.type;
    stmt.message = msg.message;
    if(typeof stmt.text  === 'string'){
        stmt.message.push(stmt.text);
    }
    else{
        stmt.message.push(stmt.text2);
    }
    stmt.category = msg.category;
}


var textSearchRules = checkStatement;

var schemaTextSearchRules = [
    {
        name: "_SYS_BI",
        pattern: /_SYS_BI\"?\./g
    },
    {
        name: "_SYS_REPO",
        pattern: /_SYS_REPO\"?\./g
    }
];

function makeTextSearch(content, rules){
	var number = 0;
	var resultSet = [];
	for(var i in rules){
		var rule = new RegExp(rules[i].pattern,"g");
		var num = content.match(rule);
		if(num){
			number += num.length;
			resultSet.push(num.length);
		}
		else{
			resultSet.push(0);
		}

	}
	return {total:number, detail:resultSet};
}

function makeTextSearchSchema(globalContext, file, content, rules) {

    rules.forEach(function(rule) {
        var result = content.match(rule.pattern);
        if (result) {
            var msg = msgs.getMessage(mc.XSJS, 10);
            msg.file = path.join(globalContext.pathMap['xsjs-relative-path'], file.RunLocation)
            msg.message.push(rule.name);
            putils.pushMessage(file, msg);
        }
    });
}

function makeAlterSearch(globalContext, file, content) {
    var s = putils.scanFileForString(
                                content,
                                alterSearch.ALTER_SEARCH_STRINGS,
                                path.join(globalContext.pathMap["xsjs-relative-path"], file.TargetFileName)
                                );
    s.forEach(function(e) {
        putils.pushMessage(file, e);
    });
}

function removeSchemaReferences(globalContext, file, tree) {

    const GENERATOR_OPTIONS = {
        parse: esprima.parse,
        comment: true
    };

    let originalContent = file.content.toString('utf8');

    let codeReplacements = [];

    let messageForEntity = {};

    var addMessage = function (messageClass, messageNumber, node, entity) {
        if (!messageForEntity[entity] || !messageForEntity[entity][messageNumber]) {
            let msg = msgs.getMessage(messageClass, messageNumber);
            msg.id = mc.getId(messageClass, messageNumber);
            msg.file = path.join(globalContext.pathMap['xsjs-relative-path'], file.RunLocation)
            msg.message.push(entity);
            msg.loc = [node.loc];
            putils.pushMessage(file, msg);
            
            if(!messageForEntity[entity]) messageForEntity[entity] = {};
            
            messageForEntity[entity][messageNumber] = msg;
        } else {
            messageForEntity[entity][messageNumber].loc.push(node.loc);
        }
    };

    estraverse.traverse(tree, {
        enter: function (node) {
            if (node.type == 'Literal' && node.value) {


                let newNode = node;

                findIndexForEscapedSchema(globalContext, (escapedSchema) => {

                    let SCHEMA_PREFIX_REGEX = new RegExp('\\\\?"' + escapedSchema + '\\\\?"\\.|[^"]' + escapedSchema + '\\.', 'g');


                    if (SCHEMA_PREFIX_REGEX.test(newNode.raw)) {

                        let codeToReplace = {
                            range: node.range,
                            newCode: node.raw.replace(SCHEMA_PREFIX_REGEX, '')
                        };

                        codeReplacements.push(codeToReplace);

                        addMessage(mc.XSJS, 11, node, escapedSchema);

                    }
                });

                // replace known calc views
                if (/_SYS_BIC/.test(newNode.raw)) {

                    let found = findIndexForSysBicContextObject(globalContext, (contextObject, escapedSchemaName, escapedObjectName) => {
                        let OBJECT_NAME_REGEX = new RegExp('\\\\?"' + escapedSchemaName + '\\\\?".\\\\?"' + escapedObjectName + '\\\\?"');
                        if (OBJECT_NAME_REGEX.test(newNode.raw)) {
                            let newObjectName = '\\"' + contextObject.objectName.replace(/\//, '::') + '\\"';
                            let newLiteral = newNode.raw.replace(OBJECT_NAME_REGEX, newObjectName);
                            
                            let codeToReplace = {
                                range: newNode.range,
                                newCode: newLiteral
                            };
                            
                            codeReplacements.push(codeToReplace);
                            addMessage(mc.XSJS, 12, node, contextObject.objectName);
                            return true;
                        }
                    });

                    if (found < 0) {
                        
                        let OBJECT_NAME_REGEX = [
                            /\\\\"_SYS_BIC"\\\\?"\.\\\\?"([^"]*?)\\\\"/g,
                            /"_SYS_BIC"\."([^"]*?)"/g
                        ];
                        
                        let REGEX_ESCAPE_CHARACTERS = [
                            { stringEscape: '\"', regexEscape: '\\\\"' },
                            { stringEscape: '"', regexEscape: '"' }
                        ];

                        let newLiteral = newNode.raw;
                        
                        let matches;
                        for(let i = 0; i < OBJECT_NAME_REGEX.length; i++) {
                            while((matches = OBJECT_NAME_REGEX[i].exec(node.raw)) !== null){
                                let objectName = matches[1];
                                let usedObject = new UsedObject("_SYS_BIC", objectName, [SQL_PRIVILEGE.SELECT], SqlSecurity.INVOKER, undefined, path.join(globalContext.pathMap['xsjs-relative-path'], file.RunLocation));
                                usedObject.addUserName(file.RunLocation);
                                
                                putils.handleUsedObject(usedObject, globalContext);
                                
                                if(usedObject.isCoverable) {                                    
                                    
                                    let quote = REGEX_ESCAPE_CHARACTERS[i].regexEscape;
                                    let replacementRegex = new RegExp(quote + '_SYS_BIC' + quote + '.' + quote + objectName + quote, 'g');
                                    
                                    newLiteral = newLiteral.replace(replacementRegex, REGEX_ESCAPE_CHARACTERS[i].stringEscape + usedObject.synonymName + REGEX_ESCAPE_CHARACTERS[i].stringEscape);
                                                                       
                                } else {
                                    addMessage(mc.XSJS, 10, node, "_SYS_BIC");
                                }
                            }
                        }

                        let codeToReplace = {
                            range: node.range,
                            newCode: newLiteral
                        };
                        
                        codeReplacements.push(codeToReplace);
                    }
                }

            } else if (node.type == 'CallExpression' && node.callee && node.callee.property) {
                let newNode = node;

                let visitor = this;
                
                switch (newNode.callee.property.name) {
                    case 'loadProcedure':
                        if (newNode.arguments.length > 1) {
                            
                            if(newNode.arguments[0].type !== 'Literal' || newNode.arguments[1].type !== 'Literal') {
                                addMessage(mc.XSJS, 15, newNode, newNode.arguments[0].name);
                                break; 
                            }
                            
                            let schemaName = newNode.arguments[0].value;
                            let objectName = newNode.arguments[1].value;
                            
                            let procedureReference = new UsedObject(schemaName, objectName, [SQL_PRIVILEGE.EXECUTE], SqlSecurity.INVOKER, undefined, path.join(globalContext.pathMap['xsjs-relative-path'], file.RunLocation));
                            procedureReference.addUserName(file.RunLocation);
                            
                            putils.handleUsedObject(procedureReference, globalContext);

                            newNode.arguments.shift();
                                                        
                            newNode.arguments[0].raw = newNode.arguments[0].raw.replace(objectName, procedureReference.synonymName);
                            newNode.arguments[0].value = procedureReference.synonymName;
                            
                            let codeToReplace = {
                                range: node.range,
                                newCode: escodegen.generate(newNode, GENERATOR_OPTIONS)
                            };
                            
                            codeReplacements.push(codeToReplace);

                            if(procedureReference.isCoverable) {
                                addMessage(mc.XSJS, 13, newNode, schemaName);
                            } else {
                                addMessage(mc.XSJS, 14, newNode, schemaName);
                            }

                        }
                        break;
                    case 'executeUpdate':
                        if (newNode && newNode.arguments.length == 1) {

                            findIndexForEscapedSchema(globalContext, (escapedSchema) => {

                                let SET_SCHEMA_REGEX = new RegExp('SET\\s+SCHEMA\\s+\\\\?"?' + escapedSchema + '\\\\?"?', 'g');

                                if (SET_SCHEMA_REGEX.test(newNode.arguments[0].value)) {
                                    let codeToReplace = {
                                        range: newNode.range,
                                        newCode: ''
                                    };
                                    codeReplacements.push(codeToReplace);
                                    addMessage(mc.XSJS, 11, node, escapedSchema);
                                }

                            });
                        }
                        break;
                    case 'prepareStatement':
                        if (newNode.arguments.length == 1) {

                            findIndexForEscapedSchema(globalContext, (escapedSchema) => {

                                let SET_SCHEMA_REGEX = new RegExp('SET\\s+SCHEMA\\s+\\\\?"?' + escapedSchema + '\\\\?"?', 'g');

                                if (newNode && newNode.arguments.length == 1) {
                                    if (SET_SCHEMA_REGEX.test(newNode.arguments[0].value)) {

                                        let nodeToReplace = findStatementNode(visitor, newNode);

                                        // do not replace code in case of more complex code such as variable assignment
                                        if (nodeToReplace) {
                                            let codeToReplace = {
                                                range: nodeToReplace.range,
                                                newCode: ''
                                            };
                                            codeReplacements.push(codeToReplace);
                                            addMessage(mc.XSJS, 11, node, escapedSchema);
                                        }
                                    }
                                }

                            });
                        }
                        break;
                    
                    case 'getConnection':
                        if(newNode.arguments.length === 1 && newNode.arguments[0].type === 'ObjectExpression'){
                            
                            var codeToReplace = {
                                range: newNode.arguments[0].range,
                                newCode: ''
                            };
                            codeReplacements.push(codeToReplace);
                         
                            let msg = msgs.getMessage(mc.XSJS, 16);
                            msg.file = file.RunLocation;
                            putils.pushMessage(file, msg);
                        }
                        
                        break;
                    default:
                }
            }
        }
    });

    return replaceCode(originalContent, codeReplacements);
}

function findStatementNode(visitor, node) {
    let parents = visitor.parents(node);
    let i = parents.length;
    let parentNode = node;
    while (!('ExpressionStatement' === parentNode.type) && (i > 0)) {
        i--;
        parentNode = parents[i];
    }

    if (i === 0) {
        parentNode = null;
    }

    return parentNode;
}

function escapeForRegex(string) {
    return string.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&');
}

function findIndexForEscapedRegexString(stringArray, visitor) {
    return stringArray.findIndex((regexString) => {
        // escape schema
        let escapedRegexString = escapeForRegex(regexString);

        if (visitor(escapedRegexString)) {
            return true;
        }
    });
}

function findIndexForEscapedSchema(globalContext, visitor) {
    return findIndexForEscapedRegexString(globalContext.applicationSchemas, visitor);
}

function findIndexForSysBicContextObject(globalContext, visitor) {
    return globalContext.contextObjects.findIndex( (contextObject) => {

        if (contextObject.schemaName == "_SYS_BIC") {
            let escapedSchemaName = escapeForRegex(contextObject.schemaName);
            let escapedObjectName = escapeForRegex(contextObject.objectName);

            if (visitor(contextObject, escapedSchemaName, escapedObjectName)) {
                return true;
            }
        }
    });
}

function replaceCode(content, replacements) {
    let reverseReplacements = replacements.reverse();
    let updatedContent = content;

    reverseReplacements.forEach( (replacement) => {
        updatedContent = replaceRange(updatedContent, replacement.range[0], replacement.range[1], replacement.newCode);
    });

    return updatedContent;
}

function replaceRange(content, start, end, newCode) {
    return content.substring(0, start) + newCode + content.substring(end);
}

function XsjsEvaluator() {

    this._findStatement = findStatementNode;

    this._parse = function(content) {
        return esprima.parse(content, esprima_options);
    }

    this.handleFile = function (file, globalContext, callback) {
    	var ret;
        var content = file.content.toString('utf8');
        var updatedContent = content;

        file.messages = [];

        try {
            var tree = this._parse(content);
            var checks = [];
            for(let i = 0; i < checkStatement.length; i ++){
                if(typeof checkStatement[i].text  === 'string'){
                    checks.push(xsjs_parser.prepareCheck(checkStatement[i].text));
                }
                else{
                    checks.push(checkStatement[i].text);
                }
            }


            updatedContent = removeSchemaReferences(globalContext, file, tree);

            ret = xsjs_parser.findObject(tree, checks);
     
            var ret_parse = [];
    
            if (ret.match.length > 0) {
                for(let i in ret.match){
                    var arrayID = ret.match[i].foundIndex;
                    if(!ret_parse[arrayID]){
                        ret_parse[arrayID] = 0;
                        let msg = {};
                        msg.type = checkStatement[arrayID].type;
                        msg.message = checkStatement[arrayID].message;
                        msg.file = path.join(globalContext.pathMap['xsjs-relative-path'], file.RunLocation);
                        msg.loc = [ret.match[i].pos];
                        msg.category = checkStatement[arrayID].category;
                        msg.id = checkStatement[arrayID].id;
                        msg.mid = checkStatement[arrayID].mid;
                        file.messages.push(msg);
                    }
                    else{
                        for(var mi = 0; mi < file.messages.length; mi++){
                            if(file.messages[mi].mid == arrayID){
                                file.messages[mi].loc.push(ret.match[i].pos);
                                break;
                            }
                        }
                    }
                    ret_parse[arrayID]++;
                }
            }
            var ret_textsearch= makeTextSearch(content, textSearchRules);
            var textSearch = ret_textsearch.total;
            for(let i = 0; i < ret_textsearch.detail.length; i++){
                if(!ret_parse[i]){ret_parse[i] = 0;}
                if(ret_textsearch.detail[i] != ret_parse[i]){
                    logUtil.debug({
                        type: mc.WARNING,
                        message: [path.join(globalContext.pathMap.xsjs, file.RunLocation) + "\n found additional {0} result using text search for not supported grammar " + checkStatement[i].text + ", the additional findings could be in comment, text string or exceptional use scenario"/* + ret_textsearch.detail[i] + " - " + ret_parse[i] + " and index:" + i + ","*/, ret_textsearch.detail[i] - ret_parse[i]],
                        file: path.join(globalContext.pathMap['xsjs-relative-path'], file.RunLocation),
                        category: mc.XSJS,
                        id: mc.XSJS + "_2"
                    });
                }
                else if(textSearch != 0){
                    logUtil.trace("file " + path.join(globalContext.pathMap.xsjs, file.RunLocation) + " match");
                }
                else{
                    logUtil.trace("file " + path.join(globalContext.pathMap.xsjs, file.RunLocation) +" nothing found");
                }
            }
    
            makeTextSearchSchema(globalContext, file, content, schemaTextSearchRules);
    
            makeAlterSearch(globalContext, file, content);
    
            file.toBeCreated.push({
                filename: path.join(xsjs.properties.devObjectFolder, file.RunLocation),
                content: updatedContent,
                file_container: 'xsjs'
            });

        } catch (e) {
            
            console.log('ERROR: failed to process xsjs file: ' + file.RunLocation);
            
            var msg = msgs.getMessage(mc.XSJS, 1);
            msg.message.push(file.RunLocation);
            
            if(e.lineNumber && e.column && e.description) {
                var parserErrorMsg = e.description + ' line: ' + e.lineNumber + ' column: ' + e.column;
                msg.message.push(parserErrorMsg);
            }
            
            file.messages.push(msg);
            
            file.toBeCreated = [{
                filename: path.join(xsjs.properties.devObjectFolder, file.RunLocation),
                content: updatedContent,
                file_container: 'todo'
            }];
        }

        file.doNotWriteContent();
        callback(null, file);
    }
}

module.exports = new XsjsEvaluator();
