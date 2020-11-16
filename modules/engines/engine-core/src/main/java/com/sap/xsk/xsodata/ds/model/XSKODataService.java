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

public class XSKODataService {
	
	private String namespace;
	
	private List<XSKODataEntity> entities = new ArrayList<XSKODataEntity>();
	
	private List<XSKODataAssociation> associations = new ArrayList<XSKODataAssociation>();

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
	 * @return the entities
	 */
	public List<XSKODataEntity> getEntities() {
		return entities;
	}

	/**
	 * @param entities the entities to set
	 */
	public void setEntities(List<XSKODataEntity> entities) {
		this.entities = entities;
	}

	/**
	 * @return the association
	 */
	public List<XSKODataAssociation> getAssociations() {
		return associations;
	}

	/**
	 * @param associations the associations to set
	 */
	public void setAssociations(List<XSKODataAssociation> associations) {
		this.associations = associations;
	}

}
