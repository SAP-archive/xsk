---
title: $.web.TupelList
---

$.web.TupelList
===

`$.web.TupelList` represents a list of name-value pairs.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/22](https://github.com/SAP/xsk/issues/22)
- Module: [web/web.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/web/web.js)
- Status: `alpha`

## Properties

| Name       | Description                 | Type        |
|------------|-----------------------------|-------------|
| **length** | The size of the tupel list. | _`integer`_ |

## Functions

| Function             | Description                          | Returns     |
|----------------------|--------------------------------------|-------------|
| **get(name)**        | Returns the values for a given name. | _`string`_  |
| **remove(name)**     | Removes the value for a given name.  | _`-`_       |
| **set(name, value)** | Sets the value for a given name.     | _`boolean`_ |
