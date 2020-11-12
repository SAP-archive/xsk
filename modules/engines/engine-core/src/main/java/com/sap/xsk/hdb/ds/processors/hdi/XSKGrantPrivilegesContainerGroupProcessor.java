package com.sap.xsk.hdb.ds.processors.hdi;

import java.sql.Connection;
import java.sql.SQLException;

public class XSKGrantPrivilegesContainerGroupProcessor extends XSKHDIAbstractProcessor {
	
	public final void execute(Connection connection, String group, String[] users) throws SQLException {
    	executeUpdate(connection, "CREATE LOCAL TEMPORARY COLUMN TABLE #PRIVILEGES LIKE _SYS_DI.TT_API_PRIVILEGES;");
    	for (String user : users) {
    		executeUpdate(connection, "INSERT INTO #PRIVILEGES (PRINCIPAL_NAME, PRIVILEGE_NAME, OBJECT_NAME) SELECT '" + user + "', PRIVILEGE_NAME, OBJECT_NAME FROM _SYS_DI.T_DEFAULT_CONTAINER_GROUP_ADMIN_PRIVILEGES;");
    	}
    	executeQuery(connection, "CALL _SYS_DI.GRANT_CONTAINER_GROUP_API_PRIVILEGES('" + group + "', #PRIVILEGES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
    	executeUpdate(connection, "DROP TABLE #PRIVILEGES;");
	}

}
