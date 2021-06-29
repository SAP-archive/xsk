var net = $.net;

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
// TODO upgrade mockito's versions in dirigible and xsk to mock the static methods
//smtp.send(mail);
//var returnValue = mail.send();

to = mail.to.map(e => e.address)

to !== "" && to !== undefined && smtp.isClosed()