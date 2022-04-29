var db = $.hdb;
 var response = require('http/v4/response');
 var assertTrue = require('utils/assert').assertTrue;
   var connection = db.getConnection();
   var insertStatement = "INSERT INTO EXAMPLE.TEST_USERS (ID, FIRSTNAME, LASTNAME, AGE, WEIGHT) VALUES (?,?,?,?,?)";
 	var insertResult = connection.executeUpdate(insertStatement, 4, 'PETKO', 'MOMCHEV', 24 ,89);
 	response.println("Insert Result: Query OK, " + insertResult + " row affected");

 	var updateStatement = "UPDATE EXAMPLE.TEST_USERS SET WEIGHT=? WHERE ID = 4";
 	var updateResult = connection.executeUpdate(updateStatement, 75);
 	response.println("Update Result: Query OK, " + updateResult + " row affected");

 	response.println("is auto commit enabled: " + connection.getAutoCommit());
 	connection.setAutoCommit(false);
 	response.println("is auto commit enabled: " + connection.getAutoCommit());

 	response.println("is auto commit enabled: " + connection.getAutoCommit());


 	response.println("is connection closed: " + connection.isClosed());
 	response.println("Warnings: " + connection.getLastWarning());
 	response.println("is connection closed: " + connection.isClosed());

  assertTrue(insertResult ===1 && updateResult > 0);