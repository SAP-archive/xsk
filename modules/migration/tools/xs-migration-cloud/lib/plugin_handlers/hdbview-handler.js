// --------------------------------------------------------------------------
//
// hdbview handler 
//
//
// --------------------------------------------------------------------------

var path = require('path');
var async = require('async');

var utils = require('./plugin-utils');

var SCHEMA_NAME_REGEX = /schema\s*=\s*"(\w+)";/;

var HDBVIEW_NAME_REGEX = /([^\/]+).hdbview$/;
var VIEW_NAME_REGEX = /([^\/]+).view$/;

var NEW_HDBVIEW_EXT = 'hdbview';
var HDBVIEW_EXT = 'hdbview';
var VIEW_EXT = 'view';

function HdbtableHandler() {

    function getSchemaName(text) {
        var m1 = text.match(SCHEMA_NAME_REGEX);
        if (m1) {
            return m1[1];
        }
        return undefined;
    }

    function getSchemaNameView(text) {

        // this file is not JSON (although it looks like it)
        //
        var SCHEMA_SEARCH_PATTERN = /"schema"\s*:\s*"(\w+)"/;
        var m = text.match(SCHEMA_SEARCH_PATTERN);
        if (m) {
            return m[1];
        } else {
            return undefined;
        }
    }

    function cleanDdl(ddl) {

        function replacer(match, p1, p2, p3, p4, offset, string) {
            return p2;
        }
        
        var pt = /(CREATE\s)(VIEW\s)("[^"]+"\.)/;
        return ddl.replace(pt, replacer);
    }

    this.handleFile = function(file, globalContext, callback, sourceDb, sqlParser) {

        var name = file.RunLocation;
        var fileContent = file.content.toString('utf8');
        file.doNotWriteContent();
        var viewName, packageName, schemaName;

        var fileType = utils.getExtension(name);
        switch (fileType) {
            case HDBVIEW_EXT:
                viewName = name.match(HDBVIEW_NAME_REGEX)[1];
                packageName = name.substring(1, name.length - viewName.length - HDBVIEW_EXT.length-2);
                packageName = packageName.replace(/\//g,".");
                schemaName = getSchemaName(fileContent);
                break;
            case VIEW_EXT:
                viewName = name.match(VIEW_NAME_REGEX)[1];
                packageName = name.substring(1, name.length - viewName.length - VIEW_EXT.length-2);
                packageName = packageName.replace(/\//g,".");
                schemaName = getSchemaNameView(fileContent);
                break;
			default:
            	throw new Error("Unknown file type for view: " + fileType);                
        }

        var newFilename = path.join(
                                globalContext.pathMap["db-dev-object-folder"],
                                utils.getFilepath(file.RunLocation),
                                utils.getObjectname(file.RunLocation) + "." + NEW_HDBVIEW_EXT);
        var updatedFilename = path.join(
                                utils.getFilepath(file.RunLocation),
                                utils.getObjectname(file.RunLocation) + "." + NEW_HDBVIEW_EXT);

        if (schemaName === undefined) {
            utils.pushSchemaNameError(file, globalContext);
            callback(null, file);
        } else {

            async.waterfall([
                function(cb) {
                    sourceDb.getDdl(schemaName, packageName, viewName, HDBVIEW_EXT, function (error, ddl) {
                        if (error) {
                            utils.pushGetDDLError(file, globalContext, schemaName, error);
                        }
                        cb(error, ddl);
                    });
                },
                function(ddl, cb) {
                    sqlParser.getObjectsInDDLStatement(ddl, function(error, objects) {
                        cb(error, objects, ddl);
                    });
                },
                function(res, ddl, cb) {
                    var sanitizedDdl = utils.removeSchema(ddl, res.RELATED_OBJECTS, globalContext, schemaName, file, updatedFilename, undefined, true);
                    sanitizedDdl = cleanDdl(sanitizedDdl);
                    
                    var container = 'db';
                    
                    if (file.container === 'todo') {
                        container = 'todo';
                    }
                    
                    file.toBeCreated.push({
                        filename: newFilename,
                        update_content: sanitizedDdl,
                        file_container: container
                    });
                    cb();
                }
                ], 
                function(error) {
                    if(error){
                        return callback(error);
                    } else {
                        callback(null, file);
                    }
                }
            ); 
        }
    }
}

module.exports = new HdbtableHandler();
