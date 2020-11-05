/**
 * Copyright (c) 2010-2018 SAP and others.
 */
package com.sap.xsk.hdb.ds.processors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableConstraintForeignKeyModel;
import com.sap.xsk.utils.XSKUtils;

/**
 * The Entity Drop Processor.
 */
public class XSKEntityDropProcessor {

    private XSKEntityDropProcessor() {}

    private static final Logger logger = LoggerFactory.getLogger(XSKEntityDropProcessor.class);

    /**
     * Execute the corresponding statement.
     *
     * @param connection  the connection
     * @param entityModel the table model
     * @throws SQLException the SQL exception
     * @return if delete operation has been performed successfully or the table does not exist
     */
    public static boolean execute(Connection connection, XSKDataStructureEntityModel entityModel) throws SQLException {
    	boolean caseSensitive = Boolean.parseBoolean(Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        String tableName = XSKUtils.getTableName(entityModel);
        if (caseSensitive) {
			tableName = "\"" + tableName + "\"";
		}
        logger.info("Processing Drop Table: {}", tableName);
        if (SqlFactory.getNative(connection).exists(connection, tableName)) {
            String sql = SqlFactory.getNative(connection).select().column("COUNT(*)").from(tableName)
                    .build();

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                logger.info(sql);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        if (count > 0) {
                            logger.error("Drop operation for the non empty Table {} will not be executed. Delete all the records in the table first.", tableName);
                            return false;
                        }
                    }
                }
            } catch (SQLException e) {
                logger.error(sql);
                logger.error(e.getMessage(), e);
            }

            if (entityModel.getConstraints().getForeignKeys() != null) {
                for (XSKDataStructureHDBTableConstraintForeignKeyModel foreignKeyModel : entityModel.getConstraints().getForeignKeys()) {
                    sql = SqlFactory.getNative(connection).drop().constraint(foreignKeyModel.getName()).fromTable(tableName).build();
                    executeUpdate(connection, sql);
                }
            }

            sql = SqlFactory.getNative(connection).drop().table(tableName).build();
            executeUpdate(connection, sql);
            return true;
        }
        logger.warn("Trying to delete non existing Table: {}", tableName);
        return true;
    }

    private static void executeUpdate(Connection connection, String sql) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            logger.info(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(sql);
            logger.error(e.getMessage(), e);
        }
    }

}
