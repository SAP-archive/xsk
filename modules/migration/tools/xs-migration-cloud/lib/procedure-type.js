/**
 * Created by SAP SE.
 */

var Enum = require('enum');

var ProcedureType = new Enum(['R', 'L', 'SQLSCRIPT'], {freez: true});

module.exports = ProcedureType;