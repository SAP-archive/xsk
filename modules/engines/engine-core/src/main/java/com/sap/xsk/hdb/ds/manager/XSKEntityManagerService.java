package com.sap.xsk.hdb.ds.manager;

import com.google.inject.Singleton;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntitiesModel;
import com.sap.xsk.hdb.ds.model.hdbdd.XSKDataStructureEntityModel;
import com.sap.xsk.hdb.ds.processors.XSKEntityAlterProcessor;
import com.sap.xsk.hdb.ds.processors.XSKEntityCreateProcessor;
import com.sap.xsk.hdb.ds.processors.XSKEntityDropProcessor;
import com.sap.xsk.hdb.ds.processors.XSKEntityUpdateProcessor;
import com.sap.xsk.hdb.ds.service.XSKDataStructuresCoreService;
import com.sap.xsk.utils.XSKUtils;
import org.eclipse.dirigible.commons.config.Configuration;
import org.eclipse.dirigible.core.scheduler.api.SynchronizationException;
import org.eclipse.dirigible.database.ds.model.IDataStructureModel;
import org.eclipse.dirigible.database.sql.SqlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static java.text.MessageFormat.format;

@Singleton
public class XSKEntityManagerService implements XSKDataStructureManager<XSKDataStructureEntitiesModel> {
    private static final Logger logger = LoggerFactory.getLogger(XSKEntityManagerService.class);

    private final Map<String, XSKDataStructureEntitiesModel> dataStructureEntitiesModel = Collections
            .synchronizedMap(new LinkedHashMap());
    private final List<String> entitiesSynchronized = Collections.synchronizedList(new ArrayList<>());

    private XSKDataStructuresCoreService dataStructuresCoreService;
    private XSKEntityUpdateProcessor xskEntityUpdateProcessor;
    private XSKEntityDropProcessor xskEntityDropProcessor;
    private XSKEntityCreateProcessor xskEntityCreateProcessor;

    public XSKEntityManagerService() {
        System.out.println("__________________________________________ Created Instace Manager Entity __________________________________________");
    }

    @Override
    public void synchronizeRuntimeMetadata(XSKDataStructureEntitiesModel entitiesModel) throws XSKDataStructuresException {
        if (!dataStructuresCoreService.existsDataStructure(entitiesModel.getLocation(), entitiesModel.getType())) {
            dataStructuresCoreService
                    .createDataStructure(entitiesModel.getLocation(), entitiesModel.getName(), entitiesModel.getHash(), entitiesModel.getType());
            dataStructureEntitiesModel.put(entitiesModel.getName(), entitiesModel);
            logger.info("Synchronized a new Entities file [{}] from location: {}", entitiesModel.getName(), entitiesModel.getLocation());
        } else {
            XSKDataStructureEntitiesModel existing = dataStructuresCoreService.getDataStructure(entitiesModel.getLocation(), entitiesModel.getType());
            if (!entitiesModel.equals(existing)) {
                dataStructuresCoreService
                        .updateDataStructure(entitiesModel.getLocation(), entitiesModel.getName(), entitiesModel.getHash(), entitiesModel.getType());
                dataStructureEntitiesModel.put(entitiesModel.getName(), entitiesModel);
                logger.info("Synchronized a modified Entities file [{}] from location: {}", entitiesModel.getName(), entitiesModel.getLocation());
            }
        }
        if (!entitiesSynchronized.contains(entitiesModel.getLocation())) {
            entitiesSynchronized.add(entitiesModel.getLocation());
        }
    }

    @Override
    public void createDataStructure(Connection connection, XSKDataStructureEntitiesModel entitiesModel) throws SQLException {
        boolean caseSensitive = Boolean.parseBoolean(
                Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        if (entitiesModel != null) {
            for (XSKDataStructureEntityModel entityModel : entitiesModel.getContext().getЕntities()) {
                String tableName = XSKUtils.getTableName(entityModel);
                if (caseSensitive) {
                    tableName = "\"" + tableName + "\"";
                }
                if (!SqlFactory.getNative(connection).exists(connection, tableName)) {
                    this.xskEntityCreateProcessor.execute(connection, entityModel);
                } else {
                    this.xskEntityUpdateProcessor.execute(connection, entityModel);
                }
            }
        }

    }

    @Override
    public void dropDataStructure(Connection connection, XSKDataStructureEntitiesModel entitiesModel) throws SQLException {
        boolean caseSensitive = Boolean.parseBoolean(
                Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        if (entitiesModel != null) {
            for (XSKDataStructureEntityModel entityModel : entitiesModel.getContext().getЕntities()) {
                String tableName = XSKUtils.getTableName(entityModel);
                if (caseSensitive) {
                    tableName = "\"" + tableName + "\"";
                }
                if (SqlFactory.getNative(connection).exists(connection, tableName)) {
                    if (SqlFactory.getNative(connection).count(connection, tableName) == 0) {
                        xskEntityDropProcessor.execute(connection, entityModel);
                    } else {
                        logger.warn(format("Entity [{0}] cannot be deleted during the update process, because it is not empty", entityModel.getName()));
                    }
                }
            }
        }
    }

    @Override
    public void updateDataStructure(Connection connection, XSKDataStructureEntitiesModel entitiesModel)
            throws SQLException, OperationNotSupportedException {
        boolean caseSensitive = Boolean.parseBoolean(
                Configuration.get(IDataStructureModel.DIRIGIBLE_DATABASE_NAMES_CASE_SENSITIVE, "false"));
        if (entitiesModel != null) {
            for (XSKDataStructureEntityModel entityModel : entitiesModel.getContext().getЕntities()) {
                String tableName = XSKUtils.getTableName(entityModel);
                if (caseSensitive) {
                    tableName = "\"" + tableName + "\"";
                }
                if (SqlFactory.getNative(connection).exists(connection, tableName)) {
                    if (SqlFactory.getNative(connection).count(connection, tableName) != 0) {
                        this.xskEntityUpdateProcessor.execute(connection, entityModel);
                    }
                }
            }
        }
    }

    @Override
    public Map<String, XSKDataStructureEntitiesModel> getDataStructureModels() {
        return Collections.unmodifiableMap(this.dataStructureEntitiesModel);
    }

    @Override
    public List<String> getDataStructureSynchronized() {
        return Collections.unmodifiableList(this.entitiesSynchronized);
    }
}
