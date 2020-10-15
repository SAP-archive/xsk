/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.xsodata.ds.service;

import static java.text.MessageFormat.format;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import org.eclipse.dirigible.api.v3.security.UserFacade;
import org.eclipse.dirigible.database.persistence.PersistenceManager;
import org.eclipse.dirigible.database.sql.SqlFactory;

import com.sap.xsk.xsodata.ds.api.IXSKODataCoreService;
import com.sap.xsk.xsodata.ds.api.XSKODataException;
import com.sap.xsk.xsodata.ds.model.XSKODataAssociation;
import com.sap.xsk.xsodata.ds.model.XSKODataEntity;
import com.sap.xsk.xsodata.ds.model.XSKODataModel;
import com.sap.xsk.xsodata.ds.model.XSKODataModelFactory;

/**
 * The Data Structure Core Service.
 */
@Singleton
public class XSKODataCoreService implements IXSKODataCoreService {

	@Inject
	private DataSource dataSource;
	
	@Inject
	private XSKODataModelFactory factory;

	@Inject
	private PersistenceManager<XSKODataModel> odataPersistenceManager;


	// OData

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#createOData(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public XSKODataModel createOData(String location, String name, String hash) throws XSKODataException {
		XSKODataModel tableModel = new XSKODataModel();
		tableModel.setLocation(location);
		tableModel.setName(name);
		tableModel.setHash(hash);
		tableModel.setCreatedBy(UserFacade.getName());
		tableModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));

		try {
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
				odataPersistenceManager.insert(connection, tableModel);
				return tableModel;
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException e) {
			throw new XSKODataException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#getOData(java.lang.String)
	 */
	@Override
	public XSKODataModel getOData(String location) throws XSKODataException {
		try {
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
				return odataPersistenceManager.find(connection, XSKODataModel.class, location);
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException e) {
			throw new XSKODataException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#getODataByName(java.lang.String)
	 */
	@Override
	public XSKODataModel getODataByName(String name) throws XSKODataException {
		try {
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
				String sql = SqlFactory.getNative(connection).select().column("*").from("XSK_ODATA")
						.where("OD_NAME = ?").toString();
				List<XSKODataModel> tableModels = odataPersistenceManager.query(connection, XSKODataModel.class, sql,
						Arrays.asList(name));
				if (tableModels.isEmpty()) {
					return null;
				}
				if (tableModels.size() > 1) {
					throw new XSKODataException(format("There are more that one OData with the same name [{0}] at locations: [{1}] and [{2}].",
							name, tableModels.get(0).getLocation(), tableModels.get(1).getLocation()));
				}
				return tableModels.get(0);
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException e) {
			throw new XSKODataException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#removeOData(java.lang.String)
	 */
	@Override
	public void removeOData(String location) throws XSKODataException {
		try {
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
				odataPersistenceManager.delete(connection, XSKODataModel.class, location);
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException e) {
			throw new XSKODataException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#updateOData(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void updateOData(String location, String name, String hash) throws XSKODataException {
		try {
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
				XSKODataModel tableModel = getOData(location);
				tableModel.setName(name);
				tableModel.setHash(hash);
				odataPersistenceManager.update(connection, tableModel);
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException e) {
			throw new XSKODataException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#getODatas()
	 */
	@Override
	public List<XSKODataModel> getODatas() throws XSKODataException {
		try {
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
				String sql = SqlFactory.getNative(connection).select().column("*").from("XSK_ODATA").toString();
				List<XSKODataModel> tableModels = odataPersistenceManager.query(connection, XSKODataModel.class, sql);
				return tableModels;
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException e) {
			throw new XSKODataException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#existsOData(java.lang.String)
	 */
	@Override
	public boolean existsOData(String location) throws XSKODataException {
		return getOData(location) != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.dirigible.database.ds.api.IDataStructuresCoreService#parseOData(java.lang.String)
	 */
	@Override
	public XSKODataModel parseOData(String location, String content) throws Exception {
		return factory.parseOData(location, content);
	}
	
	
	
	
	public static XSKODataEntity getEntity(XSKODataModel model, String alias, String navigation) {
		for (XSKODataEntity entity : model.getService().getEntities()) {
			if (alias.equals(entity.getAlias())) {
				return entity;
			}
		}
		throw new IllegalArgumentException(String.format("There is no entity with name: %s, referenced by the navigation: %s", alias, navigation));
	}
    
	public static XSKODataAssociation getAssociation(XSKODataModel model, String name, String navigation) {
		for (XSKODataAssociation association : model.getService().getAssociations()) {
			if (name.equals(association.getName())) {
				return association;
			}
		}
		throw new IllegalArgumentException(String.format("There is no association with name: %s, referenced by the navigation: %s", name, navigation));
	}

}
