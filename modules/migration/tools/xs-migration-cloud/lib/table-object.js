/**
 * Created by SAP SE.
 */

class TableObject {
    
    constructor(schemaName, objectName) {
        this._schemaName = schemaName;
        this._objectName = objectName;
    }
    
    
    getOwnerAlterCommand(platformIsWindows) {
        var execString = "EXEC '" + 'ALTER TABLE "' + this._schemaName + '"."' + this._objectName + '" OWNER TO ';
        
        if(platformIsWindows) {
            execString += '%container_owner%' + "'";
        } else {
            execString += '$container_owner' + "'";
        }
        
        return execString;
    }
    
    
    getRenameCommand(platformIsWindows) {
        var execString = "EXEC '" + 'RENAME TABLE "' + this._schemaName + '"."' + this._objectName;
        
        if(platformIsWindows) {
            execString += '" TO "%container_name%"."';
        } else {
            execString += '" TO "$container_name"."';
        }
        
        execString += this._objectName + '"' + "'";
        
        return execString;
    }
    
}

module.exports = TableObject;