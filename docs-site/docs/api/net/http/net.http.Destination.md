---
title: $.net.http.Destination
---

$.net.http.Destination
===

HTTP(s) destination class that holds metadata (e.g., host, port, useSSL). The destination can be retrieved from the database with `$.net.http.readDestination`.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/20](https://github.com/SAP/xsk/issues/20)
- Module: [http/http.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/http/http.js)
- Status: `alpha`

!!! note "API Changes"
    Destinations in XSK make use of SAP BTP Destination service (see [xshttpdest](../../../artifacts/xshttpdest.md)).
    As such, destinations no longer live in the file tree, so the first parameter of `readDestination` representing the package is no longer needed. If provided, it will be ignored. Lookup for destinations happens based on the name.

## Sample Usage

=== "destination-sample.xsjs"

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
