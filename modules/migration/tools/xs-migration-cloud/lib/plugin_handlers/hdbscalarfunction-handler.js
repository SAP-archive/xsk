// --------------------------------------------------------------------------
//
// handler for hdbtablefunction and hdbscalarfunction
//
// --------------------------------------------------------------------------


var log = require('../log-util');
var SqlScriptHandler = require('./sqlscript-handler');
var SqlscriptType = require('./sqlscript-type');


const NEW_HDBTABLEFUNCTION_EXT = "hdbfunction";


class HdbFunctionHandler {

        
  handleFile(file, globalContext, callback, sourceDb, sqlParser) {
        
        var contentString = file.content.toString('utf-8');

        var schemaName = SqlScriptHandler.getSchemaName(contentString, SqlscriptType.FUNCTION);

        log.debug("Converting file " + file.RunLocation + ", schema: " + schemaName + ", package: " + file.packageId + ", object: " + file.simpleName);


        SqlScriptHandler.handleFile(file, schemaName, sourceDb, sqlParser, globalContext, NEW_HDBTABLEFUNCTION_EXT, true, function (error, file) {
            return callback(error, file);
        });

    }
    
}

module.exports = new HdbFunctionHandler();
