const path = require('path');

const SUFFIX = 'hdbtabletype';


class TableType {
    
    
    constructor(schemaName, tableName) {
        this._schemaName = schemaName;
        this._tableName = tableName;

        var oldObjectName = tableName.substring(tableName.indexOf('/') + 1);
        var newObjectName = oldObjectName.replace(/\//g, "_");
        newObjectName = newObjectName + '.' + SUFFIX;
        
        this._objectName = newObjectName;
        
        var pathName = tableName.substring(0, tableName.indexOf('/'));
        this._pathName = pathName.replace(/\./g, path.sep);
        
    }
    
    
    get schemaName() {
        return this._schemaName;
    }
    
    
    get tableName() {
        return this._tableName;
    }
    
    
    get pathName() {
        return this._pathName;
    }
    
    
    get objectName() {
        return this._objectName;
    }
    
    
}

module.exports = TableType;