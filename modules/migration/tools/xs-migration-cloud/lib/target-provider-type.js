/**
 * Created by SAP SE.
 */

var Enum = require('enum');

var TargetProviderType = new Enum(['GRANTOR_SERVICE', 'LOGICAL_SCHEMA'], {freez: true});

module.exports = TargetProviderType;