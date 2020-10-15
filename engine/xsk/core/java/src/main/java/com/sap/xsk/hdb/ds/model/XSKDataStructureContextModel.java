/**
 * Copyright (c) 2010-2018 SAP
 */
package com.sap.xsk.hdb.ds.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Data Structure Entities Context Model.
 */
public class XSKDataStructureContextModel {

	private String name;
	
	private Map<String, XSKDataStructureTypeDefinitionModel> types = new HashMap<String, XSKDataStructureTypeDefinitionModel>();
	
	private List<XSKDataStructureEntityModel> entities = new ArrayList<XSKDataStructureEntityModel>();
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the types.
	 *
	 * @return the types
	 */
	public Map<String, XSKDataStructureTypeDefinitionModel> getTypes() {
		return types;
	}
	
	/**
	 * Gets the entities.
	 *
	 * @return the entities
	 */
	public List<XSKDataStructureEntityModel> getЕntities() {
		return entities;
	}
	
	
}
