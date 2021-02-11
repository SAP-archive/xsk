package com.sap.xsk.hdb.ds.manager;

import com.google.inject.Singleton;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbview.XSKDataStructureHDBViewModel;
import com.sap.xsk.hdb.ds.processors.XSKViewCreateProcessor;
import com.sap.xsk.hdb.ds.processors.XSKViewDropProcessor;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Singleton
public class XSKViewManagerService implements XSKDataStructureManager<XSKDataStructureHDBViewModel> {
    private static final Logger logger = LoggerFactory.getLogger(XSKViewManagerService.class);

    private final Map<String, XSKDataStructureHDBViewModel> dataStructureViewsModels = new LinkedHashMap<>();
    private final List<String> viewsSynchronized = Collections.synchronizedList(new ArrayList<>());

    private XSKDataStructuresCoreService xskDataStructuresCoreService;
    private XSKViewCreateProcessor xskViewCreateProcessor;
    private XSKViewDropProcessor xskViewDropProcessor;

    @Override
    public void synchronizeRuntimeMetadata(XSKDataStructureHDBViewModel viewModel) throws XSKDataStructuresException {
        if (!xskDataStructuresCoreService.existsDataStructure(viewModel.getLocation(), viewModel.getType())) {
            xskDataStructuresCoreService.createDataStructure(viewModel.getLocation(), viewModel.getName(), viewModel.getHash(), viewModel.getType());
            dataStructureViewsModels.put(viewModel.getName(), viewModel);
            logger.info("Synchronized a new View file [{}] from location: {}", viewModel.getName(), viewModel.getLocation());
        } else {
            XSKDataStructureHDBViewModel existing = xskDataStructuresCoreService.getDataStructure(viewModel.getLocation(), viewModel.getType());
            if (!viewModel.equals(existing)) {
                xskDataStructuresCoreService.updateDataStructure(viewModel.getLocation(), viewModel.getName(), viewModel.getHash(), viewModel.getType());
                dataStructureViewsModels.put(viewModel.getName(), viewModel);
                logger.info("Synchronized a modified View file [{}] from location: {}", viewModel.getName(), viewModel.getLocation());
            }
        }
        if (!viewsSynchronized.contains(viewModel.getLocation())) {
            viewsSynchronized.add(viewModel.getLocation());
        }
    }

    @Override
    public void createDataStructure(Connection connection, XSKDataStructureHDBViewModel viewModel) throws SQLException {
        this.xskViewCreateProcessor.execute(connection, viewModel);
    }

    @Override
    public void dropDataStructure(Connection connection, XSKDataStructureHDBViewModel viewModel) throws SQLException {
        this.xskViewDropProcessor.execute(connection, viewModel);
    }

    @Override
    public void updateDataStructure(Connection connection, XSKDataStructureHDBViewModel viewModel)
            throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    public Map<String, XSKDataStructureHDBViewModel> getDataStructureModels() {
        return Collections.unmodifiableMap(this.dataStructureViewsModels);
    }

    @Override
    public List<String> getDataStructureSynchronized() {
        return Collections.unmodifiableList(this.viewsSynchronized);
    }
}
