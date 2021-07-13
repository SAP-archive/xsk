/**
 * Created by SAP SE on 03.07.17.
 */

var Enum = require('enum');

var JoinOrderType = new Enum(['insideOut', 'outsideIn'], {freez: true});

module.exports = JoinOrderType;