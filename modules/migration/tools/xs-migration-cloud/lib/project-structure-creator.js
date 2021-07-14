// --------------------------------------------------------------------------
//
// Create skeleton project structure
//
// --------------------------------------------------------------------------

var path = require('path');
var fs = require('fs');
var report = require('./report-generator');

function ProjectStructureCreator() {

    this.createProjectStructure = function(context, callback) {

        var rootPath = context.targetDir;
        var dirs = context.config.directories;
        try {
            fs.mkdirSync(rootPath, { recursive: true });

            fs.mkdirSync(path.join(rootPath, dirs['migration']), { recursive: true });
            
            fs.mkdirSync(path.join(rootPath, dirs['xs1-src']), { recursive: true });
          
            report.generateContainer(context);         
            callback();
        } catch (e) {
            callback(e.message);
        }
    }
}

module.exports = new ProjectStructureCreator();