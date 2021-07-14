/**
 * Created by SAP SE on 6/2/16.
 */

var path = require('path');
var fs = require('fs');

class FileUtils {


    /**
     * Remove quotes in case a directory name contains a "."
     */
    sanitizeFilename(filename) {

        var paths = path.normalize(filename).split(path.sep);
        var spaths = paths.map(function (elem) {
            if (elem.match(/".*"/)) {
                return elem.substring(1, elem.length - 1);
            } else {
                return elem;
            }
        });
        return spaths.join(path.sep);
    }
    
    
    cleanupEmptyFolders (globalContext) {
        var rootFolder = globalContext.pathMap['db-full-path-dev-objects'];
       
        this._removeEmptyFolder(rootFolder);
    }


    _removeEmptyFolder(rootFolder) {
        var isDirectory = fs.statSync(rootFolder).isDirectory();
        if(!isDirectory) return;
        
        var directories = fs.readdirSync(rootFolder);
        
        if(directories.length > 0) {
            for (let directory of directories) {
                let completePath = path.join(rootFolder, directory);
                this._removeEmptyFolder(completePath);
            }
            directories = fs.readdirSync(rootFolder);
        }    
            
        if(directories.length == 0) {
            fs.rmdirSync(rootFolder);
            return;
        }
        
    }
    
    
    
    
    
    
}

module.exports = new FileUtils();
