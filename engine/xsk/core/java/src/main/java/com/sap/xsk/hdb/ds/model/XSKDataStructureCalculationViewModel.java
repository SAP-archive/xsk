/**
 * Copyright (c) 2010-2019 SAP
 */
package com.sap.xsk.hdb.ds.model;

/**
 * The calculation view model representation.
 */
public class XSKDataStructureCalculationViewModel extends XSKDataStructureModel {

	private String xml;

	/**
	 * Getter for the XML content.
	 *
	 * @return the XML content
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * Setter for the XML content.
	 *
	 * @param xml the XML content
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}

}
