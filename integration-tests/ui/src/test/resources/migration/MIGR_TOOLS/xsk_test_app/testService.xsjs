let connection;

try {
  connection = $.db.getConnection();

  // Make sure to create the table only once
  try {
    connection.prepareStatement("CREATE TABLE SAMPLE_USERS (NAME varchar(255), AGE int)").execute();
  } catch (e) {
    // Do nothing, as the table already exists
  }

  let insertStatement = connection.prepareStatement("INSERT INTO SAMPLE_USERS (NAME, AGE) VALUES ('Bob', 20)");
  insertStatement.executeUpdate();
  insertStatement.close();

  insertStatement = connection.prepareStatement("INSERT INTO SAMPLE_USERS (NAME, AGE) VALUES ('Alice', 21)");
  insertStatement.executeUpdate();
  insertStatement.close();

  let selectStatement = connection.prepareStatement("SELECT * FROM SAMPLE_USERS WHERE SESSION_CONTEXT('APPLICATIONUSER') = 'APPUSER'");
  selectStatement.execute();

  let resultSet = selectStatement.getResultSet();
  let names = [];

  while (resultSet.next()) {
    names.push(resultSet.getString(1));
  }
  selectStatement.close();
  resultSet.close();

  $.response.setBody(names.toString());
} catch (e) {
  if (connection) {
    connection.rollback();
  }
  $.response.setBody("Transaction was rolled back: " + e.message);
} finally {
  connection.close();
}