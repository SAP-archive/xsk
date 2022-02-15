---
title: $.net.Destination
---

$.net.Destination
===

`$.net.Destination` class returns the network destination with the given name. A network destination contains metadata (e.g., host name or port number). The network destination can also contain custom properties.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/19](https://github.com/SAP/xsk/issues/19)
- Module: [net/net.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/net/net.js)
- Status: `alpha`


!!! note "API Changes"
    Destinations in XSK make use of SAP BTP Destination service (see [xshttpdest](../../artifacts/xshttpdest.md)).
    As such, destinations no longer live in the file tree, so the first parameter of `Destination` representing the package is no longer needed. If provided, it will be ignored. Lookup for destinations happens based on the name.

## Sample Usage

=== "destination-sample.xsjs"

    ```javascript
    let net = $.net;

    /*
    Read service.xshttpdest inside the Demo package that contains:
    host=http://localhost;
    port=8080;
    */
    let dest = new net.Destination("Demo", "service");
    // Check if the file has been read properly
    $.response.setBody("Host: " +dest.host+ " Port: " +dest.port);
    ```
## Constructors

```javascript
new $.net.Destination(package, objectName)
```
!!! info "Throws an error if no valid destination is found with the given name."

## Parameters

| Parameter Name | Description                                        | Required     | Type       |
|----------------|----------------------------------------------------|--------------|------------|
| **package**    | Ignored as lookup is done in Destination service   | _`optional`_ | _`String`_ |
| **objectName** | The name of the destination in Destination service | _`required`_ | _`String`_ |
