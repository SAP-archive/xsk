---
title: $.hdb
---

$.hdb
===

`$.hdb` namespace provides means for seamless HANA database access. It is intended to be a replacement for the older `$.db`
namespace. The fundamental goal of the new interface is to ensure simplicity, convenience, completeness, and
performance.

## Overview

- Definition: [https://github.com/SAP/xsk/issues/14](https://github.com/SAP/xsk/issues/14)
- Module: [hdb/hdb.js](https://github.com/SAP/xsk/blob/main/modules/api/api-xsjs/src/main/resources/xsk/hdb/hdb.js)
- Status: `alpha`

## Sample Usage

```javascript
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
 
    while(iterator.next()) {
        var currentRow = iterator.value();
        totalText += `${metadata[0].name}: ${currentRow[0]}, ${metadata[1].name}: ${currentRow[1]}\n`;
        // totalText += `${metadata[0].name}: ${currentRow[metadata[0].name]}, ${metadata[1].name}: ${currentRow[metadata[1].name]}\n`;
    }
    $.response.setBody(totalText );
} catch(e) {
    connection.rollback();
    $.response.setBody("Transaction was rolled back: " + e.message);
} finally {
    if (connection) {
        connection.close();
    }
}
```

## Functions

| Function        | Description                           | Returns              |
|-----------------|---------------------------------------|----------------------|
| **getConnection()** | Returns a connection to the database. | _`$.hdb.Connection`_ |

## Properties

| Name                      | Description | Type       | Default |
|---------------------------|-------------|------------|---------|
| isolation.READ_COMITTED   | -           | _`number`_ | _`2`_   |
| isolation.REPEATABLE_READ | -           | _`number`_ | _`4`_   |
| isolation.SERIALIZABLE    | -           | _`number`_ | _`8`_   |
| types.TINYINT             | -           | _`number`_ | _`1`_   |
| types.SMALLINT            | -           | _`number`_ | _`2`_   |
| types.INTEGER             | -           | _`number`_ | _`3`_   |
| types.BIGINT              | -           | _`number`_ | _`4`_   |
| types.DECIMAL             | -           | _`number`_ | _`5`_   |
| types.REAL                | -           | _`number`_ | _`6`_   |
| types.DOUBLE              | -           | _`number`_ | _`7`_   |
| types.CHAR                | -           | _`number`_ | _`8`_   |
| types.VARCHAR             | -           | _`number`_ | _`9`_   |
| types.NCHAR               | -           | _`number`_ | _`10`_  |
| types.NVARCHAR            | -           | _`number`_ | _`11`_  |
| types.BINARY              | -           | _`number`_ | _`12`_  |
| types.VARBINARY           | -           | _`number`_ | _`13`_  |
| types.DATE                | -           | _`number`_ | _`14`_  |
| types.TIME                | -           | _`number`_ | _`15`_  |
| types.TIMESTAMP           | -           | _`number`_ | _`16`_  |
| types.CLOB                | -           | _`number`_ | _`25`_  |
| types.NCLOB               | -           | _`number`_ | _`26`_  |
| types.BLOB                | -           | _`number`_ | _`27`_  |
| types.SMALLDECIMAL        | -           | _`number`_ | _`47`_  |
| types.TEXT                | -           | _`number`_ | _`51`_  |
| types.SHORTTEXT           | -           | _`number`_ | _`52`_  |
| types.ALPHANUM            | -           | _`number`_ | _`55`_  |
| types.SECONDDATE          | -           | _`number`_ | _`62`_  |
| types.ST_GEOMETRY         | -           | _`number`_ | _`74`_  |
| types.ST_POINT            | -           | _`number`_ | _`75`_  |


!!! Note
    - **isolation** - constants that represent the isolation levels for a transaction.
    - **types** - set of constants of the database column types.
    - `types.ST_GEOMETRY` - consider using SQL's `ST_asGeoJSON()` on ST_GEOMETRY columns for easy consumption.
    - `types.ST_POINT` - consider using SQL's `ST_asGeoJSON()` on ST_POINT columns for easy consumption

## Classes

### `$.hdb.Connection`

#### Functions

| Function                           | Description                                                 | Returns             |
|------------------------------------|-------------------------------------------------------------|---------------------|
| **close()**                        | Closes the connection.                                      | _`-`_               |
| **commit()**                       | Commits the changes.                                        | _`-`_               | 
| **isClosed()**                     | Checks if the connection is closed.                         | _`boolean`_         |
| **executeQuery(query, args)**      | Executes a database query.                                  | _`$.hdb.ResultSet`_ |
| **executeUpdate(statement, args)** | Executes a SQL statement, which changes the database state. | _`$.hdb.ResultSet`_ |
| **rollback()**                     | Rolls back the changes.                                     | _`-`_               |
| **setAutoCommit(enable)**          | Changes the auto-commit flag of the connection.             | _`-`_               |

### `$.hdb.ResultSet`

#### Functions

| Function          | Description                               | Returns                     |
|-------------------|-------------------------------------------|-----------------------------|
| **getIterator()** | Returns an iterator over this result set. | _`$.hdb.ResultSetIterator`_ |

#### Properties

| Name         | Description                                                | Type                         |
|--------------|------------------------------------------------------------|------------------------------|
| **lenght**   | The number of rows in the $.hdb.ResultSet object           | _`number`_                   |
| **metadata** | Returns the ResultSetMetaData from $.hdb.ResultSet object. | _`$.hdb.ResultSetMetaData`_  |

### `$.hdb.ResultSetIterator`

#### Functions

| Method      | Description                                                                                                    | Type                         |
|-------------|----------------------------------------------------------------------------------------------------------------|------------------------------|
| **next()**  | Checks if the result set has more rows and sets the value of the iterator to the next row if it exists.        | _`boolean`_                  |
| **value()** | Returns the current row that the iterator's value is set to, should always be called after a call to `next()`. | _`row of a $.hdb.ResultSet`_ |

### `$.hdb.ResultSetMetaData`

#### Properties

| Name       | Description                                                                                                 | Type                              |
|------------|-------------------------------------------------------------------------------------------------------------|-----------------------------------|
| **column** | Returns an array of column metadata objects, each of which represents the metadata for a particular column. | _`array of $.hdb.ColumnMetadata`_ |

### `$.hdb. ColumnMetadata`

#### Properties

| Name            | Description                                                 | Type             |
|-----------------|-------------------------------------------------------------|------------------|
| **catalogName** | Returns the column's catalog name.                          | _`string`_       |
| **displaySize** | Returns the column's display size.                          | _`number`_       |
| **isNullable**  | Returns true if the column is nullable and false otherwise. | _`number`_       |
| **label**       | Returns the column's label.                                 | _`string`_       |
| **precision**   | Returns the column's name.                                  | _`string`_       |
| **scale**       | Returns the column's scale.                                 | _`string`_       |
| **tableName**   | Returns the name of the table to which the column belongs.  | _`string`_       |
| **type**        | Returns the column's type.                                  | _`string`_       |
| **typeName**    | Returns the column's type name.                             | _`$.hdb.types`_  |


