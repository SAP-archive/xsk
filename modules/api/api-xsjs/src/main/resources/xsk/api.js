/*
 * HANA XS Classic Bridge
 */

var $ = {};

$.db = require('xsk/db/db');
$.hdb = require('xsk/hdb/hdb');
$.net = require('xsk/net/net');
$.import = require("xsk/import/import").import;
$.trace = require('xsk/trace/trace');
$.security = require('xsk/security/security');
$.util = require('xsk/util/util');

try {
    let dResponse = require('xsk/web/web').WebResponse;
    let dRequest = require('xsk/web/web').WebRequest;
    $.session = require('xsk/session/session');
    $.request = new dRequest();
    $.response = new dResponse();
} catch (e) {
    // $.trace .warning("Caught exception. Api.js is being used by xsk job.")
}

$;