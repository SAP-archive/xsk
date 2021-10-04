---
title: $.net
---

$.net
===

`$.net` represents the network namespace with its fields.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/19](https://github.com/SAP/xsk/issues/19)
- Module: [net/net.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/net/net.js)
- Status: `alpha`

## Sample Usage


!!! Note
	Requires a running mail server. If `mailConfig` is not set the api defaults to a local mail server.
For more information please take a look [here](https://blogs.sap.com/2020/02/05/sending-e-mails-with-the-eclipse-dirigible-mail-api/).

```javascript
let net = $.net;

// Create a mail Object
let mail = new net.Mail({
   sender: {address: "sender@sap.com"},
   to: [{ name: "John Doe", address: "john.doe@sap.com", nameEncoding: "US-ASCII"}, {name: "Jane Doe", address: "jane.doe@sap.com"}],
   cc: [{address:"cc1@sap.com"}, {address: "cc2@sap.com"}],
   bcc: [{ name: "Jonnie Doe", address: "jonnie.doe@sap.com"}],
   subject: "subject",
   subjectEncoding: "UTF-8",
   parts: [ new net.Mail.Part({
       type: net.Mail.Part.TYPE_TEXT,
       text: "The body of the mail.",
       contentType: "text/plain",
       encoding: "UTF-8",
   })]
});

let mailConfig = {
    "mail.user": "test@gmail.com",
    "mail.password": "test",
    "mail.transport.protocol": "smtps",
    "mail.smtps.host": "smtp.gmail.com",
    "mail.smtps.port": "465",
    "mail.smtps.auth": "true"
};

let smtp = new net.SMTPConnection(mailConfig);
// Send the mail Object with SMPT
smtp.send(mail);

// Send the mail Object from the built-in send method. The send method is void in xsk. The response is mocked.
let returnValue = mail.send(mailConfig);
let response_msg = "MessageId = " + returnValue.messageId + ", final reply = " + returnValue.finalReply;

// The result is mocked to prevent errors
$.response.setBody(response_msg);
```

## Classes


| Classes                                        | Description                                               |
|------------------------------------------------|-----------------------------------------------------------|
| **[Mail](../$.net.Mail/)**                     | Class for constructing and sending multipart emails.      |
| **[SMTPConnection](../$.net.SMTPConnection/)** | Class for sending $.net.Mail objects via SMTP connection. |
| **[Destination](../$.net.Destination/)**       | Contains metadata, for example, host name and port number.|

## Properties


| Name | Description                      | Type           |
|------|----------------------------------|----------------|
| **http** | Provides access to the http API. | _`$.net.http`_ |

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


### Example
```javascript
let constantVal = $.net.http.OPTIONS;
```

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


### Example
```javascript
let statusCode = $.net.http.CONTINUE;
```
