let connection;

try {
  connection = $.db.getConnection();
  connection.prepareStatement("SESSION_CONTEXT('APPLICATIONUSER')").execute();
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