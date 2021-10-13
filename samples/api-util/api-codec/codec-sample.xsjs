const codec = $.util.codec;
const util = $.util;

const text1 = "Project XSK as Hex";
const text2 = "Project XSK as Base64";

//Hex
let result = "";
let encodedHex = codec.encodeHex(text1);
result += `'${text1}' encoded to hex is ${encodedHex}`;

let decodedHex = codec.decodeHex(encodedHex);
result += `\n'${encodedHex}' decoded to ArrayBuffer is: [${decodedHex}]`;

let valueFromHex = util.stringify(decodedHex);
result += `\n Array Buffer stringified is: '${valueFromHex}'\n`;

//Base64
let encodedToBase64 = codec.encodeBase64(text2);
result += `\n'${text2}' encoded to base64 is ${encodedToBase64}`;

let decodedBase64 = codec.decodeBase64(encodedToBase64);
result += `\n'${encodedToBase64}' encoded to ArrayBuffer is: [${decodedBase64}]`;

let valueFromBase64 = util.stringify(decodedBase64);
result += `\n Array Buffer stringified is '${valueFromBase64}'`;

$.response.setBody(result);