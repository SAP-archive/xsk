/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.api;

import java.io.IOException;
import java.util.List;

import org.eclipse.dirigible.commons.api.service.ICoreService;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

/**
 * The XSK Data Structures Core Service interface.
 */
public interface IXSKDataStructuresCoreService extends ICoreService {
	/**
	 * Creates the DataStructure.
	 *
	 * @param location
	 *            the location
	 * @param name
	 *            the name
	 * @param hash
	 *            the hash
	 * @param type
	 *            the type of the DataStructure
	 * @return the data structure model
	 * @throws XSKDataStructuresException
	 *             the data structures exception
	 */
	public XSKDataStructureModel createDataStructure(String location, String name, String hash, String type) throws XSKDataStructuresException;

	/**
	 * Gets the DataStructure.
	 *
	 * @param location
	 *            the location
	 * @param type
	 *            the type of the DataStructure
	 * @return the DataStructure
	 * @throws XSKDataStructuresException
	 *             the data structures exception
	 */
	public <T extends XSKDataStructureModel> T getDataStructure(String location, String type) throws XSKDataStructuresException;

	/**
	 * Gets the DataStructure by name.
	 *
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @return the DataStructure by name
	 * @throws XSKDataStructuresException
	 *             the data structures exception
	 */
	public <T extends XSKDataStructureModel> T getDataStructureByName(String name, String type) throws XSKDataStructuresException;

	/**
	 * Checks if DataStructure exists.
	 *
	 * @param location
	 *            the location
	 * @param type
	 * 			the type of the DataStructure
	 * @return true, if successful
	 * @throws XSKDataStructuresException
	 *             the data structures exception
	 */
	public boolean existsDataStructure(String location, String type) throws XSKDataStructuresException;

	/**
	 * Removes the DataStructure.
	 *
	 * @param location
	 *            the location
	 * @throws XSKDataStructuresException
	 *             the data structures exception
	 */
	public void removeDataStructure(String location) throws XSKDataStructuresException;

	/**
	 * Update DataStructure.
	 *
	 * @param location
	 *            the location
	 * @param name
	 *            the name
	 * @param hash
	 *            the hash
	 * @param type
	 * 			  the type of the DataStructure
	 * @throws XSKDataStructuresException
	 *             the data structures exception
	 */
	public void updateDataStructure(String location, String name, String hash, String type) throws XSKDataStructuresException;

	/**
	 * Gets the DataStructures.
	 *
	 * @params type
	 * 				the type of the DataStructure
	 * @return the tables
	 * @throws XSKDataStructuresException
	 *             the data structures exception
	 */
	public <T extends XSKDataStructureModel> List<T> getDataStructuresByType(String type) throws XSKDataStructuresException;

	/**
	 * Parses the table.
	 *
	 * @param type
	 *			  the type of the DataStructure
	 * @param location
	 *            the location
	 * @param content
	 *            the content
	 * @return the data structure table model
	 */
	public <T extends XSKDataStructureModel> T parseDataStructure(String type, String location, String content) throws XSKDataStructuresException, IOException;

}
