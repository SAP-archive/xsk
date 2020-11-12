var hdb = require('xsk/hdb');
var response = require('http/v4/response');

var conn = hdb.getConnection();
conn.executeUpdate('CREATE SCHEMA EXAMPLE');
conn.executeUpdate('CREATE TABLE EXAMPLE.TEST_METADATA( TINT TINYINT NOT NULL, FLOAT FLOAT )');
conn.executeUpdate('INSERT INTO EXAMPLE.TEST_METADATA VALUES(12, 34.5)');
conn.commit();
var resultSet = conn.executeQuery('SELECT * FROM EXAMPLE.TEST_METADATA');
var columnsMetadata = resultSet.metadata.columns;
var columnCount = columnsMetadata.length;
var body = "";
for (var i = 0; i < columnCount; i++) {
	body += "Column " + (i + 1) + " metadata:\n";
	body += "  Catalog name: " + columnsMetadata[i].catalogName + "\n";
	body += "  Display size: " + columnsMetadata[i].displaySize + "\n";
	body += "  Label:        " + columnsMetadata[i].label + "\n";
	body += "  Name:         " + columnsMetadata[i].name + "\n";
	body += "  Type:         " + columnsMetadata[i].type + "\n";
	body += "  Type name:    " + columnsMetadata[i].typeName + "\n";
	body += "  Precision:    " + columnsMetadata[i].precision + "\n";
	body += "  Scale:        " + columnsMetadata[i].scale + "\n";
	body += "  Table name:   " + columnsMetadata[i].tableName + "\n";
	body += "  Is nullable:  " + (columnsMetadata[i].isNullable ? "true" : "false") + "\n\n";
}

conn.executeUpdate('DROP TABLE EXAMPLE.TEST_METADATA');
conn.executeUpdate('DROP SCHEMA EXAMPLE');
response.contentType = "text/plain";
response.println(body);