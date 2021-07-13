// --------------------------------------------------------------------------
//
// Default Configuration
//
// --------------------------------------------------------------------------
var path = require('path');


module.exports = {
    directories: {
        "xs2-app": '',
        "migration": 'migration',
        "xs1-src": path.join('migration','orig-src'),
        "default-db-dir": 'db',
        "default-web-dir": 'web',
        "default-xsjs-dir": 'xsjs',
        "std-java-dir": "sapjvm_8_jre"
    },
    urls: {}
};