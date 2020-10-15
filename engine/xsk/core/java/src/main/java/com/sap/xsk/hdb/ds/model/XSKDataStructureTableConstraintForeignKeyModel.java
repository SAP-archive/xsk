/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.model;

/**
 * The Data Structure Table Constraint Foreign Key Model.
 */
public class XSKDataStructureTableConstraintForeignKeyModel extends XSKDataStructureTableConstraintModel {

	private String referencedTable;
	
	private String[] referencedColumns;

	/**
	 * Gets the referenced table.
	 *
	 * @return the referenced table
	 */
	public String getReferencedTable() {
		return referencedTable;
	}

	/**
	 * Sets the referenced table.
	 *
	 * @param referencedTable the new referenced table
	 */
	public void setReferencedTable(String referencedTable) {
		this.referencedTable = referencedTable;
	}

	/**
	 * Gets the referenced columns.
	 *
	 * @return the referenced columns
	 */
	public String[] getReferencedColumns() {
		return (referencedColumns != null) ? referencedColumns.clone() : null;
	}

	/**
	 * Sets the referenced columns.
	 *
	 * @param referencedColumns the new referenced columns
	 */
	public void setReferencedColumns(String[] referencedColumns) {
		this.referencedColumns = referencedColumns;
	}

}
