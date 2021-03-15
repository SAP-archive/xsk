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

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

/**
 * The table model representation.
 */
public class XSKDataStructureHDBTableModel extends XSKDataStructureModel {
	
	private String tableType;
	
	private String description;

	private List<XSKDataStructureHDBTableColumnModel> columns = new ArrayList<XSKDataStructureHDBTableColumnModel>();

	private XSKDataStructureHDBTableConstraintsModel constraints = new XSKDataStructureHDBTableConstraintsModel();

	/**
	 * Getter for the columns.
	 *
	 * @return the columns
	 */
	public List<XSKDataStructureHDBTableColumnModel> getColumns() {
		return columns;
	}

	/**
	 * Gets the constraints.
	 *
	 * @return the constraints
	 */
	public XSKDataStructureHDBTableConstraintsModel getConstraints() {
		return constraints;
	}

	/**
	 * @return the tableType
	 */
	public String getTableType() {
		return tableType;
	}

	/**
	 * @param tableType the tableType to set
	 */
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public void setColumns(List<XSKDataStructureHDBTableColumnModel> columns) {
		this.columns = columns;
	}
}
