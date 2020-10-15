package com.sap.xsk.hdb.ds.processors.hdbschema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchema;

public class HDBSchemaDropProcessor {

    private HDBSchemaDropProcessor(){}

    private static final Logger logger = LoggerFactory.getLogger(HDBSchemaDropProcessor.class);

    public static void execute(Connection connection, List<XSKDataStructureHDBSchema> hdbSchemas) throws SQLException {
        for (XSKDataStructureHDBSchema schema : hdbSchemas) {
            executeSingle(connection, schema);
        }
    }

    private static void executePreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            logger.info(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(e.getMessage(), e);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static void executeSingle(Connection connection, XSKDataStructureHDBSchema hdbSchema) throws SQLException {
        String sql = "DROP SCHEMA " + hdbSchema.getName();
        executePreparedStatement(connection, sql);
    }
}
