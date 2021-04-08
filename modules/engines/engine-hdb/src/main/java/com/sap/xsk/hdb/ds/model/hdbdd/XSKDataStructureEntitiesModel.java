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

import com.sap.xsk.hdb.ds.model.hdbtable.XSKDataStructureHDBTableModel;

/**
 * The table model representation.
 */
public class XSKDataStructureEntitiesModel extends XSKDataStructureHDBTableModel {
	
	private String namespace;
	
	private String schema;
	
	private XSKDataStructureContextModel context;

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	public XSKDataStructureContextModel getContext() {
		return context;
	}
	
	public void setContext(XSKDataStructureContextModel context) {
		this.context = context;
	}

}
