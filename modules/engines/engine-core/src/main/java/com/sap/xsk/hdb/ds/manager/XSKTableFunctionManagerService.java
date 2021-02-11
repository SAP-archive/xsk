package com.sap.xsk.hdb.ds.manager;

import com.google.inject.Singleton;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbtablefunction.XSKDataStructureHDBTableFunctionModel;
import com.sap.xsk.hdb.ds.processors.hdbtablefunction.HDBTableFunctionCreateProcessor;
import com.sap.xsk.hdb.ds.processors.hdbtablefunction.HDBTableFunctionDropProcessor;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Singleton
public class XSKTableFunctionManagerService implements XSKDataStructureManager<XSKDataStructureHDBTableFunctionModel> {
    private static final Logger logger = LoggerFactory.getLogger(XSKTableFunctionManagerService.class);

    private final Map<String, XSKDataStructureHDBTableFunctionModel> dataStructureTablefunctionsModels = new LinkedHashMap<>();
    private final List<String> tablefunctionsSynchronized = Collections.synchronizedList(new ArrayList<>());

    private XSKDataStructuresCoreService dataStructuresCoreService;
    private HDBTableFunctionCreateProcessor hdbTableFunctionCreateProcessor;
    private HDBTableFunctionDropProcessor hdbTableFunctionDropProcessor;

    @Override
    public void synchronizeRuntimeMetadata(XSKDataStructureHDBTableFunctionModel hdbTableFunctionModel) throws XSKDataStructuresException {
        // TODO: ommit double calling of finding the hdbTableFunction by extracting it in
        // variable
        // String schemaNameConcatTableFunctionName = hdbTableName.getSchemaName() + "." +
        // hdbProcedure.getName();
        if (!dataStructuresCoreService.existsDataStructure(hdbTableFunctionModel.getLocation(), hdbTableFunctionModel.getType())) {
            dataStructuresCoreService
                    .createDataStructure(hdbTableFunctionModel.getLocation(), hdbTableFunctionModel.getName(), hdbTableFunctionModel.getHash(), hdbTableFunctionModel.getType());
            dataStructureTablefunctionsModels.put(hdbTableFunctionModel.getName(), hdbTableFunctionModel);
            logger.info("Synchronized a new HDB Table Function file [{}] from location: {}", hdbTableFunctionModel.getName(), hdbTableFunctionModel.getLocation());
        } else {
            XSKDataStructureHDBTableFunctionModel existing = dataStructuresCoreService.getDataStructure(hdbTableFunctionModel.getLocation(), hdbTableFunctionModel.getType());
            if (!hdbTableFunctionModel.equals(existing)) {
                dataStructuresCoreService
                        .updateDataStructure(hdbTableFunctionModel.getLocation(), hdbTableFunctionModel.getName(), hdbTableFunctionModel.getHash(), hdbTableFunctionModel.getType());
                dataStructureTablefunctionsModels.put(hdbTableFunctionModel.getName(), hdbTableFunctionModel);
                logger.info("Synchronized a modified HDB Table Function file [{}] from location: {}", hdbTableFunctionModel.getName(), hdbTableFunctionModel.getLocation());
            }
        }
        if (!tablefunctionsSynchronized.contains(hdbTableFunctionModel.getLocation())) {
            tablefunctionsSynchronized.add(hdbTableFunctionModel.getLocation());
        }
    }

    @Override
    public void createDataStructure(Connection connection, XSKDataStructureHDBTableFunctionModel hdbTableFunctionModel) throws SQLException {
        this.hdbTableFunctionCreateProcessor.execute(connection, hdbTableFunctionModel);
    }

    @Override
    public void dropDataStructure(Connection connection, XSKDataStructureHDBTableFunctionModel hdbTableFunctionModel) throws SQLException {
        this.hdbTableFunctionDropProcessor.execute(connection, hdbTableFunctionModel);
    }

    @Override
    public void updateDataStructure(Connection connection, XSKDataStructureHDBTableFunctionModel hdbTableFunctionModel) throws SQLException, OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    public List<String> getDataStructureSynchronized() {
        return Collections.unmodifiableList(this.tablefunctionsSynchronized);
    }

    @Override
    public Map<String, XSKDataStructureHDBTableFunctionModel> getDataStructureModels() {
        return Collections.unmodifiableMap(this.dataStructureTablefunctionsModels);
    }
}
