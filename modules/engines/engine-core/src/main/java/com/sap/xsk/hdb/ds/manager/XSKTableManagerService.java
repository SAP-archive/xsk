package com.sap.xsk.hdb.ds.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;
import com.sap.xsk.hdb.ds.processors.XSKTableCreateProcessor;
import com.sap.xsk.hdb.ds.processors.XSKTableDropProcessor;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Singleton
public class XSKTableManagerService implements XSKDataStructureManager<XSKDataStructureHDBTableModel> {

	private static final Logger logger = LoggerFactory.getLogger(XSKTableManagerService.class);

	@Inject
	private XSKDataStructuresCoreService dataStructuresCoreService;
	private XSKTableCreateProcessor xskTableCreateProcessor;
	private XSKTableDropProcessor xskTableDropProcessor;
	private final Map<String, XSKDataStructureHDBTableModel> dataStructureTableModels = new LinkedHashMap<>();
	private final List<String> tablesSynchronized = Collections.synchronizedList(new ArrayList<>());


	public void synchronizeRuntimeMetadata(XSKDataStructureHDBTableModel tableModel)
			throws XSKDataStructuresException {
		if (!dataStructuresCoreService.existsDataStructure(tableModel.getLocation(), tableModel.getType())) {
			dataStructuresCoreService
					.createDataStructure(tableModel.getLocation(), tableModel.getName(), tableModel.getHash(), tableModel.getType());
			dataStructureTableModels.put(tableModel.getName(), tableModel);
			logger.info("Synchronized a new Table file [{}] from location: {}", tableModel.getName(), tableModel.getLocation());
		}
		else {
			XSKDataStructureHDBTableModel existing = dataStructuresCoreService
					.getDataStructure(tableModel.getLocation(), tableModel.getType());
			if (!tableModel.equals(existing)) {
				dataStructuresCoreService.updateDataStructure(tableModel.getLocation(), tableModel.getName(), tableModel.getHash(),
						tableModel.getType());
				dataStructureTableModels.put(tableModel.getName(), tableModel);
				logger.info("Synchronized a modified Table file [{}] from location: {}", tableModel.getName(),
						tableModel.getLocation());
			}
		}
		if (!tablesSynchronized.contains(tableModel.getLocation())) {
			tablesSynchronized.add(tableModel.getLocation());
		}
	}

	public void createDataStructure(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException
	{
		this.xskTableCreateProcessor.execute(connection, tableModel);
	}

	public void dropDataStructure(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
		this.xskTableDropProcessor.execute(connection, tableModel);
	}

	public void updateDataStructure(Connection connection, XSKDataStructureHDBTableModel tableModel) throws SQLException {
		//TODO: Create logic for updating hdb table
		logger.error("Altering of a non-empty table is not implemented yet.");
		// TableAlterProcessor.execute(connection, tableModel);
	}

	public Map<String, XSKDataStructureHDBTableModel> getDataStructureModels() {
		return this.dataStructureTableModels;
	}

	public List<String> getDataStructureSynchronized() {
		return this.tablesSynchronized;
	}
}
