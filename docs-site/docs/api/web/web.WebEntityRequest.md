---
title: $.web.WebEntityRequest
---

$.web.WebEntityRequest
===

`$.web.WebEntityRequest` represents an HTTP request entity.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/22](https://github.com/SAP/xsk/issues/22)
- Module: [web/web.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/web/web.js)
- Status: `alpha`

## Properties

| Name            | Description                     | Type                 |
|-----------------|---------------------------------|----------------------|
| **body**        | The body of the request.        | _`$.web.Body`_       |
| **contentType** | The content type of the entity. | _`string`_           |
| **entities**    | The sub-entities of the entity. | _`$.web.EntityList`_ |
| **headers**     | The headers of the entity.      | _`$.web.TupelList`_  |
| **parameters**  | The parameters of the entity.   | _`$.web.TupelList`_  |

## Functions

| Function                 | Description                  | Returns |
|--------------------------|------------------------------|---------|
| **setBody(body, index)** | Sets the body of the entity. | _`-`_   |
