var assertTrue = require('utils/assert').assertTrue;
var codec = $.util.codec;
var util = $.util;

var encodedHex = codec.encodeHex("dirigible as hex");
var decodedHex = codec.decodeHex(encodedHex);
var valueFromHex = util.stringify(decodedHex);



var encodedToBase64 = codec.encodeBase64("dirigible as base64");
var decodedBase64 = codec.decodeBase64(encodedToBase64);
var valueFromBase64 = util.stringify(decodedBase64);



assertTrue(valueFromHex === "dirigible as hex" && valueFromBase64 ==="dirigible as base64");