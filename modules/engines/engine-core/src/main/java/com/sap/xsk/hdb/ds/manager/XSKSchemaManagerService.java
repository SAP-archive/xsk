package com.sap.xsk.hdb.ds.manager;

import com.google.inject.Singleton;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbschema.XSKDataStructureHDBSchemaModel;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbschema.HDBSchemaDropProcessor;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Singleton
public class XSKSchemaManagerService implements XSKDataStructureManager<XSKDataStructureHDBSchemaModel> {

	private static final Logger logger = LoggerFactory.getLogger(XSKSchemaManagerService.class);

	private XSKDataStructuresCoreService xskDataStructuresCoreService;
	private HDBSchemaCreateProcessor hdbSchemaCreateProcessor;
	private HDBSchemaDropProcessor hdbSchemaDropProcessor;
	private Map<String, XSKDataStructureHDBSchemaModel> dataStructureSchemasModels = new LinkedHashMap();
	private final List<String> schemasSynchronized = Collections.synchronizedList(new ArrayList<>());

	@Override public void synchronizeRuntimeMetadata(XSKDataStructureHDBSchemaModel schemaModel) throws XSKDataStructuresException
	{
		// TODO: ommit double calling of finding the hdbProcedure by extracting it in
		// variable
		// String schemaNameConcatProcedureName = hdbProcedure.getSchemaName() + "." +
		// hdbProcedure.getName();
		//logger.info("here");
		if (!xskDataStructuresCoreService.existsDataStructure(schemaModel.getLocation(), schemaModel.getType())) {
			xskDataStructuresCoreService
					.createDataStructure(schemaModel.getLocation(), schemaModel.getName(), schemaModel.getHash(), schemaModel.getType());
			dataStructureSchemasModels.put(schemaModel.getName(), schemaModel);
			logger.info("Synchronized a new HDB Schema file [{}] from location: {}", schemaModel.getName(), schemaModel.getLocation());
		} else {
			XSKDataStructureHDBSchemaModel existing = xskDataStructuresCoreService.getDataStructure(schemaModel.getLocation(), schemaModel.getType());
			if (!schemaModel.equals(existing)) {
				xskDataStructuresCoreService
						.updateDataStructure(schemaModel.getLocation(), schemaModel.getName(), schemaModel.getHash(), schemaModel.getType());
				dataStructureSchemasModels.put(schemaModel.getName(), schemaModel);
				logger.info("Synchronized a modified HDB Schema file [{}] from location: {}", schemaModel.getName(), schemaModel.getLocation());
			}
		}
		if (!schemasSynchronized.contains(schemaModel.getLocation())) {
			schemasSynchronized.add(schemaModel.getLocation());
		}
	}

	@Override public void createDataStructure(Connection connection, XSKDataStructureHDBSchemaModel schemaModel) throws SQLException
	{
		this.hdbSchemaCreateProcessor.execute(connection, schemaModel);
	}

	@Override public void dropDataStructure(Connection connection, XSKDataStructureHDBSchemaModel schemaModel) throws SQLException
	{
		this.hdbSchemaDropProcessor.execute(connection, schemaModel);
	}

	@Override public void updateDataStructure(Connection connection, XSKDataStructureHDBSchemaModel schemaModel)
			throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}

	@Override public List<String> getDataStructureSynchronized()
	{
		return Collections.unmodifiableList(this.schemasSynchronized);
	}

	@Override public Map<String, XSKDataStructureHDBSchemaModel> getDataStructureModels()
	{
		return Collections.unmodifiableMap(this.dataStructureSchemasModels);
	}
}
