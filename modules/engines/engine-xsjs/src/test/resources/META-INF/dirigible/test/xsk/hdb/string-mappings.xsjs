var hdb = $.hdb;
var assertTrue = require('utils/assert').assertTrue;
var conn = hdb.getConnection();
conn.executeUpdate("CREATE TABLE MAPPINGS.STR_MAPPINGS(decimal_t DECIMAL(5), varchar_t VARCHAR(9), nvarchar_t NVARCHAR(9))");
conn.executeUpdate("INSERT INTO MAPPINGS.STR_MAPPINGS VALUES (0.0, '1', '2')");
const result = conn.executeQuery('SELECT * FROM MAPPINGS.STR_MAPPINGS');
let iterator = result.getIterator();
let metadata = result.metadata.columns;

while(iterator.next()) {

  var currentRow = iterator.value();
  for(const index in metadata){
    const buffer = currentRow[metadata[index].name];
    assertTrue((typeof buffer === 'string' || buffer instanceof String))
  }

}

