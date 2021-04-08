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
package com.sap.xsk.hdb.ds.model.hdbtable;

import java.util.ArrayList;
import java.util.List;

/**
 * The Data Structure Table Constraints Model.
 */
public class XSKDataStructureHDBTableConstraintsModel {

	private XSKDataStructureHDBTableConstraintPrimaryKeyModel primaryKey;

	private List<XSKDataStructureHDBTableConstraintForeignKeyModel> foreignKeys = new ArrayList<XSKDataStructureHDBTableConstraintForeignKeyModel>();

	private List<XSKDataStructureHDBTableConstraintUniqueModel> uniqueIndices = new ArrayList<XSKDataStructureHDBTableConstraintUniqueModel>();

	private List<XSKDataStructureHDBTableConstraintCheckModel> checks = new ArrayList<XSKDataStructureHDBTableConstraintCheckModel>();

	/**
	 * Gets the primary key.
	 *
	 * @return the primary key
	 */
	public XSKDataStructureHDBTableConstraintPrimaryKeyModel getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * Sets the primary key.
	 *
	 * @param primaryKey the new primary key
	 */
	public void setPrimaryKey(XSKDataStructureHDBTableConstraintPrimaryKeyModel primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * Gets the foreign keys.
	 *
	 * @return the foreign keys
	 */
	public List<XSKDataStructureHDBTableConstraintForeignKeyModel> getForeignKeys() {
		return foreignKeys;
	}

	/**
	 * Gets the unique indices.
	 *
	 * @return the unique indices
	 */
	public List<XSKDataStructureHDBTableConstraintUniqueModel> getUniqueIndices() {
		return uniqueIndices;
	}

	public void setUniqueIndices(List<XSKDataStructureHDBTableConstraintUniqueModel> uniqueIndices) {
		this.uniqueIndices = uniqueIndices;
	}

	/**
	 * Gets the checks.
	 *
	 * @return the checks
	 */
	public List<XSKDataStructureHDBTableConstraintCheckModel> getChecks() {
		return checks;
	}

}
