
/*
 * Generator for the TODO container. The TODO container is a sink
 * for all objects that we currently cannot handle.
 */

var path = require('path');
var utils = require('./generator-utils');

var TODO_DIR_NAME = 'todo';

var DIRECTORIES = [
    TODO_DIR_NAME,
    path.join(TODO_DIR_NAME, "src")
];

function TodoContainerGenerator() {

    this.generateContainer = function(context) {

        var targetRoot = path.join(context.pathMap['todo-root-path']);
        utils.makeDirs(targetRoot, DIRECTORIES);
    }
    this.properties = {
        folderName: TODO_DIR_NAME,
        rootFolder: path.join(TODO_DIR_NAME, "src"),
        devObjectFolder: "src"
    }
}

module.exports = new TodoContainerGenerator();