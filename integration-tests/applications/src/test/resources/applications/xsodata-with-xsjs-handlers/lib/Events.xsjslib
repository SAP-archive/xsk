
// CREATE HANDLERS

// Inserts a new row in employee status table.
function beforeCreate(params) {
    let connection = params.connection;
    connection.setAutoCommit(true);

    let statement = connection.prepareStatement("SELECT * FROM \"" + params.afterTableName + "\";");
    statement.execute();

    let resultSet = statement.getResultSet();

    while (resultSet.next()) {
        let employeeId = resultSet.getInteger("Id");
        let statement = connection.prepareStatement("INSERT INTO \"TEST_SCHEMA\".\"xsodata-with-xsjs-handlers::Entities.Status\" VALUES (?, true);");
        statement.setInteger(1, employeeId);
        statement.execute();
    }
}

// Inserts a new row in the employee table.
function onCreate(params) {
    let connection = params.connection;

    let statement = connection.prepareStatement("SELECT * FROM \"" + params.afterTableName + "\";");
    statement.execute();

    let resultSet = statement.getResultSet();

    while (resultSet.next()) {
        let employeeId = resultSet.getInteger("Id");
        let employeeName = resultSet.getString("Name");
        let employeeAge = resultSet.getInteger("Age");
        let employeeCountry = resultSet.getString("Country");

        let statement = connection.prepareStatement("INSERT INTO \"TEST_SCHEMA\".\"xsodata-with-xsjs-handlers::Entities.Employee\" VALUES (?, ?, ?, ?);");
        statement.setInteger(1, employeeId);
        statement.setString(2, employeeName);
        statement.setInteger(3, employeeAge);
        statement.setString(4, employeeCountry);
        statement.execute();
    }

    connection.commit();
}

// Inserts a new row in the employee salary table.
function afterCreate(params) {
    let connection = params.connection;

    let statement = connection.prepareStatement("SELECT * FROM \"" + params.afterTableName + "\";");
    statement.execute();

    let resultSet = statement.getResultSet();

    while (resultSet.next()) {
        let employeeId = resultSet.getInteger("Id");

        let statement = connection.prepareStatement("INSERT INTO \"TEST_SCHEMA\".\"xsodata-with-xsjs-handlers::Entities.Salary\" VALUES (?, 1000);");
        statement.setInteger(1, employeeId);
        statement.execute();
    }

    connection.commit();
}

// UPDATE HANDLERS

// Updates the employee status.
function beforeUpdate(params) {
    let connection = params.connection;
    connection.setAutoCommit(true);

    let statement = connection.prepareStatement("SELECT * FROM \"" + params.afterTableName + "\";");
    statement.execute();

    let resultSet = statement.getResultSet();

    while (resultSet.next()) {
        let employeeId = resultSet.getInteger("Id");

        let statement = connection.prepareStatement("UPDATE \"TEST_SCHEMA\".\"xsodata-with-xsjs-handlers::Entities.Status\" SET \"Active\" = false WHERE \"Id\" = ?;");
        statement.setInteger(1, employeeId);
        statement.execute();
    }
}

// Updates the employee details.
function onUpdate(params) {
    let connection = params.connection;

    let statement = connection.prepareStatement("SELECT * FROM \"" + params.afterTableName + "\";");
    statement.execute();

    let resultSet = statement.getResultSet();

    while (resultSet.next()) {
        let employeeId = resultSet.getInteger("Id");
        let employeeName = resultSet.getString("Name");
        let employeeAge = resultSet.getInteger("Age");
        let employeeCountry = resultSet.getString("Country");

        let statement = connection.prepareStatement("UPDATE \"TEST_SCHEMA\".\"xsodata-with-xsjs-handlers::Entities.Employee\" SET \"Name\" = ?, \"Age\" = ?, \"Country\" = ? WHERE \"Id\" = ? ;");
        statement.setString(1, employeeName);
        statement.setInteger(2, employeeAge);
        statement.setString(3, employeeCountry);
        statement.setInteger(4, employeeId);
        statement.execute();
    }

    connection.commit();
}

// Updates the employee salary.
function afterUpdate(params) {
    let connection = params.connection;

    let statement = connection.prepareStatement("SELECT * FROM \"" + params.afterTableName + "\";");
    statement.execute();

    let resultSet = statement.getResultSet();

    while (resultSet.next()) {
        let employeeId = resultSet.getInteger("Id");

        let statement = connection.prepareStatement("UPDATE \"TEST_SCHEMA\".\"xsodata-with-xsjs-handlers::Entities.Salary\" SET \"Amount\" = 1500 WHERE \"Id\" = ?;");
        statement.setInteger(1, employeeId);
        statement.execute();
    }

    connection.commit();
}

// DELETE HANDLERS

// Deletes the employee data from the status table.
function beforeDelete(params) {
    let connection = params.connection;
    connection.setAutoCommit(true);

    let statement = connection.prepareStatement("SELECT * FROM \"" + params.beforeTableName + "\";");
    statement.execute();

    let resultSet = statement.getResultSet();

    while (resultSet.next()) {
        let employeeId = resultSet.getInteger("Id");

        let statement = connection.prepareStatement("DELETE FROM \"TEST_SCHEMA\".\"xsodata-with-xsjs-handlers::Entities.Status\" WHERE \"Id\" = ?;");
        statement.setInteger(1, employeeId);
        statement.execute();
    }
}

// Deletes the record from employees table.
function onDelete(params) {
    let connection = params.connection;

    let statement = connection.prepareStatement("SELECT * FROM \"" + params.beforeTableName + "\";");
    statement.execute();

    let resultSet = statement.getResultSet();

    while (resultSet.next()) {
        let employeeId = resultSet.getInteger("Id");

        let statement = connection.prepareStatement("DELETE FROM \"TEST_SCHEMA\".\"xsodata-with-xsjs-handlers::Entities.Employee\" WHERE \"Id\" = ?;");
        statement.setInteger(1, employeeId);
        statement.execute();
    }

    connection.commit();
}

// Deletes the employee data from the salary table.
function afterDelete(params) {
    let connection = params.connection;

    let statement = connection.prepareStatement("SELECT * FROM \"" + params.beforeTableName + "\";");
    statement.execute();

    let resultSet = statement.getResultSet();

    while (resultSet.next()) {
        let employeeId = resultSet.getInteger("Id");

        let statement = connection.prepareStatement("DELETE FROM \"TEST_SCHEMA\".\"xsodata-with-xsjs-handlers::Entities.Salary\" WHERE \"Id\" = ?;");
        statement.setInteger(1, employeeId);
        statement.execute();
    }

    connection.commit();
}