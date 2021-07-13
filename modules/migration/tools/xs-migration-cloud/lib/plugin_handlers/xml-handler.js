// --------------------------------------------------------------------------
//
// Plugin handler for xml files
//
// Attempt to guess where to put the xml file (web or xsjs)
//
// --------------------------------------------------------------------------

var log = require('../log-util');
var path = require('path');
var xml2js = require('xml2js');
var utils = require('./plugin-utils');
var todo = require('../todo-generator');
var web = require('../web-generator');
var mc = require('../message-categories');
var msgs = require('../messages');

function XmlHandler() {

    var VIEW_IDENTIFIER = 'sap.ui.core.mvc';

    var CORE_VIEW_IDENTIFIER = 'xmlns:core="sap.ui.core"';

    var UI5_VIZ = 'xmlns:viz="sap.viz.ui5"';

    var UI5_FORM = 'xmlns:form="sap.ui.commons.form"';

    var UILIB_IDENTIFIER = 'sap.hana.admin.cockpit.uilib';

    var UI5_LAYOUT = 'xmlns:layout="sap.ui.commons.layout"';

    function isView(contents) {
        return contents.search(VIEW_IDENTIFIER) != -1;
    }
    function isUilib(contents) {
        return contents.search(UILIB_IDENTIFIER) != -1;
    }
    function isModuleOrExtensionPoint(contents) {
        var isModule = false;
        var isExtensionPoint = false;
        var isFragment = false;
        xml2js.parseString(contents, function(err, result)  {
            if (err) {
                log.error("Unable to parse xml file: " + err);
            } else {
                isModule = 'Module' in result;
                isExtensionPoint = 'ExtensionPoint' in result;
                isFragment = 'core:FragmentDefinition' in result;
            }
        });
        return isModule || isExtensionPoint || isFragment;
    }
    function isCoreView(contents) {
        return contents.search(CORE_VIEW_IDENTIFIER);
    }

    function isUI5Viz(contents) {
        return contents.search(UI5_VIZ);
    }

    function isUI5layout(contents) {
        return contents.search(UI5_LAYOUT);
    }

    function isUI5Form(contents) {
        return contents.search(UI5_FORM);
    }

    function isSiteXML(filename) {
        return utils.getFilename(filename) === 'site.xml';
    }

    this.handleFile = function(file, globalContext, callback) {
        log.debug("Processing xml file " + file.RunLocation);
        var fileContent = file.content.toString('utf8');
        if (isView(fileContent)
            || isUilib(fileContent)
            || isCoreView(fileContent)
            || isUI5Viz(fileContent)
            || isUI5layout(fileContent)
            || isUI5Form(fileContent)
            || isModuleOrExtensionPoint(fileContent)
            || isSiteXML(file.RunLocation)) {
            log.debug("xml file " + file.RunLocation + " moved to web container");
            file.toBeCreated.push({
                filename: path.join(web.properties.devObjectFolder, file.RunLocation),
                content: file.content,
                file_container: 'web'
            });
        } else {
            log.debug("xml file " + file.RunLocation + " not recognized");
            file.toBeCreated.push({
                filename: path.join(todo.properties.devObjectFolder, file.RunLocation),
                content: file.content,
                file_container: 'todo'
            });
            var msg = msgs.getMessage(mc.TODO, 2);
            msg.message.push(file.RunLocation);
            msg.file = path.join(globalContext.pathMap['todo-relative-path'], file.RunLocation);
            utils.pushMessage(file, msg);
        }
        file.doNotWriteContent();
        callback(null, file);
    }
}

module.exports = new XmlHandler();
