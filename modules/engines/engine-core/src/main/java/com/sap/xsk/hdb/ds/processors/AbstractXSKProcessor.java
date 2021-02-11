package com.sap.xsk.hdb.ds.processors;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

import java.sql.Connection;
import java.sql.SQLException;


public abstract class AbstractXSKProcessor {
	public abstract void execute(Connection connection, XSKDataStructureModel entityModel) throws SQLException;
}
