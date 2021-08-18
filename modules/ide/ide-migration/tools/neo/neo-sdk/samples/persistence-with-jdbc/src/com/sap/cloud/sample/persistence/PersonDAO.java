package com.sap.cloud.sample.persistence;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

/**
 * Data access object encapsulating all JDBC operations for a person.
 */
public class PersonDAO {
    private DataSource dataSource;

    /**
     * Create new data access object with data source.
     */
    public PersonDAO(DataSource newDataSource) throws SQLException {
        setDataSource(newDataSource);
    }

    /**
     * Get data source which is used for the database operations.
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Set data source to be used for the database operations.
     */
    public void setDataSource(DataSource newDataSource) throws SQLException {
        this.dataSource = newDataSource;
        checkTable();
    }

    /**
     * Add a person to the table.
     */
    public void addPerson(Person person) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("INSERT INTO T_PERSONS (ID, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)");
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, person.getFirstName());
            pstmt.setString(3, person.getLastName());
            pstmt.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Get all persons from the table.
     */
    public List<Person> selectAllPersons() throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT ID, FIRSTNAME, LASTNAME FROM T_PERSONS");
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Person> list = new ArrayList<Person>();
            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getString(1));
                p.setFirstName(rs.getString(2));
                p.setLastName(rs.getString(3));
                list.add(p);
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Check if the person table already exists and create it if not.
     */
    private void checkTable() throws SQLException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            if (!existsTable(connection)) {
                createTable(connection);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Check if the person table already exists.
     */
    private boolean existsTable(Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "T_PERSONS", null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (name.equals("T_PERSONS")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create the person table.
     */
    private void createTable(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection
                .prepareStatement("CREATE TABLE T_PERSONS "
                        + "(ID VARCHAR(255) PRIMARY KEY NOT NULL, "
                        + "FIRSTNAME VARCHAR (255),"
                        + "LASTNAME VARCHAR (255))");
        pstmt.executeUpdate();
    }
}
