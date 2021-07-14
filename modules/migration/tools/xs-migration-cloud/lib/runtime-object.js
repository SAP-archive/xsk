/**
 * Created by SAP SE.
 */

class RuntimeObject {
    
    constructor(schemaName, objectName, objectType, synonymName) {
        this._schemaName = schemaName;
        this._objectName = objectName;
        this._objectType = objectType;
        this._synonymName = synonymName;
    }
 
    
    get schemaName() {
        return this._schemaName;
    }
    
    
    get objectName() {
        return this._objectName;
    }
    
    
    get objectType() {
        return this._objectType;
    }
    
    
    get synonymName() {
        return this._synonymName;
    }
    
    
    set synonymName(synonymName) {
        this._synonymName = synonymName;
    }
    
    
    equals(compareObject) {
        if(!compareObject.schemaName) {
            return false;
        }    
        if(compareObject.schemaName === this.schemaName && compareObject.objectName === this.objectName) {
            return true;
        } 
        if((!compareObject.objectName || compareObject.objectName === '') && (!this.objectName || this.objectName === '') && (compareObject.schemaName === this.schemaName)){
            return true;
        }
        return false;
    }
    
    
    get serialId() {
        return this._objectName + this._schemaName + this._objectType;
    }
    
}

module.exports = RuntimeObject;