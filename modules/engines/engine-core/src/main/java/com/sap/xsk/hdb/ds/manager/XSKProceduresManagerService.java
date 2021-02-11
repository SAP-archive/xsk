package com.sap.xsk.hdb.ds.manager;

import com.google.inject.Singleton;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel;
import com.sap.xsk.hdb.ds.processors.hdbprocedure.HDBProcedureCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbprocedure.HDBProcedureDropProcessor;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Singleton
public class XSKProceduresManagerService implements XSKDataStructureManager<XSKDataStructureHDBProcedureModel> {

    private static final Logger logger = LoggerFactory.getLogger(XSKProceduresManagerService.class);


    private final Map<String, XSKDataStructureHDBProcedureModel> dataStructureProceduresModels = new LinkedHashMap<>();
    private final List<String> proceduresSynchronized = Collections.synchronizedList(new ArrayList<>());

    private XSKDataStructuresCoreService xskDataStructuresCoreService;
    private HDBProcedureDropProcessor hdbProcedureDropProcessor;
    private HDBProcedureCreateProcessor hdbProcedureCreateProcessor;

    @Override
    public void synchronizeRuntimeMetadata(XSKDataStructureHDBProcedureModel hdbProcedureModel) throws XSKDataStructuresException {
        // TODO: ommit double calling of finding the hdbProcedure by extracting it in
        // variable
        // String schemaNameConcatProcedureName = hdbProcedure.getSchemaName() + "." +
        // hdbProcedure.getName();
        if (!xskDataStructuresCoreService.existsDataStructure(hdbProcedureModel.getLocation(), hdbProcedureModel.getType())) {
            xskDataStructuresCoreService
                    .createDataStructure(hdbProcedureModel.getLocation(), hdbProcedureModel.getName(), hdbProcedureModel.getHash(), hdbProcedureModel.getType());
            dataStructureProceduresModels.put(hdbProcedureModel.getName(), hdbProcedureModel);
            logger.info("Synchronized a new HDB Procedure file [{}] from location: {}", hdbProcedureModel.getName(), hdbProcedureModel.getLocation());
        } else {
            XSKDataStructureHDBProcedureModel existing = xskDataStructuresCoreService.getDataStructure(hdbProcedureModel.getLocation(), hdbProcedureModel.getType());
            if (!hdbProcedureModel.equals(existing)) {
                xskDataStructuresCoreService
                        .updateDataStructure(hdbProcedureModel.getLocation(), hdbProcedureModel.getName(), hdbProcedureModel.getHash(), hdbProcedureModel.getType());
                dataStructureProceduresModels.put(hdbProcedureModel.getName(), hdbProcedureModel);
                logger.info("Synchronized a modified HDB Procedure file [{}] from location: {}", hdbProcedureModel.getName(), hdbProcedureModel.getLocation());
            }
        }
        if (!proceduresSynchronized.contains(hdbProcedureModel.getLocation())) {
            proceduresSynchronized.add(hdbProcedureModel.getLocation());
        }
    }

    @Override
    public void createDataStructure(Connection connection, XSKDataStructureHDBProcedureModel viewModel) throws SQLException {
        this.hdbProcedureCreateProcessor.execute(connection, viewModel);
    }

    @Override
    public void dropDataStructure(Connection connection, XSKDataStructureHDBProcedureModel viewModel) throws SQLException {
        this.hdbProcedureDropProcessor.execute(connection, viewModel);
    }

    @Override
    public void updateDataStructure(Connection connection, XSKDataStructureHDBProcedureModel viewModel) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    public List<String> getDataStructureSynchronized() {
        return Collections.unmodifiableList(this.proceduresSynchronized);
    }

    @Override
    public Map<String, XSKDataStructureHDBProcedureModel> getDataStructureModels() {
        return Collections.unmodifiableMap(this.dataStructureProceduresModels);
    }
}
