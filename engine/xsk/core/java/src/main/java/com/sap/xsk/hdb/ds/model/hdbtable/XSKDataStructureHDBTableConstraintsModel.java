/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.model.hdbtable;

import java.util.ArrayList;
import java.util.List;

/**
 * The Data Structure Table Constraints Model.
 */
public class XSKDataStructureHDBTableConstraintsModel {

	private XSKDataStructureHDBTableConstraintPrimaryKeyModel primaryKey;

	private List<XSKDataStructureHDBTableConstraintForeignKeyModel> foreignKeys = new ArrayList<XSKDataStructureHDBTableConstraintForeignKeyModel>();

	private List<XSKDataStructureHDBTableConstraintUniqueModel> uniqueIndices = new ArrayList<XSKDataStructureHDBTableConstraintUniqueModel>();

	private List<XSKDataStructureHDBTableConstraintCheckModel> checks = new ArrayList<XSKDataStructureHDBTableConstraintCheckModel>();

	/**
	 * Gets the primary key.
	 *
	 * @return the primary key
	 */
	public XSKDataStructureHDBTableConstraintPrimaryKeyModel getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * Sets the primary key.
	 *
	 * @param primaryKey the new primary key
	 */
	public void setPrimaryKey(XSKDataStructureHDBTableConstraintPrimaryKeyModel primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * Gets the foreign keys.
	 *
	 * @return the foreign keys
	 */
	public List<XSKDataStructureHDBTableConstraintForeignKeyModel> getForeignKeys() {
		return foreignKeys;
	}

	/**
	 * Gets the unique indices.
	 *
	 * @return the unique indices
	 */
	public List<XSKDataStructureHDBTableConstraintUniqueModel> getUniqueIndices() {
		return uniqueIndices;
	}

	/**
	 * Gets the checks.
	 *
	 * @return the checks
	 */
	public List<XSKDataStructureHDBTableConstraintCheckModel> getChecks() {
		return checks;
	}

}
