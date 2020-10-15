var database = require('xsk/db');
var response = require('http/v4/response');

var tableName = "TEST_USERS";
var columnName = "salary";
var value = 32767.89384;
var id = 1;

var sql = "UPDATE " + tableName + " SET " + columnName  + "=?" + " WHERE ID=?";

try {
	var connection = database.getConnection();
   	var statement = connection.prepareStatement(sql);
   	statement.setDouble(1, value);
   	statement.setInteger(2, id);
   	var result = statement.executeUpdate();
   	response.println("Query OK, " + result + " rows affected");
	statement.close();
} catch(e) {
   console.trace(e);
   response.println(e.message);
} finally {
   connection.close();
}

response.flush();
response.close();