/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Data Structure Table Constraints Model.
 */
public class XSKDataStructureTableConstraintsModel {

	private XSKDataStructureTableConstraintPrimaryKeyModel primaryKey;

	private List<XSKDataStructureTableConstraintForeignKeyModel> foreignKeys = new ArrayList<XSKDataStructureTableConstraintForeignKeyModel>();

	private List<XSKDataStructureTableConstraintUniqueModel> uniqueIndices = new ArrayList<XSKDataStructureTableConstraintUniqueModel>();

	private List<XSKDataStructureTableConstraintCheckModel> checks = new ArrayList<XSKDataStructureTableConstraintCheckModel>();

	/**
	 * Gets the primary key.
	 *
	 * @return the primary key
	 */
	public XSKDataStructureTableConstraintPrimaryKeyModel getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * Sets the primary key.
	 *
	 * @param primaryKey the new primary key
	 */
	public void setPrimaryKey(XSKDataStructureTableConstraintPrimaryKeyModel primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * Gets the foreign keys.
	 *
	 * @return the foreign keys
	 */
	public List<XSKDataStructureTableConstraintForeignKeyModel> getForeignKeys() {
		return foreignKeys;
	}

	/**
	 * Gets the unique indices.
	 *
	 * @return the unique indices
	 */
	public List<XSKDataStructureTableConstraintUniqueModel> getUniqueIndices() {
		return uniqueIndices;
	}

	/**
	 * Gets the checks.
	 *
	 * @return the checks
	 */
	public List<XSKDataStructureTableConstraintCheckModel> getChecks() {
		return checks;
	}

}
