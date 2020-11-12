var database = require('xsk/db');
var response = require('http/v4/response');

var newAge = 30;

try {
	var connection = database.getConnection();
	response.println("Is Auto Commit enabled before setting it to false: " + connection.getAutoCommit());
	connection.setAutoCommit(false);
	response.println("Is Auto Commit enabled after setting it to false:  " + connection.getAutoCommit());

   	var statement = connection.prepareStatement("UPDATE TEST_USERS SET AGE=? WHERE ID=?");
   	statement.setTinyInt(1, newAge);
   	statement.setInteger(2, 1);
   	statement.executeUpdate();

   	if (newAge <= 69) {
   		connection.commit(); // surprisingly it's working even without calling commit()
   		response.println("\nTransaction was successfully commited!");
   	} else {
   		connection.rollback();
   		response.println("\nSorry dude, the transaction was rollbacked. We prefer to stay younger:))")
   	}

    statement.close();
} catch(e) {
	connection.rollback();
	response.println("\nTransaction was rollbacked: " + e.message);
} finally {
   	connection.close();
}

response.flush();
response.close();