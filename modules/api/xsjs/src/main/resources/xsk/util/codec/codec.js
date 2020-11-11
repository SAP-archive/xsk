var base64 = require('utils/v4/base64');
var hex = require('utils/v4/hex');

exports.encodeHex = function(data) {
	if (typeof data === 'string') {
		return hex.encode(data);
	}
	return hex.encode(arrayBufferToString(data));
}

exports.encodeBase64 = function(data) {
	if (typeof data === 'string') {
		return base64.encode(data);
	}
	return base64.encode(arrayBufferToString(data));
}

exports.decodeHex = function(hexData) {
	return hex.decode(hexData);
}

exports.decodeBase64 = function(base64Data) {
	return base64.decode(base64Data);
}

function arrayBufferToString(buf) {
	return String.fromCharCode.apply(null, new Uint16Array(buf));
}