/**
 * Created by SAP SE on 05.08.16.
 */
    
var RepositoryHeader = require('./repository-header');
var ResponseAttachmentParser = require('./response-attachment-parser');

class RepositoryResponse {
    
    constructor(responseBuffer){
       
        var headerBuffer = responseBuffer.slice(0,14);
        this._header = RepositoryHeader.fromBuffer(headerBuffer);

        var contentEnd = 14 + this._header.contentLength;
        this._contentBuffer = responseBuffer.slice(14, contentEnd);
        
        this._attachments = [];
        if(this._header.attachmentCount > 1){
            let attachmentBuffer = responseBuffer.slice(contentEnd, responseBuffer.length);
            this._attachments = ResponseAttachmentParser.parse(attachmentBuffer, this._header.attachmentCount);
        }
        
    }
       
    get header() {
        return this._header;
    }
    
    
    get content() {
        var contentString = this._contentBuffer.toString('utf-8');
        return JSON.parse(contentString);
    }
    
    
    get attachments() {
        return this._attachments;
    }
    
    
}

module.exports = RepositoryResponse;