---
title: $.net.Mail
---

$.net.Mail
===

`$.net.Mail` class for constructing and sending multi-part emails.

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

// Create email from JS Object.
let mail = new $.net.Mail({
    sender: {address: "sender@sap.com"},
    to: [{ name: "John Doe", address: "john.doe@sap.com"}, {name: "Jane Doe", address: "jane.doe@sap.com"}],
    cc: [{address: "cc1@sap.com"}, {address: "cc2@sap.com"}],
    bcc: [{ name: "Jonnie Doe", address: "jonnie.doe@sap.com"}],
    subject: "subject",
    subjectEncoding: "UTF-8",
    parts: [ new $.net.Mail.Part({
        type: $.net.Mail.Part.TYPE_TEXT,
        text: "The body of the mail.",
        contentType: "text/plain",
        encoding: "UTF-8",
    })]
});


let returnValue = mail.send();
$.response.setBody(JSON.stringify(returnValue));
```

## Constructors

```javascript
new $.net.Mail(MailObject)
```

## Parameters

| Parameter Name | Description                                                                                                                                                     | Required     | Type       |
|----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------|------------|
| **MailObject** | JS object containing different part of the email in JSON format. Supported properties are {'sender', 'to', 'cc', 'bcc', 'subject', 'subjectEncoding', 'parts'}. | _`optional`_ | _`object`_ |

## Classes


| Classes      | Description                                           |
|--------------|-------------------------------------------------------|
| **[Part](../net/net.Mail.Part.md)** | Class for constructing email parts. | 

## Properties


| Name                | Description                                            | Type   |
|---------------------|--------------------------------------------------------|--------|
| **bcc**             | Property used for initializing "bcc" property of the mail. It is an array containing objects with three properties - name, encoding and address with address being required.| _`array`_  |
| **cc**              | Property used for initializing "cc" property of the mail. It is an array containing objects with three properties - name, encoding and address with address being required. | _`array`_  |
| **parts**           | Property used for initializing "parts" property of the mail. It is an array containing $.net.Mail.Part() objects. | _`array`_  |
| **sender**          | Property used for initializing "sender" property of the mail. It is an array containing objects with three properties - name, encoding and address with address being required. This property is required or the mail won't be sent.| _`object`_ |
| **subject**         | Property used for initializing "subject" property of the mail. | _`string`_ |
| **subjectEncoding** | Property used for initializing "subjectEncoding" property of the mail. It is the encoding of the subject. If this property is not set, the default value is "UTF-8". | _`string`_ |
| **to**              | Property used for initializing "to" property of the mail. It is an array containing objects with three properties - name, encoding and address with address being required.| _`array`_  |

## Functions


| Function     | Description                                                                                                                |  Returns     |
|--------------|----------------------------------------------------------------------------------------------------------------------------| -------------|
| **send()**   | method that returns an object containing two properties: 'messageId' and 'finalReply'.|  _`object`_  |


Addition mail client options can be found here:
- [SMTP/SMTPS](https://javaee.github.io/javamail/docs/api/com/sun/mail/smtp/package-summary.html)
- [IMAP](https://javaee.github.io/javamail/docs/api/com/sun/mail/imap/package-summary.html)
- [POP3](https://javaee.github.io/javamail/docs/api/com/sun/mail/pop3/package-summary.html)
