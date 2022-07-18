var hdb = $.hdb;
var assertTrue = require('utils/assert').assertTrue;
var conn = hdb.getConnection();
conn.executeUpdate("CREATE TABLE MAPPINGS.DATE_MAPPINGS(date_t DATE, time_t TIME, timestamp_t TIMESTAMP)");
conn.executeUpdate("INSERT INTO MAPPINGS.DATE_MAPPINGS (`date_t`, `time_t`, `timestamp_t`) VALUES (CURRENT_DATE(), CURRENT_TIME(), CURRENT_TIME())");

const result = conn.executeQuery('SELECT * FROM MAPPINGS.DATE_MAPPINGS');
let iterator = result.getIterator();
let metadata = result.metadata.columns;

while(iterator.next()) {

  var currentRow = iterator.value();
  for(const index in metadata){
    const date = currentRow[metadata[index].name];
    assertTrue(typeof date.getMonth === 'function');
  }

}

