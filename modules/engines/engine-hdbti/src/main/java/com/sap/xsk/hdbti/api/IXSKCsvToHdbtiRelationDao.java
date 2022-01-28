/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company and XSK contributors
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License, v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-FileCopyrightText: 2022 SAP SE or an SAP affiliate company and XSK contributors
 * SPDX-License-Identifier: Apache-2.0
 */
package com.sap.xsk.hdbti.api;

import com.sap.xsk.hdbti.model.XSKTableImportArtifact;
import com.sap.xsk.hdbti.model.XSKTableImportToCsvRelation;
import java.util.List;
import javax.sql.DataSource;
import org.eclipse.dirigible.database.persistence.PersistenceManager;

public interface IXSKCsvToHdbtiRelationDao {
    void persistNewCsvAndHdbtiRelations(XSKTableImportArtifact tableImportArtifact);

    void deleteCsvAndHdbtiRelations(String hdbtiFileName);

    List<XSKTableImportToCsvRelation> getAllHdbtiToCsvRelations();

    boolean hasCsvChanged(XSKTableImportToCsvRelation tableImportToCsvRelation, String csvContent);

    List<XSKTableImportToCsvRelation> getAffectedHdbtiToCsvRelations(String csvFilePath);

    PersistenceManager<XSKTableImportToCsvRelation> getXskTableImportToCsvRelationPersistenceManager();

    DataSource getDataSource();
}
