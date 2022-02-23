export class RepositoryRequest {
    constructor(attachmentCount, requestMethod) {
        this._attachmentCount = attachmentCount;
        this._requestMethod = requestMethod;
    }

    get contentLength() {
        return Buffer.from(JSON.stringify(this._requestMethod)).length;
    }
}
