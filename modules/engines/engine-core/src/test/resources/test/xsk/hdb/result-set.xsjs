var hdb = require('xsk/hdb');
var response = require('http/v4/response');

try {
	var connection = hdb.getConnection();
	response.println(JSON.stringify(connection));

	var result = connection.executeQuery('SELECT * FROM TEST_USERS');
	//	response.println(result.length.toString());
	//	response.println(JSON.stringify(result));
	// result can be accessed as if it were a JSON array with a structure similar to [{FLAVOR:"CHOCOLATE", PRICE:9.50, QUANTITY:2},{FLAVOR:"LEMON", PRICE:9.99, QUANTITY:8}]
	// get the first row of the result set
	var row0 = result[0];


	//	 get the value of a column in the row
	response.println(row0['NAME'] + " " + row0.NAME + " " + row0[1]);
	//
	// cycles through each row of the Result Set and gets the value of a specific column.
	var names = '';
	for (var row = 0; row < result.length; row++) {
		names += result[row]['NAME'] + ', ';
	}
	response.println(names);
} catch (e) {
	response.println(e.message);
	connection.close();
}