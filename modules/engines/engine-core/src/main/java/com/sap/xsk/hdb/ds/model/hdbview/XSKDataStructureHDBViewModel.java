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
package com.sap.xsk.hdb.ds.model.hdbview;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

/**
 * The view model representation.
 */
public class XSKDataStructureHDBViewModel extends XSKDataStructureModel {

	private String query;

	/**
	 * Getter for the query field.
	 *
	 * @return the SQL query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Setter for the query field.
	 *
	 * @param query            the SQL query
	 */
	public void setQuery(String query) {
		this.query = query;
	}

}
