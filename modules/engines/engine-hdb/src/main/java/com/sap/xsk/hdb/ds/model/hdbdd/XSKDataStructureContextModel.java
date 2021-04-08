/*
 * Copyright (c) 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2019-2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdb.ds.model.hdbdd;

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
