/**
 * Created by SAP SE.
 */

var Enum = require('enum');

var CALCVIEW_MIGRATION_STAGES = new Enum(['ONEVIEW', 'COLLECT_REFERENCES', 'MIGRATE_TO_HDI'], {freez: true});

module.exports = CALCVIEW_MIGRATION_STAGES;