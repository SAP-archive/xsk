---
title: $.util.codec
---

$.util.codec
===

$.util.codec object represents the codec namespace with its fields.

=== "Overview"
- Definition: [https://github.com/SAP/xsk/issues/21](https://github.com/SAP/xsk/issues/21)
- Module: [util/codec/codec.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/xsk/util/codec/codec.js)
- Status: `alpha`

### Basic Usage


* Sample usage:
```javascript
var codec = $.util.codec;
var util = $.util;

// encode the string value to  hex data
var encodedHex = codec.encodeHex("dirigible as hex");

//returns ArrayBuffer 
var decodedHex = codec.decodeHex(encodedHex);
//Converts an ArrayBuffer containing UTF-8 encoded string to a JavaScript String object 
var valueFromHex = util.stringify(decodedHex);

// encode the string value  base64 data
var encodedToBase64 = codec.encodeBase64("dirigible as base64");
//returns ArrayBuffer
var decodedBase64 = codec.decodeBase64(encodedToBase64);
//Converts an ArrayBuffer containing UTF-8 encoded string to a JavaScript String object 
var valueFromBase64 = util.stringify(decodedBase64);
```

* Methods

| Members      | Description                                            |Type |
|--------------|--------------------------------------------------------|:--------:|
| decodeBase64(base64Data) | Decodes Base64 data | ArrayBuffer |
| decodeHex(hexData)| Decodes hexadecimal data. |  ArrayBuffer |
| encodeBase64(data) | Encodes data into Base64. |  String |
| encodeHex(data)| Encodes data into hexadecimal format.    |   String   |
