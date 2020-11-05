package com.sap.xsk.hdb.ds.processors.hdi;

import java.sql.Connection;
import java.sql.SQLException;

public class XSKCreateContainerProcessor extends XSKHDIAbstractProcessor {
	
	public final void execute(Connection connection, String group, String container) throws SQLException {
		executeQuery(connection, "CALL _SYS_DI#" + group + ".CREATE_CONTAINER('" + container + "', _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
	}

}
