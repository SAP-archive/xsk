exports.debug = function(message) {
	console.debug(message);
}

exports.error = function(message) {
	console.error(message);
}

exports.fatal = function(message) {
	console.error(message);
}

exports.info = function(message) {
	console.info(message);
}

exports.warning = function(message) {
	console.warn(message);
}

exports.isDebugEnabled = function(message) {
	return true;
}

exports.isErrorEnabled = function(message) {
	return true;
}

exports.isFatalEnabled = function(message) {
	return true;
}

exports.isInfoEnabled = function(message) {
	return true;
}

exports.isWarningEnabled = function(message) {
	return true;
}

