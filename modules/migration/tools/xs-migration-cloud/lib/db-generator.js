var fs = require('fs');
var path = require('path');
var utils = require('./generator-utils');
var defaultConfig = require('./default-configuration');

var DB_DIR_NAME = defaultConfig.directories['default-db-dir'];

var TEMPLATE_FILES = [];

var TEMPLATE_DIR = path.join(
    path.dirname(module.filename),
    '..',
    'templates',
    'db');

class DbContainerGenerator {

    generateContainer (context) {
        
        if (context.generate_manifests) {
            TEMPLATE_FILES.push(['package.json', '', context.targetRelease.name]);
        }
        
        var targetRoot = path.join(context.targetDir, context.config.directories['xs2-app']);
        var targetDir = path.join(targetRoot, DB_DIR_NAME);

        var DIRECTORIES = [DB_DIR_NAME];
        var cfgDir = 'cfg';
        var srcDir = 'src';
        
        if (context.htaMode) {
            DIRECTORIES.push(path.join(DB_DIR_NAME, "cfg", context.rootHdiNamespace));
            DIRECTORIES.push(path.join(DB_DIR_NAME, "src", context.rootHdiNamespace));
            
            cfgDir = path.join('cfg', context.rootHdiNamespace);
            srcDir = path.join('src', context.rootHdiNamespace);
            
        } else {
            // TEMPLATE_FILES.push(['.gitignore', '']);
            DIRECTORIES.push(path.join(DB_DIR_NAME, "cfg"));
            DIRECTORIES.push(path.join(DB_DIR_NAME, "src"));
            TEMPLATE_FILES.push(['.hdiconfig', 'cfg', context.targetRelease.name]);
            TEMPLATE_FILES.push(['.hdiconfig', 'src', context.targetRelease.name]);
        }
        
        utils.makeDirs(targetRoot, DIRECTORIES);
        utils.copyTemplateFiles(TEMPLATE_DIR, targetDir, TEMPLATE_FILES, undefined, context);
        
        var hdinamespace = JSON.parse(fs.readFileSync(path.join(TEMPLATE_DIR, '.hdinamespace'), 'utf-8'));
        hdinamespace.name = context.rootHdiNamespace;        
        
        utils.writeFileSync(path.join(targetDir, srcDir), '.hdinamespace', JSON.stringify(hdinamespace, null, 4));
        utils.writeFileSync(path.join(targetDir, cfgDir), '.hdinamespace', JSON.stringify(hdinamespace, null, 4));
        
    };
    
    get properties() {
        return {
            folderName     : 'db',
            rootFolder     : path.join("db", "src"),
            devObjectFolder: "src",
            viewTmpFolder  : path.join("migration", "db", "tmp"),
            cfgFolder      : path.join('db', 'cfg')
        };
    }
}

module.exports = new DbContainerGenerator();
