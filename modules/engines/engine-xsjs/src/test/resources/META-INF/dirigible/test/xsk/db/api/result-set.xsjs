var db = $.db;
var assertTrue = require('utils/assert').assertTrue;

var connection;
try{
  connection = db.getConnection();

  try {
    connection.prepareStatement("DROP TABLE TEST_USERS").execute();
  } catch {}

  createTable();

  var insert = "INSERT INTO TEST_USERS (BLOB_, BIGINT_, DATE_, CLOB_, DEC_, DOUBLE_, FLOAT_, INT_, REAL_, SMALLINT_, STRING_, TIME_, TIMESTAMP_, TINYINT_) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  var statement = connection.prepareStatement(insert);

  setStatementFields();

  statement.execute();

  var resultSet = connection.prepareStatement("SELECT * FROM TEST_USERS").executeQuery();

  resultSet.next();

  resultSet.getBlob(1); // not sure how to assert a value on this
  var bigIntAssertion = resultSet.getBigInt(2) == 1000000000000000;
  var dateAssertion = resultSet.getDate(3).toString().contains("Fri Jan 01 2021");
  resultSet.getClob(4); // same as blob, just a smoke test
  var decAssertion = resultSet.getDecimal(5).intValue() == 10;
  var doubleAssertion = resultSet.getDouble(6) == 10.11;
  resultSet.getFloat(7); // creating a new java float with 10.11 produces 10.109999656677246
  var intAssertion = resultSet.getInteger(8) == 10;
  resultSet.getReal(9);
  var stringAssertion = resultSet.getString(11) == "test";
  var timeAssertion = resultSet.getTime(12).toString().contains("23:59:59");
  var timestampAssertion = resultSet.getTimestamp(13).toString().contains("Wed Jul 21 2021");

  resultSet.close();
  var isClosedAssertion = resultSet.isClosed() == true;

  var closeAssertion = true;
  try {
    resultSet.next(); // Expect this to fail since the statement is closed
    closeAssertion = false;
  } catch {}

  assertTrue(bigIntAssertion && dateAssertion && decAssertion && doubleAssertion && intAssertion && stringAssertion && timeAssertion && timestampAssertion && closeAssertion && isClosedAssertion);
} finally {
  if(connection){
    connection.commit();
    connection.close();
  }
}
function setStatementFields() {
  var blob = [123];
  statement.setBlob(1, blob);

  statement.setBigInt(2, 1000000000000000);

  var date = Java.type('java.sql.Date');
  var dateInput = new date(121, 0, 1);
  statement.setDate(3, dateInput);

  var clob = "abcd";

  statement.setClob(4, clob);

  var bigDec = Java.type('java.math.BigDecimal');
  var dec = new bigDec(10);

  statement.setDecimal(5, dec);
  statement.setDecimal(5, 10);

  statement.setDouble(6, 10.11);

  var float = Java.type('java.lang.Float')
  var floatInput = new float(10.11);

  statement.setFloat(7, floatInput);

  statement.setInteger(8, 10);

  statement.setReal(9, floatInput);

  statement.setSmallInt(10, 1);

  statement.setString(11, "test");

  var time = Java.type('java.sql.Time');
  var timeInput = new time(23, 59, 59);

  statement.setTime(12, timeInput);

  var timestamp = Java.type('java.sql.Timestamp');
  var timestampInput = new timestamp(1626860203000);

  statement.setTimestamp(13, timestampInput);

  statement.setTinyInt(14, 1);
}

function createTable() {
  var tableDef = "CREATE TABLE TEST_USERS (BLOB_ blob, BIGINT_ bigint, DATE_ date, CLOB_ clob, DEC_ decimal, DOUBLE_ double, FLOAT_ float, INT_ int," +
                      "REAL_ real, SMALLINT_ smallint, STRING_ varchar(10), TIME_ time, TIMESTAMP_ timestamp, TINYINT_ smallint)";

  connection.prepareStatement(tableDef).execute();
}