var net = $.net

// Create email from JS Object.
var mail = new net.Mail({
  sender: {address: "sender@sap.com"},
  to: [{ name: "John Doe", address: "john.doe@sap.com"}, {name: "Jane Doe", address: "jane.doe@sap.com"}],
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

// Set mail server configurations.
let mailConfig = {
  "mail.user": "<your-user>",
  "mail.password": "<your-password>",
  "mail.transport.protocol": "smtps",
  "mail.smtps.host": "<your-mail-provider-host>",
  "mail.smtps.port": "465",
  "mail.smtps.auth": "true"
};

var smtp = new net.SMTPConnection(mailConfig);
let returnValue = smtp.send(mail);

$.response.setBody(JSON.stringify(returnValue));