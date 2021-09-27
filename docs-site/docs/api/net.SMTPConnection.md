---
title: $.net.SMTPConnection
---

$.net.SMTPConnection
===

`$.net.SMTPConnection` class for sending `$.net.Mail` objects via SMTP connection.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/19](https://github.com/SAP/xsk/issues/19)
- Module: [net/net.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/net/net.js)
- Status: `alpha`

## Sample Usage

```javascript
var net = $.net

//create email from JS Object and send
var mail = new net.Mail({
   sender: {address: "sender@sap.com"},
   to: [{ name: "John Doe", address: "john.doe@sap.com", nameEncoding: "US-ASCII"}, {name: "Jane Doe", address: "jane.doe@sap.com"}],
   cc: ["cc1@sap.com", {address: "cc2@sap.com"}],
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

var smtp = new net.SMTPConnection();
smtp.send(mail);
```

## Functions


| Function       | Description                                                                            | Returns     |
|----------------|----------------------------------------------------------------------------------------|-------------|
| **close()**    | Mocked. The SMTP Connection is now automatically closed after calling the send method. | _`void`_    |
| **isClosed()** | Mocked. The SMTP Connection is always closed.                                          | _`boolean`_ |
| **send(Mail)** | Accepts and sends the net.Mail class.                                                  | _`void`_    |
