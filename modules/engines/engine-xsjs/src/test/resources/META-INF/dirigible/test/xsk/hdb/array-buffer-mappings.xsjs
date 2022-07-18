var hdb = $.hdb;
var assertTrue = require('utils/assert').assertTrue;
var conn = hdb.getConnection();
conn.executeUpdate('CREATE SCHEMA MAPPINGS');
conn.executeUpdate("CREATE TABLE MAPPINGS.ARR_MAPPINGS(bin BINARY(9))");
conn.executeUpdate("INSERT INTO MAPPINGS.ARR_MAPPINGS VALUES ('dirigible')");
const result = conn.executeQuery('SELECT * FROM MAPPINGS.ARR_MAPPINGS');
let iterator = result.getIterator();
let metadata = result.metadata.columns;

while(iterator.next()) {

  var currentRow = iterator.value();
  for(const index in metadata){
    const buffer = currentRow[metadata[index].name];
    assertTrue(ab2str(buffer) === "dirigible");
  }

}

function ab2str(buf) {
  return String.fromCharCode.apply(null, new Uint8Array(buf));
}
