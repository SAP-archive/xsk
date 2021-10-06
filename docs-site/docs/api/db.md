
---
title: $.db
---


$.db
===

`$.db` represents the namespace for database access.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/15](https://github.com/SAP/xsk/issues/15)
- Module: [db/db.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/META-INF/dirigible/xsk/db/db.js)
- Status: `alpha`

## Sample Usage

```javascript
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

    let selectStatement = connection.prepareStatement("SELECT * FROM SAMPLE_USERS");
    selectStatement.execute();

    let resultSet = selectStatement.getResultSet();
    let names =  [];

    while (resultSet.next()) {
        names.push(resultSet.getString(1));
    }    
    selectStatement.close();
    resultSet.close();

    $.response.setBody(names.toString());
} catch(e) {
    if (connection) {
        connection.rollback();
    }
    $.response.setBody("Transaction was rolled back: "  + e.message);
} finally {
    connection.close();
}
```

## Functions

| Function        | Description                           | Returns              |
|-----------------|---------------------------------------|----------------------|
| **getConnection()** | Returns a connection to the database. | _`$.db.Connection`_ |

## Classes

### $.db.Connection

#### Functions


| Function| Description                                            | Returns |
|--------------|--------------------------------------------------------|:--------:|
| **close()** | Closes the connection.                              |    `-`   |
| **commit()**| Commits the changes. |   `-`   |
| **isClosed()**| Checks if the connection is closed.                        |    `boolean`  |
| **prepareCall(statement)**| Prepares a stored procedure for execution                     |    `$.db.CallableStatement`  |
| **prepareStatement(statement)**| Prepares a statement for execution                        |      `$.db.PreparedStatement`  |
| **rollback()**| Rolls back the changes.                        |     `-`  |
| **setAutoCommit(enable)**| Changes the auto-commit flag of the connection                        |     `-`   |

### $.db.CallableStatement

#### Functions

| Function      | Description                                            | Returns |
|--------------|--------------------------------------------------------|:--------:|
| **close()**         | Closes the statement        |    `-`   |
| **execute()**         | Executes a specified statement        |    `boolean`   |
| **getBigInt(index)**         | Returns an Int64 value of a BIGINT parameter        |    `integer`   |
| **getBlob(index)**         | Returns the ArrayBuffer value of a BLOB specified parameter        |    `ArrayBuffer`   |
| **getBString(index)**         | Returns an ArrayBuffer object of the specified column. getBString is used for BINARY and VARBINARY column types.        |   `ArrayBuffer`   |
| **getClob(index)**         | Returns the string value of a CLOB parameter        |    `string`   |
| **getDate(index)**         |Used to retrieve the value of a DATE parameter        |    `Date`   |
| **getDecimal(index)**         | Returns a number value of a DECIMAL parameter        |    `number`   |
| **getDouble(index)**         | Returns a number value of a DOUBLE, FLOAT or REAL parameter        |    `number`   |
| **getFloat(columnIndex)**         | Returns a number value of the specified column. getFloat is used for FLOAT column types.        |    `number`   |
| **getInteger(index)**         | Returns an integer value of a TINYINT, SMALLINT, INT or BIGINT parameter types        |    `integer`   |
| **getMoreResults()**         | Checks if more result sets are available and prepares the next result set for retrieval        |    `boolean`   |
| **getNClob(index)**         | Returns the string value of an NCLOB or TEXT parameter        |    `string`   |
| **getNString(index)**         | Returns the string value of an NCHAR, an NVARCHAR, or a SHORTTEXT parameter        |    `string`   |
| **getParameterMetaData()**         | Returns the metadata for this statement        |    `$.db.ParameterMetadata`   |
| **getReal(columnIndex)**         | Returns a number value of the specified column. getReal is used for REAL column types.        |    `number`   |
| **getResultSet()**         | Returns a result set representing a table output parameter        |    `$.db.ResultSet`  |
| **getString(index)**         |Returns a string value of a CHAR or VARCHAR parameter; ASCII only, not suitable for strings containing Unicode characters.       |    `string`   |
| **getText(index)**         | Returns the string value of a TEXT parameter        |    `string`   |
| **getTime(index)**         | Used to retrieve the value of a TIME parameter        |    `Date`   |
| **getTimestamp(index)**         | Used to retrieve the value of a TIMESTAMP parameter.        |    `Date`   |
| **isClosed()**         | Checks if the statement is closed.        |    `boolean`   |
| **setBigInt(index, value)**         | Sets an integer parameter used for BIGINT parameter types       |    `-`   |
| **setBlob(index, value)**         | setBlob is used to specify the values for CHAR, VARCHAR, NCHAR, NVARCHAR, BINARY, VARBINARY parameter types.        |   `-`   |
| **setBString(index, value)**         |Sets a string parameter used for BINARY, VARBINARY parameter types.       |   `-`   |
| **setClob(index, value)**         | setClob is used to specify the values for CLOB parameter types.        |    `-`   |
| **setDate(index, value, format)**         | Sets a Date parameter for DATE parameters, but works with TIME and TIMESTAMP.        |   `-`  |
| **setDecimal(index, value)**         | setDecimal sets a decimal parameter used for DECIMAL parameter types        |    `-`   |
| **setDouble(index, value)**         | setDouble sets a double parameter used for FLOAT and DOUBLE parameter types        |    `-`   |
| **setFloat(index, value)**         | setFloat sets a float parameter used for FLOAT parameter types        |    `-`   |
| **setInteger(index, value)**         | Sets an integer parameter used for TINYINT, SMALLINT, INT parameter types        |   `-`   |
| **setNClob(index, value)**         | setNClob is used to specify the values for NCLOB parameter types.        |    `-`  |
| **setNString(columnIndex, value)**         | Sets a string parameter used for NCHAR, NVARCHAR parameter types, which should be used for strings containing Unicode characters.        |    `-`   |
| **setReal(index, value)**         | setReal sets a real parameter used for REAL parameter types        |    `-`   |
| **setSmallInt(index, value)**         | Sets an integer parameter used for SMALLINT parameter types        |    `-`   |
| **setString(columnIndex, value)**         | Sets a string parameter used for CHAR, VARCHAR column types; ASCII only, not suitable for strings containing Unicode characters        |    `-`   |
| **setText(columnIndex, value)**         | setText is used to specify the values for TEXT column types.        |    `-`   |
| **setTime(index, value, format)**         | Sets a Time parameter used for TIME parameter types (hour, min, sec) - milliseconds(mls) cannot be set        |   `-`  |
| **setTimestamp(index, value, format)**         | Sets a Timestamp parameter used for TIMESTAMP parameter types        |   `-`  |
| **setTinyInt(index, value)**         | Sets an integer parameter used for TINYINT parameter types        |    `-`   |

### $.db.ParameterMetaData

#### Functions

| Function| Description                                            | Returns |
|--------------|--------------------------------------------------------|:--------:|
| **getParameterCount()** |Returns the number of the parameters in the prepared statement                              |   `integer`   |
| **getParameterMode(index)** | Returns the mode of the specified parameter                              |   `integer`   |
| **getParameterName(columnIndex)** | Returns the name of the specified parameter                        |    `string`    |
| **getParameterType(columnIndex)** | Returns the type ($.db.types) of the specified parameter                           |    `number`   |
| **getParameterTypeName(columnIndex)** | Returns the type name of the specified parameter                              |    `string`  |
| **getPrecision(columnIndex)** | Returns the designated parameter's number of decimal digits                              |    `string`   |
| **getScale(columnIndex)** | Returns the designated parameter's scale                              |    `integer`   |
| **isNullable(index)** | Checks if the specified parameter is nullable                              |    `integer`   |
| **isSigned(index)** | Checks if the specified parameter is signed                              |   `integer`   |


### $.db.PreparedStatement

#### Functions

| Function | Description                                            | Returns |
|--------------|--------------------------------------------------------|:--------:|
| **addBatch()** | Adds last parameter values and iterates to the next batch slot                              |   `-`  |
| **close()** | Closes the statement                              |   `-`  |
| **execute()** | Executes a common statement                              |    `boolean`  |
| **executeBatch()** | Executes a batch insertion. Use setBatchSize and addBatch to prepare for batch execution.                            |    `array`   |
| **executeQuery()** | Executes an SQL statement                              |    `$.db.ResultSet`   |
| **executeUpdate()** | Executes an update statement                              |    `integer`  |
| **getMetaData()** | Returns the metadata of the ResultSet                              |    `$.db.ResultSetMetaData`  |
| **getMoreResults()** | Checks if more result sets are available and prepares the next result set for retrieval                              |    `boolean` |
| **getParameterMetaData()** | Returns the metadata of the prepared statement                              |    `$.db.ParameterMetaData`  |
| **getResultSet()** | Returns a result set representing a table output parameter                              |    `$.db.ResultSet`   |
| **getSQLWarning()** | Returns the warning of the most recently executed statement.                              |    `Object/null`   |
| **isClosed()** | Checks if the statement is closed.                              |    `boolean`   |
| **setBigInt(index, value)**         | Sets an integer parameter used for BIGINT parameter types       |    `-`   |
| **setBlob(index, value)**         | setBlob is used to specify the values for CHAR, VARCHAR, NCHAR, NVARCHAR, BINARY, VARBINARY parameter types.        |   `-`   |
| **setBString(index, value)**         |Sets a string parameter used for BINARY, VARBINARY parameter types.       |   `-`   |
| **setClob(index, value)**         | setClob is used to specify the values for CLOB parameter types.        |    `-`   |
| **setDate(index, value, format)**         | Sets a Date parameter for DATE parameters, but works with TIME and TIMESTAMP.        |   `-`  |
| **setDecimal(index, value)**         | setDecimal sets a decimal parameter used for DECIMAL parameter types        |    `-`   |
| **setDouble(index, value)**         | setDouble sets a double parameter used for FLOAT and DOUBLE parameter types        |    `-`   |
| **setFloat(index, value)**         | setFloat sets a float parameter used for FLOAT parameter types        |    `-`   |
| **setInteger(index, value)**         | Sets an integer parameter used for TINYINT, SMALLINT, INT parameter types        |   `-`   |
| **setNClob(index, value)**         | setNClob is used to specify the values for NCLOB parameter types.        |    `-`  |
| **setNString(columnIndex, value)**         | Sets a string parameter used for NCHAR, NVARCHAR parameter types, which should be used for strings containing Unicode characters.        |    `-`   |
| **setReal(index, value)**         | setReal sets a real parameter used for REAL parameter types        |    `-`   |
| **setSmallInt(index, value)**         | Sets an integer parameter used for SMALLINT parameter types        |    `-`   |
| **setString(columnIndex, value)**         | Sets a string parameter used for CHAR, VARCHAR column types; ASCII only, not suitable for strings containing Unicode characters        |    `-`   |
| **setText(columnIndex, value)**         | setText is used to specify the values for TEXT column types.        |    `-`   |
| **setTime(index, value, format)**         | Sets a Time parameter used for TIME parameter types (hour, min, sec) - milliseconds(mls) cannot be set        |   `-`  |
| **setTimestamp(index, value, format)**         | Sets a Timestamp parameter used for TIMESTAMP parameter types        |   `-`  |
| **setTinyInt(index, value)**         | Sets an integer parameter used for TINYINT parameter types        |    `-`   |


### $.db.ResultSet

#### Functions

| Function| Description                                            | Returns |
|--------------|--------------------------------------------------------|:--------:|
| **close()** | Closes the ResultSet                              |    `-`   |
| **getBigInt(index)**         | Returns an Int64 value of a BIGINT parameter        |    `integer`   |
| **getBlob(index)**         | Returns the ArrayBuffer value of a BLOB specified parameter        |    `ArrayBuffer`   |
| **getClob(index)**         | Returns the string value of a CLOB parameter        |    `string`   |
| **getDate(index)**         |Used to retrieve the value of a DATE parameter        |    `Date`   |
| **getDecimal(index)**         | Returns a number value of a DECIMAL parameter        |    `number`   |
| **getDouble(index)**         | Returns a number value of a DOUBLE, FLOAT or REAL parameter        |    `number`   |
| **getFloat(columnIndex)**         | Returns a number value of the specified column. getFloat is used for FLOAT column types.        |    `number`   |
| **getInteger(index)**         | Returns an integer value of a TINYINT, SMALLINT, INT or BIGINT parameter types        |    `integer`   |
| **getMetaData()** | Returns the metadata of the result set                              |    `$.db.ResultSetMetaData`   |
| **getNClob(index)**         | Returns the string value of an NCLOB or TEXT parameter        |    `string`   |
| **getNString(index)**         | Returns the string value of an NCHAR, an NVARCHAR, or a SHORTTEXT parameter        |    `string`   ||
| **getReal(columnIndex)**         | Returns a number value of the specified column. getReal is used for REAL column types.        |    `number`   |
| **getString(index)**         |Returns a string value of a CHAR or VARCHAR parameter; ASCII only, not suitable for strings containing Unicode characters.       |    `string`   |
| **getText(index)**         | Returns the string value of a TEXT parameter        |    `string`   |
| **getTime(index)**         | Used to retrieve the value of a TIME parameter        |    `Date`   |
| **getTimestamp(index)**         | Used to retrieve the value of a TIMESTAMP parameter.        |    `Date`   |
| **isClosed()** |Checks if the ResultSet is closed.                              |   `boolean`  |
| **next()** | Fetches the next row                              |    `boolean`   |

### $.db.ResultSetMetaData

#### Function

| Function| Description                                            | Returns |
|--------------|--------------------------------------------------------|:--------:|
| **getCatalogName(columnIndex)** | Returns the catalog name for the specified column                              |    `string`   |
| **getColumnCount()** |Returns the number of the columns in the result set                              |    `integer`   |
| **getColumnDisplaySize(columnIndex)** | Returns the column display size of the specified column                              |    `integer`   |
| **getColumnLabel(columnIndex)** | Returns the alias or name of the specified column                              |    `string`   |
| **getColumnName(columnIndex)** | Returns the name of the specified column                              |    `string` |
| **getColumnType(columnIndex)** | Returns the type of the specified column                              |    `number`   |
| **getColumnTypeName(columnIndex)** | Returns the name of the specified column type                              |    `string`  |
| **getPrecision(columnIndex)** | Returns the precision of the specified column                              |    `integer`   |
| **getScale(columnIndex)** | Returns the scale of the specified column                              |   `integer`  |
| **getTableName(columnIndex)** | Returns the table name for the specified column                              |    `string`  |
