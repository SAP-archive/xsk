package com.sap.xsk.hdb.ds.manager;

import com.google.inject.Singleton;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdi.XSKDataStructureHDIModel;
import com.sap.xsk.hdb.ds.processors.hdi.XSKHDIContainerCreateProcessor;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Singleton
public class XSKHDIContainerManagerService implements XSKDataStructureManager<XSKDataStructureHDIModel> {

    private static final Logger logger = LoggerFactory.getLogger(XSKHDIContainerManagerService.class);

    private final List<String> hdiSynchronized = Collections.synchronizedList(new ArrayList<>());
    private final Map<String, XSKDataStructureHDIModel> dataStructureHdiModels = new LinkedHashMap<>();

    private XSKDataStructuresCoreService dataStructuresCoreService;
    private XSKHDIContainerCreateProcessor xskhdiContainerCreateProcessor;

    @Override
    public void synchronizeRuntimeMetadata(XSKDataStructureHDIModel hdi) throws XSKDataStructuresException {
        if (!dataStructuresCoreService.existsDataStructure(hdi.getLocation(), hdi.getType())) {
            dataStructuresCoreService
                    .createDataStructure(hdi.getLocation(), hdi.getName(), hdi.getHash(), hdi.getType());
            dataStructureHdiModels.put(hdi.getName(), hdi);
            logger.info("Synchronized a new HDI file [{}] from location: {}", hdi.getName(), hdi.getLocation());
        } else {
            XSKDataStructureHDIModel existing = dataStructuresCoreService.getDataStructure(hdi.getLocation(), hdi.getType());
            if (!hdi.equals(existing)) {
                dataStructuresCoreService
                        .updateDataStructure(hdi.getLocation(), hdi.getName(), hdi.getHash(), hdi.getType());
                dataStructureHdiModels.put(hdi.getName(), hdi);
                logger.info("Synchronized a modified HDI file [{}] from location: {}", hdi.getName(), hdi.getLocation());
            }
        }
        if (!hdiSynchronized.contains(hdi.getLocation())) {
            hdiSynchronized.add(hdi.getLocation());
        }
    }

    @Override
    public void createDataStructure(Connection connection, XSKDataStructureHDIModel hdiModel) throws SQLException {
        this.xskhdiContainerCreateProcessor.execute(connection, hdiModel);
    }

    @Override
    public void dropDataStructure(Connection connection, XSKDataStructureHDIModel hdiModel) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateDataStructure(Connection connection, XSKDataStructureHDIModel hdiModel) throws SQLException, OperationNotSupportedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getDataStructureSynchronized() {
        return Collections.unmodifiableList(this.hdiSynchronized);
    }

    @Override
    public Map<String, XSKDataStructureHDIModel> getDataStructureModels() {
        return Collections.unmodifiableMap(this.dataStructureHdiModels);
    }
}
