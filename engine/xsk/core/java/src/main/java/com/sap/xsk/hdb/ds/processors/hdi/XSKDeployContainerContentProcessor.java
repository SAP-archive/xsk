package com.sap.xsk.hdb.ds.processors.hdi;

import java.sql.Connection;
import java.sql.SQLException;

public class XSKDeployContainerContentProcessor extends XSKHDIAbstractProcessor {
	
	public final void execute(Connection connection, String container, String[] deployPaths, String[] undeployPaths) throws SQLException {
		executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #DEPLOY_PATHS LIKE _SYS_DI.TT_FILESFOLDERS;");
    	executeUpdate(connection, "INSERT INTO #DEPLOY_PATHS (PATH) VALUES ('.hdiconfig');");
    	for (String next : deployPaths) {
    		executeUpdate(connection, "INSERT INTO #DEPLOY_PATHS (PATH) VALUES ('" + next.substring(1) + "');");
    	}
    	executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #UNDEPLOY_PATHS LIKE _SYS_DI.TT_FILESFOLDERS;");
    	for (String next : undeployPaths) {
    		executeUpdate(connection, "INSERT INTO #UNDEPLOY_PATHS (PATH) VALUES ('" + next.substring(1) + "');");
    	}
    	executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #PATH_PARAMETERS LIKE _SYS_DI.TT_FILESFOLDERS_PARAMETERS;");
    	
    	executeQuery(connection, "CALL " + container + "#DI.MAKE(#DEPLOY_PATHS, #UNDEPLOY_PATHS, #PATH_PARAMETERS, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
    	
    	executeUpdate(connection, "DROP TABLE #DEPLOY_PATHS;");
    	executeUpdate(connection, "DROP TABLE #UNDEPLOY_PATHS;");
    	executeUpdate(connection, "DROP TABLE #PATH_PARAMETERS;");
	}
	
}
