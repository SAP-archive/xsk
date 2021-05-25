let bodyMessage = "Hello Unified Runtimes!";

bodyMessage += "\n\n" + $.session.getUsername();

bodyMessage += "\nIs Developer: " + $.session.hasAppPrivilege("Developer");
bodyMessage += "\nIs Operator: " + $.session.hasAppPrivilege("Operator");
bodyMessage += "\nIs Admin: " + $.session.hasAppPrivilege("Admin");
bodyMessage += "\n";

let connection = null;
try {
    connection = $.db.getConnection();
    let statement = connection.prepareStatement("SELECT * FROM DIRIGIBLE_EXTENSIONS");
    let resultSet = statement.executeQuery();
    while (resultSet.next()) {
        bodyMessage += "\n[path]: " + resultSet.getString("EXTENSION_LOCATION");
    }
} finally {
    if (connection) {
        connection.close();
    }
}

$.response.setBody(bodyMessage);