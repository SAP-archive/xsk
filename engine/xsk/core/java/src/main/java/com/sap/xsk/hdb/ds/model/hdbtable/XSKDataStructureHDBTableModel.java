/**
 * Copyright (c) 2010-2019 SAP
 */
package com.sap.xsk.hdb.ds.model.hdbtable;

import java.util.ArrayList;
import java.util.List;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

/**
 * The table model representation.
 */
public class XSKDataStructureHDBTableModel extends XSKDataStructureModel {
	
	private String tableType;
	
	private String description;

	private List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<XSKDataStructureHDBTableColumnModel>();

	private XSKDataStructureHDBTableConstraintsModel constraints = new XSKDataStructureHDBTableConstraintsModel();

	/**
	 * Getter for the columns.
	 *
	 * @return the columns
	 */
	public List<XSKDataStructureHDBTableColumnModel> getColumns() {
		return columns;
	}

	/**
	 * Gets the constraints.
	 *
	 * @return the constraints
	 */
	public XSKDataStructureHDBTableConstraintsModel getConstraints() {
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
