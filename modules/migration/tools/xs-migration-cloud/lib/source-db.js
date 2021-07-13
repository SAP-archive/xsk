/**
 * Created by SAP SE on 11.07.16.
 */

var async = require('async');
var Table = require('./table');
var Column = require('./column');
var logUtil = require('./log-util');
var UsedObject = require('./used-object');
var migrationMessages = require('./messages');
var migrationMessageCategories = require('./message-categories');
var SqlSecurity = require('./sql-security');
var RuntimeObject = require('./runtime-object');
var pluginUtils = require('./plugin_handlers/plugin-utils');
var dbUtils = require('./db-utils');
var iconv = require('iconv-lite');
var TableType = require('./tabletype');

const PROCEDURE_TYPE = require('./procedure-type');


class SourceDb {

    constructor(hdbClient) {
        this.hdbClient = hdbClient;
    }


    getPublicSynonyms(globalContext, outerCallback) {

        var sql = "SELECT SYNONYM_NAME, OBJECT_SCHEMA, OBJECT_NAME, OBJECT_TYPE FROM SYS.SYNONYMS WHERE SCHEMA_NAME = 'PUBLIC' AND IS_VALID = 'TRUE'";

        this.hdbClient.exec(sql, function (error, resultArray) {
            if (error) {
                error.filename = 'Loading public synonyms from SYS.SYNONYMS';
                return outerCallback(error);
            }

            globalContext.publicSynonyms = resultArray.reduce(function (synonyms, synonym) {
                synonyms[synonym.SYNONYM_NAME] = synonym;
                return synonyms;
            }, {});

            logUtil.logProgress(1);
            return outerCallback();

        });

    }


    getDdl(schemaName, packageName, objectName, type, outerCallback) {

        var sql = 'CALL SYS.GET_OBJECT_DEFINITION(?, ?)';
        var completeName;

        if (packageName && type) {
            var delimiter = this._getDelimiter(type);
            completeName = '"' + packageName + delimiter + objectName + '"';
        } else {
            completeName = '"' + objectName + '"';
        }

        // escape schema name to avoid upper case conversion
        var escapedSchemaName = '"' + schemaName + '"';

        var values = {
            SCHEMA: escapedSchemaName,
            OBJECT: completeName
        };

        var sourceDbInstance = this;

        async.waterfall([
            function (callback) {
                sourceDbInstance.hdbClient.prepare(sql, callback);
            },
            function (statement, callback) {
                statement.exec(values, function onexec(error, parameters, rows) {
                    statement.drop();
                    if (error === null) {
                        callback(null, parameters, rows);
                    } else {
                        callback(error);
                    }
                });
            },
            function (parameters, rows, callback) {
                var cesu8String = rows[0].OBJECT_CREATION_STATEMENT;
                var utf8Buffer = iconv.decode(cesu8String, 'cesu8');

                callback(null, utf8Buffer.toString('utf-8'));
            }
        ], function (error, result) {
            if (error === null) {
                return outerCallback(null, result);
            } else {
                error.filename = 'Calling SYS.GET_OBJECT_DEFINITION';
                return outerCallback(error);
            }
        });

    }


    _getDelimiter(type) {
        if (type === 'procedure') {
            return '/';
        } else {
            return '::';
        }
    }


    _executePreparedSelectStatement(statement, values, callback) {

        statement.exec(values, function onexec(error, rows) {
            statement.drop();
            if (error === null) {
                callback(null, rows);
            } else {
                callback(error);
            }
        });

    }

    _convertArrayToObject(array) {
        var resultObject = array.reduce(function (object, value, iterator) {
            object[iterator] = value;
            return object;
        }, {});

        return resultObject;
    }


    _prepareAndExecuteStatement(sql, values, methodCallback){

        var sourceDbInstance = this;

        async.waterfall([
            function (callback) {
                sourceDbInstance.hdbClient.prepare(sql, callback);
            },
            function (statement, callback) {
                sourceDbInstance._executePreparedSelectStatement(statement, values, callback);
            }
        ], function(error, result){
            return methodCallback(error, result);
        });

    }


    getTableColumns(schema, table, outerCallback) {

        var sql = 'SELECT SCHEMA_NAME, TABLE_NAME, COLUMN_NAME, POSITION FROM SYS.TABLE_COLUMNS WHERE TABLE_NAME = ?';
        var values = [table];

        if (schema !== undefined) {
            sql += " AND SCHEMA_NAME = ?";
            values[1] = schema;
        }

        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            }
        ], function (error, result) {
            if (error === null) {
                var resultObject = sourceDbInstance._convertArrayToObject(result);
                return outerCallback(null, resultObject);
            } else {
                error.filename = 'Loading table columns from SYS.TABLE_COLUMNS';
                return outerCallback(error);
            }
        });

    }


    getTableTypesForProcedures(schemaName, procedureName, outerCallback) {

        var sql = "SELECT SCHEMA_NAME, TABLE_NAME from SYS.TABLES T, SYS.OBJECT_DEPENDENCIES D where D.dependent_schema_name = ? and D.dependent_object_name = ? and T.schema_name = D.base_schema_name and T.table_name = D.base_object_name and T.is_user_defined_type = 'TRUE' and T.table_name like ?";
        var tableTypePattern = procedureName + '/tabletype/%';
        var values = [schemaName, procedureName, tableTypePattern];

        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            }
        ], function (error, rows) {
            if (error === null) {
                var result = [];
                if(rows && rows.length > 0) {
                    rows.forEach( (row) => {
                        result.push(new TableType(row.SCHEMA_NAME, row.TABLE_NAME));
                    });
                }
                return outerCallback(null, result);
            } else {
                error.filename = 'Loading table types for a procedure from SYS.TABLES and SYS.OBJECT_DEPENDENCIES';
                return outerCallback(error);
            }
        });

    }


    getSchemaMapping(callback) {

        var sql = 'SELECT AUTHORING_SCHEMA, PHYSICAL_SCHEMA FROM _SYS_BI.M_SCHEMA_MAPPING';

        this.hdbClient.exec(sql, function (error, result) {
            if (error) {
                error.filename = 'Loading schema mapping from _SYS_BI.M_SCHEMA_MAPPING';
                return callback(error);
            }

            var schemaMapping = {
                schemaMapping: []
            };

            for (let schemaMappingEntry of result) {
	            schemaMapping.schemaMapping.push({
	                AUTHORING_SCHEMA: schemaMappingEntry.AUTHORING_SCHEMA,
	                PHYSICAL_SCHEMA: schemaMappingEntry.PHYSICAL_SCHEMA
	            });
            }


            return callback(null, schemaMapping);

        });

    }


    getSchemaTables(schema, outerCallback) {

        var sql = 'SELECT T.TABLE_NAME, T.IS_COLUMN_TABLE, T.TABLE_TYPE, T.IS_REPLICA, C.COLUMN_NAME, C."POSITION", C.DATA_TYPE_ID, C.DATA_TYPE_NAME, C.OFFSET, C."LENGTH", C.SCALE, C.IS_NULLABLE, C.DEFAULT_VALUE, C."COLLATION", C.COMMENTS, C.CS_DATA_TYPE_ID, C.CS_DATA_TYPE_NAME, C.DDIC_DATA_TYPE_ID, C.DDIC_DATA_TYPE_NAME, C.INDEX_TYPE, C.COLUMN_ID FROM SYS.TABLE_COLUMNS AS C INNER JOIN SYS.TABLES AS T ON T.TABLE_NAME = C.TABLE_NAME AND T.SCHEMA_NAME = C.SCHEMA_NAME WHERE T.SCHEMA_NAME = ?';
        var values = [schema];
        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (rows, callback) {
                var result = sourceDbInstance._convertRowsToHierarchy(rows);
                callback(null, result);
            }
        ], function (error, result) {
            if (error === null) {
                return outerCallback(null, result);
            } else {
                return outerCallback(error);
            }
        });

    }


    _convertRowsToHierarchy(rows) {

        var tables = new Map();

        rows.forEach(function (element) {
            var newTable = new Table(element.TABLE_NAME, element.IS_COLUMN_TABLE, element.TABLE_TYPE, element.IS_REPLICA);
            var column = new Column(element.COLUMN_NAME, element.POSITION, element.DATA_TYPE_ID, element.DATA_TYPE_NAME, element.OFFSET, element.LENGTH, element.SCALE, element.IS_NULLABLE, element.DEFAULT_VALUE, element.COLLATION, element.COMMENTS, element.CS_DATA_TYPE_ID, element.CS_DATA_TYPE_NAME, element.DDIC_DATA_TYPE_ID, element.DDIC_DATA_TYPE_NAME, element.INDEX_TYPE, element.COLUMN_ID.toString());

            if (!tables.has(newTable.name)) {
                newTable.addColumn(column);
                tables.set(newTable.name, newTable);
            } else {
                let table = tables.get(newTable.name);
                table.addColumn(column);
            }

        });

        var result = [];
        tables.forEach(function (element) {
            result.push(element);
        });

        return result;
    }


    getTranslationsForTextBundle(packageName, objectName, fileType, outerCallback) {

        var sql = "SELECT O.TEXT_ID, O.TEXT_TYPE, O.MAX_LENGTH, T.LANG, T.CONTENT FROM \"_SYS_REPO\".\"ACTIVE_CONTENT_TEXT\" AS O INNER JOIN \"_SYS_REPO\".\"ACTIVE_CONTENT_TEXT_CONTENT\" AS T ON O.TEXT_ID = T.TEXT_ID AND O.PACKAGE_ID = T.PACKAGE_ID AND O.OBJECT_NAME = T.OBJECT_NAME WHERE O.PACKAGE_ID = ? AND O.OBJECT_NAME = ? AND O.OBJECT_SUFFIX = ? AND LANG != '' ;";
        var values = [packageName, objectName, fileType];

        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                var convertedResult = [];
                result.forEach(function (textElement) {
                    convertedResult.push(sourceDbInstance._objectPropertyNamesToLowerCase(textElement));
                });

                callback(null, convertedResult);
            }
        ], function (error, result) {
            if (error === null) {
                return outerCallback(null, result);
            } else {
                error.filename = 'Loading translation texts from _SYS_REPO.ACTIVE_CONTENT_TEXT and _SYS_REPO.ACTIVE_CONTENT_TEXT_CONTENT';
                return outerCallback(error);
            }
        });

    }


    _objectPropertyNamesToLowerCase(object) {

        for (var attribute in object) {
            var convertedAttribute = attribute.toLowerCase();
            object[convertedAttribute] = object[attribute];
            delete object[attribute];
        }

        return object;
    }


    getObjectTranslation(system, packageId, objectName, objectSuffix, outerCallback) {

        var activeObjectSql = 'SELECT "T"."TEXT_ID", "T"."TEXT_TYPE", "T"."MAX_LENGTH", "C"."LANG", "C"."CONTENT" '
            + 'FROM "_SYS_REPO"."ACTIVE_OBJECT_TEXT" AS T INNER JOIN "_SYS_REPO"."ACTIVE_OBJECT_TEXT_CONTENT" AS C ON '
            + '"T"."PACKAGE_ID" = "C"."PACKAGE_ID" AND "T"."OBJECT_NAME" = "C"."OBJECT_NAME" AND "T"."OBJECT_SUFFIX" = "C"."OBJECT_SUFFIX" AND "T"."TEXT_ID" = "C"."TEXT_ID" '
            + 'WHERE "T"."PACKAGE_ID" = ? AND "T"."OBJECT_NAME" = ?';

        if(Array.isArray(objectSuffix)) {
            activeObjectSql += ' AND "T"."OBJECT_SUFFIX" IN (' + objectSuffix.map(function(suffix) {
                return "'" + suffix + "'";
            }).join(',') + ')';
        } else {
            activeObjectSql += ' AND "T"."OBJECT_SUFFIX" = ?';
        }


        var activeContentSql = 'SELECT "T"."TEXT_ID", "T"."TEXT_TYPE", "T"."MAX_LENGTH", "C"."LANG", "C"."CONTENT" '
            + 'FROM "_SYS_REPO"."ACTIVE_CONTENT_TEXT" AS T INNER JOIN "_SYS_REPO"."ACTIVE_CONTENT_TEXT_CONTENT" AS C ON '
            + '"T"."PACKAGE_ID" = "C"."PACKAGE_ID" AND "T"."OBJECT_NAME" = "C"."OBJECT_NAME" AND "T"."OBJECT_SUFFIX" = "C"."OBJECT_SUFFIX" AND "T"."TEXT_ID" = "C"."TEXT_ID" '
            + 'WHERE "T"."PACKAGE_ID" = ? AND "T"."OBJECT_NAME" = ?';


        if(Array.isArray(objectSuffix)) {
            activeContentSql += ' AND "T"."OBJECT_SUFFIX" IN (' + objectSuffix.map(function(suffix) {
                return "'" + suffix + "'";
            }).join(',') + ')';
        } else {
            activeContentSql += ' AND "T"."OBJECT_SUFFIX" = ?';
        }


        var result = {};

        var sourceDbInstance = this;

        async.parallel([
            function(callback){
                sourceDbInstance._getActiveTextContent(activeObjectSql, packageId, objectName, objectSuffix, callback);
            },
            function(callback){
                sourceDbInstance._getActiveTextContent(activeContentSql, packageId, objectName, objectSuffix, callback);
            }
        ],
            function (error, results) {
                if(error) {
                    error.filename = 'Loading translation texts from _SYS_REPO.ACTIVE_OBJECT_TEXT_CONTENT, _SYS_REPO.ACTIVE_OBJECT_TEXT, _SYS_REPO.ACTIVE_CONTENT_TEXT_CONTENT and _SYS_REPO.ACTIVE_CONTENT_TEXT';
                    return outerCallback(error);
                }

                result.object = results[0];
                result.content = results[1];
                outerCallback(null, result);

            }
        );

    }


    _getActiveTextContent(sql, packageId, objectName, objectSuffix, methodCallback) {

        var values = [packageId, objectName];

        if(objectSuffix !== null && !Array.isArray(objectSuffix)) {
            values.push(objectSuffix);
        }

        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function(result, callback){
                var resultObject = {};

                for(let i = 0; i < result.length; i++) {
                    var language = result[i].LANG;

                    if (language === '') {
                        language = 'default';
                    }


                    if (!resultObject.hasOwnProperty(language)) {
                        resultObject[language] = [];
                    }

                    delete result[i].LANG;
                    result[i].MAX_LENGTH = result[i].MAX_LENGTH.toString();

                    resultObject[language].push(result[i]);
                }
                callback(null, resultObject);
            }
        ], function (error, result) {

            methodCallback(error, result);
        });

    }


    getSystemInformation(callback) {

        var sql = 'SELECT SYSTEM_ID, VERSION FROM SYS.M_DATABASE';

        this.hdbClient.exec(sql, function (error, resultArray) {
            if(error){
                error.filename = 'Loading system information from SYS.M_DATABASE';
                return callback(error);
            }

            return callback(null, resultArray[0]);
        });
    }



    getSystemPrivileges(granteeName, methodCallback){

        var sql = "SELECT PRIVILEGE FROM SYS.GRANTED_PRIVILEGES WHERE OBJECT_TYPE = 'SYSTEMPRIVILEGE' AND GRANTEE = ?";
        var values = [granteeName];
        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                var systemPrivilegesList = [];

                result.forEach(function(systemPrivilege){
                   systemPrivilegesList.push(systemPrivilege.PRIVILEGE);
                });

                callback(null, systemPrivilegesList);
            }
        ], function (error, systemPrivilegesList){
            if(error) {
                error.filename = 'Loading system privileges from SYS.GRANTED_PRIVILEGES';
            }
            return methodCallback(error, systemPrivilegesList);
        });

    }



    getGlobalAndSchemaRoles(granteeName, globalContext, methodCallback){

        var globalAndSchemaRoles = {
            global_roles: [],
            schema_roles: [{
                names: []
            }]
        };

        var sourceDbInstance = this;

        this._getGrantedRoles(granteeName, function (error, grantedRoles) {
            if(error){
                return methodCallback(error);
            }

            grantedRoles.forEach(function(grantedRole){
                if(sourceDbInstance._isLocalRole(grantedRole, globalContext)) {
                    globalAndSchemaRoles.schema_roles[0].names.push(grantedRole);
                } else {
                    globalAndSchemaRoles.global_roles.push(grantedRole);
                }
            });


            return methodCallback(null, globalAndSchemaRoles);
        });

    }


    _isLocalRole(roleName, globalContext){
        if(globalContext.roles && globalContext.roles.hasOwnProperty(roleName)){
            return true;
        }
        return false;
    }


    _getGrantedRoles(granteeName, methodCallback){

        var sql = "SELECT ROLE_NAME FROM SYS.GRANTED_ROLES WHERE GRANTEE = ? AND GRANTEE_TYPE = 'ROLE'";
        var values = [granteeName];
        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                var grantedRoles = [];

                result.forEach(function(grantedRole){
                    grantedRoles.push(grantedRole.ROLE_NAME);
                });

                callback(null, grantedRoles);
            }
        ], function (error, grantedRoles){
            if(error) {
                error.filename = 'Loading granted roles from SYS.GRANTED_ROLES';
            }

            return methodCallback(error, grantedRoles);
        });

    }




    getSchemaPrivileges(granteeName, globalContext, methodCallback){

        var sourceDbInstance = this;

        this._getSchemaPrivilegeReferencesInRole(granteeName, function (error, schemaNames) {

            if (error) {
                return methodCallback(error);
            }

            async.map(schemaNames, function (schemaName, processingFinished) {
                sourceDbInstance._getPrivilegesForSchema(granteeName, schemaName, globalContext, processingFinished);

            }, function(error, schemaPrivileges){
                methodCallback(error, schemaPrivileges);
            });
        });

    }


    _getPrivilegesForSchema(granteeName, schemaName, globalContext,  methodCallback){

        var sql = "SELECT DISTINCT PRIVILEGE FROM SYS.GRANTED_PRIVILEGES WHERE GRANTEE = ? AND OBJECT_TYPE = 'SCHEMA' AND SCHEMA_NAME = ?";
        var values = [granteeName, schemaName];
        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                var schemaPrivileges = [];

                result.forEach(function(schemaPrivilege){
                    schemaPrivileges.push(schemaPrivilege.PRIVILEGE);
                });

                callback(null, schemaPrivileges);
            }
        ], function (error, schemaPrivileges){

            var schemaPrivilege = {};
            if(!SourceDb._schemaRefersToLocalContainer(schemaName, globalContext)) {
                globalContext.schemaPrivileges.add({schemaName: schemaName, schemaPrivileges: schemaPrivileges});
                schemaPrivilege.reference = schemaName;
            }
            schemaPrivilege.privileges = schemaPrivileges;

            if(error) {
                error.filename = 'Loading privileges from SYS.GRANTED_PRIVILEGES';
            }

            return methodCallback(error, schemaPrivilege);
        });

    }


    static _schemaRefersToLocalContainer(schemaName, globalContext){
        if(globalContext.applicationSchemas && globalContext.applicationSchemas.indexOf(schemaName) > -1){
            return true;
        }
        return false;
    }


    _getSchemaPrivilegeReferencesInRole(granteeName, methodCallback){

        var sql = "SELECT DISTINCT SCHEMA_NAME FROM SYS.GRANTED_PRIVILEGES WHERE GRANTEE = ? AND OBJECT_TYPE = 'SCHEMA'";
        var values = [granteeName];
        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                var schemaNames = [];

                result.forEach(function(schemaName){
                    schemaNames.push(schemaName.SCHEMA_NAME);
                });

                callback(null, schemaNames);
            }
        ], function (error, schemaNames){

            if(error) {
                error.filename = 'Loading schema privileges from SYS.GRANTED_PRIVILEGES';
            }

            return methodCallback(error, schemaNames);
        });


    }



    getObjectPrivileges(granteeName, globalContext, methodCallback){

        var sourceDbInstance = this;

        this._getObjectPrivilegesReferencesInRole(granteeName, function (error, objectReferences) {

            if (error) {
                return methodCallback(error);
            }

            async.map(objectReferences, function (objectReference, processingFinished) {
                sourceDbInstance._getPrivilegesForObject(granteeName, objectReference.schemaName, objectReference.objectName, objectReference.objectType, globalContext, processingFinished);

            }, function(error, objectPrivileges){
                methodCallback(error, objectPrivileges);
            });
        });
    }


    _getObjectPrivilegesReferencesInRole(granteeName, methodCallback){

        var sql = "SELECT DISTINCT SCHEMA_NAME, OBJECT_NAME, OBJECT_TYPE FROM SYS.GRANTED_PRIVILEGES WHERE GRANTEE = ? AND OBJECT_TYPE IN ('TABLE', 'SEQUENCE', 'VIEW', 'PROCEDURE', 'MONITORVIEW')";
        var values = [granteeName];
        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                var objectNames = [];

                result.forEach(function(objectName){

                    let objectReference = {
                        schemaName: objectName.SCHEMA_NAME,
                        objectName: objectName.OBJECT_NAME,
                        objectType: objectName.OBJECT_TYPE
                    };


                    objectNames.push(objectReference);
                });

                callback(null, objectNames);
            }
        ], function (error, objectNames){

            if(error) {
                error.filename = 'Loading object privileges from SYS.GRANTED_PRIVILEGES';
            }

            return methodCallback(error, objectNames);
        });
    }


    _getPrivilegesForObject(granteeName, schemaName, objectName, objectType, globalContext, methodCallback){

        var sql = "SELECT DISTINCT PRIVILEGE FROM SYS.GRANTED_PRIVILEGES WHERE GRANTEE = ? AND OBJECT_TYPE = ? AND SCHEMA_NAME = ? AND OBJECT_NAME = ?";
        var values = [granteeName, objectType, schemaName, objectName];
        var sourceDbInstance = this;

        var objectPrivileges = [];

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {

                result.forEach(function(objectPrivilege){
                    objectPrivileges.push(objectPrivilege.PRIVILEGE);
                });

                callback();
            },
            function (callback) {
                if(objectType === "PROCEDURE") {
                    sourceDbInstance._getProcedureSecurity(schemaName, objectName, callback);
                } else {
                    callback();
                }
            }
        ], function (error, sqlSecurity){

            if(schemaName === '_SYS_BIC') {
                objectName = pluginUtils.convertSlashName(objectName);
            }

            var objectPrivilege = {
                name: objectName,
                type: objectType,
                privileges: objectPrivileges
            };
            if(!SourceDb._schemaRefersToLocalContainer(schemaName, globalContext)) {
                let privilege = dbUtils.getPrivilegeForObjectType(objectType);

                let userName = schemaName + '.' + granteeName;
                let usedObject = new UsedObject(schemaName, objectName, [privilege], sqlSecurity);
                usedObject.addUserName(userName);

                pluginUtils.handleUsedObject(usedObject, globalContext);

            }

            if(error) {
                error.filename = 'Loading object privileges from SYS.GRANTED_PRIVILEGES';
            }

            return methodCallback(error, objectPrivilege);
        });

    }



    getSchemaAnalyticPrivileges(granteeName, globalContext, methodCallback){

        var sql = "SELECT OBJECT_NAME FROM SYS.GRANTED_PRIVILEGES WHERE OBJECT_TYPE = 'SQLANALYTICALPRIVILEGE' AND GRANTEE = ?";
        var values = [granteeName];

        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                var analyticPrivileges = [];
                var errorMessages = [];

                result.forEach(function(analyticPrivilege){
                    if(sourceDbInstance._analyticPrivilegeIsLocal(analyticPrivilege.OBJECT_NAME, globalContext)) {
                        let newPrivilegeName = analyticPrivilege.OBJECT_NAME.replace('/', '::');
                        analyticPrivileges.push(newPrivilegeName);
                    } else {
                        let message = migrationMessages.getMessage(migrationMessageCategories.SECURITY, 12);
                        errorMessages.push(message);
                    }

                });

                callback(null, analyticPrivileges, errorMessages);
            }
        ], function (error, analyticPrivileges, errorMessages){
            var containerLocalAnalyticPrivileges = {
                "privileges": analyticPrivileges
            };
            var privilegesAndMessages = {
                privileges: containerLocalAnalyticPrivileges,
                messages: errorMessages
            };

            if(error) {
                error.filename = 'Loading analytic privileges from SYS.GRANTED_PRIVILEGES';
            }

            return methodCallback(error, privilegesAndMessages);
        });

    }


    _analyticPrivilegeIsLocal(privilegeName, globalContext){
        if(globalContext.analyticPrivileges && globalContext.analyticPrivileges.indexOf(privilegeName) > -1){
            logUtil.trace('analytic privilege ' + privilegeName + ' is in current context ' + globalContext.analyticPrivileges);
            return true;
        }
        logUtil.trace('analytic privilege ' + privilegeName + ' is not in current context ' + globalContext.analyticPrivileges);
        return false;
    }


    getApplicationPrivileges(granteeName, methodCallback){

        var sql = "SELECT PRIVILEGE FROM SYS.GRANTED_PRIVILEGES WHERE OBJECT_TYPE = 'APPLICATIONPRIVILEGE' AND GRANTEE = ?";
        var values = [granteeName];

        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                var applicationPrivileges = [];

                result.forEach(function(applicationPrivilege){
                    let newApplicationPrivilege = applicationPrivilege.PRIVILEGE.replace('::', '.');
                    applicationPrivileges.push(newApplicationPrivilege);
                });

                callback(null, applicationPrivileges);
            }
        ], function (error, applicationPrivileges){

            if(error) {
                error.filename = 'Loading application privileges from SYS.GRANTED_PRIVILEGES';
            }

            methodCallback(error, applicationPrivileges);
        });

    }


    _getProcedureSecurity(schemaName, procedureName, methodCallback){

        var sql = 'SELECT SQL_SECURITY FROM SYS.PROCEDURES WHERE SCHEMA_NAME = ? AND PROCEDURE_NAME = ?';
        var values = [schemaName, procedureName];

        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                if(result && result.length > 0 && result[0].SQL_SECURITY) {
                    return callback(null, result[0].SQL_SECURITY);
                }
                callback();
            }
        ], function (error, sqlSecurityString){

            var errorToForward = error;

            // catch error in case SQL_SECURITY column does not exist (SP11)
            if (error && error.code == 260 || !sqlSecurityString) {
                sqlSecurityString = 'DEFINER';
                errorToForward = null;
            }

            var sqlSecurity;

            if(sqlSecurityString === 'INVOKER'){
                sqlSecurity = SqlSecurity.INVOKER;
            } else if(sqlSecurityString === 'DEFINER') {
                sqlSecurity = SqlSecurity.DEFINER;
            }

            if(errorToForward) {
                errorToForward.filename = 'Loading sql security mode for procedure from SYS.PROCEDURES';
            }

            methodCallback(errorToForward, sqlSecurity);
        });

    }


    getObjectPrivilegeType(schemaName, objectName, methodCallback) {

        var sql = "SELECT OBJECT_TYPE FROM SYS.OBJECTS WHERE SCHEMA_NAME = ? AND OBJECT_NAME = ?";
        var values = [schemaName, objectName];

        var sourceDbInstance = this;

        async.waterfall([
            function (callback){
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                var objectType = '';
                if(result.length > 0){
                    objectType = result[0].OBJECT_TYPE;
                }
                callback(null, objectType);
            }
        ], function (error, objectTypeString){

            let privilege = dbUtils.getPrivilegeForObjectType(objectTypeString);

            if(error) {
                error.filename = 'Loading object type from SYS.OBJECTS';
            }

            methodCallback(error, privilege);
        });

    }



    getRuntimeObjects(objectsList, methodCallback) {

        var sql = "SELECT cat.SCHEMA_NAME, repo.CATALOG_OBJECT_NAME, cat.OBJECT_TYPE, repo.REPOSITORY_PACKAGE_ID, repo.REPOSITORY_OBJECT_NAME, repo.REPOSITORY_OBJECT_TYPE " +
                    " FROM _SYS_REPO.CATALOG_OBJECTS_CREATED_BY_REPOSITORY_ACTIVATIONS AS repo " +
                    "INNER JOIN SYS.OBJECTS AS cat ON repo.CATALOG_SCHEMA_NAME = cat.SCHEMA_NAME AND repo.CATALOG_OBJECT_NAME = cat.OBJECT_NAME  " +
                    "WHERE repo.CATALOG_SCHEMA_NAME != 'PUBLIC'" +
                    "UNION " +
                    "SELECT cat.SCHEMA_NAME, repo.CATALOG_OBJECT_NAME, cat.OBJECT_TYPE, repo.REPOSITORY_PACKAGE_ID, repo.REPOSITORY_OBJECT_NAME, repo.REPOSITORY_OBJECT_TYPE " +
                    " FROM _SYS_REPO.CATALOG_OBJECTS_CREATED_BY_REPOSITORY_ACTIVATIONS AS repo " +
                    "INNER JOIN SYS.OBJECTS AS cat ON repo.CATALOG_SCHEMA_NAME = cat.SCHEMA_NAME AND (cat.OBJECT_NAME IS NULL AND repo.CATALOG_OBJECT_NAME = '') " +
                    "WHERE repo.CATALOG_SCHEMA_NAME != 'PUBLIC'";

        var sourceDbInstance = this;

        async.waterfall([
            function(callback) {
                sourceDbInstance.hdbClient.exec(sql, callback);
            },
            function(rows, callback) {
                logUtil.info('All runtime objects in system: ' + rows.length);
                var objectsListSet = new Set();
                var runtimeObjects = [];

                for(let object of objectsList) {
                    let key = object.packageId;
                    if(object.simpleName && object.simpleName.length > 0) {
                        key += object.simpleName;
                    }
                    key +=  object.suffix;
                    objectsListSet.add(key);
                }

                logUtil.info('Designtime objects to check: ' + objectsListSet.size);

                for(let row of rows) {
                    let key = row.REPOSITORY_PACKAGE_ID;
                    if(row.REPOSITORY_OBJECT_NAME && row.REPOSITORY_OBJECT_NAME.length > 0) {
                        key += row.REPOSITORY_OBJECT_NAME;
                    }
                    key += row.REPOSITORY_OBJECT_TYPE;

                    if(objectsListSet.has(key)) {
                        let runtimeObject = new RuntimeObject(row.SCHEMA_NAME, row.CATALOG_OBJECT_NAME, row.OBJECT_TYPE);
                        runtimeObjects.push(runtimeObject);
                    }
                }

                logUtil.info('Found runtime objects for designtime objects: ' + runtimeObjects.length);
                callback(null, runtimeObjects);

            }
        ], function(error, runtimeObjects){

            if(error) {
                error.filename = 'Loading runtime objects from _SYS_REPO.CATALOG_OBJECTS_CREATED_BY_REPOSITORY_ACTIVATIONS';
            }

            return methodCallback(error, runtimeObjects);
        });

    }



    getCdsRuntimeObjects(cdsObjects, methodCallback) {

        var sourceDbInstance = this;

        async.map(cdsObjects, function (cdsObjectName, callback) {

            sourceDbInstance._getCdsRuntimeObjectsForCdsObject(cdsObjectName, callback);

        }, function (error, cdsRuntimeObjects) {

            var flatCdsRuntimeObjects = [].concat.apply([], cdsRuntimeObjects);
            return methodCallback(error, flatCdsRuntimeObjects);
        });

    }



    _getCdsRuntimeObjectsForCdsObject(cdsObjectName, methodCallback) {

        var sourceDbInstance = this;

        async.parallel({

            artifacts: function (callback) {
                sourceDbInstance._getCdsArtifacts(cdsObjectName, callback);
            },
            schema: function (callback) {
                sourceDbInstance._getCdsSchemaName(cdsObjectName, callback);
            }
        }, function (error, results) {

            if(error) {
                return methodCallback(error);
            }

            var runtimeObjects = [];

            if(!results.schema) {
                return methodCallback(null, runtimeObjects);
            }

            for(let artifact of results.artifacts) {
                let runtimeObject = new RuntimeObject(results.schema, artifact.name, artifact.kind);
                runtimeObjects.push(runtimeObject);
            }

            return methodCallback(error, runtimeObjects);


        });

    }



    _getCdsSchemaName(cdsObjectName, methodCallback) {

        var sql = "SELECT \"valueTree\" FROM _SYS_RT.CDS_ANNOTATION_VALUE WHERE \"annotationName.name\" = 'sap.cds::Schema' AND \"artifactName.name\" = ?";
        var values = [cdsObjectName];

        var sourceDbInstance = this;

        async.waterfall([
            function (callback) {
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (rows, callback) {
                if(rows.length > 0 && rows[0].valueTree) {

                    let rawSchemaName = rows[0].valueTree.toString('utf-8');
                    let schemaNameObject = JSON.parse(rawSchemaName);

                    let schemaName = schemaNameObject[Object.keys(schemaNameObject)[0]];

                    return callback(null, schemaName);
                }
                return callback();
            }
        ], function (error, schemaName) {
            if(error) {
                error.filename = 'Loading cds schema annotation from _SYS_RT.CDS_ANNOTATION_VALUE';
            }

            return methodCallback(error, schemaName);

        });


    }



    _getCdsArtifacts(artifactName, methodCallback) {

        var sql = "SELECT \"name\", \"kind\" FROM _SYS_RT.CDS_ARTIFACT WHERE \"name\" LIKE ?";
        var values = [artifactName + '%'];

        var sourceDbInstance = this;


        async.waterfall([
            function (callback) {
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (rows, callback) {
                var cdsArtifacts = [];

                for(let row of rows) {
                    let cdsArtifact = {name: null, kind: null};

                    if(row.name && row.name.length > 0) {
                        cdsArtifact.name = row.name;
                    }

                    if(row.kind && row.kind.length > 0) {
                        cdsArtifact.kind = row.kind;
                    }

                    cdsArtifacts.push(cdsArtifact);
                }

                return callback(null, cdsArtifacts);
            }
        ], function (error, cdsArtifacts) {
            if(error) {
                error.filename = 'Loading cds artifacts from _SYS_RT.CDS_ARTIFACT';
            }

            return methodCallback(error, cdsArtifacts);
        });

    }



    getActiveTags(repositoryPackageId, repositoryObjectName, repositoryObjectSuffix, methodCallback) {

        var sql = "SELECT TAG, VALUE FROM _SYS_REPO.ACTIVE_TAGS WHERE PACKAGE_ID = ? AND OBJECT_NAME = ? AND OBJECT_SUFFIX = ?";
        var values = [repositoryPackageId, repositoryObjectName, repositoryObjectSuffix];

        var sourceDbInstance = this;

        async.waterfall([
           function(callback) {
               sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
           }
        ], function (error, result) {

            if(error) {
                error.filename = 'Loading active tags from _SYS_REPO.ACTIVE_TAGS';
            }

            return methodCallback(error, result);
        });

    }



    isReadOnlyProcedure(schemaName, procedureName, methodCallback) {

        var sql = "SELECT READ_ONLY FROM SYS.PROCEDURES WHERE SCHEMA_NAME = ? AND PROCEDURE_NAME = ?";
        var values = [schemaName, procedureName];

        var sourceDbInstance = this;

        async.waterfall([
            function (callback) {
               sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                if(result.length > 0 && result[0].READ_ONLY === 'FALSE'){
                    return callback(null, false);
                }
                return callback(null, true);
            }
        ], function (error, isReadOnly) {

            if(error) {
                error.filename = 'Loading procedure type from SYS.PROCEDURES';
            }

            return methodCallback(error, isReadOnly);
        });
    }



    getProcedureType(schemaName, procedureName, methodCallback) {

        var sql = 'SELECT PROCEDURE_TYPE FROM SYS.PROCEDURES WHERE SCHEMA_NAME = ? AND PROCEDURE_NAME = ?';
        var values = [schemaName, procedureName];

        var sourceDbInstance = this;

        async.waterfall([
            function (callback) {
                sourceDbInstance._prepareAndExecuteStatement(sql, values, callback);
            },
            function (result, callback) {
                var procedureType = PROCEDURE_TYPE.SQLSCRIPT;

                if(result.length > 0 && result[0].PROCEDURE_TYPE) {
                    switch (result[0].PROCEDURE_TYPE) {
                        case 'R':
                            procedureType = PROCEDURE_TYPE.R;
                            break;
                        case 'L':
                            procedureType = PROCEDURE_TYPE.L;
                            break;
                        default:
                            break;
                    }
                }
                callback(null, procedureType);
            }
        ], function (error, procedureType) {

            if(error) {
                error.filename = 'Loading procedure language from SYS.PROCEDURES';
            }

            return methodCallback(error, procedureType);
        });

    }



    getSysRepoObjects(methodCallback) {

        var sql = "SELECT SCHEMA_NAME, OBJECT_NAME FROM SYS.OWNERSHIP WHERE OWNER_NAME = '_SYS_REPO'";

        this.hdbClient.exec(sql, function (error, resultArray) {
            if (error) {
                error.filename = 'Loading ownership information from SYS.OWNERSHIP';
                return methodCallback(error);
            }

            var sysRepoObjects = new Set();

            for(let object of resultArray) {
                let identifier = '';

                if(object.SCHEMA_NAME) {
                    identifier += object.SCHEMA_NAME + '.';
                }

                identifier += object.OBJECT_NAME;

                sysRepoObjects.add(identifier);
            }

            return methodCallback(null, sysRepoObjects);

        });

    }

}


module.exports = SourceDb;
