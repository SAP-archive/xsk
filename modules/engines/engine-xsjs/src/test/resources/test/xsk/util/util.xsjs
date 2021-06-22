var util = $.util;
var response = require('http/v4/response');
var random1 = util.createUuid();
var random2 = util.createUuid();

var arrayBuffer = [84,104,105,115,32,105,115,32,97,32,85,105,110,116,
                          56,65,114,114,97,121,32,99,111,110,118,101,114,116,
                          101,100,32,116,111,32,97,32,115,116,114,105,110,103];
var convertedBuff = util.stringify(arrayBuffer);



random1 != random2 && convertedBuff === "This is a Uint8Array converted to a string"