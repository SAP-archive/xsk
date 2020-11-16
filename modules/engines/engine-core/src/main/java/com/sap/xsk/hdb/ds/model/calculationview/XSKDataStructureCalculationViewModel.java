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
package com.sap.xsk.hdb.ds.model.calculationview;

import com.sap.xsk.hdb.ds.model.XSKDataStructureModel;

/**
 * The calculation view model representation.
 */
public class XSKDataStructureCalculationViewModel extends XSKDataStructureModel {

	private String xml;

	/**
	 * Getter for the XML content.
	 *
	 * @return the XML content
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * Setter for the XML content.
	 *
	 * @param xml the XML content
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}

}
