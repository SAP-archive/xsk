var db = $.db;
var assertTrue = require('utils/assert').assertTrue;
var connection = db.getConnection();

assertTrue(connection != null && connection != undefined);