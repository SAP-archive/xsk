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
package com.sap.xsk.xsodata.ds.api;

import java.util.List;

import org.eclipse.dirigible.commons.api.service.ICoreService;

import com.sap.xsk.xsodata.ds.model.XSKODataModel;

/**
 * The XSK OData Core Service interface.
 */
public interface IXSKODataCoreService extends ICoreService {

	// OData

	/**
	 * Creates the odata.
	 *
	 * @param location
	 *            the location
	 * @param name
	 *            the name
	 * @param hash
	 *            the hash
	 * @return the data structure odata model
	 * @throws XSKODataException
	 *             the data structures exception
	 */
	public XSKODataModel createOData(String location, String name, String hash) throws XSKODataException;

	/**
	 * Gets the odata.
	 *
	 * @param location
	 *            the location
	 * @return the odata
	 * @throws XSKODataException
	 *             the data structures exception
	 */
	public XSKODataModel getOData(String location) throws XSKODataException;

	/**
	 * Gets the odata by name.
	 *
	 * @param name
	 *            the name
	 * @return the odata by name
	 * @throws XSKODataException
	 *             the data structures exception
	 */
	public XSKODataModel getODataByName(String name) throws XSKODataException;

	/**
	 * Exists odata.
	 *
	 * @param location
	 *            the location
	 * @return true, if successful
	 * @throws XSKODataException
	 *             the data structures exception
	 */
	public boolean existsOData(String location) throws XSKODataException;

	/**
	 * Removes the odata.
	 *
	 * @param location
	 *            the location
	 * @throws XSKODataException
	 *             the data structures exception
	 */
	public void removeOData(String location) throws XSKODataException;

	/**
	 * Update odata.
	 *
	 * @param location
	 *            the location
	 * @param name
	 *            the name
	 * @param hash
	 *            the hash
	 * @throws XSKODataException
	 *             the data structures exception
	 */
	public void updateOData(String location, String name, String hash) throws XSKODataException;

	/**
	 * Gets the odatas.
	 *
	 * @return the odatas
	 * @throws XSKODataException
	 *             the data structures exception
	 */
	public List<XSKODataModel> getODatas() throws XSKODataException;

	/**
	 * Parses the odata.
	 *
	 * @param location
	 *            the location
	 * @param content
	 *            the content
	 * @return the data structure odata model
	 * @throws Exception 
	 */
	public XSKODataModel parseOData(String location, String content) throws Exception;


}
