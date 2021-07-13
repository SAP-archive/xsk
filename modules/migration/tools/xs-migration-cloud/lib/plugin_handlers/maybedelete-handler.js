var log = require('../log-util');
var utils = require('./plugin-utils');

function MaybedeleteFileHandler() {

    this.handleFile = function(file, globalContext, callback) {
        
        log.debug("maybe delete handler");
        var ext = utils.getExtension(file.RunLocation);
        var name = utils.getObjectname(file.RunLocation);
        switch(ext) {
            case 'db':
                if (name.toLowerCase() === 'thumbs') {
                    log.debug("Deleting " + file.RunLocation);
                    file.doNotWriteContent();;
                }
                break;
        }
        callback(null, file);
    }
}

module.exports = new MaybedeleteFileHandler();
