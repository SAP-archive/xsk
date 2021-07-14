/**
 * Created by SAP SE on 17.08.16.
 */


class ResponseAttachmentParser {

    static parse(responseAttachmentBuffer, numberOfAttachments) {
        var attachments = [];
        var lengthStart = 0;
        var lengthEnd = 4;

        for(let i = 1; i <= numberOfAttachments; i++) {
            let lengthBuffer = responseAttachmentBuffer.slice(lengthStart, lengthEnd);
            let length = lengthBuffer.readInt32LE();

            let contentStart = lengthEnd;
            let contentEnd = contentStart + length;
            let contentBuffer = responseAttachmentBuffer.slice(contentStart, contentEnd);

            attachments.push(contentBuffer);

            lengthStart = contentEnd;
            lengthEnd = lengthStart + 4;
        }

        return attachments;
    }

}

module.exports = ResponseAttachmentParser;
