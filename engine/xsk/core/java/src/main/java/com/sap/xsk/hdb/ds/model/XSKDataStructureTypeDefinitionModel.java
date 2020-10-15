package com.sap.xsk.hdb.ds.model;

public class XSKDataStructureTypeDefinitionModel {
	
	private String name;
	
	private String type;
	
	private int length;

	/**
	 * @param name
	 * @param type
	 * @param length
	 */
	public XSKDataStructureTypeDefinitionModel(String name, String type, int length) {
		super();
		this.name = name;
		this.type = type;
		this.length = length;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	

}
