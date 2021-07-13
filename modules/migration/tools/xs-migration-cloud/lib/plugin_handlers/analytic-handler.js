// --------------------------------------------------------------------------
//
// Handler for analytic views, attribute views, and analytic 
// privileges
// 
// it will add error messages to the report
//
// --------------------------------------------------------------------------

var path = require('path');
var utils = require('./plugin-utils');
var mc = require('../message-categories');
var msgs = require('../messages');
var logUtil = require('../log-util');

function AnalyticHandler() {

    this.handleFile = function (file, globalContext, callback) {

        logUtil.debug('AnalyticHandler.handleFile with file' + file.RunLocation);
        
        file.doNotWriteContent();
        
        var filename = utils.getFilename(file.RunLocation);
        var extName = path.extname(file.RunLocation);        
        var msg = msgs.getMessage(mc.VIEW_MIGRATION, 6);
        msg.message.push(filename, extName);
        msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
        
        callback(null, file);

    }
}

module.exports = new AnalyticHandler();
