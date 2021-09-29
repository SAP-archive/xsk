---
title: $.request
---

$.request
===

`$.request` object represents the client HTTP request currently being processed.


## Overview

- Definition: [https://github.com/SAP/xsk/issues/12](https://github.com/SAP/xsk/issues/12)
- Module: [web/web.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/web/web.js)
- Status: `alpha`


## Sample Usage

```javascript
// defined a hardcoded users array for the example
let users = [
    {id: '1', name: 'John'},
    {id: '2', name: 'Ben'},
    {id: '3', name: 'George'}
]

function getUser(id) {
    // retrieve user operation by id
    return users.find(function(user) {
        return user.id === id;
    })
}

function createUser(data) {
    // create user operation which will update the users array only for the scope of the current request call as the users are hardcoded
    users.push({id: data.id, name: data.name});
}

function deleteUser(id) {
    // delete user operation which will remove a user only for the current scope of the request as the users are hardcoded
    users = users.filter(function(user) {
        return user.id !== id
    })
}

function allUsers() {
    // retrieve all users operation
    return users;
}

// get id query param 
const id = $.request.parameters.get("id");

// check the type of the request
switch($.request.method) {
    case $.net.http.PUT:
        if($.request.contentType === "application/json") {
            createUser(JSON.parse($.request.body.asString()));

            $.response.setBody(`created user [${ JSON.stringify($.request.body.asString()) }]`);
        } else {
            $.response.setBody(JSON.stringify({
                "error": "Unsupported content type."
            }));
        }
        break;
    case $.net.http.GET:
        if (id) {
            let user = getUser(id);

            $.response.setBody(JSON.stringify({ user: user }));
        } else {

            let users = allUsers();

            $.response.setBody(JSON.stringify({ users: users }));
        }

        break;
    case $.net.http.DELETE:
        if (id) {
            deleteUser(id);

            $.response.setBody(`deleted user with id [${ id }]`);
        } else {
            $.response.setBody(JSON.stringify({
                "error": `Parameter id is missing`
            }));
        }
        
        break;
    default:
        $.response.setBody(JSON.stringify({
            "error": `Unsupported method [${ $.request.method }]` 
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
