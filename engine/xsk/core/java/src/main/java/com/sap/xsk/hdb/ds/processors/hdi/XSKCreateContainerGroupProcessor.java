package com.sap.xsk.hdb.ds.processors.hdi;

import java.sql.Connection;
import java.sql.SQLException;

public class XSKCreateContainerGroupProcessor extends XSKHDIAbstractProcessor {
	
	public final void execute(Connection connection, String group) throws SQLException {
		executeQuery(connection, "CALL _SYS_DI.CREATE_CONTAINER_GROUP('" + group + "', _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
	}

}
