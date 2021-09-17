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
result += `\n Array Buffer stringified is: '${valueFromHex}'\n` ;

//Base64
let encodedToBase64 = codec.encodeBase64(text2);
result += `\n'${text2}' encoded to base64 is ${encodedToBase64}`;

let decodedBase64 = codec.decodeBase64(encodedToBase64);
result += `\n'${encodedToBase64}' encoded to ArrayBuffer is: [${decodedBase64}]`;

let valueFromBase64 = util.stringify(decodedBase64);
result += `\n Array Buffer stringified is '${valueFromBase64}'` ;

$.response.setBody(result);
```

## Functions

| Function               | Description                           | Returns          |
|------------------------|---------------------------------------|------------------|
| **decodeBase64(data)** | Decodes Base64 data.                  | _`ArrayBuffer`_  |
| **decodeHex(data)**    | Decodes hexadecimal data.             | _`ArrayBuffer`_  |
| **encodeBase64(data)** | Encodes data into Base64.             | _`string`_       |
| **encodeHex(data)**    | Encodes data into hexadecimal format. | _`string`_       |
