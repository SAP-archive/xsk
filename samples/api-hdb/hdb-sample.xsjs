var db = $.hdb;

let connection = null;
try {
  connection = db.getConnection();

  try {
    connection.executeUpdate("DROP TABLE CARS");
  } catch (e) {
    // Do nothing
  }
  connection.executeUpdate("CREATE TABLE CARS (MAKE varchar(255), MODEL varchar(255))");

  let rows = connection.executeUpdate("INSERT INTO CARS (MAKE, MODEL) VALUES ('BMW', '325')");
  rows += connection.executeUpdate("INSERT INTO CARS (MAKE, MODEL) VALUES ('HONDA', 'ACCORD')");
  let totalText = `Query OK, ${rows} rows affected\n\n`;

  let result = connection.executeQuery("SELECT * FROM CARS");
  let iterator = result.getIterator();
  let metadata = result.metadata.columns;

  while (iterator.next()) {
    var currentRow = iterator.value();
    totalText += `${metadata[0].name}: ${currentRow[0]}, ${metadata[1].name}: ${currentRow[1]}\n`;
    // totalText += `${metadata[0].name}: ${currentRow[metadata[0].name]}, ${metadata[1].name}: ${currentRow[metadata[1].name]}\n`;
  }
  $.response.setBody(totalText);
} catch (e) {
  connection.rollback();
  $.response.setBody("Transaction was rolled back: " + e.message);
} finally {
  if (connection) {
    connection.close();
  }
}