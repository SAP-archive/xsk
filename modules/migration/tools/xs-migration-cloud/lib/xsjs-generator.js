/*
 * Generator for xsjs container
 *
 * Notes:
 * - package.json contains project dependent information
 * - main.js must become generic with no project specific
 *   information (the name may have to be generic as well)
 */

var utils = require('./generator-utils');
var path = require('path');
var defaultConfig = require('./default-configuration');

var XSJS_DIR_NAME = defaultConfig.directories['default-xsjs-dir'];
// var TEMPLATE_FILES = [
//     ['.gitignore', ""]
// ];
var TEMPLATE_FILES = [];

var DIRECTORIES = [
    XSJS_DIR_NAME,
    path.join(XSJS_DIR_NAME, "lib")
];

var TEMPLATE_DIR = path.join(
    path.dirname(module.filename),
    '..',
    'templates',
    'xsjs');

function XsjsContainerGenerator() {

    this.generateContainer = function (context) {
        
        var targetRoot = path.join(context.targetDir,
            context.config.directories['xs2-app']);

        utils.makeDirs(targetRoot, DIRECTORIES);
        var targetDir = path.join(targetRoot, XSJS_DIR_NAME);
        var replacements = {
            "app-name": context.project.name,
            "app-description": context.project.description,
            "app-version": context.project.version
        };
        
        TEMPLATE_FILES.push(['package.json', '', context.targetRelease.name]);
        
        if(context.hasJobs) {
            TEMPLATE_FILES.push(['main.js', '', 'jobs']);
        } else {
            TEMPLATE_FILES.push(['main.js', ""]);
        }
        
        
        utils.copyTemplateFiles(TEMPLATE_DIR, targetDir, TEMPLATE_FILES, replacements, context);
    };
    this.properties = {
        folderName: XSJS_DIR_NAME,
        rootFolder: path.join(XSJS_DIR_NAME, "lib"),
        devObjectFolder: "lib"
    }
}

module.exports = new XsjsContainerGenerator();
