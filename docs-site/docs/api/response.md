---
title: $.response
---

$.response
===

`$.response` represents the HTTP response currently being populated. This API is used for returning a result response to the xsk HTTP service caller.


## Overview

- Definition: [https://github.com/SAP/xsk/issues/13](https://github.com/SAP/xsk/issues/13)
- Module: [response/response.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/web)
- Status: `alpha`


## Sample Usage

```javascript
// array of cars and their colors
let cars = [
  { make: "mercedes", color: "red" },
  { make: "audi", color: "blue" },
  { make: "toyota", color: "red" },
  { make: "vw", color: "blue" }
]

// filter by color function
function filterCarsByColor(color) {
  return cars.filter(function(car) { 
    return car.color === color;
  })
}

if ($.request.method === $.net.http.GET) {
  // get query parameter color
  let color = $.request.parameters.get("color");

  // handle some request operation 
  if (color) {
    // filter by color if passed
    let filteredCars = filterCarsByColor(color);

    // send response with filtered cars by color
    $.response.contentType = "application/json";
    $.response.status = $.net.http.OK;
    $.response.setBody(JSON.stringify({
      "cars": filteredCars
    }));
  } else {
    // send response with all cars as color param is missing
    $.response.status = $.net.http.BAD_REQUEST;
    $.response.setBody(JSON.stringify({
      "cars": cars
    }));
  }
} else {
  // unsupported method
  $.response.status = $.net.http.NOT_FOUND;
  $.response.setBody(JSON.stringify({
    "error": "not found"
  }));
}
```

## Properties


Name              | Description                                             | Type
----------------- | ------------------------------------------------------- | -----------------
**body**          | The body of the response.	                              | _`$.web.Body`_
**cacheControl**  | Easy access to the cache control header of the entity.  | _`string`_
**contentType**   | The content type of the entity.                         | _`string`_
**cookies**       | The cookies associated with the entity.                 | _`$.web.TupelList`_
**entities**      | The sub-entities of the entity.	                        | _`$.web.EntityList`_
**headers**       | The headers of the entity.	                            | _`$.web.TupelList`_
**status**        | The HTTP status code of the outgoing HTTP response.     | _`$.net.http`_

## Functions


Function               | Description                                                     | Returns
---------------------- | --------------------------------------------------------------- | --------
**followUp(object)**   | Enable running a follow up job that executes in the background. | _`-`_
**setBody(body)**      | Sets the body of the entity.                                    | _`-`_
