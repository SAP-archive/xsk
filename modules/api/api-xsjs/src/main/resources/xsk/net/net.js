/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
exports.http = require("xsk/http/http");

var mail = require("mail/v4/client");

exports.Mail = function (mailObject) {
    this.to = mailObject.to || [];
    this.cc = mailObject.cc || [];
    this.bcc = mailObject.bcc || [];
    this.parts = mailObject.parts || [];

    this.subject = mailObject.subject;
    this.sender = mailObject.sender || { name: "", address: "", nameEncoding: "" };
    this.subjectEncoding = mailObject.subjectEncoding;


    this.send = function () {
        let mailClient = mail.getClient();
        let recipients = {
            to: this.to.map(e => e.address),
            cc: this.cc.map(e => e.address),
            bcc: this.bcc.map(e => e.address)
        };

        mailClient.send(this.sender.address, recipients, this.subject, this.parts[0].text, adaptMailContentType(this.parts[0]));

        //Mocked in order to avoid null exceptions.
        return {"finalReply": "NOT SUPPORTED", "messageId": 1};
    }
}
exports.Mail.Part = function (partObject) {
    this.alternative = partObject && partObject.alternative;
    this.alternativeContentType = partObject && partObject.alternativeContentType;
    this.contentId = partObject && partObject.contentId;
    this.contentType = partObject && partObject.contentType;
    this.data = partObject && partObject.data;
    this.encoding = partObject && partObject.encoding;
    this.fileName = partObject && partObject.fileName;
    this.fileNameEncoding = partObject && partObject.fileNameEncoding;
    this.text = partObject && partObject.text;
    this.type = partObject && partObject.type;
};

//The values of the properties are unknown and not actually needed. Mocked in order to avoid null exceptions.
exports.Mail.Part.TYPE_TEXT = "ToDO";
exports.Mail.Part.TYPE_ATTACHMENT = "ToDO";
exports.Mail.Part.TYPE_INLINE = "ToDO";

exports.Destination = function(packageName, objectName) {
  let destination = exports.http.readDestination(packageName, objectName);
  this.host = destination.host || "";
  this.port = destination.port || "";
}

exports.SMTPConnection = function() {
  this.send = function(mailClass) {
    let mailConfig = {
      "mail.transport.protocol": "smtp",
      "mail.smtp.port": "587"
    };
    let mailClient = mail.getClient(mailConfig);
    let recipients = {
      to: mailClass.to.map(e => e.address),
      cc: mailClass.cc.map(e => e.address),
      bcc: mailClass.bcc.map(e => e.address)
    };

    mailClient.send(mailClass.sender.address, recipients, mailClass.subject, mailClass.parts[0].text, adaptMailContentType(mailClass.parts[0]));
  }
  this.close = function() {
    // Empty. Called automatically inside MailFacade
  }
  this.isClosed = function() {
    // The connection is being closed automatically
    return true;
  }
}

function adaptMailContentType(part){
  switch(part.contentType){
    case "text/html" : return "html";
    case "text/plain": return "plain"
  }
  return part.contentType;
}