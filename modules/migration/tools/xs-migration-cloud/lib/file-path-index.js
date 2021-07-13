/**
 * Created by SAP SE.
 */

var path = require('path');
var FilePathIndexValue = require('./file-path-index-value');

class FilePathIndex {
    
    constructor() {
        this._index = new Map();
        this._indexNr = 0;
    }
    
    
    updateIndex(filename, container, pathMap) {
        filename = path.normalize(filename);
        
        var relativeContainerRootKey = container + '-relative-container-root';
        var relativeContainerRoot = pathMap[relativeContainerRootKey];
        
        var devObjectFolderKey = container + '-dev-object-folder';
        var devObjectFolder = pathMap[devObjectFolderKey];
        
        var value = new FilePathIndexValue(filename, container, relativeContainerRoot, devObjectFolder, this._indexNr);
        
        this._index.set(filename, value);
        this._indexNr++;
    }
    
    
    toArray() {
        return Array.from(this._index.values());
    }
    
    
    toJSON() {
        var ret = {};
        for(let key of this._index.keys()){
            ret[key] = this._index.get(key).toSmallJSON();
        }
        
        return ret;
    }
    
    
}

module.exports = FilePathIndex;