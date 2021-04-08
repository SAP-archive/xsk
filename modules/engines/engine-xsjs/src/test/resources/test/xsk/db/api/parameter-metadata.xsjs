var database = require('xsk/db');
var response = require('http/v4/response');

var sql = "INSERT INTO TEST_USERS (NAME, STEPS, SALARY, RATING, IS_ADMIN, BIO, AVG_GRADE, LUCKY_NUMBER) VALUES (?,?,?,?,?,?,?,?)";

try {
	var connection = database.getConnection();
   	var statement = connection.prepareStatement(sql);
   	var parameterMetaData = statement.getParameterMetaData();
    var count = parameterMetaData.getParameterCount();
    response.println("ParameterMetaData count: " + count);

    for (var i = 1; i <= count; i++) {
 		response.println("\nType Name: " + parameterMetaData.getParameterTypeName(i));
        response.println("TYPE: " + parameterMetaData.getParameterType(i));
        response.println("Parameter Mode: " + parameterMetaData.getParameterMode(i));
        response.println("Precision: " + parameterMetaData.getPrecision(i));
        response.println("Scale: " + parameterMetaData.getScale(i));
        response.println("Is Nullable: " + parameterMetaData.isNullable(i));
        response.println("Is Signed: " + parameterMetaData.isSigned(i));
	}

   	statement.close();
} catch(e) {
   console.trace(e);
   response.println(e.message);
} finally {
   connection.close();
}

response.flush();
response.close();