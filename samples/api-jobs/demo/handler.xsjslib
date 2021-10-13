function writeInDB() {
    let connection;
    try {
        connection = $.db.getConnection();

        let insertStatement = connection.prepareStatement("INSERT INTO XSJOB_DEMO (EXECUTED_AT) VALUES (CURRENT_TIMESTAMP)");
        insertStatement.executeUpdate();
        insertStatement.close();
    } catch(e) {
        connection.rollback();
        console.log("Transaction was rolled back: " + e.message);
    } finally {
        connection.close();
    }
}