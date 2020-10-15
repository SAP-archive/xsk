package com.sap.xsk.hdb.ds.processors.hdbschema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchema;

public class HDBSchemaCreateProcessor {
    private HDBSchemaCreateProcessor() {}

    private static final Logger logger = LoggerFactory.getLogger(HDBSchemaCreateProcessor.class);

    public static void execute(Connection connection, List<XSKDataStructureHDBSchema> hdbSchemas) throws SQLException {
        for (XSKDataStructureHDBSchema schema : hdbSchemas) {
            String sql = "CREATE SCHEMA " + schema.getName();
            executeCreate(connection, sql);
        }
    }

    private static void executeCreate(Connection connection, String sql) throws SQLException {
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
}
