/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
exports.http = require("xsk/http/http");
var mail = require("mail/v4/client");

exports.Mail = function (mailObject) {
  mailObject = mailObject || {};
  this.to = mailObject.to || [];
  this.cc = mailObject.cc || [];
  this.bcc = mailObject.bcc || [];
  this.parts = mailObject.parts || [];

  this.subject = mailObject.subject;
  this.sender = mailObject.sender || {name: "", address: "", nameEncoding: ""};
  this.subjectEncoding = mailObject.subjectEncoding;

  this.send = function (mailConfig) {
    let mailClient = mail.getClient(mailConfig);

    let recipients = {
      to: this.to.map(e => e.address),
      cc: this.cc.map(e => e.address),
      bcc: this.bcc.map(e => e.address)
    };

    let result = mailClient.sendMultipart(this.sender.address, recipients, this.subject, this.parts);
    return {"finalReply": result.get("finalReply"), "messageId": result.get("messageId")};
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

exports.Mail.Part.TYPE_TEXT = "text";
exports.Mail.Part.TYPE_ATTACHMENT = "attachment";
exports.Mail.Part.TYPE_INLINE = "inline";

exports.Destination = function (packageName, objectName) {
  let destination = exports.http.readDestination(packageName, objectName);
  this.host = destination.host || "";
  this.port = destination.port || "";
}

exports.SMTPConnection = function (mailConfig) {
  this.send = function (mailClass) {
    let mailClient = mail.getClient(mailConfig);

    let recipients = {
      to: mailClass.to.map(e => e.address),
      cc: mailClass.cc.map(e => e.address),
      bcc: mailClass.bcc.map(e => e.address)
    };

    let result = mailClient.sendMultipart(mailClass.sender.address, recipients, mailClass.subject, mailClass.parts);
    return {"finalReply": result.get("finalReply"), "messageId": result.get("messageId")};
  }

  this.close = function () {
    // Empty. Called automatically inside MailFacade
  }

  this.isClosed = function () {
    // The connection is being closed automatically
    return true;
  }
}
