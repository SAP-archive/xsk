/**
 * Created by SAP SE.
 */
var Enum = require('enum');

var SqlscriptType = new Enum(['FUNCTION', 'PROCEDURE'], {freez: true});

module.exports = SqlscriptType;