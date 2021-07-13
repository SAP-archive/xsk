var log = require('../log-util');
var path = require('path');

var utils = require('./plugin-utils');
var constants = require('../constants');
var mc = require('../message-categories');
var msgs = require('../messages');

function PropertiesHandler() {

    function isUi5File(file) {
        var filename = utils.getFilename(file.RunLocation);
        for (var i=0; i < constants.UI5_PROPERTY_FILES.length; i++) {
            if (filename === constants.UI5_PROPERTY_FILES[i]) {
                return true;
            }
        }
        return false;
    }

    this.handleFile = function(file, globalContext, callback) {
        log.debug("Processing properties file " + file.RunLocation);
        if (isUi5File(file)) {
            log.debug("properties file " + file.RunLocation + " moved to web container");
            file.toBeCreated.push({
                filename: path.join(globalContext.pathMap["web-dev-object-folder"], file.RunLocation),
                content: file.content,
                file_container: 'web'
            });
        } else {
            // file type not recognized, assume it is a translation file
            log.debug("properties file " + file.RunLocation + " not recognized");
            file.toBeCreated.push({
                filename: path.join(globalContext.pathMap["todo-dev-object-folder"], file.RunLocation),
                content: file.content,
                file_container: 'todo'
            });
            var msg = msgs.getMessage(mc.TRANSLATION, 5);
            msg.message.push(utils.getFilename(file.RunLocation));
            msg.file = path.join(
                            globalContext.pathMap["todo-relative-path"],
                            file.RunLocation);
            utils.pushMessage(file, msg);
        }
        file.doNotWriteContent();
        callback(null, file);
    }
}

module.exports = new PropertiesHandler();
