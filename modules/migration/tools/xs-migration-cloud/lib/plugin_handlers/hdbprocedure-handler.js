// --------------------------------------------------------------------------
//
// Handler for .hdbprocedure and .procedure types
//
// .procedure will be migrated automatically to .hdbprocedure
//
// --------------------------------------------------------------------------

var xml2js = require('xml2js');
var path = require('path');
var async = require('async');

var log = require('../log-util');
var utils = require('./plugin-utils');
var structureHandler = require('./hdbstructure-handler');
var RuntimeObject = require('../runtime-object');
var SqlscriptType = require('./sqlscript-type');
var SqlScriptHandler = require('./sqlscript-handler');
var messages = require('../messages');
var messageCategories = require('../message-categories');

const PROCEDURE_TYPE = require('../procedure-type');


var HDBPROCEDURE_EXT = 'hdbprocedure';
var PROCEDURE_EXT = 'procedure';



class HdbprocedureHandler {

   
    _getProcedureSchemaName(contents) {

        var schemaName;
        xml2js.parseString(contents, function(err, result) {

            if ("UDE:Procedure" in result) {
                var p = result["UDE:Procedure"];
                if ('schema' in p) {
                    var s = p['schema'];
                    if (s.length > 0 && "$" in s[0] && "id" in s[0]["$"]) {
                        schemaName = s[0].$.id;
                    }
                }
            }
        });
        return schemaName;
    }

    
    
    
    _retrieveTableTypesForProcedure(globalContext, file, schemaName, procedureName, sourceDb, methodCallback) {
        
        log.debug("Retrieving table types for " + schemaName + "." + procedureName);
        
        var _this = this;
        
        async.waterfall([
            function (callback) {
                sourceDb.getTableTypesForProcedures(schemaName, procedureName, callback);                
            },
            function(tableTypes, callback) {
                _this._createNewTableTypes(tableTypes, globalContext, sourceDb, callback)
            }
        ], function(error, newTableTypes) {
            file.toBeCreated = newTableTypes;
            methodCallback(error);
        });
    }

    
    _createNewTableTypes(tableTypes, globalContext, sourceDb, callback) {
        
        var _this = this;
        
        async.map(tableTypes, function (tableType, done) {
            
            var newRuntimeObject = new RuntimeObject(tableType.schemaName, utils.getDoubleColonName(tableType.tableName));
            globalContext.contextObjects.add(newRuntimeObject);
            
            _this._createNewTableType(tableType, globalContext, sourceDb, done);
            
        }, function (error, newTableTypes) {
            return callback(error, newTableTypes);
        });        
        
    }
    
    
    _createNewTableType(tableType, globalContext, sourceDb, callback) {
        
        sourceDb.getDdl(tableType.schemaName, undefined, tableType.tableName, undefined, function(error, ddl) {

            if(error) {
                return callback(error);
            }         
            
            var tableTypeDdl = structureHandler.cleanDdl(ddl);
                        
            var newTableType = {
                filename: path.join(globalContext.pathMap["db-dev-object-folder"], tableType.pathName, tableType.objectName),
                update_content: tableTypeDdl,
                file_container: 'db'
            };

            return callback(null, newTableType);
        });        
        
    }
    
    
    handleFile (file, globalContext, callback, sourceDb, sqlParser) {

        var schemaName;
        var contentString = file.content.toString('utf-8');

        var isHdbProcedure = file.suffix === HDBPROCEDURE_EXT;
        var isProcedure = file.suffix === PROCEDURE_EXT;

        if (isHdbProcedure) {
            schemaName = SqlScriptHandler.getSchemaName(contentString, SqlscriptType.PROCEDURE);
        } else {
            schemaName = this._getProcedureSchemaName(contentString);
        }
               
        var _this = this;
        
        var oldProcedureName = file.packageId + '/' + file.simpleName;
        var procedureName = file.fullName;
        
        if(isProcedure) {
            procedureName = oldProcedureName
        }
        
        async.waterfall([
            function (callback) {
                if (isProcedure) {
                    _this._retrieveTableTypesForProcedure(globalContext, file, schemaName, oldProcedureName, sourceDb, callback);
                } else {
                    callback();
                }
            },
            function (callback) {
                if(isProcedure) {
                    sourceDb.getDdl(schemaName, file.packageId, file.simpleName, file.suffix, callback);
                } else {
                    callback(null, null);
                }
            },
            function (ddl, callback) {
                if(ddl) {
                    file.content = ddl;
                }
                
                sourceDb.getProcedureType(schemaName, procedureName, callback);
            },
            function (procedureType, callback) {
                
                if(procedureType === PROCEDURE_TYPE.R) {
                    let msg = messages.getMessage(messageCategories.HDI, 25);
                    msg.file = file.RunLocation;
                    utils.pushMessage(file, msg);
                }
                
                sourceDb.isReadOnlyProcedure(schemaName, procedureName, callback);  
            },
            function (isReadOnly, callback) {
                SqlScriptHandler.handleFile(file, schemaName, sourceDb, sqlParser, globalContext, 'hdbprocedure', isReadOnly, callback);
            }            
        ], function (error, file) {
            callback(error, file)
        });
        
    }
}

module.exports = new HdbprocedureHandler();
