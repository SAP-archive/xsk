var hdb = $.hdb;
var response = require('http/v4/response');
var assertTrue = require('utils/assert').assertTrue;

const base = [..."ABCDEFGHIJKLMNOPQRSTUVWXYZ"];

const generator = (base, len) => {
   return [...Array(len)]
     .map(i => base[Math.random()*base.length|0])
     .join('');
};

const schema = generator(base, 10);

var conn = hdb.getConnection();
conn.executeUpdate('CREATE SCHEMA ' + schema);
conn.executeUpdate("CREATE TABLE " + schema + ".TEST_USERS( ID INT NOT NULL, FIRSTNAME VARCHAR(30), LASTNAME VARCHAR (50), AGE INT NOT NULL, WEIGHT FLOAT)");
conn.executeUpdate("INSERT INTO " + schema + ".TEST_USERS(ID,FIRSTNAME,LASTNAME,AGE,WEIGHT) VALUES(1,'IVAN','VOLKOV',29,76)");
conn.executeUpdate("INSERT INTO " + schema + ".TEST_USERS(ID,FIRSTNAME,LASTNAME,AGE,WEIGHT) VALUES(1,'IVAN','NIKOLOV',27,81)");
conn.executeUpdate("INSERT INTO " + schema + ".TEST_USERS(ID,FIRSTNAME,LASTNAME,AGE,WEIGHT) VALUES(2,'MITKO','DIMITROV',39,85)");
conn.executeUpdate("INSERT INTO " + schema + ".TEST_USERS(ID,FIRSTNAME,LASTNAME,AGE,WEIGHT) VALUES(3,'PABLO','GONSALEZ',23,69)");

conn.commit();
var resultSet = conn.executeQuery('SELECT * FROM ' + schema + '.TEST_USERS');
var columnsMetadata = resultSet.metadata.columns;
var columnCount = columnsMetadata.length;
var body = "";
var lastColumnName = "";
var lastColumnTypeName = "";
for (var i = 0; i < columnCount; i++) {
	body += "Column " + (i + 1) + " metadata:\n";
	body += "  Catalog name: " + columnsMetadata[i].catalogName + "\n";

	body += "  Display size: " + columnsMetadata[i].displaySize + "\n";
	body += "  Label:        " + columnsMetadata[i].label + "\n";
	body += "  Name:         " + columnsMetadata[i].name + "\n";
	lastColumnName = columnsMetadata[i].name;
	body += "  Type:         " + columnsMetadata[i].type + "\n";
	body += "  Type name:    " + columnsMetadata[i].typeName + "\n";
	lastColumnTypeName = columnsMetadata[i].typeName
	body += "  Precision:    " + columnsMetadata[i].precision + "\n";
	body += "  Scale:        " + columnsMetadata[i].scale + "\n";
	body += "  Table name:   " + columnsMetadata[i].tableName + "\n";
	body += "  Is nullable:  " + (columnsMetadata[i].isNullable ? "true" : "false") + "\n\n";
}


response.contentType = "text/plain";
response.println(body);

assertTrue(conn != null&& resultSet != null && columnCount !=null && body != null && lastColumnName ==="WEIGHT"&& lastColumnTypeName==="DOUBLE");
