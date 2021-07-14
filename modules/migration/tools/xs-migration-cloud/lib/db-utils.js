/**
 * Created by SAP SE.
 */

const SQL_PRIVILEGE = require('./sql-privilege');

const EXECUTE_TYPES = ['PROCEDURE', 'FUNCTION'];
const EXECUTE_CHARACTERISTICS = ["CALLABLE", "CALLABLE_FUNCTION"];    

    
class DbUtils {

    
    static getPrivilegeForObjectType(objectType) {
        if(EXECUTE_TYPES.includes(objectType)){
            return SQL_PRIVILEGE.EXECUTE;
        }
        return SQL_PRIVILEGE.SELECT;
    }    
    
    
    static getPrivilegeForObjectCharacteristic(characteristic){
        if(EXECUTE_CHARACTERISTICS.includes(characteristic)) {
            return SQL_PRIVILEGE.EXECUTE;
        }
        return SQL_PRIVILEGE.SELECT;
    }
 
    
    static isFunctionOrProcedure(characteristic) {
        if(EXECUTE_CHARACTERISTICS.includes(characteristic)) {
            return true;
        }
        return false;
    }
    
    
}

module.exports = DbUtils;