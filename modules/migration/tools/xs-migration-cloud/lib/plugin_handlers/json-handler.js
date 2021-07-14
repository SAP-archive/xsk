var log = require('../log-util');
var path = require('path');

var utils = require('./plugin-utils');
var constants = require('../constants');
var mc = require('../message-categories');
var msgs = require('../messages');
var todo = require('../todo-generator');
var web = require('../web-generator');

function JsonHandler() {

    var WEB_FILENAMES = {
        'library.dependencies.json': true,
        'library.parameters.json': true

    }
    var VIEW_IDENTIFIER = 'controllerName';

    function isLibraryDependenciesFile(file) {
        var filename = utils.getFilename(file.RunLocation);
        return filename in WEB_FILENAMES;
    }

    function isView(contents) {
        return contents.search(VIEW_IDENTIFIER) != -1;
    }

    function isUi5File(file) {
        var filename = utils.getFilename(file.RunLocation);
        for (var i=0; i < constants.UI5_JSON_FILES.length; i++) {
            if (filename === constants.UI5_JSON_FILES[i]) {
                return true;
            }
        }
        return false;
    }

    this.handleFile = function(file, globalContext, callback) {
        log.debug("Processing json file " + file.RunLocation)
        var fileContent = file.content.toString('utf8');
        if (isView(fileContent)
            || isLibraryDependenciesFile(file)
            || isUi5File(file)) {
            log.debug("json file " + file.RunLocation + " moved to web container");
            file.toBeCreated.push({
                filename: path.join(web.properties.devObjectFolder, file.RunLocation),
                content: file.content,
                file_container: 'web'
            });
        } else {
            log.debug("json file " + file.RunLocation + " not recognized");
            file.toBeCreated.push({
                filename: path.join(todo.properties.devObjectFolder, file.RunLocation),
                content: file.content,
                file_container: 'todo'
            });
            var msg = msgs.getMessage(mc.TODO, 1);
            msg.file = path.join(globalContext.pathMap['todo-relative-path'], file.RunLocation);
            utils.pushMessage(file, msg);
        }
        file.doNotWriteContent();
        callback(null, file);
    }
}

module.exports = new JsonHandler();
