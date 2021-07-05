var hdb = require('xsk/hdb');
var response = require('http/v4/response');

var connection = hdb.getConnection();
var result = connection.executeQuery('SELECT * FROM TEST_USERS');
var metadata = result.metadata;

// conn.executeUpdate('DROP TABLE EXAMPLE.TEST_METADATA');
// conn.executeUpdate('DROP SCHEMA EXAMPLE');
response.println(JSON.stringify(metadata));