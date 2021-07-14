var logUtil = require('../log-util');
var mc = require('../message-categories');
var msgs = require('../messages');
var utils = require('./plugin-utils');
var path = require('path');

function DeleteFileHandler() {

    this.handleFile = function(file, globalContext, callback) {

        if(file.suffix === 'xsapp'){
            let rootPath = path.dirname(file.RunLocation);
            logUtil.debug('Root folder of project detected: ' + rootPath);
            globalContext.app_root_path = rootPath;
        }
        
    	logUtil.debug("delete handler");
    	
        file.doNotWriteContent();
        
        var msg = msgs.getMessage(mc.DELETE, 1);
        msg.message.push(utils.getFilename(file.RunLocation));
        msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
    	utils.pushMessage(file, msg);
        callback(null, file);
    }
}

module.exports = new DeleteFileHandler();
