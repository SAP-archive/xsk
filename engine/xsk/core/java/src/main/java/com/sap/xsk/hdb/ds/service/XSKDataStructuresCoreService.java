package com.sap.xsk.hdb.ds.service;

import static java.text.MessageFormat.format;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.eclipse.dirigible.database.sql.SqlFactory;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.IXSKDataStructuresCoreService;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;
import com.sap.xsk.hdb.ds.parser.calcview.XSKCalculationViewParser;
import com.sap.xsk.hdb.ds.parser.entities.XSKEntitiesParser;
import com.sap.xsk.hdb.ds.parser.hdbprocedure.XSKHDBProcedureParser;
import com.sap.xsk.hdb.ds.parser.hdbschema.XSKHDBSchemaParser;
import com.sap.xsk.hdb.ds.parser.hdbtablefunction.XSKHDBTableFunctionParser;
import com.sap.xsk.hdb.ds.parser.table.XSKTableParser;
import com.sap.xsk.hdb.ds.parser.view.XSKViewParser;

@Singleton
public class XSKDataStructuresCoreService implements IXSKDataStructuresCoreService {
//Use this if we want to keep the parsers in a resource file and load them via ServiceLoader
//    private static final ServiceLoader<XSKDataStructureParser> DATA_STRUCTURE_PARSERS = ServiceLoader.load(XSKDataStructureParser.class);
//    static {
//        parsers = new HashMap<>();
//        Iterator it = DATA_STRUCTURE_PARSERS.iterator();
//        while(it.hasNext()){
//            XSKDataStructureParser current = (XSKDataStructureParser) it.next();
//            parsers.put(current.getType(), current);
//        }
//    }

    static Map<String, XSKDataStructureParser> parsers;

    static {
        //Add other parsers if new datastructures occur
        parsers = new HashMap<>();
        parsers.put(IXSKDataStructureModel.TYPE_CALCVIEW, new XSKCalculationViewParser());
        parsers.put(IXSKDataStructureModel.TYPE_ENTITIES, new XSKEntitiesParser());
        parsers.put(IXSKDataStructureModel.TYPE_HDB_PROCEDURE, new XSKHDBProcedureParser());
        parsers.put(IXSKDataStructureModel.TYPE_HDB_SCHEMA, new XSKHDBSchemaParser());
        parsers.put(IXSKDataStructureModel.TYPE_TABLE, new XSKTableParser());
        parsers.put(IXSKDataStructureModel.TYPE_VIEW, new XSKViewParser());
        parsers.put(IXSKDataStructureModel.TYPE_HDB_TABLE_FUNCTION, new XSKHDBTableFunctionParser());
    }

    @Inject
    private DataSource dataSource;

    @Inject
    private PersistenceManager<XSKDataStructureModel> persistenceManager;

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#createDataStructure(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public XSKDataStructureModel createDataStructure(String location, String name, String hash, String type) throws XSKDataStructuresException {
        XSKDataStructureModel dataStructure = new XSKDataStructureModel();
        dataStructure.setLocation(location);
        dataStructure.setName(name);
        dataStructure.setType(type);
        dataStructure.setHash(hash);
        dataStructure.setCreatedBy(UserFacade.getName());
        dataStructure.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

        try (Connection connection = dataSource.getConnection()) {
            persistenceManager.insert(connection, dataStructure);
            return dataStructure;
        } catch (SQLException e) {
            throw new XSKDataStructuresException(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#getDataStructure(java.lang.String, java.lang.String)
     */
    @Override
    public <T extends XSKDataStructureModel> T getDataStructure(String location, String type) throws XSKDataStructuresException {
        try (Connection connection = dataSource.getConnection()) {
            return (T) persistenceManager.find(connection, parsers.get(type).getDataStructureClass(), location);
        } catch (SQLException e) {
            throw new XSKDataStructuresException(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#getDataStructureByName(java.lang.String, java.lang.String)
     */
    @Override
    public <T extends XSKDataStructureModel> T getDataStructureByName(String name, String type) throws XSKDataStructuresException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = SqlFactory.getNative(connection).select().column("*").from("XSK_DATA_STRUCTURES")
                    .where("DS_NAME = ? AND DS_TYPE = ?").toString();
            List<XSKDataStructureModel> dataStructures = persistenceManager.query(connection, XSKDataStructureModel.class, sql,
                    Arrays.asList(name, type));
            if (dataStructures.isEmpty()) {
                return null;
            }
            if (dataStructures.size() > 1) {
                throw new XSKDataStructuresException(format("There are more that one Table with the same name [{0}] at locations: [{1}] and [{2}].",
                        name, dataStructures.get(0).getLocation(), dataStructures.get(1).getLocation()));
            }
            return (T) dataStructures.get(0);
        } catch (SQLException e) {
            throw new XSKDataStructuresException(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#removeDataStructure(java.lang.String)
     */
    @Override
    public void removeDataStructure(String location) throws XSKDataStructuresException {
        try (Connection connection = dataSource.getConnection()) {
            persistenceManager.delete(connection, XSKDataStructureModel.class, location);
        } catch (SQLException e) {
            throw new XSKDataStructuresException(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#updateDataStructure(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void updateDataStructure(String location, String name, String hash, String type) throws XSKDataStructuresException {
        try (Connection connection = dataSource.getConnection()) {
            XSKDataStructureModel dataStructure = getDataStructure(location, type);
            dataStructure.setName(name);
            dataStructure.setHash(hash);
            persistenceManager.update(connection, dataStructure);
        } catch (SQLException e) {
            throw new XSKDataStructuresException(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#getDataStructures(java.lang.String)
     */
    @Override
    public <T extends XSKDataStructureModel> List<T> getDataStructuresByType(String type) throws XSKDataStructuresException {
        try (Connection connection = dataSource.getConnection()) {
            String sql = SqlFactory.getNative(connection).select().column("*").from("XSK_DATA_STRUCTURES").where("DS_TYPE = ?").toString();
            return (List<T>) persistenceManager.query(connection, parsers.get(type).getDataStructureClass(), sql,
                    Arrays.asList(type));
        } catch (SQLException e) {
            throw new XSKDataStructuresException(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#existsDataStructure(java.lang.String, java.lang.String)
     */
    @Override
    public boolean existsDataStructure(String location, String type) throws XSKDataStructuresException {
        return getDataStructure(location, type) != null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#parseDataStructure(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public <T extends XSKDataStructureModel> T parseDataStructure(String type, String location, String content) throws XSKDataStructuresException, IOException {
        XSKDataStructureParser parser = parsers.get(type);
        return parser.parse(location, content);
    }

}
