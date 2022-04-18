var db = $.db;

var createTable = "CREATE TABLE TEST_USERS (NAME varchar(255), STEPS integer, SALARY decimal, RATING decimal, IS_ADMIN boolean, BIO varchar(255), AVG_GRADE decimal, LUCKY_NUMBER integer)";

var insert = "INSERT INTO TEST_USERS (NAME, STEPS, SALARY, RATING, IS_ADMIN, BIO, AVG_GRADE, LUCKY_NUMBER) VALUES (?,?,?,?,?,?,?,?)";

var connection = db.getConnection();

try {
  connection.prepareCall("DROP TABLE TEST_USERS").execute();
} catch{}

connection.prepareCall(createTable).execute();

var statement = connection.prepareStatement(insert);
var parameterMetaData = statement.getParameterMetaData();
var count = parameterMetaData.getParameterCount();
var assertTrue = require('utils/assert').assertTrue;

var assertions = [];
for (var i = 1; i <= count; i++) {
  var typeName = parameterMetaData.getParameterTypeName(i) != null && parameterMetaData.getParameterTypeName(i) != undefined;
  var type = parameterMetaData.getParameterType(i) > 0;
  var parameterMode = parameterMetaData.getParameterMode(i) > 0;
  var precision = parameterMetaData.getPrecision(i) > 0;
  var scale = parameterMetaData.getScale(i) >=0 ;
  var isNullable = parameterMetaData.isNullable(i) == 0 || parameterMetaData.isNullable(i) == 1 || parameterMetaData.isNullable(i) == 2;
  var isSigned = typeof parameterMetaData.isSigned(i) == "boolean";
  assertions.push(typeName, type, parameterMode, precision, scale, isNullable, isSigned, count == 8);
}

statement.close();
connection.close();

assertTrue(assertions.every((assertion) => assertion));