var database = require('xsk/db');
var response = require('http/v4/response');

var tablename = "TEST_USERS";

var sql = "CREATE TABLE " + tablename + "(" +
    "ID INTEGER NOT NULL AUTO_INCREMENT, " +
    "NAME VARCHAR(20), " +
    "STEPS BIGINT, "  +
    "SALARY DOUBLE UNSIGNED, " +
    "RATING FLOAT(5), " +
    "IS_ADMIN BOOLEAN, " +
    "DATE_REGISTERED DATE DEFAULT CURRENT_DATE, " +
    "TIME_REGISTERED TIME DEFAULT CURRENT_TIME, " +
    "DATE_TIME_REGISTERED TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
    "BIO TEXT, " +
    "LUCKY_NUMBER SMALLINT UNSIGNED, " +
    "AVG_GRADE DECIMAL(34, 2), " +

    "PRIMARY KEY (ID)" +
");";

try {
	var connection = database.getConnection();
   	var statement = connection.prepareStatement(sql);
   	var result = statement.executeUpdate();
   	response.println("Query OK, " + result + " rows affected");
	statement.close();
} catch(e) {
   response.println(e.message);
} finally {
   connection.close();
}

response.flush();
response.close();