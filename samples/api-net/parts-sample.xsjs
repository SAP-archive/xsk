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