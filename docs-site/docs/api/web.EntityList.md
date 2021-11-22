---
title: $.web.EntityList
---

$.web.EntityList
===

`$.web.Entitylist` represents a list of request or response entities.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/22](https://github.com/SAP/xsk/issues/22)
- Module: [web/web.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/web/web.js)
- Status: `alpha`

## Properties

| Name       | Description                  | Type        |
|------------|------------------------------|-------------|
| **length** | The size of the entity list. | _`integer`_ |

## Functions

| Function     | Description                                                         | Returns                     |
|--------------|---------------------------------------------------------------------|-----------------------------|
| **create()** | Creates a sub-entity in the current list of entities in EntityList. | _`$.web.WebEntityResponse`_ |
