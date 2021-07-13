/**
 * Created by SAP SE.
 */
    
var fs = require('fs');
var path = require('path');
var os = require('os');
var logUtil = require('../lib/log-util');

const FILENAME_UNIX = 'moveTablesToContainer.sh';
const FILENAME_WINDOWS = 'moveTablesToContainer.bat';

class MigrationUtils {

    static createTableRenameScript(globalContext) {

        logUtil.info('Create the rename table script');

        if (globalContext && globalContext.tableObjects && globalContext.tableObjects.length < 1) {
            logUtil.info('There are no active table objects, skip it');
            return;
        }
        
        let platformIsWindows = os.platform() === 'win32';
        let templateFilename = platformIsWindows ? FILENAME_WINDOWS : FILENAME_UNIX;
                
        let filename = path.join(globalContext.targetDir, globalContext.config.directories.migration, templateFilename);

        const pathToTheTemplateFile = path.resolve(__dirname, '..', path.join('templates', 'other', templateFilename));
        let content = fs.readFileSync(pathToTheTemplateFile);

        let prefix = platformIsWindows ? '@echo.' : '';

        for(let tableObject of globalContext.tableObjects) {
            content += prefix + tableObject.getOwnerAlterCommand(platformIsWindows) + ';' + os.EOL;
            content += prefix + tableObject.getRenameCommand(platformIsWindows) + ';' + os.EOL + os.EOL;
        }
        
        content += prefix + 'END;' + os.EOL;
        
        if(platformIsWindows) {
            content += ')';
        } else {
            content += 'EOF';    
        }
        

        fs.writeFileSync(filename, content);

        let parseJsFilename = path.join(globalContext.targetDir, globalContext.config.directories.migration, 'parse-json.js');
        let parseJs = fs.readFileSync(path.resolve(__dirname, '../lib/parse-json.js'), 'utf-8');
        fs.writeFileSync(parseJsFilename, parseJs);

    }
}

module.exports = MigrationUtils;