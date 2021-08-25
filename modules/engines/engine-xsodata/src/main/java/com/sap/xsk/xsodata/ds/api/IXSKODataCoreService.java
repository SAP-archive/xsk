/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2021 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsodata.ds.api;

import com.sap.xsk.xsodata.ds.model.XSKODataModel;

import java.util.List;

/**
 * The XSK OData Core Service interface.
 */
public interface IXSKODataCoreService {
    /**
     * Creates the odata.
     *
     * @param location the location
     * @param name     the name
     * @param hash     the hash
     * @return the data structure odata model
     * @throws XSKODataException the data structures exception
     */
    XSKODataModel createOData(String location, String name, String hash) throws XSKODataException;

    /**
     * Gets the odata.
     *
     * @param location the location
     * @return the odata
     * @throws XSKODataException the data structures exception
     */
    XSKODataModel getOData(String location) throws XSKODataException;

    /**
     * Gets the odata by name.
     *
     * @param name the name
     * @return the odata by name
     * @throws XSKODataException the data structures exception
     */
    XSKODataModel getODataByName(String name) throws XSKODataException;

    /**
     * Exists odata.
     *
     * @param location the location
     * @return true, if successful
     * @throws XSKODataException the data structures exception
     */
    boolean existsOData(String location) throws XSKODataException;

    /**
     * Removes the odata.
     *
     * @param location the location
     * @throws XSKODataException the data structures exception
     */
    void removeOData(String location) throws XSKODataException;

    /**
     * Update odata.
     *
     * @param location the location
     * @param name     the name
     * @param hash     the hash
     * @throws XSKODataException the data structures exception
     */
    void updateOData(String location, String name, String hash) throws XSKODataException;

    /**
     * Gets all odata records.
     *
     * @return list of odata
     * @throws XSKODataException the data structures exception
     */
    List<XSKODataModel> getAllODataRecords() throws XSKODataException;

    /**
     * Parses the odata.
     *
     * @param location the location
     * @param content  the content
     * @return the data structure odata model
     * @throws Exception - throws exception
     */
    XSKODataModel parseOData(String location, String content) throws Exception;


}
