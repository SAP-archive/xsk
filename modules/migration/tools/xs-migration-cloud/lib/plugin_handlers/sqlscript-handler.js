/**
 * Created by SAP SE.
 */

var async = require('async');
var path = require('path');

var SqlSecurity = require('../sql-security');
var currencyUtil = require('./currency-util');
var CalcEnginePlanOperators = require('../calc-engine-plan-operators');
var utils = require('./plugin-utils');
var mc = require('../message-categories');
var msgs = require('../messages');
var alterSearch = require('../alter-search-strings');
var RuntimeObject = require('../runtime-object');
var SqlscriptType = require('./sqlscript-type');
var SQL_PRIVILEGE = require('../sql-privilege');

const DEFAULT_SCHEMA_PATTERN = [/\s+DEFAULT\s+SCHEMA\s+(\w+)/i, /\s+DEFAULT\s+SCHEMA\s+"(\w+)"/i];
const SQL_SECURITY_INVOKER_PATTERN = /\s+SQL\s+SECURITY\s+INVOKER/i;
const COMMENT_PATTERN = /\/\*+((([^\*])+)|([\*]+(?!\/)))[*]+\/|(\/\/.*$)/gm;

const CREATE_PATTERN = /CREATE/i;

const FUNCTION_SCHEMA_PATTERNS = [
    /FUNCTION\s+"(\w+)"/i,
    /FUNCTION\s+(\w+)/i
];


var PROCEDURE_SCHEMA_PATTERNS = [
    /PROCEDURE\s+"(\w+)"\./i,
    /PROCEDURE\s+(\w+)\./i
];


const NON_READ_ONLY_PRIVILEGES = [
    SQL_PRIVILEGE.UPDATE,
    SQL_PRIVILEGE.DELETE,
    SQL_PRIVILEGE.INSERT
];


class SqlScriptHandler {
    
    
    static _removeDefaultSchemaClause(ddl) {
        
        for (let pattern of DEFAULT_SCHEMA_PATTERN) {
            if((ddl.match(pattern))) {
                ddl = ddl.replace(pattern, "");
            }
        }
        
        return ddl;
    }


    static _getDefaultSchema(ddl) {

        var m;
        
        for (let pattern of DEFAULT_SCHEMA_PATTERN) {
            if (m = ddl.match(pattern)) {
                return m[1];
            }
        }

    }


    static _getSecurityMode(ddl) {
        if(ddl.match(SQL_SECURITY_INVOKER_PATTERN)){
            return SqlSecurity.INVOKER;
        }
        return SqlSecurity.DEFINER;
    }
    

    static _searchAlterStrings(ddl, globalContext, newFilename, file) {

        var searchStrings = [
            {
                search: /[^\w]SESSION_USER/,
                message: msgs.getMessage(mc.SECURITY, 100)
            }
        ];

        searchStrings = searchStrings.concat(alterSearch.ALTER_SEARCH_STRINGS);
        var finds = utils.scanFileForString(ddl, searchStrings, path.join(globalContext.pathMap["db-relative-path"], newFilename));

        finds.forEach(function(find) {
            utils.pushMessage(file, find);
        });

    }    
    
    
    static _beginsWithCreate(ddl) {
        var create = ddl.substring(0, 6);

        if (create.match(CREATE_PATTERN)) {
            return true;
        }
        return false;
    }
    
    
    static _removeCreate(ddl) {
        
        if (SqlScriptHandler._beginsWithCreate(ddl)) {
            ddl = ddl.substring(7);    
        }

        CREATE_PATTERN.lastIndex = 0;
        
        return ddl;
    }


    static getSchemaName (content, sqlscriptType) {

        var patterns;
        
        if(sqlscriptType === SqlscriptType.FUNCTION) {
            patterns = FUNCTION_SCHEMA_PATTERNS;    
        } else if(sqlscriptType === SqlscriptType.PROCEDURE) {
            patterns = PROCEDURE_SCHEMA_PATTERNS;
        }
        
        var strippedContent = SqlScriptHandler._removeComments(content);
        
        for (var i=0; i < patterns.length; i++) {
            var p = patterns[i];
            var m = strippedContent.match(p);
            if (m) {
                return m[1];
            }
        }

        return undefined;
    }
    
    
    static _removeComments(string) {
        
        var stripped = string.replace(COMMENT_PATTERN, '');
        
        return stripped;
        
    }
    

    static handleFile(file, schemaName, sourceDb, sqlParser, globalContext, extension, isReadOnly, methodCallback) {

        if (schemaName === undefined) {
            let error = 'Unable to get schema name for object ' + file.RunLocation;
            return methodCallback(error);
        }

        var usedObjectPrivileges;
        if(!isReadOnly) {
            usedObjectPrivileges = NON_READ_ONLY_PRIVILEGES;
        }

        file.doNotWriteContent();
        
        let ddl = '';
        
        if(!SqlScriptHandler._beginsWithCreate(file.content.toString('utf-8'))) {
            ddl = 'CREATE ';
        }
        
        ddl += file.content.toString('utf-8');

        async.waterfall([
            function (callback) {
                sqlParser.getObjectsInDDLStatement(ddl, function (error, objectsInDDL) {
                    callback(error, objectsInDDL);
                });
            },
            function (objectsInDDL, callback) {
                
                var defaultSchema = SqlScriptHandler._getDefaultSchema(ddl);
                if (!defaultSchema) {
                    defaultSchema = schemaName;
                }
                
                var sqlSecurity = SqlScriptHandler._getSecurityMode(ddl);
                var currencyObjects = currencyUtil.addCurrencyTablesToRelatedObjects(defaultSchema, ddl, globalContext, file.RunLocation);
                var newFilename = path.join(utils.getFilepath(file.RunLocation), file.simpleName + '.' + extension);
                var sanitizedDdl = utils.removeSchema(ddl, objectsInDDL.RELATED_OBJECTS, globalContext, defaultSchema, file, newFilename, sqlSecurity,  true, usedObjectPrivileges);


                CalcEnginePlanOperators.logMessagesForUsedOperators(ddl, file, globalContext);

                sanitizedDdl = utils.removeSchema(sanitizedDdl, objectsInDDL.DDL_PROPERTY, globalContext, defaultSchema, file, newFilename, sqlSecurity, false, usedObjectPrivileges);
                sanitizedDdl = SqlScriptHandler._removeDefaultSchemaClause(sanitizedDdl);
                sanitizedDdl = SqlScriptHandler._removeCreate(sanitizedDdl);

                // add currency conversion tables to ce_conversion call
                if (currencyObjects) {
                    sanitizedDdl = currencyUtil.addSynonymsToCeConversion(file, defaultSchema, globalContext, sanitizedDdl, currencyObjects);
                }

                sanitizedDdl = CalcEnginePlanOperators.handleDataSourceAccessOperators(sanitizedDdl, defaultSchema, globalContext, file.RunLocation);
                
                
                var container = 'db';
                if(file.container === 'todo') {
                    container = 'todo';
                }                
                
                file.toBeCreated.push({
                    filename: path.join(globalContext.pathMap["db-dev-object-folder"], newFilename),
                    update_content: sanitizedDdl,
                    file_container: container
                });

                SqlScriptHandler._searchAlterStrings(sanitizedDdl, globalContext, newFilename, file);
                
                if(objectsInDDL.DDL_PROPERTY && objectsInDDL.DDL_PROPERTY.length > 0) {
                    var origSchema = objectsInDDL.DDL_PROPERTY["0"].SCHEMA_NAME;
                    var origObject = objectsInDDL.DDL_PROPERTY["0"].OBJECT_NAME;

                    var newRuntimeObject = new RuntimeObject(origSchema, utils.getDoubleColonName(origObject));
                    globalContext.contextObjects.add(newRuntimeObject);
                }                

                callback();
                
            }
        ], function (error) {
            return methodCallback(error, file);
        })
    }


   
    
    
    
}


module.exports = SqlScriptHandler;
