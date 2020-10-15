exports.http = require("xsk/http/http");

var mail = require("mail/v4/client");

exports.Mail = function (mailObject) {
    this.to = mailObject.to || [];
    this.cc = mailObject.cc || [];
    this.bcc = mailObject.bcc || [];
    this.parts = mailObject.parts || [];

    this.subject = mailObject.subject;
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

    function adaptMailContentType(part){
        switch(part.contentType){
            case "text/html" : return "html";
            case "text/plain": return "plain"
        }
        return part.contentType;
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




