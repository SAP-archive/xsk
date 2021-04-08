var database = require('xsk/hdb');
var response = require('http/v4/response');

try {
	var connection = database.getConnection();

	var insertStatement = "INSERT INTO TEST_USERS (NAME, STEPS, SALARY, RATING, IS_ADMIN) VALUES (?,?,?,?,?);";
	var insertResult = connection.executeUpdate(insertStatement, 'Moni', 89283824238459, 3000.55, 5.34, true);
	response.println("Insert Result: Query OK, " + insertResult + " row affected");

	var updateStatement = "UPDATE TEST_USERS SET DATE_REGISTERED=? WHERE ID BETWEEN ? AND ?";
	var updateResult = connection.executeUpdate(updateStatement, new Date(3020, 3, 3), 1, 2);
	response.println("Update Result: Query OK, " + updateResult + " row affected");

	response.println("is auto commit enabled: " + connection.getAutoCommit());
	connection.setAutoCommit(false);
	response.println("is auto commit enabled: " + connection.getAutoCommit());
	var updateResult = connection.executeUpdate(updateStatement, new Date(3020, 3, 3), 1, 2);
	response.println("Update Result: Query OK, " + updateResult + " row affected");
	connection.commit();
	var batchUpdateResult = connection.executeUpdate(updateStatement, [
		[new Date(3050, 5, 6), 2, 3],
		[new Date(3023, 1, 7), 4, 4]
	]);
	response.println("Warnings: " + connection.getLastWarning());
	response.println("Update Result: " + batchUpdateResult);
	//
	response.println("is auto commit enabled: " + connection.getAutoCommit());
} catch (e) {
	console.trace(e);
	response.println("error:" + e.message);
} finally {
	response.println("is connection closed: " + connection.isClosed());
	response.println("Warnings: " + connection.getLastWarning());
	connection.close();
	response.println("is connection closed: " + connection.isClosed());
}

response.flush();
response.close();