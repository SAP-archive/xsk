/*
 * Generator for web container
 *
 * Notes:
 */

var path = require('path');
var utils = require('./generator-utils');
var defaultConfig = require('./default-configuration');

var WEB_DIR_NAME = defaultConfig.directories['default-web-dir'];

var TEMPLATE_FILES = [];

var DIRECTORIES = [
    WEB_DIR_NAME,
    path.join(WEB_DIR_NAME, "resources")
];

var TEMPLATE_DIR = path.join(
    path.dirname(module.filename),
    '..',
    'templates',
    'web');

function WebContainerGenerator() {

    this.generateContainer = function (context) {

        var targetRoot = path.join(context.targetDir,
            context.config.directories['xs2-app']);
        utils.makeDirs(targetRoot, DIRECTORIES);
        var targetDir = path.join(targetRoot, WEB_DIR_NAME);
        var replacements = {
            "app-name": context.project.name
        };
        
        TEMPLATE_FILES.push(['package.json', '', context.targetRelease.name]);
        
        utils.copyTemplateFiles(TEMPLATE_DIR, targetDir, TEMPLATE_FILES, replacements, context);
    };
    this.properties = {
        folderName: WEB_DIR_NAME,
        rootFolder: path.join(WEB_DIR_NAME, "resources"),
        devObjectFolder: "resources"
    }
}

module.exports = new WebContainerGenerator();
