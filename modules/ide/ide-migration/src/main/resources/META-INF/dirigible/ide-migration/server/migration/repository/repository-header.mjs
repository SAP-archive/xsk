import { Utils } from "../utils";

export class RepositoryHeader {
    constructor(attachmentCount, contentLength) {
        this._protocol = "repoV2";
        this._attachmentCount = attachmentCount;
        this._contentLength = contentLength;
    }

    get protocol() {
        return this._protocol;
    }

    get attachmentCount() {
        return this._attachmentCount;
    }

    get contentLength() {
        return this._contentLength;
    }

    static fromBuffer(buffer) {
        let attachmentCountBuffer = buffer.slice(6, 10);

        let attachmentCount = Utils.byteArrayToInt(attachmentCountBuffer);

        let contentLengthBuffer = buffer.slice(10, 14);
        let contentLength = Utils.byteArrayToInt(contentLengthBuffer);
        let actualAttachmentCount = Math.round(attachmentCount / 2);

        return new RepositoryHeader(actualAttachmentCount, contentLength);
    }
}
