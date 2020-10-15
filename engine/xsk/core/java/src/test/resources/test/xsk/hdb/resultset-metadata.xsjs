var hdb = require('xsk/hdb');
var response = require('http/v4/response');

var connection = hdb.getConnection();
var result = connection.executeQuery('SELECT * FROM TEST_USERS');
var metadata = result.metadata;

response.println(JSON.stringify(metadata));