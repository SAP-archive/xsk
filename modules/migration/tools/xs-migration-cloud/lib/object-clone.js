/**
 * Created by SAP SE on 6/1/16.
 */
var clone = require('clone');

function cloneObject(obj) {
	return clone(obj);
}

module.exports.cloneObject = cloneObject;
