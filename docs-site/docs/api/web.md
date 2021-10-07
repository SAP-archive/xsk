---
title: $.web
---

$.web
===

`$.web` represents the web api namespace which is related to the `$.request` and `$.response` APIs.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/22](https://github.com/SAP/xsk/issues/22)
- Module: [web/web.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/web/web.js)
- Status: `alpha`

## Classes

### $.web.Body

#### Functions

| Function            | Description                                                        | Returns              |
|---------------------|--------------------------------------------------------------------|----------------------|
| **asArrayBuffer()** | Returns the content of an HTTP request entity body as ArrayBuffer. | _`ArrayBuffer`_      |
| **asArrayBuffer()** | Returns the content of an HTTP request entity body as ArrayBuffer. | _`string`_           |
| **asWebRequest()**  | Returns the content of an HTTP request entity body as WebRequest.  | _`$.web.WebRequest`_ |

### $.web.EntityList

#### Properties

| Name       | Description                  | Type        |
|------------|------------------------------|-------------|
| **length** | The size of the entity list. | _`integer`_ |

#### Functions

| Function     | Description                                                         | Returns                     |
|--------------|---------------------------------------------------------------------|-----------------------------|
| **create()** | Creates a sub-entity in the current list of entities in EntityList. | _`$.web.WebEntityResponse`_ |

### $.web.TupelList

#### Properties

| Name       | Description                 | Type        |
|------------|-----------------------------|-------------|
| **length** | The size of the tupel list. | _`integer`_ |

#### Functions

| Function             | Description                          | Returns     |
|----------------------|--------------------------------------|-------------|
| **get(name)**        | Returns the values for a given name. | _`string`_  |
| **remove(name)**     | Removes the value for a given name.  | _`-`_       |
| **set(name, value)** | Sets the value for a given name.     | _`boolean`_ |

### $.web.WebEntityRequest

#### Properties

| Name            | Description                     | Type                 |
|-----------------|---------------------------------|----------------------|
| **body**        | The body of the request.        | _`$.web.Body`_       |
| **contentType** | The content type of the entity. | _`string`_           |
| **entities**    | The sub-entities of the entity. | _`$.web.EntityList`_ |
| **headers**     | The headers of the entity.      | _`$.web.TupelList`_  |
| **parameters**  | The parameters of the entity.   | _`$.web.TupelList`_  |

#### Functions

| Function                 | Description                  | Returns |
|--------------------------|------------------------------|---------|
| **setBody(body, index)** | Sets the body of the entity. | _`-`_   |

### $.web.WebEntityResponse

#### Properties

| Name            | Description                     | Type                 |
|-----------------|---------------------------------|----------------------|
| **body**        | The body of the response.       | _`$.web.Body`_       |
| **contentType** | The content type of the entity. | _`string`_           |
| **entities**    | The sub-entities of the entity. | _`$.web.EntityList`_ |
| **headers**     | The headers of the entity.      | _`$.web.TupelList`_  |

#### Functions

| Function                 | Description                  | Returns |
|--------------------------|------------------------------|---------|
| **setBody(body, index)** | Sets the body of the entity. | _`-`_   |

### [`$.web.WebRequest`](../request)

### [`$.web.WebResponse`](../response)
