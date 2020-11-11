package com.sap.xsk.hdb.ds.processors.hdi;

import java.sql.Connection;
import java.sql.SQLException;

public class XSKConfigureLibrariesProcessor extends XSKHDIAbstractProcessor {
	
	public final void execute(Connection connection, String container) throws SQLException {
		executeQuery(connection, "CALL " + container + "#DI.CONFIGURE_LIBRARIES(_SYS_DI.T_DEFAULT_LIBRARIES, _SYS_DI.T_NO_PARAMETERS, ?, ?, ?);");
	}

}
