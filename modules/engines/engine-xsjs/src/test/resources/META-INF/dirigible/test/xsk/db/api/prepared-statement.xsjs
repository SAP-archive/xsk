var db = $.db;
var assertTrue = require('utils/assert').assertTrue;
var connection = db.getConnection();

try {
  connection.prepareStatement("DROP TABLE TEST_USERS").execute();
} catch {}

createTable();

var insert = "INSERT INTO TEST_USERS (BLOB_, BIGINT_, DATE_, CLOB_, DOUBLE_, FLOAT_, INT_, REAL_, SMALLINT_, STRING_, TIME_, TIMESTAMP_, TINYINT_) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
var statement = connection.prepareStatement(insert);

// Smoke test for all of the different set methods
setStatementFields();

statement.addBatch();

statement.addBatch();

// We've added 2 inserts to the batch, expect this to == 2
var batchExecutes = statement.executeBatch().length;

var executeResult = statement.execute();

var resultSet = connection.prepareStatement("SELECT * FROM TEST_USERS").executeQuery();

var updatedRows = connection.prepareStatement("UPDATE TEST_USERS SET INT_ = 9 WHERE INT_ = 10").executeUpdate();

var metadataAssertion = statement.getMetaData().constructor.name == "XscResultSetMetaData";

var moreResultsAssertion = typeof statement.getMoreResults() == "boolean";

var parameterMetaDataAssertion = statement.getParameterMetaData().constructor.name == "XscParameterMetaData";

statement.getSQLWarning(); // Couldn't manage to make this return something other than null, the function itself called the wrong dirigible function

statement.close();
var isClosedAssertion = statement.isClosed() == true;

var closeAssertion = true;
try {
  statement.execute(); // Expect this to fail since the statement is closed
  closeAssertion = false;
} catch {}

assertTrue(batchExecutes == 2 && !executeResult && closeAssertion && updatedRows == 3 && metadataAssertion && moreResultsAssertion && parameterMetaDataAssertion && isClosedAssertion && closeAssertion);

function setStatementFields() {
  var blob = [123];
  statement.setBlob(1, [123]);

  statement.setBigInt(2, 1000000000000000);

  var date = Java.type('java.sql.Date');
  var dateinput = new date(1990, 10, 10);
  statement.setDate(3, dateinput);

  var clob = "abcd";

  statement.setClob(4, clob);

  statement.setDouble(5, 13.37);

  var float = Java.type('java.lang.Float')
  var floatInput = new float(10.1337);

  statement.setFloat(6, floatInput);

  statement.setInteger(7, 10);

  statement.setReal(8, floatInput);

  statement.setSmallInt(9, 1);

  statement.setString(10, "test");

  var time = Java.type('java.sql.Time');
  var timeInput = new time(23, 59, 59);

  statement.setTime(11, timeInput);

  var timestamp = Java.type('java.sql.Timestamp');
  var timestampInput = new timestamp(1626860203);

  statement.setTimestamp(12, timestampInput);

  statement.setTinyInt(13, 1);
}

function createTable() {
  var tableDef = "CREATE TABLE TEST_USERS (BLOB_ blob, BIGINT_ bigint, DATE_ date, CLOB_ clob, DOUBLE_ double, FLOAT_ float, INT_ int," +
                      "REAL_ real, SMALLINT_ smallint, STRING_ varchar(10), TIME_ time, TIMESTAMP_ timestamp, TINYINT_ smallint)";

  connection.prepareStatement(tableDef).execute();
}