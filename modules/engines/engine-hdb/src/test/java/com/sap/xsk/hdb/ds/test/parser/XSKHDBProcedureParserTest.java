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
package com.sap.xsk.hdb.ds.test.parser;

import com.sap.xsk.exceptions.XSKArtifactParserException;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.artefacts.HDBProcedureSynchronizationArtefactType;
import com.sap.xsk.hdb.ds.parser.hdbprocedure.XSKHDBProcedureLogger;
import com.sap.xsk.hdb.ds.parser.hdbprocedure.XSKHDBProcedureParser;
import com.sap.xsk.hdb.ds.synchronizer.XSKDataStructuresSynchronizer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

public class XSKHDBProcedureParserTest {

    TestContentProvider contentProvider = new TestContentProvider();
    TestProcedureParser procedureParser = new TestProcedureParser();
    private XSKHDBProcedureParser parser;

    @Before
    public void setup() {
        XSKDataStructuresSynchronizer dataStructuresSynchronizerMock = Mockito.mock(XSKDataStructuresSynchronizer.class);
        doNothing().when(dataStructuresSynchronizerMock).applyArtefactState(any(), any(), any(), any(), any());
        XSKHDBProcedureLogger xskhdbProcedureLogger = Mockito.mock(XSKHDBProcedureLogger.class);
        doNothing().when(xskhdbProcedureLogger).logError(any(), any(), any());

        HDBProcedureSynchronizationArtefactType hdbProcedureSynchronizationArtefactType = new HDBProcedureSynchronizationArtefactType();
        parser = new XSKHDBProcedureParser(dataStructuresSynchronizerMock, hdbProcedureSynchronizationArtefactType, xskhdbProcedureLogger);
    }

    @Test
    public void checkModel() throws IOException, XSKDataStructuresException, XSKArtifactParserException {
        String location = "/OrderProcedure.hdbprocedure";
        String content = contentProvider.getTestContent(location);


        var model = procedureParser.parseProcedure(parser, location, content);
        assertEquals("Unexpected hdbprocedure schema.", "MYSCHEMA", model.getSchema());
        assertEquals("Unexpected hdbprocedure name.", "hdb_view::OrderProcedure", model.getName());
        assertEquals("Unexpected hdbprocedure content.", content, model.getRawContent());
        assertEquals("Unexpected hdbprocedure raw content.", content, model.getRawContent());
        assertEquals("Unexpected hdbprocedure location.", location, model.getLocation());
        assertEquals("Unexpected hdbprocedure type.", "HDBPROC", model.getType());
        assertEquals("Unexpected hdbprocedure dependencies.", 0, model.getDependencies().size());
        assertNotNull("Null value for hdbprocedure createdAt", model.getCreatedAt());
        assertNotNull("Null value for hdbprocedure createdBy", model.getCreatedBy());
        assertEquals("Cant access data structure model class", "class com.sap.xsk.hdb.ds.model.hdbprocedure.XSKDataStructureHDBProcedureModel", parser.getDataStructureClass().toString());
    }


}
