---
title: $.net.http.Client
---

$.net.http.Client
===

HTTP(s) client for outbound connectivity. This client supports HTTP and HTTPS connections over HTTP or SOCKS proxy. You can either use a destination (preferred way) or a URL as target. To use HTTPS you need to specify a trust store with the needed certificates (either in the destination or with `setTrustStore`).

To choose between HTTP and SOCKS proxy, the proxy URL starts with either `http://` or `socks://`.

This `HttpClient` is equipped with a cookie database. If a previous response sent a `set-cookie` header, the cookie is stored for the relevant domain and path. Subsequent requests will be enriched with the stored cookies automatically.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/20](https://github.com/SAP/xsk/issues/20)
- Module: [http/http.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/http/http.js)
- Status: `alpha`

## Sample Usage

=== "client-sample.xsjs"

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
    client.request(request, destination);
    let response = client.getResponse();

    // get all the cookies and headers from the response
    let cookies = [], headers = [];

    for(let i = 0; i< response.cookies.length; i++) {
        cookies.push(response.cookies[i]);
    }

    for(let i = 0; i< response.headers.length; i++) {
        headers.push(response.headers[i]);
    }

    // check the contents of the response
    $.response.setBody("status: " + response.status + " cookies: " + JSON.stringify(cookies) + " headers: " + JSON.stringify(headers) + " body: " + response.body.asString());
    ```

=== "service.xshttpdest"

    ```javascript
    host=https://services.odata.org;
    pathPrefix=/V4/Northwind/Northwind.svc/;
    ```

## Functions

| Function                                                   | Description                                                                                                                               | Returns               |
|------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|-----------------------|
| **close()**                                                | Close the connection. It is done automatically inside the HttpClientFacade. No need to call it explicitly!                                | _`void`_              |
| **getResponse()**                                          | Retrieve the response from the previously sent request synchronously/blocking. Throws an error if there is no valid response to return    | _`$.web.WebResponse`_ |
| **request($.net.http.Request, $.net.http.Destination)**    | Send a new request object to the given destination. Throws an error if the request fails or the parameters are invalid.                   | _`void`_              |
| **request($.net.http.Request, url, proxy (optional))**     | Send a request object to the given URL. Throws an error if the request fails or the parameters are invalid.                               | _`void`_              |
| **request(WebMethod ($.net.http), url, proxy (optional))** | Send a new request to the given URL, using the specified HTTP method. Throws an error if the request fails or the parameters are invalid. | _`void`_              |
| **setTimeout(timeout)**                                    | Sets the timeout for communication with the server. Throws an error if the parameter is not a numeric value.                              | _`void`_              |
| **setTrustStore(trustStore)**                              | Sets the default trust store the will be used when issuing https:// requests via request(request, URI, ...)-syntax.                       | _`void`_              |
