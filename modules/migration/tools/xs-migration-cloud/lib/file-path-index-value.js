/**
 * Created by SAP SE.
 */

var path = require('path');
var pluginUtils = require('./plugin_handlers/plugin-utils');

class FilePathIndexValue {
    
    constructor(filename, container, relativeContainerRoot, devObjectFolder, indexNr) {
        this._name = path.join(relativeContainerRoot, filename);
        this._container = container;
        this._ext = pluginUtils.getExtension(filename);
        this._pkgOnlyName = filename.substring(devObjectFolder.length);
        this._index = indexNr;
    }
    
    
    get name() {
        return this._name;
    }
    
    
    get pkgOnlyName() {
        return this._pkgOnlyName;   
    }
    
    
    get ext() {
        return this._ext;
    }
    
    
    get container() {
        return this._container;
    }
    
        
    get index() {
        return this._index;
    }
 
    
    toJSON() {
        return {
            name: this.name,
            pkgOnlyName: this.pkgOnlyName,
            ext: this.ext,
            container: this.container,
            index: this.index
        };
    }
    
    toSmallJSON() {
        return {
            container: this.container,
            fileIndex: this.index
        };
    }
    
    
}

module.exports = FilePathIndexValue;