// --------------------------------------------------------------------------
//
// Figure out that .theming is really UI5
//
// --------------------------------------------------------------------------

var path = require('path');
var utils = require('./plugin-utils');

var mc = require('../message-categories');
var msgs = require('../messages');

function HdbthemingHandler() {

    this.handleFile = function(file, globalContext, callback) {

        var copyToWeb = false;
        try {
            var th = JSON.parse(file.content.toString('utf8'));
            if ("sEntity" in th) {
                copyToWeb = true;
            }
        } catch (e) {
            // does not appear to be a JSON file, don't copy to
            // web
        }

        if (copyToWeb) {
            file.toBeCreated.push({
                filename: path.join(globalContext.pathMap["web-dev-object-folder"], file.RunLocation),
                update_content: file.content,
                file_container: 'web'
            });            
        } else {
            var msg = msgs.getMessage(mc.TODO, 3);
            msg.file = path.join(globalContext.pathMap["todo-dev-object-folder"], file.RunLocation)
            utils.pushMessage(file, msg);
            file.toBeCreated.push({
                filename: path.join(globalContext.pathMap["todo-dev-object-folder"], file.RunLocation),
                update_content: file.content,
                file_container: 'todo'
            });
        }
        file.doNotWriteContent();
        callback(null, file);
    }
}

module.exports = new HdbthemingHandler();
