// --------------------------------------------------------------------------
// 
// Migration of hdbdd to hdbcds files
//
// --------------------------------------------------------------------------

var path = require('path');

// var converttohdbcds = require('hdbdd-to-hdbcds');

var log = require('../log-util');
var utils = require('./plugin-utils');
var mc = require('../message-categories');
var msgs = require('../messages');

var NEW_EXTENSION = "hdbcds";


function HdbddHandler() {

    var _that = this;

    function convertHdbdd(source, file, globalContext, callback) {

        try {
            var converter = new converttohdbcds.Converter(converttohdbcds.resources_dir);

            var result = converter.convert(source);
            log.trace("CDS Migration result: " + JSON.stringify(result, null, 4));

            result.messages.forEach(function(tmsg) {
                var msg = _that.createMessage(tmsg, file, globalContext);
                if (msg) {
                    utils.pushMessage(file, msg);
                }
            });
            callback(null, result.hdbcdscontent);
        } catch (e) {
            var msg = msgs.getMessage(mc.HDI, 14);
            msg.message.push(e.message);
            msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
            utils.pushMessage(file, msg);
            callback("Migration failed");
        }
    }

    this.createMessage = function(tmsg, file, context) {
        if (tmsg.type === mc.INFO) {
            // not important
            log.debug(tmsg.message);
        } else if (tmsg.type === mc.WARNING) {
            var msg = msgs.getMessage(mc.HDI, 15);
            msg.file = path.join(context.config.directories['xs1-src'], file.RunLocation);
            msg.message = tmsg.message;
            return msg;
        } else {
            var msg = msgs.getMessage(mc.HDI, 6);
            msg.file = path.join(context.config.directories['xs1-src'], file.RunLocation);
            msg.message = tmsg.message;
            return msg;
        }
        return; // undefined
    }

    this.handleFile = function(file, globalContext, callback) {

        var newHdbdd = file.content.toString('utf8');
        var newFilename = path.join(
                                globalContext.pathMap["db-dev-object-folder"],
                                utils.getFilepath(file.RunLocation),
                                utils.getObjectname(file.RunLocation) + "." + NEW_EXTENSION);

        file.doNotWriteContent();
        convertHdbdd(newHdbdd, file, globalContext, function(error, hdbCds) {
            if (!error) {
                file.toBeCreated.push({
                    filename: newFilename,
                    update_content: hdbCds,
                    file_container: 'db'
                });
            }
            callback(null, file);
        });
    }
}

module.exports = new HdbddHandler();
