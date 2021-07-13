// --------------------------------------------------------------------------
//
// Migrate hdbtable objects to their XS Advanced counterparts
//
// Handles indexes and constraints as well
//
// --------------------------------------------------------------------------

var path = require('path');
var async = require('async');

var utils = require('./plugin-utils');
var logUtil = require('../log-util');
var mc = require('../message-categories');
var msgs = require('../messages');
var RepositoryObject = require('../hana-repository/repository-object');
var TableObject = require('../table-object');

const SCHEMA_NAME_REGEX_1 = /^(?:[^\/]{2})?\s*table\.schemaName\s*=\s*["|']?(\w+)["|']?\s*;/m;
const COMMENT_PREFIX  = /^COMMENT\sON/;
const INDEX    = /^(CREATE\s(?:UNIQUE\s)?INDEX\s)"((\w|\.|::)+)"/;
const FULLTEXT_INDEX  = /^(CREATE\sFULLTEXT\sINDEX\s)"((\w|\.|::)+)"/;
const ALTER_TABLE_CONCAT_ATTRIBUTE = /^ALTER\sTABLE.+'CONCAT_ATTRIBUTE'/;
const ALTER_TABLE_FOREIGN_KEY = /^ALTER\sTABLE.+ADD FOREIGN KEY/;
const GLOBAL_TEMPORARY_TABLE = /\s*GLOBAL\s+TEMPORARY\s+(COLUMN|ROW)?\s*TABLE.*/;

const HDB_DROPCREATE_TABLE_SUFFIX = 'hdbdropcreatetable';


class HdbtableHandler {


    static _checkMultiLine(context, file, tableDdl) {
        var lines = tableDdl.match(/[^\r\n]+/g);
        if (lines.length > 1) {
            var msg = msgs.getMessage(mc.HDI, 2);
            msg.file = path.join(context.pathMap['xsjs-relative-path'], file.RunLocation);
            msg.loc =
            {
                "start": {
                    "line": 2,
                    "column": 1
                },
                "end": {
                    "line": lines.length,
                    "column": 1
                }
            };
            utils.pushMessage(file, msg);
        }
    }


    static _cleanDdl(tableDdl, schemaName) {

        var mDdl = tableDdl.replace(/^CREATE\s/,"");
        var schemaRemove = "\"" + schemaName + "\"\.";
        var schemaRegex = new RegExp(schemaRemove);
        mDdl = mDdl.replace(schemaRegex, "");
        return mDdl;
    }


    static _convertForeignKeyConstraints(content, fileName){
        var tmpConstraints = content.split("ALTER ");
        var constraints = [];
        for(let i = 0; i < tmpConstraints.length; i++){
            if(tmpConstraints[i] && tmpConstraints[i].trim() != ''){
                var constraintName = "CON_" +  fileName.substring(0, fileName.length - path.extname(fileName).length)+ "_" + i + ".hdbconstraint";
                constraints.push({name: constraintName, content: "ALTER " + tmpConstraints[i]});
            }
        }
        for(let i = 0; i < constraints.length; i++){
            constraints[i].content = constraints[i].content.replace(/ALTER TABLE/g, ("CONSTRAINT " + constraints[i].name + " ON"));
            constraints[i].content = constraints[i].content.replace(/ADD FOREIGN KEY/g, "FOREIGN KEY");
        }
        logUtil.debug("length: " + constraints.length);
        return constraints;
    }


    _getSchemaName(hdbtable) {
        var m1 = hdbtable.match(SCHEMA_NAME_REGEX_1);
        if (m1) {
            return m1[1];
        }

        return undefined;
    }


    _createIndex(globalContext, sqlParser, pattern, file, ddl, ext, callback) {

        var m = ddl.match(pattern);
        var indexName = m[2];

        var packageId;
        var colonIndex = indexName.indexOf('::');

        if(colonIndex > 0) {
            packageId = indexName.substring(0, colonIndex);
            indexName = indexName.substring(colonIndex+2);
        }

        var indexNamespace = utils.calculateNamespace(globalContext.rootHdiNamespace, file.packageId, packageId);

        var newIndex = new RepositoryObject(indexName, indexNamespace, ext);
        newIndex.content = ddl;

        this._handleSubArtifact(sqlParser, newIndex, globalContext, function (error, newArtifact) {
            if(error) {
                return callback(error);
            }

            file.toBeCreated.push(newArtifact);
            return callback();
        });

    }


    _handleSubArtifact(sqlParser, artifact, globalContext, callback) {
        sqlParser.getObjectsInDDLStatement(artifact.content, function (error, objectsInDdl) {

            if(error) {
                return callback(error);
            }

            var cleanedDdl = utils.removeSchema(artifact.content, objectsInDdl.RELATED_OBJECTS, globalContext, "", artifact, artifact.TargetFileName, undefined, false);
            var newFilename = path.join(globalContext.pathMap["db-dev-object-folder"], artifact.TargetFileName);

            var endBefore = objectsInDdl.DDL_PROPERTY[0].OBJECT_START_POSITION;
            var beginAfter = objectsInDdl.DDL_PROPERTY[0].OBJECT_END_POSITION - 2;

            var before = cleanedDdl.substring("CREATE ".length, endBefore);
            var after = cleanedDdl.substring(beginAfter);

            cleanedDdl = before + artifact.fullName + after;

            var newArtifact = {
                filename: newFilename,
                update_content: cleanedDdl,
                file_container: 'db'
            };

            callback(null, newArtifact);
        });

    }


    _isGlobalTemporaryTable(ddl) {
        if(ddl.match(GLOBAL_TEMPORARY_TABLE)) {
            return true;
        }
        return false;
    }


    _ddlSplitter(file, globalContext, ddl, sqlParser, methodCallback) {

        var lines = ddl.split(";\n");
        var tableDdlParts = [];
        var commentWarning = false;

        var _this = this;

        async.each(lines, function (line, callback) {
            if(line.match(COMMENT_PREFIX)) {
                commentWarning = true;

                return callback();

            } else if(line.match(INDEX)) {
                _this._createIndex(globalContext, sqlParser, INDEX, file, line, 'hdbindex', callback);

            } else if(line.match(FULLTEXT_INDEX)) {

                _this._createIndex(globalContext, sqlParser, FULLTEXT_INDEX, file, line, 'hdbfulltextindex', callback);

            } else if (line.match(ALTER_TABLE_CONCAT_ATTRIBUTE)) {

                let msg = msgs.getMessage(mc.HDI, 1);
                msg.file = path.join(globalContext.pathMap['db-relative-path'], file.RunLocation);
                utils.pushMessage(file, msg);
                return callback();

            } else if(line.match(ALTER_TABLE_FOREIGN_KEY)){

                var temp = HdbtableHandler._convertForeignKeyConstraints(line, file.Name);
                for(var fk_i = 0; fk_i <  temp.length; fk_i++){
                    file.toBeCreated.push({
                        filename: path.join(
                            globalContext.pathMap["db-dev-object-folder"],
                            utils.getFilepath(file.RunLocation),
                            "CON_" + utils.getObjectname(file.RunLocation) + "_" + fk_i + ".hdbconstraint"),
                        content: temp[fk_i].content,
                        file_container: 'db'
                    });
                }
                return callback();
            }
            else {
                tableDdlParts.push(line);
                return callback();
            }

        }, function(error){

            if(error) {
                return methodCallback(error);
            }

            if(commentWarning) {
                let msg = msgs.getMessage(mc.HDI, 3);
                msg.file = path.join(globalContext.pathMap['db-relative-path'], file.RunLocation);
                utils.pushMessage(file, msg);
            }

            var tableDdl = tableDdlParts.join('; \n;');

            HdbtableHandler._checkMultiLine(globalContext, file, tableDdl);

            if(_this._isGlobalTemporaryTable(tableDdl)) {
                file.suffix = HDB_DROPCREATE_TABLE_SUFFIX;
            }

            let newFilename = path.join(globalContext.pathMap["db-dev-object-folder"], file.TargetFileName);
            let newArtifact =  {
                filename: newFilename,
                update_content: tableDdl,
                file_container: 'db'
            };

            file.toBeCreated.push(newArtifact);

            return methodCallback();
        });


    }


    handleFile (file, globalContext, callback, sourceDb, sqlParser) {

        var schemaName = this._getSchemaName(file.content.toString('utf8'));

        if (schemaName === undefined) {
            let error = 'Unable to get schema name for object ' + file.RunLocation;
            return callback(error);
        }

        file.doNotWriteContent();
        var _this = this;

        async.waterfall([
            function (callback) {
                sourceDb.getDdl(schemaName, file.packageId, file.simpleName, 'hdbtable', callback);
            },
            function (ddl, callback) {
                ddl = HdbtableHandler._cleanDdl(ddl, schemaName);
                _this._ddlSplitter(file, globalContext, ddl, sqlParser, callback);
            },
            function (callback) {
                let tableObject = new TableObject(schemaName, file.fullName);
                globalContext.tableObjects.push(tableObject);
                callback();
            }

        ], function (error) {
            return callback(error, file);
        });


    }
}

module.exports = new HdbtableHandler();
