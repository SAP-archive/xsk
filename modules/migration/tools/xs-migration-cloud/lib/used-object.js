/**
 * Created by SAP SE on 23.06.16.
 */

var SqlSecurity = require('./sql-security');
var RuntimeObject = require('./runtime-object');
var StringUtil = require('./string-util');
const SQL_PRIVILEGE = require('./sql-privilege');

class UsedObject {

    constructor(schemaName, objectName, privileges, sqlSecurity = SqlSecurity.DEFINER, synonymName, userName){
        this._schemaName = schemaName;
        this._objectName = objectName;
        this._privileges = new Set();        
        
        if(!privileges) {
            this._privileges.add(SQL_PRIVILEGE.SELECT);            
        } else {
            for(let privilege of privileges){
                this._privileges.add(privilege);
            }                            
        }        
        this._sqlSecurity = sqlSecurity;
        this._synonymName = synonymName;
        this._userNames = new Set();
        if(userName) this._userNames.add(userName);
        this._isCoverable = true;
    }


    get schema_name() {
        return this._schemaName;
    }


    get object_name() {
        return this._objectName;
    }


    get privileges() {
        return Array.from(this._privileges);
    }

    
    get synonymName() {
        if(this._synonymName !== undefined){
            return this._synonymName;    
        }
        
        return this._objectName;
    }
    
    

    get userNames() {
        return this._userNames;
    }
    

    set synonymName(synonymName) {
        this._synonymName = synonymName;
    }
    
            
    requiresGrantOption() {
        return this._sqlSecurity === SqlSecurity.DEFINER;
    }
    

    addUserName(userName) {
        this._userNames.add(userName);
    }


    addUserNames(userNames) {
        for(let userName of userNames) {
            this._userNames.add(userName);
        }
    }

    
    get runtimeObjects() {
        var runtimeObjects = [];
        if(this._oldObjectName) {
            runtimeObjects.push(new RuntimeObject(this._schemaName, this._oldObjectName));
        }
        runtimeObjects.push(new RuntimeObject(this._schemaName, this._objectName));
        
        return runtimeObjects;
    }
    
    set oldObjectName(oldObjectName) {
        this._oldObjectName = oldObjectName;
    }
    
    get packageId() {
        if(this._objectName.indexOf('/') > -1){
            return this._objectName.split('/')[0];
        }
        if(this._objectName.indexOf('::') > -1){
            return this._objectName.split('::')[0];
        }        
        
        return '';
    }
    
    
    get synonymPackageId() {
        
        if(this.synonymName.indexOf('::') > -1){
            return this.synonymName.split('::')[0];
        }
        if(this.synonymName.indexOf('/') > -1){
            return this.synonymName.split('/')[0];
        }
        
        return '';
    }
    
    
    get shortObjectName() {
        if(this._objectName.indexOf('/') > -1){
            let splitIndex = this._objectName.indexOf('/') + 1;
            return this._objectName.substring(splitIndex);
        }
        if(this._objectName.indexOf('::') > -1){
            return this._objectName.split('::')[1];
        }

        return this._objectName;
    }
    
    
    get serialId() {
        return this._schemaName + this._objectName;
    }
    
    
    get privilegeSerialId() {
        var privilege = this.privileges.join();
        var grantable = this.requiresGrantOption().toString().toUpperCase();
        
        return StringUtil.hashCode(this._schemaName + this._objectName + privilege + grantable);
    
    }
    
    
    get completeName() {
        if (this._schemaName && this._schemaName.length > 0) {
            return this._schemaName + '.' + this._objectName;
        }
        return this._objectName;
    }
    
    
    merge(usedObject) {
        if(usedObject.requiresGrantOption() || this.requiresGrantOption()) {
            this._sqlSecurity = SqlSecurity.DEFINER;
        }
        this.addUserNames(usedObject.userNames);

        for(let privilege of usedObject.privileges) {
            this._privileges.add(privilege);
        }        
        
    }
    
    
    get synonymMappingObject() {
        return {
            schemaName: this._schemaName,
            objectName: this._objectName,
            synonymName: this.synonymName
        };
    }
    
    
    set isCoverable(value) {
        this._isCoverable = value;
    }
    
    
    get isCoverable() {
        return this._isCoverable;
    }
    
    
    get oldObjectName() {
        return this._oldObjectName;
    }
    
}

module.exports = UsedObject;