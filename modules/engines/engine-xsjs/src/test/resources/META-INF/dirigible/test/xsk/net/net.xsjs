let net = $.net;
var assertTrue = require('utils/assert').assertTrue;
let mail = new net.Mail({
  sender: {address: "sender@sap.com"},
  to: [{name: "John Doe", address: "john.doe@sap.com", nameEncoding: "US-ASCII"}, {name: "Jane Doe", address: "jane.doe@sap.com"}],
  cc: [{address: "cc1@sap.com"}, {address: "cc2@sap.com"}],
  bcc: [{name: "Jonnie Doe", address: "jonnie.doe@sap.com"}],
  subject: "subject",
  subjectEncoding: "UTF-8",
  parts: [new net.Mail.Part({
    type: net.Mail.Part.TYPE_TEXT,
    text: "The body of the mail.",
    contentType: "text/plain",
    encoding: "UTF-8",
  })]
});

let smtp = new net.SMTPConnection();

let to = mail.to.map(e => e.address)
let cc = mail.cc.map(e => e.address)
let bcc = mail.bcc.map(e => e.address)

assertTrue(to !== "" && to !== undefined && cc !== "" && cc !== undefined && bcc !== "" && bcc !== undefined && smtp.isClosed());
