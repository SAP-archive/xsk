/**
 * Created by SAP SE on 18.07.16.
 */

var async = require('async');
var iconv = require('iconv-lite');


class SqlParser{

    constructor(hdbClient){
        this.hdbClient = hdbClient;
    }


    getObjectsInDDLStatement(ddl, outerCallback){

        var sql = 'CALL SYS.GET_OBJECTS_IN_DDL_STATEMENT(?, ?, ?, ?)';
        var values = {};
        values['DDL'] = iconv.encode(ddl, 'cesu8');

        var sqlParserInstance = this;

        async.waterfall([
            function(callback){
                sqlParserInstance.hdbClient.prepare(sql, callback);
            },
            function(statement, callback){
                statement.exec(values, function onexec(error, parameters, errorRows, ddlPropertyRows, relatedObjectsRows){
                    statement.drop();
                    if(error) {
                        return callback(error);
                    }

                    if(errorRows.find(row => row.ERROR_TYPE === 'ERROR') !== undefined){
                        sqlParserInstance._logParserErrors(errorRows);
                        return callback(errorRows);
                    }

                    return callback(null, parameters, errorRows, ddlPropertyRows, relatedObjectsRows);

                });
            },
            function(parameters, errorRows,  ddlPropertyRows, relatedObjectsRows, callback){

                var result = {};
                result['RELATED_OBJECTS'] = relatedObjectsRows;
                result['DDL_PROPERTY'] = ddlPropertyRows;
                result['ERROR_INFO'] = errorRows;

                callback(null, result);
            }
        ], function(error, result){
            if(error === null){
                outerCallback(null, result);
            } else {
                outerCallback(error);
            }
        });

    }


    _logParserErrors(errorRows) {
        errorRows.forEach(errorRow => {
            console.error('\nParser Error: \n' + JSON.stringify(errorRow, null, 4) + '\n')
        });
    }

}

module.exports = SqlParser;
