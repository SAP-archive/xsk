var database = $.hdb;
var response = require('http/v4/response');
var assertTrue = require('utils/assert').assertTrue;

try {
	var connection = database.getConnection();
	response.println("hopaa1: ");
	var resultSet = connection.executeQuery('SELECT * FROM EXAMPLE.TEST_USERS');
	var resultSet1 = connection.executeQuery('SELECT * FROM EXAMPLE.TEST_USERS WHERE AGE < ?', 30);
	var resultSet2 = connection.executeQuery('SELECT * FROM EXAMPLE.TEST_USERS WHERE FIRSTNAME LIKE ?', 'IVAN');

	response.println("hopaa: " + JSON.stringify(resultSet[0]));
	// JSON.stringify breaks for BigDecimal and Clob
	response.println("first entry in the table " + resultSet[0]["FIRSTNAME"]);
	response.println("ResultSet1: " + resultSet1.length.toString());

	response.println("ResultSet2: " + resultSet2.length.toString());
	// there is no close() function in resultSet class from hdb namespace
	//   	resultSet.close();
} catch (e) {
	console.trace(e);
	response.println(e.message);
} finally {
   connection.close();

}



response.flush();
response.close();

assertTrue(resultSet[0]["FIRSTNAME"] == "IVAN" && resultSet2.length.toString() == 2);
