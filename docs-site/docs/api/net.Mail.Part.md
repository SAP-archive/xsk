---
title: $.net.Mail.Part
---

$.net.Mail.Part
===

`$.net.Mail.Part` class for constructing email parts.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/19](https://github.com/SAP/xsk/issues/19)
- Module: [net/net.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/net/net.js)
- Status: `alpha`

## Sample Usage

!!! Note
	Requires a running mail server. If `mailConfig` is not set the api defaults to a local mail server.
	For more information please take a look [here](https://blogs.sap.com/2020/02/05/sending-e-mails-with-the-eclipse-dirigible-mail-api/).

```javascript
var files = require("io/v4/files");

// Getting the byte array of the attachment.
var xskLogo = files.readBytes('path-to-file/xsk-logo.png');

// Create an attachment $.net.Mail.Part from JSObject.
var attachmentPart = new $.net.Mail.Part({
    type: $.net.Mail.Part.TYPE_ATTACHMENT,
    data: xskLogo, 
    contentType: "image/png",
    fileName: "xsk-logo.png",
    fileNameEncoding: "UTF-8"
});

// Getting the byte array of the inline image.
var sapLogo = files.readBytes('path-to-file/sap-logo.png');

// Create an inline $.net.Mail.Part from JSObject.
var inlinePart = new $.net.Mail.Part({
    type: $.net.Mail.Part.TYPE_INLINE,
    data: sapLogo,
    contentType: "image/png",
    contentId: "IMAGE1_ID", // The content id is used in the text part to display the image in the mail body.
    fileName: "sap-logo.png",
    fileNameEncoding: "UTF-8"
});

// Create a text $.net.Mail.Part object.
var textPart = new $.net.Mail.Part({
    type: $.net.Mail.Part.TYPE_TEXT,
    text: "<html><head></head><body><h1>This is XSK</h1><br><img src=\"cid:IMAGE1_ID\"><br></body></html>",
    contentType: "text/html",
    encoding: "UTF-8"
});

// Create an $.net.Mail object.
var mail = new $.net.Mail({
    sender: {address: "sender@sap.com"},
    to: [{name: "to1", address: "to1@sap.com"}, {name: "to2", address: "to2@sap.com"}],
    cc: [{ name: "cc1", address: "cc1@sap.com"}, { name: "cc2", address: "cc2@sap.com"}],
    bcc: [{ name: "bcc1", address: "bcc1@sap.com"}],
    subject: "subject",
    subjectEncoding: "UTF-8"
});

mail.parts.push(attachmentPart, inlinePart, textPart);

// Set mail server configurations.
let mailConfig = {
    "mail.user": "<your-user>",
    "mail.password": "<your-password>",
    "mail.transport.protocol": "smtps",
    "mail.smtps.host": "<your-mail-provider-host>",
    "mail.smtps.port": "465",
    "mail.smtps.auth": "true"
};

let returnValue = mail.send(mailConfig);
$.response.setBody(JSON.stringify(returnValue));
```

## Constructors

```javascript
new $.net.Mail.Part(PartObject)
```

## Parameters

| Parameter Name     | Description                                             | Required     | Type       |
|--------------------|---------------------------------------------------------|--------------|------------|
| **PartObject**     | JS object containing elements of a Part in JSON format. | _`optional`_ | _`object`_ |

## Properties

| Name                       | Description                                                     | Type               |
|----------------------------|-----------------------------------------------------------------|--------------------|
| **alternative**            | Property used for initializing "alternative" property of the text $.net.Mail.Part object. | _`string`_             |
| **alternativeContentType** | Property used for initializing "alternativeContentType" property of the text $.net.Mail.Part object. If this property is not set, the default value is "text/plain". | _`string`_             |
| **contentId**              | Property used for initializing "contentId" property of the inline $.net.Mail.Part object. | _`string`_             |
| **contentType**            | Property used for initializing "contentType" property of the $.net.Mail.Part object. | _`string`_             |
| **data**                   | Property used for initializing "data" property of the attachment and inline $.net.Mail.Part object. | _`string/ArrayBuffer`_ |
| **encoding**               | Property used for initializing "encoding" property of the text $.net.Mail.Part object. It also applies to alternative text. If this property is not set, the default value is "UTF-8". | _`string`_             |
| **fileName**               | Property used for initializing "fileName" property of the attachment and inline $.net.Mail.Part object. It contains the full name of the file with the extension, example "file.txt". | _`string`_             |
| **fileNameEncoding**       | Property used for initializing "fileNameEncoding" property of the attachment and inline $.net.Mail.Part object. It is the encoding of the filename. If this property is not set, the default value is "UTF-8". | _`string`_             |
| **text**                   | Property used for initializing "text" property of the text $.net.Mail.Part object. | _`string`_             |
| **type**                   | Property used for initializing "type" property of the $.net.Mail.Part object. If this property is not set, the part will not be set. It should be one of the following: $.net.Mail.Part.TYPE_TEXT $.net.Mail.Part.TYPE_ATTACHMENT $.net.Mail.Part.TYPE_INLINE | _`string`_             |

See common content types [here](https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types).
