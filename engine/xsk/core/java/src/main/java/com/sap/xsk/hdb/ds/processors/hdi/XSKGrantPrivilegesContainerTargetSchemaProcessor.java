package com.sap.xsk.hdb.ds.processors.hdi;

import java.sql.Connection;
import java.sql.SQLException;

public class XSKGrantPrivilegesContainerTargetSchemaProcessor extends XSKHDIAbstractProcessor {
	
	public final void execute(Connection connection, String container, String[] users) throws SQLException {
    	for (String user : users) {
    		executeUpdate(connection, "GRANT SELECT ON SCHEMA " + user + "  TO " + container + "#OO WITH GRANT OPTIONS;');");
    		executeUpdate(connection, "GRANT EXECUTE ON SCHEMA " + user + "  TO " + container + "#OO WITH GRANT OPTIONS;');");
    	}
	}

}
