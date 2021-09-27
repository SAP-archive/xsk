---
title: $.net.http.Destination
---

$.net.http.Destination
===

HTTP(s) destination class that holds metadata (e.g., host, port, useSSL). The destination can be retrieved from the database with `$.net.http.readDestination`.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/20](https://github.com/SAP/xsk/issues/20)
- Module: [http/http.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/http)
- Status: `alpha`

## Sample Usage

```javascript
let http = $.net.http;

/*
Read service.xshttpdest inside the Demo package that contains:
host=https://services.odata.org;
pathPrefix=/V4/Northwind/Northwind.svc/;
*/
let dest = http.readDestination("Demo", "service");
// Check if the file has been read properly
$.response.setBody("Host: " + dest.host + " Path Prefix: " + dest.pathPrefix);
```
