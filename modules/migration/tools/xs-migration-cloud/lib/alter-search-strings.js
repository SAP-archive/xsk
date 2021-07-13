// --------------------------------------------------------------------------
//
// Serch strings for ALTER TABLE statements in xsjs, xsjslib, and hdbprocedure code
// --------------------------------------------------------------------------

var mc = require('./message-categories');
var msgs = require('./messages');

module.exports.ALTER_SEARCH_STRINGS = [
{
    search: /ALTER\s+TABLE/i,
    message: msgs.getMessage(mc.HDI, 11)
},
{
    search: /ALTER\s+SEQUENCE/i,
    message: msgs.getMessage(mc.HDI, 13)
},
{
    search: /ALTER\s+SYSTEM/i,
    message: msgs.getMessage(mc.HDI, 12)
},
{
    search: /ALTER\+USER/i,
    message: msgs.getMessage(mc.HDI, 12)
},
{
    search: /GRANT\s+ALTER\s+ON/i,
    message: msgs.getMessage(mc.SECURITY, 11)
}
]