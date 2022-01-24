/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.xsodata.ds.api;

import com.sap.xsk.xsodata.ds.model.XSKODataModel;

import java.util.List;

public interface IXSKODataArtifactDao {

    XSKODataModel createXSKODataArtifact(XSKODataModel tableModel) throws XSKODataException;
    XSKODataModel getXSKODataArtifact(String location) throws XSKODataException;
    XSKODataModel getXSKODataArtifactByName(String name) throws XSKODataException;
    void removeXSKODataArtifact(String location) throws XSKODataException;
    void updateXSKODataArtifact(String location, String name, String hash) throws XSKODataException;
    List<XSKODataModel> getAllXSKODataArtifacts() throws XSKODataException;

}
