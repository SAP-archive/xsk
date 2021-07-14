/**
 * Created by SAP SE.
 */


class Handler {
    
    constructor(config) {
        this._handlerFile = config.file;
        this._extensions = config.ext ? config.ext : [];
        this._method = config.method;
        this._instance = require('./' + this._handlerFile);
    }

    
    handleFile(file, context, callback, sourceDb, sqlParser, pluginHandler){
        this._instance[this._method](file, context, callback, sourceDb, sqlParser, pluginHandler);
    }
    
}

module.exports = Handler;