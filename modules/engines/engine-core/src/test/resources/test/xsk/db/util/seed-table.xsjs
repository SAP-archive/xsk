var database = require('xsk/db');
var response = require('http/v4/response');


var sql = "INSERT INTO TEST_USERS (NAME, STEPS, SALARY, RATING, IS_ADMIN, BIO, AVG_GRADE, DESCRIPTION, NCLOB_TEXT) VALUES " +
		"('Tony', 89283824238459, 3000.55, 5.9, 1, 'Tony bio.....', 5.666, 'малко български', 'българскии'), " +
		"('Pesho', 89224238459, 800.69, 5, 0, 'Pesho bio.......', 5.55555, 'още български', 'българскии'), " +
		"('Alex', 89224238459, 400.69, 5, 0, 'Alex bio......', 5.55555555555555555555555555555555555, 'и още малко', 'българскии');";

try {
	var connection = database.getConnection();
   	var statement = connection.prepareStatement(sql);
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