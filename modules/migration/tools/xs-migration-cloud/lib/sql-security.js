/**
 * Created by SAP SE on 23.06.16.
 */

var Enum = require('enum');

var SqlSecurity = new Enum(['INVOKER', 'DEFINER'], {freez: true});

module.exports = SqlSecurity;