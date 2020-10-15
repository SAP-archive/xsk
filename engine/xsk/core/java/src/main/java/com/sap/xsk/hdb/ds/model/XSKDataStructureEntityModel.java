/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The table model representation.
 */
public class XSKDataStructureEntityModel extends XSKDataStructureModel {

	private List<XSKDataStructureTableColumnModel> columns = new ArrayList<XSKDataStructureTableColumnModel>();

	private XSKDataStructureTableConstraintsModel constraints = new XSKDataStructureTableConstraintsModel();
	
	private String namespace;
	
	private String context;

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
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}
	
	/**
	 * @return the namespace
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * @param namespace the namespace to set
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}
