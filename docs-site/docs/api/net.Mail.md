---
title: $.net.Mail
---

$.net.Mail
===

`$.net.Mail` class for constructing and sending multi-part emails.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/19](https://github.com/SAP/xsk/issues/19)
- Module: [net/net.js](https://github.com/SAP/xsk/tree/main/modules/api/api-xsjs/src/main/resources/xsk/net/net.js)
- Status: `alpha`

## Sample Usage 

```javascript
//create empty $.net.Mail
var mail = new $.net.Mail();
```

```javascript
let net = $.net;
let response = require('http/v4/response');

//create email from JS Object and send
let mail = new $.net.Mail({
    sender: {address: "sender@sap.com"},
    to: [{ name: "John Doe", address: "john.doe@sap.com", nameEncoding: "US-ASCII"}, {name: "Jane Doe", address: "jane.doe@sap.com"}],
    cc: ["cc1@sap.com", {address: "cc2@sap.com"}],
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

//return value is mocked as send is now a void method
let returnValue = mail.send();
let response_msg = "MessageId = " + returnValue.messageId + ", final reply = " + returnValue.finalReply;

response.println(response_msg);
```

```javascript
let net = $.net;
var response = require('http/v4/response');

var mail = new net.Mail();
mail.subject = "About what the email is."
mail.subjectEncoding = "UTF-8";
mail.sender = {address: "from@sap.com"};
mail.to.push({name: "John Doe", address: "john.doe@sap.com", nameEncoding: "US-ASCII"});
mail.cc = [{name: "Cc1", address: "cc1@recepient.com"}, {address: "cc2@recepient.com"}];
mail.parts.push(new net.Mail.Part({type: net.Mail.Part.TYPE_TEXT, text: 'Text'}));

var returnValue;
var response = "";

try {
    //the return value is mocked as send is now a void method
    returnValue = mail.send();
} catch(error) {
    response = "Error occurred:" + error.message;
}
response_msg = "MessageId = " + returnValue.messageId + ", final reply = " + returnValue.finalReply;

response.println(response_msg);
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
| **[Part](../$.net.Mail.Part)** | Class for constructing email parts. | 

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
| **send()**   | Void method that returns a mocked object containing two properties: 'messageId' and 'finalReply' for compatibility reasons.|  _`object`_  |