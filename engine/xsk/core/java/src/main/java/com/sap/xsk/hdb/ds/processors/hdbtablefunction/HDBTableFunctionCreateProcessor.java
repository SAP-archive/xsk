package com.sap.xsk.hdb.ds.processors.hdbtablefunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedure;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunction;

public class HDBTableFunctionCreateProcessor {
    private static final Logger logger = LoggerFactory.getLogger(HDBTableFunctionCreateProcessor.class);

    private HDBTableFunctionCreateProcessor() {}

    public static void execute(Connection connection, List<XSKDataStructureHDBTableFunction> hdbTableFunctions) throws SQLException {
        for (XSKDataStructureHDBTableFunction func : hdbTableFunctions) {
            String sql = "CREATE " + func.getContent();
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
