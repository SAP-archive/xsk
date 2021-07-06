let bodyMessage = "<h3>Hello Unified Runtimes!</h3>";

bodyMessage += "User Info:";
bodyMessage += "<ul>";
bodyMessage += "<li>Username: " + $.session.getUsername() + "</li>";
bodyMessage += "<li>Is Developer: " + $.session.hasAppPrivilege("Developer") + "</li>";
bodyMessage += "<li>Is Operator: " + $.session.hasAppPrivilege("Operator") + "</li>";
bodyMessage += "<li>Is Admin: " + $.session.hasAppPrivilege("Admin") + "</li>";
bodyMessage += "</ul>";

let connection = null;
try {
    connection = $.db.getConnection();
    let statement = connection.prepareStatement("select * from \"SYS\".\"M_DATABASE\";");
    let resultSet = statement.executeQuery();
    bodyMessage += "Database Info:";
    bodyMessage += "<ul>";
    while (resultSet.next()) {
        bodyMessage += "<li>System Id: " + resultSet.getString(1) + "</li>";
        bodyMessage += "<li>Database Name: " + resultSet.getString(2) + "</li>";
        bodyMessage += "<li>Host: " + resultSet.getString(3) + "</li>";
        bodyMessage += "<li>Version: <b>" + resultSet.getString(5) + "</b></li>";
    }
    bodyMessage += "</ul>";
} finally {
    if (connection) {
        connection.close();
    }
}

$.response.contentType = "text/html";
$.response.setBody(bodyMessage);