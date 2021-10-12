---
title: $.net.http
---

$.net.http
===

`$.net.http` represents the http namespace with its fields.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/20](https://github.com/SAP/xsk/issues/20)
- Module: [http/http.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/http/http.js)
- Status: `alpha`

## Sample Usage

=== "service.xshttpdest"

    ```javascript
    let http = $.net.http;

    /*
    Read service.xshttpdest inside the Demo package that contains:
    host=https://services.odata.org;
    pathPrefix=/V4/Northwind/Northwind.svc/;
    */

    let destination = http.readDestination("Demo", "service");

    // create client
    let client = new http.Client();
    let request = new http.Request(http.GET, "/"); // new Request(METHOD, PATH)
    // the PATH will be prefixed by destination's pathPrefix, e.g. "/search?" on the request
    // set the timeout in seconds
    client.setTimeout(10);
    // send the request and synchronously get the response
    client.request(request, dest);
    let response = client.getResponse();

    // get all the cookies and headers from the response
    let cookies = [], headers = [];

    for(let i = 0; i< response.cookies.length; i++) {
        cookies.push(response.cookies[i]);
    }

    for(let i = 0; i< response.headers.length; i++) {
        headers.push(response.headers[i]);
    }

    // get the body
    let body;
    if(!response.body)
        body = "";
    else
        body = response.body;

    // close the connection
    client.close();        // prevent socket leak - see xsengine.ini: [communication] - max_open_sockets_per_request

    // check the contents of the response
    $.response.setBody("status: " + response.status + " cookies: " + JSON.stringify(cookies) + " headers: " + JSON.stringify(headers) + " body: " + body.asString());
    ```

=== "service.xshttpdest"

    ```javascript
    host=https://services.odata.org;
    pathPrefix=/V4/Northwind/Northwind.svc/;
    ```

## Classes


| Classes                                       | Description                                               |
|-----------------------------------------------|-----------------------------------------------------------|
| **[Destination](../net.http.Destination/)** | Contains metadata, for example, host name, port number and custom values.      |
| **[Client](../net.http.Client/)**           | HTTP(s) Client for outbound connectivity. This client supports HTTP and HTTPs connections over HTTP or SOCKS proxy. |
| **[Request](../net.http.Request/)**         | Request class to be used with HTTP client.|

## Functions


| Function                                 | Description                                                               | Returns                     |
|------------------------------------------|---------------------------------------------------------------------------|-----------------------------|
| **readDestination(package, objectName)** | Returns the HTTP destination with the given name as a Destination object. | _`$.net.http. Destination`_ |

## HTTP constants for methods


| Name       | Description          | Type       | Default |
|------------|----------------------|------------|---------|
| **OPTIONS**| HTTP Method OPTIONS. | _`number`_ | _`0`_   |
| **GET**    | HTTP Method GET.     | _`number`_ | _`1`_   |
| **HEAD**   | HTTP Method HEAD.    | _`number`_ | _`2`_   |
| **POST**   | HTTP Method POST.    | _`number`_ | _`3`_   |
| **PUT**    | HTTP Method PUT.     | _`number`_ | _`4`_   |
| **DEL**    | HTTP Method DEL.     | _`number`_ | _`5`_   |
| **TRACE**  | HTTP Method TRACE.   | _`number`_ | _`6`_   |
| **CONNECT**| HTTP Method CONNECT. | _`number`_ | _`7`_   |
| **PATCH**  | HTTP Method PATCH.   | _`number`_ | _`8`_   |

## HTTP constants for status codes


| Name                                | Type       | Default |
|-------------------------------------|------------|---------|
| **CONTINUE**                        | _`number`_ | _`100`_ |
| **SWITCH_PROTOCOL**                 | _`number`_ | _`101`_ |
| **OK**                              | _`number`_ | _`200`_ |
| **CREATED**                         | _`number`_ | _`201`_ |
| **ACCEPTED**                        | _`number`_ | _`202`_ |
| **NON_AUTHORITATIVE**               | _`number`_ | _`203`_ |
| **NO_CONTENT**                      | _`number`_ | _`204`_ |
| **RESET_CONTENT**                   | _`number`_ | _`205`_ |
| **PARTIAL_CONTENT**                 | _`number`_ | _`206`_ |
| **MULTIPLE_CHOICES**                | _`number`_ | _`300`_ |
| **MOVED_PERMANENTLY**               | _`number`_ | _`301`_ |
| **FOUND**                           | _`number`_ | _`302`_ |
| **SEE_OTHER**                       | _`number`_ | _`303`_ |
| **NOT_MODIFIED**                    | _`number`_ | _`304`_ |
| **USE_PROXY**                       | _`number`_ | _`305`_ |
| **TEMPORARY_REDIRECT**              | _`number`_ | _`307`_ |
| **BAD_REQUEST**                     | _`number`_ | _`400`_ |
| **UNAUTHORIZED**                    | _`number`_ | _`401`_ |
| **PAYMENT_REQUIRED**                | _`number`_ | _`402`_ |
| **FORBIDDEN**                       | _`number`_ | _`403`_ |
| **NOT_FOUND**                       | _`number`_ | _`404`_ |
| **METHOD_NOT_ALLOWED**              | _`number`_ | _`405`_ |
| **NOT_ACCEPTABLE**                  | _`number`_ | _`406`_ |
| **PROXY_AUTH_REQUIRED**             | _`number`_ | _`407`_ |
| **REQUEST_TIMEOUT**                 | _`number`_ | _`408`_ |
| **CONFLICT**                        | _`number`_ | _`409`_ |
| **GONE**                            | _`number`_ | _`410`_ |
| **LENGTH_REQUIRED**                 | _`number`_ | _`411`_ |
| **PRECONDITION_FAILED**             | _`number`_ | _`412`_ |
| **REQUEST_ENTITY_TOO_LARGE**        | _`number`_ | _`413`_ |
| **REQUEST_URI_TOO_LONG**            | _`number`_ | _`414`_ |
| **UNSUPPORTED_MEDIA_TYPE**          | _`number`_ | _`415`_ |
| **REQUESTED_RANGE_NOT_SATISFIABLE** | _`number`_ | _`416`_ |
| **EXPECTATION_FAILED**              | _`number`_ | _`417`_ |
| **INTERNAL_SERVER_ERROR**           | _`number`_ | _`500`_ |
| **NOT_YET_IMPLEMENTED**             | _`number`_ | _`501`_ |
| **BAD_GATEWAY**                     | _`number`_ | _`502`_ |
| **SERVICE_UNAVAILABLE**             | _`number`_ | _`503`_ |
| **GATEWAY_TIMEOUT**                 | _`number`_ | _`504`_ |
| **HTTP_VERSION_NOT_SUPPORTED**      | _`number`_ | _`505`_ |
