// --------------------------------------------------------------------------
//
// Plugin handler for hdbschema files
//
// So far it only stores the schema names in the global context.
//
// --------------------------------------------------------------------------

var path = require('path');

var utils = require('./plugin-utils');
var mc = require('../message-categories');
var msgs = require('../messages');
var logUtil = require('../log-util');
var informationHandler = require('../information-handler');


var HDBSCHEMA_REGEX_1 = /schema_name\s*=\s*"(\w*)";/;
var HDBSCHEMA_REGEX_2 = /schema_name\s*=\s*(\w*);/;

var SCHEMA_REGEX_1 = /schemaname\s*=\s*"(\w*)"/;
var SCHEMA_REGEX_2 = /schemaname\s*=\s*(\w*)/;

var HDBSCHEMA_EXT = 'hdbschema';
var SCHEMA_EXT    = 'schema';  // old format

function HdbschemaHandler() {

    
    this.preprocessing = function (file, globalContext, callback) {
        logUtil.info('Collecting schemas in migration context');

        var fileContents = file.content.toString('utf8');
        file.doNotWriteContent();
        var ext = utils.getExtension(file.RunLocation);
        var schemaType = '';
        
        if(ext === HDBSCHEMA_EXT) {
            schemaType = HDBSCHEMA_EXT;
            let match = fileContents.match(HDBSCHEMA_REGEX_1);
            if (!match) {
                match = fileContents.match(HDBSCHEMA_REGEX_2);
            }
            if (!match || match.length != 2) {
                utils.pushSchemaNameError(file, globalContext);
            } else {
                globalContext.applicationSchemas.push(match[1]);
                file.toBeDeleted = true;
            }
        } else if(ext === SCHEMA_EXT) {
            schemaType = SCHEMA_EXT;
            let match = fileContents.match(SCHEMA_REGEX_1);
            if (!match) {
                match = fileContents.match(SCHEMA_REGEX_2);
            }
            if (!match || match.length != 2) {
                utils.pushSchemaNameError(file, globalContext);
            } else {
                globalContext.applicationSchemas.push(match[1]);
                file.toBeDeleted = true;
            }
        } else {
            return callback("Unknown file type for hdbschema: " + schemaType);
        }
        
        file.schemaType = schemaType;
        
        callback();
    };    
    
    
    this.handleFile = function (file, globalContext, callback) {
        
        if(file.toBeDeleted == true){
            var msg = msgs.getMessage(mc.DELETE, 1);
            msg.message.push("." + file.schemaType);
            msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
        	informationHandler.logMessage(msg);
        }
        callback(null, file);
    }
}

module.exports = new HdbschemaHandler();
