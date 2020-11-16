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
package com.sap.xsk.hdbti.api;

import java.io.IOException;
import java.util.List;

import com.sap.xsk.hdbti.model.XSKTableImportArtifact;

public interface IXSKTableImportCoreService {

    XSKTableImportArtifact createTableImportArtifact(XSKTableImportArtifact xskTableImportArtifact) throws XSKTableImportException;

    void updateTableImportArtifact(XSKTableImportArtifact artifact) throws XSKTableImportException;

    XSKTableImportArtifact getTableImportArtifact(String location) throws XSKTableImportException;

    void removeTableImportArtifact(String location);

    List<XSKTableImportArtifact> getTableImportArtifacts() throws XSKTableImportException;

    boolean existsTableImportArtifact(String location) throws XSKTableImportException;

    XSKTableImportArtifact parseTableImportArtifact(String location, String content) throws IOException;
}
