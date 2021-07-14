/**
 * Created by SAP SE.
 */

var Handler = require('./handler');    
    
class Container {
    
    constructor(config) {
        this._name = config.name;
        this._files = [];       
        this._extensions = config.extensions ? config.extensions : [];
        this._handlers = new Map();

        if(config.handlers) {
            for (let handlerConfig of config.handlers) {
                let handler = new Handler(handlerConfig);
                for(let extension of handlerConfig.ext) {
                    this._handlers.set(extension, handler);                    
                }
            }
        }
        
        this._containerType = config['container-type'];
    }
    
    
    get name() {
        return this._name;
    }
    
    
    get extensions() {
        return this._extensions;
    }
    
    
    get handlers() {
        return this._handlers;
    }
    
    
    get containerType() {
        return this._containerType;
    }
    
    
    addFile(file) {
        this._files.push(file);
    }
    
    
    isTodoContainer() {
        return this._name === 'todo';
    }
    
    
    getHandlerForExt(ext) {
                
        return this._handlers.get(ext);
    }
}

module.exports = Container;