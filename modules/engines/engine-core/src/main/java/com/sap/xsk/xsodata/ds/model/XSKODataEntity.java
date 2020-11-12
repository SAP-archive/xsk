package com.sap.xsk.xsodata.ds.model;

import java.util.ArrayList;
import java.util.List;

public class XSKODataEntity {
	
	private String namespace;
	
	private String name;
	
	private String alias;
	
	private List<XSKODataNavigation> navigates = new ArrayList<XSKODataNavigation>();

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
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}


	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}


	/**
	 * @return the navigates
	 */
	public List<XSKODataNavigation> getNavigates() {
		return navigates;
	}	

}
