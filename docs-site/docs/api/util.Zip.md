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

Create a new zip object.

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
Create a zip object from byte array source.

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

## Constructors

```javascript
new $.util.Zip(config)
```

## Parameters

###Zip object

Name              | Description                                             | Type
----------------- | ------------------------------------------------------- | -----------------
**config** | Object containing new zip configuration parameters. | _`object`_

###config object

Name              | Description                                             | Type
----------------- | ------------------------------------------------------- | -----------------
**source** | Specifies the source for the compressed content. If no source is specified, an empty Zip object is created. | _`byte array`_ | _`$.db.ResultSet`_ | _`$.web.Body`_
**index** | If the first argument is of type ResultSet, the number specifies the index of a Blob column and is mandatory. | _`number`_
**settings** | Used to specify zip options. | _`object`_

###settings object
Name              | Description                                             | Type
----------------- | ------------------------------------------------------- | -----------------
**password** | The password is mandatory when creating a zip object from an existing encrypted archive. | _`string`_
**maxUncompressedSizeInBytes** | A global restriction applies to the amount of data that can be uncompressed | _`number`_

## Properties

Name              | Description                                             | Type
----------------- | ------------------------------------------------------- | -----------------
**_metadata_**          | Contains meta information about the current zip object.	                              | _`object`_
**_password_**          | Setting a value to this property changes the password used for encryption of the zip object. Assigning an empty string disables encryption. Accessing this property will return undefined.                              | _`string`_

## Functions

Function               | Description                                                     | Returns
---------------------- | --------------------------------------------------------------- | --------
**asArrayBuffer()**    | Returns the zip archive as byte array                           | _`byte array`_

