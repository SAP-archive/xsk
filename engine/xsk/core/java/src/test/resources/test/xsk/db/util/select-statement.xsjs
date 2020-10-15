var database = require('xsk/db');
var response = require('http/v4/response');

try {
	var connection = database.getConnection();
   	var statement = connection.prepareStatement("SELECT * FROM TEST_USERS");
   	statement.execute();
   	var resultSet = statement.getResultSet();

   	while (resultSet.next()) {
       response.println(
       		"ID: " + resultSet.getInteger(1) +
       		"\nName: " + resultSet.getString(2) +
       		"\nSalary: " + resultSet.getDouble(4).toFixed(2) +
       		"\nRating: " + resultSet.getFloat(5).toFixed(2) +
       		"\nIs Admin: " + resultSet.getString(6) +
       		"\nSteps: " + resultSet.getBigInt(3) +
       		"\nDate Registered: " + resultSet.getDate(7) +
       		"\nTime Registered: " + resultSet.getTime(8) +
       		"\nTimestamp: " + resultSet.getTimestamp(9) +
       		"\nBio: " + resultSet.getText(10) +
       		"\nLucky Number: " + resultSet.getInteger(11) +
       		"\nAverage Grade: " + resultSet.getDecimal(12) +
       		"\nAge: " + resultSet.getInteger(13) + "\n----------------------------------------");
   }

   response.println("Is ResultSet closed before invoking close(): " + resultSet.isClosed());
   resultSet.close();
   response.println("Is ResultSet closed after invoking close(): " + resultSet.isClosed());
   statement.close();
} catch(e) {
   console.trace(e);
   response.println(e.message);
} finally {
   connection.close();
}

response.flush();
response.close();