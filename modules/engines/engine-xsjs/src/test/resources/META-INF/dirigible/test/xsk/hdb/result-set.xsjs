var hdb = $.hdb;
var response = require('http/v4/response');
var assertTrue = require('utils/assert').assertTrue;

try {
	var connection = hdb.getConnection();
	response.println(JSON.stringify(connection));

	var result = connection.executeQuery('SELECT * FROM EXAMPLE.TEST_USERS');
	response.println(result.length.toString());
	response.println(JSON.stringify(result));
	// result can be accessed as if it were a JSON array with a structure similar to [{FLAVOR:"CHOCOLATE", PRICE:9.50, QUANTITY:2},{FLAVOR:"LEMON", PRICE:9.99, QUANTITY:8}]
	// get the first row of the result set
	var row0 = result[0];


	//	 get the value of a column in the row
	response.println(row0['FIRSTNAME'] + " " + row0['LASTNAME'] + " " + row0['AGE']);
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

assertTrue(row0['FIRSTNAME'] === "IVAN" && row0['LASTNAME']==="VOLKOV" && row0['AGE']===29);
