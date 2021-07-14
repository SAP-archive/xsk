/**
 * Created by SAP SE.
 */
/**
 * Created by SAP SE on 23.06.16.
 */

var Enum = require('enum');

var SqlPrivilege = new Enum(['ALTER', 'DELETE', 'DROP', 'EXECUTE', 'INSERT', 'SELECT', 'UPDATE', 'DEBUG'], {freez: true});

module.exports = SqlPrivilege;