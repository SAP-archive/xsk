---
title: $.util.Zip
---

$.util.Zip
===

`$.util.Zip` is a class for manipulation of zip archives. It provides functionality for compressing, uncompressing, removal of entries and zip encryption.


## Overview

- Definition: [https://github.com/SAP/xsk/issues/684](https://github.com/SAP/xsk/issues/684)
- Module: [util/util.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/util/util.js)
- Status: `alpha`


## Sample Usage

```javascript
// Create a new zip object
var zip = new $.util.Zip();
// Set content to the zip object
zip['xsk.txt'] = 'This is XSK';

// Download the zip file
$.response.status = $.net.http.OK;
$.response.contentType = 'application/zip';
$.response.headers.set('Content-Disposition', 'attachment; filename = test.zip');
$.response.setBody(zip.asArrayBuffer());
```
```javascript
// Zip byte array source
var source = [123, 34, 120, 115, 107, 46, 116, 120, 116, 34, 58, 34, 84, 104, 105, 115, 32, 105, 115, 32, 88, 83, 75, 34, 125];

var zip = new $.util.Zip({
    source: source
});

for (var entry in zip) {
    // Loop through zip entries and modify if needed
    if (entry === 'xsk.txt') {
        zip[entry] = 'XSK is great'
    }
}

// Download the zip file
$.response.status = $.net.http.OK;
$.response.contentType = 'application/zip';
$.response.headers.set('Content-Disposition', 'attachment; filename = test.zip');
$.response.setBody(zip.asArrayBuffer());
```

## Parameters

Name              | Description                                             | Type
----------------- | ------------------------------------------------------- | -----------------

## Properties

Name              | Description                                             | Type
----------------- | ------------------------------------------------------- | -----------------

## Functions

Function               | Description                                                     | Returns
---------------------- | --------------------------------------------------------------- | --------
