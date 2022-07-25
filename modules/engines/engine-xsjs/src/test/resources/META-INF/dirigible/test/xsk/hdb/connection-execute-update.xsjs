 let assertTrue = require('utils/assert').assertTrue;
 let connection;
 try{
  connection = $.hdb.getConnection();
  let insertStatement = "INSERT INTO EXAMPLE.TEST_USERS (ID, FIRSTNAME, LASTNAME, AGE, WEIGHT) VALUES (?,?,?,?,?)";
  let insertResult = connection.executeUpdate(insertStatement, 4, 'PETKO', 'MOMCHEV', 24 ,89);

  let updateStatement = "UPDATE EXAMPLE.TEST_USERS SET WEIGHT=? WHERE ID = 4";
  let updateResult = connection.executeUpdate(updateStatement, 75);

  assertTrue(insertResult ===1 && updateResult > 0);
 } finally {
  if(connection){
    connection.close();
  }
 }