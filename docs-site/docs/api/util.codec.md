---
title: $.util.codec
---

$.util.codec
===

`$.util.codec` object represents the codec namespace with its fields.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/21](https://github.com/SAP/xsk/issues/21)
- Module: [util/codec/codec.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/xsk/util/codec/codec.js)
- Status: `alpha`

## Sample Usage

```javascript
var codec = $.util.codec;
var util = $.util;

var text1 = "dirigible as hex";
var text2 = "dirigible as base64";

//HEX
var encodedHex = codec.encodeHex(text1);
result1 = "'" + text1 + "'" + " encoded to hex is "+ encodedHex;

var decodedHex = codec.decodeHex(encodedHex);
result2 = "'" + encodedHex + "'" + " decoded to ArrayBuffer is: ["+ decodedHex+"]";

var valueFromHex = util.stringify(decodedHex);
result3 = " Array Buffer stringified is "+"'" + valueFromHex +"'" ;

let resultHex = `\n${result1} `;
resultHex += `\n${result2} `;
resultHex += `\n${result3} `;


//BASE64
var encodedToBase64 = codec.encodeBase64("dirigible as base64");
result4 = "'" + text2 + "'" + " encoded to base64 is "+ encodedToBase64;

var decodedBase64 = codec.decodeBase64(encodedToBase64);
result5 = "'" + encodedToBase64 + "'" + " encoded to ArrayBuffer is "+ decodedBase64;

var valueFromBase64 = util.stringify(decodedBase64);
result6 = " Array Buffer stringified is "+"'" +  valueFromBase64+"'" ;

let resultBase = `\n${result4} `;
resultBase += `\n${result5} `;
resultBase += `\n${result6} `;

$.response.setBody(resultHex + "\n" + resultBase );


```

## Functions

| Function               | Description                           | Returns          |
|------------------------|---------------------------------------|------------------|
| **decodeBase64(data)** | Decodes Base64 data.                  | _`ArrayBuffer`_  |
| **decodeHex(data)**    | Decodes hexadecimal data.             | _`ArrayBuffer`_  |
| **encodeBase64(data)** | Encodes data into Base64.             | _`string`_       |
| **encodeHex(data)**    | Encodes data into hexadecimal format. | _`string`_       |
