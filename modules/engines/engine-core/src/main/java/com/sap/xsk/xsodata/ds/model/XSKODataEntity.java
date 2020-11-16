/*
 * Copyright (c) 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2020 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
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
