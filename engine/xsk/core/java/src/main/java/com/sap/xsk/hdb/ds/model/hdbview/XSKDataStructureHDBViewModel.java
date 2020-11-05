/**
 * Copyright (c) 2010-2019 SAP
 */
package com.sap.xsk.hdb.ds.model.hdbview;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

/**
 * The view model representation.
 */
public class XSKDataStructureHDBViewModel extends XSKDataStructureModel {

	private String query;

	/**
	 * Getter for the query field.
	 *
	 * @return the SQL query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Setter for the query field.
	 *
	 * @param query            the SQL query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

}
