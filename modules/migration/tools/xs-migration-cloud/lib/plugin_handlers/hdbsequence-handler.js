
var path = require('path');
var async = require('async');

var utils = require('./plugin-utils');
var RuntimeObject = require('../runtime-object');

var SCHEMA_NAME_REGEX = /schema\s*=\s*"(\w+)";/;

var HDBSEQUENCE_NAME_REGEX = /([^\/]+).hdbsequence$/;

function HdbsequenceHandler() {

    function getSchemaName(hdbprocedure) {
        var m1 = hdbprocedure.match(SCHEMA_NAME_REGEX);
        if (m1) {
            return m1[1];
        }
        return undefined;
    }

    function cleanDdl(ddl) {

        function replacer(match, p1, p2) {
            return p2;
        }
        
        var pt = /(CREATE\s)(SEQUENCE\s)("\w+"\.)/;
        return ddl.replace(pt, replacer);
    }

    this.handleFile = function(file, globalContext, callback, sourceDb, sqlParser) {

        var name = file.RunLocation;
        var sequenceName = name.match(HDBSEQUENCE_NAME_REGEX)[1];
        var packageName = name.substring(1, name.length - sequenceName.length - "hdbsequence".length-2);
        packageName = packageName.replace(/\//g,".");
        var schemaName = getSchemaName(file.content.toString('utf8'));

        if (schemaName === undefined) {
            utils.pushSchemaNameError(file, globalContext);
            callback(null, file);
        } else {
            async.waterfall([
                function(callback) {
                    sourceDb.getDdl(schemaName, packageName, sequenceName, "hdbsequence", function(error, ddl) {
                        if (error) {
                            utils.pushGetDDLError(file, globalContext, schemaName, error);
                        } 
                        callback(error, ddl);
                    });
                },
                function(ddl, callback) {
                    sqlParser.getObjectsInDDLStatement(ddl, function(error, result) {
                        callback(error, ddl, result);
                    });
                },
                function(ddl, objectInfo, callback) {

                    // moved from original schema
                    //
                    var origSchema = objectInfo.DDL_PROPERTY["0"].SCHEMA_NAME;
                    var origObject = objectInfo.DDL_PROPERTY["0"].OBJECT_NAME;
                    
                    var newRuntimeObject = new RuntimeObject(origSchema, utils.getDoubleColonName(origObject));
                    globalContext.contextObjects.add(newRuntimeObject);
                    
                    var newFilename = path.join(globalContext.pathMap["db-dev-object-folder"], file.RunLocation);
                    var sanitizedDdl = utils.removeSchema(ddl, objectInfo.RELATED_OBJECTS, globalContext, file, newFilename);
                    var cDdl = cleanDdl(sanitizedDdl);
                    file.toBeCreated.push({
                        filename: newFilename,
                        update_content: cDdl,
                        file_container: 'db'
                    });
                    callback();
                }
                ],
                function(error) {
                    if(error){
                        return callback(error);
                    }
                    callback(null, file);
                }
            );
        }
    }
}

module.exports = new HdbsequenceHandler();
