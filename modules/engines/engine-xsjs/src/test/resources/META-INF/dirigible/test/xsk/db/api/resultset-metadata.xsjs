var db = $.db;
var assertTrue = require('utils/assert').assertTrue;

var connection = db.getConnection();

try {
  connection.prepareStatement("DROP TABLE TEST_USERS").execute();
} catch {}

connection.prepareStatement("CREATE TABLE TEST_USERS (ID int)").execute();

connection.prepareStatement("INSERT INTO TEST_USERS (ID) VALUES (1)").execute();

var metadata = connection.prepareStatement("SELECT * FROM TEST_USERS").executeQuery().getMetaData();

metadata.getCatalogName(1);

var columnCountAssertion = metadata.getColumnCount() == 1;

metadata.getColumnDisplaySize(1);

var labelAssertion = metadata.getColumnLabel(1) == "ID";

var nameAssertion = metadata.getColumnName(1) == "ID";

var typeAssertion = metadata.getColumnType(1) == 4;

var typeNameAssertion = metadata.getColumnTypeName(1) == "INTEGER";

var precisionAssertion = metadata.getPrecision(1) == 32;

var scaleAssertion = metadata.getScale(1) == 0;

var tableNameAssertion = metadata.getTableName(1) == "TEST_USERS";

assertTrue(columnCountAssertion && labelAssertion && nameAssertion && typeAssertion && typeNameAssertion && precisionAssertion && scaleAssertion && tableNameAssertion);