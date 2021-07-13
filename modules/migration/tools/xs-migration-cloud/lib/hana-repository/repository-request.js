/**
 * Created by SAP SE on 05.08.2016.
 */
    
var RepositoryHeader = require('./repository-header');    
    

class RepositoryRequest {

    
    constructor(attachmentCount, requestMethod){
        this._attachmentCount = attachmentCount;
        this._requestMethod = requestMethod;
    }
    
    
    get contentLength() {
        return Buffer.from(JSON.stringify(this._requestMethod)).length;
    }
    
    
    toBuffer(){
        var header = new RepositoryHeader(this._attachmentCount, this.contentLength);
        var headerBuffer = header.toBuffer();
        
        var requestMethodBuffer = Buffer.from(JSON.stringify(this._requestMethod));
                
        return Buffer.concat([headerBuffer, requestMethodBuffer]);
        
    }


}

module.exports = RepositoryRequest;