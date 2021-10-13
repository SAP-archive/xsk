const util = $.util;

let randomID = util.createUuid();

// Uint8Array
let arrayBuffer = [
  84, 104, 105, 115, 32, 105,
  115, 32, 97, 32, 85, 105,
];

let convertedBuff = util.stringify(arrayBuffer);

let result = `randomID is : ${randomID} `;
result += `\nconvertedBuff is: ${arrayBuffer} `;

$.response.setBody(result);