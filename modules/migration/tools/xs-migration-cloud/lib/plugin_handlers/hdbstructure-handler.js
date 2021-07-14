
var path = require('path');

var utils = require('./plugin-utils');
var RuntimeObject = require('../runtime-object');

var SCHEMA_NAME_REGEX_1 = /table\.schemaName\s*=\s*"(\w+)";/;
var SCHEMA_NAME_REGEX_2 = /table\.schemaName\s*=\s*'(\w+)';/;
var SCHEMA_NAME_REGEX_3 = /table\.schemaName\s*=\s*(\w+);/;

var HDBSTRUCTURE_NAME_REGEX = /([^\/]+).hdbstructure$/;

var TABLE_TYPE_NAME_PATTERN_PRESERVE_QUOTES = new RegExp('(CREATE)\\s+(TYPE)\\s*"([\\w]+)"\\s*\\.\\s*("?[\\w\\./:-]+"?")', 'i');

function HdbstructureHandler() {

    var that = this;
    function getSchemaName(hdbtable) {
        var m1 = hdbtable.match(SCHEMA_NAME_REGEX_1);
        if (m1) {
            return m1[1];
        }
        var m2 = hdbtable.match(SCHEMA_NAME_REGEX_2);
        if (m2) {
            return m2[1];
        }
        var m3 = hdbtable.match(SCHEMA_NAME_REGEX_3);
        if (m3) {
            return m3[1];
        }
        return undefined;
    }

     this.cleanDdl = function(tableDdl) {

        function tableTypeNameReplacer(match, p1, p2, schemaName, tableTypeName) {
            tableTypeName = utils.getDoubleColonName(tableTypeName);
            return p2 + " " + tableTypeName;
        }

        return tableDdl.replace(TABLE_TYPE_NAME_PATTERN_PRESERVE_QUOTES, tableTypeNameReplacer);

    };

    this.handleTableTypeFile = function(globalContext, file, ddlName, ddlContents, callback, sourceDb) {

        var structureName = ddlName.match(HDBSTRUCTURE_NAME_REGEX)[1];
        var packageName = ddlName.substring(1, ddlName.length - structureName.length - "hdbstructure".length-2);
        packageName = packageName.replace(/\//g,".");
        var schemaName = getSchemaName(ddlContents);

        var newStructureName = utils.getDoubleColonName(structureName);

        if (schemaName === undefined) {
            utils.pushSchemaNameError(file, globalContext);
            callback(null, file);
        } else {
            sourceDb.getDdl(schemaName, packageName, structureName, "hdbstructure", function(error, ddl) {
                if (error) {
                    utils.pushGetDDLError(file, globalContext, schemaName, error);
                    callback(null, file);
                } else {
                    // mapping from other schema to local schema
                    //
                    var newRuntimeObject = new RuntimeObject(schemaName, newStructureName);
                    globalContext.contextObjects.add(newRuntimeObject);
                    
                    var newName = path.join(
                                      utils.getFilepath(ddlName),
                                      utils.getObjectname(ddlName) + ".hdbtabletype"
                                      );
                    ddl = that.cleanDdl(ddl);
                    file.toBeCreated.push({
                        filename: path.join(globalContext.pathMap["db-dev-object-folder"], newName),
                        update_content: ddl,
                        file_container: 'db'
                    });
                }
                callback(null, file);
            });
        }
    };

    this.handleFile = function(file, globalContext, callback, sourceDb) {

        var ddlContent = file.content.toString('utf8');
        file.doNotWriteContent();
        this.handleTableTypeFile(globalContext, file, file.RunLocation, ddlContent, callback, sourceDb);
    }
}

module.exports = new HdbstructureHandler();