/**
 * Copyright (c) 2010-2019 SAP
 */
package com.sap.xsk.hdb.ds.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The table model representation.
 */
public class XSKDataStructureTableModel extends XSKDataStructureModel {
	
	private String tableType;
	
	private String description;

	private List<XSKDataStructureTableColumnModel> columns = new ArrayList<XSKDataStructureTableColumnModel>();

	private XSKDataStructureTableConstraintsModel constraints = new XSKDataStructureTableConstraintsModel();

	/**
	 * Getter for the columns.
	 *
	 * @return the columns
	 */
	public List<XSKDataStructureTableColumnModel> getColumns() {
		return columns;
	}

	/**
	 * Gets the constraints.
	 *
	 * @return the constraints
	 */
	public XSKDataStructureTableConstraintsModel getConstraints() {
		return constraints;
	}

	/**
	 * @return the tableType
	 */
	public String getTableType() {
		return tableType;
	}

	/**
	 * @param tableType the tableType to set
	 */
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
