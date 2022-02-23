import { RepositoryHeader } from "./repository-header";
import { ResponseAttachmentParser } from "./response-attachment-parser";

let utf8 = org.eclipse.dirigible.api.v3.utils.UTF8Facade;

export class RepositoryResponse {
    constructor(responseBuffer) {
        let headerBuffer = responseBuffer.slice(0, 14);
        this._header = RepositoryHeader.fromBuffer(headerBuffer);
        let contentEnd = 14 + this._header.contentLength;
        this._contentBuffer = responseBuffer.slice(14, contentEnd);

        this._attachments = [];
        if (this._header.attachmentCount > 1) {
            let attachmentBuffer = responseBuffer.slice(contentEnd, responseBuffer.length);
            this._attachments = ResponseAttachmentParser.parse(attachmentBuffer, this._header.attachmentCount);
        }
    }

    get header() {
        return this._header;
    }

    get content() {
        let contentString = utf8.decode(this._contentBuffer);
        return JSON.parse(contentString);
    }

    get attachments() {
        return this._attachments;
    }
}
