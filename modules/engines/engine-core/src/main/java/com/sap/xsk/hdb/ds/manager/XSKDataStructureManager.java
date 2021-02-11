package com.sap.xsk.hdb.ds.manager;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface XSKDataStructureManager <T extends XSKDataStructureModel> {

	Map<String, T> getDataStructureModels();

	void synchronizeRuntimeMetadata(T tableModel) throws XSKDataStructuresException;

	void createDataStructure(Connection connection, T tableModel) throws SQLException;

	void dropDataStructure(Connection connection, T tableModel) throws SQLException;

	void updateDataStructure(Connection connection, T tableModel) throws SQLException, OperationNotSupportedException;

	List<String> getDataStructureSynchronized();
}
