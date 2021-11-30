var db = $.hdb;
var response = require('http/v4/response');
var assertTrue = require('utils/assert').assertTrue;

var connection = db.getConnection();
var result = connection.executeQuery('SELECT * FROM EXAMPLE.TEST_USERS');
var metadata = result.metadata;

// conn.executeUpdate('DROP TABLE EXAMPLE.TEST_USERS');
// conn.executeUpdate('DROP SCHEMA EXAMPLE');
response.println(JSON.stringify(metadata));

assertTrue(metadata != null);