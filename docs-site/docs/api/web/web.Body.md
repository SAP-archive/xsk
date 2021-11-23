---
title: $.web.Body
---

$.web.Body
===

`$.web.Body` represents the body of an HTTP request entity.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/22](https://github.com/SAP/xsk/issues/22)
- Module: [web/web.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/web/web.js)
- Status: `alpha`

## Functions

| Function            | Description                                                        | Returns              |
|---------------------|--------------------------------------------------------------------|----------------------|
| **asArrayBuffer()** | Returns the content of an HTTP request entity body as ArrayBuffer. | _`ArrayBuffer`_      |
| **asArrayBuffer()** | Returns the content of an HTTP request entity body as ArrayBuffer. | _`string`_           |
| **asWebRequest()**  | Returns the content of an HTTP request entity body as WebRequest.  | _`$.web.WebRequest`_ |
