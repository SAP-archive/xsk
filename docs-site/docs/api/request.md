---
title: $.request
---

$.request
===

`$.request` object represents the client HTTP request currently being processed.


## Overview

- Definition: [https://github.com/SAP/xsk/issues/12](https://github.com/SAP/xsk/issues/12)
- Module: [web/web.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/web)
- Status: `alpha`


## Sample Usage

```javascript
function getUser(id) {
    // retrieve user by id
}

function createUser(data) {
    // create user
}

function deleteUser(id) {
    // delete user
}

if(/\/?users$/.test($.request.queryPath)) {
    const id = $.request.parameters.get("id");
    if ($.request.method === $.net.http.PUT) {
        if($.request.contentType === "application/json") {
            createUser($.request.body);

            $.response.setBody(`created user [${ JSON.stringify($.request.body.asString()) }]`);
        } else {
            $.response.setBody(JSON.stringify({
                "error": "Unsupported content type."
            }));
        }
    } else if(id) {
        switch($.request.method) {
            case $.net.http.GET:
                getUser(id);

                $.response.setBody(`retrieved user with id ${ id }`);

                break;
            case $.net.http.DELETE:
                deleteUser(id);

                $.response.setBody(`deleted user with id [${ id }]`);

                break;
            default:
                $.response.setBody(JSON.stringify({
                    "error": `Unsupported method [${ $.request.method }]` 
                }));
        }
    } else {
        $.response.setBody(JSON.stringify({
            "error": "Missing required parameter [id]"
        }));
    }
} else {
    $.response.setBody(JSON.stringify({ 
        "error": `Unsupported query path [${ $.request.queryPath }]` 
    }));
}
```

## Properties


Name             | Description                                      | Type
---------------- | ------------------------------------------------ | -----------------
**body**         | The body of the request.	                        | _`$.web.Body`_
**contentType**  | The content type of the entity.                  | _`string`_
**cookies**      | The cookies associated with the entity.          | _`$.web.TupelList`_
**entities**     | The sub-entities of the entity.	                | _`$.web.EntityList`_
**headers**      | The headers of the entity.	                      | _`$.web.TupelList`_
**language**     | Language of the request in IETF (BCP 47) format.	| _`string`_
**method**       | The HTTP method of the incoming HTTP request.	  | _`$.net.http`_
**parameters**   | The parameters of the entity.	                  | _`$.web.TupelList`_
**path**         | The URL path specified in the request.	          | _`string`_
**queryPath**    | The URL query path specified in the request.	    | _`string`_

## Functions


Function            | Description                  | Returns
------------------- | ---------------------------- | --------
**setBody(body)**   | Sets the body of the entity. | _`-`_
