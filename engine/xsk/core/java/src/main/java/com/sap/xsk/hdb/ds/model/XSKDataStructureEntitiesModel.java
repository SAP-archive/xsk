/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.model;

/**
 * The table model representation.
 */
public class XSKDataStructureEntitiesModel extends XSKDataStructureTableModel {
	
	private String namespace;
	
	private String schema;
	
	private XSKDataStructureContextModel context;

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	public XSKDataStructureContextModel getContext() {
		return context;
	}
	
	public void setContext(XSKDataStructureContextModel context) {
		this.context = context;
	}

}
