
var utils = require('./plugin-utils');
var mc = require('../message-categories');
var msgs = require('../messages');
var path = require('path');

function UnsupportedHandler() {

    this.handleFile = function(file, globalContext, callback) {

        file.doNotWriteContent();
        var ext = utils.getExtension(file.RunLocation);

        switch(ext) {
            case 'aflpmml':
                var msg = msgs.getMessage(mc.VIEW_MIGRATION, 2);
                msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
                utils.pushMessage(file, msg);
                break;

            case 'xswidget':
            case 'xsappsite':
                var msg = msgs.getMessage(mc.UNSUPPORTED_FEATURE, 4);
                msg.message.push(ext);
                msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
                utils.pushMessage(file, msg);
                break;

            // text mining, flowgraph
            case 'hdbtextconfig':
            case 'hdbtextdict':
            case 'hdbtextrule':
            case 'hdbtextinclude':
            case 'hdbtextlexicon':
            case 'hdbreptask':
                var msg = msgs.getMessage(mc.UNSUPPORTED_FEATURE, 3);
                msg.message.push(ext);
                msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
                utils.pushMessage(file, msg);
                break;

            case 'xscfgd':
            case 'xscfgm':
            case 'xsrtcfgd':
            case 'xscfunc':
            case 'halmservice':
                var msg = msgs.getMessage(mc.UNSUPPORTED_FEATURE, 1);
                msg.message.push(ext);
                msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
                utils.pushMessage(file, msg);
                break;

            // HANA rule framework
            case 'hprrule':
            case 'hprvocabulary':
            case 'hprruletemplate':
            case 'hprruleservice':
            case 'hrfrule':
            case 'hrfvocabulary':
            case 'hrfruletemplate':
            case 'hrfruleservice':
                var msg = msgs.getMessage(mc.UNSUPPORTED_FEATURE, 3);
                msg.message.push(ext);
                msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
                utils.pushMessage(file, msg);
                break;
            case 'hdbruldec':
                var msg = msgs.getMessage(mc.VIEW_MIGRATION, 7);
                msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
                utils.pushMessage(file, msg);
                break;

            default:
                var msg = msgs.getMessage(mc.UNSUPPORTED_FEATURE, 1);
                msg.message.push(ext);
                msg.file = path.join(globalContext.config.directories['xs1-src'], file.RunLocation);
                utils.pushMessage(file, msg);
                break;
        }
        callback(null, file);
    }
}

module.exports = new UnsupportedHandler();
