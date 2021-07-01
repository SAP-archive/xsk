var db = $.db;

var connection = db.getConnection();

var insert = "INSERT INTO TEST_USERS (NAME, STEPS, SALARY, RATING, IS_ADMIN, BIO, AVG_GRADE, LUCKY_NUMBER) VALUES (?,?,?,?,?,?,?,?)";

//connection.prepareCall("DROP TABLE TEST_USERS").execute();

var createTable = "CREATE TABLE TEST_USERS (NAME blob, STEPS integer, SALARY decimal, RATING decimal, IS_ADMIN boolean, BIO varchar(255), AVG_GRADE decimal, LUCKY_NUMBER integer)";

try{
  connection.prepareCall(createTable).execute();
} catch (err) {}

var call = connection.prepareCall(insert);

//call.setBigInt(1, 1);
var array = Java.type('java.io.ByteArrayInputStream');
var input = new array([1]);
call.setBlob(1, input);

//call.execute();
//var get = connection.prepareStatement("SELECT * from TEST_USERS");
//get.execute();
//var rs = get.getResultSet();
//while(rs.next()){
//console.log(rs.getBigInt(1).toString());
//console.log(rs.getBigInt(2).toString());
//console.log(rs.getBigInt(3).toString());
//}
//console.log(call.getResultSet().getBigInt());

true