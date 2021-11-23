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

=== "service.xshttpdest"

    ```javascript
    host=http://localhost;
    port=8080;
    ```

## Constructors

```javascript
new $.net.Destination(package, objectName)
```
!!! info "Throws an error if no valid network destination is found with the given name."

## Parameters

| Parameter Name    | Description                                   | Required     | Type       |
|-------------------|-----------------------------------------------|--------------|------------|
| **package**       | The package where the destination is located. | _`required`_ | _`String`_ |
| **objectName**    |The name of the destination file.              | _`required`_ | _`String`_ |

## Properties

| Name           | Description                                                          | Type       |
|----------------|----------------------------------------------------------------------|------------|
| **host**       | Property used to retrieve the host defined in a network destination. | _`String`_ |
| **port**       | Property used to retrieve the port defined in a network destination. | _`String`_ |
