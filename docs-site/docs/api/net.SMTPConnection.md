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
   cc: [{address: "cc1@sap.com"}, {address: "cc2@sap.com"}],
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

var mailConfig = {
    "mail.user": "test@gmail.com",
    "mail.password": "test",
    "mail.transport.protocol": "smtps",
    "mail.smtps.host": "smtp.gmail.com",
    "mail.smtps.port": "465",
    "mail.smtps.auth": "true"
};

var smtp = new net.SMTPConnection(mailConfig);
smtp.send(mail);
```

## Class options (mailConfig)

Property     | Description | Type
------------ | ----------- | --------
**mail.user**   | The mailbox user | *string*
**mail.password**   | The mailbox password | *string*
**mail.transport.protocol**   | (optional) The mail transport protocol, default is *smtps* | *string*
**mail.smtps.host**   | The mail SMPTPS host | *string*
**mail.smtps.port**   | The mail SMPTPS port | *number as string*
**mail.smtps.auth**   | Enable/Disable mail SMPTPS authentication | *boolean as string*
**mail.smtp.host**   | The mail SMPTP host | *string*
**mail.smtp.port**   | The mail SMPTP port | *number as string*
**mail.smtp.auth**   | Enable/Disable mail SMPTP authentication | *boolean as string*

Addition mail client options can be found here:
- [SMTP/SMTPS](https://javaee.github.io/javamail/docs/api/com/sun/mail/smtp/package-summary.html)
- [IMAP](https://javaee.github.io/javamail/docs/api/com/sun/mail/imap/package-summary.html)
- [POP3](https://javaee.github.io/javamail/docs/api/com/sun/mail/pop3/package-summary.html)

## Functions


| Function       | Description                                                                            | Returns     |
|----------------|----------------------------------------------------------------------------------------|-------------|
| **close()**    | Mocked. The SMTP Connection is now automatically closed after calling the send method. | _`void`_    |
| **isClosed()** | Mocked. The SMTP Connection is always closed.                                          | _`boolean`_ |
| **send(Mail)** | Accepts and sends the net.Mail class.                                                  | _`void`_    |
