var database = require('xsk/db');
var response = require('http/v4/response');

try {
	var connection = database.getConnection();
   	var statement = connection.prepareStatement("SELECT * FROM TEST_USERS");

   	var metaDataColumnIndex = 5;

   	statement.execute();
   	var resultSetMetaData = statement.getMetaData();

   	response.println("Catalog Name: " + resultSetMetaData.getCatalogName(1));
   	response.println("Table Name: " + resultSetMetaData.getTableName(metaDataColumnIndex));
   	response.println("Columns count: " + resultSetMetaData.getColumnCount());
   	response.println("\n----------------------------------------\nDisplaying data for column with index " + metaDataColumnIndex + "\n----------------------------------------");
   	response.println("Column display size: " + resultSetMetaData.getColumnDisplaySize(metaDataColumnIndex));
   	response.println("Column Label: " + resultSetMetaData.getColumnLabel(metaDataColumnIndex));
   	response.println("Column Name: " + resultSetMetaData.getColumnName(metaDataColumnIndex));
   	response.println("Column Type: " + resultSetMetaData.getColumnType(metaDataColumnIndex));
   	response.println("Column Type Name: " + resultSetMetaData.getColumnTypeName(metaDataColumnIndex));
   	response.println("Column Precision: " + resultSetMetaData.getPrecision(metaDataColumnIndex));
   	response.println("Column Scale: " + resultSetMetaData.getScale(metaDataColumnIndex));

   	statement.close();
} catch(e) {
   console.trace(e);
   response.println(e.message);
} finally {
   connection.close();
}

response.flush();
response.close();