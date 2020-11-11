var database = require('xsk/hdb');
var response = require('http/v4/response');

try {
	var connection = database.getConnection();
	response.println("hopaa1: ");
	var resultSet = connection.executeQuery('SELECT * FROM TEST_USERS');
	var resultSet1 = connection.executeQuery('SELECT * FROM TEST_USERS WHERE AVG_GRADE < ?', 5.6);
	var resultSet2 = connection.executeQuery('SELECT * FROM TEST_USERS WHERE NAME LIKE ?', 'Tony');
	//	   	response.println("hopaa: " + JSON.stringify(resultSet[0]));
	// JSON.stringify breaks for BigDecimal and Clob
	response.println("hopaa: " + resultSet[0][11].toString());
	response.println("ResultSet1: " + resultSet1.length.toString());
	response.println("ResultSet2: " + resultSet2.length.toString());
	var iterator = resultSet.getIterator();
	//	response.println("iterator: "  + JSON.stringify(iterator.value()));
	var index = 0;
	while (iterator.next()) {
		var rowValue = iterator.value();
		response.println("Row " + index);
		index++;
	}
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