var db = $.db;
var assertTrue = require('utils/assert').assertTrue;

var connection = db.getConnection();

try {
  connection.prepareCall("DROP TABLE TEST_USERS").execute();
} catch{}

var createTable = "CREATE TABLE TEST_USERS (BLOB_ blob, BIGINT_ bigint, DATE_ date, CLOB_ clob, DEC_ decimal, DOUBLE_ double, FLOAT_ float, INT_ int," +
                    "NCLOB_ clob, NSTRING_ varchar(50), REAL_ real, SMALLINT_ smallint, STRING_ varchar(10), TIME_ time, TIMESTAMP_ timestamp, TINYINT_ smallint)";
connection.prepareCall(createTable).execute();

var insert = "INSERT INTO TEST_USERS (BLOB_, BIGINT_, DATE_, CLOB_, DEC_, DOUBLE_, FLOAT_, INT_, NCLOB_, NSTRING_, REAL_, SMALLINT_, STRING_, TIME_, TIMESTAMP_, TINYINT_) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
var call = connection.prepareCall(insert);

var blob = [123];
call.setBlob(1, [123]);

call.setBigInt(2, 1000000000000000)

var date = Java.type('java.sql.Date');
var dateinput = new date(1990, 10, 10);
call.setDate(3, dateinput);

var clob = "abcd";

call.setClob(4, clob);

var bigDec = Java.type('java.math.BigDecimal');
var dec = new bigDec(10.11);

call.setDecimal(5, dec);

call.setDouble(6, 10.11);

var float = Java.type('java.lang.Float')
var floatInput = new float(10.11);

call.setFloat(7, floatInput);

call.setInteger(8, 10);

//call.setNClob(9, clob); won't test - java.sql.SQLFeatureNotSupportedException

//call.setNString(10, "asd"); won't test - java.sql.SQLFeatureNotSupportedException

call.setReal(11, floatInput);

call.setSmallInt(12, 1);

call.setString(13, "test");

var time = Java.type('java.sql.Time');
var timeInput = new time(23, 59, 59);

call.setTime(14, timeInput);

var timestamp = Java.type('java.sql.Timestamp');
var timestampInput = new timestamp(1626860203);

call.setTimestamp(15, timestampInput);

call.setTinyInt(16, 1);

assertTrue(true);