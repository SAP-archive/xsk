let connection;

try {
  connection = $.db.getConnection();
  connection.prepareStatement("DBADMIN").execute();
}
catch (e) {
  if (connection) {
    connection.rollback();
  }
  $.response.setBody("Transaction was rolled back: " + e.message);
}
finally {
  connection.close();
}